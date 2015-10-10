package com.cplatform.mall.back.lottery.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.lottery.dao.LotteryActiveDao;
import com.cplatform.mall.back.lottery.dao.LotteryTargetDao;
import com.cplatform.mall.back.lottery.entity.LotteryActive;
import com.cplatform.mall.back.lottery.entity.LotteryActiveConf;
import com.cplatform.mall.back.lottery.entity.LotteryTarget;
import com.cplatform.mall.back.utils.ExcelExportUtil;
import com.cplatform.mall.back.utils.LogUtils;
import com.cplatform.mall.back.utils.PathUtil.PathInfo;
import com.cplatform.mall.back.utils.ReadExcel;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.mall.back.websys.service.BsFileService;
//import com.cplatform.mall.back.lottery.bean.Item;
//import com.cplatform.mall.back.lottery.bean.Template;

/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-7-22
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Service(value="lotteryActiveService")
public class LotteryActiveService {
	@Autowired
	private DbHelper dbHelper;
	
	@Autowired
	private LotteryActiveDao lotteryActiveDao;
	
	@Autowired
	private LotteryTargetDao lotteryTargetDao;
	
	@Autowired
	private LotteryTargetService lotteryTargetService;
	
	@Autowired
	private LotteryActiveConfService lotteryActiveConfService;
	@Autowired
	private BsFileService bsFileService;
	
	
	@Autowired
    private	LogUtils  logUtils;
	
