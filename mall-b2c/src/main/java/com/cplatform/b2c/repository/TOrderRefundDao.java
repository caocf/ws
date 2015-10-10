package com.cplatform.b2c.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TOrderRefund;

/**
 * 退款. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-20 上午9:31:29
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Repository
public class TOrderRefundDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 保存 TOrderRefund
	 * 
	 * @param tOrderRefund
	 * @return
	 */
	/*
	 * public TOrderRefund save(TOrderRefund tOrderRefund) { tOrderRefund =
	 * (TOrderRefund) sessionFactory.getCurrentSession().save(tOrderRefund);
	 * return tOrderRefund; }
	 */

	public void save(TOrderRefund tOrderRefund) {
		sessionFactory.getCurrentSession().save(tOrderRefund);
	}

}
