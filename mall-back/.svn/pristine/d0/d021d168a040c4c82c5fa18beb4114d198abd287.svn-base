package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysType;

/**
 * 分类管理jpa数据访问接口. <br>
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
public interface SysTypeDao extends PagingAndSortingRepository<SysType, Long> {

	/**
	 * 查询出所有商户类别
	 * 
	 * @return
	 */
	@Query("select t from SysType t where t.type = 1 order by t.id asc")
	List<SysType> findStoreSysType();

	/**
	 * 根据分类类型查询（1:商户 2:商品）
	 * 
	 * @param type
	 * @return
	 */
	List<SysType> getSysTypeByType(Long type);

	/**
	 * 查询出所有商品类别
	 * 
	 * @return
	 */
	@Query("select t from SysType t where t.type = 2 order by t.id asc")
	List<SysType> findGoodsSysType();

	/**
	 * 根据type查询出类别
	 * 
	 * @return
	 */
	@Query("select t from SysType t where t.type = ?1 and  t.isValid = 1 order by t.id asc")
	List<SysType> findSysType(Long type);

	/**
	 * 查找分类 按照父id查找
	 * 
	 * @param type
	 *            分类类型 1-商户 2-商品
	 * @param pid
	 * @return
	 */
	@Query("select t from SysType t where t.type = ?1 and t.pId = ?2 order by t.id asc")
	List<SysType> findSysTypeAndPid(Long type, Long pid);
}
