package com.cplatform.mall.back.giftcard.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.giftcard.entity.GiftCard;

public interface GiftCardDao extends PagingAndSortingRepository<GiftCard,String>{
	@Query("select t from GiftCard t where t.batchNo = ?1")
	List<GiftCard> findGiftCardsByBatchNo(Long batchNo);
	
	GiftCard findGiftCardBySerialNoAndStatus(String serialNo, Long status);
}
