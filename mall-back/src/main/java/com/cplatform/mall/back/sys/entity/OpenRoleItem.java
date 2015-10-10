package com.cplatform.mall.back.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * 
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author m@c-platform.com
 * @version 1.0.0
 */
@javax.persistence.Table(name = "T_OPEN_ROLE_ITEM")
@Entity
public class OpenRoleItem extends IdEntity {
	private String name;
	private String path;
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "PATH")	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
