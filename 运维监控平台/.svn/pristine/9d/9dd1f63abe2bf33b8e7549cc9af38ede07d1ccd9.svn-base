package com.cplatform.mall.dc.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.dc.entity.DcArea;

public interface DcAreaDao extends PagingAndSortingRepository<DcArea, Long> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.
	 * Serializable)
	 */
	DcArea findOne(Long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll
	 * (org.springframework.data.domain.Pageable)
	 */
	Page<DcArea> findAll(Pageable pageable);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<DcArea> findAll();
	
	DcArea findByAreaCode(String areaCode);
}
