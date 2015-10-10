CREATE TABLE "LIFE".t_open_customer_item (
  app_id VARCHAR2(32 BYTE),
  item_id NUMBER(9),
  business_id VARCHAR2(20 BYTE)
);
COMMENT ON TABLE "LIFE".t_open_customer_item IS '第三方应用商品关联表';
COMMENT ON COLUMN "LIFE".t_open_customer_item.app_id IS '应用ID';
COMMENT ON COLUMN "LIFE".t_open_customer_item.item_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_open_customer_item.business_id IS '商品的第三方商品ID';