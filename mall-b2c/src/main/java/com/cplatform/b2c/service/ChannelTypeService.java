package com.cplatform.b2c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.TChannelType;
import com.cplatform.b2c.repository.TChannelTypeDao;

/**
 * 
 * 商城显示分类 <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-7-2 上午11:08:12
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
@Service
@Transactional
public class ChannelTypeService {

	@Autowired
	private TChannelTypeDao tChannelTypeDao;

	public List<TChannelType> getList(Integer channel, String regionCode) {
		return tChannelTypeDao.getList(channel, regionCode);
	}
}
