CREATE TABLE "LIFE".t_es_event_types (
  event_type_id NUMBER(9),
  event_type_name VARCHAR2(200 BYTE),
  event_type_description VARCHAR2(500 BYTE),
  "ID" NUMBER(9)
);
ALTER TABLE "LIFE".t_es_event_types ADD SUPPLEMENTAL LOG GROUP ggs_240343 (event_type_description, event_type_id, event_type_name, "ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_es_event_types IS '事件类型表，用于事件中心的UI界面展示';
COMMENT ON COLUMN "LIFE".t_es_event_types.event_type_id IS '事件类型ID';
COMMENT ON COLUMN "LIFE".t_es_event_types.event_type_name IS '事件类型名称，例如“用户身份验证”';
COMMENT ON COLUMN "LIFE".t_es_event_types.event_type_description IS '事件类型说明，';