CREATE TABLE "LIFE".t_item_sale_0909_full (
  "ID" NUMBER(8) NOT NULL,
  org_id NUMBER(8),
  sale_price_id NUMBER(8),
  verify_code_type NUMBER(1),
  send_code_mode NUMBER(1),
  send_code_channel NUMBER(1),
  send_code_src NUMBER(2),
  post_flag NUMBER(1),
  sale_start_time VARCHAR2(14 BYTE),
  sale_stop_time VARCHAR2(14 BYTE),
  verify_start_time VARCHAR2(14 BYTE),
  verify_stop_time VARCHAR2(14 BYTE),
  stock_num NUMBER(19),
  user_per_buy_num NUMBER(8),
  status NUMBER(1),
  is_valid NUMBER(1),
  sync_gy_flag NUMBER(1),
  shop_class NUMBER(1),
  store_id NUMBER(8),
  market_price NUMBER(9,2),
  item_mode NUMBER(1),
  type_id NUMBER(8),
  group_flag NUMBER(1),
  virtual_flag NUMBER(1),
  virtual_type NUMBER(1),
  "NAME" VARCHAR2(200 BYTE),
  short_name VARCHAR2(200 BYTE),
  warm_prompt VARCHAR2(200 BYTE),
  remark CLOB,
  shop_user_id NUMBER(8),
  update_time VARCHAR2(14 BYTE),
  brand VARCHAR2(20 BYTE),
  weight NUMBER(8,2),
  create_time VARCHAR2(14 BYTE),
  create_user_id NUMBER(9),
  update_user_id NUMBER(9),
  market_content VARCHAR2(200 BYTE),
  shop_price NUMBER(9,2),
  img_path VARCHAR2(200 BYTE),
  fee_type NUMBER(9),
  settle_price NUMBER(9,2),
  verify_day NUMBER(5),
  is_view NUMBER(1),
  iseckill NUMBER(2),
  cash_idgoods NUMBER(1),
  coin_idgoods NUMBER(1),
  score_idgoods NUMBER(1),
  grounding_time VARCHAR2(14 BYTE),
  audit_time VARCHAR2(14 BYTE),
  iseckill_price NUMBER(9,2)
);
ALTER TABLE "LIFE".t_item_sale_0909_full ADD SUPPLEMENTAL LOG DATA (ALL) COLUMNS;