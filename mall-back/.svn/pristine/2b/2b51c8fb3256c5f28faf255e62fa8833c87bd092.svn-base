package com.cplatform.mall.back.websys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.ChannelCatalogRcmdConfig;

public interface FloorCatalogRcmdDao extends PagingAndSortingRepository<ChannelCatalogRcmdConfig, Long> {

	@Query("from ChannelCatalogRcmdConfig t where t.groupId = ?1 and t.channel = ?2 and t.type = ?3")
	public List<ChannelCatalogRcmdConfig> findChannelIsExit(Integer groupId, Integer channel, Integer type);

}
