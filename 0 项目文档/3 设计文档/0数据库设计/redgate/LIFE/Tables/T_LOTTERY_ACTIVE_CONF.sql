CREATE TABLE "LIFE".t_lottery_active_conf (
  "ID" NUMBER(8) NOT NULL,
  active_id NUMBER(8),
  "KEY" VARCHAR2(400 BYTE),
  "VALUE" VARCHAR2(400 BYTE),
  CONSTRAINT pk_t_lottery_active_conf PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_lottery_active_conf ADD SUPPLEMENTAL LOG GROUP ggs_240517 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_lottery_active_conf IS '活动配置表';
COMMENT ON COLUMN "LIFE".t_lottery_active_conf.active_id IS '所属活动ID';
COMMENT ON COLUMN "LIFE".t_lottery_active_conf."KEY" IS '配置项：key-value方式存放。';
COMMENT ON COLUMN "LIFE".t_lottery_active_conf."VALUE" IS '配置项：key-value方式存放。';