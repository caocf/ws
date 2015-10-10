CREATE TABLE "LIFE".t_act_order_history (
  "ID" NUMBER(19) NOT NULL,
  act_order_id NUMBER(19),
  status NUMBER(10),
  status_type NUMBER(10),
  update_data VARCHAR2(255 CHAR),
  update_descriptions VARCHAR2(255 CHAR),
  update_time VARCHAR2(255 CHAR),
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_act_order_history ADD SUPPLEMENTAL LOG GROUP ggs_240332 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_act_order_history IS '业务订单状态变更历史表';
COMMENT ON COLUMN "LIFE".t_act_order_history."ID" IS ' ';
COMMENT ON COLUMN "LIFE".t_act_order_history.act_order_id IS '订单ID';
COMMENT ON COLUMN "LIFE".t_act_order_history.status IS '状态，依据状态类型而定';
COMMENT ON COLUMN "LIFE".t_act_order_history.status_type IS '状态类型 1支付、2退款、3验证、4关闭、5物流';
COMMENT ON COLUMN "LIFE".t_act_order_history.update_data IS '更新数据';
COMMENT ON COLUMN "LIFE".t_act_order_history.update_descriptions IS '更新说明';
COMMENT ON COLUMN "LIFE".t_act_order_history.update_time IS '更新时间';