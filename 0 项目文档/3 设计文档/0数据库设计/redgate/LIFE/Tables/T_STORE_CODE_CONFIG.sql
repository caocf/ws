CREATE TABLE "LIFE".t_store_code_config (
  "ID" NUMBER(8) NOT NULL,
  send_channel NUMBER(3),
  send_type VARCHAR2(200 BYTE),
  into_user VARCHAR2(50 BYTE),
  into_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_store_code_config PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_store_code_config."ID" IS '流水号';
COMMENT ON COLUMN "LIFE".t_store_code_config.send_channel IS '发码渠道(0 自有码  1方正码 2  第三方（非卡密类）3 第三方（卡密类）7 河南优惠券)';
COMMENT ON COLUMN "LIFE".t_store_code_config.send_type IS '发码方式(1:按照订单发码;2:按照商品个数发码)';
COMMENT ON COLUMN "LIFE".t_store_code_config.into_user IS '导入人';
COMMENT ON COLUMN "LIFE".t_store_code_config.into_time IS '导入日期';