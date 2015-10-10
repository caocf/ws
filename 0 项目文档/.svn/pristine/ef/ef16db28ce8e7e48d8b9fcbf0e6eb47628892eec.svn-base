CREATE TABLE "LIFE".t_sms_mt_wait (
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
  CONSTRAINT pk_t_sms_mt_wait PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sms_mt_wait ADD SUPPLEMENTAL LOG GROUP ggs_240610 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sms_mt_wait IS '短信业务下行日志表';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait."ID" IS '序列，递增无实意，主键，SEQ_SMS_MT_WAIT';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.msg_content IS '短信内容';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.fee_terminal_id IS '计费手机号码，第三方付费必填';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.dest_terminal_id IS '接收方手机号码，可为多个，以“,”间隔，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.sp_code IS '特服号';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.service_id IS '业务代码，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.fee_type IS '计费类型：1－免费，2－点播，3－包月';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.fee IS '费率，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.is_report IS '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.msg_format IS '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.request_time IS '记录插入时间：YYYYMMDDHHMMSS，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.submit_time IS '要求下发时间：YYYYMMDDHHMMSS，非空';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.act_id IS '活动ID';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.unit_id IS '单位（第三合作方）编号';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.area_code IS '地区标识';
COMMENT ON COLUMN "LIFE".t_sms_mt_wait.operator_code IS '运营商标识';