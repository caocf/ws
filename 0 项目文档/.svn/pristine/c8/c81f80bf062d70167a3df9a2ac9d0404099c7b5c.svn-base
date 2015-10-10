CREATE TABLE "LIFE".t_item_group_link (
  "ID" NUMBER(8),
  item_org_id NUMBER(8),
  item_sale_id NUMBER(8)
);
ALTER TABLE "LIFE".t_item_group_link ADD SUPPLEMENTAL LOG GROUP ggs_240355 ("ID", item_org_id, item_sale_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_item_group_link.item_org_id IS '套餐商品的ID';
COMMENT ON COLUMN "LIFE".t_item_group_link.item_sale_id IS '套餐所属的商品的ID';