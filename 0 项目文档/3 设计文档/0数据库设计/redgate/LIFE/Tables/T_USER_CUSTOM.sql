CREATE TABLE "LIFE".t_user_custom (
  "ID" NUMBER(12) NOT NULL,
  terminal_id VARCHAR2(21 BYTE) NOT NULL,
  product_id VARCHAR2(20 BYTE) NOT NULL,
  status NUMBER(1) DEFAULT 0 NOT NULL,
  update_time VARCHAR2(14 BYTE) NOT NULL,
  custom_time VARCHAR2(14 BYTE) NOT NULL,
  CONSTRAINT pk_user_custom PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_user_custom ADD SUPPLEMENTAL LOG GROUP ggs_240528 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_user_custom."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_user_custom.terminal_id IS '号码';
COMMENT ON COLUMN "LIFE".t_user_custom.product_id IS '定制的业务编号，Boss的产品ID或Misc的业务ID  106003-红钻';
COMMENT ON COLUMN "LIFE".t_user_custom.status IS '0正常，1暂停';
COMMENT ON COLUMN "LIFE".t_user_custom.update_time IS '14位时间，记录更新时间';
COMMENT ON COLUMN "LIFE".t_user_custom.custom_time IS '定制时间（来自BOSS或MISC）';