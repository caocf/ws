CREATE TABLE "LIFE".t_year_goods_region (
  "ID" NUMBER(10) NOT NULL CHECK ("ID" IS NOT NULL) CHECK ("ID" IS NOT NULL),
  sale_id NUMBER(10) NOT NULL CHECK ("SALE_ID" IS NOT NULL) CHECK ("SALE_ID" IS NOT NULL),
  city_code VARCHAR2(100 BYTE),
  city_name VARCHAR2(200 BYTE),
  PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_year_goods_region.sale_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_year_goods_region.city_code IS '城市编号';
COMMENT ON COLUMN "LIFE".t_year_goods_region.city_name IS '城市名称';