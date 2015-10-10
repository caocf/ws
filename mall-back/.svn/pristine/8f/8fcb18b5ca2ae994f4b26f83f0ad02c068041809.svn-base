package com.cplatform.mall.back.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.Shop;

/**
 * 门店管理jpa数据访问接口. <br>
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
public interface ShopDao extends PagingAndSortingRepository<Shop, Long> {

	/**
	 * 校验门店名称唯一
	 * 
	 * @param name
	 *            门店名称
	 * @return
	 */
	public List<Shop> findByName(String name);

	/**
	 * 校验门店简称唯一
	 * 
	 * @param shortName
	 *            门店简称
	 * @return
	 */
	public List<Shop> findByShortName(String shortName);

	/**
	 * 获得一个商户的可用门店列表
	 * 
	 * @param storeId
	 * @return
	 */
	@Query("select s from Shop s where s.acShopId = ?1 and status = 3 and isValid = 1")
	public List<Shop> findStoreValidShops(Long storeId);
}
