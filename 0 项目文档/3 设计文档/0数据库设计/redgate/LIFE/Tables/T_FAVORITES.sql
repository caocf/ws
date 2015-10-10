CREATE TABLE "LIFE".t_favorites (
  "ID" NUMBER(9) NOT NULL,
  "NAME" VARCHAR2(200 BYTE),
  "TYPE" NUMBER(1),
  url VARCHAR2(200 BYTE),
  create_time VARCHAR2(14 BYTE),
  obj_id NUMBER(9),
  user_id NUMBER(9),
  CONSTRAINT pk_t_favorites PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_favorites ADD SUPPLEMENTAL LOG GROUP ggs_240346 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_favorites."NAME" IS '名称';
COMMENT ON COLUMN "LIFE".t_favorites."TYPE" IS '类型
1-商品
2-店铺';
COMMENT ON COLUMN "LIFE".t_favorites.url IS '地址';
COMMENT ON COLUMN "LIFE".t_favorites.create_time IS '收藏时间';
COMMENT ON COLUMN "LIFE".t_favorites.obj_id IS '对象id';
COMMENT ON COLUMN "LIFE".t_favorites.user_id IS '收藏人';