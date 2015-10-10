package com.cplatform.mall.back.store.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.StoreCode;

public interface StoreCodeDao extends PagingAndSortingRepository<StoreCode, Long> {
	
}
