package com.cplatform.mall.dc.utils.fusionChart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class FusionChartsCreator {

    public static String addCacheToDataURL(String strDataURL) {
	String cachedURL = strDataURL;
	Calendar nowCal = Calendar.getInstance();
	Date now = nowCal.getTime();
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH_mm_ss a");
	String strNow = sdf.format(now);

	try {

	    if (strDataURL.indexOf("?") > 0) {
		cachedURL = strDataURL + "&FCCurrTime="
		+ URLEncoder.encode(strNow, "UTF-8");
	    } else {
		cachedURL = strDataURL + "?FCCurrTime="
		+ URLEncoder.encode(strNow, "UTF-8");
	    }

	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	    cachedURL = strDataURL + "?FCCurrTime=" + strNow;
	}

	return cachedURL;
    }

    /**
     * Creates the JavaScript + HTML code required to embed a chart.<br>
     * Uses the javascript FusionCharts class to create the chart by supplying <br>
     * the required parameters to it.<br>
     * Note: Only one of the parameters strURL or strXML has to be not null for this<br>
     * method to work. If both the parameters are provided then strURL is used for further processing.<br>
     * 
     * @param chartSWF -
     *                SWF File Name (and Path) of the chart which you intend
     *                to plot
     * @param strURL -
     *                If you intend to use dataURL method for this chart,
     *                pass the URL as this parameter. Else, set it to "" (in
     *                case of dataXML method)
     * @param strXML -
     *                If you intend to use dataXML method for this chart,
     *                pass the XML data as this parameter. Else, set it to ""
     *                (in case of dataURL method)
     * @param chartId -
     *                Id for the chart, using which it will be recognized in
     *                the HTML page. Each chart on the page needs to have a
     *                unique Id.
     * @param chartWidth -
     *                Intended width for the chart (in pixels)
     * @param chartHeight -
     *                Intended height for the chart (in pixels)
     * @param debugMode -
     *                Whether to start the chart in debug mode
     * @param registerWithJS -
     *                Whether to ask chart to register itself with
     *                JavaScript
     */
    public static String createChart(String chartSWF, String strURL,
	    String strXML, String chartId, int chartWidth, int chartHeight,
	    boolean debugMode, boolean registerWithJS) {
	StringBuffer strBuf = new StringBuffer();
	strBuf.append("\t\t<!-- START Script Block for Chart-->\n");
	strBuf.append("\t\t<div id='" + chartId + "Div' align='center'>\n");
	strBuf.append("\t\t</div>\n");
	strBuf.append("\t\t<script type='text/javascript'>\n");
	Boolean registerWithJSBool = Boolean.valueOf(registerWithJS);
	Boolean debugModeBool = Boolean.valueOf(debugMode);
	int regWithJSInt = boolToNum(registerWithJSBool);
	int debugModeInt = boolToNum(debugModeBool);
	strBuf.append("\t\tfunction showPie3D(){\n");
	strBuf.append("\t\t\t\tvar chart_" + chartId + " = new FusionCharts('"
		+ chartSWF + "', '" + chartId + "', '" + chartWidth + "', '"
		+ chartHeight + "', '" + debugModeInt + "', '" + regWithJSInt
		+ "');\n");
	if (strXML.equals("")) {
	    strBuf.append("\t\t\t\t// Set the dataURL of the chart\n");
	    strBuf.append("\t\t\t\tchart_" + chartId + ".setDataURL(\"" + strURL
		    + "\");\n");
	} else {
	    strBuf.append("\t\t\t\t// Provide entire XML data using dataXML method\n");
	    strBuf.append("\t\t\t\tchart_" + chartId + ".setDataXML(\"" + strXML
		    + "\");\n");
	}
	strBuf.append("\t\t\t\t// Finally, render the chart.\n");
	strBuf.append("\t\t\t\tchart_" + chartId + ".render(\"" + chartId + "Div\");\n");
	strBuf.append("\t\t}\n");
	strBuf.append("\t\t</script>\n");
	strBuf.append("\t\t<!--END Script Block for Chart-->\n");
	return strBuf.substring(0);
    }

    /**
     * Creates the object tag required to embed a chart.
     * Generates the object tag to embed the swf directly into the html page.<br>
     * Note: Only one of the parameters strURL or strXML has to be not null for this<br>
     * method to work. If both the parameters are provided then strURL is used for further processing.<br>
     *  
     * @param chartSWF -
     *                SWF File Name (and Path) of the chart which you intend
     *                to plot
     * @param strURL -
     *                If you intend to use dataURL method for this chart,
     *                pass the URL as this parameter. Else, set it to "" (in
     *                case of dataXML method)
     * @param strXML -
     *                If you intend to use dataXML method for this chart,
     *                pass the XML data as this parameter. Else, set it to ""
     *                (in case of dataURL method)
     * @param chartId -
     *                Id for the chart, using which it will be recognized in
     *                the HTML page. Each chart on the page needs to have a
     *                unique Id.
     * @param chartWidth -
     *                Intended width for the chart (in pixels)
     * @param chartHeight -
     *                Intended height for the chart (in pixels)
     * @param debugMode -
     *                Whether to start the chart in debug mode
     */
    public static String createChartHTML(String chartSWF, String strURL,
	    String strXML, String chartId, int chartWidth, int chartHeight,
	    boolean debugMode) {
	String strFlashVars = "";
	Boolean debugModeBool = Boolean.valueOf(debugMode);

	if (strXML.equals("")) {
	    // DataURL Mode
	    strFlashVars = "chartWidth=" + chartWidth + "&chartHeight="
	    + chartHeight + "&debugMode=" + boolToNum(debugModeBool)
	    + "&dataURL=" + strURL + "";
	} else {
	    // DataXML Mode
	    strFlashVars = "chartWidth=" + chartWidth + "&chartHeight="
	    + chartHeight + "&debugMode=" + boolToNum(debugModeBool)
	    + "&dataXML=" + strXML + "";
	}
	StringBuffer strBuf = new StringBuffer();

	strBuf.append("\t\t<!--START Code Block for Chart-->\n");
	strBuf
	.append("\t\t<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' width='"
		+ chartWidth
		+ "' height='"
		+ chartHeight
		+ "' id='"
		+ chartId + "'>\n");
	strBuf.append("\t\t\t\t<param name='allowScriptAccess' value='always' />\n");
	strBuf.append("\t\t\t\t<param name='movie' value='" + chartSWF + "'/>\n");
	strBuf.append("\t\t\t\t<param name='FlashVars' value=\"" + strFlashVars
		+ "\" />\n");
	strBuf.append("\t\t\t\t<param name='quality' value='high' />\n");
	strBuf
	.append("\t\t\t\t<embed src='"
		+ chartSWF
		+ "' FlashVars=\""
		+ strFlashVars
		+ "\" quality='high' width='"
		+ chartWidth
		+ "' height='"
		+ chartHeight
		+ "' name='"
		+ chartId
		+ "' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' />\n");
	strBuf.append("\t\t</object>\n");
	strBuf.append("\t\t<!--END Code Block for Chart-->\n");
	return strBuf.substring(0);
    }

   public static int boolToNum(Boolean bool) {
	int num = 0;
	if (bool.booleanValue()) {
	    num = 1;
	}
	return num;
    }
}
