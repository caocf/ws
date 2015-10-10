CREATE TABLE "LIFE".t_sc_shop_order_untredted (
  "ID" NUMBER(9) NOT NULL,
  shop_id NUMBER(9),
  product_id VARCHAR2(50 BYTE),
  update_time VARCHAR2(14 BYTE),
  product_status NUMBER(2),
  deal_time VARCHAR2(14 BYTE),
  deal_status NUMBER(2),
  CONSTRAINT pk_t_sc_user_order_untredted PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sc_shop_order_untredted ADD SUPPLEMENTAL LOG GROUP ggs_240636 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sc_shop_order_untredted."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_untredted.shop_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_untredted.product_id IS '业务编号';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_untredted.update_time IS '同步时间';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_untredted.product_status IS '应用状态  0:关闭
1:开通
2:暂停
3:恢复';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_untredted.deal_time IS '处理时间';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_untredted.deal_status IS '处理状态  0：未处理
1：已处理';