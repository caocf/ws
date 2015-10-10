CREATE TABLE "LIFE".t_market_area_link (
  "ID" NUMBER(8) NOT NULL,
  good_id NUMBER(10),
  province_code VARCHAR2(10 BYTE),
  city_code VARCHAR2(10 BYTE),
  region_code VARCHAR2(10 BYTE),
  "TYPE" NUMBER(1),
  CONSTRAINT pk_t_market_area_link PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_market_area_link."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_area_link.good_id IS '商品编号';
COMMENT ON COLUMN "LIFE".t_market_area_link.province_code IS '省编码';
COMMENT ON COLUMN "LIFE".t_market_area_link.city_code IS '城市编码';
COMMENT ON COLUMN "LIFE".t_market_area_link.region_code IS '地区编码';
COMMENT ON COLUMN "LIFE".t_market_area_link."TYPE" IS '0为团购，1为商品';