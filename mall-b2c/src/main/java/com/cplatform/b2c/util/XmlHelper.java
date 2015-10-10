package com.cplatform.b2c.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) Mar 12, 2014 4:42:14 PM
 * <p>
 * Company: 宽连信息(苏州)技术有限公司
 * <p>
 * 
 * @author wangtaob@c-platform.com
 * @version 1.0.0
 */
class XmlHelper {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public List<Element> XmlParse(String name) {
		SAXBuilder builder = new SAXBuilder();
		InputStream file = null;
		try {
			if (StringUtils.isNotBlank(name)) {
				file = new FileInputStream(this.getClass().getClassLoader().getResource("menu/" + name + ".xml").getPath());
			} else {
				file = new FileInputStream(this.getClass().getClassLoader().getResource("menu/base.xml").getPath());
			}
			Document document = builder.build(file);// 获得文档对象
			Element root = document.getRootElement();// 获得根节点
			List<Element> list = root.getChildren();
			return list;
		}
		catch (Exception e) {
			logger.error("解析菜单xml异常", e);
			return null;
		}
	}

	public static void main(String[] args) {
		XmlHelper helper = new XmlHelper();
		helper.XmlParse("");
	}
}
