CREATE TABLE "LIFE".t_gift_card_task_ext (
  "ID" NUMBER(8) NOT NULL,
  task_id NUMBER(8) NOT NULL,
  serial_no VARCHAR2(20 BYTE) NOT NULL,
  status NUMBER(1) DEFAULT 0,
  create_time VARCHAR2(14 BYTE),
  act_time VARCHAR2(14 BYTE),
  apply_id NUMBER(8),
  CONSTRAINT pk_t_gift_card_task_ext PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_gift_card_task_ext.status IS '激活状态 0：未激活，1：已激活，2：激活失败';
COMMENT ON COLUMN "LIFE".t_gift_card_task_ext.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_gift_card_task_ext.act_time IS '激活时间';
COMMENT ON COLUMN "LIFE".t_gift_card_task_ext.apply_id IS '出库申请id';