CREATE TABLE "LIFE".sms_mt_wait (
  sequence_id NUMBER(9) NOT NULL,
  act_code VARCHAR2(20 BYTE) NOT NULL,
  sp_code VARCHAR2(60 BYTE) NOT NULL,
  fee_terminal_id VARCHAR2(64 BYTE) NOT NULL,
  dest_terminal_id VARCHAR2(64 BYTE),
  register_delivery VARCHAR2(100 BYTE),
  msg_content VARCHAR2(2000 BYTE),
  request_time VARCHAR2(14 BYTE),
  service_id VARCHAR2(14 BYTE) NOT NULL,
  fee_type NUMBER(3),
  fee_code NUMBER(3),
  msg_level NUMBER(9),
  valid_time NUMBER(9) DEFAULT 0 NOT NULL,
  operator_code VARCHAR2(20 BYTE),
  area_code VARCHAR2(20 BYTE),
  pid VARCHAR2(20 BYTE),
  userid VARCHAR2(20 BYTE),
  linkid VARCHAR2(20 BYTE),
  msg_format VARCHAR2(2 BYTE),
  PRIMARY KEY (sequence_id)
);
ALTER TABLE "LIFE".sms_mt_wait ADD SUPPLEMENTAL LOG GROUP ggs_240330 (sequence_id) ALWAYS;