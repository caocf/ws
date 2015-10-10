CREATE TABLE "LIFE".t_market_auction (
  goods_no NUMBER(20) NOT NULL,
  floor_no VARCHAR2(5 BYTE) NOT NULL,
  operator_id NUMBER(20) NOT NULL,
  auction_price NUMBER(12,2) NOT NULL,
  auction_time TIMESTAMP NOT NULL,
  goods_name VARCHAR2(50 BYTE),
  "ID" NUMBER(9) DEFAULT 0 NOT NULL,
  CONSTRAINT pk_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_auction ADD SUPPLEMENTAL LOG GROUP ggs_240625 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_auction IS '竞拍出价记录';
COMMENT ON COLUMN "LIFE".t_market_auction.goods_no IS '竞拍商品编号';
COMMENT ON COLUMN "LIFE".t_market_auction.floor_no IS '加价楼层';
COMMENT ON COLUMN "LIFE".t_market_auction.operator_id IS '出价人编号';
COMMENT ON COLUMN "LIFE".t_market_auction.auction_price IS '出价金额';
COMMENT ON COLUMN "LIFE".t_market_auction.auction_time IS '出价时间';
COMMENT ON COLUMN "LIFE".t_market_auction.goods_name IS '商品名';
COMMENT ON COLUMN "LIFE".t_market_auction."ID" IS '编号';