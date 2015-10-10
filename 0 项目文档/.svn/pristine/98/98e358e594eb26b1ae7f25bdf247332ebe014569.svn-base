CREATE TABLE "LIFE".t_boss_precontract (
  "ID" NUMBER(9) NOT NULL,
  mobile VARCHAR2(21 BYTE),
  prd_code VARCHAR2(50 BYTE),
  opr_code VARCHAR2(4 BYTE),
  area_code VARCHAR2(20 BYTE),
  opr_time VARCHAR2(20 BYTE),
  service_id VARCHAR2(20 BYTE),
  receive_time VARCHAR2(20 BYTE),
  insert_time VARCHAR2(20 BYTE),
  status NUMBER(3),
  trans_id VARCHAR2(40 BYTE),
  "ERROR" VARCHAR2(1000 BYTE),
  CONSTRAINT pk_t_boss_precontract PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_boss_precontract ADD SUPPLEMENTAL LOG GROUP ggs_240527 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_boss_precontract."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_boss_precontract.mobile IS '号码';
COMMENT ON COLUMN "LIFE".t_boss_precontract.prd_code IS '需要开通的应用，多个用半角逗号分隔';
COMMENT ON COLUMN "LIFE".t_boss_precontract.opr_code IS '操作类型，01开通，02关闭，03暂停，04恢复，10变更';
COMMENT ON COLUMN "LIFE".t_boss_precontract.area_code IS '地区';
COMMENT ON COLUMN "LIFE".t_boss_precontract.opr_time IS '操作时间（预约操作时间），14位时间';
COMMENT ON COLUMN "LIFE".t_boss_precontract.service_id IS '业务代码';
COMMENT ON COLUMN "LIFE".t_boss_precontract.receive_time IS '接收时间，14位时间';
COMMENT ON COLUMN "LIFE".t_boss_precontract.insert_time IS '插表时间，14位时间';
COMMENT ON COLUMN "LIFE".t_boss_precontract.status IS '状态，0未处理，1已处理，2处理成功，3处理失败，4忽略';
COMMENT ON COLUMN "LIFE".t_boss_precontract.trans_id IS 'Boss请求的编号';
COMMENT ON COLUMN "LIFE".t_boss_precontract."ERROR" IS '错误详情';