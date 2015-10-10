package com.cplatform.mall.back.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.StoreAgent;

/**
 * Title.渠道商代理结算商户关系 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-30 上午10:48:05
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface StoreAgentDao extends PagingAndSortingRepository<StoreAgent, Long> {

//	@Query("select a.qdStoreId,a.startTime,a.stopTime,(select name from Store where id=a.qdStoreId ) as storeName from StoreAgent a,Store s where a.jsStoreId=s.id and a.jsStoreId=?1 ")
//	List<Object[]> findByJsStoreId(Long jsStoreId);

	List<StoreAgent> findByQdStoreId(Long qdStoreId);
	List<StoreAgent> findByJsStoreId(Long jsStoreId);

	@Modifying
	@Query("delete from StoreAgent where jsStoreId = ?1 ")
	void deleteStoreAgent(Long jsStoreId);

}
