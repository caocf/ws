package com.cplatform.mall.back.websys.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.ChannelNaviOper;

public interface ChannelNaviOperDao extends PagingAndSortingRepository<ChannelNaviOper, Long> 
{
	@Query("from ChannelNaviOper t where t.id =?1")
	public ChannelNaviOper findChannelNaviOperById(Long id);
}
