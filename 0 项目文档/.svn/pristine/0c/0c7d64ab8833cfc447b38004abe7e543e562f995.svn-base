CREATE TABLE "LIFE".t_sso_info (
  "SID" VARCHAR2(50 BYTE),
  remember_me VARCHAR2(1 BYTE),
  create_time VARCHAR2(14 BYTE),
  user_id NUMBER(9) NOT NULL,
  login_type VARCHAR2(10 BYTE),
  CONSTRAINT pk_t_sso_info_temp PRIMARY KEY (user_id)
);
ALTER TABLE "LIFE".t_sso_info ADD SUPPLEMENTAL LOG GROUP ggs_240420 (user_id) ALWAYS;