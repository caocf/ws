CREATE TABLE "LIFE".t_bsct_store_settle (
  platform_id VARCHAR2(4 BYTE),
  store_id NUMBER(9),
  merch_id VARCHAR2(15 BYTE),
  capital_flag NUMBER(1),
  settle_type NUMBER(1),
  fee_settle NUMBER(1),
  settle_period NUMBER(1),
  settle_day NUMBER(3),
  settle_day_bit VARCHAR2(31 BYTE),
  settle_trf_days NUMBER(3),
  settle_begin_amt NUMBER(13),
  min_ret_amt NUMBER(11),
  withdraw_bankid VARCHAR2(60 BYTE),
  withdraw_cupsid VARCHAR2(12 BYTE),
  openbank_prov VARCHAR2(2 BYTE),
  openbank_city VARCHAR2(4 BYTE),
  openbank_desc VARCHAR2(60 BYTE),
  bank_acname VARCHAR2(60 BYTE),
  settle_ac VARCHAR2(32 BYTE),
  create_opt NUMBER(10),
  update_date VARCHAR2(14 BYTE),
  status NUMBER(2),
  store_index VARCHAR2(50 BYTE),
  sel_state NUMBER(2)
);
COMMENT ON COLUMN "LIFE".t_bsct_store_settle.capital_flag IS '1 统一支付  2宽连资金帐号';
COMMENT ON COLUMN "LIFE".t_bsct_store_settle.settle_type IS '0：系统结算';
COMMENT ON COLUMN "LIFE".t_bsct_store_settle.fee_settle IS '0：收支两条线  1：作扣
';
COMMENT ON COLUMN "LIFE".t_bsct_store_settle.settle_period IS '0：日  1：周   2：旬   3：月   4：季  5：半年  6：年 7：指定日
';
COMMENT ON COLUMN "LIFE".t_bsct_store_settle.settle_day IS '结算周期数量，例如：结算周期为1，结算周期数量为2时，表示结算周期是2周';
COMMENT ON COLUMN "LIFE".t_bsct_store_settle.settle_day_bit IS '000001000000001000000001000000    对应结算日为6日、16日、25日
';
COMMENT ON COLUMN "LIFE".t_bsct_store_settle.settle_trf_days IS '保留';
COMMENT ON COLUMN "LIFE".t_bsct_store_settle.status IS '1：正常  0： 删除   ';
COMMENT ON COLUMN "LIFE".t_bsct_store_settle.sel_state IS '1.高阳状态';