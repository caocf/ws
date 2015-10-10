package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TStore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_STORE", schema = "LIFE")
public class TStore implements java.io.Serializable {

	// Fields

	/** serialVersionUID */
    private static final long serialVersionUID = 6056759941155866953L;

	private Integer id;

	private String name;

	private String shortName;

	private String areaCode;

	private Integer sort;

	private Integer shopAgentFlag;

	private Integer goodsAgentFlag;

	private String baseShopName;

	private String createTime;

	private String updateTime;

	private String address;

	private Integer sysUserId;

	private Integer updateUserId;

	private Integer isBsAccount;

	private String bsManagerName;

	private String bsManagerPhone;

	private String fcManagerName;

	private String fcManagerPhone;

	private String linkName;

	private String linkPhone;

	private String linkFax;

	private Integer shopClass;

	private Integer status;

	private Integer isValid;

	private Integer itemEditAuditFlag;

	private Integer itemPublishAuditFlag;

	private Integer shopEditAuditFlag;

	private Integer syncGyFlag;

	private String bsScope;

	private String servicePhone;

	private String areaId;

	private String merchid;

	// Constructors

	/** default constructor */
	public TStore() {
	}

	/** minimal constructor */
	public TStore(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TStore(Integer id, String name, String shortName, String areaCode, Integer sort, Integer shopAgentFlag, Integer goodsAgentFlag,
	              String baseShopName, String createTime, String updateTime, String address, Integer sysUserId, Integer updateUserId,
	              Integer isBsAccount, String bsManagerName, String bsManagerPhone, String fcManagerName, String fcManagerPhone, String linkName,
	              String linkPhone, String linkFax, Integer shopClass, Integer status, Integer isValid, Integer itemEditAuditFlag,
	              Integer itemPublishAuditFlag, Integer shopEditAuditFlag, Integer syncGyFlag, String bsScope, String servicePhone, String areaId,
	              String merchid) {
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.areaCode = areaCode;
		this.sort = sort;
		this.shopAgentFlag = shopAgentFlag;
		this.goodsAgentFlag = goodsAgentFlag;
		this.baseShopName = baseShopName;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.address = address;
		this.sysUserId = sysUserId;
		this.updateUserId = updateUserId;
		this.isBsAccount = isBsAccount;
		this.bsManagerName = bsManagerName;
		this.bsManagerPhone = bsManagerPhone;
		this.fcManagerName = fcManagerName;
		this.fcManagerPhone = fcManagerPhone;
		this.linkName = linkName;
		this.linkPhone = linkPhone;
		this.linkFax = linkFax;
		this.shopClass = shopClass;
		this.status = status;
		this.isValid = isValid;
		this.itemEditAuditFlag = itemEditAuditFlag;
		this.itemPublishAuditFlag = itemPublishAuditFlag;
		this.shopEditAuditFlag = shopEditAuditFlag;
		this.syncGyFlag = syncGyFlag;
		this.bsScope = bsScope;
		this.servicePhone = servicePhone;
		this.areaId = areaId;
		this.merchid = merchid;
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

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SHORT_NAME", length = 20)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "AREA_CODE", length = 10)
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "SORT", precision = 1, scale = 0)
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "SHOP_AGENT_FLAG", precision = 1, scale = 0)
	public Integer getShopAgentFlag() {
		return this.shopAgentFlag;
	}

	public void setShopAgentFlag(Integer shopAgentFlag) {
		this.shopAgentFlag = shopAgentFlag;
	}

	@Column(name = "GOODS_AGENT_FLAG", precision = 1, scale = 0)
	public Integer getGoodsAgentFlag() {
		return this.goodsAgentFlag;
	}

	public void setGoodsAgentFlag(Integer goodsAgentFlag) {
		this.goodsAgentFlag = goodsAgentFlag;
	}

	@Column(name = "BASE_SHOP_NAME", length = 50)
	public String getBaseShopName() {
		return this.baseShopName;
	}

	public void setBaseShopName(String baseShopName) {
		this.baseShopName = baseShopName;
	}

