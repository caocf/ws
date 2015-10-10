CREATE TABLE "LIFE".t_member_favorite (
  "ID" NUMBER(9) NOT NULL,
  favorite_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  favorite_type NUMBER(1),
  user_id NUMBER(9),
  CONSTRAINT pk_member_favorite_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_member_favorite ADD SUPPLEMENTAL LOG GROUP ggs_240372 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_member_favorite IS '用户收藏表';
COMMENT ON COLUMN "LIFE".t_member_favorite.favorite_id IS '商品id或商户id';
COMMENT ON COLUMN "LIFE".t_member_favorite.favorite_type IS '收藏类型 1、商品 2、商户';