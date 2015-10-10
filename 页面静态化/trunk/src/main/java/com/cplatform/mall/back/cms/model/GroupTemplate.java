package com.cplatform.mall.back.cms.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-20 下午6:15:50
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_TEMPLATE_GROUP_REF")
public class GroupTemplate {

	@Id
	private String id;

	private String gid;

	private String tid;
	
	public static GroupTemplate newInstance(String gid,String tid){
		
		GroupTemplate groupTemplate =  new GroupTemplate();
		groupTemplate.setId(UUID.randomUUID().toString());
		groupTemplate.setGid(gid);
		groupTemplate.setTid(tid);
		
		return groupTemplate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

}
