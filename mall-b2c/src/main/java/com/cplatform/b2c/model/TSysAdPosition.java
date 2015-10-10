package com.cplatform.b2c.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
* 广告Position表信息
 * <p>
 * Copyright: Copyright (c) Jun 26, 2013 10:10:00 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author yangxm@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SYS_AD_POSITION", schema = "LIFE")
public class TSysAdPosition{

	/** id */
	private Integer id;
	/** 名称 */
	private String name;
	/** 宽 */
	private Integer width;
	/** 高 */
	private Integer height;
	/** 模板位置 */
	private String position;
	/** 模板代码 */
	private String code;
	/** 模板类型 */
	private Integer type;
	/** 广告清单 */
	private List<TSysAd> adList = new ArrayList<TSysAd>(); 
	/**
	 * 主键id
	 * @return
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 设置NAME
	 * @return
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 设置WIDTH
	 * @return
	 */
	@Column(name = "WIDTH")
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	
	/**
	 * 设置HEIGHT
	 * @return
	 */
	@Column(name = "HEIGHT")
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	/**
	 * 设置POSITION
	 * @return
	 */
	@Column(name = "POSITION")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * 设置CODE
	 * @return
	 */
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 设置TYPE
	 * @return
	 */
	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取广告列表
	 * @return
	 */
	@OneToMany(mappedBy = "position", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<TSysAd> gettSysAdList() {
		return adList;
	}

	public void settSysAdList(List<TSysAd> tSysAdList) {
		this.adList = tSysAdList;
	}

}
