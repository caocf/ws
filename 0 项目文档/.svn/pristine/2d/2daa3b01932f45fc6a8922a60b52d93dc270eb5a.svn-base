CREATE TABLE "LIFE".t_smsbuy_item_online (
  item_id VARCHAR2(30 BYTE) NOT NULL,
  item_name VARCHAR2(50 BYTE),
  auditor_id VARCHAR2(20 BYTE),
  advice VARCHAR2(100 BYTE),
  opt_id VARCHAR2(20 BYTE),
  opt_time CHAR(14 BYTE),
  quato_buy NUMBER(9),
  reply_buy VARCHAR2(300 BYTE),
  quato_store NUMBER(9),
  reply_store VARCHAR2(300 BYTE),
  CONSTRAINT pk_t_smsbuy_item_online PRIMARY KEY (item_id)
);
ALTER TABLE "LIFE".t_smsbuy_item_online ADD SUPPLEMENTAL LOG GROUP ggs_240411 (item_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_smsbuy_item_online.auditor_id IS '放空';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_online.advice IS '放空';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_online.quato_buy IS '0，不限购';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_online.quato_store IS '0，库存不限';