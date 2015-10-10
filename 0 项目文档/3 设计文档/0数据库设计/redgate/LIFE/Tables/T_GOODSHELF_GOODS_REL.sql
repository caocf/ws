CREATE TABLE "LIFE".t_goodshelf_goods_rel (
  "ID" NUMBER(9) NOT NULL,
  shelf_id NUMBER(9),
  good_id NUMBER(9),
  CONSTRAINT t_goodshelf_rel_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_goodshelf_goods_rel ADD SUPPLEMENTAL LOG GROUP ggs_240347 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_goodshelf_goods_rel IS '货架关联商品关系表';
COMMENT ON COLUMN "LIFE".t_goodshelf_goods_rel.shelf_id IS '货架ID';
COMMENT ON COLUMN "LIFE".t_goodshelf_goods_rel.good_id IS '商品ID（对应t_item_org的id）';