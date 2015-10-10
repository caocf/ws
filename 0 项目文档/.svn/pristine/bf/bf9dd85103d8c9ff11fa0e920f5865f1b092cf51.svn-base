CREATE TABLE "LIFE".t_sys_province (
  "ID" NUMBER(9) NOT NULL,
  prov_name VARCHAR2(30 BYTE) NOT NULL,
  short_name VARCHAR2(10 BYTE),
  seq_val NUMBER(3) DEFAULT 999 NOT NULL
);
ALTER TABLE "LIFE".t_sys_province ADD SUPPLEMENTAL LOG GROUP ggs_240448 ("ID", prov_name, seq_val, short_name) ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_province IS '省信息表';
COMMENT ON COLUMN "LIFE".t_sys_province."ID" IS '省ID';
COMMENT ON COLUMN "LIFE".t_sys_province.prov_name IS '省名称';
COMMENT ON COLUMN "LIFE".t_sys_province.short_name IS '省简称';
COMMENT ON COLUMN "LIFE".t_sys_province.seq_val IS '排序值，排序使用';