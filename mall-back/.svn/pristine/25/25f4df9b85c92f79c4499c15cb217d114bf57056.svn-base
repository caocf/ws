package com.cplatform.mall.back.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.order.entity.OrderRefundGoods;

/**
 * 
 * 订单退款商品管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 上午11:10:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface OrderRefundGoodsDao extends PagingAndSortingRepository<OrderRefundGoods, Long> {
	/**
	 * 根据退款单ID查询退款商品列表
	 * @param refundId    退款单ID
	 * @return
	 */
	public List<OrderRefundGoods> findByRefundId(Long refundId);
}
