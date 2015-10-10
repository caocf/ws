CREATE TABLE "LIFE".t_verify_reverse (
  "ID" NUMBER(10) NOT NULL,
  order_id VARCHAR2(30 BYTE) NOT NULL,
  ship_id VARCHAR2(20 BYTE),
  pos_id VARCHAR2(30 BYTE),
  reverse_channel VARCHAR2(10 BYTE),
  reverse_date CHAR(14 BYTE),
  reverse_user VARCHAR2(20 BYTE),
  reverse_status VARCHAR2(10 BYTE),
  CONSTRAINT pk_t_verify_reverse PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_verify_reverse ADD SUPPLEMENTAL LOG GROUP ggs_240480 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_verify_reverse.reverse_channel IS 'WEB，WAP，客户端，终端，短信';