CREATE TABLE "LIFE".t_sys_fee (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(120 BYTE),
  fee NUMBER(9,2),
  valid NUMBER(1),
  decr_fee VARCHAR2(100 BYTE),
  sync_gy_flag NUMBER(1),
  sync_gy_time VARCHAR2(14 BYTE),
  store_id NUMBER(9),
  settle_type VARCHAR2(1 BYTE) DEFAULT 'C',
  CONSTRAINT pk_t_sys_fee PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_fee ADD SUPPLEMENTAL LOG GROUP ggs_240521 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_fee IS '费率配置表';
COMMENT ON COLUMN "LIFE".t_sys_fee."NAME" IS '费率名称';
COMMENT ON COLUMN "LIFE".t_sys_fee.fee IS '费率';
COMMENT ON COLUMN "LIFE".t_sys_fee.valid IS '1-有效 0-无效';
COMMENT ON COLUMN "LIFE".t_sys_fee.decr_fee IS '描述';
COMMENT ON COLUMN "LIFE".t_sys_fee.sync_gy_flag IS '0-未同步 1-已同步';
COMMENT ON COLUMN "LIFE".t_sys_fee.settle_type IS 'C按商户费率结，P按商品协议结';