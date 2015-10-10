package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysUserRole;

public interface SysUserRoleDao extends PagingAndSortingRepository<SysUserRole, Long> {

	SysUserRole findById(Long id);

	List<SysUserRole> findByUserId(Long userId);

}
