package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TChannelType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CHANNEL_TYPE", schema = "LIFE")
public class TChannelType implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = 2400765168931225520L;

	private Integer id;

	private Integer typeId;

	private String displayName;

	private String regionCode;

	private Integer channel;

	// Constructors

	/** default constructor */
	public TChannelType() {
	}

	/** minimal constructor */
	public TChannelType(Integer id, Integer typeId) {
		this.id = id;
		this.typeId = typeId;
	}

	/** full constructor */
	public TChannelType(Integer id, Integer typeId, String displayName, String regionCode, Integer channel) {
		this.id = id;
		this.typeId = typeId;
		this.displayName = displayName;
		this.regionCode = regionCode;
		this.channel = channel;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TYPE_ID", nullable = false, precision = 20, scale = 0)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "DISPLAY_NAME", length = 100)
	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "REGION_CODE", length = 100)
	public String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@Column(name = "CHANNEL", precision = 4, scale = 0)
	public Integer getChannel() {
		return this.channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

}