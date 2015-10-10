CREATE TABLE "LIFE".t_verify_store_code_common (
  code_md5 VARCHAR2(32 BYTE) NOT NULL,
  code_rsa VARCHAR2(256 BYTE),
  store_id VARCHAR2(20 BYTE) NOT NULL,
  item_id VARCHAR2(30 BYTE),
  create_date CHAR(14 BYTE),
  status NUMBER(3),
  generate_date CHAR(14 BYTE),
  verify_date CHAR(14 BYTE),
  order_id VARCHAR2(30 BYTE),
  user_id VARCHAR2(30 BYTE),
  import_id NUMBER(8),
  code_name_id NUMBER(8),
  CONSTRAINT pk_t_verify_store_code_common PRIMARY KEY (code_md5)
);
ALTER TABLE "LIFE".t_verify_store_code_common ADD SUPPLEMENTAL LOG GROUP ggs_240483 (code_md5) ALWAYS;
COMMENT ON TABLE "LIFE".t_verify_store_code_common IS '存放商户预生成的验证码';
COMMENT ON COLUMN "LIFE".t_verify_store_code_common.item_id IS '0：验证码可对应所有商品';
COMMENT ON COLUMN "LIFE".t_verify_store_code_common.status IS '0：已制码，未验证
1：已撤销
2：使用中
3：已使用
4：已过期
100: 初始化，未使用';
COMMENT ON COLUMN "LIFE".t_verify_store_code_common.import_id IS '导入文件id';
COMMENT ON COLUMN "LIFE".t_verify_store_code_common.code_name_id IS '码名称编号';