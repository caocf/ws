CREATE TABLE "LIFE".t_wx_bm_menu (
  menu_code VARCHAR2(20 BYTE) NOT NULL,
  menu_name VARCHAR2(50 BYTE) NOT NULL,
  menu_pcode VARCHAR2(20 BYTE) NOT NULL,
  menu_url VARCHAR2(100 BYTE),
  leaf_yn NUMBER(3) NOT NULL,
  model_code VARCHAR2(200 BYTE),
  url_btns VARCHAR2(100 BYTE),
  sys_type NUMBER(1) DEFAULT 0 NOT NULL,
  CONSTRAINT pk_wx_bm_menu PRIMARY KEY (menu_code)
);
COMMENT ON TABLE "LIFE".t_wx_bm_menu IS '无锡营业厅菜单表';
COMMENT ON COLUMN "LIFE".t_wx_bm_menu.menu_code IS '菜单code，唯一值';
COMMENT ON COLUMN "LIFE".t_wx_bm_menu.menu_name IS '菜单名称';
COMMENT ON COLUMN "LIFE".t_wx_bm_menu.menu_pcode IS '当前菜单父菜单code';
COMMENT ON COLUMN "LIFE".t_wx_bm_menu.menu_url IS '菜单指向的URL链接，没有链接可以使用#';
COMMENT ON COLUMN "LIFE".t_wx_bm_menu.leaf_yn IS '是否为末级标志，0-是;1否';
COMMENT ON COLUMN "LIFE".t_wx_bm_menu.model_code IS '标识模块,最末级菜单需要指明该字段,且在本系统内具有唯一性';
COMMENT ON COLUMN "LIFE".t_wx_bm_menu.url_btns IS '每个页面所拥有的按钮(add_btn,mod_btn,del_btn)';
COMMENT ON COLUMN "LIFE".t_wx_bm_menu.sys_type IS '每个系统有自己的菜单：0:无锡代客下单菜单';