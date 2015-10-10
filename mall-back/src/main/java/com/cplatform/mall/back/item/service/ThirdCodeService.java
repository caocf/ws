package com.cplatform.mall.back.item.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.item.dao.ThirdCodeImportDao;
import com.cplatform.mall.back.item.dao.ThirdCodeNameDao;
import com.cplatform.mall.back.item.dao.VerifyCardCodeDao;
import com.cplatform.mall.back.item.dao.VerifyStoreCodeDao;
import com.cplatform.mall.back.item.entity.ThirdCodeImport;
import com.cplatform.mall.back.item.entity.ThirdCodeName;
import com.cplatform.mall.back.item.entity.VerifyCardCode;
import com.cplatform.mall.back.item.entity.VerifyStoreCode;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.utils.ReadExcel;
import com.cplatform.util2.TimeStamp;
import com.cplatform.util2.security.MD5;
import com.cplatform.verifycode.Encrypt;

/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-10-21
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Service
public class ThirdCodeService {
	private static Logger log = Logger.getLogger(ThirdCodeService.class);
	@Autowired
	private DbHelper dbHelper;
	@Autowired
	private ThirdCodeNameDao thirdCodeNameDao;
	@Autowired
	private ThirdCodeImportDao thirdCodeImportDao;
	@Autowired
	private VerifyStoreCodeDao verifyStoreCodeDao;
	@Autowired
	private VerifyCardCodeDao verifyCardCodeDao;
	
	public void saveThirdCodeName(ThirdCodeName thirdCodeName){
		thirdCodeNameDao.save(thirdCodeName);
	}
	/**
	 * 查询全部码名称
	 * @return
	 */
	public List<ThirdCodeName> listAllThirdCodeName(){
		return thirdCodeNameDao.listByNameAsc();
	}
	
	/**
	 * 根据码名称查询导入记录
	 * @param codeNameId
	 * @return
	 */
	public List<ThirdCodeImport> findByCodeNameId(Long codeNameId){
		return thirdCodeImportDao.findByNameId(codeNameId);
	}
	
	public ThirdCodeImport findCodeImportById(Long id){
		return thirdCodeImportDao.findOne(id);
	}
	
	/**
	 * 根据码名称编号查询
	 * @param id
	 * @return
	 */
	public ThirdCodeName findCodeNameById(Long id){
		return thirdCodeNameDao.findOne(id);
	}
	
	public void saveStoreCode(VerifyStoreCode verifyStoreCode){
		verifyStoreCodeDao.save(verifyStoreCode);
	}
	
	public VerifyStoreCode findStoreCodeById(String codeMd5){
		return verifyStoreCodeDao.findOne(codeMd5);
	}
	
	public void saveCardCode(VerifyCardCode verifyCardCode){
		verifyCardCodeDao.save(verifyCardCode);
	}
	
	public VerifyCardCode findCardCodeByCardId(String cardId){
		return verifyCardCodeDao.findCardCodeByCardId(cardId);
	}
	
