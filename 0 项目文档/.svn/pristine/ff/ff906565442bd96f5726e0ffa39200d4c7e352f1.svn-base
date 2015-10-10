CREATE TABLE "LIFE".t_act_order_express (
  "ID" NUMBER(19) NOT NULL,
  act_order_id NUMBER(19),
  address VARCHAR2(255 CHAR),
  cellphone_number VARCHAR2(255 CHAR),
  express_company_id NUMBER(19),
  express_company_name VARCHAR2(255 CHAR),
  express_cost NUMBER(10),
  express_no VARCHAR2(255 CHAR),
  receive_time VARCHAR2(14 CHAR),
  receiver_name VARCHAR2(255 CHAR),
  send_time VARCHAR2(14 CHAR),
  status NUMBER(10),
  status_description VARCHAR2(255 CHAR),
  status_time VARCHAR2(255 CHAR),
  telephone_number VARCHAR2(255 CHAR),
  zip_code VARCHAR2(255 CHAR),
  remark VARCHAR2(255 CHAR),
  auto_receive_time VARCHAR2(255 CHAR),
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_act_order_express ADD SUPPLEMENTAL LOG GROUP ggs_240525 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_act_order_express IS '业务订单物流信息附表';
COMMENT ON COLUMN "LIFE".t_act_order_express."ID" IS ' ';
COMMENT ON COLUMN "LIFE".t_act_order_express.act_order_id IS '订单号';
COMMENT ON COLUMN "LIFE".t_act_order_express.address IS '收货地址';
COMMENT ON COLUMN "LIFE".t_act_order_express.cellphone_number IS '手机号码';
COMMENT ON COLUMN "LIFE".t_act_order_express.express_company_id IS '物流公司';
COMMENT ON COLUMN "LIFE".t_act_order_express.express_company_name IS '物流公司名称';
COMMENT ON COLUMN "LIFE".t_act_order_express.express_cost IS '物流费（分）';
COMMENT ON COLUMN "LIFE".t_act_order_express.express_no IS '物流单号';
COMMENT ON COLUMN "LIFE".t_act_order_express.receive_time IS '收货时间';
COMMENT ON COLUMN "LIFE".t_act_order_express.receiver_name IS '收货人名称';
COMMENT ON COLUMN "LIFE".t_act_order_express.send_time IS '发货时间';
COMMENT ON COLUMN "LIFE".t_act_order_express.status IS '状态 0未发货，1已发货，2已收货';
COMMENT ON COLUMN "LIFE".t_act_order_express.status_description IS '状态说明';
COMMENT ON COLUMN "LIFE".t_act_order_express.status_time IS '状态时间';
COMMENT ON COLUMN "LIFE".t_act_order_express.telephone_number IS '固话号码';
COMMENT ON COLUMN "LIFE".t_act_order_express.zip_code IS '邮政编码';
COMMENT ON COLUMN "LIFE".t_act_order_express.remark IS '物流备注';
COMMENT ON COLUMN "LIFE".t_act_order_express.auto_receive_time IS '已发货未收货时的自动收货时间';