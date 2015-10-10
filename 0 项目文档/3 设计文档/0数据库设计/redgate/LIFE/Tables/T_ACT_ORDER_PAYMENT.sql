CREATE TABLE "LIFE".t_act_order_payment (
  "ID" NUMBER(19) NOT NULL,
  act_order_id NUMBER(19),
  amount NUMBER(10),
  channal VARCHAR2(255 CHAR),
  currency VARCHAR2(255 CHAR),
  splite_info VARCHAR2(1000 CHAR),
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_act_order_payment ADD SUPPLEMENTAL LOG GROUP ggs_240523 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_act_order_payment IS '业务订单支付项目附表';
COMMENT ON COLUMN "LIFE".t_act_order_payment."ID" IS ' ';
COMMENT ON COLUMN "LIFE".t_act_order_payment.act_order_id IS '订单ID';
COMMENT ON COLUMN "LIFE".t_act_order_payment.amount IS '支付数量（分）';
COMMENT ON COLUMN "LIFE".t_act_order_payment.channal IS '支付渠道（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_payment.currency IS '支付币种（现金cash、商城币coin、积分score）';