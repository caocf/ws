CREATE TABLE "LIFE".t_item_sale_shop_link (
  "ID" NUMBER(8) NOT NULL,
  sale_id NUMBER(8),
  store_id NUMBER(8),
  shop_id NUMBER(8),
  CONSTRAINT pk_t_item_sale_shop_link PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_sale_shop_link ADD SUPPLEMENTAL LOG GROUP ggs_240365 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_item_sale_shop_link.shop_id IS '如果是全商户门店都可以使用，则只存商户ID，该业务门店ID可以允许为空';