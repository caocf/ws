package com.cplatform.mall.back.store.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.StoreSettle;

/**
 * 商户结算信息Dao Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-5 上午11:34:37
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface StoreSettleDao extends PagingAndSortingRepository<StoreSettle, Long> {

	List<StoreSettle> findByStoreId(Long storeId);

	List<StoreSettle> findBySettleAc(String settleAc);

}
