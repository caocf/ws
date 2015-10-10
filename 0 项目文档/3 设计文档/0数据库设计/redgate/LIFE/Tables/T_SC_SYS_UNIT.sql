CREATE TABLE "LIFE".t_sc_sys_unit (
  "ID" NUMBER(9) NOT NULL,
  "NAME" VARCHAR2(100 BYTE) NOT NULL,
  area_code VARCHAR2(8 BYTE),
  unit_type NUMBER(1) NOT NULL,
  remark VARCHAR2(200 BYTE),
  flag NUMBER(1) NOT NULL,
  update_user_id NUMBER(9) NOT NULL,
  update_time VARCHAR2(14 BYTE) NOT NULL,
  prov_id NUMBER(9),
  parent_unit_id NUMBER(9)
);
ALTER TABLE "LIFE".t_sc_sys_unit ADD SUPPLEMENTAL LOG GROUP ggs_240651 (area_code, flag, "ID", "NAME", parent_unit_id, prov_id, remark, unit_type, update_time, update_user_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_sc_sys_unit."ID" IS '单位ID';
COMMENT ON COLUMN "LIFE".t_sc_sys_unit."NAME" IS '单位名称';
COMMENT ON COLUMN "LIFE".t_sc_sys_unit.area_code IS '单位归属地市，匹配T_SYS_regon表中AREA_CODE';
COMMENT ON COLUMN "LIFE".t_sc_sys_unit.unit_type IS '单位分类，1-电商基地单位
2-省单位
3-地市单位';
COMMENT ON COLUMN "LIFE".t_sc_sys_unit.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_sc_sys_unit.flag IS '单位标识，0表示正常，9表示注销';
COMMENT ON COLUMN "LIFE".t_sc_sys_unit.update_user_id IS '单位变更用户ID，匹配T_SYS_USER表中ID';
COMMENT ON COLUMN "LIFE".t_sc_sys_unit.update_time IS '单位更新时间';
COMMENT ON COLUMN "LIFE".t_sc_sys_unit.prov_id IS '单位所属省ID';