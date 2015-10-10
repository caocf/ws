package com.cplatform.mall.back.websys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.cplatform.mall.back.entity.IdEntity;

/**
 * Title.缩略图存储类 <br>
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

@javax.persistence.Table(name = "T_SYS_FILE_IMG_THUMB")
@Entity
public class SysFileImgThumb extends IdEntity {

	private Long fileId;

	/** 图片大小 50*50 */
	private String imgSize;

	/** 更新时间 */
	private String updateTime;

	/** 文件物理路径 */
	private String imgAbsPath;

	/** web路径 */
	private String imgWebPath;

	public SysFileImgThumb() {
		super();
	}

	public SysFileImgThumb(Long fileId, String imgSize, String updateTime, String imgAbsPath, String imgWebPath) {
		super();
		this.fileId = fileId;
		this.imgSize = imgSize;
		this.updateTime = updateTime;
		this.imgAbsPath = imgAbsPath;
		this.imgWebPath = imgWebPath;
	}

	@Column(name = "update_time")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "file_id")
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	@Column(name = "img_size")
	public String getImgSize() {
		return imgSize;
	}

	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}

	@Column(name = "img_abs_path")
	public String getImgAbsPath() {
		return imgAbsPath;
	}

	public void setImgAbsPath(String imgAbsPath) {
		this.imgAbsPath = imgAbsPath;
	}

	@Column(name = "img_web_path")
	public String getImgWebPath() {
		return imgWebPath;
	}

	public void setImgWebPath(String imgWebPath) {
		this.imgWebPath = imgWebPath;
	}

}
