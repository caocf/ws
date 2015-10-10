CREATE TABLE "LIFE".t_wx_bm_item (
  "ID" NUMBER(8) NOT NULL,
  item_id NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(512 BYTE),
  rec_id NUMBER(3),
  remark VARCHAR2(200 BYTE),
  status NUMBER(1) DEFAULT 1 NOT NULL,
  item_type_id NUMBER(8),
  store_id NUMBER(9),
  create_time VARCHAR2(14 BYTE),
  create_user_id NUMBER(8),
  update_time VARCHAR2(14 BYTE),
  update_user_id NUMBER(8),
  CONSTRAINT pk_t_wx_bm_item PRIMARY KEY ("ID",item_id)
);
COMMENT ON TABLE "LIFE".t_wx_bm_item IS '推荐商品表';
COMMENT ON COLUMN "LIFE".t_wx_bm_item.item_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_wx_bm_item."NAME" IS '商品名称';
COMMENT ON COLUMN "LIFE".t_wx_bm_item.rec_id IS '推荐类型Id';
COMMENT ON COLUMN "LIFE".t_wx_bm_item.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_wx_bm_item.status IS '状态 -1：已删除，1：有效';
COMMENT ON COLUMN "LIFE".t_wx_bm_item.item_type_id IS '商品分类ID';
COMMENT ON COLUMN "LIFE".t_wx_bm_item.store_id IS '商户ID';
COMMENT ON COLUMN "LIFE".t_wx_bm_item.update_time IS '修改时间';
COMMENT ON COLUMN "LIFE".t_wx_bm_item.update_user_id IS '修改人';