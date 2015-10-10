package com.cplatform.mall.back.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @Title	第三方码导入信息实体类
 * @Description
 * @Copyright: Copyright (c) 2013-10-21
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Entity
@Table(name="T_VERIFY_CODE_IMPORT")
public class ThirdCodeImport implements java.io.Serializable{

	private static final long serialVersionUID = 8657085140204989494L;

	private Long id;
	/**
	 * 第三方码名称id
	 */
	private Long codeNameId;
	/**
	 * 上传文件路径
	 */
	private String filePath;
	/**
	 * 导入文件时间
	 */
	private String createTime;
	/**
	 * 操作用户
	 */
	private Long userId;
	/**
	 * 导入记录数量
	 */
	private Long totalCount;
	/**
	 * 导入类型,0-方正码平台 1-平台自己 2-第三方应用3-卡密
	 */
	private Long codeType;
	/**
	 * 导入用户名
	 */
	private String userName;
	/**
	 * 第三方码名称
	 */
	private String codeName;
	
	private Long storeId;
	
	private String storeName;
	
	
	@SequenceGenerator(name = "seq_third_code_import", sequenceName = "SEQ_THIRD_CODE_IMPORT")
	@Id
	@GeneratedValue(generator = "seq_third_code_import")
	@JsonProperty
	public Long getId() {
		return id;
	}
	@Column(name = "CODE_NAME_ID")
	public Long getCodeNameId() {
		return codeNameId;
	}
	@Column(name = "FILE_PATH")
	public String getFilePath() {
		return filePath;
	}
	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}
	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "TOTAL_COUNT")
	public Long getTotalCount() {
		return totalCount;
	}
	@Column(name = "CODE_TYPE")
	public Long getCodeType() {
		return codeType;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCodeNameId(Long codeNameId) {
		this.codeNameId = codeNameId;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public void setCodeType(Long codeType) {
		this.codeType = codeType;
	}
	@Transient
	public String getUserName() {
		return userName;
	}
	@Column(name="code_name")
	public String getCodeName() {
		return codeName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	@Column(name = "STORE_ID")
	public Long getStoreId() {
		return storeId;
	}
	@Transient
	public String getStoreName() {
		return storeName;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	
	
}
