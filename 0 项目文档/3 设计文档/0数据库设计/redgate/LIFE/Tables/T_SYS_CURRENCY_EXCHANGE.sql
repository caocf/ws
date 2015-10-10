CREATE TABLE "LIFE".t_sys_currency_exchange (
  "ID" NUMBER(9) NOT NULL,
  province VARCHAR2(20 BYTE) NOT NULL,
  area_code VARCHAR2(20 BYTE),
  main_currency VARCHAR2(20 BYTE) NOT NULL,
  exchange_currency VARCHAR2(20 BYTE) NOT NULL,
  exchange_value NUMBER(11,2) NOT NULL,
  remark VARCHAR2(500 BYTE),
  "OPERATOR" VARCHAR2(20 BYTE),
  input_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_sys_currency_exchange PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_currency_exchange ADD SUPPLEMENTAL LOG GROUP ggs_240431 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_currency_exchange IS '存放商城币、积分、现金之间的兑换比例';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange."ID" IS '唯一ID';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange.province IS '省份';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange.area_code IS '地市';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange.main_currency IS '主货币名称,SCORE';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange.exchange_currency IS '兑换货币名称，填写商城币COIN';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange.exchange_value IS '兑换值，即一元现金可以兑换多少积分或商城币';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange."OPERATOR" IS '操作者';
COMMENT ON COLUMN "LIFE".t_sys_currency_exchange.input_time IS '操作时间';