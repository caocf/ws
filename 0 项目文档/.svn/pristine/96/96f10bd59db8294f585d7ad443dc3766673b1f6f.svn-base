CREATE TABLE "LIFE".t_mms_mo_log_07 (
  "ID" NUMBER(9) NOT NULL,
  title VARCHAR2(200 BYTE),
  mms_dir VARCHAR2(250 BYTE),
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
  CONSTRAINT pk_t_mms_mo_log_07 PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_mms_mo_log_07 ADD SUPPLEMENTAL LOG GROUP ggs_240381 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_mms_mo_log_07 IS '彩信上行日志表，由路由模块完成路由后记录。';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07."ID" IS '序列，递增无实意，主键';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.title IS '彩信标题，可为空';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.mms_dir IS '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.src_terminal_id IS '发送源手机号码，非空';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.sp_code IS '特服号，非空';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.get_time IS '上行接收时间（YYYYMMDDHHMMSS）';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.mo_from IS 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.mo_to IS '1、ACT处理
2、J2EE处理
3、TP接口处理';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.act_id IS '活动ID';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.done_time IS '处理此上行的时间（YYYYMMDDHHMMSS）';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.unit_id IS '单位编码';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.area_code IS '地区标识';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.operator_code IS '运营商标识';
COMMENT ON COLUMN "LIFE".t_mms_mo_log_07.linkid IS 'LINKID，保留字段';