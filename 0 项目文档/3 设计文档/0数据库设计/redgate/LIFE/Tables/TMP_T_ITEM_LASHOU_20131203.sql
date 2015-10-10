CREATE TABLE "LIFE".tmp_t_item_lashou_20131203 (
  "ID" NUMBER(9) NOT NULL,
  item_id NUMBER(9),
  vender_team_id VARCHAR2(20 BYTE),
  sms_title VARCHAR2(255 BYTE),
  first_catalog NUMBER(9),
  second_catalog NUMBER(9),
  third_catalog NUMBER(9),
  province_id NUMBER(9),
  city_id NUMBER(9),
  brand VARCHAR2(254 BYTE),
  stock_mode NUMBER(1),
  refund_strategy NUMBER(1),
  max_sale NUMBER(9),
  create_time VARCHAR2(14 BYTE),
  update_time VARCHAR2(14 BYTE)
);