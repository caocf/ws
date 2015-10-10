CREATE TABLE "LIFE".t_bsct_store_base (
  platform_id VARCHAR2(4 BYTE),
  store_id NUMBER(9),
  store_index VARCHAR2(20 BYTE),
  merch_id VARCHAR2(15 BYTE) NOT NULL,
  sel_state NUMBER(2),
  status NUMBER(2),
  CONSTRAINT pk_t_bsct_store_base PRIMARY KEY (merch_id)
);
COMMENT ON COLUMN "LIFE".t_bsct_store_base.sel_state IS '高阳状态';
COMMENT ON COLUMN "LIFE".t_bsct_store_base.status IS '同步状态 1待同步，2已同步';