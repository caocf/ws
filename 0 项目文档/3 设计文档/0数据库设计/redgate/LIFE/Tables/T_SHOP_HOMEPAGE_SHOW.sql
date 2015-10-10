CREATE TABLE "LIFE".t_shop_homepage_show (
  "ID" NUMBER(9) NOT NULL,
  title VARCHAR2(100 BYTE),
  shelf_id NUMBER(9),
  good_num NUMBER(3),
  status NUMBER(1),
  order_index NUMBER(1),
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  shop_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  CONSTRAINT t_shop_homepage_show_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_homepage_show ADD SUPPLEMENTAL LOG GROUP ggs_240397 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop_homepage_show IS '首页商品展示配置表';
COMMENT ON COLUMN "LIFE".t_shop_homepage_show.title IS '标题';
COMMENT ON COLUMN "LIFE".t_shop_homepage_show.shelf_id IS '货架ID';
COMMENT ON COLUMN "LIFE".t_shop_homepage_show.good_num IS '商品数量';
COMMENT ON COLUMN "LIFE".t_shop_homepage_show.status IS '状态：0，停用；1，启用';
COMMENT ON COLUMN "LIFE".t_shop_homepage_show.order_index IS '排序';
COMMENT ON COLUMN "LIFE".t_shop_homepage_show.shop_class IS '商户分类：1--业务门店
2--结算商户
3--渠道商';
COMMENT ON COLUMN "LIFE".t_shop_homepage_show.shop_id IS '商户编号';
COMMENT ON COLUMN "LIFE".t_shop_homepage_show.shop_user_id IS '商户账号ID';
COMMENT ON COLUMN "LIFE".t_shop_homepage_show.update_time IS '更新时间';