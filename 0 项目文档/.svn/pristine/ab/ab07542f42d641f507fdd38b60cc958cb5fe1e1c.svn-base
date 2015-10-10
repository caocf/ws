CREATE TABLE "LIFE".t_lottery_log (
  "ID" NUMBER(8) NOT NULL,
  active_id NUMBER(8),
  target_id VARCHAR2(21 BYTE),
  "CONTENT" VARCHAR2(200 BYTE),
  hit_time VARCHAR2(14 BYTE),
  prize_id NUMBER(8),
  CONSTRAINT pk_t_lottery_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_lottery_log ADD SUPPLEMENTAL LOG GROUP ggs_240518 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_lottery_log IS '抽奖日志表';
COMMENT ON COLUMN "LIFE".t_lottery_log.active_id IS '活动ID';
COMMENT ON COLUMN "LIFE".t_lottery_log.target_id IS '中奖手机号';
COMMENT ON COLUMN "LIFE".t_lottery_log."CONTENT" IS '中奖内容';
COMMENT ON COLUMN "LIFE".t_lottery_log.hit_time IS '中奖时间';
COMMENT ON COLUMN "LIFE".t_lottery_log.prize_id IS '奖品ID';