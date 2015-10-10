package com.cplatform.b2c.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.TOrderRefund;
import com.cplatform.b2c.model.TOrderRefundGoods;
import com.cplatform.b2c.repository.TOrderRefundDao;
import com.cplatform.b2c.repository.TOrderRefundGoodsDao;

/**
 * 退款逻辑. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-20 上午9:29:49
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class TOrderRefundGoodsService {

	@Autowired
	private TOrderRefundDao tOrderRefundDao;

	@Autowired
	private TOrderRefundGoodsDao tOrderRefundGoodsDao;

	@Autowired
	private PayOrderService payOrderService;

	/**
	 * 保存退款单，同时保存退款单商品
	 * 
	 * @param orderRefund
	 *            退款单实体类
	 * @param orderGoodsIds
	 *            退款商品订单id数组
	 * @param goodsIds
	 *            退款商品id数组
	 * @param backNumbers
	 *            退款商品数量数组
	 * @param method
	 * @return
	 */
	@Transactional
	public TOrderRefund saveOrderRefund(TOrderRefund tOrderRefund, Integer refundSource, String[] orderRefundGoodsInfos) {
		// 保存 tOrderRefund
		tOrderRefund.setRefundSource(refundSource);
		tOrderRefundDao.save(tOrderRefund);
		if (null != orderRefundGoodsInfos) {
			// 退款商品
			for (int i = 0; i < orderRefundGoodsInfos.length; i++) {

				String[] contents = orderRefundGoodsInfos[i].split("_");

				if (null != contents && contents.length > 0) {
					TOrderRefundGoods orderRefundGoods = new TOrderRefundGoods();
					// 判断是否退款商品
					if (StringUtils.isEmpty(contents[2])) {
						continue;
					}
					orderRefundGoods.setRefundId(tOrderRefund.getId());
					orderRefundGoods.setOrderGoodsId(Long.valueOf(payOrderService.getStringValue(contents[0], null)));
					orderRefundGoods.setGoodsId(Long.valueOf(payOrderService.getStringValue(contents[1], null)));
					orderRefundGoods.setBackNumber(Long.valueOf(payOrderService.getStringValue(contents[2], null)));
					tOrderRefundGoodsDao.save(orderRefundGoods);
				}
			}
		}
		return tOrderRefund;
	}
}
