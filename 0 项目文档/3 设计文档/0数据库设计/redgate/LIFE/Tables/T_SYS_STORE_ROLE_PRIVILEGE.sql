CREATE TABLE "LIFE".t_sys_store_role_privilege (
  "ID" NUMBER(9) NOT NULL,
  role_id NUMBER(9),
  menu_code VARCHAR2(20 BYTE),
  menu_btn VARCHAR2(100 BYTE)
);
ALTER TABLE "LIFE".t_sys_store_role_privilege ADD SUPPLEMENTAL LOG GROUP ggs_240455 ("ID", menu_btn, menu_code, role_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_store_role_privilege IS '商户自服务平台角色权限关系表';
COMMENT ON COLUMN "LIFE".t_sys_store_role_privilege."ID" IS 'ID';
COMMENT ON COLUMN "LIFE".t_sys_store_role_privilege.menu_btn IS '所拥有的按钮访问列表,格式如:ADD_BTN,MOD_BTN,DEL_BTN';