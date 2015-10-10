package com.cplatform.mall.back.smsact.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonProperty;
/**
 * 二次开发短信业务指令表. <br>
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
@Table(name = "T_SMS_ACT_ROUTER")
public class SmsActRouter implements java.io.Serializable {
	/** serialVersionUID */
    private static final long serialVersionUID = 5235703634135451437L;
    /** 指令类型map */
	private static Map<String, String> cmdOptTypeMap = null;
	static {
		cmdOptTypeMap = new HashMap<String, String>();
		cmdOptTypeMap.put("1", "入口指令");
		cmdOptTypeMap.put("2", "其它指令");
	}
	/** 支付方式map */
	private static Map<String, String> payTypeMap = null;
	static {
		payTypeMap = new HashMap<String, String>();
		payTypeMap.put("0", "不需要支付");
		payTypeMap.put("1", "积分");
		payTypeMap.put("2", "商城币");
	}
	/** 是否需要会话map */
	private static Map<String, String> isSessionMap = null;
	static {
		isSessionMap = new HashMap<String, String>();
		isSessionMap.put("0", "不需要");
		isSessionMap.put("1", "需要");
	}	
	
	@Transient
	public static Map<String, String> getCmdOptTypeMap() {
		return cmdOptTypeMap;
	}	
	@Transient
	public String getCmdOptTypeName() {
		return cmdOptTypeMap.get(this.getCmdOptType() + "");
	}

	@Transient
	public static Map<String, String> getPayTypeMap() {
		return payTypeMap;
	}	
	@Transient
	public String getPayTypeName() {
		return payTypeMap.get(this.getPayType() + "");
	}
	
	@Transient
	public static Map<String, String> getIsSessionMap() {
		return isSessionMap;
	}	
	@Transient
	public String getIsSessionName() {
		return isSessionMap.get(this.getIsSession() + "");
	}
	
	//是否需要会话
	/**
	 * 是否需要会话 － 不需要
	 */
	public static final Long IS_SESSION_0 = 0L;
	/**
	 * 是否需要会话 －需要
	 */
	public static final Long IS_SESSION_1 = 1L;
	
	/** 序号 **/
	private Long id;
	/** 活动编号 **/
	private Long actId;
	/** 特服号 **/
	private String spCode;
	/** 指令类型 **/
	private Long cmdOptType;
	/** 指令内容 **/
	private String command;
	/** 支付方式 **/
	private Long payType;
	/** 购买价格 **/
	private Long itemPrice;
	/** 是否需要会话 **/
	private Long isSession;

	public void setId(Long id) {
		this.id = id;
	}
	@SequenceGenerator(name = "seq_sms_act_router", sequenceName = "SEQ_SMS_ACT_ROUTER")
	@Id
	@GeneratedValue(generator = "seq_sms_act_router")
	@JsonProperty
	public Long getId() {
		return id;
	}

	public void setActId(Long actId) {
	    this.actId = actId;
    }
	@Column(name = "ACT_ID")
	public Long getActId() {
	    return actId;
    }

	public void setSpCode(String spCode) {
	    this.spCode = spCode;
    }
	@Column(name = "SP_CODE")
	public String getSpCode() {
	    return spCode;
    }

	public void setCmdOptType(Long cmdOptType) {
	    this.cmdOptType = cmdOptType;
    }
	@Column(name = "CMD_OPT_TYPE")
	public Long getCmdOptType() {
	    return cmdOptType;
    }

	public void setCommand(String command) {
	    this.command = command;
    }
	@Column(name = "COMMAND")
	public String getCommand() {
	    return command;
    }

	public void setPayType(Long payType) {
	    this.payType = payType;
    }
	@Column(name = "PAY_TYPE")
	public Long getPayType() {
	    return payType;
    }

	public void setItemPrice(Long itemPrice) {
	    this.itemPrice = itemPrice;
    }
	@Column(name = "ITEM_PRICE")
	public Long getItemPrice() {
	    return itemPrice;
    }

	public void setIsSession(Long isSession) {
	    this.isSession = isSession;
    }
	@Column(name = "IS_SESSION")
	public Long getIsSession() {
	    return isSession;
    }
	//***************bus method***************
	private	String rSpCode;//根特服号
	private	String cSpCode;//尾特服号
	private SmsActOnline smsActOnline;
	
	@Transient
	public void setrSpCode(String rSpCode) {
	    this.rSpCode = rSpCode;
    }
	@Transient
	public String getrSpCode() {
	    return rSpCode;
    }
	
	@Transient
	public void setcSpCode(String cSpCode) {
	    this.cSpCode = cSpCode;
    }
	@Transient
	public String getcSpCode() {
	    return cSpCode;
    }
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACT_ID", insertable = false, updatable = false)
	public SmsActOnline getSmsActOnline() {
		return smsActOnline;
	}

	public void setSmsActOnline(SmsActOnline smsActOnline) {
		this.smsActOnline = smsActOnline;
	}	
}
