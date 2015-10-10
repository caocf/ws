CREATE TABLE "LIFE".com_audit_trail (
  aud_user VARCHAR2(100 BYTE) NOT NULL,
  aud_client_ip VARCHAR2(15 BYTE) NOT NULL,
  aud_server_ip VARCHAR2(15 BYTE) NOT NULL,
  aud_resource VARCHAR2(100 BYTE) NOT NULL,
  aud_action VARCHAR2(100 BYTE) NOT NULL,
  applic_cd VARCHAR2(5 BYTE) NOT NULL,
  aud_date TIMESTAMP NOT NULL,
  CONSTRAINT com_audit_trail_pk PRIMARY KEY (aud_user,aud_client_ip,aud_server_ip,aud_resource,aud_action,applic_cd,aud_date)
);
ALTER TABLE "LIFE".com_audit_trail ADD SUPPLEMENTAL LOG GROUP ggs_240644 (applic_cd, aud_action, aud_client_ip, aud_date, aud_resource, aud_server_ip, aud_user) ALWAYS;
COMMENT ON TABLE "LIFE".com_audit_trail IS '登录审计表';
COMMENT ON COLUMN "LIFE".com_audit_trail.aud_user IS '用户';
COMMENT ON COLUMN "LIFE".com_audit_trail.aud_client_ip IS '客户端IP';
COMMENT ON COLUMN "LIFE".com_audit_trail.aud_server_ip IS '服务端IP';
COMMENT ON COLUMN "LIFE".com_audit_trail.aud_resource IS '结果';
COMMENT ON COLUMN "LIFE".com_audit_trail.aud_action IS '操作';
COMMENT ON COLUMN "LIFE".com_audit_trail.applic_cd IS '应用标识';
COMMENT ON COLUMN "LIFE".com_audit_trail.aud_date IS '时间';