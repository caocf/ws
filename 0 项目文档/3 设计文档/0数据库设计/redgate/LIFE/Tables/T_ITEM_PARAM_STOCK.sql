CREATE TABLE "LIFE".t_item_param_stock (
  "ID" NUMBER(8) NOT NULL,
  item_id NUMBER(8) NOT NULL,
  item_color VARCHAR2(50 BYTE),
  item_size VARCHAR2(50 BYTE),
  stocknum NUMBER(8),
  item_type NUMBER(1),
  remark VARCHAR2(200 BYTE),
  CONSTRAINT pk_t_item_param_stock PRIMARY KEY ("ID",item_id)
);
COMMENT ON COLUMN "LIFE".t_item_param_stock."ID" IS '流水号';
COMMENT ON COLUMN "LIFE".t_item_param_stock.item_id IS '商品编号';
COMMENT ON COLUMN "LIFE".t_item_param_stock.item_color IS '颜色';
COMMENT ON COLUMN "LIFE".t_item_param_stock.item_size IS '尺码';
COMMENT ON COLUMN "LIFE".t_item_param_stock.stocknum IS '库存';
COMMENT ON COLUMN "LIFE".t_item_param_stock.item_type IS '类型(0 男鞋 1女鞋 2 童鞋)';
COMMENT ON COLUMN "LIFE".t_item_param_stock.remark IS '备注';