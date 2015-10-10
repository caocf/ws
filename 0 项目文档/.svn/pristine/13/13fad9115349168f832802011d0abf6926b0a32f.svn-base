CREATE TABLE "LIFE".t_market_type (
  "ID" NUMBER(20) NOT NULL,
  p_id NUMBER(20),
  "NAME" VARCHAR2(100 BYTE),
  "TYPE" NUMBER(1),
  lashou_type_id NUMBER(15) DEFAULT 0,
  status NUMBER(1) DEFAULT 0,
  isview NUMBER(1) DEFAULT 0,
  CONSTRAINT pk_t_market_type PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_type IS '分类表，营销商品分类，以树状结构存储';
COMMENT ON COLUMN "LIFE".t_market_type."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_type.p_id IS '根的话该字段为0';
COMMENT ON COLUMN "LIFE".t_market_type."NAME" IS '分类名';
COMMENT ON COLUMN "LIFE".t_market_type."TYPE" IS '1--团购类型';
COMMENT ON COLUMN "LIFE".t_market_type.lashou_type_id IS '商城商品类型表ID';
COMMENT ON COLUMN "LIFE".t_market_type.status IS '0有效 1无效';
COMMENT ON COLUMN "LIFE".t_market_type.isview IS '0可见 1不可见';