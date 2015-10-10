CREATE TABLE "LIFE".t_goodshelf_goods_rel_1 (
  "ID" NUMBER(9) NOT NULL,
  shelf_id NUMBER(9),
  good_id NUMBER(9),
  xw_product_id VARCHAR2(60 BYTE)
);
ALTER TABLE "LIFE".t_goodshelf_goods_rel_1 ADD SUPPLEMENTAL LOG GROUP ggs_240348 (good_id, "ID", shelf_id, xw_product_id) ALWAYS;