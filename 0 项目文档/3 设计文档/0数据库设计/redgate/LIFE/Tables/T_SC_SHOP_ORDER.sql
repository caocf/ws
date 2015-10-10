CREATE TABLE "LIFE".t_sc_shop_order (
  "ID" NUMBER(9) NOT NULL,
  shop_id NUMBER(9),
  app_id NUMBER(9),
  product_id VARCHAR2(50 BYTE),
  update_time VARCHAR2(14 BYTE),
  product_status NUMBER(2),
  end_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_sc_user_order PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sc_shop_order ADD SUPPLEMENTAL LOG GROUP ggs_240634 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sc_shop_order."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_sc_shop_order.shop_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_sc_shop_order.app_id IS '应用编号';
COMMENT ON COLUMN "LIFE".t_sc_shop_order.product_id IS '业务编号';
COMMENT ON COLUMN "LIFE".t_sc_shop_order.update_time IS '同步时间';
COMMENT ON COLUMN "LIFE".t_sc_shop_order.product_status IS '应用状态 1:开通
2:停用
';
COMMENT ON COLUMN "LIFE".t_sc_shop_order.end_time IS '到期时间';