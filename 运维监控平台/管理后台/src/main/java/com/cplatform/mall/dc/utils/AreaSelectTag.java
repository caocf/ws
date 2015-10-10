package com.cplatform.mall.dc.utils;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.stereotype.Component;

import com.cplatform.mall.dc.entity.DcArea;
import com.cplatform.mall.dc.model.SessionUser;

/**
 * 地区标签生成类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-08-07
 */
@Component
public class AreaSelectTag extends TagSupport {
	private static final long serialVersionUID = 4620604666190215267L;

	protected String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			StringBuilder tagStr = new StringBuilder("<select id=\"area\" name=\"area\">");
			tagStr.append("<option value=\"\">全部</option>");

			SessionUser sessionUser = SessionUser.getSessionUser();
			if (sessionUser != null) {
				List<DcArea> areas = sessionUser.getAreas();
				if (areas != null) {
					for (DcArea area : areas) {
						String areaCode = area.getAreaCode();
						String areaName = area.getAreaName();
						if (value != null && areaCode.equals(value)) {
							tagStr.append("<option value=\"").append(areaCode).append("\" selected=\"selected\">").append(areaName).append("</option>");
						} else {
							tagStr.append("<option value=\"").append(areaCode).append("\">").append(areaName).append("</option>");
						}
					}
				}
			}

			tagStr.append("</select>");
			pageContext.getOut().write(tagStr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	/**
	 * 获取地区下拉框脚本
	 * 
	 * @param selectedArea
	 *            选择的地区
	 * @return 地区下拉框脚本
	 */
	public static StringBuilder getAreaTag(Object selectedArea) {
		StringBuilder tagStr = new StringBuilder("<select id=\"area\" name=\"area\">");
		tagStr.append("<option value=\"\">全部</option>");
		SessionUser sessionUser = SessionUser.getSessionUser();
		if (sessionUser != null) {
			List<DcArea> areas = sessionUser.getAreas();
			if (areas != null) {
				for (DcArea area : areas) {
					String areaCode = area.getAreaCode();
					String areaName = area.getAreaName();
					if (selectedArea != null && areaCode.equals(selectedArea)) {
						tagStr.append("<option value=\"").append(areaCode).append("\" selected=\"selected\">").append(areaName).append("</option>");
					} else {
						tagStr.append("<option value=\"").append(areaCode).append("\">").append(areaName).append("</option>");
					}
				}
			}
		}
		tagStr.append("</select>");

		return tagStr;
	}
	
	/**
	 * 获取商户所在地的下拉脚本
	 * @param selectedArea
	 * @return
	 */
	public static StringBuilder getShopTag(Object selectedArea) {
		StringBuilder tagStr = new StringBuilder("<select id=\"area\" name=\"area\">");
	
		SessionUser sessionUser = SessionUser.getSessionUser();
		if (sessionUser != null) {
			List<DcArea> areas = sessionUser.getAreas();
			if (areas != null) {
				for (DcArea area : areas) {
					String areaCode = area.getAreaCode();
					String areaName = area.getAreaName();
					if (selectedArea != null && areaCode.equals(selectedArea)) {
						tagStr.append("<option value=\"").append(areaCode).append("\" selected=\"selected\">").append(areaName).append("</option>");
					} else {
						tagStr.append("<option value=\"").append(areaCode).append("\">").append(areaName).append("</option>");
					}
				}
			}
		}
		tagStr.append("</select>");

		return tagStr;
	}
}
