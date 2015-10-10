package com.cplatform.mall.back.cms.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.mall.back.cms.model.WebTemplate;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-20 下午6:34:20
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Repository
public class WebTemplateRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public WebTemplate get(int id) {

		return (WebTemplate) sessionFactory.getCurrentSession().get(
				WebTemplate.class, id);
	}

	public void save(WebTemplate template) {

		sessionFactory.getCurrentSession().save(template);

	}

	@SuppressWarnings("unchecked")
	public List<WebTemplate> getAll() {

		return sessionFactory.getCurrentSession()
				.createQuery(" from WebTemplate w ").list();
	}

}
