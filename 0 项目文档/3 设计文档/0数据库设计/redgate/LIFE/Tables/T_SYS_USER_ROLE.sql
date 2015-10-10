CREATE TABLE "LIFE".t_sys_user_role (
  user_id NUMBER(9) NOT NULL,
  role_id NUMBER(9) NOT NULL,
  "ID" NUMBER(9) NOT NULL,
  CONSTRAINT pk_t_sys_user_role PRIMARY KEY ("ID") DISABLE
);
ALTER TABLE "LIFE".t_sys_user_role ADD SUPPLEMENTAL LOG GROUP ggs_240466 ("ID", role_id, user_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_user_role IS '用户与角色多对多关系';
COMMENT ON COLUMN "LIFE".t_sys_user_role.user_id IS '用户ID';
COMMENT ON COLUMN "LIFE".t_sys_user_role.role_id IS '角色ID';