CREATE TABLE "LIFE".t_gift_apply_out_ext (
  "ID" NUMBER(8) NOT NULL,
  apply_id NUMBER(8),
  batch_no NUMBER(8),
  num NUMBER(10),
  model_no VARCHAR2(200 BYTE),
  pay_price NUMBER(10),
  remark VARCHAR2(255 BYTE),
  CONSTRAINT pk_t_gift_apply_out_ext PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_gift_apply_out_ext.apply_id IS '出库申请编号';
COMMENT ON COLUMN "LIFE".t_gift_apply_out_ext.batch_no IS '批次号';
COMMENT ON COLUMN "LIFE".t_gift_apply_out_ext.num IS '卡数量';
COMMENT ON COLUMN "LIFE".t_gift_apply_out_ext.model_no IS '卡型号';
COMMENT ON COLUMN "LIFE".t_gift_apply_out_ext.pay_price IS '支付单价，分';
COMMENT ON COLUMN "LIFE".t_gift_apply_out_ext.remark IS '备注';