package com.cplatform.mall.back.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-20 下午6:25:56
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Entity
@Table(name = "T_SYS_TEMPLATE_EVENT")
@SequenceGenerator(name="SEQ_SYS_COMM_ID",sequenceName="SEQ_SYS_COMM_ID")
public class EventRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SYS_COMM_ID")
	private int id;

	private int CODE;

	public final static int EVENT_TYPE_TEMPLATE = 1;

	public final static int EVENT_TYPE_GROUP = 2;

	private int type;

	private String tgid;

	private String name;

	private String memo;

	private String tgName;

	public static EventRegister newInstance(String name, String memo, int type,
			String tgid, int code, String tgName) {

		EventRegister eventRegister = new EventRegister();
		eventRegister.setMemo(memo);
		eventRegister.setName(name);
		eventRegister.setTgid(tgid);
		eventRegister.setType(type);
		eventRegister.setCODE(code);
		eventRegister.setTgName(tgName);
		return eventRegister;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTgid() {
		return tgid;
	}

	public void setTgid(String tgid) {
		this.tgid = tgid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getCODE() {
		return CODE;
	}

	public void setCODE(int CODE) {
		this.CODE = CODE;
	}

	public String getTgName() {
		return tgName;
	}

	public void setTgName(String tgName) {
		this.tgName = tgName;
	}

}
