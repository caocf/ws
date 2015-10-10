CREATE TABLE "LIFE".t_order_refund (
  "ID" NUMBER(9) NOT NULL,
  order_id NUMBER(19),
  store_id NUMBER(9),
  status NUMBER(1),
  refund_fee NUMBER(9,2),
  create_time VARCHAR2(14 BYTE),
  user_id NUMBER(9),
  reason VARCHAR2(300 BYTE),
  shop_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  shop_remark VARCHAR2(300 BYTE),
  audit_user_id NUMBER(9),
  audit_time VARCHAR2(14 BYTE),
  audit_remark VARCHAR2(300 BYTE),
  deal_time VARCHAR2(14 BYTE),
  cash_fee NUMBER(9,2),
  coin_fee NUMBER(9,2),
  total_code NUMBER(9),
  success_code NUMBER(9),
  order_record_cash_id NUMBER(19),
  order_record_coin_id NUMBER(19),
  order_record_score_id NUMBER(19),
  score_fee NUMBER(9,2),
  phone_fee NUMBER(9,2),
  order_record_phone_id NUMBER(19),
  refund_type NUMBER(1),
  CONSTRAINT pk_t_order_refund PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_order_refund ADD SUPPLEMENTAL LOG GROUP ggs_240389 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_order_refund.order_id IS '订单id';
COMMENT ON COLUMN "LIFE".t_order_refund.store_id IS '商户id';
COMMENT ON COLUMN "LIFE".t_order_refund.status IS '状态。1等待商户确认2等待审核3商户拒绝确认4等待系统退款 5审核失败6退款成功 7退款失败 8退款已通知9系统退款中';
COMMENT ON COLUMN "LIFE".t_order_refund.refund_fee IS '实际退款金额';
COMMENT ON COLUMN "LIFE".t_order_refund.create_time IS '申请时间';
COMMENT ON COLUMN "LIFE".t_order_refund.user_id IS '操作人';
COMMENT ON COLUMN "LIFE".t_order_refund.reason IS '申请原因';
COMMENT ON COLUMN "LIFE".t_order_refund.shop_user_id IS '商户操作人';
COMMENT ON COLUMN "LIFE".t_order_refund.update_time IS '商户操作时间';
COMMENT ON COLUMN "LIFE".t_order_refund.shop_remark IS '商户说明';
COMMENT ON COLUMN "LIFE".t_order_refund.audit_user_id IS '审核人';
COMMENT ON COLUMN "LIFE".t_order_refund.audit_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_order_refund.audit_remark IS '审核说明';
COMMENT ON COLUMN "LIFE".t_order_refund.deal_time IS '处理时间';
COMMENT ON COLUMN "LIFE".t_order_refund.cash_fee IS '实际退款现金';
COMMENT ON COLUMN "LIFE".t_order_refund.coin_fee IS '实际退款商城币';
COMMENT ON COLUMN "LIFE".t_order_refund.total_code IS '拟退码数量';
COMMENT ON COLUMN "LIFE".t_order_refund.success_code IS '退码成功数量';
COMMENT ON COLUMN "LIFE".t_order_refund.order_record_cash_id IS '成功退款的现金id，0表示调用接口异常';
COMMENT ON COLUMN "LIFE".t_order_refund.order_record_coin_id IS '成功退款的商城币id，0表示调用接口异常';
COMMENT ON COLUMN "LIFE".t_order_refund.order_record_score_id IS '成功退款的积分id，0表示调用接口异常';
COMMENT ON COLUMN "LIFE".t_order_refund.score_fee IS '实际退款积分';
COMMENT ON COLUMN "LIFE".t_order_refund.phone_fee IS '实际退款话费';
COMMENT ON COLUMN "LIFE".t_order_refund.order_record_phone_id IS '成功退款的话费id，0表示调用接口异常';
COMMENT ON COLUMN "LIFE".t_order_refund.refund_type IS '退款单类型，1平台自己，2拉手，3永乐';