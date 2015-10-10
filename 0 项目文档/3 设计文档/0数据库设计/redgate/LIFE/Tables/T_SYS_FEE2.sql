CREATE TABLE "LIFE".t_sys_fee2 (
  "ID" NUMBER,
  store_id VARCHAR2(200 BYTE),
  store_name VARCHAR2(150 BYTE),
  "CATEGORY" VARCHAR2(50 BYTE),
  fee_type VARCHAR2(1 BYTE),
  decr_fee VARCHAR2(100 BYTE)
);
ALTER TABLE "LIFE".t_sys_fee2 ADD SUPPLEMENTAL LOG GROUP ggs_240513 ("CATEGORY", decr_fee, fee_type, "ID", store_id, store_name) ALWAYS;