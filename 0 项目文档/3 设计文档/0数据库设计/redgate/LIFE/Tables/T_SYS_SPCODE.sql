CREATE TABLE "LIFE".t_sys_spcode (
  "ID" NUMBER(8) NOT NULL,
  sp_code VARCHAR2(50 BYTE),
  valid NUMBER(1),
  CONSTRAINT pk_t_sys_spcode PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_spcode ADD SUPPLEMENTAL LOG GROUP ggs_240453 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_spcode IS '系统特服号';
COMMENT ON COLUMN "LIFE".t_sys_spcode.sp_code IS 'spcode';
COMMENT ON COLUMN "LIFE".t_sys_spcode.valid IS '0-有效 1-无效';