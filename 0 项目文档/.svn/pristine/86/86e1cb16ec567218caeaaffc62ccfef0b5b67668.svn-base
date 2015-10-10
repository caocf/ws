CREATE TABLE "LIFE".t_mms_mt_log_04 (
  "ID" NUMBER(9) NOT NULL,
  title VARCHAR2(200 BYTE),
  smil_name VARCHAR2(40 BYTE),
  mms_dir VARCHAR2(250 BYTE),
  fee_terminal_id VARCHAR2(21 BYTE),
  dest_terminal_id VARCHAR2(120 BYTE),
  cc_terminal_id VARCHAR2(250 BYTE),
  service_id VARCHAR2(20 BYTE),
  fee_type NUMBER(3),
  fee NUMBER(9),
  is_report NUMBER(1),
  request_time VARCHAR2(14 BYTE),
  submit_time VARCHAR2(14 BYTE),
  sale_mode NUMBER(3),
  act_id NUMBER(9),
  mms_id NUMBER(9),
  unit_id VARCHAR2(20 BYTE),
  area_code VARCHAR2(8 BYTE),
  operator_code VARCHAR2(50 BYTE),
  msg_id VARCHAR2(50 BYTE),
  status VARCHAR2(10 BYTE),
  status_sign VARCHAR2(100 BYTE),
  mms_type NUMBER(3),
  CONSTRAINT pk_t_mms_mt_log_04 PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_mms_mt_log_04 ADD SUPPLEMENTAL LOG GROUP ggs_240555 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_mms_mt_log_04 IS '彩信业务下行日志表，按月分表存储';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04."ID" IS '序列，递增无实意，主键';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.title IS '彩信标题，可为空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.smil_name IS '彩信SMIL文件，可有可无';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.mms_dir IS '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.fee_terminal_id IS '计费手机号码，第三方付费必填';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.dest_terminal_id IS '接收方手机号码，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.cc_terminal_id IS '抄送方手机号码，个数为0-9，以“,”间隔';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.service_id IS '业务代码，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.fee_type IS '计费类型：1－免费，2－点播，3－包月';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.fee IS '费率，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.is_report IS '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.request_time IS '请求下行时间：YYYYMMDDHHMMSS，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.submit_time IS '实际下行时间：YYYYMMDDHHMMSS，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.sale_mode IS '本字段无效，默认填0';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.act_id IS '活动ID';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.mms_id IS '彩信编号，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.unit_id IS '单位（第三合作方）编号';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.area_code IS '地区标识';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.operator_code IS '运营商标识';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.msg_id IS '下行标识，彩信中心返回，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.status IS '下行状态码，彩信中心返回，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.status_sign IS '下行状态注解';
COMMENT ON COLUMN "LIFE".t_mms_mt_log_04.mms_type IS '1、彩信
2、图片
3、铃声';