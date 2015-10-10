CREATE TABLE "LIFE".t_page_module_data (
  "ID" NUMBER(8) NOT NULL,
  module_id NUMBER(8),
  "CONTENT" VARCHAR2(3000 BYTE),
  status CHAR,
  sort_no NUMBER(6),
  region_code VARCHAR2(200 BYTE),
  start_time VARCHAR2(14 BYTE),
  end_time VARCHAR2(14 BYTE),
  templet_id NUMBER(8),
  create_user NUMBER(9),
  create_time VARCHAR2(14 BYTE),
  update_user NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  audit_user NUMBER(9),
  audit_time VARCHAR2(14 BYTE),
  audit_advice VARCHAR2(200 BYTE),
  module_code VARCHAR2(20 BYTE),
  content_title VARCHAR2(200 BYTE),
  CONSTRAINT pk_t_page_module_data PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_page_module_data IS '模块数据信息表';
COMMENT ON COLUMN "LIFE".t_page_module_data.module_id IS '模块ID';
COMMENT ON COLUMN "LIFE".t_page_module_data."CONTENT" IS '内容';
COMMENT ON COLUMN "LIFE".t_page_module_data.status IS '状态 0：未审核 1：审核通过 2：审核驳回';
COMMENT ON COLUMN "LIFE".t_page_module_data.sort_no IS '排序';
COMMENT ON COLUMN "LIFE".t_page_module_data.region_code IS '省地市编码';
COMMENT ON COLUMN "LIFE".t_page_module_data.start_time IS '开始时间';
COMMENT ON COLUMN "LIFE".t_page_module_data.end_time IS '结束时间';
COMMENT ON COLUMN "LIFE".t_page_module_data.templet_id IS '关联数据模板';
COMMENT ON COLUMN "LIFE".t_page_module_data.create_user IS '创建人';
COMMENT ON COLUMN "LIFE".t_page_module_data.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_page_module_data.update_user IS '修改人';
COMMENT ON COLUMN "LIFE".t_page_module_data.update_time IS '修改时间';
COMMENT ON COLUMN "LIFE".t_page_module_data.audit_user IS '审核人';
COMMENT ON COLUMN "LIFE".t_page_module_data.audit_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_page_module_data.audit_advice IS '审核意见';
COMMENT ON COLUMN "LIFE".t_page_module_data.module_code IS '模块代码';
COMMENT ON COLUMN "LIFE".t_page_module_data.content_title IS '内容标题';