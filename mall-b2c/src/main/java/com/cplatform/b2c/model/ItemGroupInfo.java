package com.cplatform.b2c.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Title. 套餐商品<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-7 下午6:41:25
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
@JsonAutoDetect
public class ItemGroupInfo {

	@JsonProperty("items")
	public List<ItemInfo> items;

	@JsonProperty("info")
	public ItemInfo groupItem;

	public List<ItemInfo> getItems() {
		return items;
	}

	public void setItems(List<ItemInfo> items) {
		this.items = items;
	}

	public ItemInfo getGroupItem() {
		return groupItem;
	}

	public void setGroupItem(ItemInfo groupItem) {
		this.groupItem = groupItem;
	}

}
