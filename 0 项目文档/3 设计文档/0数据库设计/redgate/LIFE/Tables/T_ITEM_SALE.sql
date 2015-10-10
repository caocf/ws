CREATE TABLE "LIFE".t_item_sale (
  "ID" NUMBER(8) NOT NULL,
  org_id NUMBER(8),
  sale_price_id NUMBER(8),
  verify_code_type NUMBER(1),
  send_code_mode NUMBER(1),
  send_code_channel NUMBER(1),
  send_code_src NUMBER(8),
  post_flag NUMBER(1),
  sale_start_time VARCHAR2(14 BYTE),
  sale_stop_time VARCHAR2(14 BYTE),
  verify_start_time VARCHAR2(14 BYTE),
  verify_stop_time VARCHAR2(14 BYTE),
  stock_num NUMBER(19),
  user_per_buy_num NUMBER(8),
  status NUMBER(1),
  is_valid NUMBER(1),
  sync_gy_flag NUMBER(1),
  shop_class NUMBER(1),
  store_id NUMBER(8),
  market_price NUMBER(9,2),
  item_mode NUMBER(1),
  type_id NUMBER(8),
  group_flag NUMBER(1),
  virtual_flag NUMBER(1),
  virtual_type NUMBER(1),
  "NAME" VARCHAR2(512 BYTE),
  short_name VARCHAR2(255 BYTE),
  warm_prompt VARCHAR2(1000 BYTE),
  remark CLOB,
  shop_user_id NUMBER(8),
  update_time VARCHAR2(14 BYTE),
  brand VARCHAR2(20 BYTE),
  weight NUMBER(8,2),
  create_time VARCHAR2(14 BYTE),
  create_user_id NUMBER(9),
  update_user_id NUMBER(9),
  market_content VARCHAR2(200 BYTE),
  shop_price NUMBER(9,2),
  img_path VARCHAR2(200 BYTE),
  fee_type NUMBER(9),
  settle_price NUMBER(9,2),
  verify_day NUMBER(5),
  is_view NUMBER(1) DEFAULT 1,
  iseckill NUMBER(2) DEFAULT 0,
  cash_idgoods NUMBER(1),
  coin_idgoods NUMBER(1),
  score_idgoods NUMBER(1),
  grounding_time VARCHAR2(14 BYTE),
  audit_time VARCHAR2(14 BYTE),
  iseckill_price NUMBER(9,2),
  logistics_fee NUMBER(9,2) DEFAULT 0,
  logistics_fee_type NUMBER(1) DEFAULT 0,
  verify_code_desc VARCHAR2(200 BYTE),
  "SOURCE" VARCHAR2(32 BYTE) DEFAULT '0',
  pid NUMBER(8),
  channel_sort VARCHAR2(50 BYTE) DEFAULT '0',
  CONSTRAINT pk_t_item_sale PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_sale ADD SUPPLEMENTAL LOG GROUP ggs_240661 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_item_sale IS '基于商品基本属性所发布销售的商品表';
COMMENT ON COLUMN "LIFE".t_item_sale.org_id IS '和t_item_org匹配';
COMMENT ON COLUMN "LIFE".t_item_sale.verify_code_type IS '方正码平台验证方式0短信1彩信2短信彩信';
COMMENT ON COLUMN "LIFE".t_item_sale.send_code_mode IS '0-不发码 1-按照订单发码 2-按照商品个数发码';
COMMENT ON COLUMN "LIFE".t_item_sale.send_code_channel IS '0-平台自己 1-方正码平台 2-第三方应用 3-卡密 4-拉手 5-拓维电影票 6-鼎山电影票 7-河南优惠券 8-联动汽车票';
COMMENT ON COLUMN "LIFE".t_item_sale.send_code_src IS '如果制码方选择第三方应用，则该字段有用 10-85度C 11-鲜芋仙 ...发码渠道为河南优惠券时，1表示影片优惠券，2表示演唱会门票，3表示景区门票';
COMMENT ON COLUMN "LIFE".t_item_sale.post_flag IS '0-不需要物流配送 1-需要物流配送';
COMMENT ON COLUMN "LIFE".t_item_sale.sale_start_time IS '销售有效开始时间';
COMMENT ON COLUMN "LIFE".t_item_sale.sale_stop_time IS '销售有效结束时间';
COMMENT ON COLUMN "LIFE".t_item_sale.verify_start_time IS '验证有效开始时间';
COMMENT ON COLUMN "LIFE".t_item_sale.verify_stop_time IS '验证有效结束时间';
COMMENT ON COLUMN "LIFE".t_item_sale.stock_num IS '商品库存数量';
COMMENT ON COLUMN "LIFE".t_item_sale.user_per_buy_num IS '0不限制  单个用户购买数量';
COMMENT ON COLUMN "LIFE".t_item_sale.status IS '-1已删除0-草稿 1-待审核 2-审核中 3-审核通过 4-审核驳回';
COMMENT ON COLUMN "LIFE".t_item_sale.is_valid IS '0-下架 1-上架';
COMMENT ON COLUMN "LIFE".t_item_sale.sync_gy_flag IS '0-未同步 1-已同步 2-待审核 3-审核通过4-审核驳回5-删除';
COMMENT ON COLUMN "LIFE".t_item_sale.shop_class IS '"1--业务门店2--商户3--渠道商"';
COMMENT ON COLUMN "LIFE".t_item_sale.store_id IS '匹配表t_store 商户id';
COMMENT ON COLUMN "LIFE".t_item_sale.market_price IS '市场价';
COMMENT ON COLUMN "LIFE".t_item_sale.item_mode IS '商品类型0--实物 1--虚拟物';
COMMENT ON COLUMN "LIFE".t_item_sale.type_id IS '商品分类';
COMMENT ON COLUMN "LIFE".t_item_sale.group_flag IS '"是否是优惠套餐，如果是则是N多商品的组合 0-普通商品 1-优惠套餐"';
COMMENT ON COLUMN "LIFE".t_item_sale.virtual_flag IS '"是否虚拟商品 0-否 1-是"    (暂无用处)';
COMMENT ON COLUMN "LIFE".t_item_sale.virtual_type IS '"虚拟商品类型 1-卡密 2-直充"';
COMMENT ON COLUMN "LIFE".t_item_sale."NAME" IS '商品名称';
COMMENT ON COLUMN "LIFE".t_item_sale.short_name IS '商品简称';
COMMENT ON COLUMN "LIFE".t_item_sale.warm_prompt IS '温馨提示';
COMMENT ON COLUMN "LIFE".t_item_sale.remark IS '商品介绍';
COMMENT ON COLUMN "LIFE".t_item_sale.shop_user_id IS '商户用户id';
COMMENT ON COLUMN "LIFE".t_item_sale.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_item_sale.brand IS '品牌';
COMMENT ON COLUMN "LIFE".t_item_sale.weight IS '重量';
COMMENT ON COLUMN "LIFE".t_item_sale.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_item_sale.create_user_id IS '创建人';
COMMENT ON COLUMN "LIFE".t_item_sale.update_user_id IS '更新人';
COMMENT ON COLUMN "LIFE".t_item_sale.market_content IS '营销语';
COMMENT ON COLUMN "LIFE".t_item_sale.shop_price IS '商城价';
COMMENT ON COLUMN "LIFE".t_item_sale.img_path IS '封面图路径';
COMMENT ON COLUMN "LIFE".t_item_sale.fee_type IS '对应t_sys_fee表id';
COMMENT ON COLUMN "LIFE".t_item_sale.settle_price IS '结算价';
COMMENT ON COLUMN "LIFE".t_item_sale.verify_day IS '验证天数';
COMMENT ON COLUMN "LIFE".t_item_sale.is_view IS '商品是否显示 0，不显示；1显示';
COMMENT ON COLUMN "LIFE".t_item_sale.iseckill IS '营销商品类型： 0普通商品 ，1秒杀商品，2礼品卡兑换商品，3促销商品 4礼品卡5代金券商品6劳保红包';
COMMENT ON COLUMN "LIFE".t_item_sale.cash_idgoods IS '商品支付方式为现金 ，0不支持， 1支持。';
COMMENT ON COLUMN "LIFE".t_item_sale.coin_idgoods IS '商品支付方式为商城币 ，0不支持， 1支持。';
COMMENT ON COLUMN "LIFE".t_item_sale.score_idgoods IS '商品支付方式为积分，0不支持， 1支持。';
COMMENT ON COLUMN "LIFE".t_item_sale.grounding_time IS '上架时间';
COMMENT ON COLUMN "LIFE".t_item_sale.audit_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_item_sale.iseckill_price IS '秒杀价格';
COMMENT ON COLUMN "LIFE".t_item_sale.logistics_fee IS '物流运费';
COMMENT ON COLUMN "LIFE".t_item_sale.logistics_fee_type IS '物流计算方式 0-不累计';
COMMENT ON COLUMN "LIFE".t_item_sale.verify_code_desc IS '自定义短信';
COMMENT ON COLUMN "LIFE".t_item_sale."SOURCE" IS '商品来源 0商城 1拉手 4河南积分兑换商品 5河南优惠券 其他为第三方接口录入，值为APPID';
COMMENT ON COLUMN "LIFE".t_item_sale.pid IS '主商品ID(商品批次号)';
COMMENT ON COLUMN "LIFE".t_item_sale.channel_sort IS '商品排序字段';