CREATE TABLE "LIFE".t_act_order_out_log (
  act_order_id NUMBER(12) NOT NULL,
  out_order_id VARCHAR2(40 BYTE),
  boss_trans_id VARCHAR2(40 BYTE),
  total_price NUMBER(12),
  price NUMBER(12),
  out_product_id VARCHAR2(20 BYTE),
  product_id NUMBER(12),
  mobile VARCHAR2(21 BYTE),
  user_id NUMBER(12),
  out_user_id VARCHAR2(20 BYTE),
  operate_time VARCHAR2(14 BYTE) NOT NULL,
  status NUMBER(2) NOT NULL,
  src VARCHAR2(10 BYTE) NOT NULL,
  "ID" NUMBER(12) NOT NULL,
  "TYPE" VARCHAR2(20 BYTE) NOT NULL,
  discount NUMBER(12) DEFAULT 0 NOT NULL,
  num NUMBER(12) NOT NULL,
  resp_code VARCHAR2(10 BYTE) NOT NULL,
  resp_msg VARCHAR2(100 BYTE),
  "SEQUENCE" VARCHAR2(40 BYTE),
  insert_time VARCHAR2(14 BYTE) NOT NULL,
  CONSTRAINT pk_t_act_order_out_log PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_act_order_out_log IS '外部订单同步日志表';
COMMENT ON COLUMN "LIFE".t_act_order_out_log."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_act_order_out_log.discount IS '优惠金额';
COMMENT ON COLUMN "LIFE".t_act_order_out_log.num IS '数量';
COMMENT ON COLUMN "LIFE".t_act_order_out_log.resp_code IS '响应码';
COMMENT ON COLUMN "LIFE".t_act_order_out_log.resp_msg IS '响应内容';
COMMENT ON COLUMN "LIFE".t_act_order_out_log."SEQUENCE" IS '请求流水号';
COMMENT ON COLUMN "LIFE".t_act_order_out_log.insert_time IS '记录插入时间';