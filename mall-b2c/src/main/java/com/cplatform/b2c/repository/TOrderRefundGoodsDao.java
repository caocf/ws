package com.cplatform.b2c.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.model.TOrderRefundGoods;

/**
 * 退款商品. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-20 上午9:28:35
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Repository
public class TOrderRefundGoodsDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 保存 TOrderRefundGoods
	 * 
	 * @param tOrderRefundGoods
	 * @return
	 */
	public void save(TOrderRefundGoods tOrderRefundGoods) {
		sessionFactory.getCurrentSession().save(tOrderRefundGoods);
	}

}
