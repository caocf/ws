CREATE TABLE "LIFE".t_order_refund_goods (
  "ID" NUMBER(9) NOT NULL,
  refund_id NUMBER(9),
  order_goods_id NUMBER(19),
  goods_id NUMBER(9),
  back_number NUMBER(5),
  cash NUMBER(9,2),
  coin NUMBER(9,2),
  CONSTRAINT pk_t_order_refund_goods PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_order_refund_goods ADD SUPPLEMENTAL LOG GROUP ggs_240390 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_order_refund_goods.refund_id IS '退货单编号';
COMMENT ON COLUMN "LIFE".t_order_refund_goods.order_goods_id IS '订单商品id';
COMMENT ON COLUMN "LIFE".t_order_refund_goods.goods_id IS '商品id';
COMMENT ON COLUMN "LIFE".t_order_refund_goods.back_number IS '退货数量';
COMMENT ON COLUMN "LIFE".t_order_refund_goods.cash IS '现金';
COMMENT ON COLUMN "LIFE".t_order_refund_goods.coin IS '商城币';