package com.cplatform.mall.back.giftcard.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.giftcard.entity.GiftCardStorageExt;

public interface GiftCardStorageExtDao extends PagingAndSortingRepository<GiftCardStorageExt, Long> {
	List<GiftCardStorageExt> findGiftCardStorageExtListByApplyId(Long applyId);
}
