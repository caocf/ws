CREATE TABLE "LIFE".t_gift_card_lc_log (
  "ID" NUMBER(9) NOT NULL,
  serial_no VARCHAR2(20 BYTE),
  user_name VARCHAR2(50 BYTE),
  terminal_id VARCHAR2(50 BYTE),
  "TYPE" NUMBER(2),
  create_date VARCHAR2(14 BYTE),
  PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_gift_card_lc_log IS '礼品卡挂失，换卡记录表';
COMMENT ON COLUMN "LIFE".t_gift_card_lc_log."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_gift_card_lc_log.serial_no IS '卡号';
COMMENT ON COLUMN "LIFE".t_gift_card_lc_log.user_name IS '客户名';
COMMENT ON COLUMN "LIFE".t_gift_card_lc_log.terminal_id IS '手机号';
COMMENT ON COLUMN "LIFE".t_gift_card_lc_log."TYPE" IS '类型：1是挂失，2是换卡';