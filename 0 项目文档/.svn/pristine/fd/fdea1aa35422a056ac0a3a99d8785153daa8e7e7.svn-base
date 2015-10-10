CREATE TABLE "LIFE".t_wx_bm_worker_points (
  job_no VARCHAR2(30 BYTE) NOT NULL,
  points NUMBER(9) DEFAULT 0 NOT NULL,
  remark VARCHAR2(200 BYTE),
  create_time VARCHAR2(14 BYTE),
  update_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_wx_bm_worker_points PRIMARY KEY (job_no)
);
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_points.job_no IS '关联t_worker表的工号';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_points.points IS '员工积点数';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_points.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_points.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_points.update_time IS '最近更新时间';