CREATE TABLE "LIFE".t_market_product (
  "ID" VARCHAR2(4 BYTE) NOT NULL,
  product_name VARCHAR2(200 BYTE),
  list_price NUMBER(12,2),
  sc_price NUMBER(12,2),
  product_pic VARCHAR2(50 BYTE),
  product_link VARCHAR2(150 BYTE),
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_product ADD SUPPLEMENTAL LOG GROUP ggs_240629 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_product IS '竞拍商品广告';
COMMENT ON COLUMN "LIFE".t_market_product."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_product.product_name IS '商品名';
COMMENT ON COLUMN "LIFE".t_market_product.list_price IS '市场价';
COMMENT ON COLUMN "LIFE".t_market_product.sc_price IS '商城价';
COMMENT ON COLUMN "LIFE".t_market_product.product_pic IS '商品图片路径';
COMMENT ON COLUMN "LIFE".t_market_product.product_link IS '商品页面链接';