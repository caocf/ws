CREATE TABLE "LIFE".t_module_data_templet (
  "ID" NUMBER(8) NOT NULL,
  title VARCHAR2(50 BYTE),
  conf_path VARCHAR2(100 BYTE),
  "TYPE" NUMBER(2),
  create_user NUMBER(9),
  create_time VARCHAR2(14 BYTE),
  update_user NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  remark VARCHAR2(300 BYTE),
  CONSTRAINT pk_t_module_data_templet PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_module_data_templet IS '数据模板表';
COMMENT ON COLUMN "LIFE".t_module_data_templet.title IS '模板名称';
COMMENT ON COLUMN "LIFE".t_module_data_templet.conf_path IS '模板配置路径';
COMMENT ON COLUMN "LIFE".t_module_data_templet."TYPE" IS '模板类型 1:通用模板';
COMMENT ON COLUMN "LIFE".t_module_data_templet.create_user IS '创建人';
COMMENT ON COLUMN "LIFE".t_module_data_templet.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_module_data_templet.update_user IS '修改人';
COMMENT ON COLUMN "LIFE".t_module_data_templet.update_time IS '修改时间';
COMMENT ON COLUMN "LIFE".t_module_data_templet.remark IS '描述';