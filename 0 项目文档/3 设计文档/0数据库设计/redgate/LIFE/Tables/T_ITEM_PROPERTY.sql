CREATE TABLE "LIFE".t_item_property (
  "ID" NUMBER(8),
  item_id NUMBER(8),
  property_id NUMBER(8),
  "CONTENT" VARCHAR2(50 BYTE),
  file_id NUMBER(8),
  img_path VARCHAR2(100 BYTE)
);
ALTER TABLE "LIFE".t_item_property ADD SUPPLEMENTAL LOG GROUP ggs_240362 ("CONTENT", file_id, "ID", img_path, item_id, property_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_item_property IS '商品属性，例如颜色、尺寸等，涉及到组合和价格相关';
COMMENT ON COLUMN "LIFE".t_item_property.item_id IS '对应t_item_org表中ID字段';
COMMENT ON COLUMN "LIFE".t_item_property.property_id IS '关联t_sys_property表id';
COMMENT ON COLUMN "LIFE".t_item_property.file_id IS '对应t_sys_file表中ID';
COMMENT ON COLUMN "LIFE".t_item_property.img_path IS '图片路径';