package com.cplatform.mall.back.cms.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-20 下午5:07:39
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */

@Entity
@Table(name = "T_SYS_TEMPLATE_INFO")
@SequenceGenerator(name="SEQ_SYS_COMM_ID",sequenceName="SEQ_SYS_COMM_ID")
public class WebTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4583598706313954596L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SYS_COMM_ID")
	private int id;

	private String tName;

	/*
	 * type=1，数据查询路径 type=2，jsp页面路径
	 */
	private String dataURL;

	/*
	 * type=1，模板路径 type=2，jsp缓存保存
	 */
	private String filePath;

	public final static int TYPE_TEMPLATE = 1;

	public final static int TYPE_JSP = 2;

	private int type;

	// 输出页面编码格式
	private String outputChartset;

	public static WebTemplate newInstance(String name, String dataURL,
			String filePath, int type, String outputChartset) {

		WebTemplate template = new WebTemplate();

		template.settName(name);
		template.setFilePath(filePath);
		template.setDataURL(dataURL);
		template.setType(type);
		template.setOutputChartset(outputChartset);

		return template;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getDataURL() {
		return dataURL;
	}

	public void setDataURL(String dataURL) {
		this.dataURL = dataURL;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getOutputChartset() {
		return outputChartset;
	}

	public void setOutputChartset(String outputChartset) {
		this.outputChartset = outputChartset;
	}

}
