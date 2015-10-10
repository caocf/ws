package com.cplatform.mall.dc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.dc.entity.MonitorInfo;

public interface MonitorInfoDao extends PagingAndSortingRepository<MonitorInfo, Long> {
	@Query("select m from MonitorInfo m where m.show='1'  order by m.seqId desc")
	List<MonitorInfo> findShowMonitors();
	
	@Query("select m from MonitorInfo m where m.show='1' and m.platName = ?1  order by m.seqId desc")
	List<MonitorInfo> findShowMonitors(String plat);
}
