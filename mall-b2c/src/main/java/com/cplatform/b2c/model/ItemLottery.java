package com.cplatform.b2c.model;

import java.util.Date;

/**
 * 我的中奖信息简要说明. <br>
 * ItemLottery用于对查询的活动名称,抽奖时间,奖品进行封装处理
 * <p>
 * Copyright: Copyright (c) 2014-3-20 上午9:16:24
 * <p>
 * Company: 宽连信息(苏州)技术有限公司
 * <p>
 * 
 * @author liuchao@c-platform.com
 * @version 1.0.0
 */
public class ItemLottery {

	/** 活动名称 */
	private String activeName;

	/** 抽奖时间 时间格式一般为yyyy-MM-dd HH:mm:ss */
	private Date hitTime;

	/** 奖品信息 */
	private String lotteryInfo;

	/**
	 * 获取活动名称
	 * 
	 * @return
	 */
	public String getActiveName() {
		return activeName;
	}

	/**
	 * 获取中奖时间
	 * 
	 * @return
	 */
	public Date getHitTime() {
		return hitTime;
	}

	/**
	 * 获取奖品
	 * 
	 * @return
	 */
	public String getLotteryInfo() {
		return lotteryInfo;
	}

	/**
	 * 设置活动名称
	 * 
	 * @param activeName
	 */
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	/**
	 * 设置中奖时间
	 * 
	 * @param hitTime
	 */
	public void setHitTime(Date hitTime) {
		this.hitTime = hitTime;
	}

	/**
	 * 设置奖品
	 * 
	 * @param lotteryInfo
	 */
	public void setLotteryInfo(String lotteryInfo) {
		this.lotteryInfo = lotteryInfo;
	}

}
