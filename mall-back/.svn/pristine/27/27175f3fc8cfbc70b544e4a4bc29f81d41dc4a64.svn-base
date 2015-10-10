package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.store.entity.Store;

public interface ItemManageDao extends PagingAndSortingRepository<ItemSale, Long> {

	/**
	 * 根据类型查询
	 * 
	 * @param coded
	 * @return
	 */
	public List<ItemSale> findByTypeId(String typeId);

	public List<Store> findByIdIn(List<Long> idsList);

	public List<ItemSale> findByIdNotIn(List<Long> idsList);

	public List<ItemSale> findByIdInAndStatus(List<Long> idsList, Long l);

	@Query("select a.name from ItemSale a where a.id = ?1")
	public List findNameById(Long l);
}
