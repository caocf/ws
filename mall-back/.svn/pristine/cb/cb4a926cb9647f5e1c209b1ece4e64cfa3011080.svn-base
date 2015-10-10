package com.cplatform.mall.back.websys.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.PageStaticInfo;

/**
 * 
 * Title.页面静态化管理持久化接口 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-7-18 上午10:10:17
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: macl@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface PageStaticManageDao extends
		PagingAndSortingRepository<PageStaticInfo, Long> {

	public PageStaticInfo findByResourceId(Long resourceId);
	
	public PageStaticInfo findById(Long id);
}
