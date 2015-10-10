CREATE TABLE "LIFE".t_sys_template_info (
  "ID" NUMBER(9) NOT NULL,
  tname VARCHAR2(100 BYTE),
  "TYPE" NUMBER(2),
  dataurl VARCHAR2(200 BYTE),
  filepath VARCHAR2(200 BYTE),
  outputchartset VARCHAR2(50 BYTE),
  CONSTRAINT pk_t_sys_template_info PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_template_info ADD SUPPLEMENTAL LOG GROUP ggs_240459 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sys_template_info."TYPE" IS 'type=1，数据查询路径
type=2，jsp页面路径';
COMMENT ON COLUMN "LIFE".t_sys_template_info.dataurl IS 'type=1，此字段标识数据获取URL
type=2，此字段填入需要通过httpclient静态化的jsp页面的地址';
COMMENT ON COLUMN "LIFE".t_sys_template_info.outputchartset IS 'GBK
UTF-8
ISO-8859-1';