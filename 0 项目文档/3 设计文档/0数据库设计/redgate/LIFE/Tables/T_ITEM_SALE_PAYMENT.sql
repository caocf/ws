CREATE TABLE "LIFE".t_item_sale_payment (
  "ID" NUMBER(8) NOT NULL,
  item_id NUMBER(8),
  pay_type NUMBER(1) DEFAULT 0,
  cash_pay_ratio NUMBER(1) DEFAULT 0,
  other_pay_ratio NUMBER(1) DEFAULT 0,
  bill_pay NUMBER(1),
  delivery_pay NUMBER(1) DEFAULT 0,
  hnscore_pay NUMBER(1) DEFAULT 0,
  CONSTRAINT pk_t_item_sale_payment PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_item_sale_payment."ID" IS '流水号';
COMMENT ON COLUMN "LIFE".t_item_sale_payment.item_id IS '商品编号';
COMMENT ON COLUMN "LIFE".t_item_sale_payment.pay_type IS '支付类型(0 单一支付 1组合支付)';
COMMENT ON COLUMN "LIFE".t_item_sale_payment.cash_pay_ratio IS '现金支付比例';
COMMENT ON COLUMN "LIFE".t_item_sale_payment.other_pay_ratio IS '商城币或积分支付比例';
COMMENT ON COLUMN "LIFE".t_item_sale_payment.bill_pay IS '话费支付(0 不支持 1支持)';
COMMENT ON COLUMN "LIFE".t_item_sale_payment.delivery_pay IS '支付方式：是否支持货到付款（限现金）   0为不支持，1为支持';
COMMENT ON COLUMN "LIFE".t_item_sale_payment.hnscore_pay IS '河南积分支付(0 不支持 1支持)';