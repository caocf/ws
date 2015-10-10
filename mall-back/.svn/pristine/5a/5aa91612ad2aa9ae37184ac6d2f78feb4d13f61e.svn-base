package com.cplatform.mall.back.cont.mms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.cplatform.mall.back.cont.mms.bean.Frame;

/**
 * smil文件内容的帮助类. <br>
 * 类详细说明.
 * <p>
 * 修改历史: Jan 15, 2010 10:50:55 AM baowr@c-platform.com <br>
 * 修改说明: 代码规范修改 <br>
 * <p>
 * Copyright: Copyright (c) Jan 15, 2009 10:50:37 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author chengyao@c-platform.com
 * @version 1.0.0
 */
public class SmilHelper {

	/** 换行字符 */
	private static final String LINE_SEPARATOR = "\r\n";

	/** smil文件内容尾部 */
	private static String SMIL_END = "";

	/** smil文件内容头部 */
	private static String SMIL_START = "";

	/** 缩进字符 */
	private static final String TAB = "  ";

	/**
	 * smil文件头部分和尾部分
	 */
	static {
		SMIL_START = "<smil xmlns=\"http://www.w3.org/2000/SMIL20/CR/Language\">" + LINE_SEPARATOR + TAB + "<head>" + LINE_SEPARATOR + TAB + TAB
		        + "<layout>" + LINE_SEPARATOR + TAB + TAB + TAB + "<region id=\"Image\" top=\"0\" left=\"0\" fit=\"meet\"></region>" + LINE_SEPARATOR
		        + TAB + TAB + TAB + "<region id=\"Text\" top=\"150\" left=\"0\"></region>" + LINE_SEPARATOR + TAB + TAB + "</layout>"
		        + LINE_SEPARATOR + TAB + "</head>" + LINE_SEPARATOR + TAB + "<body>" + LINE_SEPARATOR;
		SMIL_END = TAB + "</body>" + LINE_SEPARATOR + "</smil>";
	}

	/**
	 * 声音节点
	 * 
	 * @param src
	 *            声音源文件
	 * @return string
	 */
	private static String audio(String src) {
		return TAB + TAB + TAB + "<audio src=\"" + src + "\"></audio>" + LINE_SEPARATOR;
	}

	/**
	 * 查询smil文件声音节点
	 * 
	 * @param smilFilePath
	 *            smil文件路径
	 * @return string
	 */
	public static String getSrcValues(String smilFilePath) {
		String content = "";
		try {
			File smilFile = new File(smilFilePath);
			if (!smilFile.exists())
				return "";
			FileInputStream fis = new FileInputStream(smilFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.indexOf("audio") != -1) {
					if (line.indexOf("src=\"") != -1) {
						line = line.substring(line.indexOf("src=\"") + 5);
						content = line.substring(0, line.indexOf("\""));

						if (!"".equals(content)) {
							return content;
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return content;

	}

	/**
	 * 图片节点
	 * 
	 * @param src
	 *            图片源文件路径
	 * @return string
	 */
	public static String image(String src) {
		return TAB + TAB + TAB + "<img src=\"" + src + "\" region=\"Image\"></img>" + LINE_SEPARATOR;
	}

	/**
	 * 根据彩信内容对象Collection<Frame>生成Smil文件内容
	 * 
	 * @param frames
	 *            彩信内容对象
	 * @return 返回与彩信内容对象对应的smil文件内容
	 */
	public static String makeSmilContent(Collection<Frame> frames) {
		StringBuilder buff = new StringBuilder();
		buff.append(SMIL_START);
		for (Frame frame : frames) {
			buff.append(parStart(frame.getShowtime()));
			if (frame.getPic() != null) {
				buff.append(image(frame.getPic().getName()));
			}
			if (frame.getSound() != null) {
				buff.append(audio(frame.getSound().getName()));
			}
			if (frame.getText() != null) {
				if (StringUtils.isNotBlank(frame.getText().getValue())) {
					buff.append(text(frame.getText().getName()));
				}
			}
			buff.append(parEnd());
		}
		buff.append(SMIL_END);
		return buff.toString();
	}

	/**
	 * 帧节点尾部
	 * 
	 * @return string
	 */
	public static String parEnd() {
		return TAB + TAB + "</par>" + LINE_SEPARATOR;
	}

	/**
	 * 帧节点头部
	 * 
	 * @param obj
	 *            obj
	 * @return string
	 */
	private static String parStart(Object obj) {
		return TAB + TAB + "<par dur=\"" + obj + "s\">" + LINE_SEPARATOR;
	}

	/**
	 * 文本节点
	 * 
	 * @param src
	 *            文本文件原文件
	 * @return string
	 */
	public static String text(String src) {
		return TAB + TAB + TAB + "<text src=\"" + src + "\" region=\"Text\"></text>" + LINE_SEPARATOR;
	}
}
