package com.cplatform.mall.back.store.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.Store;

public interface StoreDao extends PagingAndSortingRepository<Store, Long> {

	public List<Store> findByShopClassAndStatusAndIsValid(Long shopClass, Long status, Long isValid);

	List<Store> findByShopClass(Long shopClass);

	List<Store> findByName(String name);

	List<Store> findBylinkPhone(String linkPhone);

	public List<Store> findByShopClassIn(List<Long> list);

	public List<Store> findByShopClassInAndStatus(List<Long> params, Long i);
}
