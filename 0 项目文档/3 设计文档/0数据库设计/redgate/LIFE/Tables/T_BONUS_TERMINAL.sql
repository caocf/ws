CREATE TABLE "LIFE".t_bonus_terminal (
  "ID" NUMBER(9) NOT NULL,
  "NAME" VARCHAR2(20 BYTE),
  terminal_id NUMBER(13),
  jobnumber VARCHAR2(50 BYTE),
  company VARCHAR2(100 BYTE),
  department VARCHAR2(100 BYTE),
  address VARCHAR2(300 BYTE),
  remark VARCHAR2(30 BYTE),
  CONSTRAINT pk_t_bonus_terminal PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_bonus_terminal."NAME" IS '姓名';
COMMENT ON COLUMN "LIFE".t_bonus_terminal.terminal_id IS '目标号码';
COMMENT ON COLUMN "LIFE".t_bonus_terminal.jobnumber IS '工号';
COMMENT ON COLUMN "LIFE".t_bonus_terminal.company IS '所属公司';
COMMENT ON COLUMN "LIFE".t_bonus_terminal.department IS '所属部门';
COMMENT ON COLUMN "LIFE".t_bonus_terminal.address IS '公司地址';
COMMENT ON COLUMN "LIFE".t_bonus_terminal.remark IS '备注';