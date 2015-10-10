CREATE TABLE "LIFE".t_gift_card_storage_ext (
  serial_no VARCHAR2(20 BYTE) NOT NULL,
  batch_no NUMBER(8) NOT NULL,
  storage_status NUMBER(1) NOT NULL,
  operated_time VARCHAR2(14 BYTE),
  operated_user_id NUMBER(8),
  "ID" NUMBER(8) NOT NULL,
  apply_id NUMBER(8),
  CONSTRAINT stockprimary PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_gift_card_storage_ext IS '礼品卡库存扩展表';
COMMENT ON COLUMN "LIFE".t_gift_card_storage_ext.serial_no IS '礼品卡序列号';
COMMENT ON COLUMN "LIFE".t_gift_card_storage_ext.batch_no IS '批次号';
COMMENT ON COLUMN "LIFE".t_gift_card_storage_ext.storage_status IS '出入库状态 0：出库，1：入库';
COMMENT ON COLUMN "LIFE".t_gift_card_storage_ext.operated_time IS '操作时间';
COMMENT ON COLUMN "LIFE".t_gift_card_storage_ext.operated_user_id IS '操作人';
COMMENT ON COLUMN "LIFE".t_gift_card_storage_ext.apply_id IS '出库申请id';