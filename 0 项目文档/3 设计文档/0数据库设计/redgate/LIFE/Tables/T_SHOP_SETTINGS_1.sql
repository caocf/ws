CREATE TABLE "LIFE".t_shop_settings_1 (
  "ID" NUMBER(9) NOT NULL,
  shop_pic_url VARCHAR2(100 BYTE),
  homepage_word CLOB,
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  pic_name VARCHAR2(100 BYTE),
  xw_store_id VARCHAR2(32 BYTE)
);
ALTER TABLE "LIFE".t_shop_settings_1 ADD SUPPLEMENTAL LOG GROUP ggs_240673 ("ID", pic_name, shop_class, shop_id, shop_pic_url, xw_store_id) ALWAYS;