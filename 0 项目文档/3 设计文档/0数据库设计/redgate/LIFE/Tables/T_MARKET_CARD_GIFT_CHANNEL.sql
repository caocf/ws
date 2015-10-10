CREATE TABLE "LIFE".t_market_card_gift_channel (
  channel_id NUMBER(8) NOT NULL,
  "ID" NUMBER(8) NOT NULL,
  card_id NUMBER(8),
  CONSTRAINT pk_gift_channel PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_market_card_gift_channel IS '礼金券 使用渠道';
COMMENT ON COLUMN "LIFE".t_market_card_gift_channel.channel_id IS '渠道编号';
COMMENT ON COLUMN "LIFE".t_market_card_gift_channel."ID" IS 'ID';
COMMENT ON COLUMN "LIFE".t_market_card_gift_channel.card_id IS '礼金券编号';