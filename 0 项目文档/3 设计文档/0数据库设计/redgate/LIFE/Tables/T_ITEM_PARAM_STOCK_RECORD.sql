CREATE TABLE "LIFE".t_item_param_stock_record (
  "ID" NUMBER(8) NOT NULL,
  stock_id NUMBER(8) NOT NULL,
  remark VARCHAR2(200 BYTE) NOT NULL,
  "TYPE" NUMBER(1),
  quantity NUMBER(8) DEFAULT 1,
  item_color VARCHAR2(50 BYTE),
  item_size VARCHAR2(50 BYTE),
  item_brand VARCHAR2(50 BYTE),
  create_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_item_param_stock_record PRIMARY KEY ("ID",stock_id)
);
COMMENT ON COLUMN "LIFE".t_item_param_stock_record."ID" IS '流水号';
COMMENT ON COLUMN "LIFE".t_item_param_stock_record.stock_id IS '商品参数编号';
COMMENT ON COLUMN "LIFE".t_item_param_stock_record.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_item_param_stock_record."TYPE" IS '操作类型(0：扣减库存；1：释放库存)';
COMMENT ON COLUMN "LIFE".t_item_param_stock_record.quantity IS '库存量';
COMMENT ON COLUMN "LIFE".t_item_param_stock_record.item_color IS '颜色';
COMMENT ON COLUMN "LIFE".t_item_param_stock_record.item_size IS '尺码';
COMMENT ON COLUMN "LIFE".t_item_param_stock_record.item_brand IS '品牌';
COMMENT ON COLUMN "LIFE".t_item_param_stock_record.create_time IS '操作时间';