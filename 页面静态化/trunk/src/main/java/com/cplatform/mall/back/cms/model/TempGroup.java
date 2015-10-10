package com.cplatform.mall.back.cms.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-20 下午5:07:39
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_TEMPLATE_GROUP")
public class TempGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9072092074464436591L;

	@Id
	private String id;

	private String gName;

	private String memo;

	public static TempGroup newInstance(String gName, String memo) {

		TempGroup tempGroup = new TempGroup();
		tempGroup.setgName(gName);
		tempGroup.setMemo(memo);
		tempGroup.setId(UUID.randomUUID().toString());

		return tempGroup;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
