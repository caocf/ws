CREATE TABLE "LIFE".t_member_invoice (
  "ID" NUMBER(9) NOT NULL,
  mid NUMBER(9),
  invoice_type CHAR,
  invoice_title_type CHAR,
  invoice_title VARCHAR2(40 BYTE),
  CONSTRAINT pk_member_invoice PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_member_invoice ADD SUPPLEMENTAL LOG GROUP ggs_240373 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_member_invoice."ID" IS 'id';
COMMENT ON COLUMN "LIFE".t_member_invoice.mid IS '会员编号';
COMMENT ON COLUMN "LIFE".t_member_invoice.invoice_type IS '发票类型 1普通发票';
COMMENT ON COLUMN "LIFE".t_member_invoice.invoice_title_type IS '发票抬头类型 1个人2单位';
COMMENT ON COLUMN "LIFE".t_member_invoice.invoice_title IS '发票抬头内容';