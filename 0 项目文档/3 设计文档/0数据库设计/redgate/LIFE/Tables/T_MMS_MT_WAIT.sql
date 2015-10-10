CREATE TABLE "LIFE".t_mms_mt_wait (
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
  mms_type NUMBER(3),
  CONSTRAINT pk_t_mms_mt_wait PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_mms_mt_wait ADD SUPPLEMENTAL LOG GROUP ggs_240564 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_mms_mt_wait IS '彩信业务下行等待表，存放待发送的彩信下行；
一般用于定时发送下行。';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait."ID" IS '序列，递增无实意，主键，SEQ_MMS_MT_WAIT';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.title IS '彩信标题，可为空';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.smil_name IS '彩信SMIL文件，可有可无';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.mms_dir IS '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.fee_terminal_id IS '计费手机号码，第三方付费必填';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.dest_terminal_id IS '接收方手机号码，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.cc_terminal_id IS '抄送方手机号码，个数为0-9，以“,”间隔';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.service_id IS '业务代码，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.fee_type IS '计费类型：1－免费，2－点播，3－包月';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.fee IS '费率，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.is_report IS '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.request_time IS '记录插入时间：YYYYMMDDHHMMSS，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.submit_time IS '要求下行时间：YYYYMMDDHHMMSS，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.sale_mode IS '本字段无效，默认填0';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.act_id IS '活动ID';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.mms_id IS '彩信编号，非空';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.unit_id IS '单位（第三合作方）编号';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.area_code IS '地区标识';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.operator_code IS '运营商标识';
COMMENT ON COLUMN "LIFE".t_mms_mt_wait.mms_type IS '1、彩信
2、图片
3、铃声';