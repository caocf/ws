CREATE TABLE "LIFE".t_sc_app_use_log (
  "ID" NUMBER(9) NOT NULL,
  app_code VARCHAR2(50 BYTE),
  app_name VARCHAR2(200 BYTE),
  product_id VARCHAR2(50 BYTE),
  product_name VARCHAR2(200 BYTE),
  shop_id NUMBER(9),
  event_type_id VARCHAR2(50 BYTE),
  event_time VARCHAR2(14 BYTE),
  event_value VARCHAR2(200 BYTE),
  CONSTRAINT pk_t_sc_app_use_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sc_app_use_log ADD SUPPLEMENTAL LOG GROUP ggs_240638 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sc_app_use_log."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_sc_app_use_log.app_code IS '应用识别码';
COMMENT ON COLUMN "LIFE".t_sc_app_use_log.app_name IS '应用名称';
COMMENT ON COLUMN "LIFE".t_sc_app_use_log.product_id IS '产品ID';
COMMENT ON COLUMN "LIFE".t_sc_app_use_log.product_name IS '产品名称';
COMMENT ON COLUMN "LIFE".t_sc_app_use_log.shop_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_sc_app_use_log.event_type_id IS '事件类型编号';
COMMENT ON COLUMN "LIFE".t_sc_app_use_log.event_time IS '事件时间';
COMMENT ON COLUMN "LIFE".t_sc_app_use_log.event_value IS '事件值';