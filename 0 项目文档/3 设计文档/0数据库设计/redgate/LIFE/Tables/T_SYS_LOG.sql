CREATE TABLE "LIFE".t_sys_log (
  "ID" NUMBER(9) NOT NULL,
  user_id VARCHAR2(20 BYTE) NOT NULL,
  oper_time VARCHAR2(14 BYTE) NOT NULL,
  oper_type NUMBER(3) NOT NULL,
  module VARCHAR2(100 BYTE) NOT NULL,
  description VARCHAR2(200 BYTE),
  result_code VARCHAR2(10 BYTE),
  ip VARCHAR2(20 BYTE),
  user_name VARCHAR2(100 BYTE),
  user_type VARCHAR2(20 BYTE),
  op_id NUMBER(9),
  CONSTRAINT pk_t_sys_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_log ADD SUPPLEMENTAL LOG GROUP ggs_240441 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_log IS '系统日志表';
COMMENT ON COLUMN "LIFE".t_sys_log.oper_type IS '操作类型   1-查看 2-添加 3-修改 4-删除 5-审核  6-其他';
COMMENT ON COLUMN "LIFE".t_sys_log.result_code IS '操作是否成功 0-成功  非0 失败';
COMMENT ON COLUMN "LIFE".t_sys_log.user_name IS '用户名称';
COMMENT ON COLUMN "LIFE".t_sys_log.user_type IS '用户类型';
COMMENT ON COLUMN "LIFE".t_sys_log.op_id IS '操作对象ID';