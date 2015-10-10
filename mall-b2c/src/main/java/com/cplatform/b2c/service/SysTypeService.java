package com.cplatform.b2c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.TSysType;
import com.cplatform.b2c.repository.TSysTypeDao;

/**
 * 商品分类服务. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-13 下午03:03:22
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
@Service
@Transactional
public class SysTypeService {
	
	@Autowired
	private TSysTypeDao tSysTypeDao;

	
	public List<TSysType> getAllList(Integer channel){
		return tSysTypeDao.getAllList(channel);
	}
	
	public List<TSysType> getParentTypeList(Integer typeId, Integer channel){
		return tSysTypeDao.getParentTypeList(typeId, channel);
	}
}
