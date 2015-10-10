CREATE TABLE "LIFE".t_market_fullpresent (
  "ID" NUMBER(10) NOT NULL,
  "NAME" VARCHAR2(150 BYTE) NOT NULL,
  start_time VARCHAR2(30 BYTE) NOT NULL,
  end_time VARCHAR2(30 BYTE) NOT NULL,
  positionlimit VARCHAR2(10 BYTE) DEFAULT 0,
  channel VARCHAR2(10 BYTE),
  "SCOPE" VARCHAR2(10 BYTE),
  "COUNT" NUMBER(10) DEFAULT 0,
  create_time VARCHAR2(30 BYTE),
  check_status VARCHAR2(1 BYTE) DEFAULT 0,
  check_content VARCHAR2(600 BYTE),
  check_man VARCHAR2(20 BYTE),
  check_time VARCHAR2(30 BYTE),
  isdel VARCHAR2(1 BYTE)
);
COMMENT ON COLUMN "LIFE".t_market_fullpresent."NAME" IS '活动名称';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.start_time IS '活动开始时间';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.end_time IS '活动结束时间';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.positionlimit IS '身份限制  1为红钻会员 2为商盟会员3为指定用户 0为不限';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.channel IS '活动渠道 1为商盟平台 2为商城平台 3为WAP 4为android 5为iphoen';
COMMENT ON COLUMN "LIFE".t_market_fullpresent."SCOPE" IS '活动前台展示范围 0为频道 1为商户 2为指定商品';
COMMENT ON COLUMN "LIFE".t_market_fullpresent."COUNT" IS '赠送次数';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.check_status IS '0未审核 1待审核 2审核通过 3审核未通过';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.check_content IS '审核意见';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.check_man IS '审核人';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.check_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_market_fullpresent.isdel IS '是否已经删除 0为未删除 1为已经删除 ';