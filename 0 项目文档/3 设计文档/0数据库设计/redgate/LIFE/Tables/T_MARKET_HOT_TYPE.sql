CREATE TABLE "LIFE".t_market_hot_type (
  "ID" NUMBER(20) NOT NULL,
  p_id NUMBER(20),
  "NAME" VARCHAR2(100 BYTE),
  "SORT" NUMBER(10),
  CONSTRAINT t_market_hot_type PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_hot_type IS '团购热门分类表';
COMMENT ON COLUMN "LIFE".t_market_hot_type."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_hot_type.p_id IS '根的话该字段为0';
COMMENT ON COLUMN "LIFE".t_market_hot_type."NAME" IS '分类名';
COMMENT ON COLUMN "LIFE".t_market_hot_type."SORT" IS '排序';