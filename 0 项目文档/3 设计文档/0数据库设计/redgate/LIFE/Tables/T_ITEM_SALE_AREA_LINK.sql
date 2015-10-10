CREATE TABLE "LIFE".t_item_sale_area_link (
  "ID" NUMBER(8) NOT NULL,
  sale_id NUMBER(8),
  province_code VARCHAR2(10 BYTE),
  city_code VARCHAR2(10 BYTE),
  region_code VARCHAR2(10 BYTE),
  CONSTRAINT pk_t_item_sale_area_link PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_sale_area_link ADD SUPPLEMENTAL LOG GROUP ggs_240363 ("ID") ALWAYS;