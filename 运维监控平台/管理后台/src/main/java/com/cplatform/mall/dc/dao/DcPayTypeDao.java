package com.cplatform.mall.dc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cplatform.mall.dc.entity.DcPayTypeEntity;

/**
 * T_DC_PAY_TYPE数据库访问类<br>
 * <p>
 * Copyright: Copyright (c) 2014-1-13 下午3:10:25
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
public interface DcPayTypeDao extends CrudRepository<DcPayTypeEntity, Long> {

//	@Override
//	List<DcPayTypeEntity> findAll();
}
