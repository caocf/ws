CREATE TABLE "LIFE".t_sys_file (
  "ID" NUMBER(8) NOT NULL,
  file_type VARCHAR2(20 BYTE),
  remark VARCHAR2(100 BYTE),
  update_time VARCHAR2(14 BYTE),
  file_abs_path VARCHAR2(100 BYTE),
  file_web_path VARCHAR2(100 BYTE),
  create_user NUMBER(8),
  bs_key VARCHAR2(50 BYTE),
  bs_id NUMBER(8),
  table_name VARCHAR2(100 BYTE),
  CONSTRAINT pk_t_sys_file PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_file ADD SUPPLEMENTAL LOG GROUP ggs_240433 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_file IS '平台所有平台通过该表存储';
COMMENT ON COLUMN "LIFE".t_sys_file.file_type IS '文件类型';
COMMENT ON COLUMN "LIFE".t_sys_file.remark IS '文件描述';
COMMENT ON COLUMN "LIFE".t_sys_file.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_sys_file.file_abs_path IS '存储路径';
COMMENT ON COLUMN "LIFE".t_sys_file.file_web_path IS '文件访问路径';
COMMENT ON COLUMN "LIFE".t_sys_file.create_user IS '创建人';
COMMENT ON COLUMN "LIFE".t_sys_file.bs_key IS '根据不同业务定义相应类型KEY，例如主图，菜肴图，附件';
COMMENT ON COLUMN "LIFE".t_sys_file.bs_id IS '业务唯一编号，例如商户ID、商品ID、其它';
COMMENT ON COLUMN "LIFE".t_sys_file.table_name IS '表名';