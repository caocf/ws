CREATE TABLE "LIFE".t_store_fee_2 (
  "ID" NUMBER,
  store_id NUMBER(9),
  merchid VARCHAR2(20 BYTE),
  capital_type1 NUMBER(1),
  capital_type2 NUMBER(1),
  capital_type3 NUMBER(1),
  trade_mode NUMBER(1),
  fee_type VARCHAR2(2 BYTE),
  production_type VARCHAR2(10 BYTE),
  clear_type VARCHAR2(2 BYTE),
  effortdate VARCHAR2(8 BYTE),
  expirydate VARCHAR2(8 BYTE),
  feedrtflag VARCHAR2(1 BYTE),
  feeperiodunit VARCHAR2(50 BYTE),
  fc_name VARCHAR2(50 BYTE),
  feemode NUMBER(1),
  feemothod NUMBER(1),
  beganamount VARCHAR2(13 BYTE),
  minfeeamount VARCHAR2(11 BYTE),
  maxfeeamount VARCHAR2(11 BYTE),
  feebasicflag NUMBER(1),
  feelevelflag VARCHAR2(1 BYTE),
  feelvlbasicflag VARCHAR2(1 BYTE),
  upreference1 VARCHAR2(11 BYTE),
  fixfeeamount1 VARCHAR2(9 BYTE),
  feerate1 VARCHAR2(5 BYTE),
  upreference2 VARCHAR2(11 BYTE),
  fixfeeamount2 VARCHAR2(9 BYTE),
  feerate2 VARCHAR2(5 BYTE),
  upreference3 VARCHAR2(11 BYTE),
  fixfeeamount3 VARCHAR2(9 BYTE),
  feerate3 VARCHAR2(5 BYTE),
  upreference4 VARCHAR2(11 BYTE),
  fixfeeamount4 VARCHAR2(9 BYTE),
  feerate4 VARCHAR2(5 BYTE),
  upreference5 VARCHAR2(11 BYTE),
  fixfeeamount5 VARCHAR2(9 BYTE),
  feerate5 VARCHAR2(5 BYTE),
  create_time VARCHAR2(14 BYTE),
  sync_time VARCHAR2(14 BYTE),
  create_user NUMBER(9),
  status NUMBER(1),
  sync_gy_flag1 NUMBER(1),
  sync_gy_flag2 NUMBER(1),
  sync_gy_flag3 NUMBER(1)
);
ALTER TABLE "LIFE".t_store_fee_2 ADD SUPPLEMENTAL LOG DATA (ALL) COLUMNS;