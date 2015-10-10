package com.cplatform.mall.back.order.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.order.dao.SmsbuyOrderDao;
import com.cplatform.mall.back.order.entity.SmsAddress;
import com.cplatform.mall.back.order.entity.SmsbuyOrder;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.order.ActOrderExpressInfo;

@Service
public class SmsbuyOrderService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private SmsbuyOrderDao smsbuyOrderDao;

	/**
	 * 获取短信购列表页面
	 * 
	 * @param order
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<SmsbuyOrder> findSmsbuyOrderList(SmsbuyOrder smsbuyOrder, int pageNo, int pageSize) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sqlBuff.setLength(0);
		sqlBuff.append("select t1.act_type actType, ");
		sqlBuff.append("t1.create_time createTime, ");
		sqlBuff.append("t2.terminal_id terminalId, ");
		// sqlBuff.append("t3.goods_subject goodSubject, ");
		// sqlBuff.append("t4.name shopName, ");
		sqlBuff.append("t5.address address, ");
		sqlBuff.append("t1.id orderId, ");
		sqlBuff.append("t1.pay_status payStatus, ");
		sqlBuff.append("t5.status sendStatus ");
		sqlBuff.append("from t_act_order t1 ");
		sqlBuff.append(" join t_member t2 ");
		sqlBuff.append("on t1.user_id = t2.id ");
		sqlBuff.append(" join t_act_order_goods t3 ");
		sqlBuff.append("on t1.id = t3.act_order_id ");
		// t_store store
		sqlBuff.append("left join t_store store ");
		sqlBuff.append("on t1.shop_id = store.id ");
		// sqlBuff.append("left join t_store t4 ");
		// sqlBuff.append("on t1.shop_id = t4.id ");
		sqlBuff.append("left join t_act_order_express t5 ");
		sqlBuff.append("on t1.id = t5.act_order_id ");
		sqlBuff.append("where 1 = 1 ");
		sqlBuff.append("and t1.id is not null ");
		sqlBuff.append("and t2.terminal_id is not null ");
		sqlBuff.append("and t1.act_type = 20 ");
		sqlBuff.append("and t3.goods_id in ( select t6.id from t_item_sale t6 where t6.post_flag = 1 ) ");
		if (StringUtils.isNotEmpty(smsbuyOrder.getTerminalId())) {
			sqlBuff.append(" and t2.terminal_id = ? ");
			params.add(smsbuyOrder.getTerminalId());
		}
		if (smsbuyOrder.getOrderId() != null) {
			sqlBuff.append(" and t1.id = ? ");
			params.add(smsbuyOrder.getOrderId());
		}
		if (StringUtils.isNotEmpty(smsbuyOrder.getBeginTime())) {
			sqlBuff.append(" and t1.create_time > ?  ");
			params.add(smsbuyOrder.getBeginTime() + "000000");
		}
		if (StringUtils.isNotEmpty(smsbuyOrder.getEndTime())) {
			sqlBuff.append(" and t1.create_time < ?  ");
			params.add(smsbuyOrder.getEndTime() + "235959");
		}
		if (StringUtils.isNotEmpty(smsbuyOrder.getIsAdd())) {
			if ("0".equals(smsbuyOrder.getIsAdd())) {
				sqlBuff.append(" and t5.address is null  ");
			}
			if ("1".equals(smsbuyOrder.getIsAdd())) {
				sqlBuff.append(" and t5.address is not null  ");
			}
		}
		if (null != smsbuyOrder.getPayStatus()) {
			sqlBuff.append(" and t1.pay_status = ? ");
			params.add(smsbuyOrder.getPayStatus());
		}
		sqlBuff.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_STORE));
		sqlBuff.append("order by t1.create_time desc ");
		Page<SmsbuyOrder> pageData = dbHelper.getPage(sqlBuff.toString(), SmsbuyOrder.class, pageNo, pageSize, params.toArray());
		return pageData;
	}

	public List<SmsAddress> findSmsAddressessByterminalId(String terminalId) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select terminal_id terminalId,address address,update_time updateTime ");
		sqlBuff.append("  from t_smsbuy_user_address t ");
		sqlBuff.append(" where t.terminal_id = ? order by update_time desc");
		return dbHelper.getBeanList(sqlBuff.toString(), SmsAddress.class, new Object[] { terminalId });
	}

	public void save(ActOrderExpressInfo express) throws SQLException {
		// StringBuilder sqlBuff = new StringBuilder();
		// sqlBuff.setLength(0);
		// sqlBuff.append("update t_act_order_express t set t.address = ?,t.receiver_name = ?,t.cellphone_number = ? ");
		// sqlBuff.append("where t.act_order_id = ?");
		// dbHelper.execute(sqlBuff.toString(), new
		// Object[]{express.getAddress(),express.getReceiverName(),express.getCellphoneNumber(),express.getActOrderId()});

		ActOrderExpressInfo actOrderExpressInfo = smsbuyOrderDao.findActOrderExpressInfoByOrderId(express.getActOrderId());
		if (actOrderExpressInfo == null) {
			smsbuyOrderDao.save(express);
		} else {
			express.setId(actOrderExpressInfo.getId());
			smsbuyOrderDao.save(express);
		}
	}

	@Transactional
	public void saveActOrderAndSmsAddress(ActOrderExpressInfo express, SmsAddress smsAddress) throws SQLException {
		// StringBuilder sqlBuff = new StringBuilder();
		// sqlBuff.setLength(0);
		// sqlBuff.append("update t_act_order_express t set t.address = ?,t.receiver_name = ?,t.cellphone_number = ? ");
		// sqlBuff.append("where t.act_order_id = ?");
		// dbHelper.execute(sqlBuff.toString(), new
		// Object[]{express.getAddress(),express.getReceiverName(),express.getCellphoneNumber(),express.getActOrderId()});

		ActOrderExpressInfo actOrderExpressInfo = smsbuyOrderDao.findActOrderExpressInfoByOrderId(express.getActOrderId());
		if (actOrderExpressInfo == null) {
			smsbuyOrderDao.save(express);
		} else {
			express.setId(actOrderExpressInfo.getId());
			smsbuyOrderDao.save(express);
		}
		List<SmsAddress> addressList = this.findSmsAddressess(smsAddress.getTerminalId(), smsAddress.getAddress());
		// 如果地址不重复才保存
		if (addressList.size() == 0) {
			this.saveSmsAddress(smsAddress);
		}
	}

	public ActOrderExpressInfo findByOrderId(Long orderId) {
		return smsbuyOrderDao.findActOrderExpressInfoByOrderId(orderId);
	}

	// 保存地址信息
	public void saveSmsAddress(SmsAddress smsAddress) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("insert into t_smsbuy_user_address values(?,?,?)");
		Object[] params = new Object[3];
		params[0] = smsAddress.getTerminalId();
		params[1] = smsAddress.getAddress();
		params[2] = smsAddress.getUpdateTime();
		dbHelper.execute(sqlBuff.toString(), params);
	}

	// 查询地址信息是否存在
	public List<SmsAddress> findSmsAddressess(String terminalId, String address) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select * from t_smsbuy_user_address t ");
		sqlBuff.append("where t.terminal_id = ? and t.address = ?");
		return dbHelper.getBeanList(sqlBuff.toString(), SmsAddress.class, new Object[] { terminalId, address });
	}

}
