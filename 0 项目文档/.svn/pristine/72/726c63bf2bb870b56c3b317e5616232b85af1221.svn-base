CREATE TABLE "LIFE".t_market_region (
  "ID" NUMBER(20) NOT NULL,
  p_id NUMBER(20),
  "NAME" VARCHAR2(100 BYTE),
  CONSTRAINT pk_t_market_region PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_region IS '营销平台地区，以树状结构存储';
COMMENT ON COLUMN "LIFE".t_market_region."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_region.p_id IS '根的话该字段为0';
COMMENT ON COLUMN "LIFE".t_market_region."NAME" IS '地区名';