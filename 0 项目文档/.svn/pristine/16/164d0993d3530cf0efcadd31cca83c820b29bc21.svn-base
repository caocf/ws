CREATE TABLE "LIFE".t_verify_code_info (
  order_id VARCHAR2(30 BYTE) NOT NULL,
  item_order_id NUMBER(19),
  act_order_id NUMBER(19),
  code VARCHAR2(256 BYTE),
  code_2d VARCHAR2(100 BYTE),
  code_status NUMBER(3),
  trans_date CHAR(14 BYTE),
  valid_date CHAR(14 BYTE),
  expire_date CHAR(14 BYTE),
  valid_times NUMBER(3),
  remain_times NUMBER(3),
  terminal_id VARCHAR2(15 BYTE),
  item_id VARCHAR2(30 BYTE),
  item_name VARCHAR2(100 BYTE),
  item_quantity NUMBER(9),
  total_price NUMBER(10),
  platform_id VARCHAR2(15 BYTE),
  store_id VARCHAR2(20 BYTE),
  sms_content VARCHAR2(300 BYTE),
  CONSTRAINT pk_t_verify_code_info PRIMARY KEY (order_id)
);
ALTER TABLE "LIFE".t_verify_code_info ADD SUPPLEMENTAL LOG GROUP ggs_240472 (order_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_verify_code_info.code_status IS '0：正常
1：已撤销
2：使用中
3：已使用
4：已过期';
COMMENT ON COLUMN "LIFE".t_verify_code_info.platform_id IS 'FOUNDER：方正
STORE：商户
CPLATFORM：宽连';