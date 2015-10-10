CREATE TABLE "LIFE".t_bsct_store_base_tmp (
  platform_id VARCHAR2(4 BYTE),
  store_id NUMBER(9),
  store_index VARCHAR2(20 BYTE),
  merch_id VARCHAR2(15 BYTE),
  status NUMBER(2) NOT NULL,
  opt_type NUMBER(2),
  CONSTRAINT pk_t_bsct_store_base_tmp PRIMARY KEY (status)
);
COMMENT ON COLUMN "LIFE".t_bsct_store_base_tmp.status IS '0--草稿 1 待审核 2 审核中  3-审核通过 4-审核驳回 5 等待同步   ';
COMMENT ON COLUMN "LIFE".t_bsct_store_base_tmp.opt_type IS '1：增加  2：修改  3删除';