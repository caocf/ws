package com.cplatform.mall.back.websys.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cplatform.mall.back.entity.IdEntity;

@javax.persistence.Table(name = "T_SYS_FILE_LINK")
@Entity
public class SysFileLink extends IdEntity {

	/** 业务key */
	private String bsKey;

	/** 业务id */
	private Long bsId;

	/** 文件id */
	private Long fileId;

	/** 表明 */
	private String tableName;

	/** 原始文件表 */
	private SysFile sysFile;

	public SysFileLink() {
	}

	public SysFileLink(String bsKey, Long bsId, Long fileId, String tableName) {
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

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "FILE_ID", insertable = false, updatable = false)
	public SysFile getSysFile() {
		return sysFile;
	}

	public void setSysFile(SysFile sysFile) {
		this.sysFile = sysFile;
	}

}
