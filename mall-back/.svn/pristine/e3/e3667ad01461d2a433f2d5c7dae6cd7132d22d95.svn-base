package com.cplatform.mall.back.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.order.entity.OrderRefund;

/**
 * 
 * 订单退款管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 上午11:10:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface OrderRefundDao extends PagingAndSortingRepository<OrderRefund, Long> {
	/**
	 * 根据订单ID查询退款单
	 * @param orderId    订单ID
	 * @return
	 */
	@Query("select t from OrderRefund t where t.orderId = ?1 and t.status not in(3,5,7)")
	public List<OrderRefund> findByOrderId(Long orderId);
	
	/**
	 * 根据订单id查询全部退款单
	 * @param orderId
	 * @return
	 */
	@Query("select t from OrderRefund t where t.orderId = ?1 order by t.id asc")
	public List<OrderRefund> findRefundByOrderId(Long orderId);
	
	/**
	 * 根据订单ID查询成功退款单
	 * @param orderId    订单ID
	 * @return
	 */
	@Query("select t from OrderRefund t where t.orderId = ?1 and t.status = 6")
	public List<OrderRefund> findSuccessByOrderId(Long orderId);
	
	/**
	 * 根据退款ID查询成功退款单
	 * @param orderId    订单ID
	 * @return
	 */
	@Query("select t from OrderRefund t where t.id = ?1 and t.status = 6")
	public List<OrderRefund> findSuccessByRefundId(Long refundId);
}
