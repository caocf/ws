package com.cplatform.mall.back.websys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysType;
import com.cplatform.mall.back.websys.entity.SysAdRegion;

/**
 * 
 * 广告投放区域jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:08:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface SysAdRegionDao extends PagingAndSortingRepository<SysAdRegion, Long> {	
	SysAdRegion findByAdId(Long adId);
	
	/**
	 * 根据adId查询
	 * @return
	 */
	@Query("select t from SysAdRegion t where t.adId = ?1 order by t.id asc")
    List<SysAdRegion> findAdRegion(Long adId);
	
	/**
	 * 根据regionCode和adId查询
	 * @return
	 */
	@Query("select t from SysAdRegion t where t.adId = ?1 and t.regionCode = ?2 order by t.id asc")
    List<SysAdRegion> findAdRegionList(Long adId, String regionCode);
}
