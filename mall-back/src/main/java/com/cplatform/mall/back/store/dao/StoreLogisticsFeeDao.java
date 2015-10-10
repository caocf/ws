package com.cplatform.mall.back.store.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.StoreLogisticsFee;

/**
 * 物流运费 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-6 下午2:01:00
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface StoreLogisticsFeeDao extends PagingAndSortingRepository<StoreLogisticsFee, Long> {

	List<StoreLogisticsFee> findByStoreId(Long storeId);
}
