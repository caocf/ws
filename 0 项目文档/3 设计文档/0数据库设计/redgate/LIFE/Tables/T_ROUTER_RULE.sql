CREATE TABLE "LIFE".t_router_rule (
  sp_code VARCHAR2(22 BYTE) NOT NULL,
  target VARCHAR2(40 BYTE) NOT NULL
);
ALTER TABLE "LIFE".t_router_rule ADD SUPPLEMENTAL LOG GROUP ggs_240393 (sp_code, target) ALWAYS;
COMMENT ON COLUMN "LIFE".t_router_rule.sp_code IS '特服号';
COMMENT ON COLUMN "LIFE".t_router_rule.target IS '目的队列';