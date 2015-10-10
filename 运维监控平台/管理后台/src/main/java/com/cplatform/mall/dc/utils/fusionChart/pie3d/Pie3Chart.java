package com.cplatform.mall.dc.utils.fusionChart.pie3d;

public class Pie3Chart {

	/** 百分比显示状态：显示 */
	public static final String SHOW_PERCENT_SHOW = "1";

	/** 百分比显示状态：隐藏 */
	public static final String SHOW_PERCENT_HIDE = "0";

	private String caption;

	private String showpercentvalues;

	public Pie3Chart(String caption) {
		this.caption = caption;
		this.showpercentvalues = SHOW_PERCENT_SHOW;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getShowpercentvalues() {
		return showpercentvalues;
	}

	public void setShowpercentvalues(String showpercentvalues) {
		this.showpercentvalues = showpercentvalues;
	}
}
