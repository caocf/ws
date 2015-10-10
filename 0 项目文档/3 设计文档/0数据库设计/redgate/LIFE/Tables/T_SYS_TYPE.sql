CREATE TABLE "LIFE".t_sys_type (
  "ID" NUMBER(20) NOT NULL,
  p_id NUMBER(20),
  "NAME" VARCHAR2(100 BYTE),
  "TYPE" NUMBER(1),
  is_valid NUMBER(1) DEFAULT 1,
  CONSTRAINT pk_t_sys_type PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_type ADD SUPPLEMENTAL LOG GROUP ggs_240461 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_type IS '分类表，存储商户分类和商品分类，以树状结构存储';
COMMENT ON COLUMN "LIFE".t_sys_type.p_id IS '根的话该字段为0';
COMMENT ON COLUMN "LIFE".t_sys_type."TYPE" IS '1--商户类型
2--商品类型';
COMMENT ON COLUMN "LIFE".t_sys_type.is_valid IS '0-无效 1有效';