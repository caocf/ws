package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysUserRegion;

public interface SysUserRegonDao extends PagingAndSortingRepository<SysUserRegion, Long> {

	SysUserRegion findById(Long id);

	SysUserRegion findByRegionCode(String id);

	List<SysUserRegion> findByUserId(Long userId);

}
