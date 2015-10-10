package com.cplatform.b2c.model;

import java.util.List;

import javax.persistence.Id;

/**
 * TSysType entity. @author MyEclipse Persistence Tools
 */
public class TSysTypeDTO implements java.io.Serializable {

	// Fields


	/** serialVersionUID */
    private static final long serialVersionUID = -5726701126900646493L;

	private Integer id;

	private Integer PId;

	private String name;

	private Integer type;
	
	private Integer count;
	
	private List<TSysTypeDTO> typeList;
	
	

	// Constructors

	/** default constructor */
	public TSysTypeDTO() {
	}

	/** minimal constructor */
	public TSysTypeDTO(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TSysTypeDTO(Integer id, Integer PId, String name, Integer type, Integer count, List<TSysTypeDTO> typeList) {
		this.id = id;
		this.PId = PId;
		this.name = name;
		this.type = type;
		this.count = count;
		this.typeList = typeList;
	}

	// Property accessors
	@Id
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
    public Integer getCount() {
    	return count;
    }

	
    public void setCount(Integer count) {
    	this.count = count;
    }

	
    public List<TSysTypeDTO> getTypeList() {
    	return typeList;
    }

	
    public void setTypeList(List<TSysTypeDTO> typeList) {
    	this.typeList = typeList;
    }

}