package com.cplatform.mall.dc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.dc.entity.DcLoginLog;

/**
 * T_DC_LOGIN_LOG数据库访问接口
 * 
 * @author zhangdong
 * @since 2013-7-11
 */
public interface DcLoginLogDao extends
		PagingAndSortingRepository<DcLoginLog, Long> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.
	 * Serializable)
	 */
	DcLoginLog findOne(Long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll
	 * (org.springframework.data.domain.Pageable)
	 */
	Page<DcLoginLog> findAll(Pageable pageable);

}
