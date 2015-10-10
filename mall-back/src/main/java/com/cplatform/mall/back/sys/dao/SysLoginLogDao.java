package com.cplatform.mall.back.sys.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysLoginInfo;

/**
 * Title. 系统登录日志管理dao<br>
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
public interface SysLoginLogDao extends PagingAndSortingRepository<SysLoginInfo, Long> {}
