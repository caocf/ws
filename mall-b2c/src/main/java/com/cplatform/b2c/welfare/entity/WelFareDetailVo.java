package com.cplatform.b2c.welfare.entity;

import java.util.List;


/**
 * Title. 根据商品id 获取扩展数据<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-11-6 上午08:53:41
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class WelFareDetailVo {
	private List<String> colorLst;
	private List<String> sizeLst;
	private WelFareModel model;
    /**
     * 获取 colorLst
     * @return colorLst
     */
    public List<String> getColorLst() {
    	return colorLst;
    }
	
    /**
     * 设置 colorLst
     * @param colorLst 
     */
    public void setColorLst(List<String> colorLst) {
    	this.colorLst = colorLst;
    }
	
    /**
     * 获取 sizeLst
     * @return sizeLst
     */
    public List<String> getSizeLst() {
    	return sizeLst;
    }
	
    /**
     * 设置 sizeLst
     * @param sizeLst 
     */
    public void setSizeLst(List<String> sizeLst) {
    	this.sizeLst = sizeLst;
    }

	
    /**
     * 获取 model
     * @return model
     */
    public WelFareModel getModel() {
    	return model;
    }

	
    /**
     * 设置 model
     * @param model 
     */
    public void setModel(WelFareModel model) {
    	this.model = model;
    }
	

}
