CREATE TABLE "LIFE".t_wx_bm_worker (
  "ID" NUMBER(8) NOT NULL,
  job_no VARCHAR2(30 BYTE),
  "NAME" VARCHAR2(30 BYTE),
  region_code VARCHAR2(100 BYTE),
  mall_id NUMBER(8),
  mall_name VARCHAR2(250 BYTE),
  status NUMBER(1) DEFAULT 1 NOT NULL,
  remark VARCHAR2(200 BYTE),
  create_time VARCHAR2(14 BYTE),
  create_user_id NUMBER(8),
  update_time VARCHAR2(14 BYTE),
  update_user_id NUMBER(8),
  CONSTRAINT pk_t_wx_bm_worker PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_wx_bm_worker IS '营业厅员工表';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.job_no IS '营业员工号';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker."NAME" IS '营业员姓名';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.region_code IS '所属区域';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.mall_id IS '营业厅编号';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.mall_name IS '营业厅名称';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.status IS '状态-1：已删除，0：停用，1：启用';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.create_time IS '创建日期';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.create_user_id IS '创建人';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.update_time IS '更新日期';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker.update_user_id IS '更新人';