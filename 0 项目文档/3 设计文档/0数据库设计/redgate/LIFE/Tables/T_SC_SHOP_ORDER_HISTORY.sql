CREATE TABLE "LIFE".t_sc_shop_order_history (
  "ID" NUMBER(9) NOT NULL,
  shop_id NUMBER(9),
  app_id NUMBER(9),
  product_id VARCHAR2(50 BYTE),
  update_time VARCHAR2(14 BYTE),
  product_status NUMBER(2),
  end_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_sc_user_order_history PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sc_shop_order_history ADD SUPPLEMENTAL LOG GROUP ggs_240635 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sc_shop_order_history."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_history.shop_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_history.app_id IS '应用编号';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_history.product_id IS '业务编号';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_history.update_time IS '同步时间';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_history.product_status IS '应用状态 1:开通
2:停用
';
COMMENT ON COLUMN "LIFE".t_sc_shop_order_history.end_time IS '到期时间';