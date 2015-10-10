package com.cplatform.mall.back.lottery.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.lottery.entity.LotteryTarget;


/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-7-22
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */

public interface LotteryTargetDao extends PagingAndSortingRepository<LotteryTarget, Long>{
	
}
