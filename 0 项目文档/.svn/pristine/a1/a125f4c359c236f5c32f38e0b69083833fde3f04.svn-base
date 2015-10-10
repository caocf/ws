CREATE TABLE "LIFE".t_item_lashou (
  "ID" NUMBER(9) NOT NULL,
  item_id NUMBER(9),
  vender_team_id VARCHAR2(20 BYTE),
  sms_title VARCHAR2(255 BYTE),
  first_catalog NUMBER(9),
  second_catalog NUMBER(9),
  third_catalog NUMBER(9),
  province_id NUMBER(9),
  city_id NUMBER(9),
  brand VARCHAR2(254 BYTE),
  stock_mode NUMBER(1),
  refund_strategy NUMBER(1),
  max_sale NUMBER(9),
  create_time VARCHAR2(14 BYTE),
  update_time VARCHAR2(14 BYTE),
  main_image VARCHAR2(1024 BYTE),
  PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_item_lashou."ID" IS '序列：seq_item_lashou';
COMMENT ON COLUMN "LIFE".t_item_lashou.item_id IS '商品表t_item_sale的id';
COMMENT ON COLUMN "LIFE".t_item_lashou.vender_team_id IS '拉手商品id：VenderTeamId';
COMMENT ON COLUMN "LIFE".t_item_lashou.sms_title IS '短信标题：SmsTitle';
COMMENT ON COLUMN "LIFE".t_item_lashou.first_catalog IS '一级分类id：FirstCatalog';
COMMENT ON COLUMN "LIFE".t_item_lashou.second_catalog IS '二级分类id：SecondCatalog';
COMMENT ON COLUMN "LIFE".t_item_lashou.third_catalog IS '三级分类id：ThirdCatalog';
COMMENT ON COLUMN "LIFE".t_item_lashou.province_id IS '省份id：ProvinceId';
COMMENT ON COLUMN "LIFE".t_item_lashou.city_id IS '城市id：CityId';
COMMENT ON COLUMN "LIFE".t_item_lashou.brand IS '品牌：Brand';
COMMENT ON COLUMN "LIFE".t_item_lashou.stock_mode IS '库存模式：StockMode，0：共享，1：独占';
COMMENT ON COLUMN "LIFE".t_item_lashou.refund_strategy IS '退款策略：RefundStrategy，0：不支持，1：支持退款';
COMMENT ON COLUMN "LIFE".t_item_lashou.max_sale IS '总库存：MaxSale';
COMMENT ON COLUMN "LIFE".t_item_lashou.create_time IS '创建时间，即拉手第一次上报商品的时间';
COMMENT ON COLUMN "LIFE".t_item_lashou.update_time IS '修改时间，即收到拉手更新商品的时间';
COMMENT ON COLUMN "LIFE".t_item_lashou.main_image IS '商品主图URL';