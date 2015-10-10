package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemGroupLink;

public interface ItemGroupLinkDao extends PagingAndSortingRepository<ItemGroupLink, String> {

	 List<ItemGroupLink> findByItemOrgId(Long id);

	 List<ItemGroupLink> findByItemSaleId(Long id);

	@Modifying
	@Query("delete from ItemGroupLink where itemOrgId=?1")
	void deleteByItemId(Long itemOrgId);

}
