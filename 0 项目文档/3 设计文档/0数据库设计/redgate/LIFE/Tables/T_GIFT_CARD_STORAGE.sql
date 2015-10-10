CREATE TABLE "LIFE".t_gift_card_storage (
  "ID" NUMBER(8) NOT NULL,
  batch_no NUMBER(8) NOT NULL,
  stocks NUMBER(7) DEFAULT 0 NOT NULL,
  remark VARCHAR2(200 BYTE),
  stocks_in NUMBER(7) DEFAULT 0 NOT NULL,
  stocks_out NUMBER(7) DEFAULT 0 NOT NULL,
  CONSTRAINT pk_t_gift_card_storage PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_gift_card_storage IS '礼品卡库存表';
COMMENT ON COLUMN "LIFE".t_gift_card_storage.batch_no IS '卡批次号';
COMMENT ON COLUMN "LIFE".t_gift_card_storage.stocks IS '出库量';
COMMENT ON COLUMN "LIFE".t_gift_card_storage.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_gift_card_storage.stocks_in IS '入库量';
COMMENT ON COLUMN "LIFE".t_gift_card_storage.stocks_out IS '出库量';