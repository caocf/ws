CREATE TABLE "LIFE".t_sys_ad_region (
  "ID" NUMBER(9) NOT NULL,
  region_code VARCHAR2(10 BYTE),
  ad_id NUMBER(9),
  CONSTRAINT pk_t_sys_ad_regon PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_ad_region ADD SUPPLEMENTAL LOG GROUP ggs_240428 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_ad_region IS '广告展现区域表';
COMMENT ON COLUMN "LIFE".t_sys_ad_region.region_code IS '展现区域编号';
COMMENT ON COLUMN "LIFE".t_sys_ad_region.ad_id IS '广告id';