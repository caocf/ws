CREATE TABLE "LIFE".t_member_address (
  "ID" NUMBER(9) NOT NULL,
  mid NUMBER(9) NOT NULL,
  remark VARCHAR2(10 BYTE),
  region VARCHAR2(10 BYTE) NOT NULL,
  address VARCHAR2(200 BYTE) NOT NULL,
  zipcode VARCHAR2(10 BYTE),
  "NAME" VARCHAR2(50 BYTE) NOT NULL,
  mobile VARCHAR2(40 BYTE),
  phone VARCHAR2(40 BYTE),
  update_time VARCHAR2(14 BYTE),
  create_time VARCHAR2(14 BYTE),
  last_use_time VARCHAR2(14 BYTE),
  default_shipping CHAR DEFAULT '0',
  default_pay_type VARCHAR2(2 BYTE),
  CONSTRAINT pk_member_address PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_member_address ADD SUPPLEMENTAL LOG GROUP ggs_240371 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_member_address IS '收货地址';
COMMENT ON COLUMN "LIFE".t_member_address.mid IS '用户编号';
COMMENT ON COLUMN "LIFE".t_member_address.remark IS '地址标注';
COMMENT ON COLUMN "LIFE".t_member_address.region IS '地区编码';
COMMENT ON COLUMN "LIFE".t_member_address.address IS '详细地址';
COMMENT ON COLUMN "LIFE".t_member_address.zipcode IS '邮政编码';
COMMENT ON COLUMN "LIFE".t_member_address."NAME" IS '收货人';
COMMENT ON COLUMN "LIFE".t_member_address.mobile IS '联系电话：手机';
COMMENT ON COLUMN "LIFE".t_member_address.phone IS '联系电话：固话';
COMMENT ON COLUMN "LIFE".t_member_address.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_member_address.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_member_address.last_use_time IS '最后使用时间';
COMMENT ON COLUMN "LIFE".t_member_address.default_shipping IS '默认地址标志 1是 0否';
COMMENT ON COLUMN "LIFE".t_member_address.default_pay_type IS '默认支付方式（保留）';