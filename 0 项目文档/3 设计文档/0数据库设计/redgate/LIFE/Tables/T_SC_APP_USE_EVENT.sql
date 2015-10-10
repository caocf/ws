CREATE TABLE "LIFE".t_sc_app_use_event (
  "ID" NUMBER(9) NOT NULL,
  app_code VARCHAR2(50 BYTE),
  event_type_id VARCHAR2(50 BYTE),
  event_name VARCHAR2(50 BYTE),
  event_remark VARCHAR2(200 BYTE),
  CONSTRAINT pk_t_sc_app_shop_event PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sc_app_use_event ADD SUPPLEMENTAL LOG GROUP ggs_240640 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sc_app_use_event.app_code IS '应用识别码';
COMMENT ON COLUMN "LIFE".t_sc_app_use_event.event_type_id IS '事件类型编号';
COMMENT ON COLUMN "LIFE".t_sc_app_use_event.event_name IS '事件名称';
COMMENT ON COLUMN "LIFE".t_sc_app_use_event.event_remark IS '描述';