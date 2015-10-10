CREATE TABLE "LIFE".t_market_promotion (
  "ID" NUMBER(8) NOT NULL,
  promotion_name VARCHAR2(150 BYTE),
  promotion_start_time VARCHAR2(30 BYTE) NOT NULL,
  promotion_end_time VARCHAR2(30 BYTE) NOT NULL,
  create_time VARCHAR2(30 BYTE),
  promotion_area VARCHAR2(100 BYTE),
  discount NUMBER(10),
  status VARCHAR2(1 BYTE) DEFAULT 0 NOT NULL,
  check_content VARCHAR2(600 BYTE),
  check_man VARCHAR2(20 BYTE),
  check_time VARCHAR2(30 BYTE),
  isdel VARCHAR2(1 BYTE) DEFAULT 0,
  PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_market_promotion.promotion_name IS '限时特价活动名称';
COMMENT ON COLUMN "LIFE".t_market_promotion.promotion_start_time IS '活动开始时间';
COMMENT ON COLUMN "LIFE".t_market_promotion.promotion_end_time IS '活动结束时间';
COMMENT ON COLUMN "LIFE".t_market_promotion.create_time IS '活动创建时间';
COMMENT ON COLUMN "LIFE".t_market_promotion.promotion_area IS '活动区域';
COMMENT ON COLUMN "LIFE".t_market_promotion.discount IS '按折扣换算';
COMMENT ON COLUMN "LIFE".t_market_promotion.status IS '0未审核 1待审核 2审核通过 3审核未通过';
COMMENT ON COLUMN "LIFE".t_market_promotion.check_content IS '审核内容';
COMMENT ON COLUMN "LIFE".t_market_promotion.check_man IS 's审核人';
COMMENT ON COLUMN "LIFE".t_market_promotion.check_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_market_promotion.isdel IS '是否已经删除，1为已经删除';