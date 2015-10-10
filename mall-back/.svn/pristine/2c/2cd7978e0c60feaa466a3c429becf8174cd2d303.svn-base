package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysRolePrivilege;

public interface SysRolePrivilegeDao extends PagingAndSortingRepository<SysRolePrivilege, Long> {

	SysRolePrivilege findById(Long Id);

	List<SysRolePrivilege> findByRoleId(Long roleId);

	/**
	 * 获得当前登录用户的权限列表
	 * 
	 * @param userId
	 * @return
	 */
	@Query("select p from  SysRolePrivilege p where p.roleId in (select roleId from SysUserRole where userId = ?1)")
	List<SysRolePrivilege> findByUserRolePrivilege(Long userId);

}
