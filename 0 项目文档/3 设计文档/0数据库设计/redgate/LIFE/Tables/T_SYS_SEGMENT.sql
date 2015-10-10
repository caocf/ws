CREATE TABLE "LIFE".t_sys_segment (
  "ID" NUMBER(9) NOT NULL,
  segment_code VARCHAR2(10 BYTE) NOT NULL,
  operator_code VARCHAR2(50 BYTE) NOT NULL,
  area_code VARCHAR2(8 BYTE) NOT NULL,
  mmsc_id VARCHAR2(20 BYTE) NOT NULL,
  CONSTRAINT pk_t_sys_segment PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_segment ADD SUPPLEMENTAL LOG GROUP ggs_240452 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_segment IS '号段表';
COMMENT ON COLUMN "LIFE".t_sys_segment."ID" IS '唯一ID，使用序列SEQ_SEGMENT';
COMMENT ON COLUMN "LIFE".t_sys_segment.segment_code IS '号段';
COMMENT ON COLUMN "LIFE".t_sys_segment.operator_code IS '中国移动ZGYD';
COMMENT ON COLUMN "LIFE".t_sys_segment.area_code IS '该号段归属地市，匹配T_SYS_AREA_INFO表中area_code';
COMMENT ON COLUMN "LIFE".t_sys_segment.mmsc_id IS '彩信中心编号，此处无用，适应c-product平台';