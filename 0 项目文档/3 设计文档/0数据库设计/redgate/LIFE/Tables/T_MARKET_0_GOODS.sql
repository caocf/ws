CREATE TABLE "LIFE".t_market_0_goods (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(60 BYTE) NOT NULL,
  image VARCHAR2(100 BYTE) NOT NULL,
  goods_link VARCHAR2(100 BYTE) NOT NULL,
  position NUMBER(8),
  create_time VARCHAR2(30 BYTE),
  update_time VARCHAR2(30 BYTE),
  PRIMARY KEY ("ID")
);