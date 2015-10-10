CREATE TABLE "LIFE".t_shop_ext (
  "ID" NUMBER(8) NOT NULL,
  shop_id NUMBER(8),
  comment_num NUMBER(8),
  s_level NUMBER(8),
  fz_store_id NUMBER(8),
  fz_shop_id NUMBER(8),
  CONSTRAINT pk_t_shop_ext PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_ext ADD SUPPLEMENTAL LOG GROUP ggs_240396 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop_ext IS '业务门店表的扩展字段表，和t_shop_bs 关系1:1';
COMMENT ON COLUMN "LIFE".t_shop_ext.s_level IS '1/2/3/4/5星';
COMMENT ON COLUMN "LIFE".t_shop_ext.fz_store_id IS '商户方正同步ID';
COMMENT ON COLUMN "LIFE".t_shop_ext.fz_shop_id IS '门店方正同步ID';