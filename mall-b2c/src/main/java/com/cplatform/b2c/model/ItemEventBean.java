package com.cplatform.b2c.model;

import java.util.HashMap;
import java.util.List;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-19 上午11:59:06
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
public class ItemEventBean {

	private String item_name;
	private String item_no;
	private String item_type;
	private String pay_method;
	private String pay_hint;
	private String[] imgs;
	private String shop_name;
	private String shop_id;
	private String item_intro;
	private HashMap<String, String> item_param;
	private String after_service;
	private String item_mode;
    private HashMap<String,HashMap<String,List<String>>> addresses;
    
    private HashMap<String,String[]> item_properties;
    //{
    //	颜色:[红色,蓝色],
    //	尺寸:[短,中,长]
    //}
    private HashMap<String, List<HashMap<String,String>>> item_properties_data;
    //红色=>[
    //	{短:商品A},
    //	{中:商品B},
    //	{长:商品C}]
    //蓝色=>[
    //  {短:0}],
    //  {中:商品D},
    //  {长:商品E}]
    public HashMap<String, HashMap<String, List<String>>> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(HashMap<String, HashMap<String, List<String>>> addresses) {
        this.addresses = addresses;
    }

    public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_no() {
		return item_no;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getPay_hint() {
		return pay_hint;
	}

	public void setPay_hint(String pay_hint) {
		this.pay_hint = pay_hint;
	}

	public String[] getImgs() {
		return imgs;
	}

	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getItem_intro() {
		return item_intro;
	}

	public void setItem_intro(String item_intro) {
		this.item_intro = item_intro;
	}

	public HashMap<String, String> getItem_param() {
		return item_param;
	}

	public void setItem_param(HashMap<String, String> item_param) {
		this.item_param = item_param;
	}

	public String getAfter_service() {
		return after_service;
	}

	public void setAfter_service(String after_service) {
		this.after_service = after_service;
	}

	public String getItem_mode() {
		return item_mode;
	}

	public void setItem_mode(String item_mode) {
		this.item_mode = item_mode;
	}

	public void setItem_properties_data(HashMap<String, List<HashMap<String,String>>> item_properties_data) {
		this.item_properties_data = item_properties_data;
	}

	public HashMap<String, List<HashMap<String,String>>> getItem_properties_data() {
		return item_properties_data;
	}

	public void setItem_properties(HashMap<String,String[]> item_properties) {
		this.item_properties = item_properties;
	}

	public HashMap<String,String[]> getItem_properties() {
		return item_properties;
	}
}
