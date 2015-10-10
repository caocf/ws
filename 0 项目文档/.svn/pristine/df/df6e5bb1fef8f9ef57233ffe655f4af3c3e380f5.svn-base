CREATE TABLE "LIFE".t_bsct_treaty_ref (
  "ID" NUMBER(9) NOT NULL,
  treaty_id NUMBER(9),
  index_id VARCHAR2(32 BYTE),
  status NUMBER(2),
  query_status NUMBER(2),
  merch_id VARCHAR2(15 BYTE),
  platform_id VARCHAR2(4 BYTE)
);
COMMENT ON COLUMN "LIFE".t_bsct_treaty_ref.status IS ' 5 等待同步   6 已同步，带审核，7已同步，审核通过';
COMMENT ON COLUMN "LIFE".t_bsct_treaty_ref.query_status IS '0 生效   1 注销  2 删除   3 待审核  4 审核退回
';