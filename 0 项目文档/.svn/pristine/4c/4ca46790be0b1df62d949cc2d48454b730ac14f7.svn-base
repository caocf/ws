CREATE TABLE "LIFE".t_shop_pos_link (
  "ID" NUMBER(8) NOT NULL,
  shop_id NUMBER(8),
  pos_id NUMBER(8),
  pos_no VARCHAR2(50 BYTE),
  terminal_id VARCHAR2(50 BYTE),
  CONSTRAINT pk_t_shop_pos_link PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_pos_link ADD SUPPLEMENTAL LOG GROUP ggs_240402 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop_pos_link IS '业务门店终端关系表，每个门店可以配置多个终端';
COMMENT ON COLUMN "LIFE".t_shop_pos_link.terminal_id IS '终端标识';