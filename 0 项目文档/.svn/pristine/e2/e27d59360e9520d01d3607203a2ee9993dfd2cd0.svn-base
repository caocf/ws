CREATE TABLE "LIFE".t_monitor_log (
  "ID" NUMBER(9) NOT NULL,
  log_time VARCHAR2(14 BYTE) DEFAULT TO_CHAR(SYSDATE, 'yyyyMMddHHmmss'),
  log_threshold NUMBER(2) DEFAULT 0,
  log_content VARCHAR2(300 BYTE),
  modual_name VARCHAR2(100 BYTE),
  remark VARCHAR2(300 BYTE),
  CONSTRAINT pk_t_monitor_log PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_monitor_log IS '业务监控日志表';
COMMENT ON COLUMN "LIFE".t_monitor_log."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_monitor_log.log_time IS '日志记录时间';
COMMENT ON COLUMN "LIFE".t_monitor_log.log_threshold IS '日志级别（0：DEBUG；1：INFO；2：WARN；3：ERROR。）';
COMMENT ON COLUMN "LIFE".t_monitor_log.log_content IS '日志内容';
COMMENT ON COLUMN "LIFE".t_monitor_log.modual_name IS '模块名称';
COMMENT ON COLUMN "LIFE".t_monitor_log.remark IS '备注';