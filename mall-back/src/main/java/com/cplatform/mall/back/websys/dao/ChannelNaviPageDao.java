package com.cplatform.mall.back.websys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.ChannelNaviOper;
import com.cplatform.mall.back.websys.entity.ChannelNaviPage;

public interface ChannelNaviPageDao extends PagingAndSortingRepository<ChannelNaviPage, Long> 
{
	@Query("from ChannelNaviPage t")
	public List<ChannelNaviPage> findChannelNaviPage();
	
	@Query("select t.id from ChannelNaviPage t where t.code = ?1")
	public Long findChannelNaviPageId(String code);
}
