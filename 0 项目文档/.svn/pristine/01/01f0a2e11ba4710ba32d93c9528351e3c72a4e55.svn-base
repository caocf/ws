CREATE TABLE "LIFE".t_gift_apply_out (
  "ID" NUMBER(8) NOT NULL,
  "TYPE" NUMBER(1),
  apply_user NUMBER(9),
  apply_time VARCHAR2(14 BYTE),
  order_id NUMBER(19),
  pay_status NUMBER(1),
  payment NUMBER(10),
  pay_unit VARCHAR2(255 BYTE),
  pay_bank VARCHAR2(255 BYTE),
  contact_name VARCHAR2(255 BYTE),
  guarantor_name VARCHAR2(255 BYTE),
  address VARCHAR2(255 BYTE),
  cellphone_number VARCHAR2(255 BYTE),
  zip_code VARCHAR2(255 BYTE),
  accounted_name VARCHAR2(255 BYTE),
  accounted_code VARCHAR2(255 BYTE),
  accounted_time VARCHAR2(14 BYTE),
  out_status NUMBER(1),
  active_status NUMBER(1),
  remark VARCHAR2(255 BYTE),
  status NUMBER(1),
  audit_user NUMBER(9),
  audit_time VARCHAR2(14 BYTE),
  audit_advice VARCHAR2(255 BYTE),
  receive_name VARCHAR2(255 CHAR),
  CONSTRAINT pk_t_gift_apply_out PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_gift_apply_out."TYPE" IS '申请类型,0：线上申请，1：线下申请';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.apply_user IS '申请人';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.apply_time IS '申请时间';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.order_id IS '支付订单';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.pay_status IS '支付状态，0未支付，2已支付';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.payment IS '支付金额金额,分';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.pay_unit IS '付款单位全称';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.pay_bank IS '付款行名称';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.contact_name IS '联系人';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.guarantor_name IS '担保人';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.address IS '收货地址';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.cellphone_number IS '联系电话';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.zip_code IS '邮政编码';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.accounted_name IS '入账人';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.accounted_code IS '入账单号';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.accounted_time IS '入账时间';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.out_status IS '出库状态';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.active_status IS '激活状态';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.status IS '状态 0：未审核 1：审核通过 2：审核驳回';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.audit_user IS '审核人';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.audit_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.audit_advice IS '审核意见';
COMMENT ON COLUMN "LIFE".t_gift_apply_out.receive_name IS '领卡人';