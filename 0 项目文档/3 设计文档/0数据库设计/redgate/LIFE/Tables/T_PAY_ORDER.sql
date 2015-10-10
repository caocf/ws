CREATE TABLE "LIFE".t_pay_order (
  "ID" NUMBER(19) NOT NULL,
  act_order_id NUMBER(19),
  pay_order_id NUMBER(19),
  operate NUMBER(3),
  pay_mode NUMBER(3),
  pay_channel VARCHAR2(50 BYTE),
  user_id NUMBER(19),
  shop_id NUMBER(19),
  payment_amount NUMBER(9),
  status NUMBER(3),
  status_text VARCHAR2(500 BYTE),
  create_time VARCHAR2(14 BYTE),
  update_time VARCHAR2(14 BYTE),
  remark VARCHAR2(500 BYTE),
  charge_time VARCHAR2(14 BYTE),
  out_trade_no VARCHAR2(64 BYTE),
  CONSTRAINT pk_pay_order PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_pay_order ADD SUPPLEMENTAL LOG GROUP ggs_240391 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_pay_order IS '支付订单主表';
COMMENT ON COLUMN "LIFE".t_pay_order."ID" IS '记录ID';
COMMENT ON COLUMN "LIFE".t_pay_order.act_order_id IS '业务订单Id';
COMMENT ON COLUMN "LIFE".t_pay_order.pay_order_id IS '支付订单Id';
COMMENT ON COLUMN "LIFE".t_pay_order.operate IS '1-支付,2-退款';
COMMENT ON COLUMN "LIFE".t_pay_order.pay_mode IS '1-web, 2-后台';
COMMENT ON COLUMN "LIFE".t_pay_order.pay_channel IS '支付宝支付:alipay, 测试用支付渠道:demo, EMS货到付款:ems.pay_on_delivery, 紅包支付:red.envelopes.pay, 统一支付-余额支付:unify.balance, 统一支付-购物车支付申请-支付宝:unify.cart.apply.alipay, 统一支付-购物车支付申请-手机支付:unify.cart.apply.cmpay, 统一支付-购物车支付申请-积分支付:unify.cart.apply.score, 统一支付-购物车支付-商城币支付:unify.cart.coin, 统一支付-购物车支付-积分支付:unify.cart.score, 统一支付-河南积分支付:unify.henan.score, 统一支付-WAP页面的支付宝支付:unify.wap.alipay, ';
COMMENT ON COLUMN "LIFE".t_pay_order.user_id IS '用户Id';
COMMENT ON COLUMN "LIFE".t_pay_order.shop_id IS '商户Id，用户购买商户的';
COMMENT ON COLUMN "LIFE".t_pay_order.payment_amount IS '应付款等价金额(单位:分)';
COMMENT ON COLUMN "LIFE".t_pay_order.status IS '状态, -1异常、0未执行、1执行中、2成功、3失败';
COMMENT ON COLUMN "LIFE".t_pay_order.status_text IS '状态文字说明';
COMMENT ON COLUMN "LIFE".t_pay_order.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_pay_order.update_time IS '状态更新时间';
COMMENT ON COLUMN "LIFE".t_pay_order.remark IS '订单备注';
COMMENT ON COLUMN "LIFE".t_pay_order.charge_time IS '支付平台回调中的“支付/退款”的时间';
COMMENT ON COLUMN "LIFE".t_pay_order.out_trade_no IS '外部交易流水号';