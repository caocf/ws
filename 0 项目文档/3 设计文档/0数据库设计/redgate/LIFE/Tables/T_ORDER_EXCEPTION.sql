CREATE TABLE "LIFE".t_order_exception (
  "ID" NUMBER(19) NOT NULL,
  act_order_id VARCHAR2(50 CHAR),
  create_time VARCHAR2(14 CHAR),
  description VARCHAR2(500 CHAR),
  "SOURCE" VARCHAR2(100 CHAR),
  "TYPE" VARCHAR2(50 CHAR),
  user_id VARCHAR2(50 CHAR),
  pay_order_id NUMBER(19),
  status_text VARCHAR2(500 BYTE),
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_order_exception ADD SUPPLEMENTAL LOG GROUP ggs_240637 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_order_exception."ID" IS '记录ID';
COMMENT ON COLUMN "LIFE".t_order_exception.act_order_id IS '订单ID';
COMMENT ON COLUMN "LIFE".t_order_exception.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_order_exception.description IS '异常说明';
COMMENT ON COLUMN "LIFE".t_order_exception."SOURCE" IS '异常来源';
COMMENT ON COLUMN "LIFE".t_order_exception."TYPE" IS '异常类型';
COMMENT ON COLUMN "LIFE".t_order_exception.user_id IS '用户ID';
COMMENT ON COLUMN "LIFE".t_order_exception.pay_order_id IS '支付订单ID';
COMMENT ON COLUMN "LIFE".t_order_exception.status_text IS '异常状态文字';