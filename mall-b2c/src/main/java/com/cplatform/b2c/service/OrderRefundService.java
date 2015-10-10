package com.cplatform.b2c.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.model.OrderRefund;
import com.cplatform.b2c.repository.OrderRefundDao;
import com.cplatform.dbhelp.page.ListPage;

@Service
public class OrderRefundService {

	@Autowired
	OrderRefundDao orderRefundDao;

	public ListPage<Map<String, Object>> getRefundList(OrderRefund orderRefund, int page, int pageSize, Long userId) throws Exception {
		return orderRefundDao.findRefundList(orderRefund, page, pageSize, userId);
	}
}
