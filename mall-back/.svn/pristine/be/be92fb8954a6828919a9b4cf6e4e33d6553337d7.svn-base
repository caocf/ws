package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemPrice;

public interface ItemPriceDao extends PagingAndSortingRepository<ItemPrice, Long> {

	public List<ItemPrice> findBysaleId(Long saleId);
	@Modifying
	@Query("delete from ItemPrice where saleId=?1")
	void deleteBySaleId(Long saleId);

}
