CREATE TABLE "LIFE".t_sc_app_product (
  "ID" NUMBER(9) NOT NULL,
  app_id NUMBER(9),
  product_id VARCHAR2(50 BYTE),
  product_name VARCHAR2(200 BYTE),
  product_remark VARCHAR2(2000 BYTE),
  product_price NUMBER(8,2),
  product_type NUMBER(2),
  CONSTRAINT pk_t_app_product PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sc_app_product ADD SUPPLEMENTAL LOG GROUP ggs_240645 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sc_app_product.product_type IS '1:正常套餐
2:叠加包';