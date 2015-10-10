CREATE TABLE "LIFE".t_channel_type (
  "ID" NUMBER(20) NOT NULL,
  type_id NUMBER(20) NOT NULL,
  display_name VARCHAR2(100 BYTE),
  region_code VARCHAR2(100 BYTE),
  channel NUMBER(4) DEFAULT 1,
  CONSTRAINT pk_t_channel_type PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_channel_type ADD SUPPLEMENTAL LOG GROUP ggs_240339 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_channel_type."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_channel_type.type_id IS '商品分类ID';
COMMENT ON COLUMN "LIFE".t_channel_type.display_name IS '显示名称';
COMMENT ON COLUMN "LIFE".t_channel_type.region_code IS '省市地区编码';
COMMENT ON COLUMN "LIFE".t_channel_type.channel IS '频道 1-商城首页';