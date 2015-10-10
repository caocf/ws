package com.cplatform.mall.back.smsact.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.smsact.entity.SmsActRouter;

/**
 * 
 * 二次开发短信业务指令管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:08:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface SmsActRouterDao extends PagingAndSortingRepository<SmsActRouter, Long> {
	/**
	 * 根据业务ID查询指令
	 * @param actId
	 * @return
	 */
	public List<SmsActRouter> findByActId(Long actId);
	
	/**
	 * 校验指令唯一
	 * @param command
	 * @param spCode
	 * @return
	 */
	public List<SmsActRouter> findByCommandAndSpCode(String command, String spCode);
	
}
