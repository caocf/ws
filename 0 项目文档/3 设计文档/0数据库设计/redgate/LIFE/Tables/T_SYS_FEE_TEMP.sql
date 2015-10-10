CREATE TABLE "LIFE".t_sys_fee_temp (
  "ID" NUMBER(8) NOT NULL,
  t1 NUMBER(7,4),
  g1 NUMBER(12,2),
  t2 NUMBER(7,4),
  g2 NUMBER(12,2),
  t3 NUMBER(7,4),
  g3 NUMBER(12,2),
  t4 NUMBER(7,4),
  g4 NUMBER(12,2),
  t5 NUMBER(7,4),
  g5 NUMBER(12,2)
);
ALTER TABLE "LIFE".t_sys_fee_temp ADD SUPPLEMENTAL LOG GROUP ggs_240502 (g1, g2, g3, g4, g5, "ID", t1, t2, t3, t4, t5) ALWAYS;