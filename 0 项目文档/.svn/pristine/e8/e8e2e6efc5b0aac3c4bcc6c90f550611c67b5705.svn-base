CREATE TABLE "LIFE".t_user_custom_bak (
  "ID" NUMBER(12) NOT NULL,
  terminal_id VARCHAR2(21 BYTE) NOT NULL,
  product_id VARCHAR2(20 BYTE) NOT NULL,
  status NUMBER(1) NOT NULL,
  update_time VARCHAR2(14 BYTE) NOT NULL,
  custom_time VARCHAR2(14 BYTE) NOT NULL
);
ALTER TABLE "LIFE".t_user_custom_bak ADD SUPPLEMENTAL LOG GROUP ggs_240537 (custom_time, "ID", product_id, status, terminal_id, update_time) ALWAYS;