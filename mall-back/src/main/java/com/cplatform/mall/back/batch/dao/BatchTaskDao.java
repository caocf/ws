package com.cplatform.mall.back.batch.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.batch.entity.BatchTask;

/**
 * Title. 群发任务管理<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-20 下午5:48:49
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
public interface BatchTaskDao extends PagingAndSortingRepository<BatchTask, Long> {

	@Query(value = "select SEQ_SMS_BATCH_TASK.Nextval from dual",nativeQuery=true)
	public BigDecimal getSeqId();
}
