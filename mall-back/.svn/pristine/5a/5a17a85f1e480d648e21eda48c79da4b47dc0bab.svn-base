package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysFee;
import com.cplatform.mall.back.sys.entity.SysSpcode;

/**
 * spcode管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 上午11:10:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface SysSpcodeDao extends PagingAndSortingRepository<SysSpcode, Long> {

	/**
	 * 获得所有有效的费率分类
	 * 
	 * @return
	 */
	@Query("select s from SysSpcode s where s.valid = 0")
	List<SysFee> getAllSpcode();

	List<SysSpcode> findByValid(Integer valid);
}
