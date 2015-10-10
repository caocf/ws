CREATE TABLE "LIFE".t_gift_card_basket (
  "ID" NUMBER(9) NOT NULL,
  user_id NUMBER(9),
  serial_no VARCHAR2(20 BYTE),
  req_level NUMBER(4,2),
  item_id NUMBER(9),
  item_num NUMBER(10),
  item_level NUMBER(4,2),
  status NUMBER DEFAULT 0,
  create_time VARCHAR2(14 BYTE),
  CONSTRAINT t_gift_card_basket_pk PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_gift_card_basket."ID" IS '主键，序列：SEQ_GIFT_CARD_BASKET';
COMMENT ON COLUMN "LIFE".t_gift_card_basket.user_id IS '用户id';
COMMENT ON COLUMN "LIFE".t_gift_card_basket.serial_no IS '卡号';
COMMENT ON COLUMN "LIFE".t_gift_card_basket.req_level IS '卡星级';
COMMENT ON COLUMN "LIFE".t_gift_card_basket.item_id IS '礼品id';
COMMENT ON COLUMN "LIFE".t_gift_card_basket.item_num IS '礼品数量';
COMMENT ON COLUMN "LIFE".t_gift_card_basket.item_level IS '礼品星级';
COMMENT ON COLUMN "LIFE".t_gift_card_basket.status IS '状态';
COMMENT ON COLUMN "LIFE".t_gift_card_basket.create_time IS '加入提货篮时间';