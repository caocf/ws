CREATE TABLE "LIFE".t_shop_tag (
  "ID" NUMBER(8) NOT NULL,
  shop_class NUMBER(1),
  shop_id NUMBER(8),
  tag VARCHAR2(20 BYTE),
  CONSTRAINT pk_t_shop_tag PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_tag ADD SUPPLEMENTAL LOG GROUP ggs_240404 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop_tag IS '存放商户打签标识，纯文字';
COMMENT ON COLUMN "LIFE".t_shop_tag.shop_class IS '1--业务门店
2--商户
3--渠道商';