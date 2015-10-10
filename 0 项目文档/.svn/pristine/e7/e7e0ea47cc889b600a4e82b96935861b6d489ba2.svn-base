CREATE TABLE "LIFE".t_cart (
  uuid VARCHAR2(64 BYTE),
  access_time NUMBER,
  expire_time NUMBER,
  "CONTENT" CLOB
);
ALTER TABLE "LIFE".t_cart ADD SUPPLEMENTAL LOG GROUP ggs_240655 (access_time, expire_time, uuid) ALWAYS;