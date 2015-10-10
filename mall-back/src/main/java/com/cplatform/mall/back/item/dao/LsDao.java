package com.cplatform.mall.back.item.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.LsItem;

public interface LsDao  extends PagingAndSortingRepository<LsItem,Long>{
	@Query("select t from LsItem t where t.itemId = ?1")
	LsItem findLsItemByItemId(Long itemId);
}
