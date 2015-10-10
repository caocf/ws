CREATE TABLE "LIFE".t_pay_order_callback (
  "ID" NUMBER(19) NOT NULL,
  record_id NUMBER(19),
  pay_order_id NUMBER(19),
  status NUMBER(10),
  status_text VARCHAR2(500 BYTE),
  update_time VARCHAR2(14 BYTE),
  charge_time VARCHAR2(14 BYTE),
  out_trade_no VARCHAR2(64 BYTE),
  CONSTRAINT pk_pay_order_callback PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_pay_order_callback IS '支付订单回调日志表';
COMMENT ON COLUMN "LIFE".t_pay_order_callback."ID" IS '记录ID';
COMMENT ON COLUMN "LIFE".t_pay_order_callback.pay_order_id IS '支付订单Id';
COMMENT ON COLUMN "LIFE".t_pay_order_callback.status IS '状态, -1异常、0未执行、1执行中、2成功、3失败';
COMMENT ON COLUMN "LIFE".t_pay_order_callback.status_text IS '状态文字说明';
COMMENT ON COLUMN "LIFE".t_pay_order_callback.update_time IS '状态更新时间';
COMMENT ON COLUMN "LIFE".t_pay_order_callback.charge_time IS '支付平台回调中的“支付/退款”的时间';
COMMENT ON COLUMN "LIFE".t_pay_order_callback.out_trade_no IS '外部交易流水号';