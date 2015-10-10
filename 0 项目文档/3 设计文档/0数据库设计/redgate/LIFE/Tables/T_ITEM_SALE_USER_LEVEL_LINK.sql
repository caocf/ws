CREATE TABLE "LIFE".t_item_sale_user_level_link (
  "ID" NUMBER(8) NOT NULL,
  sale_id NUMBER(8),
  user_level VARCHAR2(4 BYTE),
  CONSTRAINT pk_t_item_sale_user_level_link PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_sale_user_level_link ADD SUPPLEMENTAL LOG GROUP ggs_240367 ("ID") ALWAYS;