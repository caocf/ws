CREATE TABLE "LIFE".t_market_right (
  "ID" NUMBER(8) NOT NULL,
  activity_name VARCHAR2(500 BYTE) NOT NULL,
  activity_id VARCHAR2(50 BYTE) DEFAULT 0 NOT NULL,
  "TYPE" VARCHAR2(50 BYTE) DEFAULT 0 NOT NULL,
  region_code VARCHAR2(200 BYTE),
  region_name VARCHAR2(200 BYTE),
  activity_type VARCHAR2(50 BYTE) DEFAULT 1 NOT NULL,
  create_time VARCHAR2(20 BYTE) NOT NULL,
  CONSTRAINT pk_right_id PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_right IS '营销活动用户权限控制';
COMMENT ON COLUMN "LIFE".t_market_right."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_right.activity_name IS '营销活动名称';
COMMENT ON COLUMN "LIFE".t_market_right.activity_id IS '营销活动编号';
COMMENT ON COLUMN "LIFE".t_market_right."TYPE" IS '限制类型 0：地区限制，1：目标库限制';
COMMENT ON COLUMN "LIFE".t_market_right.region_code IS '可以参加的地区';
COMMENT ON COLUMN "LIFE".t_market_right.region_name IS '可以参加的目标库';
COMMENT ON COLUMN "LIFE".t_market_right.activity_type IS '活动类型:营销活动 1、竞拍  2 、秒杀 3、团购 4';
COMMENT ON COLUMN "LIFE".t_market_right.create_time IS '创建时间';