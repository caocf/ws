CREATE TABLE "LIFE".t_sys_file_link (
  "ID" NUMBER(8) NOT NULL,
  bs_key VARCHAR2(50 BYTE),
  bs_id NUMBER(8),
  file_id NUMBER(8),
  table_name VARCHAR2(100 BYTE),
  CONSTRAINT pk_t_sys_file_link PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_file_link ADD SUPPLEMENTAL LOG GROUP ggs_240437 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_file_link IS '业务和文件关联表';
COMMENT ON COLUMN "LIFE".t_sys_file_link.bs_key IS '根据不同业务定义相应类型KEY，例如主图，菜肴图，附件';
COMMENT ON COLUMN "LIFE".t_sys_file_link.bs_id IS '业务唯一编号，例如商户ID、商品ID、其它';
COMMENT ON COLUMN "LIFE".t_sys_file_link.file_id IS '关联原始文件存储表t_sys_file表中ID';
COMMENT ON COLUMN "LIFE".t_sys_file_link.table_name IS '表名';