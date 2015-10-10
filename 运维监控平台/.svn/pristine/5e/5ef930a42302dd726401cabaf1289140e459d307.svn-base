package com.cplatform.mall.dc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.dc.entity.DcRoleMenu;

/**
 * T_DC_ROLE_MENU数据库访问接口
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-13
 */
public interface DcRoleMenuDao extends
		PagingAndSortingRepository<DcRoleMenu, Long> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.
	 * Serializable)
	 */
	DcRoleMenu findOne(Long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll
	 * (org.springframework.data.domain.Pageable)
	 */
	Page<DcRoleMenu> findAll(Pageable pageable);

}
