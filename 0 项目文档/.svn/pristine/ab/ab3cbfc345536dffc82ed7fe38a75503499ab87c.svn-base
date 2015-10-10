CREATE TABLE "LIFE".t_verify_founder_store (
  founder_store_id VARCHAR2(30 BYTE) NOT NULL,
  founder_shop_id VARCHAR2(300 BYTE),
  local_store_id VARCHAR2(30 BYTE) NOT NULL,
  CONSTRAINT pk_t_verify_founder_store PRIMARY KEY (founder_store_id)
);
ALTER TABLE "LIFE".t_verify_founder_store ADD SUPPLEMENTAL LOG GROUP ggs_240509 (founder_store_id) ALWAYS;