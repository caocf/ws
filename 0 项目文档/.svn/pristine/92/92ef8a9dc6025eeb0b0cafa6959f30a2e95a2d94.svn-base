CREATE TABLE "LIFE".t_wx_bm_worker_privilege (
  job_no VARCHAR2(30 BYTE) NOT NULL,
  menu_code VARCHAR2(20 BYTE) NOT NULL,
  create_time VARCHAR2(14 BYTE),
  create_user_id NUMBER(8),
  status NUMBER(1) DEFAULT 1 NOT NULL,
  update_time VARCHAR2(14 BYTE),
  update_user_id NUMBER(8),
  CONSTRAINT pk_t_wx_bm_worker_privilege PRIMARY KEY (job_no,menu_code)
);
COMMENT ON TABLE "LIFE".t_wx_bm_worker_privilege IS '营业员权限表';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_privilege.job_no IS '营业员工号';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_privilege.menu_code IS '菜单编号';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_privilege.status IS '状态 -1：已删除，0：停用，1：启用';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_privilege.update_time IS '更新日期';
COMMENT ON COLUMN "LIFE".t_wx_bm_worker_privilege.update_user_id IS '更新人';