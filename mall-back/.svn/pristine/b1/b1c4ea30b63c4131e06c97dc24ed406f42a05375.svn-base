package com.cplatform.mall.back.websys.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.SysAd;

/**
 * 
 * 广告管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:08:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface SysAdDao extends PagingAndSortingRepository<SysAd, Long> {
	/**
	 * 根据广告位置查询
	 * @param positionId
	 * @return
	 */
	public List<SysAd> findByPositionId(Long positionId);

}
