CREATE TABLE "LIFE".t_gift_card (
  serial_no VARCHAR2(20 BYTE) NOT NULL,
  batch_no NUMBER(8) NOT NULL,
  member_id NUMBER(9),
  exchange_status NUMBER(1),
  exchange_time VARCHAR2(14 BYTE),
  status NUMBER(1) DEFAULT 0 NOT NULL,
  receive_time VARCHAR2(14 BYTE),
  created_time VARCHAR2(14 BYTE),
  storage_status NUMBER(1) DEFAULT 0 NOT NULL,
  payment_day VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_gift_card PRIMARY KEY (serial_no)
);
COMMENT ON TABLE "LIFE".t_gift_card IS '礼品卡表';
COMMENT ON COLUMN "LIFE".t_gift_card.serial_no IS '卡序列号，高阳给出';
COMMENT ON COLUMN "LIFE".t_gift_card.batch_no IS '批次号';
COMMENT ON COLUMN "LIFE".t_gift_card.member_id IS '兑换用户ID';
COMMENT ON COLUMN "LIFE".t_gift_card.exchange_status IS '兑换状态 0：未兑换 ，1：已兑换';
COMMENT ON COLUMN "LIFE".t_gift_card.exchange_time IS '兑换时间';
COMMENT ON COLUMN "LIFE".t_gift_card.status IS '激活状态 -1：异常，0：待激活，1：激活';
COMMENT ON COLUMN "LIFE".t_gift_card.receive_time IS '领卡时间';
COMMENT ON COLUMN "LIFE".t_gift_card.created_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_gift_card.storage_status IS '库存状态：0未入库，1已入库，2已出库';