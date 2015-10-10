package com.cplatform.mall.back.store.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.GoodsShelfRel;

public interface GoodShelfRelDao extends PagingAndSortingRepository<GoodsShelfRel, Long> {
	
	@Modifying
	@Query("delete from GoodsShelfRel where goodId = ?1")
	void delGoodShelfGoodRel(Long goodId);

}
