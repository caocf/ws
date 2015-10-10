package com.cplatform.b2c.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.act.ActServiceClient;
import com.cplatform.act.CloseRequest;
import com.cplatform.act.CloseResponse;
import com.cplatform.act.PayResponse;
import com.cplatform.b2c.model.TOrderRefund;

/**
 * 取消操作. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-2-8 上午8:30:49
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class CloseRequestService {

	Logger logger = Logger.getLogger(CloseRequestService.class);

	@Autowired
	private ActServiceClient actBusinessClient;

	@Autowired
	private TOrderRefundGoodsService tOrderRefundGoodsService;

	/**
	 * 个人中心取消操作
	 * 
	 * @param actOrderId
	 *            订单编号
	 * @return
	 */
	public CloseRequest getCloseRequest(Long actOrderId) {
		logger.info("用户取消订单操作，准备数据");
		CloseRequest closeRequest = new CloseRequest();
		closeRequest.setActOrderId(actOrderId);
		closeRequest.setCloseDescription("用户取消");
		closeRequest.setRollbackInventory(true);
		return closeRequest;
	}

	/**
	 * 用户取消订单操作
	 * 
	 * @param actOrderId
	 * @return
	 */
	public CloseResponse close(Long actOrderId) {
		logger.info("用户取消订单操作");
		CloseRequest closeRequest = getCloseRequest(actOrderId);
		CloseResponse closeResponse = actBusinessClient.close(closeRequest);
		if (closeResponse.getStatus() != PayResponse.STATUS_OK) {
			logger.error("用户取消订单失败");
		}
		logger.info("用户取消订单成功");
		return closeResponse;
	}

	/**
	 * 用户取消订单，并且执行取消操作
	 * 
	 * @param tOrderRefund
	 * @param refundSource
	 * @param orderRefundGoodsInfos
	 * @return
	 */
	@Transactional
	public Map<String, Object> refundAndClose(TOrderRefund tOrderRefund, Integer refundSource, String[] orderRefundGoodsInfos) {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("用户申请退款，并且执行取消订单操作");
		// 退款申请操作
		tOrderRefund = tOrderRefundGoodsService.saveOrderRefund(tOrderRefund, refundSource, orderRefundGoodsInfos);
		logger.info("订单管理：增加退款单,退款单ID：" + tOrderRefund.getId());
		// 用户取消订单操作
		CloseResponse closeResponse = close(tOrderRefund.getOrderId());
		map.put("tOrderRefund", tOrderRefund);
		map.put("closeResponse", closeResponse);
		logger.info("用户申请退款，并且执行取消订单操作----执行成功");
		return map;
	}
}
