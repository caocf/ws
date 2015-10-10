CREATE TABLE "LIFE".t_sys_file_img (
  "ID" NUMBER(8) NOT NULL,
  file_type VARCHAR2(20 BYTE),
  remark VARCHAR2(100 BYTE),
  update_time VARCHAR2(14 BYTE),
  file_abs_path VARCHAR2(100 BYTE),
  file_web_path VARCHAR2(100 BYTE),
  create_user NUMBER(8),
  file_name VARCHAR2(100 BYTE),
  bs_key VARCHAR2(50 BYTE),
  bs_id NUMBER(8),
  table_name VARCHAR2(100 BYTE),
  "SORT" NUMBER(3) DEFAULT 0,
  CONSTRAINT pk_t_sys_file_img PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_file_img ADD SUPPLEMENTAL LOG GROUP ggs_240434 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_file_img IS '平台所有平台通过该表存储';
COMMENT ON COLUMN "LIFE".t_sys_file_img.file_type IS '文件类型';
COMMENT ON COLUMN "LIFE".t_sys_file_img.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_sys_file_img.file_abs_path IS '存储路径';
COMMENT ON COLUMN "LIFE".t_sys_file_img.file_web_path IS 'web路径';
COMMENT ON COLUMN "LIFE".t_sys_file_img.create_user IS '创建人';
COMMENT ON COLUMN "LIFE".t_sys_file_img.file_name IS '文件名';
COMMENT ON COLUMN "LIFE".t_sys_file_img.bs_key IS '根据不同业务定义相应类型KEY，例如主图，菜肴图，附件';
COMMENT ON COLUMN "LIFE".t_sys_file_img.bs_id IS '业务唯一编号，例如商户ID、商品ID、其它';
COMMENT ON COLUMN "LIFE".t_sys_file_img.table_name IS '表名';
COMMENT ON COLUMN "LIFE".t_sys_file_img."SORT" IS '排序';