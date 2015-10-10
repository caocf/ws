CREATE TABLE "LIFE".t_act_order (
  "ID" NUMBER(19) NOT NULL,
  act_type NUMBER(10),
  close_description VARCHAR2(255 CHAR),
  close_status NUMBER(10),
  close_time VARCHAR2(255 CHAR),
  create_time VARCHAR2(255 CHAR),
  expire_time NUMBER(10),
  pay_description VARCHAR2(255 CHAR),
  pay_status NUMBER(10),
  pay_time VARCHAR2(255 CHAR),
  refund_description VARCHAR2(255 CHAR),
  shop_id NUMBER(19),
  shop_subject VARCHAR2(255 CHAR),
  subject VARCHAR2(255 CHAR),
  user_id NUMBER(19),
  delete_description VARCHAR2(255 CHAR),
  delete_status NUMBER(10),
  delete_time VARCHAR2(14 CHAR),
  invoice_content VARCHAR2(255 CHAR),
  invoice_subject VARCHAR2(255 CHAR),
  invoice_type NUMBER(10),
  create_source NUMBER(10),
  remark VARCHAR2(255 CHAR),
  uuid VARCHAR2(32 CHAR),
  order_type NUMBER(10),
  ext_info VARCHAR2(500 BYTE),
  pay_on_delivery NUMBER(10) DEFAULT 0,
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_act_order ADD SUPPLEMENTAL LOG GROUP ggs_240524 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_act_order IS '业务订单主表';
COMMENT ON COLUMN "LIFE".t_act_order."ID" IS 'ID';
COMMENT ON COLUMN "LIFE".t_act_order.act_type IS '业务类型，20短信购，40网站商品购买，其他值内部测试';
COMMENT ON COLUMN "LIFE".t_act_order.close_description IS '订单关闭 说明';
COMMENT ON COLUMN "LIFE".t_act_order.close_status IS '订单关闭 状态, 0未关闭，1已关闭';
COMMENT ON COLUMN "LIFE".t_act_order.close_time IS '订单关闭 时间';
COMMENT ON COLUMN "LIFE".t_act_order.create_time IS '订单创建时间';
COMMENT ON COLUMN "LIFE".t_act_order.expire_time IS '订单超时时间（秒）';
COMMENT ON COLUMN "LIFE".t_act_order.pay_description IS '订单支付 说明';
COMMENT ON COLUMN "LIFE".t_act_order.pay_status IS '订单支付 状态，0未支付，1支付中，2已支付';
COMMENT ON COLUMN "LIFE".t_act_order.pay_time IS '订单支付 时间';
COMMENT ON COLUMN "LIFE".t_act_order.refund_description IS '（暂不使用）';
COMMENT ON COLUMN "LIFE".t_act_order.shop_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_act_order.shop_subject IS '商户标题';
COMMENT ON COLUMN "LIFE".t_act_order.subject IS '订单标题';
COMMENT ON COLUMN "LIFE".t_act_order.user_id IS '用户ID';
COMMENT ON COLUMN "LIFE".t_act_order.delete_description IS '订单删除 说明';
COMMENT ON COLUMN "LIFE".t_act_order.delete_status IS '订单删除 状态，0未删除，1已删除';
COMMENT ON COLUMN "LIFE".t_act_order.delete_time IS '订单删除 时间';
COMMENT ON COLUMN "LIFE".t_act_order.invoice_content IS '发票内容';
COMMENT ON COLUMN "LIFE".t_act_order.invoice_subject IS '发票标题';
COMMENT ON COLUMN "LIFE".t_act_order.invoice_type IS '发票类型 0不要发票，1普通发票，2增值税发票';
COMMENT ON COLUMN "LIFE".t_act_order.create_source IS '创建来源， 1网站，2WAP，3客户端，4短信，5外部接口，0其他';
COMMENT ON COLUMN "LIFE".t_act_order.remark IS '订单备注';
COMMENT ON COLUMN "LIFE".t_act_order.uuid IS '唯一号';
COMMENT ON COLUMN "LIFE".t_act_order.order_type IS '订单类型 0普通 1竞拍 2秒杀 1000礼品卡 10劳保用品 20拉手团购 30永乐演出票 40电影票 50河南积分商品  51河南优惠券 60电子券';
COMMENT ON COLUMN "LIFE".t_act_order.ext_info IS '订单扩展信息。用于下单时填写订单特有信息， 例如短信购的活动ID，营销活动的活动ID';
COMMENT ON COLUMN "LIFE".t_act_order.pay_on_delivery IS '是否是货到付款，0否1是';