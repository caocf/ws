package com.cplatform.mall.back.utils.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.common.Logger;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class RoleDataConfig {

	// 配置文件
	private static String configFile = "com/cplatform/mall/back/utils/data/role-data.xml";
	
	private static Logger log = Logger.getLogger(RoleDataConfig.class);

	public static RoleDataConfig instance = new RoleDataConfig();

	// 以模块名称加单位类型作为key(module-unitType)
	private Map<String, RoleData> roleRuleMap = new HashMap<String, RoleData>();

	private RoleDataConfig() {
		init();
	}

	public static RoleDataConfig getInstance() {
		return instance;
	}

	public void reload() {
		roleRuleMap.clear();
		init();
	}

	private void init() {
		try {

			SAXBuilder builder = new SAXBuilder(false);
			Document dom = builder.build(this.getClass().getClassLoader().getResource(configFile));
			// Document dom =
			// builder.build(this.getClass().getClassLoader().getResourceAsStream(configFile));
			Element settings = dom.getRootElement();
			List rules = settings.getChildren("rule");
			for (Iterator<Element> iter = rules.iterator(); iter.hasNext();) {
				Element rule = (Element) iter.next();
				String module = rule.getAttributeValue("module");
				String unitType = rule.getAttributeValue("unitType");
				String description = rule.getAttributeValue("description");
				String operate = rule.getAttributeValue("operate");
				String opStr = rule.getTextTrim();
				roleRuleMap.put(module + "-" + unitType, new RoleData(module, unitType, description, opStr, operate));
			}
		}
		catch (Exception e) {
			log.debug("加载配置文件失败:"+e.getMessage());
		}
		finally {

		}
	}

	public RoleData getModuleRule(String moduleKey) {
		return roleRuleMap.get(moduleKey);
	}

}
