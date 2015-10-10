CREATE TABLE "LIFE".t_sms_molong_wait (
  "ID" NUMBER(9) NOT NULL,
  sp_code VARCHAR2(30 BYTE),
  src_terminalid VARCHAR2(30 BYTE),
  msg_content VARCHAR2(200 BYTE),
  "KEY" VARCHAR2(200 BYTE),
  sum_count NUMBER(9),
  now_order NUMBER(9),
  linkid VARCHAR2(30 BYTE),
  recive_time VARCHAR2(30 BYTE),
  area_code VARCHAR2(30 BYTE),
  CONSTRAINT pk_sms_molong_wait PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sms_molong_wait ADD SUPPLEMENTAL LOG GROUP ggs_240587 ("ID") ALWAYS;