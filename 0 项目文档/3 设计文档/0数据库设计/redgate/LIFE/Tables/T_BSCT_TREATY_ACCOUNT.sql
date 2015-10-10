CREATE TABLE "LIFE".t_bsct_treaty_account (
  "ID" NUMBER(9) NOT NULL,
  platform_id VARCHAR2(4 BYTE),
  store_id NUMBER(9),
  service_id NUMBER(3),
  merch_id VARCHAR2(15 BYTE),
  server_ip VARCHAR2(16 BYTE),
  server_port VARCHAR2(5 BYTE),
  user_name VARCHAR2(16 BYTE),
  user_pass VARCHAR2(32 BYTE),
  CONSTRAINT pk_t_bsct_treaty_account PRIMARY KEY ("ID")
);