CREATE TABLE "LIFE".t_sys_platform (
  platform_id VARCHAR2(4 BYTE) NOT NULL,
  platform_name VARCHAR2(60 BYTE),
  platform_desc VARCHAR2(500 BYTE),
  "ID" NUMBER(9) NOT NULL,
  CONSTRAINT pk_t_sys_platform PRIMARY KEY ("ID")
);