CREATE TABLE "LIFE".t_brand (
  "ID" NUMBER(9) NOT NULL,
  "NAME" VARCHAR2(50 BYTE),
  remark VARCHAR2(200 BYTE),
  website VARCHAR2(100 BYTE),
  brand_img VARCHAR2(100 BYTE),
  create_time VARCHAR2(14 BYTE),
  is_valid NUMBER(1),
  CONSTRAINT pk_t_brand PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_brand ADD SUPPLEMENTAL LOG GROUP ggs_240335 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_brand IS '商品品牌表';
COMMENT ON COLUMN "LIFE".t_brand."NAME" IS '品牌名称';
COMMENT ON COLUMN "LIFE".t_brand.remark IS '品牌描述';
COMMENT ON COLUMN "LIFE".t_brand.website IS '品牌网站';
COMMENT ON COLUMN "LIFE".t_brand.brand_img IS '品牌图片';
COMMENT ON COLUMN "LIFE".t_brand.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_brand.is_valid IS '是否有效
0-无效
1-有效';