CREATE TABLE "LIFE".t_app_info (
  "ID" NUMBER(9) NOT NULL,
  app_code VARCHAR2(50 BYTE),
  app_name VARCHAR2(200 BYTE),
  app_remark CLOB,
  app_open_url VARCHAR2(500 BYTE),
  app_close_url VARCHAR2(500 BYTE),
  app_url VARCHAR2(500 BYTE),
  status NUMBER(2) DEFAULT 0,
  app_contacts VARCHAR2(200 BYTE),
  app_tel VARCHAR2(200 BYTE),
  app_mail VARCHAR2(200 BYTE),
  app_notice_url1 VARCHAR2(200 BYTE),
  CONSTRAINT pk_t_app_info PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_app_info ADD SUPPLEMENTAL LOG GROUP ggs_240682 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_app_info.status IS '1：在用
0：停用';