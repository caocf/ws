package com.cplatform.mall.back.store.entity;

public class StoreCodeConfig {

	private Long id;

	private Long sendChannel;

	private String sendType;

	private String intoUser;

	private String intoTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIntoUser() {
		return intoUser;
	}

	public void setIntoUser(String intoUser) {
		this.intoUser = intoUser;
	}

	public String getIntoTime() {
		return intoTime;
	}

	public void setIntoTime(String intoTime) {
		this.intoTime = intoTime;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public Long getSendChannel() {
		return sendChannel;
	}

	public void setSendChannel(Long sendChannel) {
		this.sendChannel = sendChannel;
	}

}
