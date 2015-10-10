CREATE TABLE "LIFE".t_sc_sys_role (
  "ID" NUMBER(9) NOT NULL,
  remark VARCHAR2(200 BYTE),
  update_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  role_name VARCHAR2(50 CHAR) NOT NULL,
  unit_id VARCHAR2(20 CHAR)
);
ALTER TABLE "LIFE".t_sc_sys_role ADD SUPPLEMENTAL LOG GROUP ggs_240649 ("ID", remark, role_name, unit_id, update_time, update_user_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_sc_sys_role IS '角色主表';
COMMENT ON COLUMN "LIFE".t_sc_sys_role."ID" IS 'ID，使用序列SEQ_COMM_ID';
COMMENT ON COLUMN "LIFE".t_sc_sys_role.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_sc_sys_role.update_user_id IS '创建人，匹配T_SYS_USER表中的ID字段';
COMMENT ON COLUMN "LIFE".t_sc_sys_role.update_time IS '更新时间';