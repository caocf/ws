package com.cplatform.mall.back.giftcard.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.giftcard.entity.GiftRequiredItem;

/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-9-22
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
public interface GiftRequiredItemDao extends PagingAndSortingRepository<GiftRequiredItem, Long> {
	
	@Query("select t from GiftRequiredItem t where t.batchNo = ?1")
	List<GiftRequiredItem> findByBatchNo(Long batchNo);
}
