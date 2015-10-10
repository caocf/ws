CREATE TABLE "LIFE".t_market_card_channel_type (
  "ID" NUMBER(8) NOT NULL,
  channel_name VARCHAR2(50 BYTE) NOT NULL,
  parent_id NUMBER(8) DEFAULT 0 NOT NULL,
  "SIGN" VARCHAR2(100 BYTE),
  CONSTRAINT pk_channel_type PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_card_channel_type IS '礼金券频道配置表';
COMMENT ON COLUMN "LIFE".t_market_card_channel_type."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_card_channel_type.channel_name IS '频道名';
COMMENT ON COLUMN "LIFE".t_market_card_channel_type.parent_id IS '父频道';
COMMENT ON COLUMN "LIFE".t_market_card_channel_type."SIGN" IS '标签';