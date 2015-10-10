CREATE TABLE "LIFE".t_good_shelf_1 (
  "ID" NUMBER(9),
  title VARCHAR2(50 BYTE),
  pid NUMBER(9),
  level_flag NUMBER(1),
  order_index NUMBER(2),
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  shop_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  img_url VARCHAR2(100 BYTE),
  xw_store_id VARCHAR2(32 BYTE)
);
ALTER TABLE "LIFE".t_good_shelf_1 ADD SUPPLEMENTAL LOG GROUP ggs_240350 ("ID", img_url, level_flag, order_index, pid, shop_class, shop_id, shop_user_id, title, update_time, xw_store_id) ALWAYS;