	public void saveThirdCodeImport(ThirdCodeImport thirdCodeImport){
		thirdCodeImportDao.save(thirdCodeImport);
	}
	
	
	public List<ThirdCodeName> findCodeNameByCodeType(Long codeType) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct name.* from t_verify_code_name name ");
		sql.append(" where name.code_type=?  ");
		List<Object> params = new ArrayList<Object>();
		params.add(codeType);
		sql.append(" order by name.id desc");
		return dbHelper.getBeanList(sql.toString(), ThirdCodeName.class, params.toArray());
	}
	/**
	 * 根据商户id查询第三方码名称
	 * @param storeId
	 * @return
	 * @throws SQLException
	 */
	public Page<ThirdCodeName> findCodeNameByStoreId(Long storeId,int page, int defaultPagesize) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct name.* from t_verify_code_import import ");
		sql.append(" join t_verify_code_name name ");
		sql.append(" on import.code_name_id=name.id ");
		sql.append(" where import.store_id=?  ");
		List<Object> params = new ArrayList<Object>();
		params.add(storeId);
		Page<ThirdCodeName> pageData = dbHelper.getPage(sql.toString(), ThirdCodeName.class, page, defaultPagesize, params.toArray());
		return pageData;
	}
	
	public Page<ThirdCodeName> listCodeNames(ThirdCodeName thirdCodeName, int page, int defaultPagesize) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select name.id,name.code_type codeType,store.name storeName,name.name,name.create_time,sum(import.total_count) num  ");
		sql.append(" from t_verify_code_name name  ");
		sql.append(" join t_verify_code_import import ");
		sql.append(" on name.id=import.code_name_id ");
		sql.append(" join t_store store ");
		sql.append(" on import.store_id=store.id ");
		sql.append(" where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(null!=thirdCodeName.getId()){
			sql.append(" and name.id=? ");
			params.add(thirdCodeName.getId());
		}
		if(StringUtils.isNotEmpty(thirdCodeName.getName())){
			sql.append(" and name.name like ? ");
			params.add("%"+thirdCodeName.getName().trim()+"%");
		}
		if (StringUtils.isNotEmpty(thirdCodeName.getBeginTime())) {
			sql.append(" and name.create_time > ?  ");
			params.add(thirdCodeName.getBeginTime()+"000000");
		}
		if (StringUtils.isNotEmpty(thirdCodeName.getEndTime())) {
			sql.append(" and name.create_time < ?  ");
			params.add(thirdCodeName.getEndTime()+"235959");
		}
		sql.append(" group by name.id,name.name,name.create_time,store.name,name.code_type ");
		sql.append(" order by name.id desc ");
		Page<ThirdCodeName> pageData = dbHelper.getPage(sql.toString(), ThirdCodeName.class, page, defaultPagesize, params.toArray());
		return pageData;
	}
	
	public Page<ThirdCodeImport> viewCodeNames(ThirdCodeImport thirdCodeImport, int page, int defaultPagesize) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select store.name storeName,import.*,name.name codeName,us.user_name userName");
		sql.append(" from t_verify_code_import import ");
		sql.append(" join t_verify_code_name name on import.code_name_id=name.id ");
		sql.append(" join t_sys_user us on import.user_id=us.id ");
		sql.append(" join t_store store on store.id=import.store_id ");
		sql.append(" where  1 =1 ");
		List<Object> params = new ArrayList<Object>();
		if(null!=thirdCodeImport.getCodeNameId()){
			sql.append(" and import.code_name_id=? ");
			params.add(thirdCodeImport.getCodeNameId());
		}
		if(null!=thirdCodeImport.getCodeType()){
			sql.append(" and import.code_type=? ");
			params.add(thirdCodeImport.getCodeType());
		}
		sql.append(" order by import.id desc ");
		Page<ThirdCodeImport> pageData = dbHelper.getPage(sql.toString(), ThirdCodeImport.class, page, defaultPagesize, params.toArray());
		return pageData;
	}
	
	/**
	 * 解析上传的excell
	 * @param storeId	商户id
	 * @param uploadfile	上传excell对象
	 * @param name	新建验证码名称
	 * @param nameId	选择的验证码
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String importExcell(Long codeType,Long storeId,MultipartFile uploadfile,String name,Long nameId,HttpServletRequest request) throws Exception {
		String result="";
		List<String[]> codes=ReadExcel.getData(uploadfile.getInputStream(), 0, 0);
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		log.info("导入码数量:"+codes.size());
		//保存码名称
		ThirdCodeName thirdCodeName=null;
		if(StringUtils.isNotEmpty(name.trim())){
			thirdCodeName=new ThirdCodeName();
			thirdCodeName.setName(name.trim());
			thirdCodeName.setCreateTime(TimeStamp.getTime(14));
			thirdCodeName.setCodeType(codeType);
			thirdCodeName.setUserId(sessionUser.getId());
			this.saveThirdCodeName(thirdCodeName);
		}else{
			thirdCodeName=this.findCodeNameById(nameId);
		}
		//判断码名称和商户是否一致
		List<ThirdCodeImport> imports=this.findByCodeNameId(thirdCodeName.getId());
		if(imports!=null &&imports.size()>0){
			ThirdCodeImport codeImport=imports.get(0);
			if(!codeImport.getStoreId().equals(storeId)){
				return null;
			}
		}
		//保存ThirdCodeImport
		ThirdCodeImport thirdCodeImport=new ThirdCodeImport();
		thirdCodeImport.setCodeNameId(thirdCodeName.getId());
		thirdCodeImport.setCodeType(codeType);
		thirdCodeImport.setStoreId(storeId);
		thirdCodeImport.setCreateTime(TimeStamp.getTime(14));
		thirdCodeImport.setFilePath(uploadfile.getOriginalFilename());
		thirdCodeImport.setUserId(sessionUser.getId());
		thirdCodeImport.setTotalCount((long)codes.size());
		thirdCodeImport.setCodeName(thirdCodeName.getName());
		this.saveThirdCodeImport(thirdCodeImport);
		//导入码文件中可用码数量
		long codeNum=0;
		//保存VerifyStoreCode或VerifyCardCode
		for(String[] strs:codes){
			//非卡密
			if(codeType==2){
				//查询码是否存在，如果存在不插入
				VerifyStoreCode code=this.findStoreCodeById(MD5.digest2Str(strs[0]));
				if(code==null){
					code=new VerifyStoreCode();
					code.setCodeMd5(MD5.digest2Str(strs[0]));
					code.setCodeRsa(Encrypt.encrypt(strs[0]));
					code.setCreateDate(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
					code.setStatus("100");
					code.setUserId(sessionUser.getId().toString());
					code.setStoreId(storeId+"");
					code.setImportId(thirdCodeImport.getId());
					code.setCodeNameId(thirdCodeName.getId());
					this.saveStoreCode(code);
					codeNum++;
				}
			}
			//卡密
			if(codeType==3){
				//查询卡号是否存在，如果存在不插入
				VerifyCardCode code=this.findCardCodeByCardId(strs[0]);
				if(code ==null){
					code=new VerifyCardCode();
					code.setCardId(strs[0]);
					code.setCardKey(Encrypt.encrypt(strs[1]));
					code.setCodeNameId(thirdCodeName.getId());
					code.setStatus(100);
					code.setUserId(sessionUser.getId().toString());
					code.setStoreId(storeId+"");
					code.setImportId(thirdCodeImport.getId());
					code.setCreateDate(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
					code.setGenerateDate(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
					verifyCardCodeDao.save(code);
					codeNum++;
				}
			}
			
		}
		//更新导入文件中可用码数量
		thirdCodeImport=this.findCodeImportById(thirdCodeImport.getId());
		thirdCodeImport.setTotalCount(codeNum);
		this.saveThirdCodeImport(thirdCodeImport);
		
		result="导入码总数量为"+codes.size()+",成功数量为"+codeNum;
		return result;
	}
	
	
	public Page<Store> selectStore(Store store, int page, int defaultPagesize) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select store.id,store.name,region.region_name areaName from t_store store ");
		sql.append(" join t_sys_region region on store.area_code=region.region_code ");
		sql.append(" where store.id in ");
		sql.append(" (select distinct(store_id) from t_store_code  where send_channel_id=2) ");
		List<Object> params = new ArrayList<Object>();
		if(null!=store.getId()){
			sql.append(" and store.id=? ");
			params.add(store.getId());
		}
		if(StringUtils.isNotEmpty(store.getName())){
			sql.append(" and store.name like ? ");
			params.add("%"+store.getName().trim()+"%");
		}
		sql.append(" order by store.id desc ");
		Page<Store> pageData = dbHelper.getPage(sql.toString(), Store.class, page, defaultPagesize, params.toArray());
		return pageData;
	}

}
