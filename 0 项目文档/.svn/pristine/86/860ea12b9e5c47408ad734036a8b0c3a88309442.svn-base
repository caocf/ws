CREATE TABLE "LIFE".t_year_activity (
  "ID" NUMBER(10) NOT NULL CHECK ("ID" IS NOT NULL) CHECK ("ID" IS NOT NULL),
  sale_id NUMBER(10) NOT NULL CHECK ("SALE_ID" IS NOT NULL) CHECK ("SALE_ID" IS NOT NULL),
  goods_name VARCHAR2(600 BYTE),
  goods_price NUMBER(10,2),
  image VARCHAR2(200 BYTE),
  create_time VARCHAR2(30 BYTE),
  "SOURCE" VARCHAR2(2 BYTE),
  "TYPE" VARCHAR2(1 BYTE) DEFAULT 0,
  market_price NUMBER(10,2),
  goods_area_type VARCHAR2(1 BYTE),
  price_type VARCHAR2(1 BYTE) DEFAULT 0,
  PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_year_activity.sale_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_year_activity.goods_name IS '商品名称';
COMMENT ON COLUMN "LIFE".t_year_activity.goods_price IS '商品价格';
COMMENT ON COLUMN "LIFE".t_year_activity.image IS '图片';
COMMENT ON COLUMN "LIFE".t_year_activity.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_year_activity."SOURCE" IS '商品来源 ';
COMMENT ON COLUMN "LIFE".t_year_activity."TYPE" IS '展示类型 ';
COMMENT ON COLUMN "LIFE".t_year_activity.market_price IS '市场价';
COMMENT ON COLUMN "LIFE".t_year_activity.goods_area_type IS '商品归属地类型 1为全省商品，0为归属地商品';
COMMENT ON COLUMN "LIFE".t_year_activity.price_type IS '价格展示类型 0为现金 1为积分';