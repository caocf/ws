CREATE TABLE "LIFE".t_channel_catalog_conf (
  "ID" NUMBER(8) NOT NULL,
  item_id NUMBER(8),
  "GROUP_ID" NUMBER(4),
  update_time VARCHAR2(14 BYTE),
  order_index NUMBER(4),
  status NUMBER(1) DEFAULT 1,
  channel NUMBER(4) DEFAULT 1,
  region_code VARCHAR2(300 BYTE),
  CONSTRAINT t_channel_catalog_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_channel_catalog_conf ADD SUPPLEMENTAL LOG GROUP ggs_240336 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_channel_catalog_conf IS '产品推送分组配置表';
COMMENT ON COLUMN "LIFE".t_channel_catalog_conf."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_channel_catalog_conf.item_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_channel_catalog_conf."GROUP_ID" IS '分组类别id 1-1楼, 2-2楼推送, 3-3楼推送, 4-4楼推送,5-新品首发, 11-合约机, 12-城市特色, 13-新品推荐, 14-热卖商品';
COMMENT ON COLUMN "LIFE".t_channel_catalog_conf.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_channel_catalog_conf.order_index IS '排序';
COMMENT ON COLUMN "LIFE".t_channel_catalog_conf.status IS '状态 1-启用, 0禁用';
COMMENT ON COLUMN "LIFE".t_channel_catalog_conf.channel IS '频道 1-商城首页';
COMMENT ON COLUMN "LIFE".t_channel_catalog_conf.region_code IS '省市地区编码';