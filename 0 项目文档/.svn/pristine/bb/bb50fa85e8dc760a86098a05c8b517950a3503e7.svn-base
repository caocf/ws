CREATE TABLE "LIFE".t_market_seckill (
  goods_no NUMBER(9) NOT NULL,
  goods_name VARCHAR2(250 BYTE) NOT NULL,
  goods_price NUMBER(13,2) NOT NULL,
  seckill_price NUMBER(13,2) NOT NULL,
  goods_pic1 VARCHAR2(250 BYTE),
  goods_pic2 VARCHAR2(250 BYTE),
  goods_pic3 VARCHAR2(250 BYTE),
  goods_pic4 VARCHAR2(250 BYTE),
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP NOT NULL,
  update_time TIMESTAMP,
  status VARCHAR2(1 BYTE),
  imagepath VARCHAR2(100 BYTE),
  user_id NUMBER(20) NOT NULL,
  order_id VARCHAR2(13 BYTE),
  create_time TIMESTAMP,
  "ID" NUMBER(9) NOT NULL,
  stock_num NUMBER(19) DEFAULT 1,
  CONSTRAINT seckill_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_seckill ADD SUPPLEMENTAL LOG GROUP ggs_240631 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_seckill IS '秒杀订单表';
COMMENT ON COLUMN "LIFE".t_market_seckill.goods_no IS '商品编号';
COMMENT ON COLUMN "LIFE".t_market_seckill.goods_name IS '商品名称';
COMMENT ON COLUMN "LIFE".t_market_seckill.goods_price IS '市场价';
COMMENT ON COLUMN "LIFE".t_market_seckill.seckill_price IS '秒杀价';
COMMENT ON COLUMN "LIFE".t_market_seckill.goods_pic1 IS '图片1';
COMMENT ON COLUMN "LIFE".t_market_seckill.goods_pic2 IS '图片2';
COMMENT ON COLUMN "LIFE".t_market_seckill.goods_pic3 IS '图片3';
COMMENT ON COLUMN "LIFE".t_market_seckill.goods_pic4 IS '图片4';
COMMENT ON COLUMN "LIFE".t_market_seckill.start_time IS '秒杀开始时间';
COMMENT ON COLUMN "LIFE".t_market_seckill.end_time IS '秒杀结束时间';
COMMENT ON COLUMN "LIFE".t_market_seckill.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_market_seckill.status IS '0-未支付，未生成订单  1-支付成功   2-已过期  3-已生成订单';
COMMENT ON COLUMN "LIFE".t_market_seckill.imagepath IS '封面图片';
COMMENT ON COLUMN "LIFE".t_market_seckill.user_id IS '支付者id';
COMMENT ON COLUMN "LIFE".t_market_seckill.order_id IS '支付者手机';
COMMENT ON COLUMN "LIFE".t_market_seckill.create_time IS '订单号';
COMMENT ON COLUMN "LIFE".t_market_seckill."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_seckill.stock_num IS '库存';