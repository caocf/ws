CREATE TABLE "LIFE".user_custom (
  act_code VARCHAR2(30 BYTE) NOT NULL,
  terminal_id VARCHAR2(21 BYTE) NOT NULL,
  fee_terminal_id VARCHAR2(21 BYTE),
  keyword1 VARCHAR2(20 BYTE),
  keyword2 VARCHAR2(20 BYTE),
  order_time VARCHAR2(14 BYTE),
  area_code VARCHAR2(4 BYTE),
  operator_code VARCHAR2(4 BYTE),
  fee_month VARCHAR2(14 BYTE),
  send_flag VARCHAR2(8 BYTE),
  service_id VARCHAR2(30 BYTE),
  fee_type VARCHAR2(2 BYTE),
  fee_code VARCHAR2(10 BYTE),
  xxly CHAR,
  liveflag CHAR,
  mask CHAR
);
ALTER TABLE "LIFE".user_custom ADD SUPPLEMENTAL LOG GROUP ggs_240485 (act_code, area_code, fee_code, fee_month, fee_terminal_id, fee_type, keyword1, keyword2, liveflag, mask, operator_code, order_time, send_flag, service_id, terminal_id, xxly) ALWAYS;