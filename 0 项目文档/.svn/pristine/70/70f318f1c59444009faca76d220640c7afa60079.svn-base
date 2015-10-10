CREATE TABLE "LIFE".t_channel_catalog_rcmd_conf (
  "ID" NUMBER(8) NOT NULL,
  "GROUP_ID" NUMBER(4),
  channel NUMBER(4),
  display_name VARCHAR2(20 BYTE),
  link_url VARCHAR2(500 BYTE),
  order_index NUMBER(4),
  "TYPE" NUMBER(2) DEFAULT 1,
  image_path VARCHAR2(150 BYTE),
  region_code VARCHAR2(300 BYTE),
  CONSTRAINT pk_t_channel_catalog_rcmd_conf PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_channel_catalog_rcmd_conf ADD SUPPLEMENTAL LOG GROUP ggs_240337 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_channel_catalog_rcmd_conf."GROUP_ID" IS '分组类别id
1-1楼,
2-2楼推送,
3-3楼推送,
4-4楼推送,
';
COMMENT ON COLUMN "LIFE".t_channel_catalog_rcmd_conf.channel IS '频道 1-商城首页';
COMMENT ON COLUMN "LIFE".t_channel_catalog_rcmd_conf."TYPE" IS '1-文字推送类型, 2-图片推送类型';
COMMENT ON COLUMN "LIFE".t_channel_catalog_rcmd_conf.image_path IS '图片推送类型情况的图片路径';
COMMENT ON COLUMN "LIFE".t_channel_catalog_rcmd_conf.region_code IS '省市地区编码';