CREATE TABLE "LIFE".t_sms_mt_log_09 (
  "ID" NUMBER(9) NOT NULL,
  msg_content VARCHAR2(1000 BYTE),
  fee_terminal_id VARCHAR2(21 BYTE),
  dest_terminal_id VARCHAR2(21 BYTE),
  sp_code VARCHAR2(20 BYTE),
  service_id VARCHAR2(20 BYTE),
  fee_type NUMBER(3),
  fee NUMBER(5),
  is_report NUMBER(3),
  msg_format NUMBER(2),
  request_time VARCHAR2(14 BYTE),
  submit_time VARCHAR2(14 BYTE),
  act_id NUMBER(9),
  unit_id VARCHAR2(20 BYTE),
  area_code VARCHAR2(8 BYTE),
  operator_code VARCHAR2(50 BYTE),
  msg_id VARCHAR2(50 BYTE),
  status VARCHAR2(10 BYTE),
  sms_id NUMBER(9),
  CONSTRAINT pk_t_sms_mt_log_09 PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sms_mt_log_09 ADD SUPPLEMENTAL LOG GROUP ggs_240606 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sms_mt_log_09 IS '短信业务下行日志表';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09."ID" IS '序列，递增无实意，主键';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.msg_content IS '短信内容';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.fee_terminal_id IS '计费手机号码，第三方付费必填';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.dest_terminal_id IS '接收方手机号码，可为多个，以“,”间隔，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.sp_code IS '特服号';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.service_id IS '业务代码，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.fee_type IS '计费类型：1－免费，2－点播，3－包月';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.fee IS '费率，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.is_report IS '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.msg_format IS '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.request_time IS '请求下行时间：YYYYMMDDHHMMSS，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.submit_time IS '实际下行时间：YYYYMMDDHHMMSS，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.act_id IS '活动ID';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.unit_id IS '单位（第三合作方）编号';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.area_code IS '地区标识';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.operator_code IS '运营商标识';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.msg_id IS '下行标识';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.status IS '发送状态码，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_log_09.sms_id IS '短信编号';