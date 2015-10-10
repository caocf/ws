CREATE TABLE "LIFE".t_tp_report_log (
  tp_msg_id VARCHAR2(50 BYTE) NOT NULL,
  msg_id VARCHAR2(30 BYTE),
  status VARCHAR2(30 BYTE),
  update_time VARCHAR2(14 BYTE) NOT NULL,
  "TYPE" NUMBER(1) DEFAULT 1 NOT NULL,
  need_report VARCHAR2(10 BYTE) DEFAULT 'false' NOT NULL,
  CONSTRAINT pk_t_tp_report_log PRIMARY KEY (tp_msg_id)
);
ALTER TABLE "LIFE".t_tp_report_log ADD SUPPLEMENTAL LOG GROUP ggs_240539 (tp_msg_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_tp_report_log.tp_msg_id IS 'TP的MsgId，主键';
COMMENT ON COLUMN "LIFE".t_tp_report_log.msg_id IS '网关的MsgId';
COMMENT ON COLUMN "LIFE".t_tp_report_log.status IS '发送状态';
COMMENT ON COLUMN "LIFE".t_tp_report_log.update_time IS '更新时间，14位时间';
COMMENT ON COLUMN "LIFE".t_tp_report_log."TYPE" IS '类型，1为短信，2为彩信';
COMMENT ON COLUMN "LIFE".t_tp_report_log.need_report IS '是否需要状态报告，true=需要，其他不需要';