package com.cplatform.mall.dc.model;

import com.cplatform.mall.dc.entity.DcMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 层级表示的菜单
 * <p/>
 * Copyright: Copyright (c) 13-6-1 下午1:35
 * <p/>
 * Company: 苏州宽连信息技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class StackSysMenu extends DcMenu implements Serializable {

	private static final long serialVersionUID = -2459212174990557261L;

	public List<StackSysMenu> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<StackSysMenu> childMenus) {
		this.childMenus = childMenus;
	}

	private List<StackSysMenu> childMenus = new ArrayList<StackSysMenu>();

}
