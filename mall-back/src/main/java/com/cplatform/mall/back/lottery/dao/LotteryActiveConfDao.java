package com.cplatform.mall.back.lottery.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.lottery.entity.LotteryActive;
import com.cplatform.mall.back.lottery.entity.LotteryActiveConf;


/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-7-22
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */

public interface LotteryActiveConfDao extends PagingAndSortingRepository<LotteryActiveConf, Long>{

}
