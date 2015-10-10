CREATE TABLE "LIFE".t_wx_bm_points_record (
  "ID" NUMBER(8) NOT NULL,
  job_no VARCHAR2(30 BYTE),
  act_order_id NUMBER(19),
  item_id NUMBER(8),
  item_name VARCHAR2(512 BYTE),
  item_number NUMBER(4),
  point_number NUMBER(5),
  remark VARCHAR2(200 BYTE),
  create_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_wx_bm_points_record PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_wx_bm_points_record IS '积点日志表';
COMMENT ON COLUMN "LIFE".t_wx_bm_points_record.job_no IS '营业员工号';
COMMENT ON COLUMN "LIFE".t_wx_bm_points_record.act_order_id IS '业务订单号';
COMMENT ON COLUMN "LIFE".t_wx_bm_points_record.item_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_wx_bm_points_record.item_name IS '商品名称';
COMMENT ON COLUMN "LIFE".t_wx_bm_points_record.item_number IS '商品数量';
COMMENT ON COLUMN "LIFE".t_wx_bm_points_record.point_number IS '新增积点数';
COMMENT ON COLUMN "LIFE".t_wx_bm_points_record.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_wx_bm_points_record.create_time IS '创建时间';