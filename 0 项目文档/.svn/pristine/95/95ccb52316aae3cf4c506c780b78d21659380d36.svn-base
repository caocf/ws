CREATE TABLE "LIFE".t_user_custom_log (
  "ID" NUMBER(12) NOT NULL,
  operate_type NUMBER(1) NOT NULL,
  terminal_id VARCHAR2(21 BYTE) NOT NULL,
  product_id VARCHAR2(20 BYTE) NOT NULL,
  custom_time VARCHAR2(14 BYTE),
  cancel_time VARCHAR2(14 BYTE),
  operate_time VARCHAR2(14 BYTE) NOT NULL,
  reason VARCHAR2(200 BYTE),
  CONSTRAINT pk_user_custom_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_user_custom_log ADD SUPPLEMENTAL LOG GROUP ggs_240529 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_user_custom_log."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_user_custom_log.operate_type IS '操作类型，1定制，2退定，3暂停，4恢复';
COMMENT ON COLUMN "LIFE".t_user_custom_log.terminal_id IS '号码';
COMMENT ON COLUMN "LIFE".t_user_custom_log.product_id IS '产品ID';
COMMENT ON COLUMN "LIFE".t_user_custom_log.custom_time IS '定制时间';
COMMENT ON COLUMN "LIFE".t_user_custom_log.cancel_time IS '退定时间';
COMMENT ON COLUMN "LIFE".t_user_custom_log.operate_time IS '操作时间';
COMMENT ON COLUMN "LIFE".t_user_custom_log.reason IS '操作原因（操作的补充说明）';