CREATE TABLE "LIFE".t_market_card_payment (
  "ID" NUMBER(12) NOT NULL,
  card_detail_id NUMBER(12) NOT NULL,
  status CHAR NOT NULL,
  item_id NUMBER(12) NOT NULL,
  pay_amount NUMBER(18,2) NOT NULL,
  pay_time VARCHAR2(32 BYTE) NOT NULL,
  refund_time VARCHAR2(32 BYTE),
  CONSTRAINT pk_payment PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_card_payment IS '礼金券支付明细';
COMMENT ON COLUMN "LIFE".t_market_card_payment."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_card_payment.card_detail_id IS '礼金券明细编号';
COMMENT ON COLUMN "LIFE".t_market_card_payment.status IS '支付状态：1-已支付，2-已退款';
COMMENT ON COLUMN "LIFE".t_market_card_payment.item_id IS '支付商品编号';
COMMENT ON COLUMN "LIFE".t_market_card_payment.pay_amount IS '支付金额';
COMMENT ON COLUMN "LIFE".t_market_card_payment.pay_time IS '支付时间';
COMMENT ON COLUMN "LIFE".t_market_card_payment.refund_time IS '退款时间';