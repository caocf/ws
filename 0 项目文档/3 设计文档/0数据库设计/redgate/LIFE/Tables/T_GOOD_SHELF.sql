CREATE TABLE "LIFE".t_good_shelf (
  "ID" NUMBER(9) NOT NULL,
  title VARCHAR2(50 BYTE),
  pid NUMBER(9),
  level_flag NUMBER(1),
  order_index NUMBER(3),
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  shop_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  img_url VARCHAR2(100 BYTE),
  CONSTRAINT t_good_shelf_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_good_shelf ADD SUPPLEMENTAL LOG GROUP ggs_240349 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_good_shelf IS '货架分类表';
COMMENT ON COLUMN "LIFE".t_good_shelf.title IS '货架分类名称';
COMMENT ON COLUMN "LIFE".t_good_shelf.pid IS '父货架ID';
COMMENT ON COLUMN "LIFE".t_good_shelf.level_flag IS '货架层级（暂只支持三级:1,2,3）';
COMMENT ON COLUMN "LIFE".t_good_shelf.order_index IS '排序';
COMMENT ON COLUMN "LIFE".t_good_shelf.shop_class IS '商户分类：1--业务门店
2--商户
3--渠道商';
COMMENT ON COLUMN "LIFE".t_good_shelf.shop_id IS '商户编号';
COMMENT ON COLUMN "LIFE".t_good_shelf.shop_user_id IS '创建商户账号';
COMMENT ON COLUMN "LIFE".t_good_shelf.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_good_shelf.img_url IS '图片地址(相对地址) ';