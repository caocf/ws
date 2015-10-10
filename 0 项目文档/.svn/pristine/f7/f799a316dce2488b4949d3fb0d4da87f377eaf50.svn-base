CREATE TABLE "LIFE".t_act_order_batch_ship (
  "ID" NUMBER(19) NOT NULL,
  act_order_id NUMBER(19) NOT NULL,
  order_goods VARCHAR2(1000 BYTE),
  cellphone_number VARCHAR2(255 BYTE),
  receiver_name VARCHAR2(255 BYTE),
  address VARCHAR2(255 BYTE),
  express_company_name VARCHAR2(255 BYTE),
  express_no VARCHAR2(255 BYTE),
  status NUMBER(19) DEFAULT 0,
  shop_id NUMBER(19),
  CONSTRAINT pk_t_act_order_batch_ship PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship."ID" IS 'ID';
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship.act_order_id IS '订单号';
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship.order_goods IS '订单商品信息';
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship.cellphone_number IS '收件人手机号';
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship.receiver_name IS '收件人姓名';
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship.address IS '收件人地址';
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship.express_company_name IS '物流公司';
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship.express_no IS '物流单号';
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship.status IS '发货状态 0-未发货;1-已发货';
COMMENT ON COLUMN "LIFE".t_act_order_batch_ship.shop_id IS '商户ID';