package com.cplatform.mall.back.store.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.ShopPosLink;
import com.cplatform.mall.back.store.entity.ShopTag;

/**
 * 
 * 业务门店标签管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:08:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface ShopTagDao extends PagingAndSortingRepository<ShopTag, Long> {
	/**
	 * 根据门店ID查询列表
	 * 
	 * @param shopId
	 *            
	 * @return
	 */
	public List<ShopTag> findByShopId(Long shopId);
}