	/**
	 * 活动列表查询
	 * @param createMemberId	创建者id
	 * @param lotteryActive
	 * @param pageNo
	 * @param pageSize
	 * @param status
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public Page<LotteryActive> listLotteryActive(LotteryActive lotteryActive, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.* from t_lottery_active t1 ");
		sql.append(" where 1 =1 ");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.isNotEmpty(lotteryActive.getName())){
			sql.append(" and t1.name like ? ");
			params.add("%"+lotteryActive.getName().trim()+"%");
		}
		if(lotteryActive.getId()!=null){
			sql.append(" and t1.id=? ");
			params.add(lotteryActive.getId());
		}
		if (lotteryActive.getStatus()!=null) {
			sql.append(" and t1.status=? ");
			params.add(lotteryActive.getStatus());
		}
		if (StringUtils.isNotEmpty(lotteryActive.getStartTime())) {
			sql.append(" and t1.start_time >= ? ");
			params.add(lotteryActive.getStartTime()+"000000");
		}
		if (StringUtils.isNotEmpty(lotteryActive.getStopTime())) {
			sql.append(" and t1.stop_time <= ? ");
			params.add(lotteryActive.getStopTime()+"235959");
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_LOTTERY));
		sql.append(" order by t1.id desc ");
		return dbHelper.getPage(sql.toString(), LotteryActive.class, pageNo, pageSize, params.toArray());
	}
	
	public Page<LotteryActive> listLotteryActiveAndConfig(LotteryActive lotteryActive, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.id id, ");
		sql.append(" t.name name, ");
		sql.append(" t1.value prizeNumValue");
		sql.append(" from t_lottery_active t ");
		sql.append(" join t_lottery_active_conf t1 ");
		sql.append(" on t.id = t1.active_id ");
		sql.append(" where t1.key = 'prizeNum' ");
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_LOTTERY));
		if(StringUtils.isNotEmpty(lotteryActive.getName())){
			sql.append(" and t.name like ? ");
			params.add("%"+lotteryActive.getName()+"%");
		}
		if(lotteryActive.getId()!=null){
			sql.append(" and t.id=? ");
			params.add(lotteryActive.getId());
		}
		sql.append(" order by t1.id desc ");
		return dbHelper.getPage(sql.toString(), LotteryActive.class, pageNo, pageSize, params.toArray());
	}
	public LotteryActive findActiveAndConfigById(Long id) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.value prizeNumValue ");
		sql.append(" from t_lottery_active t ");
		sql.append(" join t_lottery_active_conf t1 ");
		sql.append(" on t.id = t1.active_id ");
		sql.append(" where t1.key = 'prizeNum' ");
		sql.append(" and t.id = ? ");
		return dbHelper.getBean(sql.toString(), LotteryActive.class, new Object[]{id});
	}
	
	/**
	 * 保存
	 * @param lotteryActive
	 */
	public void save(LotteryActive lotteryActive){
		lotteryActiveDao.save(lotteryActive);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public LotteryActive findById(Long id){
		LotteryActive lotteryActive=lotteryActiveDao.findOne(id);
		return lotteryActive;
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Long id){
		lotteryActiveDao.delete(id);
	}
	
	/**
	 * 上传文件
	 * @param tempflag	模板标识（根据xml配置文件分辨）
	 * @param filerequest
	 * @param request
	 * @param uploadfile
	 * @param lotteryActive
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean excellUpload(String prizeNum,MultipartFile bgImg,MultipartFile pointerImg,MultipartFile hitImg,HttpServletRequest request,
			MultipartFile uploadfile,LotteryActive lotteryActive) throws Exception{
		//如果有id说明是修改操作，修改时要先删除target表中该活动相关信息，然后重新插入数据
		if(lotteryActive.getId()!=null && uploadfile!=null){
			lotteryTargetService.deleteByActiveId(lotteryActive.getId());
		}
		lotteryActiveDao.save(lotteryActive);
		
		//保存抽奖图片
		PathInfo filepath = null;
		LotteryActiveConf lotteryActiveConf=null;
		if(bgImg!=null && StringUtils.isNotEmpty(bgImg.getOriginalFilename())){
			List<LotteryActiveConf> confs=lotteryActiveConfService.findByActiveIdAndKey(lotteryActive.getId(), "bgImg");
			if(confs!=null && confs.size()>0){
				lotteryActiveConf=confs.get(0);
			}else{
				lotteryActiveConf=new LotteryActiveConf();
				lotteryActiveConf.setActiveId(lotteryActive.getId());
				lotteryActiveConf.setKey("bgImg");
			}
			filepath = bsFileService.LotteryActive(bgImg, BsFileService.LOTTERY_ACTIVE_CONF_KEY, lotteryActive.getId());
			lotteryActiveConf.setValue(filepath.getWebPath()+filepath.getFilename());
			lotteryActiveConfService.save(lotteryActiveConf);
		}
		if(pointerImg!=null && StringUtils.isNotEmpty(pointerImg.getOriginalFilename())){
			List<LotteryActiveConf> confs=lotteryActiveConfService.findByActiveIdAndKey(lotteryActive.getId(), "pointerImg");
			if(confs!=null && confs.size()>0){
				lotteryActiveConf=confs.get(0);
			}else{
				lotteryActiveConf=new LotteryActiveConf();
				lotteryActiveConf.setActiveId(lotteryActive.getId());
				lotteryActiveConf.setKey("pointerImg");
			}
			filepath = bsFileService.LotteryActive(pointerImg, BsFileService.LOTTERY_ACTIVE_CONF_KEY, lotteryActive.getId());
			lotteryActiveConf.setValue(filepath.getWebPath()+filepath.getFilename());
			lotteryActiveConfService.save(lotteryActiveConf);
		}
		if(hitImg!=null && StringUtils.isNotEmpty(hitImg.getOriginalFilename())){
			List<LotteryActiveConf> confs=lotteryActiveConfService.findByActiveIdAndKey(lotteryActive.getId(), "hitImg");
			if(confs!=null && confs.size()>0){
				lotteryActiveConf=confs.get(0);
			}else{
				lotteryActiveConf=new LotteryActiveConf();
				lotteryActiveConf.setActiveId(lotteryActive.getId());
				lotteryActiveConf.setKey("hitImg");
			}
			filepath = bsFileService.LotteryActive(hitImg, BsFileService.LOTTERY_ACTIVE_CONF_KEY, lotteryActive.getId());
			lotteryActiveConf.setValue(filepath.getWebPath()+filepath.getFilename());
			lotteryActiveConfService.save(lotteryActiveConf);
		}

		//保存奖区数量
		if(prizeNum!=null){
			List<LotteryActiveConf> confs=lotteryActiveConfService.findByActiveIdAndKey(lotteryActive.getId(), "prizeNum");
			if(confs!=null && confs.size()>0){
				lotteryActiveConf=confs.get(0);
			}else{
				lotteryActiveConf=new LotteryActiveConf();
				lotteryActiveConf.setActiveId(lotteryActive.getId());
				lotteryActiveConf.setKey("prizeNum");
			}
			lotteryActiveConf.setValue(prizeNum);
			lotteryActiveConfService.save(lotteryActiveConf);
		}
		
		
		//保存导入活动用户信息
		boolean flag=false;
		if(uploadfile!=null && !uploadfile.isEmpty() && StringUtils.isNotEmpty(uploadfile.getOriginalFilename())){
			List<String[]> lists=ReadExcel.getData(uploadfile.getInputStream(), 1, 0);
			ArrayList<LotteryTarget> lotteryTargets=new ArrayList<LotteryTarget>();
			for(String[] strs:lists){
				LotteryTarget lotteryTarget=new LotteryTarget();
				lotteryTarget.setPhone(strs[0]);
				lotteryTarget.setActiveId(lotteryActive.getId());
				lotteryTarget.setNumHad(Long.valueOf(strs[1]));
				lotteryTarget.setNumCould(Long.valueOf(strs[2]));
				lotteryTargets.add(lotteryTarget);
			}
			//当所有target都解析正确后，保存到数据库
			for(LotteryTarget t:lotteryTargets){
				lotteryTargetDao.save(t);
			}
			flag=true;
		}
		return flag;
		
	}
	
	/**
	 * 下载csv文件
	 */
	public void csvdownload(HttpServletRequest request,  HttpServletResponse response,Long activeId)  {
		BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        String dir = request.getSession().getServletContext().getRealPath("")+"/";
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        String csvname=format.format(new Date())+".csv";
        File file=new File(dir,csvname); 
        try {
        	response.setContentType("text/html;charset=UTF-8"); 
			request.setCharacterEncoding("UTF-8");
			if(!file.exists()){
	            file.createNewFile();
	        }
            //导出数据
	        ArrayList<LotteryTarget> lotteryTargets=(ArrayList<LotteryTarget>) lotteryTargetService.findByActiveId(activeId);
			BufferedWriter bw=new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(file)));
			bw.write("手机号"+"\n");
			for(LotteryTarget t:lotteryTargets){
//				bw.write(t.getId()+","+t.getActiveId()+","+t.getPhone()+","+t.getNumHad()+","+t.getNumCould()+"\n");
				bw.write(t.getPhone()+"\n");
			}
			bw.flush();
			bw.close();
	        //下载文件
	        response.setContentType("text/plain; charset=utf-8");  
	        response.setHeader("Content-disposition", "attachment; filename="  
	                + new String(csvname.getBytes("utf-8"), "ISO8859-1"));  
	        bis = new BufferedInputStream(new FileInputStream(dir+csvname));  
	        bos = new BufferedOutputStream(response.getOutputStream());  
	        byte[] buff = new byte[1024];  
	        int bytesRead;  
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	            bos.write(buff, 0, bytesRead);  
	        }  
		} catch (Exception e) {
			logUtils.logOther("下载目标库名单", "id:",activeId);
			e.printStackTrace();
		}finally{
			try {
				if(bis!=null){
					bis.close();
				}
				if(bos!=null){
					bos.close();
				}
				if(file.exists()){
					file.delete();
				}
				
			} catch (IOException e) {
				logUtils.logOther("下载目标库名单", "id:",activeId);
				e.printStackTrace();
			}  
	          
		}
		
	}
	
	
	public void download(Long activeId,HttpServletRequest request,HttpServletResponse response) throws Exception{
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select t.phone,t.num_had,t.num_could from t_lottery_target t ");
		sqlBuff.append(" where t.active_id=  "+activeId);
		sqlBuff.append(" order by t.id asc ");
		String[] head = {"手机号","已抽奖次数","可抽奖次数"};
		ExcelExportUtil.exportExcel(dbHelper.getConn(),head,"lottery_", sqlBuff.toString(),request,response);
	}
	
}
