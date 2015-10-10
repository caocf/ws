CREATE TABLE "LIFE".t_gift_card_use_info (
  "ID" NUMBER(9) NOT NULL,
  user_id NUMBER(9),
  create_date VARCHAR2(14 BYTE),
  serial_no VARCHAR2(20 BYTE),
  par_value NUMBER(9),
  status NUMBER(2),
  item_id NUMBER(8),
  item_name VARCHAR2(200 BYTE),
  store_id NUMBER(8),
  store_name VARCHAR2(200 BYTE),
  r_name VARCHAR2(50 BYTE),
  r_address VARCHAR2(200 BYTE),
  r_mobile VARCHAR2(40 BYTE),
  r_phone VARCHAR2(40 BYTE),
  r_zipcode VARCHAR2(10 BYTE),
  remark VARCHAR2(500 BYTE),
  src_type NUMBER(2) DEFAULT 1,
  CONSTRAINT pk_t_gift_card_use_info PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_gift_card_use_info."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.user_id IS '用户ID';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.create_date IS '创建时间';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.serial_no IS '卡号';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.par_value IS '礼品卡面额';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.status IS '状态';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.item_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.item_name IS '商品名称';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.store_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.store_name IS '商户名称';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.r_name IS '收货人';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.r_address IS '收货人地址';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.r_mobile IS '收货人手机';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.r_phone IS '收货人电话';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.r_zipcode IS '收货人邮编';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_gift_card_use_info.src_type IS '来源类型：1是web，2是wap，3是html5';