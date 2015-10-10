CREATE TABLE "LIFE".t_market_seckill_content (
  "ID" VARCHAR2(2 BYTE) NOT NULL,
  title VARCHAR2(20 BYTE),
  last_modify_time VARCHAR2(20 BYTE),
  "CONTENT" CLOB,
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_seckill_content ADD SUPPLEMENTAL LOG GROUP ggs_240693 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_seckill_content IS '秒杀文案配置表';
COMMENT ON COLUMN "LIFE".t_market_seckill_content."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_seckill_content.title IS '秒杀文案名称';
COMMENT ON COLUMN "LIFE".t_market_seckill_content.last_modify_time IS '最后修改时间';
COMMENT ON COLUMN "LIFE".t_market_seckill_content."CONTENT" IS '秒杀文案内容';