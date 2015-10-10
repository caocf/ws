CREATE TABLE "LIFE".t_user_exchange_point_log (
  "ID" NUMBER(10) NOT NULL,
  terminal_id VARCHAR2(20 BYTE),
  amount NUMBER(5),
  user_ip VARCHAR2(30 BYTE),
  exc_date VARCHAR2(14 BYTE),
  r_code NUMBER(2),
  r_msg VARCHAR2(200 BYTE),
  exc_percent NUMBER(2),
  CONSTRAINT pk_t_user_exchange_point_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_user_exchange_point_log ADD SUPPLEMENTAL LOG GROUP ggs_240493 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_user_exchange_point_log IS '商城币兑换日志表';
COMMENT ON COLUMN "LIFE".t_user_exchange_point_log.terminal_id IS '手机号码';
COMMENT ON COLUMN "LIFE".t_user_exchange_point_log.amount IS '兑换商城币数';
COMMENT ON COLUMN "LIFE".t_user_exchange_point_log.user_ip IS '用户ip';
COMMENT ON COLUMN "LIFE".t_user_exchange_point_log.exc_date IS '兑换时间';
COMMENT ON COLUMN "LIFE".t_user_exchange_point_log.r_code IS '兑换结果code';
COMMENT ON COLUMN "LIFE".t_user_exchange_point_log.r_msg IS '兑换结果描述';
COMMENT ON COLUMN "LIFE".t_user_exchange_point_log.exc_percent IS '兑换比例';