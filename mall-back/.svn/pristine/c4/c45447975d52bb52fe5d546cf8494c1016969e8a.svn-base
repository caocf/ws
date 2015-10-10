package com.cplatform.mall.back.cont.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.cont.entity.ContentCode;

public interface ContentCodeDao extends PagingAndSortingRepository<ContentCode, Long> {

	/**
	 * 根据Code查询
	 * 
	 * @param coded
	 * @return
	 */
	public List<ContentCode> findByCode(String coded);

	public List<ContentCode> findByContType(Integer contType);
	
	/**
	 * 根据Code查询
	 * 
	 * @param code
	 * @return
	 */
	public ContentCode findContentCodeByCode(String code);
}
