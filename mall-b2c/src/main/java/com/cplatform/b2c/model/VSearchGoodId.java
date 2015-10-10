package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VSearchGoodId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class VSearchGoodId implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = -2855376270361689576L;

	private Integer GId;

//	private Integer GOrgId;

	// Constructors

	/** default constructor */
	public VSearchGoodId() {
	}

	/** minimal constructor */
	public VSearchGoodId(Integer GId) {
		this.GId = GId;
	}


	// Property accessors

	@Column(name = "G_ID", nullable = false, precision = 8, scale = 0)
	public Integer getGId() {
		return this.GId;
	}

	public void setGId(Integer GId) {
		this.GId = GId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VSearchGoodId))
			return false;
		VSearchGoodId castOther = (VSearchGoodId) other;

		return ((this.getGId() == castOther.getGId()) || (this.getGId() != null && castOther.getGId() != null && this.getGId()
		        .equals(castOther.getGId())))
		        ;
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getGId() == null ? 0 : this.getGId().hashCode());
		return result;
	}

}