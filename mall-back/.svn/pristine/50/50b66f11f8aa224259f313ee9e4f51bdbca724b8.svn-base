package com.cplatform.mall.back.item.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemSaleShopLink;

public interface ItemSaleShopLinkDao extends PagingAndSortingRepository<ItemSaleShopLink, Long> {

	@Modifying
	@Query("delete from ItemSaleShopLink where saleId=?1")
	void deleteBySaleId(Long saleId);
}
