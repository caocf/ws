CREATE TABLE "LIFE".t_act_order_out (
  act_order_id NUMBER(12) NOT NULL,
  out_order_id VARCHAR2(40 BYTE) NOT NULL,
  boss_trans_id VARCHAR2(40 BYTE),
  total_price NUMBER(12) NOT NULL,
  price NUMBER(12),
  out_product_id VARCHAR2(20 BYTE),
  product_id NUMBER(12),
  mobile VARCHAR2(21 BYTE),
  user_id NUMBER(12),
  out_user_id VARCHAR2(20 BYTE),
  operate_time VARCHAR2(14 BYTE) NOT NULL,
  status NUMBER(2) NOT NULL,
  src VARCHAR2(10 BYTE) NOT NULL,
  "TYPE" VARCHAR2(10 BYTE) NOT NULL,
  discount NUMBER(12) DEFAULT 0 NOT NULL,
  num NUMBER(12) NOT NULL,
  update_time VARCHAR2(14 BYTE) NOT NULL,
  CONSTRAINT pk_t_act_order_out PRIMARY KEY (act_order_id)
);
COMMENT ON TABLE "LIFE".t_act_order_out IS '外部订单表';
COMMENT ON COLUMN "LIFE".t_act_order_out.act_order_id IS '业务订单号';
COMMENT ON COLUMN "LIFE".t_act_order_out.out_order_id IS '外部订单号';
COMMENT ON COLUMN "LIFE".t_act_order_out.boss_trans_id IS 'Boss流水号';
COMMENT ON COLUMN "LIFE".t_act_order_out.total_price IS '总金额，单位分';
COMMENT ON COLUMN "LIFE".t_act_order_out.price IS '单价，单位分';
COMMENT ON COLUMN "LIFE".t_act_order_out.out_product_id IS '外部商品编号';
COMMENT ON COLUMN "LIFE".t_act_order_out.product_id IS '平台商品编号';
COMMENT ON COLUMN "LIFE".t_act_order_out.mobile IS '号码';
COMMENT ON COLUMN "LIFE".t_act_order_out.user_id IS '平台用户编号';
COMMENT ON COLUMN "LIFE".t_act_order_out.out_user_id IS '外部用户编号';
COMMENT ON COLUMN "LIFE".t_act_order_out.operate_time IS '操作时间';
COMMENT ON COLUMN "LIFE".t_act_order_out.status IS '1 已支付  2 申请退码退款 
3 已退码退款 0 订单关闭
';
COMMENT ON COLUMN "LIFE".t_act_order_out.src IS '来源，out=外部同步给我们，platform=我们同步给外部';
COMMENT ON COLUMN "LIFE".t_act_order_out."TYPE" IS '类型，henan=河南商家联盟';
COMMENT ON COLUMN "LIFE".t_act_order_out.discount IS '优惠金额，单位分';
COMMENT ON COLUMN "LIFE".t_act_order_out.num IS '购买数量';
COMMENT ON COLUMN "LIFE".t_act_order_out.update_time IS '更新时间';