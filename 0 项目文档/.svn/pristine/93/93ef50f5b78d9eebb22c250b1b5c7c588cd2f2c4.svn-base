CREATE TABLE "LIFE".t_shop_invoice (
  "ID" NUMBER(9) NOT NULL,
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  invoice_id NUMBER(9),
  invoice_name VARCHAR2(40 BYTE),
  CONSTRAINT pk_shop_invoice PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_invoice ADD SUPPLEMENTAL LOG GROUP ggs_240399 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop_invoice IS '商户发票表';
COMMENT ON COLUMN "LIFE".t_shop_invoice.shop_class IS '商户类型';
COMMENT ON COLUMN "LIFE".t_shop_invoice.shop_id IS '商户id';
COMMENT ON COLUMN "LIFE".t_shop_invoice.invoice_id IS '发票id';
COMMENT ON COLUMN "LIFE".t_shop_invoice.invoice_name IS '发票名';