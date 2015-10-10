CREATE TABLE "LIFE".t_sys_property (
  "ID" NUMBER(8) NOT NULL,
  "CONTENT" VARCHAR2(50 BYTE),
  "TYPE" NUMBER(8),
  CONSTRAINT pk_t_sys_property PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_property ADD SUPPLEMENTAL LOG GROUP ggs_240447 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_property IS '属性配置相关，例如颜色、尺寸等';
COMMENT ON COLUMN "LIFE".t_sys_property."TYPE" IS '0--单选
1--多选 2--录入';