CREATE TABLE "LIFE".t_store_agent (
  "ID" NUMBER(8) NOT NULL,
  qd_store_id NUMBER(8),
  js_store_id NUMBER(8),
  start_time VARCHAR2(14 BYTE),
  stop_time VARCHAR2(14 BYTE),
  status NUMBER(1),
  sys_user_id NUMBER(8),
  shop_agent_flag NUMBER(1),
  goods_agent_flag NUMBER(1),
  CONSTRAINT pk_t_store_agent PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_store_agent ADD SUPPLEMENTAL LOG GROUP ggs_240422 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_store_agent IS '渠道商代理结算商户关系表';
COMMENT ON COLUMN "LIFE".t_store_agent.status IS '0--草稿
1--待审核
2--审核中
3--审核通过
4--审核驳回';
COMMENT ON COLUMN "LIFE".t_store_agent.shop_agent_flag IS '是否折扣商户代理 0-否 1-是';
COMMENT ON COLUMN "LIFE".t_store_agent.goods_agent_flag IS '是否商品销售代理 0-否 1-是';