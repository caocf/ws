package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysUser;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-21 下午2:53
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public interface UserDao extends PagingAndSortingRepository<SysUser, Long> {
	
	SysUser findById(Long id);

	SysUser findByUserCode(String userCode);

	SysUser findByEmail(String email);

	SysUser findByIdNotAndEmail(Long id, String email);
	
	List<SysUser> findByFlag(Long flag);

}
