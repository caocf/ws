CREATE TABLE "LIFE".t_sms_mo_log_06 (
  "ID" NUMBER(9) NOT NULL,
  msg_content VARCHAR2(1000 BYTE),
  src_terminal_id VARCHAR2(21 BYTE),
  sp_code VARCHAR2(20 BYTE),
  get_time VARCHAR2(14 BYTE),
  mo_from NUMBER(3),
  mo_to NUMBER(3),
  act_id NUMBER(9),
  done_time VARCHAR2(14 BYTE),
  unit_id VARCHAR2(20 BYTE),
  area_code VARCHAR2(8 BYTE),
  operator_code VARCHAR2(50 BYTE),
  linkid VARCHAR2(20 BYTE),
  CONSTRAINT pk_t_sms_mo_log_06 PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sms_mo_log_06 ADD SUPPLEMENTAL LOG GROUP ggs_240592 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sms_mo_log_06 IS '短信上行日志表，数据由业务处理程序流入';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06."ID" IS '序列，递增无实意，主键';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.msg_content IS '短信内容';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.src_terminal_id IS '发送源手机号码，非空';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.sp_code IS '特服号，非空';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.get_time IS '上行接收时间（YYYYMMDDHHMMSS）';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.mo_from IS 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.mo_to IS '1、ACT处理
2、J2EE处理
3、TP接口处理';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.act_id IS '活动ID';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.done_time IS '处理此上行的时间（YYYYMMDDHHMMSS）';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.unit_id IS '单位（第三合作方）编码';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.area_code IS '地区标识';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.operator_code IS '运营商标识';
COMMENT ON COLUMN "LIFE".t_sms_mo_log_06.linkid IS 'LINKID，保留字段';