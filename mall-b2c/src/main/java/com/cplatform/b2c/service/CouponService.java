package com.cplatform.b2c.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.b2c.dto.CreateOrderDTO;
import com.cplatform.b2c.dto.CreateOrderResponse;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.order.ActOrderInfo;
import com.cplatform.order.ActOrderServiceClient;
import com.google.common.collect.Lists;

@Service
public class CouponService {

	private static final Logger logger = Logger.getLogger("CouponService");

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private ActOrderServiceClient actOrderClient;

	public List<String> getGoodIds(List<CreateOrderDTO.Good> goods)
			throws Exception {
		List<String> goodsId = Lists.newArrayList();
		for (CreateOrderDTO.Good good : goods) {
			goodsId.add(good.getId().toString());
		}
		return goodsId;
	}

	public CreateOrderResponse createOrder(CreateOrderDTO createOrderDTO)
			throws Exception {
		return thirdInterDao.createOrder(createOrderDTO);
	}

	public ActOrderInfo getOrderInfo(Long orderId) throws Exception {
		return actOrderClient.getActOrderInfo(orderId);
	}

	public int changeScoreAmount(int score) {
		return new BigDecimal(score).divide(new BigDecimal(100))
				.divide(new BigDecimal("0.015"), 0, BigDecimal.ROUND_DOWN)
				.multiply(new BigDecimal(100)).intValue();
	}

	// 四舍五入
	public int changeToScore(int score) {
		return new BigDecimal(score).divide(new BigDecimal(100))
				.divide(new BigDecimal("0.015"), 0, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(100)).intValue();
	}

}
