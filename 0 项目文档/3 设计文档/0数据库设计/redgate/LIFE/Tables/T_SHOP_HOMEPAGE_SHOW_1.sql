CREATE TABLE "LIFE".t_shop_homepage_show_1 (
  "ID" NUMBER(9) NOT NULL,
  title VARCHAR2(100 BYTE),
  shelf_id NUMBER(9),
  good_num NUMBER(3),
  status NUMBER(1),
  order_index NUMBER(1),
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  shop_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  store_id VARCHAR2(30 BYTE)
);
ALTER TABLE "LIFE".t_shop_homepage_show_1 ADD SUPPLEMENTAL LOG GROUP ggs_240398 (good_num, "ID", order_index, shelf_id, shop_class, shop_id, shop_user_id, status, store_id, title, update_time) ALWAYS;