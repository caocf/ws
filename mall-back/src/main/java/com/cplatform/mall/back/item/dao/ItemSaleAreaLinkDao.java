package com.cplatform.mall.back.item.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemSaleAreaLink;

public interface ItemSaleAreaLinkDao extends PagingAndSortingRepository<ItemSaleAreaLink, Long> {

	@Modifying
	@Query("delete from ItemSaleAreaLink where saleId=?1")
	void deleteBySaleId(Long saleId);
}
