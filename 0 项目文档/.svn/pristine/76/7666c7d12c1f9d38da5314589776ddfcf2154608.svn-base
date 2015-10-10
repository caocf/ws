CREATE TABLE "LIFE".t_lottery_target (
  "ID" NUMBER(8) NOT NULL,
  active_id NUMBER(8),
  phone VARCHAR2(13 BYTE),
  num_had NUMBER(2),
  num_could NUMBER(2),
  CONSTRAINT pk_t_lottery_target PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_lottery_target ADD SUPPLEMENTAL LOG GROUP ggs_240520 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_lottery_target IS '抽奖目标库';
COMMENT ON COLUMN "LIFE".t_lottery_target.active_id IS '所属活动ID';
COMMENT ON COLUMN "LIFE".t_lottery_target.phone IS '手机号码';
COMMENT ON COLUMN "LIFE".t_lottery_target.num_had IS '本活动中已中奖次数';
COMMENT ON COLUMN "LIFE".t_lottery_target.num_could IS '本活动可中奖次数';