CREATE TABLE "LIFE".t_gift_card_task (
  "ID" NUMBER(8) NOT NULL,
  resources NUMBER(1),
  status NUMBER(1) DEFAULT 0,
  batch_no NUMBER(8),
  file_path VARCHAR2(200 BYTE),
  serial_start_no VARCHAR2(20 BYTE),
  serial_end_no VARCHAR2(20 BYTE),
  remark VARCHAR2(200 BYTE),
  create_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_gift_card_task PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_gift_card_task.resources IS '任务创建方式  0：按批次号导入，1：文件导入，2：起止序列号导入';
COMMENT ON COLUMN "LIFE".t_gift_card_task.status IS '激活状态：0未处理，1：处理中，2：已处理';
COMMENT ON COLUMN "LIFE".t_gift_card_task.file_path IS '文件路径';
COMMENT ON COLUMN "LIFE".t_gift_card_task.serial_start_no IS '起始序列号';
COMMENT ON COLUMN "LIFE".t_gift_card_task.serial_end_no IS '结束序列号';
COMMENT ON COLUMN "LIFE".t_gift_card_task.remark IS '备注';