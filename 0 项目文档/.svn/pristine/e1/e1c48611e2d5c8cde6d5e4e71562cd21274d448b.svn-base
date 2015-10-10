CREATE TABLE "LIFE".t_shop_delivery_mode (
  "ID" NUMBER(9) NOT NULL,
  logistics_id NUMBER(9),
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  fee NUMBER(9,2),
  CONSTRAINT pk_t_shop_delivery_mode PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_delivery_mode ADD SUPPLEMENTAL LOG GROUP ggs_240395 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop_delivery_mode IS '物流配送方式';
COMMENT ON COLUMN "LIFE".t_shop_delivery_mode."ID" IS 'ID';
COMMENT ON COLUMN "LIFE".t_shop_delivery_mode.logistics_id IS '物流信息表ID';
COMMENT ON COLUMN "LIFE".t_shop_delivery_mode.shop_class IS '1--业务门店
2--商户
3--渠道商';
COMMENT ON COLUMN "LIFE".t_shop_delivery_mode.fee IS '运费';