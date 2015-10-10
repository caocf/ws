CREATE TABLE "LIFE".t_pkg_debug_log (
  vtime DATE,
  log_step NUMBER(3),
  vtable VARCHAR2(30 BYTE),
  op_type VARCHAR2(8 BYTE),
  op_result VARCHAR2(500 BYTE)
);
ALTER TABLE "LIFE".t_pkg_debug_log ADD SUPPLEMENTAL LOG GROUP ggs_240501 (log_step, op_result, op_type, vtable, vtime) ALWAYS;