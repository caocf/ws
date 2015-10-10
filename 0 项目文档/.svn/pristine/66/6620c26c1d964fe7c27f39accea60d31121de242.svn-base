CREATE TABLE "LIFE".t_ems_history (
  "ID" NUMBER(19) NOT NULL,
  express_no VARCHAR2(16 BYTE),
  status_code NUMBER(10),
  status_text VARCHAR2(50 BYTE),
  pay_date VARCHAR2(14 BYTE),
  raw_data VARCHAR2(200 BYTE),
  save_date VARCHAR2(14 BYTE),
  CONSTRAINT pk_ems_history UNIQUE ("ID")
);
COMMENT ON TABLE "LIFE".t_ems_history IS 'EMS对账历史记录';
COMMENT ON COLUMN "LIFE".t_ems_history."ID" IS '记录ID';
COMMENT ON COLUMN "LIFE".t_ems_history.express_no IS '快递单号';
COMMENT ON COLUMN "LIFE".t_ems_history.status_code IS '状态码 0成功 1拒收 2无法投递';
COMMENT ON COLUMN "LIFE".t_ems_history.status_text IS '状态说明';
COMMENT ON COLUMN "LIFE".t_ems_history.pay_date IS '支付到货时间';
COMMENT ON COLUMN "LIFE".t_ems_history.raw_data IS '原始记录内容';
COMMENT ON COLUMN "LIFE".t_ems_history.save_date IS '记录保存时间';