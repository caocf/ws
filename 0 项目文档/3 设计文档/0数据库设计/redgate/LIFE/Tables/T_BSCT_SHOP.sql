CREATE TABLE "LIFE".t_bsct_shop (
  shop_id VARCHAR2(20 BYTE) NOT NULL,
  "NAME" VARCHAR2(500 BYTE),
  nick_name VARCHAR2(100 BYTE),
  store_id VARCHAR2(20 BYTE),
  region_code VARCHAR2(100 BYTE),
  address VARCHAR2(500 BYTE),
  tel VARCHAR2(100 BYTE),
  biz_time VARCHAR2(14 BYTE),
  bus_info VARCHAR2(500 BYTE),
  biz_area VARCHAR2(100 BYTE),
  car_spots VARCHAR2(100 BYTE),
  per_consum VARCHAR2(100 BYTE),
  logo VARCHAR2(100 BYTE),
  remark VARCHAR2(500 BYTE),
  map_long VARCHAR2(20 BYTE),
  map_dim VARCHAR2(20 BYTE),
  is_valid NUMBER(2),
  shop_services VARCHAR2(500 BYTE),
  create_time VARCHAR2(14 BYTE),
  update_time VARCHAR2(14 BYTE),
  status NUMBER(2),
  f_status NUMBER(2) DEFAULT 3,
  t_status NUMBER(2) DEFAULT 3,
  sync_fz_log_id NUMBER(9),
  sync_tz_log_id NUMBER(9),
  CONSTRAINT pk_t_bsct_shop PRIMARY KEY (shop_id)
);
COMMENT ON COLUMN "LIFE".t_bsct_shop.status IS '0 待审核 1-审核通过 2-审核驳回 3-删除   ';
COMMENT ON COLUMN "LIFE".t_bsct_shop.f_status IS '方正同步状态  0：待同步  1：已同步，未回应  2：已同步  3：未同步 ';
COMMENT ON COLUMN "LIFE".t_bsct_shop.t_status IS '线下同步状态  0：待同步  1：已同步，未回应  2：已同步  3：未同步 ';