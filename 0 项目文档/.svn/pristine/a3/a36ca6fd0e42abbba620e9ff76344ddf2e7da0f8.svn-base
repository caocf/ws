CREATE TABLE "LIFE".t_shop_settings (
  "ID" NUMBER(9) NOT NULL,
  shop_pic_url VARCHAR2(100 BYTE),
  homepage_word CLOB,
  shop_class NUMBER(1),
  shop_id NUMBER(9),
  pic_name VARCHAR2(100 BYTE),
  pub_tag NUMBER(1) DEFAULT 0,
  operate_end_time VARCHAR2(14 BYTE),
  audit_advice VARCHAR2(200 BYTE),
  CONSTRAINT t_shop_settings_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop_settings ADD SUPPLEMENTAL LOG GROUP ggs_240670 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop_settings IS '商户设置信息';
COMMENT ON COLUMN "LIFE".t_shop_settings.shop_pic_url IS '商户招牌图片路径';
COMMENT ON COLUMN "LIFE".t_shop_settings.homepage_word IS '首页文字介绍';
COMMENT ON COLUMN "LIFE".t_shop_settings.shop_class IS '1--业务门店
2--商户
3--渠道商';
COMMENT ON COLUMN "LIFE".t_shop_settings.pic_name IS '图片名称';
COMMENT ON COLUMN "LIFE".t_shop_settings.pub_tag IS '发布状态。 0未发布 1已发布 修改任意网店设置后此字段为 0 审核后为1';
COMMENT ON COLUMN "LIFE".t_shop_settings.operate_end_time IS '最后操作时间';