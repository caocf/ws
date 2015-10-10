CREATE TABLE "LIFE".t_verify_delay (
  "ID" NUMBER(9) NOT NULL,
  trans_time CHAR(14 BYTE),
  code_tye NUMBER(1),
  code_content VARCHAR2(100 BYTE),
  expire_date CHAR(14 BYTE),
  platform_id VARCHAR2(15 BYTE),
  store_id VARCHAR2(20 BYTE),
  CONSTRAINT pk_t_verify_delay PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_verify_delay ADD SUPPLEMENTAL LOG GROUP ggs_240476 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_verify_delay.code_tye IS '1：订单编号
2：子订单编号
3：辅助码串';
COMMENT ON COLUMN "LIFE".t_verify_delay.platform_id IS 'FOUNDER：方正
STORE：商户
CPLATFORM：宽连';