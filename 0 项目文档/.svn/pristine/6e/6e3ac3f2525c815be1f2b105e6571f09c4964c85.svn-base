CREATE TABLE "LIFE".t_lashou_category (
  "ID" NUMBER(9) NOT NULL,
  pid NUMBER(9),
  category_name VARCHAR2(200 BYTE),
  "TYPE" NUMBER(1),
  status NUMBER(3),
  "SORT" NUMBER(4),
  clevel NUMBER(3),
  type_id NUMBER(8)
);
COMMENT ON COLUMN "LIFE".t_lashou_category."ID" IS 'ID：拉手分类id';
COMMENT ON COLUMN "LIFE".t_lashou_category.pid IS '父ID：拉手分类id';
COMMENT ON COLUMN "LIFE".t_lashou_category.category_name IS '分类名称';
COMMENT ON COLUMN "LIFE".t_lashou_category."TYPE" IS '1-服务类，2-商品类';
COMMENT ON COLUMN "LIFE".t_lashou_category.status IS '0-无效，1-有效';
COMMENT ON COLUMN "LIFE".t_lashou_category."SORT" IS '排序';
COMMENT ON COLUMN "LIFE".t_lashou_category.clevel IS '级别';
COMMENT ON COLUMN "LIFE".t_lashou_category.type_id IS '对应商城分类表id';