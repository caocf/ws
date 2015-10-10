package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.TMemberAddress;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.order.ActOrderExpressInfo;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.util2.TimeStamp;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-14 上午11:52
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class MemberAddressService {

	@Autowired
	DbHelper dbHelper;

	@Autowired
	AreaService areaService;

	public List<TMemberAddress> findAddressByUser(Long userId) throws SQLException {
		return dbHelper.getBeanList("select * from t_member_address where mid = ? order by update_time desc, id desc", TMemberAddress.class, userId);
	}

	public int findAddressCount(Long userId) throws SQLException {
		String count = dbHelper.queryScalar("select count(1) from t_member_address where mid = ?", userId);
		return Integer.valueOf(count);
	}

	public void delAddress(String id, Long userId) throws SQLException {
		dbHelper.execute("delete from t_member_address where id = ? and mid = ?", id, userId);
	}

	public TMemberAddress findAddress(String id, Long userId) throws SQLException {
		return dbHelper.getBean("select * from t_member_address where id = ? and mid = ?", TMemberAddress.class, id, userId);
	}

	public TMemberAddress addOrUpdateAddress(TMemberAddress address, HttpServletResponse response) throws SQLException {
		String now = TimeStamp.getTime(14);
		address.setMid(SessionUser.getSessionUser(response).getId().intValue());
		address.setUpdateTime(now);
		address.setLastUseTime(now);
		if (address.getId() == null) {
			address.setCreateTime(now);

			String sql = "select SEQ_SYS_COMM_ID.nextval from dual";
			String id = dbHelper.queryScalar(sql);
			address.setId(Integer.valueOf(id));

			sql = "insert into t_member_address (id, mid, remark, region, address, zipcode, name, mobile, phone, update_time, "
			        + "create_time, last_use_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			dbHelper.execute(sql, address.getId(), address.getMid(), address.getRemark(), address.getRegion(), address.getAddress(),
			                 address.getZipcode(), address.getName(), address.getMobile(), address.getPhone(), address.getUpdateTime(),
			                 address.getCreateTime(), address.getLastUseTime());
		} else {

			String sql = "update t_member_address set remark = ?, region = ?, address = ?, zipcode = ?, name = ?, mobile = ?, "
			        + "phone = ?, update_time = ?, create_time = ?, last_use_time = ? where id = ? and mid = ?";
			dbHelper.execute(sql, address.getRemark(), address.getRegion(), address.getAddress(), address.getZipcode(), address.getName(),
			                 address.getMobile(), address.getPhone(), address.getUpdateTime(), address.getCreateTime(), address.getLastUseTime(),
			                 address.getId(), address.getMid());
		}

		return address;
	}

	public void setDefault(String id, Long userId) throws SQLException {
		dbHelper.execute("update t_member_address set DEFAULT_SHIPPING = '1' where id = ? and mid = ?", id, userId);
		dbHelper.execute("update t_member_address set DEFAULT_SHIPPING = '0' where id <> ? and mid = ?", id, userId);
	}

	public void fillOrder(ActOrderInfo orderInfo, String id, HttpServletResponse response) throws SQLException {
		if (StringUtils.isBlank(id))
			return;

		TMemberAddress address = findAddress(id, SessionUser.getSessionUser(response).getId());
		if (address == null)
			return;

		if (orderInfo.getExpressInfo() == null) {
			orderInfo.setExpressInfo(new ActOrderExpressInfo());
		}

		ActOrderExpressInfo actOrderExpressInfo = orderInfo.getExpressInfo();
		actOrderExpressInfo.setCellphoneNumber(address.getMobile());
		actOrderExpressInfo.setReceiverName(address.getName());
		actOrderExpressInfo.setTelephoneNumber(address.getPhone());
		actOrderExpressInfo.setZipCode(address.getZipcode());
		actOrderExpressInfo.setRemark(address.getRemark()); // ?
		actOrderExpressInfo.setAddress(areaService.getFullName(address.getRegion()) + " " + address.getAddress());
	}

	/**
	 * 根据用户编号查找当前用户，默认收货地址
	 * 
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public TMemberAddress findTMemberAddressByUidAndDefult(Long uid) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.setLength(0);
		sqlBuilder.append("SELECT * ");
		sqlBuilder.append("  FROM t_member_address ms ");
		sqlBuilder.append(" WHERE ms.mid = ? ");
		sqlBuilder.append("   AND ms.default_shipping = 1 ");
		return dbHelper.getBean(sqlBuilder.toString(), TMemberAddress.class, uid);
	}
}
