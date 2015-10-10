package com.cplatform.mall.back.item.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ItemSaleExt;



/**
 * 
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-20 下午4:36:47
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface ItemSaleExtDao extends PagingAndSortingRepository<ItemSaleExt, Long> {
	@Modifying
	@Query("delete from ItemSaleExt where saleId=?1")
	void deleteBySaleId(Long saleId);
	
	ItemSaleExt findBySaleId(Long saleId);
}
