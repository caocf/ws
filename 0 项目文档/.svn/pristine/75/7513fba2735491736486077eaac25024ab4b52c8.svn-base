CREATE TABLE "LIFE".t_sms_batch_log_07 (
  "ID" NUMBER(9) NOT NULL,
  task_id NUMBER(9),
  terminal_id VARCHAR2(21 BYTE),
  submit_time VARCHAR2(14 BYTE),
  status VARCHAR2(10 BYTE),
  message_id VARCHAR2(50 BYTE),
  CONSTRAINT pk_t_sms_batch_log_07 PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sms_batch_log_07 ADD SUPPLEMENTAL LOG GROUP ggs_240581 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sms_batch_log_07 IS '彩信群发下行日志表，按月分表存储';
COMMENT ON COLUMN "LIFE".t_sms_batch_log_07."ID" IS '序列，递增无实意，主键';
COMMENT ON COLUMN "LIFE".t_sms_batch_log_07.task_id IS '群发任务ID';
COMMENT ON COLUMN "LIFE".t_sms_batch_log_07.terminal_id IS '计费手机号码，第三方付费必填';
COMMENT ON COLUMN "LIFE".t_sms_batch_log_07.message_id IS '通讯消息ID';