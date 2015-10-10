package com.cplatform.mall.back.cont.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.cplatform.mall.back.cont.entity.ContMms;

/**
 * 彩信DAO类
 * <p>
 * Copyright: Copyright (c) 2012-11-19 下午1:36:06
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author xuyi@c-platform.com
 * @version 1.0.0
 */
public interface ContMmsDao extends PagingAndSortingRepository<ContMms, Long> {

	@Modifying
	@Query(" delete from ContMms where contentSrc=:contentSrc ")
	void deleteMmsByAppId(@Param("contentSrc") String contentSrc);
}
