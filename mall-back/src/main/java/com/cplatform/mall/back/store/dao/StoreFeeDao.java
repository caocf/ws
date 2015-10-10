package com.cplatform.mall.back.store.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.StoreFee;
/**
 * 商户费率信息表Dao
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-5 上午11:34:54
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface StoreFeeDao extends PagingAndSortingRepository<StoreFee, Long> {

	List<StoreFee> findByStoreId(Long storeId);
	
	List<StoreFee> findByProductionTypeAndStoreId(String producttionType,Long storeId);

}
