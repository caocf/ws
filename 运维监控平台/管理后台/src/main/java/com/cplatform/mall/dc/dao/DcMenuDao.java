package com.cplatform.mall.dc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.dc.entity.DcMenu;

/**
 * T_DC_MENU数据库访问接口
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-10
 */
public interface DcMenuDao extends PagingAndSortingRepository<DcMenu, String> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@Query("select m from SysMenu m order by m.menuCode asc")
	List<DcMenu> findAll();
}
