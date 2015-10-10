package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ThirdCodeImport;

/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-10-21
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
public interface ThirdCodeImportDao extends PagingAndSortingRepository<ThirdCodeImport, Long>{
	
	@Query("select t from ThirdCodeImport t where t.codeNameId = ?1 ")
	public List<ThirdCodeImport> findByNameId(Long nameId);

}
