CREATE TABLE "LIFE".new_fee_rate2 (
  "ID" VARCHAR2(50 BYTE),
  store_name VARCHAR2(200 BYTE),
  "CATEGORY" VARCHAR2(200 BYTE),
  fee VARCHAR2(10 BYTE),
  describe VARCHAR2(80 BYTE),
  "TYPE" NUMBER,
  lad NUMBER,
  starttime VARCHAR2(8 BYTE),
  endtime VARCHAR2(8 BYTE),
  fee_id NUMBER
);
ALTER TABLE "LIFE".new_fee_rate2 ADD SUPPLEMENTAL LOG GROUP ggs_240512 ("CATEGORY", describe, endtime, fee, fee_id, "ID", lad, starttime, store_name, "TYPE") ALWAYS;