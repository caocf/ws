CREATE TABLE "LIFE".t_sc_app_info (
  "ID" NUMBER(9) NOT NULL,
  app_code VARCHAR2(50 BYTE),
  app_name VARCHAR2(200 BYTE),
  app_remark CLOB,
  app_open_url VARCHAR2(500 BYTE),
  app_close_url VARCHAR2(500 BYTE),
  app_url VARCHAR2(500 BYTE),
  status NUMBER(2) DEFAULT 1 NOT NULL,
  app_contacts VARCHAR2(200 BYTE),
  app_tel VARCHAR2(200 BYTE),
  app_mail VARCHAR2(200 BYTE),
  app_notice_url VARCHAR2(200 BYTE)
);
ALTER TABLE "LIFE".t_sc_app_info ADD SUPPLEMENTAL LOG GROUP ggs_240696 (app_close_url, app_code, app_contacts, app_mail, app_name, app_notice_url, app_open_url, app_tel, app_url, "ID", status) ALWAYS;
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_code IS '应用识别码';
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_name IS '应用名称';
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_remark IS '应用说明';
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_open_url IS '应用开通链接';
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_close_url IS '应用关闭链接';
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_url IS '应用访问地址';
COMMENT ON COLUMN "LIFE".t_sc_app_info.status IS '1：在用
0：停用';
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_contacts IS '联系人';
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_tel IS '联系电话';
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_mail IS '联系人邮箱';
COMMENT ON COLUMN "LIFE".t_sc_app_info.app_notice_url IS '应用透传接口';