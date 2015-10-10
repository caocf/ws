CREATE TABLE "LIFE".t_sys_service (
  service_id NUMBER(3) NOT NULL,
  service_name VARCHAR2(100 BYTE),
  service_desc VARCHAR2(500 BYTE),
  platform_id VARCHAR2(4 BYTE),
  is_need_shop NUMBER(1),
  CONSTRAINT pk_t_sys_service PRIMARY KEY (service_id)
);
COMMENT ON COLUMN "LIFE".t_sys_service.is_need_shop IS '0：不需要 1：需要';