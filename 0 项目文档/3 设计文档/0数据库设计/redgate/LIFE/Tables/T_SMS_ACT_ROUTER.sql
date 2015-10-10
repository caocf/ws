CREATE TABLE "LIFE".t_sms_act_router (
  "ID" NUMBER(12) NOT NULL,
  act_id NUMBER(12) NOT NULL,
  sp_code VARCHAR2(20 BYTE),
  cmd_opt_type NUMBER(2),
  command VARCHAR2(50 BYTE),
  pay_type NUMBER(1),
  item_price NUMBER(9),
  is_session NUMBER(1),
  CONSTRAINT pk_t_sms_act_router PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sms_act_router ADD SUPPLEMENTAL LOG GROUP ggs_240415 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sms_act_router.cmd_opt_type IS '1：入口指令
2：其它指令';
COMMENT ON COLUMN "LIFE".t_sms_act_router.command IS '正则指令内容';
COMMENT ON COLUMN "LIFE".t_sms_act_router.pay_type IS '0：不需要支付
1：积分
2：商城币
';
COMMENT ON COLUMN "LIFE".t_sms_act_router.is_session IS '0：不需要
1：需要';