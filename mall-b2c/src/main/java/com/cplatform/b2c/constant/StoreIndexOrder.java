package com.cplatform.b2c.constant;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

public enum StoreIndexOrder {
	
	SHOP_PRICE_ASC("价格从低到高", 1), SHOP_PRICE_DESC("价格从高到低", 2), START_TIME_DESC("最新上架在前", 3), START_TIME_ASC("最新上架在后", 4);

	// 成员变量
	private String name;
	
	private int index;
	
	public  static final Map<String, String> map = new LinkedHashMap<String, String>(); 
	static {  
        for (StoreIndexOrder s : EnumSet.allOf(StoreIndexOrder.class)) {  
        	map.put(String.valueOf(s.getIndex()), s.getName());  
        }
    }

	// 构造方法
	private StoreIndexOrder(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (StoreIndexOrder c : StoreIndexOrder.values()) {
			if (c.getIndex() == index ) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public static StoreIndexOrder valueOf(int index) { 
		for(StoreIndexOrder s : StoreIndexOrder.values()) { 
			if(s.getIndex() == index) { 
				return s; 
			}
		}
		return null; 
	}
	
	public String toString(){
		return this.getName();
	}
}