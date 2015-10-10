CREATE TABLE "LIFE".t_verify_code_name (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(100 BYTE),
  remark VARCHAR2(100 BYTE),
  create_time VARCHAR2(14 BYTE),
  user_id NUMBER(9),
  code_type NUMBER(1),
  CONSTRAINT pk_t_verify_code_name PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_verify_code_name IS '第三方码名称表';
COMMENT ON COLUMN "LIFE".t_verify_code_name."NAME" IS '码名称';
COMMENT ON COLUMN "LIFE".t_verify_code_name.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_verify_code_name.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_verify_code_name.user_id IS '创建人id,对应t_sys_user表id';
COMMENT ON COLUMN "LIFE".t_verify_code_name.code_type IS '码类型，2非卡密，3卡密';