CREATE TABLE "LIFE".t_bsct_treaty (
  "ID" NUMBER(9) NOT NULL,
  platform_id VARCHAR2(4 BYTE),
  store_id VARCHAR2(20 BYTE),
  service_config_id NUMBER(9),
  service_id NUMBER(3),
  contract_id VARCHAR2(32 BYTE),
  contract_name VARCHAR2(256 BYTE),
  contract_main VARCHAR2(30 BYTE),
  contract_type VARCHAR2(30 BYTE),
  eff_date VARCHAR2(8 BYTE),
  exp_date VARCHAR2(8 BYTE),
  file_name VARCHAR2(32 BYTE),
  remote_path VARCHAR2(32 BYTE),
  create_date VARCHAR2(14 BYTE),
  update_date VARCHAR2(14 BYTE),
  create_opt NUMBER(10),
  status NUMBER(2),
  CONSTRAINT pk_t_bsct_treaty PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_bsct_treaty.contract_type IS '合同、补充协议';
COMMENT ON COLUMN "LIFE".t_bsct_treaty.status IS '1：正常 0：删除   ';