CREATE TABLE "LIFE".t_bsct_service (
  "ID" NUMBER(9) NOT NULL,
  platform_id VARCHAR2(4 BYTE),
  store_id VARCHAR2(20 BYTE),
  service_id NUMBER(3),
  eff_date VARCHAR2(14 BYTE),
  exp_date VARCHAR2(14 BYTE),
  if_valid NUMBER(1),
  status NUMBER(2),
  sync_status NUMBER(2) DEFAULT 0,
  sync_log_id NUMBER(9),
  CONSTRAINT pk_t_bsct_service PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_bsct_service.if_valid IS '1 ：有效 0： 无效';
COMMENT ON COLUMN "LIFE".t_bsct_service.status IS '0 待审核 1-审核通过 2-审核驳回 3-删除   ';
COMMENT ON COLUMN "LIFE".t_bsct_service.sync_status IS ' 0：待同步  1：已同步，未回应  2：已同步  3：未同步 ';
COMMENT ON COLUMN "LIFE".t_bsct_service.sync_log_id IS '同步日志ID';