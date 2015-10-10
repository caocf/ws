CREATE TABLE "LIFE".t_shop_user_role (
  "ID" NUMBER(9) NOT NULL,
  user_id NUMBER(9),
  role_id NUMBER(9)
);
ALTER TABLE "LIFE".t_shop_user_role ADD SUPPLEMENTAL LOG GROUP ggs_240408 ("ID", role_id, user_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_shop_user_role.user_id IS '商户帐号';
COMMENT ON COLUMN "LIFE".t_shop_user_role.role_id IS '角色id';