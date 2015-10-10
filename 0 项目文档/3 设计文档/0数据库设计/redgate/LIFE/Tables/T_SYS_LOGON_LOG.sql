CREATE TABLE "LIFE".t_sys_logon_log (
  "ID" NUMBER(9) NOT NULL,
  user_code VARCHAR2(20 BYTE),
  logon_ip VARCHAR2(20 BYTE),
  logon_time VARCHAR2(14 BYTE),
  success_tag CHAR,
  CONSTRAINT pk_t_sys_logon_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_logon_log ADD SUPPLEMENTAL LOG GROUP ggs_240444 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_logon_log IS '管理后台登录日志表,负责限制登录错误次数';
COMMENT ON COLUMN "LIFE".t_sys_logon_log.success_tag IS '0失败
1成功';