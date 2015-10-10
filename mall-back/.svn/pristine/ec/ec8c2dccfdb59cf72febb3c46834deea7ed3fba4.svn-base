package com.cplatform.mall.back.websys.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.cplatform.mall.back.entity.IdEntity;

@javax.persistence.Table(name = "T_SYS_FILE_IMG_LINK")
@Entity
public class SysFileImgLink extends IdEntity {

	/** 业务key */
	private String bsKey;

	/** 业务id */
	private Long bsId;

	/** 文件id */
	private Long fileId;

	/** 表明 */
	private String tableName;

	/** 文件名 */
	private String fileName;

	/** 排序 */
	private Integer sort;

	private SysFileImg sysFileImg;

	public SysFileImgLink() {
	}

	public SysFileImgLink(String bsKey, Long bsId, Long fileId, String tableName) {
		super();
		this.bsKey = bsKey;
		this.bsId = bsId;
		this.fileId = fileId;
		this.tableName = tableName;
	}

	@Column(name = "BS_KEY")
	public String getBsKey() {
		return bsKey;
	}

	public void setBsKey(String bsKey) {
		this.bsKey = bsKey;
	}

	@Column(name = "BS_ID")
	public Long getBsId() {
		return bsId;
	}

	public void setBsId(Long bsId) {
		this.bsId = bsId;
	}

	@Column(name = "FILE_ID")
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	@Column(name = "TABLE_NAME")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "FILE_ID", insertable = false, updatable = false)
	public SysFileImg getSysFileImg() {
		return sysFileImg;
	}

	public void setSysFileImg(SysFileImg sysFileImg) {
		this.sysFileImg = sysFileImg;
	}

	// -------------bus method ---------------------
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
