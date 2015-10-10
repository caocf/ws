CREATE TABLE "LIFE".t_act_order_goods (
  "ID" NUMBER(19) NOT NULL,
  act_order_id NUMBER(19),
  "COUNT" NUMBER(10),
  discount NUMBER(10),
  fee_price NUMBER(10),
  goods_description VARCHAR2(255 CHAR),
  goods_id NUMBER(19),
  goods_subject VARCHAR2(255 CHAR),
  pay_price NUMBER(10),
  refund_amount NUMBER(10),
  refund_count NUMBER(10),
  refund_status NUMBER(10),
  refund_time VARCHAR2(255 CHAR),
  verify_code_id VARCHAR2(30 CHAR),
  verify_description VARCHAR2(255 CHAR),
  verify_status NUMBER(10),
  verify_time VARCHAR2(255 CHAR),
  refund_description VARCHAR2(255 CHAR),
  remark VARCHAR2(255 CHAR),
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_act_order_goods ADD SUPPLEMENTAL LOG GROUP ggs_240331 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_act_order_goods IS '业务订单商品信息附表';
COMMENT ON COLUMN "LIFE".t_act_order_goods."ID" IS ' ';
COMMENT ON COLUMN "LIFE".t_act_order_goods.act_order_id IS '业务订单Id';
COMMENT ON COLUMN "LIFE".t_act_order_goods."COUNT" IS '商品数量';
COMMENT ON COLUMN "LIFE".t_act_order_goods.discount IS '商品折扣（分）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.fee_price IS '结算金额（分）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.goods_description IS '商品描述';
COMMENT ON COLUMN "LIFE".t_act_order_goods.goods_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_act_order_goods.goods_subject IS '商品标题';
COMMENT ON COLUMN "LIFE".t_act_order_goods.pay_price IS '支付单价';
COMMENT ON COLUMN "LIFE".t_act_order_goods.refund_amount IS '（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.refund_count IS '（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.refund_status IS '退款状态 0未退款，1已退款（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.refund_time IS '退款时间（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.verify_code_id IS '（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.verify_description IS '（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.verify_status IS '（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.verify_time IS '（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.refund_description IS '退款说明（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order_goods.remark IS '商品备注';