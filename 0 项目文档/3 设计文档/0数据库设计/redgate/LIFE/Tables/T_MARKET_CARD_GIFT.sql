CREATE TABLE "LIFE".t_market_card_gift (
  "ID" NUMBER(8) NOT NULL,
  card_no VARCHAR2(60 BYTE) NOT NULL,
  card_name VARCHAR2(60 BYTE) NOT NULL,
  card_desc VARCHAR2(600 BYTE) NOT NULL,
  card_num NUMBER DEFAULT 0 NOT NULL,
  card_money NUMBER(18,2) DEFAULT 0 NOT NULL,
  begin_time VARCHAR2(32 BYTE) NOT NULL,
  end_time VARCHAR2(32 BYTE) NOT NULL,
  use_scope VARCHAR2(2 BYTE) NOT NULL,
  limit_sum CHAR DEFAULT '0',
  total_amount NUMBER(18,2),
  create_time VARCHAR2(32 BYTE),
  update_time VARCHAR2(32 BYTE),
  status CHAR DEFAULT '0',
  card_link NVARCHAR2(200),
  send_type CHAR DEFAULT '1',
  user_level VARCHAR2(200 BYTE),
  PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_card_gift IS '礼金券表';
COMMENT ON COLUMN "LIFE".t_market_card_gift."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_market_card_gift.card_no IS '批次号';
COMMENT ON COLUMN "LIFE".t_market_card_gift.card_name IS '名称';
COMMENT ON COLUMN "LIFE".t_market_card_gift.card_desc IS '描述';
COMMENT ON COLUMN "LIFE".t_market_card_gift.card_num IS '数量';
COMMENT ON COLUMN "LIFE".t_market_card_gift.card_money IS '金额';
COMMENT ON COLUMN "LIFE".t_market_card_gift.begin_time IS '开始时间';
COMMENT ON COLUMN "LIFE".t_market_card_gift.end_time IS '结束时间';
COMMENT ON COLUMN "LIFE".t_market_card_gift.use_scope IS '使用范围1:特定商品 2:全场券 3:店铺券';
COMMENT ON COLUMN "LIFE".t_market_card_gift.limit_sum IS '订单总金额限制0:无限制1:限制';
COMMENT ON COLUMN "LIFE".t_market_card_gift.total_amount IS '订单总金额';
COMMENT ON COLUMN "LIFE".t_market_card_gift.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_market_card_gift.update_time IS '修改时间';
COMMENT ON COLUMN "LIFE".t_market_card_gift.status IS '状态0:未发布1:发布2:已过期';
COMMENT ON COLUMN "LIFE".t_market_card_gift.card_link IS '礼金券活动页面';
COMMENT ON COLUMN "LIFE".t_market_card_gift.send_type IS '发放类型:1:系统发放2:用户领取';
COMMENT ON COLUMN "LIFE".t_market_card_gift.user_level IS '用户等级 ALL:全部会员，HONGZUAN:红钻会员 以'',''分割 ';