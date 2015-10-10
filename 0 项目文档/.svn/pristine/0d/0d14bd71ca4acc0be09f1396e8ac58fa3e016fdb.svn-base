CREATE TABLE "LIFE".t_market_p_goods (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(550 BYTE) NOT NULL,
  goods_id NUMBER(10) NOT NULL,
  shop_name VARCHAR2(70 BYTE) NOT NULL,
  shop_id NUMBER(8) NOT NULL,
  store_price NUMBER(10,2) NOT NULL,
  promotion_price NUMBER(10,2) NOT NULL,
  promotion_stock NUMBER(10) DEFAULT 0 NOT NULL,
  store_stock NUMBER(10),
  goods_area VARCHAR2(3500 BYTE),
  create_time VARCHAR2(30 BYTE),
  promotion_id NUMBER(8),
  isdel VARCHAR2(1 BYTE) DEFAULT 0,
  buy_limit VARCHAR2(5 BYTE) DEFAULT 0,
  num_limit NUMBER(10) DEFAULT 0,
  PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_market_p_goods."NAME" IS '商品名称';
COMMENT ON COLUMN "LIFE".t_market_p_goods.goods_id IS '关联商品ID';
COMMENT ON COLUMN "LIFE".t_market_p_goods.shop_name IS '商户名称';
COMMENT ON COLUMN "LIFE".t_market_p_goods.shop_id IS '商户id';
COMMENT ON COLUMN "LIFE".t_market_p_goods.store_price IS '商城价';
COMMENT ON COLUMN "LIFE".t_market_p_goods.promotion_price IS '限时特价';
COMMENT ON COLUMN "LIFE".t_market_p_goods.promotion_stock IS '特价库存';
COMMENT ON COLUMN "LIFE".t_market_p_goods.store_stock IS '总库存';
COMMENT ON COLUMN "LIFE".t_market_p_goods.goods_area IS '销售区域';
COMMENT ON COLUMN "LIFE".t_market_p_goods.promotion_id IS '关联 限时特价活动ID';
COMMENT ON COLUMN "LIFE".t_market_p_goods.isdel IS '是否已经删除，1为已经删除';
COMMENT ON COLUMN "LIFE".t_market_p_goods.buy_limit IS '0为无限制，1为商盟会员  2为红砖会员';
COMMENT ON COLUMN "LIFE".t_market_p_goods.num_limit IS '购买数量(0为不限制购买数量)';