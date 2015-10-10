CREATE TABLE "LIFE".t_gift_card_cb_log (
  "ID" NUMBER(9) NOT NULL,
  create_time VARCHAR2(14 BYTE),
  serial_no VARCHAR2(20 BYTE),
  us_id NUMBER(9),
  status VARCHAR2(100 BYTE),
  "CONTENT" VARCHAR2(500 BYTE),
  remark VARCHAR2(500 BYTE),
  CONSTRAINT pk_t_gift_card_cb_log PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_gift_card_cb_log IS '礼品卡回调日志表';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_log."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_log.create_time IS '回调时间';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_log.serial_no IS '卡号';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_log.us_id IS '流水号';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_log."CONTENT" IS '报文';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_log.remark IS '备注';