CREATE TABLE "LIFE".t_sys_menu (
  menu_code VARCHAR2(20 BYTE) NOT NULL,
  menu_name VARCHAR2(50 BYTE) NOT NULL,
  menu_pcode VARCHAR2(20 BYTE) NOT NULL,
  menu_url VARCHAR2(100 BYTE),
  leaf_yn NUMBER(3) NOT NULL,
  model_code VARCHAR2(200 BYTE),
  url_btns VARCHAR2(500 BYTE),
  sys_type NUMBER(1) NOT NULL,
  CONSTRAINT pk_sys_menu PRIMARY KEY (menu_code)
);
ALTER TABLE "LIFE".t_sys_menu ADD SUPPLEMENTAL LOG GROUP ggs_240445 (menu_code) ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_menu IS '菜单表';
COMMENT ON COLUMN "LIFE".t_sys_menu.menu_code IS '菜单code，唯一值';
COMMENT ON COLUMN "LIFE".t_sys_menu.menu_name IS '菜单名称';
COMMENT ON COLUMN "LIFE".t_sys_menu.menu_pcode IS '当前菜单父菜单code';
COMMENT ON COLUMN "LIFE".t_sys_menu.menu_url IS '菜单指向的URL链接，没有链接可以使用#';
COMMENT ON COLUMN "LIFE".t_sys_menu.leaf_yn IS '是否为末级标志，0-是;1否';
COMMENT ON COLUMN "LIFE".t_sys_menu.model_code IS '标识模块,最末级菜单需要指明该字段,且在本系统内具有唯一性';
COMMENT ON COLUMN "LIFE".t_sys_menu.url_btns IS '每个页面所拥有的按钮(add_btn,mod_btn,del_btn)';
COMMENT ON COLUMN "LIFE".t_sys_menu.sys_type IS '每个系统有自己的菜单：0后台管理系统 1商户自服务业务门店 2商户自服务结算商户 3商户自服务渠道商';