CREATE TABLE "LIFE".t_market_fullpresent_goods (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(550 BYTE) NOT NULL,
  goods_id NUMBER(10) NOT NULL,
  shop_name VARCHAR2(70 BYTE) NOT NULL,
  shop_id NUMBER(8) NOT NULL,
  store_price NUMBER(10,2) NOT NULL,
  store_stock NUMBER(10),
  create_time VARCHAR2(30 BYTE),
  fullpresent_id NUMBER(8),
  isdel VARCHAR2(1 BYTE) DEFAULT 0,
  goods_area VARCHAR2(3500 BYTE)
);
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods."NAME" IS '商品名称';
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods.goods_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods.shop_name IS '商户名称';
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods.shop_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods.store_price IS '商城价格';
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods.store_stock IS '库存';
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods.fullpresent_id IS '满额赠活动ID';
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods.isdel IS '是否已经删除 0为未删除 1为已经删除';
COMMENT ON COLUMN "LIFE".t_market_fullpresent_goods.goods_area IS '显示地区';