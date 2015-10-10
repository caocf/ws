package com.cplatform.mall.back.cms.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.mall.back.cms.model.GroupTemplate;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 上午11:49:34
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Repository
public class GroupTemplateRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(GroupTemplate groupTemplate) {

		sessionFactory.getCurrentSession().save(groupTemplate);

	}

	@SuppressWarnings("unchecked")
	public List<GroupTemplate> getByGID(String gid) {

		return sessionFactory.getCurrentSession()
				.createQuery("   from GroupTemplate g where g.gid=:gid")
				.setString("gid", gid).list();

	}

	public void deleteByGroupId(String gid) {

		sessionFactory.getCurrentSession()
				.createQuery("delete from GroupTemplate g where g.gid=:gid")
				.setString("gid", gid).executeUpdate();

	}
}
