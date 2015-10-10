package com.cplatform.mall.back.cms.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.mall.back.cms.model.TempGroup;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 上午9:54:03
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Repository
public class TempGroupRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(TempGroup tempGroup) {

		sessionFactory.getCurrentSession().save(tempGroup);

	}

	@SuppressWarnings("unchecked")
	public List<TempGroup> getAll() {

		return sessionFactory.getCurrentSession()
				.createQuery(" from TempGroup t").list();
	}

	public TempGroup get(String id) {

		return (TempGroup) sessionFactory.getCurrentSession().get(
				TempGroup.class, id);
	}

}
