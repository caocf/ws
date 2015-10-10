CREATE TABLE "LIFE".t_verify_consume_notify (
  "ID" NUMBER(10) NOT NULL,
  order_id VARCHAR2(30 BYTE) NOT NULL,
  ship_id VARCHAR2(20 BYTE),
  pos_id VARCHAR2(30 BYTE),
  pos_seq VARCHAR2(30 BYTE),
  verify_date CHAR(14 BYTE),
  terminal_id VARCHAR2(15 BYTE),
  CONSTRAINT pk_t_verify_consume_notify PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_verify_consume_notify ADD SUPPLEMENTAL LOG GROUP ggs_240474 ("ID") ALWAYS;