CREATE TABLE "LIFE".t_bsct_store_fee_tmp (
  "ID" NUMBER(9) NOT NULL,
  platform_id VARCHAR2(4 BYTE),
  store_id NUMBER(9),
  merch_id VARCHAR2(15 BYTE),
  capital_type NUMBER(2),
  fee_type NUMBER(1),
  fee_mode NUMBER(1),
  fee_method NUMBER(1),
  begin_amount NUMBER(13),
  minfee_amount NUMBER(11),
  maxfee_amount NUMBER(11),
  fee_basicflag NUMBER(1),
  fee_levelflag NUMBER(1),
  up_reference1 VARCHAR2(11 BYTE),
  fix_feeamount1 VARCHAR2(9 BYTE),
  fee_rate1 VARCHAR2(5 BYTE),
  up_reference2 VARCHAR2(11 BYTE),
  fix_feeamount2 VARCHAR2(9 BYTE),
  fee_rate2 VARCHAR2(5 BYTE),
  up_reference3 VARCHAR2(11 BYTE),
  fix_feeamount3 VARCHAR2(9 BYTE),
  fee_rate3 VARCHAR2(5 BYTE),
  up_reference4 VARCHAR2(11 BYTE),
  fix_feeamount4 VARCHAR2(9 BYTE),
  fee_rate4 VARCHAR2(5 BYTE),
  up_reference5 VARCHAR2(11 BYTE),
  fix_feeamount5 VARCHAR2(9 BYTE),
  fee_rate5 VARCHAR2(5 BYTE),
  status NUMBER(2),
  store_index VARCHAR2(50 BYTE),
  update_date VARCHAR2(14 BYTE),
  create_opt NUMBER(9),
  opt_type NUMBER(2),
  CONSTRAINT pk_t_bsct_store_fee_tmp PRIMARY KEY ("ID") DISABLE
);
COMMENT ON COLUMN "LIFE".t_bsct_store_fee_tmp.capital_type IS '1：现金  2：商城币  3：积分
';
COMMENT ON COLUMN "LIFE".t_bsct_store_fee_tmp.fee_type IS '1：业务平台计费   2：统一清算计费
';
COMMENT ON COLUMN "LIFE".t_bsct_store_fee_tmp.fee_mode IS '0：不收取  2：单笔计算、按周期收取  3：按周期汇总计算并收取
';
COMMENT ON COLUMN "LIFE".t_bsct_store_fee_tmp.fee_method IS '1:按固定金额收取  2:按金额固定百分比收取
';
COMMENT ON COLUMN "LIFE".t_bsct_store_fee_tmp.fee_basicflag IS '0：金额  1：笔数';
COMMENT ON COLUMN "LIFE".t_bsct_store_fee_tmp.fee_levelflag IS '空格：表示只有一档；0：套档，1：分层；最多可分5层/档。
本字段没有输入的情况下，只有一档费用计算参数。
';
COMMENT ON COLUMN "LIFE".t_bsct_store_fee_tmp.status IS '0--草稿 1 待审核 2 审核中  3-审核通过 4-审核驳回  5等待同步';