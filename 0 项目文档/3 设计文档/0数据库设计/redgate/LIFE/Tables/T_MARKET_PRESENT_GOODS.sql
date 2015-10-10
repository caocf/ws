CREATE TABLE "LIFE".t_market_present_goods (
  "ID" NUMBER(10) NOT NULL,
  goods_id NUMBER(10) NOT NULL,
  create_time VARCHAR2(30 BYTE),
  isdel VARCHAR2(1 BYTE) DEFAULT 0,
  fullpresent_id NUMBER(10),
  order_price NUMBER(10,2),
  scale_type VARCHAR2(1 BYTE),
  "SCALE" NUMBER(10)
);
COMMENT ON COLUMN "LIFE".t_market_present_goods.goods_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_market_present_goods.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_market_present_goods.isdel IS '是否删除 0为未删除 1为已经删除';
COMMENT ON COLUMN "LIFE".t_market_present_goods.fullpresent_id IS '满额赠活动ID';
COMMENT ON COLUMN "LIFE".t_market_present_goods.order_price IS '订单总金额';
COMMENT ON COLUMN "LIFE".t_market_present_goods.scale_type IS '占比类型  0为现金 1为话费';
COMMENT ON COLUMN "LIFE".t_market_present_goods."SCALE" IS '所占比例';