package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemSale;

/**
 * 
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-7-3 下午2:41:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * @deprecated 这个类不在维护，请使用ItemManageDao
 */
@Deprecated
public interface ItemSaleDao extends PagingAndSortingRepository<ItemSale, Long> {

	List<ItemSale> findByStatus(Long i);

	List<ItemSale> findByIdNotInAndStatus(List<Long> idsList, Long i);
	List<ItemSale> findByIdInAndStatus(List<Long> idsList, Long l);
}
