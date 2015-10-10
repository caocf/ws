package com.cplatform.mall.dc.utils.fusionChart.pie3d;

import org.apache.commons.lang3.StringUtils;

public class Pie3Data {

	private String label;

	private String value;

	public Pie3Data(String label) {
		this(label, null);
	}

	public Pie3Data(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return StringUtils.trimToEmpty(label);
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		if (StringUtils.isBlank(value)) {
			return "0";
		}
		return StringUtils.trimToEmpty(value);
	}

	public void setValue(String value) {
		this.value = value;
	}
}
