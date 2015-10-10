CREATE TABLE "LIFE".t_sys_region (
  "ID" NUMBER(9) NOT NULL,
  region_code VARCHAR2(100 BYTE),
  region_name VARCHAR2(200 BYTE),
  region_level NUMBER(10),
  parent_region VARCHAR2(100 BYTE) NOT NULL,
  short_name VARCHAR2(50 BYTE),
  region_spell VARCHAR2(200 BYTE),
  is_show NUMBER(1) DEFAULT 1,
  sort_num NUMBER(9) DEFAULT 99999,
  area_code VARCHAR2(200 BYTE),
  CONSTRAINT pk_sys_region PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_region ADD SUPPLEMENTAL LOG GROUP ggs_240449 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_region IS '系统行政区域表';
COMMENT ON COLUMN "LIFE".t_sys_region.region_code IS '区域代码';
COMMENT ON COLUMN "LIFE".t_sys_region.region_name IS '区域名称';
COMMENT ON COLUMN "LIFE".t_sys_region.region_level IS '区域等级';
COMMENT ON COLUMN "LIFE".t_sys_region.parent_region IS '父区域
0-一级区域';
COMMENT ON COLUMN "LIFE".t_sys_region.short_name IS '简称';
COMMENT ON COLUMN "LIFE".t_sys_region.region_spell IS '区域拼音';
COMMENT ON COLUMN "LIFE".t_sys_region.is_show IS '是否显示 0-不显示 1-显示';
COMMENT ON COLUMN "LIFE".t_sys_region.sort_num IS '排序';
COMMENT ON COLUMN "LIFE".t_sys_region.area_code IS '地市码';