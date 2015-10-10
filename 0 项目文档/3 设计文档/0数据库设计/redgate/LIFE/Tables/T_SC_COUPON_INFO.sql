CREATE TABLE "LIFE".t_sc_coupon_info (
  "ID" NUMBER(9) NOT NULL,
  coupon_name VARCHAR2(50 BYTE),
  coupon_content VARCHAR2(500 BYTE),
  activity_time VARCHAR2(100 BYTE),
  shop_id NUMBER(9),
  status NUMBER(1),
  coupon_pic VARCHAR2(500 BYTE)
);
COMMENT ON COLUMN "LIFE".t_sc_coupon_info.status IS '1 使用 ， 2停用
';