CREATE TABLE "LIFE".t_wx_bm_recommend_cnf (
  "ID" NUMBER(3) NOT NULL,
  "TYPE" VARCHAR2(5 BYTE),
  "NAME" VARCHAR2(50 BYTE),
  status NUMBER(1) DEFAULT 1 NOT NULL,
  remark VARCHAR2(200 BYTE),
  create_time VARCHAR2(14 BYTE),
  update_time VARCHAR2(14 BYTE),
  update_user_id NUMBER(8),
  create_user_id NUMBER(8),
  CONSTRAINT pk_t_wx_bm_recommend_cnf PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_wx_bm_recommend_cnf IS '推荐配置表';
COMMENT ON COLUMN "LIFE".t_wx_bm_recommend_cnf."TYPE" IS '推荐类型 0：全部，1：外呼代客下单，2：营业厅代客下单';
COMMENT ON COLUMN "LIFE".t_wx_bm_recommend_cnf."NAME" IS '类型名称';
COMMENT ON COLUMN "LIFE".t_wx_bm_recommend_cnf.status IS '状态：-1：已删除，0：停用，1：启用';
COMMENT ON COLUMN "LIFE".t_wx_bm_recommend_cnf.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_wx_bm_recommend_cnf.update_time IS '修改时间';
COMMENT ON COLUMN "LIFE".t_wx_bm_recommend_cnf.update_user_id IS '修改人';
COMMENT ON COLUMN "LIFE".t_wx_bm_recommend_cnf.create_user_id IS '创建人';