package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysRole;

/**
 * Title. 系统角色管理dao<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午5:15:38
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
public interface SysRoleDao extends PagingAndSortingRepository<SysRole, Long> {

	SysRole findById(Long id);
	
	List<SysRole> findByUpdateUserId(Long updateUserId);
}
