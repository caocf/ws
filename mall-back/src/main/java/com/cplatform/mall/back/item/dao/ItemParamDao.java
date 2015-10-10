package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemParam;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-8 下午4:06:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface ItemParamDao extends PagingAndSortingRepository<ItemParam, Long> {

	List<ItemParam> findByItemId(Long itemId);

	List<ItemParam> findByItemIdOrderByRankDesc(Long itemId);

}
