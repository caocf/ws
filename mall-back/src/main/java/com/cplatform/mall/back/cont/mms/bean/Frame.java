package com.cplatform.mall.back.cont.mms.bean;

/**
 * bean类， 彩信的单帧类
 * <p>
 * Copyright: Copyright (c) Aug 11, 2009 2:31:54 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: chengyao
 * <p>
 * Version: 1.0
 * <p>
 */
public class Frame {

	/** 帧显示时间，秒为单位 */
	int showtime;

	/** 帧中图片 */
	Pic pic;

	/** 帧中文本 */
	Text text;

	/** 帧中声音 */
	Sound sound;

	public int getShowtime() {
		return showtime;
	}

	public void setShowtime(int showtime) {
		this.showtime = showtime;
	}

	public Pic getPic() {
		return pic;
	}

	public void setPic(Pic pic) {
		this.pic = pic;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}

}
