package com.cplatform.b2c.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.OrderRefund;
import com.cplatform.b2c.util.DateUtils;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.ListPage;

@Repository
public class OrderRefundDao {

	@Autowired
	DbHelper dbHelper;

	/**
	 * 退款订单列表
	 * 
	 * @param orderRefund
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public ListPage<Map<String, Object>> findRefundList(OrderRefund orderRefund, int page, int pageSize, Long userId) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select tao.id as orderId,tor.id as refundId,tao.shop_subject,tor.refund_fee,tor.create_time,tor.status ");
		sql.append(" from T_ACT_ORDER tao right join T_ORDER_REFUND tor on tao.id = tor.order_id where tao.user_id = ?  ");
		params.add(userId);
		if (StringUtils.isNotEmpty(orderRefund.getOrderId())) {
			sql.append(" and tao.id = ? ");
			params.add(orderRefund.getOrderId());
		}
		if (StringUtils.isNotEmpty(orderRefund.getRefundId())) {
			sql.append(" and tor.id = ? ");
			params.add(orderRefund.getRefundId());
		}
		if (StringUtils.isNotEmpty(orderRefund.getStatus())) {
			sql.append(" and tor.status = ? ");
			params.add(orderRefund.getStatus());
		}

		if (StringUtils.isNotEmpty(orderRefund.getStartTime())) {
			sql.append(" and tor.create_time >= ?");
			params.add(DateUtils.fromStringToString(orderRefund.getStartTime() + " 00:00:00"));
		}

		if (StringUtils.isNotEmpty(orderRefund.getEndTime())) {
			sql.append(" and tor.create_time <= ?");
			params.add(DateUtils.fromStringToString(orderRefund.getEndTime() + " 23:59:59"));
		}

		sql.append(" order by tor.create_time desc, tao.id");

		return dbHelper.getNativeMapPage(sql.toString(), page, pageSize, params.toArray());
	}
}
