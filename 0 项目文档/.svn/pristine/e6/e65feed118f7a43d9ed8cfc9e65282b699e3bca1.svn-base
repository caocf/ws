CREATE TABLE "LIFE".t_pay_order_payment (
  "ID" NUMBER(19) NOT NULL,
  pid NUMBER(19) NOT NULL,
  currency VARCHAR2(10 BYTE),
  amount NUMBER(9),
  remark VARCHAR2(50 BYTE)
);
ALTER TABLE "LIFE".t_pay_order_payment ADD SUPPLEMENTAL LOG GROUP ggs_240392 (amount, currency, "ID", pid, remark) ALWAYS;
COMMENT ON TABLE "LIFE".t_pay_order_payment IS '支付订单支付币种附表';
COMMENT ON COLUMN "LIFE".t_pay_order_payment."ID" IS '记录ID';
COMMENT ON COLUMN "LIFE".t_pay_order_payment.pid IS '主记录ID';
COMMENT ON COLUMN "LIFE".t_pay_order_payment.currency IS '支付货币，现金cash, 积分score, 商城币coin，红包redPackage, 花费余额balance';
COMMENT ON COLUMN "LIFE".t_pay_order_payment.amount IS '支付数量';
COMMENT ON COLUMN "LIFE".t_pay_order_payment.remark IS '备注';