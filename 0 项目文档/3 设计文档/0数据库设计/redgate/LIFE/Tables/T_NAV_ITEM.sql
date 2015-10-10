CREATE TABLE "LIFE".t_nav_item (
  "ID" NUMBER(8) NOT NULL,
  page_id NUMBER(9),
  code VARCHAR2(50 BYTE),
  title VARCHAR2(100 BYTE),
  region_code VARCHAR2(200 BYTE),
  href VARCHAR2(300 BYTE),
  sort_no NUMBER(4),
  create_user NUMBER(9),
  create_time VARCHAR2(14 BYTE),
  update_user NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  page_code VARCHAR2(50 BYTE),
  class_name VARCHAR2(15 BYTE),
  target VARCHAR2(15 BYTE),
  p_code VARCHAR2(50 BYTE) DEFAULT 0
);
COMMENT ON TABLE "LIFE".t_nav_item IS '导航栏目表';
COMMENT ON COLUMN "LIFE".t_nav_item.page_id IS '所属网页（T_WEB_PAGE表的ID）';
COMMENT ON COLUMN "LIFE".t_nav_item.title IS '标题';
COMMENT ON COLUMN "LIFE".t_nav_item.region_code IS '省地市编码';
COMMENT ON COLUMN "LIFE".t_nav_item.create_user IS '创建人';
COMMENT ON COLUMN "LIFE".t_nav_item.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_nav_item.update_user IS '修改人';
COMMENT ON COLUMN "LIFE".t_nav_item.update_time IS '修改时间';
COMMENT ON COLUMN "LIFE".t_nav_item.page_code IS '所属网页的编码';
COMMENT ON COLUMN "LIFE".t_nav_item.class_name IS '样式名称';
COMMENT ON COLUMN "LIFE".t_nav_item.target IS '目标_self、_blank、_top、_parent';
COMMENT ON COLUMN "LIFE".t_nav_item.p_code IS '上级节点';