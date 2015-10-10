CREATE TABLE "LIFE".t_item_price (
  "ID" NUMBER(9) NOT NULL,
  province VARCHAR2(20 BYTE),
  area_code VARCHAR2(20 BYTE),
  store_id NUMBER(9) NOT NULL,
  item_id NUMBER(9),
  price_type_code VARCHAR2(20 BYTE) NOT NULL,
  price NUMBER(11,2) NOT NULL,
  remark VARCHAR2(500 BYTE),
  sale_id NUMBER(9) NOT NULL,
  CONSTRAINT pk_t_item_price PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_price ADD SUPPLEMENTAL LOG GROUP ggs_240359 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_item_price IS '存放商品的各类价格信息，以纵表形式展示。';
COMMENT ON COLUMN "LIFE".t_item_price."ID" IS '唯一ID';
COMMENT ON COLUMN "LIFE".t_item_price.province IS '省份';
COMMENT ON COLUMN "LIFE".t_item_price.area_code IS '地市';
COMMENT ON COLUMN "LIFE".t_item_price.store_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_item_price.item_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_item_price.price_type_code IS '来源:T_ITEM_PRICE_TYPE';
COMMENT ON COLUMN "LIFE".t_item_price.price IS '商品价格';
COMMENT ON COLUMN "LIFE".t_item_price.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_item_price.sale_id IS '商品id';