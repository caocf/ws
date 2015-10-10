package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TSysType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_SYS_TYPE", schema = "LIFE")
public class TSysType implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
	private static final long serialVersionUID = 6158404777529598658L;

	private Integer id;

	private Integer PId;

	private String name;

	private Integer type;

	private Integer isValid;

	// Constructors

	/** default constructor */
	public TSysType() {
	}

	/** minimal constructor */
	public TSysType(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TSysType(Integer id, Integer PId, String name, Integer type, Integer isValid) {
		this.id = id;
		this.PId = PId;
		this.name = name;
		this.type = type;
		this.isValid = isValid;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "P_ID", precision = 8, scale = 0)
	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE", precision = 1, scale = 0)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "IS_VALID", precision = 1, scale = 0)
	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

}