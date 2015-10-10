CREATE TABLE "LIFE".t_cont_sms (
  "ID" NUMBER(9) NOT NULL,
  content_src VARCHAR2(20 BYTE) NOT NULL,
  "CONTENT" VARCHAR2(1000 BYTE) NOT NULL,
  start_time VARCHAR2(14 BYTE),
  end_time VARCHAR2(14 BYTE),
  keyword VARCHAR2(200 BYTE),
  area_code VARCHAR2(100 BYTE),
  unit_id VARCHAR2(20 BYTE) NOT NULL,
  remark VARCHAR2(200 BYTE),
  status NUMBER(3) NOT NULL,
  auditor_id NUMBER(9),
  advice VARCHAR2(200 BYTE),
  update_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE)
);
ALTER TABLE "LIFE".t_cont_sms ADD SUPPLEMENTAL LOG GROUP ggs_240342 (advice, area_code, auditor_id, "CONTENT", content_src, end_time, "ID", keyword, remark, start_time, status, unit_id, update_time, update_user_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_cont_sms IS '短信信息表';
COMMENT ON COLUMN "LIFE".t_cont_sms.status IS '0:未审核   1:审核通过   2:审核驳回    3:内部审核通过   4:内部审核驳回';
COMMENT ON COLUMN "LIFE".t_cont_sms.advice IS '审核驳回时存放原因';