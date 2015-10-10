CREATE TABLE "LIFE".t_cont_mms (
  "ID" NUMBER(9) NOT NULL,
  content_src VARCHAR2(20 BYTE) NOT NULL,
  title VARCHAR2(200 BYTE) NOT NULL,
  content_path VARCHAR2(100 BYTE),
  "CONTENT" VARCHAR2(2000 BYTE) NOT NULL,
  pic_size VARCHAR2(200 BYTE),
  smil_name VARCHAR2(200 BYTE),
  start_time VARCHAR2(14 BYTE) NOT NULL,
  end_time VARCHAR2(14 BYTE),
  keyword VARCHAR2(200 BYTE),
  area_code VARCHAR2(100 BYTE),
  remark VARCHAR2(200 BYTE),
  param1 VARCHAR2(60 BYTE),
  param2 VARCHAR2(60 BYTE),
  unit_id VARCHAR2(20 BYTE) NOT NULL,
  content_size NUMBER(9),
  status NUMBER(3) NOT NULL,
  auditor_id NUMBER(9),
  advice VARCHAR2(200 BYTE),
  update_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_cont_mms PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_cont_mms ADD SUPPLEMENTAL LOG GROUP ggs_240341 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_cont_mms IS '彩信信息表';
COMMENT ON COLUMN "LIFE".t_cont_mms.content_src IS '如果是cs产品，该字段填写cs产品的产品id';
COMMENT ON COLUMN "LIFE".t_cont_mms.status IS '0:未审核   1:审核通过   2:审核驳回    3:内部审核通过   4:内部审核驳回';
COMMENT ON COLUMN "LIFE".t_cont_mms.advice IS '审核驳回时存放原因';