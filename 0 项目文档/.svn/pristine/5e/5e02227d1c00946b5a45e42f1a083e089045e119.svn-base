CREATE TABLE "LIFE".t_market_type_rel (
  "ID" NUMBER(10) NOT NULL,
  type_id NUMBER(20),
  good_id NUMBER(20),
  "SOURCE" NUMBER(1),
  CONSTRAINT t_market_type_rel PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_type_rel IS '团购分类关联商品关系表';
COMMENT ON COLUMN "LIFE".t_market_type_rel.type_id IS '团购分类ID';
COMMENT ON COLUMN "LIFE".t_market_type_rel.good_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_market_type_rel."SOURCE" IS '数据来源 0团购  1拉手';