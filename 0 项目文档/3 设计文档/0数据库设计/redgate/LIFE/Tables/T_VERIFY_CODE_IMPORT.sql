CREATE TABLE "LIFE".t_verify_code_import (
  "ID" NUMBER(8) NOT NULL,
  code_name_id NUMBER(8),
  file_path VARCHAR2(100 BYTE),
  create_time VARCHAR2(14 BYTE),
  user_id NUMBER(9),
  total_count NUMBER(10),
  code_type NUMBER(1),
  store_id NUMBER(8),
  code_name VARCHAR2(100 BYTE),
  CONSTRAINT pk_t_verify_code_import PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_verify_code_import IS '码导入信息表';
COMMENT ON COLUMN "LIFE".t_verify_code_import.code_name_id IS '码名称编号';
COMMENT ON COLUMN "LIFE".t_verify_code_import.file_path IS '导入文件路径';
COMMENT ON COLUMN "LIFE".t_verify_code_import.create_time IS '导入时间';
COMMENT ON COLUMN "LIFE".t_verify_code_import.user_id IS '导入人id';
COMMENT ON COLUMN "LIFE".t_verify_code_import.total_count IS '码数量';
COMMENT ON COLUMN "LIFE".t_verify_code_import.code_type IS '码类型，0-方正码平台 1-平台自己 2-第三方应用3-卡密';
COMMENT ON COLUMN "LIFE".t_verify_code_import.store_id IS '商户';
COMMENT ON COLUMN "LIFE".t_verify_code_import.code_name IS '码名称';