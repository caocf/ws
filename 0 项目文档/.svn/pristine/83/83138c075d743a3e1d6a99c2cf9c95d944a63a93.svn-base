CREATE TABLE "LIFE".t_shop_op_log (
  "ID" NUMBER(9) NOT NULL,
  user_id VARCHAR2(20 BYTE) NOT NULL,
  oper_time VARCHAR2(14 BYTE) NOT NULL,
  oper_type NUMBER(3) NOT NULL,
  module VARCHAR2(100 BYTE) NOT NULL,
  description VARCHAR2(1000 BYTE),
  result_code VARCHAR2(10 BYTE),
  ip VARCHAR2(20 BYTE),
  user_name VARCHAR2(100 BYTE),
  op_id NUMBER(16),
  shop_id NUMBER(9),
  shop_class NUMBER(1),
  shop_name VARCHAR2(100 BYTE),
  user_code VARCHAR2(20 BYTE)
);
ALTER TABLE "LIFE".t_shop_op_log ADD SUPPLEMENTAL LOG GROUP ggs_240401 (description, "ID", ip, module, oper_time, oper_type, op_id, result_code, shop_class, shop_id, shop_name, user_code, user_id, user_name) ALWAYS;
COMMENT ON COLUMN "LIFE".t_shop_op_log.oper_type IS '操作类型   1-查看 2-添加 3-修改 4-删除 5-审核  6-其他';
COMMENT ON COLUMN "LIFE".t_shop_op_log.result_code IS '操作是否成功 0-成功  非0 失败';
COMMENT ON COLUMN "LIFE".t_shop_op_log.user_name IS '用户名称';
COMMENT ON COLUMN "LIFE".t_shop_op_log.op_id IS '操作对象ID';
COMMENT ON COLUMN "LIFE".t_shop_op_log.shop_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_shop_op_log.shop_class IS '商户类别';
COMMENT ON COLUMN "LIFE".t_shop_op_log.shop_name IS '商户名';
COMMENT ON COLUMN "LIFE".t_shop_op_log.user_code IS '用户帐号';