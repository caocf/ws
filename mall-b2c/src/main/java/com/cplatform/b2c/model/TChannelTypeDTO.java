package com.cplatform.b2c.model;

import java.util.List;
/**
 * TChannelType entity. @author MyEclipse Persistence Tools
 */
public class TChannelTypeDTO implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = 2400765168931225520L;

	private Integer id;

	private Integer typeId;

	private String displayName;

	private String regionCode;

	private Integer channel;
	
	private List<TSysTypeDTO> tSysTypeDTOList;

	// Constructors

	/** default constructor */
	public TChannelTypeDTO() {
	}

	/** minimal constructor */
	public TChannelTypeDTO(Integer id, Integer typeId) {
		this.id = id;
		this.typeId = typeId;
	}

	/** full constructor */
	public TChannelTypeDTO(Integer id, Integer typeId, String displayName, String regionCode, Integer channel) {
		this.id = id;
		this.typeId = typeId;
		this.displayName = displayName;
		this.regionCode = regionCode;
		this.channel = channel;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public Integer getChannel() {
		return this.channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	
    public List<TSysTypeDTO> gettSysTypeDTOList() {
    	return tSysTypeDTOList;
    }

	
    public void settSysTypeDTOList(List<TSysTypeDTO> tSysTypeDTOList) {
    	this.tSysTypeDTOList = tSysTypeDTOList;
    }

}