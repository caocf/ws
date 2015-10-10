package com.cplatform.b2c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.repository.VSearchGoodDao;

/**
 * 标题、简要说明. 
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-17 上午11:15:21
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Service
@Transactional
public class VSearchGoodService {

	@Autowired
	private VSearchGoodDao vSearchGoodDao;

	public VSearchGood get(String id) {
		return vSearchGoodDao.get(id);
	}

	public List<VSearchGood> getList(Integer[] ids) {
		return vSearchGoodDao.getList(ids);
	}

	public List<VSearchGood> getListByType(Integer[] typeIds, int maxResults) {
		return vSearchGoodDao.getListByType(typeIds, maxResults);
	}
	
	
}
