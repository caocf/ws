CREATE TABLE "LIFE".t_store_logistics_fee (
  "ID" NUMBER(8) NOT NULL,
  store_id NUMBER(8),
  qd_id NUMBER(8),
  fee_num FLOAT,
  CONSTRAINT pk_t_store_logistics_fee PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_store_logistics_fee ADD SUPPLEMENTAL LOG GROUP ggs_240424 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_store_logistics_fee IS '物流运费';