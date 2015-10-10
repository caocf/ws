CREATE TABLE "LIFE".szmall_order_history (
  order_id VARCHAR2(64 BYTE),
  user_id NUMBER(8),
  order_time VARCHAR2(14 BYTE),
  store_name VARCHAR2(254 BYTE),
  goods_name VARCHAR2(254 BYTE),
  totalcost NUMBER(13,2),
  productcost NUMBER(13,2),
  status VARCHAR2(2 BYTE)
);
ALTER TABLE "LIFE".szmall_order_history ADD SUPPLEMENTAL LOG GROUP ggs_245507 (goods_name, order_id, order_time, productcost, status, store_name, totalcost, user_id) ALWAYS;
COMMENT ON COLUMN "LIFE".szmall_order_history.order_id IS '订单ID';
COMMENT ON COLUMN "LIFE".szmall_order_history.user_id IS '用户ID';
COMMENT ON COLUMN "LIFE".szmall_order_history.order_time IS '下单时间';
COMMENT ON COLUMN "LIFE".szmall_order_history.store_name IS '商户名';
COMMENT ON COLUMN "LIFE".szmall_order_history.goods_name IS '商品名';
COMMENT ON COLUMN "LIFE".szmall_order_history.totalcost IS '订单总金额';
COMMENT ON COLUMN "LIFE".szmall_order_history.productcost IS '商品金额';
COMMENT ON COLUMN "LIFE".szmall_order_history.status IS '订单状态 01待处理订单，02已收款未发货，03缺货订单，04无效订单，05应退款订单，06已发货，07已到货订单，08已退款订单';