CREATE TABLE "LIFE".t_open_customer_role (
  app_id VARCHAR2(32 BYTE) NOT NULL,
  role_id NUMBER(9) NOT NULL,
  CONSTRAINT pk_open_customer_role PRIMARY KEY (app_id,role_id)
);
ALTER TABLE "LIFE".t_open_customer_role ADD SUPPLEMENTAL LOG GROUP ggs_240643 (app_id, role_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_open_customer_role IS '第三方应用和权限的对应关系表';
COMMENT ON COLUMN "LIFE".t_open_customer_role.app_id IS 'app id';
COMMENT ON COLUMN "LIFE".t_open_customer_role.role_id IS 'role_id';