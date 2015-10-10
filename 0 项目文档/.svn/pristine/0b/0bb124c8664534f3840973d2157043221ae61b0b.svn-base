CREATE TABLE "LIFE".test111 (
  sp_code VARCHAR2(21 BYTE) NOT NULL,
  command VARCHAR2(20 BYTE),
  target_sp_code VARCHAR2(21 BYTE),
  target_command VARCHAR2(20 BYTE)
);
ALTER TABLE "LIFE".test111 ADD SUPPLEMENTAL LOG GROUP ggs_247876 (command, sp_code, target_command, target_sp_code) ALWAYS;