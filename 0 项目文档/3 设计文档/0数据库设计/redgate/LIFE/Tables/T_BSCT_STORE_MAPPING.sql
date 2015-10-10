CREATE TABLE "LIFE".t_bsct_store_mapping (
  "ID" NUMBER(12) NOT NULL,
  platform_id VARCHAR2(4 BYTE),
  store_id VARCHAR2(20 BYTE) NOT NULL,
  p_store_id VARCHAR2(20 BYTE),
  CONSTRAINT pk_t_bsct_store_mapping PRIMARY KEY ("ID")
);