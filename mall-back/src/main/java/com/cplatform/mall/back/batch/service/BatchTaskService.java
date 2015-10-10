package com.cplatform.mall.back.batch.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.batch.dao.BatchTaskDao;
import com.cplatform.mall.back.batch.entity.BatchTask;
import com.cplatform.mall.back.cont.dao.ContMmsDao;
import com.cplatform.mall.back.cont.entity.ContMms;
import com.cplatform.mall.back.order.web.OrderController;
import com.cplatform.mall.back.smsbuy.dao.SmsbuyItemRouterDao;
import com.cplatform.mall.back.smsbuy.entity.SmsbuyItemRouter;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.PathUtil.PathInfo;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.mall.back.websys.service.BsFileService;
import com.cplatform.util2.FileTools;
import com.cplatform.util2.TimeStamp;

@Service("batchTaskService")
public class BatchTaskService {
	private static Logger log = Logger.getLogger(BatchTaskService.class);

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private BatchTaskDao batchTaskDao;

	@Autowired
	private BsFileService bsFileService;

	@Autowired
	private ContMmsDao mmsDao;

	@Autowired
	private SmsbuyItemRouterDao smsbuyItemRouterDao;

	/**
	 * 群发任务查询
	 * 
	 * @param batchTask
	 *            群发任务查询条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws SQLException
	 */
	public Page<BatchTask> findBatchTask(BatchTask batchTask, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql
		        .append("select task.id,task.TASK_NAME,task.title,task.CREATE_TIME,task.STATUS,u.USER_NAME,task.act_id,task.item_id,task.batch_type,task.submit_cnt,task.success_cnt from t_batch_task task,t_sys_user u where task.CREATOR_ID=u.id ");
		sql.append(" and task.TASK_TYPE = ? ");
		List params = new ArrayList();
		params.add(batchTask.getTaskType());
		if (batchTask != null) {
			if (batchTask.getStatus() != null) {
				sql.append(" and task.status = ? ");
				params.add(batchTask.getStatus());
			}

			if (StringUtils.isNotEmpty(batchTask.getTaskName())) {
				sql.append(" and task.task_name like ? ");
				params.add("%" + batchTask.getTaskName().trim() + "%");
			}

			if (StringUtils.isNotEmpty(batchTask.getTitle())) {
				sql.append(" and task.title like ? ");
				params.add("%" + batchTask.getTitle().trim() + "%");
			}

			if (StringUtils.isNotEmpty(batchTask.getUserName())) {
				sql.append(" and u.USER_NAME like ? ");
				params.add("%" + batchTask.getUserName().trim() + "%");
			}
			if (StringUtils.isNotEmpty(batchTask.getStartTime())) {
				sql.append(" and substr(task.create_time,0,8) >= ? ");
				params.add(batchTask.getStartTime().replaceAll("-", "").replace(" ", ""));
			}
			if (StringUtils.isNotEmpty(batchTask.getStopTime())) {
				sql.append(" and substr(task.create_time,0,8) <= ? ");
				params.add(batchTask.getStopTime().replaceAll("-", "").replace(" ", ""));
			}

			// 群发类型 1-普通群发 2-短信购
			if (batchTask.getBatchType() != null && batchTask.getBatchType() != 0) {
				sql.append(" and task.batch_type = ? ");
				params.add(batchTask.getBatchType());
			}

			// 短信购商品id
			if (StringUtils.isNotEmpty(batchTask.getItemId())) {
				sql.append(" and task.item_id like ? ");
				params.add("%" + batchTask.getItemId() + "%");
			}

			// 短信购活动id
			if (batchTask.getActId() != null && batchTask.getActId() != 0) {
				sql.append(" and task.act_id = ? ");
				params.add(batchTask.getActId());
			}

			// 商品指令id
			if (batchTask.getRouterId() != null) {
				sql.append(" and task.ROUTER_ID = ? ");
				params.add(batchTask.getRouterId());
			}

		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_BATCH_TASK));// 控制数据访问
		sql.append(" order by task.create_time desc ");
		return dbHelper.getPage(sql.toString(), BatchTask.class, pageNo, pageSize, params.toArray());
	}

	/**
	 * 生成群发文件
	 * 
	 * @param terminalid
	 *            群发号码
	 * @param blackid
	 *            黑名单号码
	 * @param whiteid
	 *            白名单号码
	 * @param task
	 *            群发任务对象
	 * @throws Exception 
	 */

	public long saveTaskSendFile(String terminalid, String filePath) throws Exception {
		int submitCnt = 0;
		try {
			if (null != terminalid && !terminalid.equals("")) {
				terminalid = terminalid.replaceAll("，", ",");
				submitCnt = terminalid.split(",").length;
				// 把号码写入文件
				terminalid = terminalid.replaceAll(",", "\r\n");// 把输入的号码分隔符","换成行号
				FileTools.writeTxt(terminalid, filePath);
			}
		}
		catch (Exception e) {
			log.error("生成群发文件出错:"+e.getMessage());
		}
		return submitCnt;
	}

	/**
	 * 保存SMS群发任务
	 * 
	 * @param task
	 * @param uploadfile
	 * @param uploadblackfile
	 * @param uploadwhitefile
	 * @param batchType
	 *            （1 普通群发 2 短信购）
	 * @throws Exception
	 */
	@Transactional
	public void saveTask(BatchTask task, MultipartFile uploadfile, MultipartFile uploadblackfile, MultipartFile uploadwhitefile, Long batchType)
	        throws Exception {
		String srcFrom = task.getSrcFrom();
		String IsNewTerminalId = task.getIsNewTerminalId();
		String smsorwapservice[] = task.getService().split("\\|");
		task.setFeeType(Long.valueOf(smsorwapservice[1]));
		task.setServiceId(smsorwapservice[0]);
		task.setFee(Long.valueOf(smsorwapservice[2]));
		task.setStatus(0L);
		task.setSpeed(50L);
		task.setTaskType(1L);
		task.setCreateTime(TimeStamp.getTime(14));
		task.setPriority(10L);
		task.setTraceRespond(1L);
		task.setStartTime(task.getStartTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		task.setStopTime(task.getStopTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		task.setCustomTag(0l);
		task.setBatchType(batchType);
		batchTaskDao.save(task);
		if (IsNewTerminalId.equals("1")) {
			PathInfo filepath = bsFileService.dealModuleFile(uploadfile, BsFileService.MODULE_TASK_BATCH, task.getId());// 群发号码文件路径
			PathInfo whiteFilepath = bsFileService.dealModuleFile(uploadwhitefile, BsFileService.MODULE_TASK_BATCH_WHITE, task.getId());// 白名单号码文件路径
			PathInfo blackFilepath = bsFileService.dealModuleFile(uploadblackfile, BsFileService.MODULE_TASK_BATCH_BLACK, task.getId());// 黑名单号码文件路径

			// 号码文件来源文本文件
			if (srcFrom.equals("1")) {
				if (uploadfile != null && !uploadfile.isEmpty()) {
					List list = FileTools.readLines(filepath.getRealPath());
					task.setSubmitCnt(Long.valueOf(list.size()));
					// task.setMobileListFile(filepath.getRealWebPath(""));
					task.setMobileListFile(filepath.getRealPath());// 存放号码文件绝对路径
				}
				if (uploadwhitefile != null && !uploadwhitefile.isEmpty()) {
					// task.setWhiteListFile(whiteFilepath.getRealWebPath(""));
					task.setWhiteListFile(whiteFilepath.getRealPath());// 存放号码文件绝对路径
				}
				if (uploadblackfile != null && !uploadblackfile.isEmpty()) {
					// task.setBlackListFile(blackFilepath.getRealWebPath(""));
					task.setBlackListFile(blackFilepath.getRealPath());// 存放号码文件绝对路径
				}
			} else {
				task.setSubmitCnt(saveTaskSendFile(task.getTerminalid(), filepath.getRealPath()));
				task.setMobileListFile(filepath.getRealPath());// 存放号码文件绝对路径
				if (null != task.getBlackid() && !"".equals(task.getBlackid())) {
					saveTaskSendFile(task.getBlackid(), blackFilepath.getRealPath());
					// task.setBlackListFile(blackFilepath.getRealWebPath(""));
					task.setBlackListFile(blackFilepath.getRealPath());// 存放号码文件绝对路径
				}
				if (null != task.getWhiteid() && !"".equals(task.getWhiteid())) {
					saveTaskSendFile(task.getWhiteid(), whiteFilepath.getRealPath());
					// task.setWhiteListFile(whiteFilepath.getRealWebPath(""));
					task.setWhiteListFile(whiteFilepath.getRealPath());// 存放号码文件绝对路径

				}
			}

			// task.setMobileListFile(filepath.getSavepath() +
			// filepath.getFilename());
			System.out.println("[文件上传以后路径]------" + filepath.getSavepath() + filepath.getFilename());
			batchTaskDao.save(task);
		}
	}

	/**
	 * 保存SMS群发任务
	 * 
	 * @param task
	 * @param uploadfile
	 * @param uploadblackfile
	 * @param uploadwhitefile
	 * @throws Exception
	 */
	@Transactional
	public void saveMsmTask(BatchTask task, MultipartFile uploadfile, MultipartFile uploadblackfile, MultipartFile uploadwhitefile, Long mmsvalue)
	        throws Exception {
		String IsNewTerminalId = task.getIsNewTerminalId();
		String srcFrom = task.getSrcFrom();
		String smsorwapservice[] = task.getService().split("\\|");
		task.setFeeType(Long.valueOf(smsorwapservice[1]));
		task.setServiceId(smsorwapservice[0]);
		task.setFee(Long.valueOf(smsorwapservice[2]));
		task.setStatus(0L);

		task.setSpeed(50L);
		task.setTaskType(2l);
		task.setCreateTime(TimeStamp.getTime(14));
		task.setPriority(10L);
		task.setTraceRespond(1L);
		task.setStartTime(task.getStartTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		task.setStopTime(task.getStopTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		task.setCustomTag(0l);
		if (null != mmsvalue && !"".equals(mmsvalue)) {
			ContMms mmsInfo = mmsDao.findOne(mmsvalue);
			String mmstitle = mmsInfo.getTitle();
			String mmscontent = mmsInfo.getContentPath();
			String mmssmil = mmsInfo.getSmilName();
			task.setTitle(mmstitle);
			task.setContent(mmscontent);
			task.setSmil(mmssmil);
		}
		task = batchTaskDao.save(task);
		if (IsNewTerminalId.equals("1")) {
			PathInfo filepath = bsFileService.dealModuleFile(uploadfile, BsFileService.MODULE_TASK_BATCH, task.getId());// 群发号码文件路径
			PathInfo whiteFilepath = bsFileService.dealModuleFile(uploadwhitefile, BsFileService.MODULE_TASK_BATCH_WHITE, task.getId());// 白名单号码文件路径
			PathInfo blackFilepath = bsFileService.dealModuleFile(uploadblackfile, BsFileService.MODULE_TASK_BATCH_BLACK, task.getId());// 黑名单号码文件路径

			// 号码文件来源文本文件
			if (srcFrom.equals("1")) {
				if (uploadfile != null && !uploadfile.isEmpty()) {
					List list = FileTools.readLines(filepath.getRealPath());
					task.setSubmitCnt(Long.valueOf(list.size()));
					//task.setMobileListFile(filepath.getRealWebPath(""));
					task.setMobileListFile(filepath.getRealPath());// 存放号码文件绝对路径
				}
				if (uploadwhitefile != null && !uploadwhitefile.isEmpty()) {
					//task.setWhiteListFile(whiteFilepath.getRealPath());
					task.setWhiteListFile(whiteFilepath.getRealPath());// 存放号码文件绝对路径
				}
				if (uploadblackfile != null && !uploadblackfile.isEmpty()) {
					//task.setBlackListFile(blackFilepath.getRealWebPath(""));
					task.setBlackListFile(blackFilepath.getRealPath());// 存放号码文件绝对路径
				}
			} else {
				task.setSubmitCnt(saveTaskSendFile(task.getTerminalid(), filepath.getRealPath()));
				// saveTaskSendFile(task.getTerminalid(),
				// filepath.getRealPath());
				task.setMobileListFile(filepath.getRealPath());// 存放号码文件绝对路径
				if (null != task.getBlackid() && !"".equals(task.getBlackid())) {
					//黑名单文件
					saveTaskSendFile(task.getBlackid(), blackFilepath.getRealPath());
					//task.setBlackListFile(blackFilepath.getRealWebPath(""));
					task.setBlackListFile(blackFilepath.getRealPath());// 存放号码文件绝对路径
				}
				if (null != task.getWhiteid() && !"".equals(task.getWhiteid())) {
					//白名单文件
					saveTaskSendFile(task.getWhiteid(), whiteFilepath.getRealPath());
					//task.setWhiteListFile(whiteFilepath.getRealWebPath(""));
					task.setWhiteListFile(whiteFilepath.getRealPath());// 存放号码文件绝对路径
				}
			}

			batchTaskDao.save(task);
		}
	}

	/**
	 * 查询商品指令信息配置短信购群发任务
	 */
	public SmsbuyItemRouter findOneBuyItemRouter(Long id) throws SQLException {
		String sql = "select ROUTER.*,ACT.ACT_NAME from T_SMSBUY_ITEM_ROUTER ROUTER,T_SMSBUY_ACT_ONLINE ACT where ROUTER.ACT_ID = ACT.ACT_ID and ROUTER.Id = "
		        + id;
		return dbHelper.getBean(sql, SmsbuyItemRouter.class);
	}

	/**
	 * 查询商品指令对应的短信购群发任务信息
	 */
	public BatchTask findBatchTask(Long id) throws SQLException {
		String sql = "select task.*,router.item_name from t_batch_task task,t_smsbuy_item_router router where task.item_id = router.item_id and task.id ="
		        + id;
		return dbHelper.getBean(sql, BatchTask.class);
	}
}
