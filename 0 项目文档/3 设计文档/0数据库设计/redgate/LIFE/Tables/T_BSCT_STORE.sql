CREATE TABLE "LIFE".t_bsct_store (
  store_id VARCHAR2(20 BYTE) NOT NULL,
  "NAME" VARCHAR2(500 BYTE),
  nick_name VARCHAR2(100 BYTE),
  region_code VARCHAR2(100 BYTE),
  store_type NUMBER(2),
  address VARCHAR2(500 BYTE),
  contact VARCHAR2(100 BYTE),
  tel VARCHAR2(100 BYTE),
  fax VARCHAR2(100 BYTE),
  services VARCHAR2(1000 BYTE),
  services_tel VARCHAR2(100 BYTE),
  is_valid NUMBER(2),
  is_signed NUMBER(2),
  create_uid VARCHAR2(100 BYTE),
  create_time VARCHAR2(14 BYTE),
  update_uid VARCHAR2(100 BYTE),
  update_time VARCHAR2(14 BYTE),
  status NUMBER(2),
  f_status NUMBER(2) DEFAULT 3,
  t_status NUMBER(2) DEFAULT 3,
  sync_fz_log_id NUMBER(9) DEFAULT null,
  sync_tz_log_id NUMBER(9) DEFAULT null,
  "SOURCE" NUMBER(2),
  CONSTRAINT pk_t_bsct_store PRIMARY KEY (store_id)
);
COMMENT ON TABLE "LIFE".t_bsct_store IS '包含商户中心商户与代理商表';
COMMENT ON COLUMN "LIFE".t_bsct_store.store_type IS '1：结算商户（A类）2：非结算商户（B类） 3 渠道商';
COMMENT ON COLUMN "LIFE".t_bsct_store.is_valid IS '1：有效 0： 无效';
COMMENT ON COLUMN "LIFE".t_bsct_store.status IS '0 待审核 1-审核通过 2-审核驳回 3-删除   ';
COMMENT ON COLUMN "LIFE".t_bsct_store.f_status IS '方正同步状态 0：待同步  1：已同步，未回应  2：已同步  3：未同步 ';
COMMENT ON COLUMN "LIFE".t_bsct_store.t_status IS '线下同步状态 0：待同步  1：已同步，未回应  2：已同步  3：未同步';
COMMENT ON COLUMN "LIFE".t_bsct_store.sync_fz_log_id IS '方正同步日志ID';
COMMENT ON COLUMN "LIFE".t_bsct_store.sync_tz_log_id IS '线下同步日志ID';
COMMENT ON COLUMN "LIFE".t_bsct_store."SOURCE" IS '1:商城 2:移动营销';