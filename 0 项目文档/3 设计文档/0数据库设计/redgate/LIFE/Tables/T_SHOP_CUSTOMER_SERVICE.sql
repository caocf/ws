CREATE TABLE "LIFE".t_shop_customer_service (
  "ID" NUMBER(9) NOT NULL,
  nick_name VARCHAR2(50 BYTE),
  code VARCHAR2(2000 BYTE),
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  status NUMBER(1),
  CONSTRAINT pk_t_shop_customer_service PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_customer_service ADD SUPPLEMENTAL LOG GROUP ggs_240538 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_shop_customer_service.nick_name IS '客服昵称';
COMMENT ON COLUMN "LIFE".t_shop_customer_service.code IS '飞信代码';
COMMENT ON COLUMN "LIFE".t_shop_customer_service.shop_class IS '"1--业务门店
2--商户
3--渠道商"';
COMMENT ON COLUMN "LIFE".t_shop_customer_service.status IS '1 -有效 0-无效';