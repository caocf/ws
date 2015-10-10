CREATE TABLE "LIFE".t_item_tag (
  "ID" NUMBER(8) NOT NULL,
  item_id NUMBER(8),
  tag VARCHAR2(20 BYTE),
  CONSTRAINT pk_t_item_tag PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_tag ADD SUPPLEMENTAL LOG GROUP ggs_240368 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_item_tag IS '存放商品打签标识，纯文字';