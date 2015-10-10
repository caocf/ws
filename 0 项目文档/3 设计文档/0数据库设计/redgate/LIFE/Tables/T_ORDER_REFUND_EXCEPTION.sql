CREATE TABLE "LIFE".t_order_refund_exception (
  "ID" NUMBER(8) NOT NULL,
  resource_id NUMBER(19),
  resouce_type NUMBER(1),
  payment VARCHAR2(30 BYTE),
  reason NUMBER(1),
  create_user_id NUMBER(9),
  create_time VARCHAR2(14 BYTE),
  remark VARCHAR2(20 BYTE),
  CONSTRAINT pk_t_order_refund_exception PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_order_refund_exception IS '订单和退款异常管理表';
COMMENT ON COLUMN "LIFE".t_order_refund_exception.resource_id IS '来源ID： 订单ID或者退款单ID';
COMMENT ON COLUMN "LIFE".t_order_refund_exception.resouce_type IS '来源类型：0：订单，1：退款单';
COMMENT ON COLUMN "LIFE".t_order_refund_exception.payment IS '支付方式：现金、商品币、积分、话费或者其他';
COMMENT ON COLUMN "LIFE".t_order_refund_exception.reason IS '修改原因 0：清算系统反馈长款';
COMMENT ON COLUMN "LIFE".t_order_refund_exception.create_user_id IS '修改人';
COMMENT ON COLUMN "LIFE".t_order_refund_exception.create_time IS '修改时间';
COMMENT ON COLUMN "LIFE".t_order_refund_exception.remark IS '备注';