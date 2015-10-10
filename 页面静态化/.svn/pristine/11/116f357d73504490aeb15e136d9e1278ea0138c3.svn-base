package com.cplatform.mall.back.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

	@Value("#{appProperties['Template.Upload_Path']}")
	private String Template_Upload_Path;

	@Value("#{appProperties['Template.Config_Encoding']}")
	private String Template_Config_Encoding;

	@Value("#{appProperties['Template.FTL_Encoding']}")
	private String Template_FTL_Encoding;

	@Value("#{appProperties['Template.Component_URL']}")
	private String Template_Component_URL;

	public String getTemplate_Upload_Path() {
		return Template_Upload_Path;
	}

	public void setTemplate_Upload_Path(String template_Upload_Path) {
		Template_Upload_Path = template_Upload_Path;
	}

	public String getTemplate_Config_Encoding() {
		return Template_Config_Encoding;
	}

	public void setTemplate_Config_Encoding(String template_Config_Encoding) {
		Template_Config_Encoding = template_Config_Encoding;
	}

	public String getTemplate_FTL_Encoding() {
		return Template_FTL_Encoding;
	}

	public void setTemplate_FTL_Encoding(String template_FTL_Encoding) {
		Template_FTL_Encoding = template_FTL_Encoding;
	}

	public String getTemplate_Component_URL() {
		return Template_Component_URL;
	}

	public void setTemplate_Component_URL(String template_Component_URL) {
		Template_Component_URL = template_Component_URL;
	}

}
