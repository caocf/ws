CREATE TABLE "LIFE".t_market_card_gift_details (
  "ID" NUMBER(12) NOT NULL,
  card_id NUMBER(12) NOT NULL,
  status CHAR DEFAULT '2' NOT NULL,
  mobile VARCHAR2(20 BYTE),
  order_id VARCHAR2(20 BYTE),
  use_money NUMBER(18,2),
  use_time VARCHAR2(32 BYTE),
  create_time VARCHAR2(32 BYTE),
  update_time VARCHAR2(32 BYTE),
  card_link VARCHAR2(200 BYTE) DEFAULT ' ',
  channel NUMBER(12),
  PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_card_gift_details IS '礼金券详细表';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.card_id IS '礼金批次id';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.status IS '状态1:已领取2:未领取3:已使用4:已过期';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.mobile IS '电话';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.order_id IS '订单id';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.use_money IS '使用金额';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.use_time IS '使用时间';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.update_time IS '修改时间';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.card_link IS '活动地址';
COMMENT ON COLUMN "LIFE".t_market_card_gift_details.channel IS '礼金券所属频道。';