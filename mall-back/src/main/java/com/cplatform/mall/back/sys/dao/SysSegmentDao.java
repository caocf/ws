package com.cplatform.mall.back.sys.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysSegment;

/**
 * Title. 系统号段管理dao<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午5:15:38
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
public interface SysSegmentDao extends PagingAndSortingRepository<SysSegment, Long> {

	/**
	 * 根据号段查询号段列表
	 * 
	 * @param segmentCode
	 *            号段码
	 * @return
	 */
	public List<SysSegment> findBySegmentCode(String segmentCode);
}
