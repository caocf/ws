CREATE TABLE "LIFE".t_member_verify_log (
  "ID" NUMBER(9) NOT NULL,
  member_id NUMBER(9),
  verify_time VARCHAR2(14 BYTE),
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  CONSTRAINT pk_member_verify_log PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_member_verify_log ADD SUPPLEMENTAL LOG GROUP ggs_240489 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_member_verify_log IS '会员验证记录表';
COMMENT ON COLUMN "LIFE".t_member_verify_log.member_id IS '验证成功的会员id';
COMMENT ON COLUMN "LIFE".t_member_verify_log.verify_time IS '验证时间';
COMMENT ON COLUMN "LIFE".t_member_verify_log.shop_class IS '"1--业务门店 2--商户 3--渠道商"';