CREATE TABLE "LIFE".t_market_remind (
  "ID" NUMBER(8) NOT NULL,
  mobile VARCHAR2(11 BYTE),
  goods_no NUMBER(8),
  status VARCHAR2(2 BYTE) DEFAULT 0,
  begin_time TIMESTAMP,
  "TYPE" VARCHAR2(2 BYTE) DEFAULT 1,
  goods_name VARCHAR2(70 BYTE),
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_remind ADD SUPPLEMENTAL LOG GROUP ggs_240630 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_remind IS '定时提醒';
COMMENT ON COLUMN "LIFE".t_market_remind."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_remind.mobile IS '手机号';
COMMENT ON COLUMN "LIFE".t_market_remind.goods_no IS '提醒商品编号';
COMMENT ON COLUMN "LIFE".t_market_remind.status IS '状态 0： 未发送， 1：已发送';
COMMENT ON COLUMN "LIFE".t_market_remind.begin_time IS '商品活动开始时间';
COMMENT ON COLUMN "LIFE".t_market_remind."TYPE" IS '1:竞拍提醒,2:秒杀提醒 ';
COMMENT ON COLUMN "LIFE".t_market_remind.goods_name IS '商品名称';