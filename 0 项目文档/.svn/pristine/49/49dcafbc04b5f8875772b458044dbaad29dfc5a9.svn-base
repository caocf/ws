CREATE TABLE "LIFE".t_shop_type_link (
  "ID" NUMBER(8) NOT NULL,
  shop_id NUMBER(8),
  type_id NUMBER(8),
  CONSTRAINT pk_t_shop_type_link PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_type_link ADD SUPPLEMENTAL LOG GROUP ggs_240405 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop_type_link IS '存储业务门店分类关系表
1业务门店对应N个商户分类';
COMMENT ON COLUMN "LIFE".t_shop_type_link.shop_id IS '可以为空';