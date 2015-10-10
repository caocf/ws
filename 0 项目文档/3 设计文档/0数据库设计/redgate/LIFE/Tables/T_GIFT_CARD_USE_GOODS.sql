CREATE TABLE "LIFE".t_gift_card_use_goods (
  "ID" NUMBER(9) NOT NULL,
  us_id NUMBER(9),
  item_id NUMBER(9),
  item_num NUMBER(10),
  item_level NUMBER(4,2),
  created_time VARCHAR2(14 BYTE),
  item_name VARCHAR2(200 BYTE),
  CONSTRAINT t_gift_card_use_goods_pk PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_gift_card_use_goods.us_id IS '流水号管理use_info表';
COMMENT ON COLUMN "LIFE".t_gift_card_use_goods.item_id IS '商品id';
COMMENT ON COLUMN "LIFE".t_gift_card_use_goods.item_num IS '商品数量';
COMMENT ON COLUMN "LIFE".t_gift_card_use_goods.item_level IS '商品星级';
COMMENT ON COLUMN "LIFE".t_gift_card_use_goods.created_time IS '兑换时间';
COMMENT ON COLUMN "LIFE".t_gift_card_use_goods.item_name IS '商品名称';