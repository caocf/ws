CREATE TABLE "LIFE".t_bsct_shop_user (
  "ID" NUMBER(8) NOT NULL,
  code VARCHAR2(100 BYTE),
  pwd VARCHAR2(32 BYTE),
  status NUMBER(1),
  update_time VARCHAR2(14 BYTE),
  "TYPE" NUMBER(1),
  email VARCHAR2(100 BYTE),
  mobile VARCHAR2(20 BYTE),
  nick_name VARCHAR2(20 BYTE),
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  CONSTRAINT pk_t_bsct_shop_user PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_bsct_shop_user IS '渠道商、结算商户、业务门店账号表';
COMMENT ON COLUMN "LIFE".t_bsct_shop_user.status IS '0-失效
1-有效
2-暂停';
COMMENT ON COLUMN "LIFE".t_bsct_shop_user."TYPE" IS '1-管理员
2-普通用户
3-操作员';
COMMENT ON COLUMN "LIFE".t_bsct_shop_user.shop_class IS '"1--业务门店
2--商户
3--渠道商"';