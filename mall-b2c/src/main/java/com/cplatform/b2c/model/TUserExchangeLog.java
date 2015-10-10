package com.cplatform.b2c.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER_EXCHANGE_POINT_LOG", schema = "LIFE")
public class TUserExchangeLog implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String terminalId;
	
	private Integer amount;
	
	private String userIp;
	
	private Integer excPercent;
	
	private String excDate;
	
	private Integer rCode;
	
	private String rMsg;
	
	public TUserExchangeLog(){
		
	}
	
	public TUserExchangeLog(Integer id){
		this.id = id;
	}
	
	
	

	public TUserExchangeLog(Integer id, String terminalId, Integer amount,
			String userIp, Integer excPercent, String excDate, Integer rCode,
			String rMsg) {
		super();
		this.id = id;
		this.terminalId = terminalId;
		this.amount = amount;
		this.userIp = userIp;
		this.excPercent = excPercent;
		this.excDate = excDate;
		this.rCode = rCode;
		this.rMsg = rMsg;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gen")
	@SequenceGenerator(name = "gen", sequenceName = "SEQ_EXCHANGE_POINT_LOG_ID")
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "TERMINAL_ID")
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "AMOUNT")
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name = "USER_IP")
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
	@Column(name = "EXC_PERCENT")
	public Integer getExcPercent() {
		return excPercent;
	}

	public void setExcPercent(Integer excPercent) {
		this.excPercent = excPercent;
	}

	@Column(name = "EXC_DATE")
	public String getExcDate() {
		return excDate;
	}

	public void setExcDate(String excDate) {
		this.excDate = excDate;
	}

	@Column(name = "R_CODE")
	public Integer getrCode() {
		return rCode;
	}

	public void setrCode(Integer rCode) {
		this.rCode = rCode;
	}

	@Column(name = "R_MSG")
	public String getrMsg() {
		return rMsg;
	}

	public void setrMsg(String rMsg) {
		this.rMsg = rMsg;
	}
	
	

}
