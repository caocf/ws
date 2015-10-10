CREATE TABLE "LIFE".t_smsbuy_order_info (
  order_id NUMBER(12) NOT NULL,
  terminal_id VARCHAR2(21 BYTE) NOT NULL,
  act_id NUMBER(12),
  sp_code VARCHAR2(21 BYTE) NOT NULL,
  update_time VARCHAR2(14 BYTE) NOT NULL,
  CONSTRAINT pk_smsbuy_order_info PRIMARY KEY (order_id)
);
ALTER TABLE "LIFE".t_smsbuy_order_info ADD SUPPLEMENTAL LOG GROUP ggs_240639 (order_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_smsbuy_order_info.order_id IS '订单编号';
COMMENT ON COLUMN "LIFE".t_smsbuy_order_info.terminal_id IS '号码';
COMMENT ON COLUMN "LIFE".t_smsbuy_order_info.act_id IS '所属活动编号';
COMMENT ON COLUMN "LIFE".t_smsbuy_order_info.sp_code IS '上行特服号';
COMMENT ON COLUMN "LIFE".t_smsbuy_order_info.update_time IS '更新时间';