CREATE TABLE "LIFE".t_sys_area_info (
  "ID" NUMBER(9) NOT NULL,
  area_code VARCHAR2(8 BYTE) NOT NULL,
  area_name VARCHAR2(20 BYTE) NOT NULL,
  prov_id NUMBER(9),
  province_name VARCHAR2(20 BYTE) NOT NULL,
  area_spell VARCHAR2(20 BYTE),
  day_limit VARCHAR2(9 CHAR),
  month_limit VARCHAR2(9 CHAR),
  CONSTRAINT pk_t_sys_area_info PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_area_info ADD SUPPLEMENTAL LOG GROUP ggs_240429 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sys_area_info."ID" IS '地市ID';
COMMENT ON COLUMN "LIFE".t_sys_area_info.area_code IS '地市代码(区号)';
COMMENT ON COLUMN "LIFE".t_sys_area_info.area_name IS '地市名称';
COMMENT ON COLUMN "LIFE".t_sys_area_info.prov_id IS '所属省ID';
COMMENT ON COLUMN "LIFE".t_sys_area_info.province_name IS '所属省名称';
COMMENT ON COLUMN "LIFE".t_sys_area_info.area_spell IS '地市全拼';