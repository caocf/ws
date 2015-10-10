package com.cplatform.mall.back.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.order.entity.OrderRefundException;

/**
 * 
 * 订单退款状态管理. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-10-29 上午11:10:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author luyd@c-platform.com
 * @version 1.0.0
 */

public interface OrderRefundExceptionDao extends PagingAndSortingRepository<OrderRefundException,Long> {
	@Query("select t from OrderRefundException t where t.resourceId = ?1 and t.resouceType = ?2 and t.payment = ?3")
	OrderRefundException findOrderRefundExceptionBySourceIdAndSourceTypeAndPayment(Long sourceId,Long sourceType,String currency);
	
	@Query("select t from OrderRefundException t where t.resourceId = ?1 and t.resouceType = ?2")
	List<OrderRefundException> findOrderRefundExceptionBySourceIdAndSourceType(Long sourceId,Long sourceType);

}
