package com.cplatform.mall.back.cms.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.mall.back.cms.model.EventRegister;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午4:23:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Repository
public class EventRegisterRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(EventRegister eventRegister) {

		sessionFactory.getCurrentSession().save(eventRegister);
	}

	public void deleteById(String id) {

		sessionFactory.getCurrentSession()
				.createQuery("delete from EventRegister e where e.id=:id")
				.setString("id", id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<EventRegister> getAll() {

		return sessionFactory.getCurrentSession()
				.createQuery("from EventRegister").list();
	}
	
	public EventRegister getByCode(int code){
		
		return (EventRegister) sessionFactory.getCurrentSession()
				.createQuery("from EventRegister e where e.CODE = :code")
				.setInteger("code", code).uniqueResult();
		
	}
	
	
	
	
}
