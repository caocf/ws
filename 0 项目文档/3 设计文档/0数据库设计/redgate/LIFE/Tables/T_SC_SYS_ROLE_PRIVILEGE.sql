CREATE TABLE "LIFE".t_sc_sys_role_privilege (
  "ID" NUMBER(9) NOT NULL,
  role_id NUMBER(9),
  menu_code VARCHAR2(20 BYTE),
  menu_btn VARCHAR2(100 BYTE)
);
ALTER TABLE "LIFE".t_sc_sys_role_privilege ADD SUPPLEMENTAL LOG GROUP ggs_240650 ("ID", menu_btn, menu_code, role_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_sc_sys_role_privilege IS '[关于权限按钮列表]:
系统有固化的权限按钮,新增,修改,删除,查看,审核..
名称对应:ADD_BTN,MOD_BTN,DEL_BTN,VIEW_BTN,AUDIT_BTN...';
COMMENT ON COLUMN "LIFE".t_sc_sys_role_privilege."ID" IS 'ID';
COMMENT ON COLUMN "LIFE".t_sc_sys_role_privilege.menu_btn IS '所拥有的按钮访问列表,格式如:ADD_BTN,MOD_BTN,DEL_BTN';