package com.cplatform.mall.back.model;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-21 下午1:49
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class MenuPrivilege implements Serializable {

    private static final long serialVersionUID = -5162370424637506068L;

    // 菜单代码
    private String menuCode;

    // 该模块下的按钮权限
    private Set<String> buttons;

    public MenuPrivilege() {
        // default
    }

    public MenuPrivilege(String menuCode) {
        this.menuCode = menuCode;
    }

    public MenuPrivilege(String menuCode, String buttons) {
        this.menuCode = menuCode;

        if (StringUtils.hasText(buttons)) {
            this.buttons = new HashSet<String>();
            String[] bts = buttons.split("#");

            for (String bt : bts) {
                if (StringUtils.hasText(bt)) {
                    this.buttons.add(bt);
                }
            }
        }
    }

    public MenuPrivilege(String menuCode, Set<String> buttons) {
        this.menuCode = menuCode;
        this.buttons = buttons;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Set<String> getButtons() {
        return buttons;
    }

    public void setButtons(Set<String> buttons) {
        this.buttons = buttons;
    }

}
