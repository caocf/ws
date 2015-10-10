CREATE TABLE "LIFE".t_router_rule_special (
  sp_code VARCHAR2(21 BYTE) NOT NULL,
  command VARCHAR2(20 BYTE),
  target_sp_code VARCHAR2(21 BYTE),
  target_command VARCHAR2(20 BYTE)
);
ALTER TABLE "LIFE".t_router_rule_special ADD SUPPLEMENTAL LOG GROUP ggs_244670 (command, sp_code, target_command, target_sp_code) ALWAYS;
COMMENT ON COLUMN "LIFE".t_router_rule_special.sp_code IS '特服号';
COMMENT ON COLUMN "LIFE".t_router_rule_special.command IS '指令，为空时匹配所有指令';
COMMENT ON COLUMN "LIFE".t_router_rule_special.target_sp_code IS '目标特服号，为空时无变化';
COMMENT ON COLUMN "LIFE".t_router_rule_special.target_command IS '目标指令，为空时无变化';