CREATE TABLE "LIFE".t_market_0_activity (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(120 BYTE) NOT NULL,
  start_time VARCHAR2(30 BYTE) NOT NULL,
  end_time VARCHAR2(30 BYTE) NOT NULL,
  product_id NUMBER(8) NOT NULL,
  image VARCHAR2(100 BYTE) NOT NULL,
  create_time VARCHAR2(30 BYTE),
  update_time VARCHAR2(30 BYTE),
  update_code NUMBER(10),
  market_content VARCHAR2(300 BYTE),
  goods_remark CLOB,
  main_img VARCHAR2(100 BYTE),
  isdel VARCHAR2(1 BYTE) DEFAULT 0,
  isview VARCHAR2(1 BYTE) DEFAULT 0,
  activity_num NUMBER(15) DEFAULT 0,
  PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_market_0_activity.isdel IS '0为可见 1为不可见（删除）';
COMMENT ON COLUMN "LIFE".t_market_0_activity.isview IS '0为下架，1为上架';
COMMENT ON COLUMN "LIFE".t_market_0_activity.activity_num IS '参与活动人数基数';