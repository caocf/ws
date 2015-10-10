CREATE TABLE "LIFE".t_shop_user_link (
  "ID" NUMBER(8) NOT NULL,
  shop_id NUMBER(8),
  shop_class NUMBER(1),
  shop_user_id NUMBER(8),
  CONSTRAINT pk_t_shop_user_link PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_user_link ADD SUPPLEMENTAL LOG GROUP ggs_240407 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop_user_link IS '商户及对应账号关联表';
COMMENT ON COLUMN "LIFE".t_shop_user_link.shop_class IS '1--业务门店
2--商户
3--渠道商';