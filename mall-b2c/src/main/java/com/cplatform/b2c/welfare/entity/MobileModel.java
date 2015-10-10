package com.cplatform.b2c.welfare.entity;


/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-12 下午05:09:49
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class MobileModel {
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private Long terminalId;
	/**
	 * 工号
	 */
	private String jobNumber;
	/**
	 * 所属公司
	 */
	private String company;
	/**
	 * 所属部门
	 */
	private String department;
	/**
	 * 公司地址
	 */
	private String address;
	/**
	 * 备注
	 */
	private String remark;
	
    /**
     * 获取 id
     * @return id
     */
    public Long getId() {
    	return id;
    }
	
    /**
     * 设置 id
     * @param id 
     */
    public void setId(Long id) {
    	this.id = id;
    }
	
    /**
     * 获取 name
     * @return name
     */
    public String getName() {
    	return name;
    }
	
    /**
     * 设置 name
     * @param name 
     */
    public void setName(String name) {
    	this.name = name;
    }
	
    /**
     * 获取 terminalId
     * @return terminalId
     */
    public Long getTerminalId() {
    	return terminalId;
    }
	
    /**
     * 设置 terminalId
     * @param terminalId 
     */
    public void setTerminalId(Long terminalId) {
    	this.terminalId = terminalId;
    }
	
    /**
     * 获取 jobNumber
     * @return jobNumber
     */
    public String getJobNumber() {
    	return jobNumber;
    }
	
    /**
     * 设置 jobNumber
     * @param jobNumber 
     */
    public void setJobNumber(String jobNumber) {
    	this.jobNumber = jobNumber;
    }
	
    /**
     * 获取 company
     * @return company
     */
    public String getCompany() {
    	return company;
    }
	
    /**
     * 设置 company
     * @param company 
     */
    public void setCompany(String company) {
    	this.company = company;
    }
	
    /**
     * 获取 department
     * @return department
     */
    public String getDepartment() {
    	return department;
    }
	
    /**
     * 设置 department
     * @param department 
     */
    public void setDepartment(String department) {
    	this.department = department;
    }
	
    /**
     * 获取 address
     * @return address
     */
    public String getAddress() {
    	return address;
    }
	
    /**
     * 设置 address
     * @param address 
     */
    public void setAddress(String address) {
    	this.address = address;
    }
	
    /**
     * 获取 remark
     * @return remark
     */
    public String getRemark() {
    	return remark;
    }
	
    /**
     * 设置 remark
     * @param remark 
     */
    public void setRemark(String remark) {
    	this.remark = remark;
    }
	
	
	
}
