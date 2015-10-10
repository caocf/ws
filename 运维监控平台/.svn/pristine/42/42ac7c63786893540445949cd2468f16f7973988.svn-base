package com.cplatform.mall.dc.utils.fusionChart;

import javax.servlet.http.HttpServletRequest;

/**
 * 脚本生成类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-8-8
 */
public class ScriptCreater {
	/**
	 * 获取生成fusionChart所需的脚本
	 * 
	 * @param width
	 *            图形宽度
	 * @param height
	 *            图形高度
	 * @param count
	 *            图形个数
	 * @param divName
	 *            div名称
	 * @param request
	 *            HttpServletRequest
	 * @return 生成fusionChart所需的脚本
	 */
	public static String getScript(int width, int height, int[] count, String[] divName, HttpServletRequest request) {
		if (count == null || divName == null || count.length != divName.length) {
			return "";
		}

		StringBuilder script = new StringBuilder("<script type=\"text/javascript\">").append("\n");

		script.append("var myChart = new FusionCharts({type : 'StackedColumn3D',width : '").append(width).append("',height : '").append(height).append("'});").append("\n");

		for (int i = 0; i < count.length; i++) {
			for (int j = 1; j <= count[i]; j++) {
				script.append("var jsonData").append(divName[i]).append(j).append(" = ").append(request.getAttribute("jsonData" + divName[i] + j)).append(";").append("\n");
			}
		}

		script.append("</script>");

		return script.toString();
	}
}
