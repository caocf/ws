CREATE TABLE "LIFE".t_mms_report_02 (
  "ID" NUMBER(9) NOT NULL,
  msg_id VARCHAR2(50 BYTE),
  status VARCHAR2(10 BYTE),
  status_detail VARCHAR2(50 BYTE),
  get_time VARCHAR2(14 BYTE),
  sender VARCHAR2(21 BYTE),
  CONSTRAINT pk_t_mms_report_02 PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_mms_report_02 ADD SUPPLEMENTAL LOG GROUP ggs_240565 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_mms_report_02 IS '彩信下行状态报告表，按月分表存放';
COMMENT ON COLUMN "LIFE".t_mms_report_02."ID" IS '序列，递增无实意，主键';
COMMENT ON COLUMN "LIFE".t_mms_report_02.msg_id IS '彩信信息标识，以此关联彩信下行，非空';
COMMENT ON COLUMN "LIFE".t_mms_report_02.status IS '状态报告状态码，非空';
COMMENT ON COLUMN "LIFE".t_mms_report_02.status_detail IS '状态报告状态细节代码';
COMMENT ON COLUMN "LIFE".t_mms_report_02.get_time IS '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
COMMENT ON COLUMN "LIFE".t_mms_report_02.sender IS '手机号码';