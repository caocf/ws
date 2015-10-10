CREATE TABLE "LIFE".t_market_order (
  "ID" NUMBER(9) NOT NULL,
  product_id VARCHAR2(32 BYTE) NOT NULL,
  auction_quantity NUMBER(*,0) NOT NULL,
  auction_price NUMBER(13,2) NOT NULL,
  auction_time TIMESTAMP,
  status NUMBER(*,0),
  operator_id VARCHAR2(32 BYTE),
  product_name VARCHAR2(254 BYTE),
  product_price NUMBER(13,2),
  goods_no VARCHAR2(20 BYTE),
  orderid VARCHAR2(64 BYTE),
  create_time TIMESTAMP,
  update_time TIMESTAMP,
  del_flag NUMBER(*,0) DEFAULT 0 NOT NULL,
  CONSTRAINT pk_order_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_order ADD SUPPLEMENTAL LOG GROUP ggs_240628 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_order IS '竞拍订单表';
COMMENT ON COLUMN "LIFE".t_market_order."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_order.product_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_market_order.auction_quantity IS '竞拍商品数量';
COMMENT ON COLUMN "LIFE".t_market_order.auction_price IS '竞拍价';
COMMENT ON COLUMN "LIFE".t_market_order.auction_time IS '竞拍时间';
COMMENT ON COLUMN "LIFE".t_market_order.status IS '0-未支付，未生成订单  1-支付成功   2-已过期  3-已生成订单';
COMMENT ON COLUMN "LIFE".t_market_order.operator_id IS '拍得者卡号';
COMMENT ON COLUMN "LIFE".t_market_order.product_name IS '商品名称';
COMMENT ON COLUMN "LIFE".t_market_order.product_price IS '商品单价';
COMMENT ON COLUMN "LIFE".t_market_order.goods_no IS '竞拍商品号';
COMMENT ON COLUMN "LIFE".t_market_order.orderid IS '订单编号，用于根据订单的状态更新竞拍商品的状态';
COMMENT ON COLUMN "LIFE".t_market_order.create_time IS '记录创建时间';
COMMENT ON COLUMN "LIFE".t_market_order.update_time IS '记录更新时间';
COMMENT ON COLUMN "LIFE".t_market_order.del_flag IS '删除标志：0-未删除 1-已删除';