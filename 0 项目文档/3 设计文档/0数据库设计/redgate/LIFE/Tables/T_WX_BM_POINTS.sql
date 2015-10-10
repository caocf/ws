CREATE TABLE "LIFE".t_wx_bm_points (
  "ID" CHAR(10 BYTE) NOT NULL,
  item_id NUMBER(8) NOT NULL,
  item_name VARCHAR2(512 BYTE),
  points NUMBER(5) DEFAULT 0 NOT NULL,
  status NUMBER(1) DEFAULT 1 NOT NULL,
  remark VARCHAR2(200 BYTE),
  create_time VARCHAR2(14 BYTE),
  create_user_id NUMBER(8),
  update_time VARCHAR2(14 BYTE),
  update_user_id NUMBER(8),
  CONSTRAINT pk_t_wx_bm_points PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_wx_bm_points IS '产品积点表';
COMMENT ON COLUMN "LIFE".t_wx_bm_points.item_id IS '商品编号';
COMMENT ON COLUMN "LIFE".t_wx_bm_points.item_name IS '商品名称';
COMMENT ON COLUMN "LIFE".t_wx_bm_points.points IS '积点';
COMMENT ON COLUMN "LIFE".t_wx_bm_points.status IS '状态-1：已删除，0：停用，1:启用';
COMMENT ON COLUMN "LIFE".t_wx_bm_points.remark IS '备注';