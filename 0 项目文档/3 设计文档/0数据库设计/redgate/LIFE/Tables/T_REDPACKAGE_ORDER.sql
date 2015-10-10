CREATE TABLE "LIFE".t_redpackage_order (
  "ID" NUMBER,
  act_order_id NUMBER,
  amount VARCHAR2(10 BYTE),
  terminal_id VARCHAR2(11 BYTE),
  code VARCHAR2(10 BYTE),
  create_time VARCHAR2(14 BYTE)
);
COMMENT ON TABLE "LIFE".t_redpackage_order IS '红包充值流水表';
COMMENT ON COLUMN "LIFE".t_redpackage_order.act_order_id IS '业务订单号';
COMMENT ON COLUMN "LIFE".t_redpackage_order.amount IS '充值金额';
COMMENT ON COLUMN "LIFE".t_redpackage_order.terminal_id IS '用户账户';
COMMENT ON COLUMN "LIFE".t_redpackage_order.code IS '充值情况 0000成功';