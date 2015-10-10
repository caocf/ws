package com.cplatform.mall.back.websys.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.ChannelFloor;

public interface ChannelFloorDao extends PagingAndSortingRepository<ChannelFloor, Long> {
	@Query("select t from ChannelFloor t where t.channel = ?1 and t.floorId = ?2")
	ChannelFloor findChannelFloorByChannelAndFloor(Integer channel,Integer floor);

}
