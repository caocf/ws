CREATE TABLE "LIFE".t_bsct_agent_shop (
  "ID" NUMBER(9) NOT NULL,
  platform_id VARCHAR2(4 BYTE),
  service_id NUMBER(3),
  store_id VARCHAR2(20 BYTE),
  proxy_storeid VARCHAR2(20 BYTE),
  proxy_shopids VARCHAR2(2000 BYTE),
  proxy_shopnames VARCHAR2(2000 BYTE),
  service_seq_id NUMBER(9),
  CONSTRAINT pk_t_bsct_agent_shop PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_bsct_agent_shop.proxy_shopids IS '0表示全部门店，多个门店用逗号分隔';