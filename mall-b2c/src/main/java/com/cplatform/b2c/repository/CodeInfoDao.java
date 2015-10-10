package com.cplatform.b2c.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.CodeInfo;
import com.cplatform.dbhelp.DbHelper;

/**
 * 发码，退码. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-20 上午9:40:50
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Repository
public class CodeInfoDao {

	@Autowired
	private DbHelper dbHelper;

	/**
	 * 根据码表ID查找
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public CodeInfo findCodeById(String id) throws SQLException {
		String sql = "SELECT * FROM T_VERIFY_CODE_INFO t WHERE t.order_id = ? ";
		return dbHelper.getBean(sql, CodeInfo.class, id);
	}

	/**
	 * 查询订单中有效码
	 * 
	 * @param actOrderId
	 *            业务订单id
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listValidCodesByActOrderId(Long actOrderId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder(100);
		List<Object> params = new ArrayList<Object>();
		sqlBuff.append(" select t.* ");
		sqlBuff.append(" from T_VERIFY_CODE_INFO t ");
		sqlBuff.append(" where t.code_status=0 ");
		if (null != actOrderId) {
			sqlBuff.append(" and t.ACT_ORDER_ID=? ");
			params.add(actOrderId);
		}
		return dbHelper.getBeanList(sqlBuff.toString(), CodeInfo.class, params.toArray());
	}

	/**
	 * 根据业务订单号查询码（只包含商品订单号、业务订单号、发码平台字段）
	 * 
	 * @param actOrderId
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listCodesByActOrderId(Long actOrderId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder(100);
		List<Object> params = new ArrayList<Object>();
		sqlBuff.append(" select t.ITEM_ORDER_ID,t.ACT_ORDER_ID,t.PLATFORM_ID ");
		sqlBuff.append(" from T_VERIFY_CODE_INFO t ");
		sqlBuff.append(" where t.ACT_ORDER_ID=? ");
		params.add(actOrderId);
		sqlBuff.append(" group by  t.ITEM_ORDER_ID,t.ACT_ORDER_ID,t.PLATFORM_ID ");
		return dbHelper.getBeanList(sqlBuff.toString(), CodeInfo.class, params.toArray());
	}

	/**
	 * 根据业务订单号查询码
	 * 
	 * @param actOrderId
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listOrderCodesByActOrderId(Long actOrderId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder(100);
		List<Object> params = new ArrayList<Object>();
		sqlBuff.append(" select t.* ");
		sqlBuff.append(" from T_VERIFY_CODE_INFO t ");
		sqlBuff.append(" where 1 = 1 ");
		if (null != actOrderId) {
			sqlBuff.append(" and t.ACT_ORDER_ID=? ");
			params.add(actOrderId);
		}
		return dbHelper.getBeanList(sqlBuff.toString(), CodeInfo.class, params.toArray());
	}

	/**
	 * 查询订单中虚拟商品的验证码
	 * 
	 * @param storeCode
	 * @return
	 * @throws SQLException
	 */
	public List<CodeInfo> listStoreCodes(CodeInfo codeInfo) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder(100);
		List<Object> params = new ArrayList<Object>();
		sqlBuff.append(" SELECT t.* FROM T_VERIFY_CODE_INFO t WHERE 1 = 1 ");
		if (null != codeInfo.getActOrderId()) {
			sqlBuff.append(" and t.ACT_ORDER_ID=? ");
			params.add(codeInfo.getActOrderId());
		}
		if (null != codeInfo.getItemOrderId()) {
			sqlBuff.append(" and t.ITEM_ORDER_ID=? ");
			params.add(codeInfo.getItemOrderId());
		}
		return dbHelper.getBeanList(sqlBuff.toString(), CodeInfo.class, params.toArray());
	}

}
