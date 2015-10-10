package com.cplatform.mall.back.websys.dao;



import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.SysChannelPicConf;


/**
 * 
 * 模版jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-8
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhouhui@c-platform.com
 * @version 1.0
 */
public interface SysChannelPicConfDao extends PagingAndSortingRepository<SysChannelPicConf, Integer> {

	SysChannelPicConf findById(int id);
	
}