	@Column(name = "CREATE_TIME", length = 14)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME", length = 14)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "ADDRESS", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "SYS_USER_ID", precision = 8, scale = 0)
	public Integer getSysUserId() {
		return this.sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

	@Column(name = "UPDATE_USER_ID", precision = 9, scale = 0)
	public Integer getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "IS_BS_ACCOUNT", precision = 1, scale = 0)
	public Integer getIsBsAccount() {
		return this.isBsAccount;
	}

	public void setIsBsAccount(Integer isBsAccount) {
		this.isBsAccount = isBsAccount;
	}

	@Column(name = "BS_MANAGER_NAME", length = 50)
	public String getBsManagerName() {
		return this.bsManagerName;
	}

	public void setBsManagerName(String bsManagerName) {
		this.bsManagerName = bsManagerName;
	}

	@Column(name = "BS_MANAGER_PHONE", length = 50)
	public String getBsManagerPhone() {
		return this.bsManagerPhone;
	}

	public void setBsManagerPhone(String bsManagerPhone) {
		this.bsManagerPhone = bsManagerPhone;
	}

	@Column(name = "FC_MANAGER_NAME", length = 50)
	public String getFcManagerName() {
		return this.fcManagerName;
	}

	public void setFcManagerName(String fcManagerName) {
		this.fcManagerName = fcManagerName;
	}

	@Column(name = "FC_MANAGER_PHONE", length = 50)
	public String getFcManagerPhone() {
		return this.fcManagerPhone;
	}

	public void setFcManagerPhone(String fcManagerPhone) {
		this.fcManagerPhone = fcManagerPhone;
	}

	@Column(name = "LINK_NAME", length = 50)
	public String getLinkName() {
		return this.linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	@Column(name = "LINK_PHONE", length = 50)
	public String getLinkPhone() {
		return this.linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	@Column(name = "LINK_FAX", length = 50)
	public String getLinkFax() {
		return this.linkFax;
	}

	public void setLinkFax(String linkFax) {
		this.linkFax = linkFax;
	}

	@Column(name = "SHOP_CLASS", precision = 1, scale = 0)
	public Integer getShopClass() {
		return this.shopClass;
	}

	public void setShopClass(Integer shopClass) {
		this.shopClass = shopClass;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "IS_VALID", precision = 1, scale = 0)
	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	@Column(name = "ITEM_EDIT_AUDIT_FLAG", precision = 1, scale = 0)
	public Integer getItemEditAuditFlag() {
		return this.itemEditAuditFlag;
	}

	public void setItemEditAuditFlag(Integer itemEditAuditFlag) {
		this.itemEditAuditFlag = itemEditAuditFlag;
	}

	@Column(name = "ITEM_PUBLISH_AUDIT_FLAG", precision = 1, scale = 0)
	public Integer getItemPublishAuditFlag() {
		return this.itemPublishAuditFlag;
	}

	public void setItemPublishAuditFlag(Integer itemPublishAuditFlag) {
		this.itemPublishAuditFlag = itemPublishAuditFlag;
	}

	@Column(name = "SHOP_EDIT_AUDIT_FLAG", precision = 1, scale = 0)
	public Integer getShopEditAuditFlag() {
		return this.shopEditAuditFlag;
	}

	public void setShopEditAuditFlag(Integer shopEditAuditFlag) {
		this.shopEditAuditFlag = shopEditAuditFlag;
	}

	@Column(name = "SYNC_GY_FLAG", precision = 1, scale = 0)
	public Integer getSyncGyFlag() {
		return this.syncGyFlag;
	}

	public void setSyncGyFlag(Integer syncGyFlag) {
		this.syncGyFlag = syncGyFlag;
	}

	@Column(name = "BS_SCOPE", length = 200)
	public String getBsScope() {
		return this.bsScope;
	}

	public void setBsScope(String bsScope) {
		this.bsScope = bsScope;
	}

	@Column(name = "SERVICE_PHONE", length = 50)
	public String getServicePhone() {
		return this.servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	@Column(name = "AREA_ID", length = 20)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "MERCHID", length = 20)
	public String getMerchid() {
		return this.merchid;
	}

	public void setMerchid(String merchid) {
		this.merchid = merchid;
	}

}