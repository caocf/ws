package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemVerifyShopLink;

public interface ItemVerifyShopLinkDao extends PagingAndSortingRepository<ItemVerifyShopLink, Long> {

	@Modifying
	@Query("delete from ItemVerifyShopLink where saleId=?1")
	void deleteBySaleId(Long saleId);
	
	@Query("select t from ItemVerifyShopLink t where t.saleId=?1")
	List<ItemVerifyShopLink> findBySaleId(Long saleId);
}
