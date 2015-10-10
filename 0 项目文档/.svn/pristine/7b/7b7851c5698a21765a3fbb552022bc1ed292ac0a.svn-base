CREATE TABLE "LIFE".t_sys_type_item_param (
  "ID" NUMBER(8) NOT NULL,
  type_id NUMBER(8),
  param_key VARCHAR2(20 BYTE),
  param_value VARCHAR2(80 BYTE),
  "RANK" NUMBER(8),
  param_type NUMBER(1),
  required_flag NUMBER(1),
  search_flag NUMBER(1) DEFAULT 1,
  shop_id NUMBER(9),
  shop_class NUMBER(1),
  price_rule NUMBER(1) DEFAULT 0,
  CONSTRAINT pk_t_sys_type_item_param PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_type_item_param ADD SUPPLEMENTAL LOG GROUP ggs_240462 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_type_item_param IS '分类对应商品规格参数模板表。
由商户自己创建或者后台定义的分类规格参数模板都在该表中定义';
COMMENT ON COLUMN "LIFE".t_sys_type_item_param.param_value IS '规格参数值,用分号分割';
COMMENT ON COLUMN "LIFE".t_sys_type_item_param."RANK" IS '可以根据该值排序所有参数值，否则默认根据录入顺序';
COMMENT ON COLUMN "LIFE".t_sys_type_item_param.param_type IS '参数类型
1-单选
2-复选
3-自填';
COMMENT ON COLUMN "LIFE".t_sys_type_item_param.required_flag IS '是否必填
0-no
1-必填';
COMMENT ON COLUMN "LIFE".t_sys_type_item_param.search_flag IS '是否参与检索
0-否
1-是';
COMMENT ON COLUMN "LIFE".t_sys_type_item_param.shop_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_sys_type_item_param.shop_class IS '1--业务门店
2--商户
3--渠道商';
COMMENT ON COLUMN "LIFE".t_sys_type_item_param.price_rule IS '是否参与定价规则： 0-不参与，1-参与 ';