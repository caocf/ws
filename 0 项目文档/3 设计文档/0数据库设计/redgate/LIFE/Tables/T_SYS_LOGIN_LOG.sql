CREATE TABLE "LIFE".t_sys_login_log (
  "ID" NUMBER(9) NOT NULL,
  user_name VARCHAR2(20 BYTE),
  user_id NUMBER(9),
  ip VARCHAR2(15 BYTE),
  login_time VARCHAR2(14 BYTE),
  success_flag NUMBER(2),
  user_type NUMBER(2),
  CONSTRAINT pk_t_sys_login_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_login_log ADD SUPPLEMENTAL LOG GROUP ggs_240442 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_login_log IS '管理后台登录日志表';
COMMENT ON COLUMN "LIFE".t_sys_login_log.success_flag IS '1-成功 0-失败';