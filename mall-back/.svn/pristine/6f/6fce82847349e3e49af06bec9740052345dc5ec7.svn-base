package com.cplatform.mall.back.websys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.SysPos;

/**
 * 
 * 终端管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-24 上午11:10:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface SysPosDao extends PagingAndSortingRepository<SysPos, Long> {
	/**
	 * 查询出终端
	 * @return
	 */
	@Query("select t from SysPos t order by t.id asc")
    List<SysPos> findSysPos();
	/**
	 * 校验名称唯一性
	 * @param name
	 * @return
	 */
	List<SysPos> findByName(String name);
}
