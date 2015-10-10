CREATE TABLE "LIFE".t_sms_act_online (
  act_id NUMBER(12) NOT NULL,
  act_name VARCHAR2(50 BYTE) NOT NULL,
  act_desc VARCHAR2(500 BYTE),
  "GROUP_ID" VARCHAR2(10 BYTE),
  start_time CHAR(14 BYTE),
  end_time CHAR(14 BYTE),
  status VARCHAR2(10 BYTE),
  CONSTRAINT pk_t_sms_act_online PRIMARY KEY (act_id)
);
ALTER TABLE "LIFE".t_sms_act_online ADD SUPPLEMENTAL LOG GROUP ggs_240414 (act_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_sms_act_online.status IS 'audit：待审核
release：待发布
online：商用
pause：暂停
offline：下线';