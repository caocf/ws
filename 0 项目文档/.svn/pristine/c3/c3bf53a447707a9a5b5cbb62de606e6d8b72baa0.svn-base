CREATE TABLE "LIFE".t_es_listeners (
  listener_id NUMBER(9) NOT NULL,
  listener_name VARCHAR2(200 BYTE),
  listener_description VARCHAR2(500 BYTE),
  event_send_type NUMBER(3) DEFAULT 0,
  event_send_address VARCHAR2(200 BYTE),
  event_send_concurrency NUMBER(3) DEFAULT 1,
  CONSTRAINT pk_es_listeners PRIMARY KEY (listener_id)
);
ALTER TABLE "LIFE".t_es_listeners ADD SUPPLEMENTAL LOG GROUP ggs_240344 (listener_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_es_listeners IS '事件监听者';
COMMENT ON COLUMN "LIFE".t_es_listeners.listener_id IS '事件监听者ID';
COMMENT ON COLUMN "LIFE".t_es_listeners.listener_name IS '事件监听者名称';
COMMENT ON COLUMN "LIFE".t_es_listeners.listener_description IS '事件监听者说明';
COMMENT ON COLUMN "LIFE".t_es_listeners.event_send_type IS '事件发送方式，0：Servlet';
COMMENT ON COLUMN "LIFE".t_es_listeners.event_send_address IS '事件发送地址，type=0:URL地址';
COMMENT ON COLUMN "LIFE".t_es_listeners.event_send_concurrency IS '发送线程数量';