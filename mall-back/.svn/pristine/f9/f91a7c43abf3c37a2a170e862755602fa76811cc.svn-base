package com.cplatform.mall.back.websys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title.普通文件存储类 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-29 下午5:45:18
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@javax.persistence.Table(name = "T_SYS_FILE_IMG")
@Entity
public class SysFileImg extends IdEntity {

	/** 文件类型 */
	private String fileType;

	/** 说明 */
	private String remark;

	/** 更新时间 */
	private String updateTime;

	/** 文件物理路径 */
	private String fileAbsPath;

	/** web路径 */
	private String fileWebPath;

	/** 创建人 */
	private Long createUser;

	/** 文件名 */
	private String fileName;

	/** 业务key */
	private String bsKey;

	/** 业务id */
	private Long bsId;

	/** 表明 */
	private String tableName;

	/** 排序 */
	private Integer sort;

	@Column(name = "file_type")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "update_time")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "file_abs_path")
	public String getFileAbsPath() {
		return fileAbsPath;
	}

	public void setFileAbsPath(String fileAbsPath) {
		this.fileAbsPath = fileAbsPath;
	}

	@Column(name = "file_web_path")
	public String getFileWebPath() {
		return fileWebPath;
	}

	public void setFileWebPath(String fileWebPath) {
		this.fileWebPath = fileWebPath;
	}

	@Column(name = "create_user")
	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "bs_key")
	public String getBsKey() {
		return bsKey;
	}

	public void setBsKey(String bsKey) {
		this.bsKey = bsKey;
	}

	@Column(name = "bs_id")
	public Long getBsId() {
		return bsId;
	}

	public void setBsId(Long bsId) {
		this.bsId = bsId;
	}

	@Column(name = "table_name")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	//******bus method****
	private String path;
	@Transient
	public void setPath(String path) {
	    this.path = path;
    }
	@Transient
	public String getPath() {
	    return path;
    }
}
