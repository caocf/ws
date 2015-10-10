CREATE TABLE "LIFE".t_bsct_store_tmp (
  store_id VARCHAR2(20 BYTE) NOT NULL,
  "NAME" VARCHAR2(500 BYTE),
  nick_name VARCHAR2(100 BYTE),
  region_code VARCHAR2(100 BYTE),
  store_type NUMBER(2),
  address VARCHAR2(500 BYTE),
  contact VARCHAR2(100 BYTE),
  tel VARCHAR2(100 BYTE),
  fax VARCHAR2(100 BYTE),
  services VARCHAR2(1000 BYTE),
  services_tel VARCHAR2(100 BYTE),
  is_valid NUMBER(2),
  is_signed NUMBER(2),
  create_uid VARCHAR2(100 BYTE),
  create_time VARCHAR2(14 BYTE),
  update_uid VARCHAR2(100 BYTE),
  update_time VARCHAR2(14 BYTE),
  status NUMBER(2),
  opt_type NUMBER(2),
  CONSTRAINT pk_t_bsct_store_tmp PRIMARY KEY (store_id)
);
COMMENT ON TABLE "LIFE".t_bsct_store_tmp IS '包含商户中心商户与代理商表';
COMMENT ON COLUMN "LIFE".t_bsct_store_tmp.store_type IS '1：结算商户（A类）2：非结算商户（B类） 3 渠道商';
COMMENT ON COLUMN "LIFE".t_bsct_store_tmp.status IS '0--草稿 1 待审核 2 审核中  3-审核通过 4-审核驳回 5 等待同步   ';
COMMENT ON COLUMN "LIFE".t_bsct_store_tmp.opt_type IS '1：增加  2：修改  ';