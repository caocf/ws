CREATE TABLE "LIFE".t_wx_bm_hall (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(500 BYTE),
  region_code VARCHAR2(100 BYTE),
  remark VARCHAR2(200 BYTE),
  status NUMBER(1) DEFAULT 1 NOT NULL,
  create_time VARCHAR2(14 BYTE),
  create_user_id NUMBER(8),
  update_time VARCHAR2(14 BYTE),
  update_user_id NUMBER(8),
  CONSTRAINT pk_t_wx_bm_hall PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_wx_bm_hall IS '营业厅配置表';
COMMENT ON COLUMN "LIFE".t_wx_bm_hall."NAME" IS '营业厅名称';
COMMENT ON COLUMN "LIFE".t_wx_bm_hall.region_code IS '区域表ID';
COMMENT ON COLUMN "LIFE".t_wx_bm_hall.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_wx_bm_hall.status IS '状态 -1:已删除，0：停用 1：启用';