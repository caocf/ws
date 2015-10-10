CREATE TABLE "LIFE".t_sms_report_05 (
  "ID" NUMBER(9) NOT NULL,
  msg_id VARCHAR2(50 BYTE),
  status VARCHAR2(10 BYTE),
  get_time VARCHAR2(14 BYTE),
  sender VARCHAR2(21 BYTE),
  CONSTRAINT pk_t_sms_report_05 PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sms_report_05 ADD SUPPLEMENTAL LOG GROUP ggs_240614 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sms_report_05 IS '短信下行状态报告表，按月分表存放';
COMMENT ON COLUMN "LIFE".t_sms_report_05."ID" IS '序列，递增无实意，主键';
COMMENT ON COLUMN "LIFE".t_sms_report_05.msg_id IS '短信信息标识，以此关联短信下行，非空';
COMMENT ON COLUMN "LIFE".t_sms_report_05.status IS '状态报告状态，非空';
COMMENT ON COLUMN "LIFE".t_sms_report_05.get_time IS '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
COMMENT ON COLUMN "LIFE".t_sms_report_05.sender IS '手机号码';