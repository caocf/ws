package com.cplatform.mall.back.giftcard.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.giftcard.entity.GiftApplyOutExt;

public interface GiftApplyOutExtDao extends PagingAndSortingRepository<GiftApplyOutExt,Long>{
	@Query("select t from GiftApplyOutExt t where t.id = ?1 and t.batchNo = ?2")
	List<GiftApplyOutExt> findGiftApplyOutExtList(Long id,Long batchNo);
	
	List<GiftApplyOutExt> findGiftApplyOutExtListByApplyId(Long applyId);
	
	@Modifying
	@Query("delete from GiftApplyOutExt where applyId = ?1 ")
	void delGiftApplyOutExt(Long applyId);
}
