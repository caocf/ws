CREATE TABLE "LIFE".t_es_listener_events (
  listener_id NUMBER(9),
  event_type_id NUMBER(9),
  match_text VARCHAR2(255 BYTE),
  "ID" NUMBER(9)
);
ALTER TABLE "LIFE".t_es_listener_events ADD SUPPLEMENTAL LOG GROUP ggs_240345 (event_type_id, "ID", listener_id, match_text) ALWAYS;
COMMENT ON COLUMN "LIFE".t_es_listener_events.listener_id IS '监听者ID';
COMMENT ON COLUMN "LIFE".t_es_listener_events.event_type_id IS '消息事务ID';