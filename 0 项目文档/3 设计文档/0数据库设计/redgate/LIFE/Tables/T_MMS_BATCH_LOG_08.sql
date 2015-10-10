CREATE TABLE "LIFE".t_mms_batch_log_08 (
  "ID" NUMBER(9) NOT NULL,
  task_id NUMBER(9),
  terminal_id VARCHAR2(21 BYTE),
  submit_time VARCHAR2(14 BYTE),
  status VARCHAR2(10 BYTE),
  msg_id VARCHAR2(50 BYTE),
  CONSTRAINT pk_t_mms_batch_log_08 PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_mms_batch_log_08 ADD SUPPLEMENTAL LOG GROUP ggs_240548 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_mms_batch_log_08 IS '彩信群发下行日志表，按月分表存储';
COMMENT ON COLUMN "LIFE".t_mms_batch_log_08."ID" IS '序列，递增无实意，主键';
COMMENT ON COLUMN "LIFE".t_mms_batch_log_08.task_id IS '计费手机号码，第三方付费必填';
COMMENT ON COLUMN "LIFE".t_mms_batch_log_08.terminal_id IS '计费手机号码，第三方付费必填';
COMMENT ON COLUMN "LIFE".t_mms_batch_log_08.status IS 'submit_resp回包中的状态。';
COMMENT ON COLUMN "LIFE".t_mms_batch_log_08.msg_id IS '通讯消息ID';