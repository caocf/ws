package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysUnit;

/**
 * 系统单位管理Dao. <br>
 * 系统单位的数据操纵.
 * <p>
 * Copyright: Copyright (c) 2013-5-22 上午11:05:33
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface SysUnitDao extends PagingAndSortingRepository<SysUnit, Long> {

	/**
	 * 获得字单位编号
	 * 
	 * @param unitId
	 * @return
	 */
	public List<SysUnit> findByParentUnitId(Long unitId);

	SysUnit findById(Long id);

	List<SysUnit> findByUnitType(Long unitType);

}
