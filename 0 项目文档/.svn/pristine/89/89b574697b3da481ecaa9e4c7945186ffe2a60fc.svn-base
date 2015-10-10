CREATE TABLE "LIFE".t_item_logistics_fee (
  "ID" NUMBER(8) NOT NULL,
  sale_id NUMBER(8),
  qd_id NUMBER(8),
  fee_num NUMBER(8,2),
  CONSTRAINT pk_t_item_logistics_fee PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_logistics_fee ADD SUPPLEMENTAL LOG GROUP ggs_240356 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_item_logistics_fee IS '物流运费';