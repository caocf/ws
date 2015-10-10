package com.cplatform.mall.back.item.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemSaleUserLevelaLink;

public interface ItemSaleUserLevelaLinkDao extends PagingAndSortingRepository<ItemSaleUserLevelaLink, Long> {

	@Modifying
	@Query("delete from ItemSaleUserLevelaLink where saleId=?1")
	void deleteBySaleId(Long saleId);
}
