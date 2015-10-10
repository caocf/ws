package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysRegion;

/**
 * 区域管理 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-27 下午8:06:36
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface SysRegionDao extends PagingAndSortingRepository<SysRegion, Long> {

	SysRegion findById(Long id);

	List<SysRegion> findByRegionLevel(Long regionLevel);

	@Query("select r from SysRegion r  where r.parentRegion = ?1 and regionLevel <= 1 and isShow =1 order by sortNum   ")
	List<SysRegion> findByParentRegion(String parentRegion);
	
	@Query("select r from SysRegion r  where r.areaCode = ?1 and regionLevel = 1 and isShow =1 order by sortNum   ")
	List<SysRegion> findByAreaCode(String areaCode);

	SysRegion findByRegionCode(String regionCode);

	@Query("select r from SysRegion r  where r.parentRegion = ?1 and r.areaCode is not null  ")
	List<SysRegion> findAreaByParentRegion(String parentRegion);

}
