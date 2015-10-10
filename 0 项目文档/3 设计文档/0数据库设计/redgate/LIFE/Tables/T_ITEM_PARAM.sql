CREATE TABLE "LIFE".t_item_param (
  "ID" NUMBER(9) NOT NULL,
  type_id NUMBER(9),
  item_id NUMBER(9),
  param_id NUMBER(9),
  param_key VARCHAR2(20 BYTE),
  param_value VARCHAR2(200 BYTE),
  "RANK" NUMBER(8),
  label VARCHAR2(200 BYTE),
  CONSTRAINT pk_item_param PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_param ADD SUPPLEMENTAL LOG GROUP ggs_240357 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_item_param.type_id IS '分类编号';
COMMENT ON COLUMN "LIFE".t_item_param.item_id IS '商品编号';
COMMENT ON COLUMN "LIFE".t_item_param.param_id IS '规格参数id (t_sys_type_item_param)';
COMMENT ON COLUMN "LIFE".t_item_param.param_key IS '规格参数名';
COMMENT ON COLUMN "LIFE".t_item_param.param_value IS '规格参数值';
COMMENT ON COLUMN "LIFE".t_item_param."RANK" IS '排序参数';
COMMENT ON COLUMN "LIFE".t_item_param.label IS 'PARAM_VALUE的自定义页面显示内容';