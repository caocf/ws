CREATE TABLE "LIFE".t_lottery_active (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(100 BYTE),
  start_time VARCHAR2(14 BYTE),
  stop_time VARCHAR2(14 BYTE),
  active_type NUMBER(1),
  active_desc VARCHAR2(200 BYTE),
  unhit NUMBER(1),
  unhit_msg VARCHAR2(50 BYTE),
  create_time VARCHAR2(14 BYTE),
  create_member_id NUMBER(8),
  status NUMBER(1),
  hit_limit NUMBER(2) DEFAULT -1,
  CONSTRAINT pk_t_lottery_active PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_lottery_active ADD SUPPLEMENTAL LOG GROUP ggs_240516 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_lottery_active IS '抽奖活动表';
COMMENT ON COLUMN "LIFE".t_lottery_active."NAME" IS '活动名称';
COMMENT ON COLUMN "LIFE".t_lottery_active.start_time IS '活动开始时间';
COMMENT ON COLUMN "LIFE".t_lottery_active.stop_time IS '活动结束时间';
COMMENT ON COLUMN "LIFE".t_lottery_active.active_type IS '活动类型';
COMMENT ON COLUMN "LIFE".t_lottery_active.active_desc IS '活动介绍';
COMMENT ON COLUMN "LIFE".t_lottery_active.unhit IS '未中奖设置(预留)';
COMMENT ON COLUMN "LIFE".t_lottery_active.unhit_msg IS '未中奖提示语';
COMMENT ON COLUMN "LIFE".t_lottery_active.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_lottery_active.create_member_id IS '创建人ID';
COMMENT ON COLUMN "LIFE".t_lottery_active.status IS '活动状态:0草稿，1审核中，2审核通过，3驳回';
COMMENT ON COLUMN "LIFE".t_lottery_active.hit_limit IS '中奖次数限制，-1表示不限制次数';