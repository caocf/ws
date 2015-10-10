CREATE TABLE "LIFE".t_item_price_coin_pay_proporti (
  "ID" NUMBER(9) NOT NULL,
  "RANGE" VARCHAR2(20 BYTE) NOT NULL,
  item_type_id NUMBER(9),
  cash_proportion NUMBER(11,2) NOT NULL,
  coin_proportion NUMBER(11,2) NOT NULL,
  remark VARCHAR2(500 BYTE),
  CONSTRAINT pk_t_item_price_coin_pay_propo PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_price_coin_pay_proporti ADD SUPPLEMENTAL LOG GROUP ggs_240360 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_item_price_coin_pay_proporti IS '商城币+现金=100%';
COMMENT ON COLUMN "LIFE".t_item_price_coin_pay_proporti."ID" IS '唯一ID';
COMMENT ON COLUMN "LIFE".t_item_price_coin_pay_proporti."RANGE" IS '使用范围（全局/局部）';
COMMENT ON COLUMN "LIFE".t_item_price_coin_pay_proporti.item_type_id IS '商品分类ID';
COMMENT ON COLUMN "LIFE".t_item_price_coin_pay_proporti.cash_proportion IS '现金支付比例';
COMMENT ON COLUMN "LIFE".t_item_price_coin_pay_proporti.coin_proportion IS '商城币支付比例';
COMMENT ON COLUMN "LIFE".t_item_price_coin_pay_proporti.remark IS '备注';