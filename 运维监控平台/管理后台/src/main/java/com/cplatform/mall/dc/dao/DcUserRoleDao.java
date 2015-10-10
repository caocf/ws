package com.cplatform.mall.dc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.dc.entity.DcUserRole;

/**
 * T_DC_USER_ROLE数据库访问接口
 * 
 * @author zhangdong
 * @since 2013-7-11
 */
public interface DcUserRoleDao extends
		PagingAndSortingRepository<DcUserRole, Long> {
	List<DcUserRole> findByUserId(Long userId);

	@Modifying
	@Query("delete from DcUserRole t where t.userId = ?1 ")
	void deleteByUserId(Long userId);
	
	@Query("SELECT r.roleId FROM DcUserRole r WHERE r.userId = ?1 ")
	List<Long> findById(Long userId);
}
