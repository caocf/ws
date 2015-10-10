package com.cplatform.mall.dc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.dc.entity.DcUser;

/**
 * T_DC_USER数据库访问接口 <br>
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午6:45:15
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
public interface DcUserDao extends PagingAndSortingRepository<DcUser, Long> {

	/**
	 * 按照id查询
	 * 
	 * @param id
	 *            用户id
	 * @return 对象
	 */
	DcUser findById(Long id);

	/**
	 * 按照编号和状态查询
	 * 
	 * @param code
	 *            编号
	 * @param status
	 *            状态
	 * @return 对象
	 */
	DcUser findByCodeAndStatus(String code, int status);

	/**
	 * 按照编号查询
	 * 
	 * @param code
	 *            编号
	 * @return 对象
	 */
	DcUser findByCode(String code);

	/**
	 * 按照用户id查询地区
	 * 
	 * @param id
	 *            用户id
	 * @return 地区
	 */
	@Query("SELECT du.area FROM DcUser du WHERE du.id = ?1")
	String getAreaByUserId(Long id);
	
	/**
	 * 根据角色查找用户
	 * @param role
	 * @return
	 */
	@Query("SELECT du FROM DcUser du,DcUserRole dur,DcRole dr WHERE du.id = dur.userId and dur.roleId = dr.id and dr.roleName = ?1")
	List<DcUser> findMonitorUsers(String role);
	
}
