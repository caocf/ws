CREATE TABLE "LIFE".t_channel_floor_conf (
  "ID" NUMBER(8) NOT NULL,
  title VARCHAR2(100 BYTE),
  floor_id NUMBER(4),
  type_id NUMBER(8),
  order_index NUMBER(4),
  channel NUMBER(4) DEFAULT 1,
  region_code VARCHAR2(200 BYTE),
  update_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_channel_floor_conf_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_channel_floor_conf ADD SUPPLEMENTAL LOG GROUP ggs_240490 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_channel_floor_conf."ID" IS '主键ID';
COMMENT ON COLUMN "LIFE".t_channel_floor_conf.title IS '楼层标题 可以为空';
COMMENT ON COLUMN "LIFE".t_channel_floor_conf.floor_id IS '楼层ID,  0-顶层, 1-一层(以此类推)';
COMMENT ON COLUMN "LIFE".t_channel_floor_conf.type_id IS '类别ID, 对应商品的分类ID';
COMMENT ON COLUMN "LIFE".t_channel_floor_conf.order_index IS '排序';
COMMENT ON COLUMN "LIFE".t_channel_floor_conf.channel IS '频道 1-商城';
COMMENT ON COLUMN "LIFE".t_channel_floor_conf.region_code IS '区域编号';
COMMENT ON COLUMN "LIFE".t_channel_floor_conf.update_time IS '更新时间';