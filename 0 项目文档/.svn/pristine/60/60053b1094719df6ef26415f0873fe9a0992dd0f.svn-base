CREATE TABLE "LIFE".t_open_role_item (
  "ID" NUMBER(9),
  "NAME" VARCHAR2(20 BYTE),
  "PATH" VARCHAR2(200 BYTE)
);
ALTER TABLE "LIFE".t_open_role_item ADD SUPPLEMENTAL LOG GROUP ggs_240642 ("ID", "NAME", "PATH") ALWAYS;
COMMENT ON TABLE "LIFE".t_open_role_item IS '开放接口中需要权限的项目表';
COMMENT ON COLUMN "LIFE".t_open_role_item."ID" IS 'id';
COMMENT ON COLUMN "LIFE".t_open_role_item."NAME" IS '名称';
COMMENT ON COLUMN "LIFE".t_open_role_item."PATH" IS '对应允许的路径，antpath风格。多个用逗号分割';