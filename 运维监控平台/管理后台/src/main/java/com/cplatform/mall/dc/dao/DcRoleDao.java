package com.cplatform.mall.dc.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.dc.entity.DcRole;

/**
 * T_DC_ROLE数据库访问接口
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-11
 */
public interface DcRoleDao extends PagingAndSortingRepository<DcRole, Long> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.
	 * Serializable)
	 */
	DcRole findOne(Long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll
	 * (org.springframework.data.domain.Pageable)
	 */
	Page<DcRole> findAll(Pageable pageable);

	/**
	 * 按照角色名查找
	 * 
	 * @param roleName
	 *            角色名
	 * @return
	 */
	DcRole findByRoleName(String roleName);
	
	/**
	 * 查找出所有有效角色
	 * @param status
	 * @return
	 */
	List<DcRole> findByStatus(int status);
	
	@Query("SELECT r FROM DcRole r,DcUserRole u WHERE u.userId = ?1 and r.id = u.roleId")
	DcRole findByUserId(Long userId);
}
