CREATE TABLE "LIFE".t_sys_ad (
  "ID" NUMBER(9) NOT NULL,
  ad_name VARCHAR2(100 BYTE),
  ad_type NUMBER(2),
  ad_flag NUMBER(2) DEFAULT 0,
  "LINK" VARCHAR2(200 BYTE),
  linkman VARCHAR2(100 BYTE),
  "CONTENT" VARCHAR2(200 BYTE),
  status NUMBER(2),
  start_time VARCHAR2(14 BYTE),
  valid_time VARCHAR2(14 BYTE),
  click_cnt NUMBER(9) DEFAULT 0,
  view_cnt NUMBER(9) DEFAULT 0,
  create_time VARCHAR2(14 BYTE),
  create_user NUMBER(9),
  position_id NUMBER(9),
  CONSTRAINT pk_t_sys_ad PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_ad ADD SUPPLEMENTAL LOG GROUP ggs_240426 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sys_ad.ad_name IS '广告名称';
COMMENT ON COLUMN "LIFE".t_sys_ad.ad_type IS '广告类型
1-图片
2-文字
3-脚本';
COMMENT ON COLUMN "LIFE".t_sys_ad.ad_flag IS '广告类别
0-内部广告
1-外部广告';
COMMENT ON COLUMN "LIFE".t_sys_ad."LINK" IS '广告链接';
COMMENT ON COLUMN "LIFE".t_sys_ad.linkman IS '广告负责人';
COMMENT ON COLUMN "LIFE".t_sys_ad."CONTENT" IS '广告内容
如果是图片广告，则为图片存储路径';
COMMENT ON COLUMN "LIFE".t_sys_ad.status IS '广告状态
0-未审核
1-审核通过
2-审核不通过
';
COMMENT ON COLUMN "LIFE".t_sys_ad.start_time IS '开始时间';
COMMENT ON COLUMN "LIFE".t_sys_ad.valid_time IS '有效期';
COMMENT ON COLUMN "LIFE".t_sys_ad.click_cnt IS '点击次数';
COMMENT ON COLUMN "LIFE".t_sys_ad.view_cnt IS '展现次数';
COMMENT ON COLUMN "LIFE".t_sys_ad.create_time IS '创建日期';
COMMENT ON COLUMN "LIFE".t_sys_ad.create_user IS '创建人';
COMMENT ON COLUMN "LIFE".t_sys_ad.position_id IS '广告位置';