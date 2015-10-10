package com.cplatform.mall.dc.utils.fusionChart;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ChartsXmlUtils {

	public static final String DEFAULT_XML_TOP = "<chart caption='支付失败原因统计' palette='2' showLabels='0' animation= '0' numberSuffix='%' showLegend='1' legendPosition='BOTTOM'>";

	/**
	 * 獲取餅圖表
	 * @param list
	 * @param swf
	 * @param url
	 * @param chartId
	 * @param width
	 * @param height
	 * @param debugMode
	 * @param regJS
	 * @return
	 */
	public String getChart_Pie(List<PiePart> list, String xmlTop, String swf, String url, String chartId, int width, int height, boolean debugMode, boolean regJS) {
		String xml = getPieXml(list, xmlTop);
		String chartPie = FusionChartsCreator.createChart(swf, url, xml, chartId, width, height, debugMode, regJS);
		return chartPie;
	}

	/**
	 * 获取整个XML文件内容
	 * @param list
	 * @return
	 */
	public String getPieXml(List<PiePart> list, String xmlTop) {
		StringBuilder xml = new StringBuilder();
		xml.setLength(0);
		xml.append(xmlTop);
		xml.append(getPieXmlCenter(list, false, false, false));
		xml.append(getXmlBottom(FunctionsUtils.getCaptionFontColor(), "15", "0"));
		return xml.toString();
	}

	/**
	 * 获取饼图中间的xml
	 * @param list
	 * @param slicePies
	 * @param addJSLinks
	 * @param forDataURL
	 * @return
	 */
	public String getPieXmlCenter(List<PiePart> list, boolean slicePies, boolean addJSLinks, boolean forDataURL) {
		int slicedOut = 0;
		String strLink = "";
		StringBuilder strXML = new StringBuilder("");
		for (int i = 0; i < list.size(); i++) {
			PiePart piePart = list.get(i);
			if (addJSLinks) {
				strLink = " link='javascript:updateChart(" + piePart.getId() + ");' ";
			} else {
				strLink = "";
			}
			slicedOut = (slicePies && (i + 1) < 3) ? 1 : 0;
			strXML = strXML.append("<set label='").append(FunctionsUtils.escapeXML(piePart.getName(), forDataURL)).append("' value='" + piePart.getValue()).append("' isSliced='").append(slicedOut).append("' ").append(strLink).append(" />");
		}
		return strXML.toString();
	}

	/**
	 * xml文件的底部
	 * @param color
	 * @param size
	 * @param bold
	 * @return
	 */
	public String getXmlBottom(String color, String size, String bold) {
		StringBuilder bottom = new StringBuilder();
		bottom.setLength(0);
		bottom.append("<styles><definition><style type='font' color='");
		bottom.append(color);
		bottom.append("' name='CaptionFont' size='");
		bottom.append(size);
		bottom.append("' />");
		bottom.append("<style type='font' name='SubCaptionFont' bold='");
		bottom.append(bold);
		bottom.append("' /></definition><application><apply toObject='caption' styles='CaptionFont' /><apply toObject='SubCaption' styles='SubCaptionFont' /></application></styles></chart>");
		return bottom.toString();
	}

}
