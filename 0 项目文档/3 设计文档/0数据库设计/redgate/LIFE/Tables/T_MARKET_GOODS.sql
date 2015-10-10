CREATE TABLE "LIFE".t_market_goods (
  goods_no NUMBER(20) NOT NULL,
  goods_name VARCHAR2(50 BYTE) NOT NULL,
  goods_photo_path VARCHAR2(250 BYTE) NOT NULL,
  marche_price NUMBER(12,2) NOT NULL,
  time_range VARCHAR2(15 BYTE) NOT NULL,
  product_id VARCHAR2(20 BYTE),
  date_range DATE,
  price_range NUMBER(12,2),
  margin_flag VARCHAR2(2 BYTE) DEFAULT '1',
  vip_margin_flag VARCHAR2(2 BYTE) DEFAULT '1',
  status VARCHAR2(2 BYTE) DEFAULT 1,
  auction_type VARCHAR2(2 BYTE) DEFAULT 1,
  min_auction_time VARCHAR2(3 BYTE) DEFAULT 0,
  delay_time VARCHAR2(3 BYTE) DEFAULT 0,
  end_time VARCHAR2(8 BYTE) DEFAULT 0,
  account_for_auction CLOB,
  goods_description CLOB,
  shop_goods_id NUMBER(8),
  goods_small_pic_a VARCHAR2(250 BYTE),
  goods_small_pic_b VARCHAR2(250 BYTE),
  goods_small_pic_c VARCHAR2(250 BYTE),
  start_price NUMBER(12,2) DEFAULT 0,
  check_status VARCHAR2(2 BYTE) DEFAULT 0,
  check_content VARCHAR2(600 BYTE),
  check_man VARCHAR2(20 BYTE),
  check_time VARCHAR2(20 BYTE),
  CONSTRAINT pk_goods PRIMARY KEY (goods_no)
);
ALTER TABLE "LIFE".t_market_goods ADD SUPPLEMENTAL LOG GROUP ggs_240688 (goods_no) ALWAYS;
COMMENT ON TABLE "LIFE".t_market_goods IS '竞拍商品表';
COMMENT ON COLUMN "LIFE".t_market_goods.goods_no IS '商品编号';
COMMENT ON COLUMN "LIFE".t_market_goods.goods_name IS '商品名';
COMMENT ON COLUMN "LIFE".t_market_goods.goods_photo_path IS '主图片路径';
COMMENT ON COLUMN "LIFE".t_market_goods.marche_price IS '市场价';
COMMENT ON COLUMN "LIFE".t_market_goods.time_range IS '竞拍时间';
COMMENT ON COLUMN "LIFE".t_market_goods.product_id IS '保证金编号';
COMMENT ON COLUMN "LIFE".t_market_goods.date_range IS '竞拍日期';
COMMENT ON COLUMN "LIFE".t_market_goods.price_range IS '加价幅度';
COMMENT ON COLUMN "LIFE".t_market_goods.margin_flag IS '普通会员是否需要购买保证金，0：需要，1：不需要';
COMMENT ON COLUMN "LIFE".t_market_goods.vip_margin_flag IS 'VIP会员是否需要购买保证金，0：需要，1：不需要';
COMMENT ON COLUMN "LIFE".t_market_goods.status IS '有效状态，0是上架，1是下架';
COMMENT ON COLUMN "LIFE".t_market_goods.auction_type IS '1：限时抢拍，2：延时竞拍';
COMMENT ON COLUMN "LIFE".t_market_goods.min_auction_time IS '延时竞拍活动最短时长（分钟）';
COMMENT ON COLUMN "LIFE".t_market_goods.delay_time IS '延时时长（分钟）';
COMMENT ON COLUMN "LIFE".t_market_goods.end_time IS '活动结束时间';
COMMENT ON COLUMN "LIFE".t_market_goods.account_for_auction IS '描述';
COMMENT ON COLUMN "LIFE".t_market_goods.goods_description IS '商品描述';
COMMENT ON COLUMN "LIFE".t_market_goods.shop_goods_id IS '商品链接';
COMMENT ON COLUMN "LIFE".t_market_goods.goods_small_pic_a IS '轮换图片路径';
COMMENT ON COLUMN "LIFE".t_market_goods.goods_small_pic_b IS '轮换图片路径';
COMMENT ON COLUMN "LIFE".t_market_goods.goods_small_pic_c IS '轮换图片路径';
COMMENT ON COLUMN "LIFE".t_market_goods.start_price IS '起拍价';
COMMENT ON COLUMN "LIFE".t_market_goods.check_status IS '0-未审核，1-待审核，2-审核通过， 3-审核不通过';
COMMENT ON COLUMN "LIFE".t_market_goods.check_content IS '审核意见';
COMMENT ON COLUMN "LIFE".t_market_goods.check_man IS '审核人';
COMMENT ON COLUMN "LIFE".t_market_goods.check_time IS '审核时间';