CREATE TABLE "LIFE".t_shop_login_log (
  "ID" NUMBER(9) NOT NULL,
  user_name VARCHAR2(20 BYTE),
  user_id NUMBER(9),
  ip VARCHAR2(15 BYTE),
  login_time VARCHAR2(14 BYTE),
  success_flag NUMBER(2),
  user_code VARCHAR2(20 BYTE),
  shop_id NUMBER(9),
  shop_class NUMBER(1),
  shop_name VARCHAR2(100 BYTE)
);
ALTER TABLE "LIFE".t_shop_login_log ADD SUPPLEMENTAL LOG GROUP ggs_240400 ("ID", ip, login_time, shop_class, shop_id, shop_name, success_flag, user_code, user_id, user_name) ALWAYS;
COMMENT ON COLUMN "LIFE".t_shop_login_log.success_flag IS '1-成功 0-失败';