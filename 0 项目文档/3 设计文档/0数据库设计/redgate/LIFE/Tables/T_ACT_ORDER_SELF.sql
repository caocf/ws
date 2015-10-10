CREATE TABLE "LIFE".t_act_order_self (
  "ID" NUMBER(19) NOT NULL,
  act_order_id NUMBER(19),
  self_get NUMBER(1) DEFAULT 0,
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_act_order_self ADD SUPPLEMENTAL LOG GROUP ggs_240622 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_act_order_self IS '订单自取记录表';
COMMENT ON COLUMN "LIFE".t_act_order_self.act_order_id IS '订单ID';
COMMENT ON COLUMN "LIFE".t_act_order_self.self_get IS '自取标示，0：未取，1：已取';