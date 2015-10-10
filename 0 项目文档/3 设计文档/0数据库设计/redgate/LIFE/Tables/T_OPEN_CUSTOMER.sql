CREATE TABLE "LIFE".t_open_customer (
  app_id VARCHAR2(32 BYTE) NOT NULL,
  app_key VARCHAR2(32 BYTE),
  "NAME" VARCHAR2(100 BYTE),
  remark VARCHAR2(1000 BYTE),
  ips VARCHAR2(300 BYTE),
  status CHAR DEFAULT 0,
  pay_notify VARCHAR2(200 BYTE),
  app_type CHAR DEFAULT 1,
  shop_class NUMBER(1),
  shop_id NUMBER(9) DEFAULT 0,
  store_id NUMBER(9) DEFAULT 0,
  CONSTRAINT pk_open_customer PRIMARY KEY (app_id)
);
ALTER TABLE "LIFE".t_open_customer ADD SUPPLEMENTAL LOG GROUP ggs_240641 (app_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_open_customer IS '开放平台第三方应用信息';
COMMENT ON COLUMN "LIFE".t_open_customer.app_id IS 'app id';
COMMENT ON COLUMN "LIFE".t_open_customer.app_key IS 'app key';
COMMENT ON COLUMN "LIFE".t_open_customer."NAME" IS '应用名称';
COMMENT ON COLUMN "LIFE".t_open_customer.remark IS '应用详情';
COMMENT ON COLUMN "LIFE".t_open_customer.ips IS '最多20个的可信任ip地址，为空表示不限制ip，逗号分割';
COMMENT ON COLUMN "LIFE".t_open_customer.status IS '应用状态，0新建 1审核驳回 2测试中 3上线 4下线';
COMMENT ON COLUMN "LIFE".t_open_customer.pay_notify IS '支付通知接口';
COMMENT ON COLUMN "LIFE".t_open_customer.app_type IS '应用类型，1垂直频道 2商户';
COMMENT ON COLUMN "LIFE".t_open_customer.shop_class IS '商户类型，1业务门店
2商户
3渠道商';
COMMENT ON COLUMN "LIFE".t_open_customer.shop_id IS '门店ID，如果为商户或渠道商则填0';
COMMENT ON COLUMN "LIFE".t_open_customer.store_id IS '商户、渠道商ID';