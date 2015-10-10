package com.cplatform.mall.back.cont.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-25 下午1:25:03
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Entity
@Table(name = "T_CONT_CODE")
public class ContentCode extends IdEntity implements java.io.Serializable {

	private static Map<Integer, String> codeTypeMap = null;

	// 0,"表示点播"，1,"表示定制,3 ,"折扣券短信,4,"挂机短信,5," 挂机彩信,
	// 6,"广告彩信,7,"折扣券彩信,8,"种子彩信,9,"群发,10,"添加折扣券彩信时录入短信
	static {
		codeTypeMap = new HashMap<Integer, String>();
		codeTypeMap.put(0, "点播");
		codeTypeMap.put(1, "定制");
		codeTypeMap.put(2, "群发");
	}

	public static Map<Integer, String> getCodeTypeMap() {
		return codeTypeMap;
	}

	private static Map<Integer, String> contTypeMap = null;
	static {
		contTypeMap = new HashMap<Integer, String>();
		contTypeMap.put(0, "短信");
		contTypeMap.put(1, "彩信");
	}

	public static Map<Integer, String> getContTypeMap() {
		return contTypeMap;
	}

	/** 内容源ID，使用序列SEQ_CONT_CODE */
	private Long id;

	/** 内容源英文标识，唯一 */
	private String code;

	/** 内容源名称 */
	private String name;

	/** 内容源归属地市,例如025 */
	private String areaCode;

	/** 内容源信息类型，0表示短信，1表示彩信 */
	private Integer contType;

	@Transient
	public String getContTypeName() {
		return contTypeMap.get(this.contType);
	}

	private Integer codeType;

	@Transient
	public String getCodeTypeName() {
		return codeTypeMap.get(this.codeType);
	}

	@Column(name = "CODE", nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "CONT_TYPE", nullable = false, precision = 1, scale = 0)
	public Integer getContType() {
		return contType;
	}

	public void setContType(Integer contType) {
		this.contType = contType;
	}

	@Column(name = "CODE_TYPE", nullable = false, precision = 2, scale = 0)
	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
}
