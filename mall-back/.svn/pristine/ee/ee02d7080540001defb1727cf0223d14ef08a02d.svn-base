package com.cplatform.mall.back.order.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.order.dao.CodeInfoDao;
import com.cplatform.mall.back.order.entity.CodeInfo;
import com.cplatform.mall.back.sys.entity.OpenCustomer;

/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-8-16
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Service
public class CodeInfoService {
	private static Logger log=Logger.getLogger(CodeInfoService.class);
	
	@Autowired
	private DbHelper dbHelper;
	
	@Autowired
	private CodeInfoDao codeInfoDao;
	
	/**
	 * 根据码编号查询
	 * @param id
	 * @return
	 */
	public CodeInfo findCodeById(String id){
		CodeInfo codeInfo=codeInfoDao.findOne(id);
		return codeInfo;
	}
	
	/**
	 * 保存或更新码
	 * @param codeInfo
	 */
	public void save(CodeInfo codeInfo){
		codeInfoDao.save(codeInfo);
	}
	
	
	/**
	 * 查询订单中虚拟商品的验证码
	 * @param storeCode
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listStoreCodes(CodeInfo codeInfo) throws SQLException{
		StringBuilder sqlBuff = new StringBuilder(100);
		List<Object> params = new ArrayList<Object>();
		sqlBuff.append(" select t.* ");
		sqlBuff.append(" from T_VERIFY_CODE_INFO t ");
		sqlBuff.append(" where 1 = 1 ");
		if(null!=codeInfo.getActOrderId()){
			sqlBuff.append(" and t.ACT_ORDER_ID=? ");
			params.add(codeInfo.getActOrderId());
		}
		if(null!=codeInfo.getItemOrderId()){
			sqlBuff.append(" and t.ITEM_ORDER_ID=? ");
			params.add(codeInfo.getItemOrderId());
		}
		return dbHelper.getBeanList(sqlBuff.toString(),CodeInfo.class, params.toArray());
	}
	
	
	/**
	 * 根据业务订单号查询码（只包含商品订单号、业务订单号、发码平台字段）
	 * @param actOrderId
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listCodesByActOrderId(Long actOrderId) throws SQLException{
		StringBuilder sqlBuff = new StringBuilder(100);
		List<Object> params = new ArrayList<Object>();
		sqlBuff.append(" select t.ITEM_ORDER_ID,t.ACT_ORDER_ID,t.PLATFORM_ID ");
		sqlBuff.append(" from T_VERIFY_CODE_INFO t ");
		sqlBuff.append(" where t.ACT_ORDER_ID=? ");
		params.add(actOrderId);
		sqlBuff.append(" group by  t.ITEM_ORDER_ID,t.ACT_ORDER_ID,t.PLATFORM_ID ");
		return dbHelper.getBeanList(sqlBuff.toString(),CodeInfo.class, params.toArray());
	}
	
	/**
	 * 验证码综合查询
	 * @param storeCode
	 * @return
	 * @throws SQLException
	 */
	public Page<CodeInfo> compositeCodeList(CodeInfo codeInfo, int pageNo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql .append("  select  *  from  T_VERIFY_CODE_INFO  t  ").append("   where 1=1  ");
		
		if(StringUtils.isNotEmpty(codeInfo.getCode())){
			sql.append(" and t.CODE=? ");
			params.add(codeInfo.getCode());
		}
		if(StringUtils.isNotEmpty(codeInfo.getTerminalId())){
			sql.append(" and t.TERMINAL_ID=? ");
			params.add(codeInfo.getTerminalId());
		}
		
		if(null!=codeInfo.getActOrderId()){
			sql.append(" and t.ACT_ORDER_ID=? ");
			params.add(codeInfo.getActOrderId());
		}
		
		if(null!=codeInfo.getPlatformId() &&!"".equals(codeInfo.getPlatformId())){
			sql.append(" and t.PLATFORM_ID=? ");
			params.add(codeInfo.getPlatformId());
		}
		
		if (StringUtils.isNotEmpty(codeInfo.getStartTimeBegin())) {
			sql.append(" and T.TRANS_DATE >= ? ");
			params.add(codeInfo.getStartTimeBegin().replaceAll("-", "") + "");
		}

		if (StringUtils.isNotEmpty(codeInfo.getStartTimeEnd())) {
			sql.append(" and T.TRANS_DATE <= ? ");
			params.add(codeInfo.getStartTimeEnd().replaceAll("-", "") + "");
		}
		
		
		
		if (StringUtils.isNotEmpty(codeInfo.getPastTimeBegin())) {
			sql.append(" and T.EXPIRE_DATE >= ? ");
			params.add(codeInfo.getPastTimeBegin().replaceAll("-", "") + "");
		}

		if (StringUtils.isNotEmpty(codeInfo.getPastTimeEnd())) {
			sql.append(" and T.EXPIRE_DATE <= ? ");
			params.add(codeInfo.getPastTimeEnd().replaceAll("-", "") + "");
		}
		
		return dbHelper.getPage(sql.toString(), CodeInfo.class, pageNo,  Page.DEFAULT_PAGESIZE, params.toArray());
	}
}
