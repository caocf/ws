CREATE TABLE "LIFE".t_sys_currency_exchange_log (
  "ID" NUMBER(9) NOT NULL,
  user_id VARCHAR2(20 BYTE) NOT NULL,
  terminal_id VARCHAR2(20 BYTE) NOT NULL,
  exchange_time VARCHAR2(14 BYTE) NOT NULL,
  score NUMBER(11,2) NOT NULL,
  coin NUMBER(11,2) NOT NULL,
  order_id NUMBER(9),
  remark VARCHAR2(500 BYTE),
  CONSTRAINT pk_t_sys_currency_exchange_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_currency_exchange_log ADD SUPPLEMENTAL LOG GROUP ggs_240432 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_currency_exchange_log IS '存放商城币、积分、现金之间的兑换的日志';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange_log."ID" IS '唯一ID';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange_log.user_id IS '用户ID';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange_log.terminal_id IS '手机号码';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange_log.exchange_time IS '兑换日期';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange_log.score IS '兑换积分,如输入1000';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange_log.coin IS '兑换商城币????';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange_log.order_id IS '订单号';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange_log.remark IS '备注';