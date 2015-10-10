package com.cplatform.mall.back.smsschedule.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.cont.mms.util.TimeUtil;
import com.cplatform.mall.back.smsschedule.dao.BatchTaskScheduleDao;
import com.cplatform.mall.back.smsschedule.entity.BatchTaskSchedule;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.FileUtil;
import com.cplatform.util2.FileTools;
import com.cplatform.util2.zip.ZipFileUtil;

@Service
public class BatchTaskScheduleService {

	private final Logger logger = Logger.getLogger(BatchTaskScheduleService.class.getName());

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private BatchTaskScheduleDao batchTaskScheduleDao;

	@Autowired
	AppConfig appconfig;

	/**
	 * 列表
	 * 
	 * @param smsbuyHelp
	 * @param page
	 * @param defaultPagesize
	 * @return
	 * @throws SQLException
	 */
	public Page<BatchTaskSchedule> list(String userCode, Integer status, String startTime, String endTime, String pStartTime, String pEndTime,
	        Integer page, int pagesize) throws SQLException {
		StringBuilder sql = new StringBuilder(100);
		List<Object> params = new ArrayList<Object>();
		sql.append(" select id, ");
		sql.append("        user_code, ");
		sql.append("        status, ");
		sql.append("        upload_terminal_count, ");
		sql.append("        result_terminal_count, ");
		sql.append("        create_time, ");
		sql.append("        end_time, ");
		sql.append("        upload_file_path, ");
		sql.append("        result_file_path, ");
		sql.append("        date_time, ");
		sql.append("        filter_type, ");
		sql.append("        limit, ");
		sql.append("        failure_reason, ");
		sql.append("        remark ");
		sql.append("   from t_batch_task_schedule ");
		sql.append("   	where status<>0 ");

		// 创建帐号
		if (StringUtils.isNotEmpty(userCode)) {
			params.add("%" + userCode.trim() + "%");
			sql.append("  and user_code like ? ");
		}

		// 创建开始时间
		if (StringUtils.isNotEmpty(startTime)) {
			params.add(startTime.replaceAll("-", "") + "000000");
			sql.append("  and create_time >= ? ");
		}

		// 创建结束时间
		if (StringUtils.isNotEmpty(endTime)) {
			params.add(endTime.replaceAll("-", "") + "235959");
			sql.append("  and create_time <= ? ");
		}

		// 排期开始时间
		if (StringUtils.isNotEmpty(pStartTime)) {
			params.add(pStartTime.replaceAll("-", ""));
			sql.append("  and date_time >= ? ");
		}

		// 排期结束时间
		if (StringUtils.isNotEmpty(pEndTime)) {
			params.add(pEndTime.replaceAll("-", ""));
			sql.append("  and date_time <= ? ");
		}

		// 状态
		if (status != null && status != 0) {
			params.add(status);
			sql.append("  and status = ? ");
		}

		sql.append(" order by create_time desc ");
		return dbHelper.getPage(sql.toString(), BatchTaskSchedule.class, page, pagesize, params.toArray());
	}

