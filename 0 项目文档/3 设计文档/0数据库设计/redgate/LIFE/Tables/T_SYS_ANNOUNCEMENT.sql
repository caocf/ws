CREATE TABLE "LIFE".t_sys_announcement (
  "ID" NUMBER(9) NOT NULL,
  title VARCHAR2(100 BYTE),
  description CLOB,
  pub_status NUMBER(1),
  pub_user NUMBER(9),
  pub_time VARCHAR2(14 BYTE),
  pub_dest NUMBER(1),
  audit_user NUMBER(9),
  audit_time VARCHAR2(14 BYTE),
  end_time VARCHAR2(14 BYTE),
  advise_pic VARCHAR2(200 BYTE),
  advise_extend_pic VARCHAR2(200 BYTE),
  file_path VARCHAR2(200 BYTE),
  CONSTRAINT pk_sys_announcement PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_announcement ADD SUPPLEMENTAL LOG GROUP ggs_240676 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_announcement IS '公告表';
COMMENT ON COLUMN "LIFE".t_sys_announcement."ID" IS 'id';
COMMENT ON COLUMN "LIFE".t_sys_announcement.title IS '标题';
COMMENT ON COLUMN "LIFE".t_sys_announcement.description IS '内容';
COMMENT ON COLUMN "LIFE".t_sys_announcement.pub_status IS '状态 0新建 1审核驳回 2已发布 3删除';
COMMENT ON COLUMN "LIFE".t_sys_announcement.pub_user IS '发布人';
COMMENT ON COLUMN "LIFE".t_sys_announcement.pub_time IS '发布时间';
COMMENT ON COLUMN "LIFE".t_sys_announcement.pub_dest IS '发布对象 0后台 1商户自服务 2个人中心 3门户';
COMMENT ON COLUMN "LIFE".t_sys_announcement.audit_user IS '审核人';
COMMENT ON COLUMN "LIFE".t_sys_announcement.audit_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_sys_announcement.end_time IS '结束时间';
COMMENT ON COLUMN "LIFE".t_sys_announcement.advise_pic IS '公告图';
COMMENT ON COLUMN "LIFE".t_sys_announcement.advise_extend_pic IS '拉伸公告图';
COMMENT ON COLUMN "LIFE".t_sys_announcement.file_path IS '附件绝对路径';