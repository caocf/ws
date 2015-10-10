CREATE TABLE "LIFE".t_item_sale_ext (
  "ID" NUMBER(9) NOT NULL,
  sale_id NUMBER(8),
  sale_num NUMBER(8) DEFAULT 0,
  click_num NUMBER(8) DEFAULT 0,
  comment_num NUMBER(8) DEFAULT 0,
  user_num NUMBER(8) DEFAULT 0,
  collect_num NUMBER(8) DEFAULT 0,
  "RANK" NUMBER(9,2) DEFAULT 0,
  CONSTRAINT pk_t_item_sale_ext PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_sale_ext ADD SUPPLEMENTAL LOG GROUP ggs_240364 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_item_sale_ext IS '基于发布商品表的附加其它属性表';
COMMENT ON COLUMN "LIFE".t_item_sale_ext.sale_id IS '和t_item_sale匹配';
COMMENT ON COLUMN "LIFE".t_item_sale_ext.sale_num IS '销售数量';
COMMENT ON COLUMN "LIFE".t_item_sale_ext.click_num IS '人气数';
COMMENT ON COLUMN "LIFE".t_item_sale_ext.comment_num IS '评论量';
COMMENT ON COLUMN "LIFE".t_item_sale_ext.user_num IS '购买人数';
COMMENT ON COLUMN "LIFE".t_item_sale_ext.collect_num IS '收藏数量';
COMMENT ON COLUMN "LIFE".t_item_sale_ext."RANK" IS '商品评分';