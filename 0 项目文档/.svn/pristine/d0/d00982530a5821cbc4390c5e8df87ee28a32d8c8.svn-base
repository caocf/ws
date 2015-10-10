CREATE TABLE "LIFE".tmp_lashou_finance_category (
  "ID" NUMBER(11) NOT NULL,
  "CATEGORY" VARCHAR2(200 BYTE) NOT NULL,
  f_id NUMBER(11) DEFAULT 0,
  "TYPE" NUMBER(1) DEFAULT 1,
  status NUMBER(3) DEFAULT 0,
  "SORT" NUMBER(4) DEFAULT 0,
  clevel NUMBER(3) DEFAULT 0,
  pid NUMBER(11),
  PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".tmp_lashou_finance_category."TYPE" IS '1服务类；2商品类';
COMMENT ON COLUMN "LIFE".tmp_lashou_finance_category.status IS '0无效；1有效';
COMMENT ON COLUMN "LIFE".tmp_lashou_finance_category."SORT" IS '排序';
COMMENT ON COLUMN "LIFE".tmp_lashou_finance_category.clevel IS '级别';
COMMENT ON COLUMN "LIFE".tmp_lashou_finance_category.pid IS '父id，0表示是一级分类';