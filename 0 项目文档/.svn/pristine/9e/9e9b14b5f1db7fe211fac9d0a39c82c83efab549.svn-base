CREATE TABLE "LIFE".t_verify_cplatform_code (
  code_md5 VARCHAR2(32 BYTE) NOT NULL,
  code_rsa VARCHAR2(256 BYTE),
  store_id VARCHAR2(20 BYTE),
  item_id VARCHAR2(30 BYTE),
  generate_date CHAR(14 BYTE),
  status NUMBER(3),
  order_id VARCHAR2(30 BYTE),
  "SOURCE" NUMBER(1),
  batch_no VARCHAR2(20 BYTE),
  user_id VARCHAR2(30 BYTE),
  verify_times NUMBER(5),
  remain_times NUMBER(5),
  terminal_id VARCHAR2(15 BYTE),
  code_type VARCHAR2(20 BYTE),
  CONSTRAINT pk_t_verify_cplatform_code PRIMARY KEY (code_md5)
);
ALTER TABLE "LIFE".t_verify_cplatform_code ADD SUPPLEMENTAL LOG GROUP ggs_240475 (code_md5) ALWAYS;
COMMENT ON TABLE "LIFE".t_verify_cplatform_code IS '存放宽连自有码';
COMMENT ON COLUMN "LIFE".t_verify_cplatform_code.item_id IS '0：验证码可对应所有商品';
COMMENT ON COLUMN "LIFE".t_verify_cplatform_code.status IS '0：已制码，未验证
1：已撤销
2：使用中
3：已使用
4：已过期
100: 初始化，未使用';
COMMENT ON COLUMN "LIFE".t_verify_cplatform_code.order_id IS '集采预生成订单编号，关联验证表记录（SEQ_ACT_ORDER_SUB）';
COMMENT ON COLUMN "LIFE".t_verify_cplatform_code."SOURCE" IS '1：集采，2：制码';
COMMENT ON COLUMN "LIFE".t_verify_cplatform_code.code_type IS '团购，O2O商城， 彩信折扣券， 短信折扣券';