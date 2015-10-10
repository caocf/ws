CREATE TABLE "LIFE".t_sys_landmark (
  "ID" NUMBER(9) NOT NULL,
  area_code VARCHAR2(20 BYTE),
  regon_id NUMBER(9),
  landmark_name VARCHAR2(100 BYTE),
  map_long VARCHAR2(20 BYTE),
  map_dim VARCHAR2(20 BYTE),
  flag NUMBER(1) DEFAULT 1,
  CONSTRAINT pk_t_sys_landmark PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_landmark ADD SUPPLEMENTAL LOG GROUP ggs_240440 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_landmark IS '地标管理';
COMMENT ON COLUMN "LIFE".t_sys_landmark."ID" IS 'id';
COMMENT ON COLUMN "LIFE".t_sys_landmark.area_code IS '地市编码';
COMMENT ON COLUMN "LIFE".t_sys_landmark.regon_id IS '区域id';
COMMENT ON COLUMN "LIFE".t_sys_landmark.landmark_name IS '地标名称';
COMMENT ON COLUMN "LIFE".t_sys_landmark.map_long IS '坐标long';
COMMENT ON COLUMN "LIFE".t_sys_landmark.map_dim IS '坐标dim';
COMMENT ON COLUMN "LIFE".t_sys_landmark.flag IS '是否显示1-显示 0-不显示 ';