CREATE TABLE "LIFE".t_lottery_prize (
  "ID" NUMBER(8) NOT NULL,
  active_id NUMBER(8),
  hit_level NUMBER(2),
  hit_probability VARCHAR2(5 BYTE),
  hit_limit NUMBER(2),
  hit_msg VARCHAR2(100 BYTE),
  numbers NUMBER(6),
  "NAME" VARCHAR2(200 BYTE),
  description VARCHAR2(200 BYTE),
  position VARCHAR2(20 BYTE),
  used NUMBER(8),
  CONSTRAINT pk_t_lottery_prize PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_lottery_prize ADD SUPPLEMENTAL LOG GROUP ggs_240519 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_lottery_prize IS '奖品表';
COMMENT ON COLUMN "LIFE".t_lottery_prize.active_id IS '所属活动ID';
COMMENT ON COLUMN "LIFE".t_lottery_prize.hit_level IS '奖区编号（奖品等级）';
COMMENT ON COLUMN "LIFE".t_lottery_prize.hit_probability IS '中奖概率15,35,55)';
COMMENT ON COLUMN "LIFE".t_lottery_prize.hit_limit IS '每人中奖限制次数';
COMMENT ON COLUMN "LIFE".t_lottery_prize.hit_msg IS '中奖提示语';
COMMENT ON COLUMN "LIFE".t_lottery_prize.numbers IS '奖品数量';
COMMENT ON COLUMN "LIFE".t_lottery_prize."NAME" IS '奖品名称';
COMMENT ON COLUMN "LIFE".t_lottery_prize.description IS '备注';
COMMENT ON COLUMN "LIFE".t_lottery_prize.position IS '中奖位置';
COMMENT ON COLUMN "LIFE".t_lottery_prize.used IS '使用次数';