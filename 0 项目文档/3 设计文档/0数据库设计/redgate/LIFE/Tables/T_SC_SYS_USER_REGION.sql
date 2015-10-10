CREATE TABLE "LIFE".t_sc_sys_user_region (
  "ID" NUMBER(9) NOT NULL,
  user_id NUMBER(9),
  region_code VARCHAR2(100 BYTE)
);
ALTER TABLE "LIFE".t_sc_sys_user_region ADD SUPPLEMENTAL LOG GROUP ggs_240653 ("ID", region_code, user_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_sc_sys_user_region IS '系统用户区域表
该表主要是针对数据权限进行管理。
该用户可以操作的区域进行管理';