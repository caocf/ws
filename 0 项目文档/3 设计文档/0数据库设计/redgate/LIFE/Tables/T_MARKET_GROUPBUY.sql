CREATE TABLE "LIFE".t_market_groupbuy (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(550 BYTE),
  image VARCHAR2(200 BYTE),
  description VARCHAR2(550 BYTE),
  "SORT" NUMBER(10),
  status VARCHAR2(1 BYTE) DEFAULT 0,
  modify_value NUMBER(10) DEFAULT 0,
  product_id VARCHAR2(10 BYTE) NOT NULL,
  "TYPE" VARCHAR2(30 BYTE),
  create_time VARCHAR2(32 BYTE),
  issale VARCHAR2(1 BYTE) DEFAULT 0,
  goods_detail CLOB,
  goods_area VARCHAR2(100 BYTE),
  goods_area_code VARCHAR2(200 BYTE),
  goods_type VARCHAR2(50 BYTE),
  check_status VARCHAR2(1 BYTE) DEFAULT 0,
  check_content VARCHAR2(600 BYTE),
  check_man VARCHAR2(20 BYTE),
  check_time VARCHAR2(30 BYTE),
  create_from VARCHAR2(50 BYTE) DEFAULT 0,
  sms_title VARCHAR2(255 BYTE),
  brand VARCHAR2(254 BYTE),
  update_time VARCHAR2(32 BYTE),
  reserve VARCHAR2(1 BYTE) DEFAULT 0,
  holiday VARCHAR2(1 BYTE) DEFAULT 0,
  meal NUMBER(2) DEFAULT 0,
  refund_type VARCHAR2(1 BYTE) DEFAULT 0,
  PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_market_groupbuy."ID" IS '编码';
COMMENT ON COLUMN "LIFE".t_market_groupbuy."NAME" IS '团购名称';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.image IS '图片';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.description IS '团购标题';
COMMENT ON COLUMN "LIFE".t_market_groupbuy."SORT" IS '排序';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.status IS '团购方式  0为普通团购 1为闭场团购';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.modify_value IS '修改值';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.product_id IS '关联商品ID';
COMMENT ON COLUMN "LIFE".t_market_groupbuy."TYPE" IS '业务渠道 0为商城平台， 1为商盟平台 ，2为wap ，3为android客户端，4为iphone客户端';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.create_time IS '生成时间';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.issale IS '下架 0  上架1';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.goods_detail IS '商品详情';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.goods_area IS '商品销售区域';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.goods_area_code IS '商品销售区域编号';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.goods_type IS '商品类型';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.check_status IS '审核状态（未使用）';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.check_content IS '（未使用）';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.check_man IS '（未使用）';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.check_time IS '（未使用）';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.create_from IS '团购商品来源0：商城团购，1：拉手团购';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.sms_title IS '短信标题：SmsTitle';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.brand IS '品牌：Brand';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.update_time IS '同步更新时间';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.reserve IS '是否需要预约：  0需要预约 ， 1免预约';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.holiday IS '是否节假日通用：0节假日不通用， 1节假日通用';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.meal IS '套餐人数';
COMMENT ON COLUMN "LIFE".t_market_groupbuy.refund_type IS '退款类型：0不支持退款，1支持随时退款，2支持过期退款';