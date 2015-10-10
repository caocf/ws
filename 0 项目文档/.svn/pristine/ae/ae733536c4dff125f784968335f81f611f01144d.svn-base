CREATE TABLE "LIFE".t_item_org (
  "ID" NUMBER(8) NOT NULL,
  item_mode NUMBER(1),
  type_id NUMBER(8),
  group_flag NUMBER(1),
  virtual_flag NUMBER(1),
  virtual_type NUMBER(1),
  "NAME" VARCHAR2(100 BYTE),
  short_name VARCHAR2(100 BYTE),
  warm_prompt VARCHAR2(200 BYTE),
  remark CLOB,
  status NUMBER(1),
  shop_user_id NUMBER(8),
  shop_class NUMBER(1),
  shop_id NUMBER(8),
  update_time VARCHAR2(14 BYTE),
  brand VARCHAR2(20 BYTE),
  weight NUMBER(8,2),
  create_time VARCHAR2(14 BYTE),
  create_user_id NUMBER(9),
  update_user_id NUMBER(9),
  market_content VARCHAR2(200 BYTE),
  CONSTRAINT pk_t_item_org PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_org ADD SUPPLEMENTAL LOG GROUP ggs_240658 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_item_org IS '商品基本属性存储表';
COMMENT ON COLUMN "LIFE".t_item_org.type_id IS '商品分类';
COMMENT ON COLUMN "LIFE".t_item_org.group_flag IS '是否是优惠套餐，如果是则是N多商品的组合
0-普通商品
1-优惠套餐';
COMMENT ON COLUMN "LIFE".t_item_org.virtual_flag IS '是否虚拟商品
0-否
1-是';
COMMENT ON COLUMN "LIFE".t_item_org.virtual_type IS '虚拟商品类型
1-卡密
2-直充';
COMMENT ON COLUMN "LIFE".t_item_org."NAME" IS '商品名称';
COMMENT ON COLUMN "LIFE".t_item_org.short_name IS '商品简称';
COMMENT ON COLUMN "LIFE".t_item_org.warm_prompt IS '温馨提示';
COMMENT ON COLUMN "LIFE".t_item_org.remark IS '商品介绍';
COMMENT ON COLUMN "LIFE".t_item_org.status IS '0--草稿
1--待审核
2--审核中
3--审核通过
4--审核驳回';
COMMENT ON COLUMN "LIFE".t_item_org.shop_class IS '1--业务门店
2--商户
3--渠道商';
COMMENT ON COLUMN "LIFE".t_item_org.shop_id IS '商户编号';
COMMENT ON COLUMN "LIFE".t_item_org.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_item_org.brand IS '品牌';
COMMENT ON COLUMN "LIFE".t_item_org.weight IS '重量';
COMMENT ON COLUMN "LIFE".t_item_org.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_item_org.create_user_id IS '创建人';
COMMENT ON COLUMN "LIFE".t_item_org.update_user_id IS '更新人';
COMMENT ON COLUMN "LIFE".t_item_org.market_content IS '营销语';