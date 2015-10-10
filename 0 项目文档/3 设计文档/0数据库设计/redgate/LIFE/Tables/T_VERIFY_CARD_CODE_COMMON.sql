CREATE TABLE "LIFE".t_verify_card_code_common (
  "ID" NUMBER(12) NOT NULL,
  card_id VARCHAR2(30 BYTE) NOT NULL,
  card_key VARCHAR2(256 BYTE) NOT NULL,
  store_id VARCHAR2(20 BYTE) NOT NULL,
  item_id VARCHAR2(30 BYTE),
  create_date CHAR(14 BYTE),
  status NUMBER(3),
  generate_date CHAR(14 BYTE),
  order_id VARCHAR2(30 BYTE),
  user_id VARCHAR2(30 BYTE),
  import_id NUMBER(8),
  code_name_id NUMBER(8),
  CONSTRAINT pk_t_verify_card_code_common PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_verify_card_code_common ADD SUPPLEMENTAL LOG GROUP ggs_240471 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_verify_card_code_common IS '存放商户预生成的卡密';
COMMENT ON COLUMN "LIFE".t_verify_card_code_common.item_id IS '0：验证码可对应所有商品';
COMMENT ON COLUMN "LIFE".t_verify_card_code_common.status IS '0：已制码，未验证
1：已撤销
2：使用中
3：已使用
4：已过期
100: 初始化，未使用';
COMMENT ON COLUMN "LIFE".t_verify_card_code_common.import_id IS '导入文件id';
COMMENT ON COLUMN "LIFE".t_verify_card_code_common.code_name_id IS '码名称编号';