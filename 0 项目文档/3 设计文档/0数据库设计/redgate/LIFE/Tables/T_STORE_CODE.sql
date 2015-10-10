CREATE TABLE "LIFE".t_store_code (
  "ID" NUMBER(8) NOT NULL,
  store_id NUMBER(8),
  send_channel_id NUMBER(8),
  send_type_id NUMBER(8),
  starttime VARCHAR2(14 BYTE),
  stoptime VARCHAR2(14 BYTE),
  oper_user VARCHAR2(20 BYTE),
  opertime VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_store_code PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_store_code."ID" IS '流水号';
COMMENT ON COLUMN "LIFE".t_store_code.store_id IS '商户编号';
COMMENT ON COLUMN "LIFE".t_store_code.send_channel_id IS '发码渠道(0 自有码  1方正码 2  第三方（非卡密类）3 第三方（卡密类）)';
COMMENT ON COLUMN "LIFE".t_store_code.send_type_id IS '发码方式(1:按照订单发码;2:按照商品个数发码)';
COMMENT ON COLUMN "LIFE".t_store_code.starttime IS '生效时间';
COMMENT ON COLUMN "LIFE".t_store_code.stoptime IS '失效时间';
COMMENT ON COLUMN "LIFE".t_store_code.oper_user IS '操作人';
COMMENT ON COLUMN "LIFE".t_store_code.opertime IS '操作时间';