package com.cplatform.mall.dc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.cplatform.mall.dc.entity.DcOpLog;

/**
 * T_DC_OPERATE_LOG数据库访问接口
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-12
 */
public interface DcOpLogDao extends CrudRepository<DcOpLog, Long> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.
	 * Serializable)
	 */
	DcOpLog findOne(Long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll
	 * (org.springframework.data.domain.Pageable)
	 */
	Page<DcOpLog> findAll(Pageable pageable);
}
