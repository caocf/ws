CREATE TABLE "LIFE".t_item_price_type (
  "ID" NUMBER(9) NOT NULL,
  province VARCHAR2(20 BYTE) NOT NULL,
  area_code VARCHAR2(20 BYTE) NOT NULL,
  price_type VARCHAR2(20 BYTE),
  price_type_code VARCHAR2(20 BYTE) NOT NULL,
  remark VARCHAR2(500 BYTE),
  CONSTRAINT pk_t_item_price_type PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_price_type ADD SUPPLEMENTAL LOG GROUP ggs_240361 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_item_price_type IS '存放商品各类类型编码及名称，发布商品时可以动态的生成页面，录入不同类型的价格。';
COMMENT ON COLUMN "LIFE".t_item_price_type."ID" IS '唯一ID';
COMMENT ON COLUMN "LIFE".t_item_price_type.province IS '商品发布销售省份';
COMMENT ON COLUMN "LIFE".t_item_price_type.area_code IS '地市';
COMMENT ON COLUMN "LIFE".t_item_price_type.price_type IS '价格类型名称,市场价,商城价,红钻价';
COMMENT ON COLUMN "LIFE".t_item_price_type.price_type_code IS '价格类型编码,根据订购关系表中的会员类型确定';
COMMENT ON COLUMN "LIFE".t_item_price_type.remark IS '备注';