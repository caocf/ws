package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemTag;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-4 下午12:22:35
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface ItemTagDao extends PagingAndSortingRepository<ItemTag, Long> {

	public List<ItemTag> findByItemId(Long id);
	
	@Modifying
	@Query("delete from ItemTag where itemId=?1")
	void deleteByItemId(Long itemId);

}
