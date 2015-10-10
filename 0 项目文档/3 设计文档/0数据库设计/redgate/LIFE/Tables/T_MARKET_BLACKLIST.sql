CREATE TABLE "LIFE".t_market_blacklist (
  "ID" NUMBER(8) NOT NULL,
  user_id VARCHAR2(20 BYTE),
  mobile VARCHAR2(13 BYTE),
  mistaketime DATE NOT NULL,
  reason VARCHAR2(100 BYTE),
  status VARCHAR2(1 BYTE),
  "TYPE" VARCHAR2(10 BYTE) NOT NULL,
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_blacklist ADD SUPPLEMENTAL LOG GROUP ggs_240626 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_blacklist IS '黑名单';
COMMENT ON COLUMN "LIFE".t_market_blacklist."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_blacklist.user_id IS '用户ID';
COMMENT ON COLUMN "LIFE".t_market_blacklist.mobile IS '手机';
COMMENT ON COLUMN "LIFE".t_market_blacklist.mistaketime IS '违规时间';
COMMENT ON COLUMN "LIFE".t_market_blacklist.reason IS '违规原因';
COMMENT ON COLUMN "LIFE".t_market_blacklist.status IS '状态';
COMMENT ON COLUMN "LIFE".t_market_blacklist."TYPE" IS '违规的类型 1为竞拍，2为秒杀';