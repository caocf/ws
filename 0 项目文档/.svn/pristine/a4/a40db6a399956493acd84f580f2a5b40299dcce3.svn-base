CREATE TABLE "LIFE".t_sys_template_event (
  "ID" NUMBER(9) NOT NULL,
  code NUMBER(6),
  "TYPE" NUMBER(1),
  tgid NUMBER(9),
  tgname VARCHAR2(100 BYTE),
  "NAME" VARCHAR2(50 BYTE),
  memo VARCHAR2(100 BYTE),
  CONSTRAINT pk_t_sys_template_event PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_template_event ADD SUPPLEMENTAL LOG GROUP ggs_240456 ("ID") ALWAYS;