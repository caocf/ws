package com.cplatform.mall.back.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cplatform.mall.back.entity.IdEntity;
/**
 * 门店扩展管理表. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-29 下午07:46:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
@Entity
@Table(name = "T_SHOP_EXT")
public class ShopExt extends IdEntity implements java.io.Serializable {
	
	/** 序列 **/
	private Long id;
	/** 门店ID **/
	private Long shopId;
	/** 评论量 **/
	private Long commentNum;
	/** 级别 **/
	private Long sLevel;
	/** 商户方正同步ID **/
	private Long fzStoreId;
	/** 门店方正同步ID **/
	private Long fzShopId;
	
	public void setShopId(Long shopId) {
	    this.shopId = shopId;
    }
	@Column(name = "SHOP_ID")
	public Long getShopId() {
	    return shopId;
    }
	public void setCommentNum(Long commentNum) {
	    this.commentNum = commentNum;
    }
	@Column(name = "COMMENT_NUM")
	public Long getCommentNum() {
	    return commentNum;
    }
	public void setsLevel(Long sLevel) {
	    this.sLevel = sLevel;
    }
	@Column(name = "S_LEVEL")
	public Long getsLevel() {
	    return sLevel;
    }
	public void setFzStoreId(Long fzStoreId) {
	    this.fzStoreId = fzStoreId;
    }
	@Column(name = "FZ_STORE_ID")
	public Long getFzStoreId() {
	    return fzStoreId;
    }
	public void setFzShopId(Long fzShopId) {
	    this.fzShopId = fzShopId;
    }
	@Column(name = "FZ_SHOP_ID")
	public Long getFzShopId() {
	    return fzShopId;
    }
	
	
}
