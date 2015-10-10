CREATE TABLE "LIFE".t_audit_step (
  "ID" NUMBER(8) NOT NULL,
  bs_id NUMBER(8),
  bs_type NUMBER(1),
  status NUMBER(1),
  remark VARCHAR2(100 BYTE),
  audit_user_id NUMBER(8),
  update_time VARCHAR2(14 BYTE),
  bs_tabel VARCHAR2(100 BYTE),
  CONSTRAINT pk_t_audit_step PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_audit_step ADD SUPPLEMENTAL LOG GROUP ggs_240333 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_audit_step IS '审核步骤记录表，涉及多次审核';
COMMENT ON COLUMN "LIFE".t_audit_step.bs_id IS '根据不同业务类型，所对应的商户id、商品id等';
COMMENT ON COLUMN "LIFE".t_audit_step.bs_type IS '1--业务门店
2--商户
3--渠道商
4--商品
5--已发布商品';
COMMENT ON COLUMN "LIFE".t_audit_step.status IS '1--审核通过
2--审核驳回';
COMMENT ON COLUMN "LIFE".t_audit_step.bs_tabel IS '业务表名';