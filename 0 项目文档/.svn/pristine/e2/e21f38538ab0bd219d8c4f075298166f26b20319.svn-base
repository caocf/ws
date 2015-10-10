CREATE TABLE "LIFE".t_shop_region (
  "ID" NUMBER(9) NOT NULL,
  region_code VARCHAR2(100 BYTE),
  shop_class NUMBER(1),
  shop_id NUMBER(9)
);
ALTER TABLE "LIFE".t_shop_region ADD SUPPLEMENTAL LOG GROUP ggs_240403 ("ID", region_code, shop_class, shop_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_shop_region.region_code IS '区域编码';
COMMENT ON COLUMN "LIFE".t_shop_region.shop_class IS '商户分类：1-业务门店；2-结算商户；3-渠道商';
COMMENT ON COLUMN "LIFE".t_shop_region.shop_id IS '商户编号';