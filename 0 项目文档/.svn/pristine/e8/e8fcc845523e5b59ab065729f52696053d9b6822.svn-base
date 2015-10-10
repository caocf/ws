CREATE TABLE "LIFE".t_page_static_info (
  "ID" NUMBER(8) NOT NULL,
  "TYPE" NUMBER(1) NOT NULL,
  resource_id NUMBER(8) NOT NULL,
  status NUMBER(1) NOT NULL,
  create_time VARCHAR2(14 BYTE) NOT NULL,
  CONSTRAINT pagestaticinfoid PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_page_static_info ADD SUPPLEMENTAL LOG GROUP ggs_240491 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_page_static_info."TYPE" IS '类型：0是商品，1是商户';
COMMENT ON COLUMN "LIFE".t_page_static_info.resource_id IS '资源ID：商品ID或商户ID';
COMMENT ON COLUMN "LIFE".t_page_static_info.status IS '状态：0是静态化失败，1是静态化成功';