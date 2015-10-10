CREATE TABLE "LIFE".t_sys_store_role (
  "ID" NUMBER(9) NOT NULL,
  remark VARCHAR2(200 BYTE),
  update_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  role_name VARCHAR2(50 CHAR) NOT NULL,
  shop_class VARCHAR2(20 CHAR),
  shop_id NUMBER(9)
);
ALTER TABLE "LIFE".t_sys_store_role ADD SUPPLEMENTAL LOG GROUP ggs_240454 ("ID", remark, role_name, shop_class, shop_id, update_time, update_user_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_store_role IS '商户自服务平台角色表';
COMMENT ON COLUMN "LIFE".t_sys_store_role."ID" IS 'ID，使用序列SEQ_COMM_ID';
COMMENT ON COLUMN "LIFE".t_sys_store_role.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_sys_store_role.update_user_id IS '创建人，匹配T_SYS_USER表中的ID字段';
COMMENT ON COLUMN "LIFE".t_sys_store_role.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_sys_store_role.role_name IS '角色名称';
COMMENT ON COLUMN "LIFE".t_sys_store_role.shop_class IS '角色所属商户 1门店 2商户 3渠道';
COMMENT ON COLUMN "LIFE".t_sys_store_role.shop_id IS '角色所属商户ID';