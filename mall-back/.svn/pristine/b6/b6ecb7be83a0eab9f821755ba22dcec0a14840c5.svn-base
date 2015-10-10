package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.HisunProductionLink;

/**
 * 商品协议管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:08:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface HisunProductionLinkDao extends PagingAndSortingRepository<HisunProductionLink, Long> {

	/**
	 * 根据商品协议ID查询商品协议关联
	 * 
	 * @param settleId
	 * @return
	 */
	public List<HisunProductionLink> findBySettleId(Long settleId);

	/**
	 * 根据商品ID查询商品协议关联
	 * 
	 * @param itemId
	 * @return
	 */
	public HisunProductionLink findByItemId(Long itemId);

	public HisunProductionLink findByItemIdAndSettleId(Long itemId, Long settleId);
	
	public HisunProductionLink findByItemIdAndProductionType(Long itemId, String productionType);
}
