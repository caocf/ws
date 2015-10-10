CREATE TABLE "LIFE".xw_fee_id2kl_fee_id (
  merc_id VARCHAR2(30 BYTE),
  prod_typ VARCHAR2(20 BYTE),
  fee_rate1 VARCHAR2(200 BYTE),
  t_sys_fee_id NUMBER
);
ALTER TABLE "LIFE".xw_fee_id2kl_fee_id ADD SUPPLEMENTAL LOG GROUP ggs_240504 (fee_rate1, merc_id, prod_typ, t_sys_fee_id) ALWAYS;