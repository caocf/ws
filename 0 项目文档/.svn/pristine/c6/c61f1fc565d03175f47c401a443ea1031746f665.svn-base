CREATE TABLE "LIFE".t_item_keyword_dic (
  "ID" NUMBER(9) NOT NULL,
  "NAME" VARCHAR2(60 BYTE) NOT NULL,
  region_code VARCHAR2(6 BYTE) NOT NULL,
  "COUNT" NUMBER(9) DEFAULT 0,
  weight NUMBER(5) DEFAULT 0,
  last_update_time TIMESTAMP DEFAULT sysdate,
  "SOURCE" NUMBER(1) DEFAULT 0,
  CONSTRAINT pk_item_keyword_dic PRIMARY KEY ("ID"),
  CONSTRAINT uni_item_keyword_dic UNIQUE ("NAME",region_code,"SOURCE")
);
COMMENT ON COLUMN "LIFE".t_item_keyword_dic."ID" IS '主键，seq_item_keyword';
COMMENT ON COLUMN "LIFE".t_item_keyword_dic."NAME" IS '关键字';
COMMENT ON COLUMN "LIFE".t_item_keyword_dic.region_code IS '地区码';
COMMENT ON COLUMN "LIFE".t_item_keyword_dic."COUNT" IS '结果数量';
COMMENT ON COLUMN "LIFE".t_item_keyword_dic.weight IS '首排序权重';
COMMENT ON COLUMN "LIFE".t_item_keyword_dic.last_update_time IS '最后更新时间';
COMMENT ON COLUMN "LIFE".t_item_keyword_dic."SOURCE" IS '数据来源，0商城商品 1代金券';