CREATE TABLE "LIFE".t_boss_request (
  "ID" NUMBER(9) NOT NULL,
  terminal_id VARCHAR2(21 BYTE) NOT NULL,
  area_code VARCHAR2(10 BYTE) NOT NULL,
  "TYPE" VARCHAR2(2 BYTE) NOT NULL,
  send_date VARCHAR2(14 BYTE),
  status NUMBER(1) DEFAULT 0 NOT NULL,
  "ERROR" VARCHAR2(500 BYTE),
  product_id VARCHAR2(10 BYTE) NOT NULL,
  req_src VARCHAR2(50 BYTE) NOT NULL,
  insert_time VARCHAR2(14 BYTE) NOT NULL,
  CONSTRAINT pk_t_boss_request PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_boss_request ADD SUPPLEMENTAL LOG GROUP ggs_240624 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_boss_request."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_boss_request.terminal_id IS '号码';
COMMENT ON COLUMN "LIFE".t_boss_request.area_code IS '地区（NJ、SZ等）';
COMMENT ON COLUMN "LIFE".t_boss_request."TYPE" IS '处理类型，01开通，02关闭';
COMMENT ON COLUMN "LIFE".t_boss_request.send_date IS '发送时间';
COMMENT ON COLUMN "LIFE".t_boss_request.status IS '状态，0未处理，1处理成功，2处理失败，3已处理待发送';
COMMENT ON COLUMN "LIFE".t_boss_request."ERROR" IS '处理失败的原因';
COMMENT ON COLUMN "LIFE".t_boss_request.product_id IS '产品编号';
COMMENT ON COLUMN "LIFE".t_boss_request.req_src IS '插入记录的来源';
COMMENT ON COLUMN "LIFE".t_boss_request.insert_time IS '插入记录的时间';