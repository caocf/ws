CREATE TABLE "LIFE".t_channel_pic_conf (
  "ID" NUMBER(8) NOT NULL,
  pic_path VARCHAR2(150 BYTE),
  pic_alt VARCHAR2(150 BYTE),
  pic_index NUMBER(4),
  link_url VARCHAR2(150 BYTE),
  postion NUMBER(2),
  channel NUMBER(1) DEFAULT 1,
  update_time VARCHAR2(14 BYTE),
  region_code VARCHAR2(300 BYTE),
  CONSTRAINT t_channel_pic_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_channel_pic_conf ADD SUPPLEMENTAL LOG GROUP ggs_240338 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_channel_pic_conf IS '首页图片轮换表';
COMMENT ON COLUMN "LIFE".t_channel_pic_conf."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_channel_pic_conf.pic_path IS '图片路径';
COMMENT ON COLUMN "LIFE".t_channel_pic_conf.pic_alt IS '图片名称';
COMMENT ON COLUMN "LIFE".t_channel_pic_conf.pic_index IS '图片排序';
COMMENT ON COLUMN "LIFE".t_channel_pic_conf.link_url IS '链接';
COMMENT ON COLUMN "LIFE".t_channel_pic_conf.postion IS '位置 0-标识最顶部, 往下依次为1楼,2,3,4';
COMMENT ON COLUMN "LIFE".t_channel_pic_conf.channel IS '频道1-商城';
COMMENT ON COLUMN "LIFE".t_channel_pic_conf.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_channel_pic_conf.region_code IS '省市地区编码';