CREATE TABLE "LIFE".t_wx_bm_order (
  "ID" NUMBER(8) NOT NULL,
  act_order_id NUMBER(19),
  job_no VARCHAR2(30 BYTE),
  item_id NUMBER(8),
  item_number NUMBER(4),
  create_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_wx_bm_order PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_wx_bm_order IS '营业厅代购订单表';
COMMENT ON COLUMN "LIFE".t_wx_bm_order.act_order_id IS '订单中心的业务订单ID';
COMMENT ON COLUMN "LIFE".t_wx_bm_order.job_no IS '下单营业员工号';
COMMENT ON COLUMN "LIFE".t_wx_bm_order.item_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_wx_bm_order.item_number IS '商品数量';
COMMENT ON COLUMN "LIFE".t_wx_bm_order.create_time IS '发起订单时间';