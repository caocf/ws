CREATE TABLE "LIFE".t_monitor_item_cnt (
  "ID" NUMBER(9) NOT NULL,
  store_id NUMBER(9),
  item_cnt NUMBER(10) DEFAULT 0,
  log_time VARCHAR2(14 BYTE) DEFAULT TO_CHAR(SYSDATE, 'yyyyMMddHHmmss'),
  CONSTRAINT pk_t_monitor_item_cnt PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_monitor_item_cnt IS '业务监控商户商品上架数量表';
COMMENT ON COLUMN "LIFE".t_monitor_item_cnt."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_monitor_item_cnt.store_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_monitor_item_cnt.item_cnt IS '商品数量';
COMMENT ON COLUMN "LIFE".t_monitor_item_cnt.log_time IS '记录时间';