	/**
	 * 添加
	 * 
	 * @param smsbuyHelp
	 * @throws Exception
	 * @throws IOException
	 */
	@Transactional
	public void add(BatchTaskSchedule batchTaskSchedule, String userCode, MultipartFile uploadfile,String isZip) throws IOException, Exception {
		batchTaskSchedule.setCreateTime(TimeUtil.now());// 创建时间
		batchTaskSchedule.setUserCode(userCode);// 创建用户帐号
		batchTaskSchedule.setStatus(BatchTaskSchedule.STATUS_VALID);// 默认有效
		batchTaskSchedule.setDateTime(batchTaskSchedule.getDateTime().replaceAll("-", ""));
		batchTaskSchedule.setLimit(BatchTaskSchedule.DEFAULT_LIMIT);

		batchTaskSchedule = batchTaskScheduleDao.save(batchTaskSchedule);
		
		//文件保存路径
		String fileName = uploadfile.getOriginalFilename();
		String fileext = FileTools.getExtFilename(fileName);
		
		String savepath = String.format("%supload/%s/%d."+fileext, appconfig.getUploadMmsScheduleDir(), TimeUtil.nowMonth(), batchTaskSchedule.getId());
		if(StringUtils.equals("zip", fileext) || StringUtils.equals("rar", fileext)) {
			savepath = String.format("%supload/%s/zip/%d/terminal."+fileext, appconfig.getUploadMmsScheduleDir(), TimeUtil.nowMonth(), batchTaskSchedule.getId());
		}
		
		//程序处理结果文件
		String resultFilePath = String.format("%sresult/%s/%d.txt", appconfig.getUploadMmsScheduleDir(), TimeUtil.nowMonth(), batchTaskSchedule.getId());
		if(StringUtils.equals("1", isZip)) {
			resultFilePath = String.format("%sresult/%s/%d.zip", appconfig.getUploadMmsScheduleDir(), TimeUtil.nowMonth(), batchTaskSchedule.getId());
		}
								
		FileTools.write(uploadfile.getBytes(), savepath);
		batchTaskSchedule.setUploadFilePath(String.format("%supload/%s/%d.txt", appconfig.getUploadMmsScheduleDir(), TimeUtil.nowMonth(), batchTaskSchedule.getId()));
		batchTaskSchedule.setResultFilePath(resultFilePath);
		batchTaskScheduleDao.save(batchTaskSchedule);		
		
		if(StringUtils.equals("zip", fileext) || StringUtils.equals("rar", fileext)) {
			String path = String.format("%supload/%s/zip/%d/", appconfig.getUploadMmsScheduleDir(), TimeUtil.nowMonth(),batchTaskSchedule.getId());
			ZipFileUtil.unzip(path+"terminal."+fileext, path, "UTF-8");
			String uploadFileName = getFileName(path);

			if(!StringUtils.isEmpty(uploadFileName)) {
				FileUtil.copy(path+"/"+uploadFileName, batchTaskSchedule.getUploadFilePath());
			}			
		}	
	}
	
	/**
	 * 获得解压后的txt文件
	 * @param path
	 * @return
	 */
	private String getFileName(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		String fileName = "";
		for (int i = 0; i < files.length; i++) {
		  if(!files[i].isDirectory()){		    
		     if(files[i].getName().indexOf(".txt")!=-1) {
		    	 fileName = files[i].getName();
		    	 break;
		     }
		     
		  }
		}
		return fileName;
	}

	/**
	 * 删除
	 * 
	 * @param helpSpcode
	 */
	public void delete(Long id) {
		BatchTaskSchedule batchTaskSchedule = batchTaskScheduleDao.findOne(id);
		batchTaskSchedule.setStatus(BatchTaskSchedule.STATUS_DELETED);
		batchTaskScheduleDao.save(batchTaskSchedule);
	}

	/**
	 * 修改状态
	 * 
	 * @param id
	 */
	public void updateStatus(Long id, Integer status) {
		BatchTaskSchedule batchTaskSchedule = batchTaskScheduleDao.findOne(id);
		batchTaskSchedule.setStatus(status);
		batchTaskScheduleDao.save(batchTaskSchedule);
	}

	/**
	 * 下载文件
	 * 
	 * @param request
	 * @param response
	 * @param filePath
	 *            文件路径
	 * @param realName
	 *            要保存的文件名
	 * @throws Exception
	 */
	public void download(HttpServletRequest request, HttpServletResponse response, String filePath, String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		long fileLength = new File(filePath).length();

		response.setHeader("Content-disposition", "attachment; filename=" + new String(realName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		try {
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		}
		catch (Exception e) {
			logger.error("下载文件出错", e);
		}
		finally {
			bis.close();
			bos.close();
		}
	}

}
