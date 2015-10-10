CREATE TABLE "LIFE".t_verify_consume (
  "ID" NUMBER(10) NOT NULL,
  order_id VARCHAR2(30 BYTE) NOT NULL,
  ship_id VARCHAR2(20 BYTE),
  pos_id VARCHAR2(30 BYTE),
  verify_channel VARCHAR2(10 BYTE),
  verify_date CHAR(14 BYTE),
  use_times NUMBER(3),
  verify_trans_id VARCHAR2(30 BYTE),
  is_reverse NUMBER(1),
  verify_user VARCHAR2(20 BYTE),
  verify_status VARCHAR2(10 BYTE),
  CONSTRAINT pk_t_verify_consume PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_verify_consume ADD SUPPLEMENTAL LOG GROUP ggs_240473 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_verify_consume.verify_channel IS 'WEB，WAP，客户端，终端，短信';
COMMENT ON COLUMN "LIFE".t_verify_consume.is_reverse IS '0：未冲正
1：已冲正';