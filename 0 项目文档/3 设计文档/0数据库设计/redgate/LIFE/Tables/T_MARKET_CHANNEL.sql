CREATE TABLE "LIFE".t_market_channel (
  "ID" NUMBER(20) NOT NULL,
  goods_no NUMBER(20) NOT NULL,
  goods_name NVARCHAR2(50) NOT NULL,
  position NVARCHAR2(2) NOT NULL,
  CONSTRAINT pk_t_market_channel PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_channel ADD SUPPLEMENTAL LOG GROUP ggs_240627 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_channel IS '秒杀频道表';
COMMENT ON COLUMN "LIFE".t_market_channel."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_channel.goods_no IS '秒杀商品编号';
COMMENT ON COLUMN "LIFE".t_market_channel.goods_name IS '秒杀商品名称';
COMMENT ON COLUMN "LIFE".t_market_channel.position IS '秒杀频道位置';