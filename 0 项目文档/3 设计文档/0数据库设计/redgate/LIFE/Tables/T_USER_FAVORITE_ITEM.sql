CREATE TABLE "LIFE".t_user_favorite_item (
  "ID" NUMBER(8) NOT NULL,
  user_id NUMBER(19),
  f_type NUMBER(1),
  f_id NUMBER(8),
  f_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_user_favorite_item PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_user_favorite_item ADD SUPPLEMENTAL LOG GROUP ggs_240470 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_user_favorite_item.f_type IS '1：商品
2：商户';