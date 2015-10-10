CREATE TABLE "LIFE".t_market_seckill_log (
  "ID" NUMBER(8) NOT NULL,
  goods_no NUMBER(8) NOT NULL,
  user_id NUMBER(20) NOT NULL,
  create_time TIMESTAMP NOT NULL,
  status VARCHAR2(1 BYTE) DEFAULT 0 NOT NULL,
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_seckill_log ADD SUPPLEMENTAL LOG GROUP ggs_240632 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_seckill_log IS '秒杀记录表';
COMMENT ON COLUMN "LIFE".t_market_seckill_log."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_seckill_log.goods_no IS '商品编号';
COMMENT ON COLUMN "LIFE".t_market_seckill_log.user_id IS '用户编号';
COMMENT ON COLUMN "LIFE".t_market_seckill_log.create_time IS '秒杀时间';
COMMENT ON COLUMN "LIFE".t_market_seckill_log.status IS '0：秒杀失败；1：秒杀成功';