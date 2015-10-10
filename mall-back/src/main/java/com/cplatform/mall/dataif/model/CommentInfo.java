package com.cplatform.mall.dataif.model;

/**
 * Title. 评论对象<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-8 下午1:49:15
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
public class CommentInfo {

	private Long id;

	private String nickname;

	private String avatar;

	private String rank;

	private String content;

	private String updateTime;

	private String usefulNum;

	private String uselessNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUsefulNum() {
		return usefulNum;
	}

	public void setUsefulNum(String usefulNum) {
		this.usefulNum = usefulNum;
	}

	public String getUselessNum() {
		return uselessNum;
	}

	public void setUselessNum(String uselessNum) {
		this.uselessNum = uselessNum;
	}

}
