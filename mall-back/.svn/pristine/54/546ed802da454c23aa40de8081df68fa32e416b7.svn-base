package com.cplatform.mall.back.websys.entity;

/**
 * Title. <br>
 * 模版信息实体类
 * <p>
 * Copyright: Copyright (c) 2013-6-8
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: zhouhui@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_SYS_TEMPLATE_INFO")
public class SysTemplateInfo {

	// 模版组建
	private Integer id;

	// 模版名称
	private String tName;

	/*
	 * type=1，数据查询路径 type=2，jsp页面路径
	 */
	private int type;

	/*
	 * type=1，此字段标识数据获取URL type=2，此字段填入需要通过 httpclient静态化的jsp页面的地址
	 */
	private String dataUrl;

	/**
	 * 模板上传保存全路径
	 */
	private String filePath;

	/**
	 * 输出文件编码类型
	 */
	private String outputCharTset;

	/** 支持的输入文件编码 */
	public static List<String> charList = new ArrayList<String>();
	static {
		charList.add("UTF-8");
		charList.add("GBK");
		charList.add("GB2312");
	}

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TNAME", nullable = false)
	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	@Column(name = "TYPE", nullable = false)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "DATAURL", nullable = false)
	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	@Column(name = "FILEPATH")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "OUTPUTCHARTSET")
	public String getOutputCharTset() {
		return outputCharTset;
	}

	public void setOutputCharTset(String outputCharTset) {
		this.outputCharTset = outputCharTset;
	}

}
