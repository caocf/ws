CREATE TABLE "LIFE".t_sc_esop_shop_sync_log (
  "ID" NUMBER(9) NOT NULL,
  update_time VARCHAR2(14 BYTE),
  "CONTENT" VARCHAR2(1000 BYTE),
  shop_name VARCHAR2(200 BYTE),
  shop_contact VARCHAR2(20 BYTE),
  tel VARCHAR2(50 BYTE),
  email VARCHAR2(50 BYTE),
  shop_address VARCHAR2(100 BYTE),
  CONSTRAINT pk_t_sc_esop_shop_sync_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sc_esop_shop_sync_log ADD SUPPLEMENTAL LOG GROUP ggs_240633 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sc_esop_shop_sync_log."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_sc_esop_shop_sync_log.update_time IS '同步时间';
COMMENT ON COLUMN "LIFE".t_sc_esop_shop_sync_log."CONTENT" IS '原始报文';
COMMENT ON COLUMN "LIFE".t_sc_esop_shop_sync_log.shop_name IS '商户名称';
COMMENT ON COLUMN "LIFE".t_sc_esop_shop_sync_log.shop_contact IS '联系人';
COMMENT ON COLUMN "LIFE".t_sc_esop_shop_sync_log.tel IS '电话';
COMMENT ON COLUMN "LIFE".t_sc_esop_shop_sync_log.email IS '邮箱';
COMMENT ON COLUMN "LIFE".t_sc_esop_shop_sync_log.shop_address IS '地址';