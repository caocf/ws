CREATE TABLE "LIFE".t_lashou_area (
  "ID" NUMBER(9) NOT NULL,
  pid NUMBER(9),
  area_name VARCHAR2(50 BYTE),
  region_code VARCHAR2(6 BYTE),
  region_name VARCHAR2(100 BYTE),
  pk_id NUMBER(9) NOT NULL,
  CONSTRAINT pk_lashou_area PRIMARY KEY (pk_id)
);
COMMENT ON COLUMN "LIFE".t_lashou_area."ID" IS 'ID：拉手地区id，省或市或区县id';
COMMENT ON COLUMN "LIFE".t_lashou_area.pid IS '父ID：拉手地区id，如果为0，表示是省';
COMMENT ON COLUMN "LIFE".t_lashou_area.area_name IS '省或市或区县名称';
COMMENT ON COLUMN "LIFE".t_lashou_area.region_code IS '区域代码';
COMMENT ON COLUMN "LIFE".t_lashou_area.region_name IS '区域名称';