CREATE TABLE "LIFE".t_web_access_log (
  "ID" NUMBER(9) NOT NULL,
  terminal_id VARCHAR2(11 BYTE),
  custom_id VARCHAR2(20 BYTE),
  access_ip VARCHAR2(20 BYTE),
  access_start_time VARCHAR2(20 BYTE) NOT NULL,
  access_stop_time VARCHAR2(20 BYTE),
  settle_time VARCHAR2(10 BYTE),
  remark VARCHAR2(200 BYTE),
  src VARCHAR2(10 BYTE),
  sessionid VARCHAR2(200 BYTE)
);
ALTER TABLE "LIFE".t_web_access_log ADD SUPPLEMENTAL LOG GROUP ggs_240484 (access_ip, access_start_time, access_stop_time, custom_id, "ID", remark, sessionid, settle_time, src, terminal_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_web_access_log.terminal_id IS '用户手机号';
COMMENT ON COLUMN "LIFE".t_web_access_log.custom_id IS '用户会员编号';
COMMENT ON COLUMN "LIFE".t_web_access_log.access_ip IS '访问IP';
COMMENT ON COLUMN "LIFE".t_web_access_log.access_start_time IS '访问开始时间';
COMMENT ON COLUMN "LIFE".t_web_access_log.access_stop_time IS '访问结束时间';
COMMENT ON COLUMN "LIFE".t_web_access_log.settle_time IS '停留时间';
COMMENT ON COLUMN "LIFE".t_web_access_log.src IS '来源：wap网站/web网站';