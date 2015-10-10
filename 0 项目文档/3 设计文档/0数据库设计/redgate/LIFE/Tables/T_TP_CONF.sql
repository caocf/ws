CREATE TABLE "LIFE".t_tp_conf (
  unit_id VARCHAR2(20 BYTE) NOT NULL,
  passwd VARCHAR2(20 BYTE) NOT NULL,
  sms_sp_code VARCHAR2(21 BYTE),
  mms_sp_code VARCHAR2(21 BYTE),
  sms_flux_limited NUMBER(8) DEFAULT 0 NOT NULL,
  mms_flux_limited NUMBER(8) DEFAULT 0 NOT NULL,
  url VARCHAR2(200 BYTE),
  status NUMBER(1) DEFAULT 0 NOT NULL,
  ip VARCHAR2(200 BYTE),
  "ID" NUMBER(9) NOT NULL,
  "NAME" VARCHAR2(40 BYTE),
  CONSTRAINT pk_t_tp_conf PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_tp_conf ADD SUPPLEMENTAL LOG GROUP ggs_240540 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_tp_conf.unit_id IS 'TP编号';
COMMENT ON COLUMN "LIFE".t_tp_conf.passwd IS 'TP密码（明文）';
COMMENT ON COLUMN "LIFE".t_tp_conf.sms_sp_code IS '短信特服号';
COMMENT ON COLUMN "LIFE".t_tp_conf.mms_sp_code IS '彩信特服号';
COMMENT ON COLUMN "LIFE".t_tp_conf.sms_flux_limited IS '每分钟短信下发限制，0为不许发送，-1为不限';
COMMENT ON COLUMN "LIFE".t_tp_conf.mms_flux_limited IS '每分钟彩信下发限制，0为不许发送，-1为不限';
COMMENT ON COLUMN "LIFE".t_tp_conf.url IS 'TP接收上行的url（目前无用）';
COMMENT ON COLUMN "LIFE".t_tp_conf.status IS '审核状态，0待审核，1审核通过';
COMMENT ON COLUMN "LIFE".t_tp_conf.ip IS '客户端IP，多个用半角逗号分隔，没有限制时填空';
COMMENT ON COLUMN "LIFE".t_tp_conf."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_tp_conf."NAME" IS 'TP名称';