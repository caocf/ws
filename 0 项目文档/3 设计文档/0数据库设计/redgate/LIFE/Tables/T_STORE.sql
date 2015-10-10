CREATE TABLE "LIFE".t_store (
  "ID" NUMBER(8) NOT NULL,
  "NAME" VARCHAR2(50 BYTE),
  short_name VARCHAR2(20 BYTE),
  area_code VARCHAR2(10 BYTE),
  "SORT" NUMBER(1),
  shop_agent_flag NUMBER(1),
  goods_agent_flag NUMBER(1),
  base_shop_name VARCHAR2(50 BYTE),
  create_time VARCHAR2(14 BYTE),
  update_time VARCHAR2(14 BYTE),
  address VARCHAR2(100 BYTE),
  sys_user_id NUMBER(8),
  update_user_id NUMBER(9),
  is_bs_account NUMBER(1),
  bs_manager_name VARCHAR2(50 BYTE),
  bs_manager_phone VARCHAR2(50 BYTE),
  fc_manager_name VARCHAR2(50 BYTE),
  fc_manager_phone VARCHAR2(50 BYTE),
  link_name VARCHAR2(50 BYTE),
  link_phone VARCHAR2(50 BYTE),
  link_fax VARCHAR2(50 BYTE),
  shop_class NUMBER(1),
  status NUMBER(1),
  is_valid NUMBER(1),
  item_edit_audit_flag NUMBER(1),
  item_publish_audit_flag NUMBER(1),
  shop_edit_audit_flag NUMBER(1),
  sync_gy_flag NUMBER(1),
  bs_scope VARCHAR2(200 BYTE),
  service_phone VARCHAR2(50 BYTE),
  area_id VARCHAR2(20 BYTE),
  merchid VARCHAR2(20 BYTE),
  stop_time VARCHAR2(14 BYTE),
  start_time VARCHAR2(14 BYTE),
  account_type NUMBER(1) DEFAULT 0,
  CONSTRAINT pk_t_store PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_store ADD SUPPLEMENTAL LOG GROUP ggs_240421 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_store IS '包括代理商和商户在此表维护';
COMMENT ON COLUMN "LIFE".t_store.area_code IS '0--失效 1--有效';
COMMENT ON COLUMN "LIFE".t_store."SORT" IS '0--非签约 1--签约';
COMMENT ON COLUMN "LIFE".t_store.shop_agent_flag IS '是否折扣商户代理 0-否 1-是';
COMMENT ON COLUMN "LIFE".t_store.goods_agent_flag IS '是否商品销售代理 0-否 1-是';
COMMENT ON COLUMN "LIFE".t_store.base_shop_name IS '（废弃）';
COMMENT ON COLUMN "LIFE".t_store.is_bs_account IS '0--不是 1--是(废弃 ，放在t_store_settle表中)';
COMMENT ON COLUMN "LIFE".t_store.shop_class IS '1--业务门店 2--结算商户 3--渠道商';
COMMENT ON COLUMN "LIFE".t_store.status IS '0--草稿 1 待审核 2 审核中  3-审核通过 4-审核驳回 ';
COMMENT ON COLUMN "LIFE".t_store.is_valid IS '0--下架 1--上架';
COMMENT ON COLUMN "LIFE".t_store.item_edit_audit_flag IS '0--需要审核 1--免审';
COMMENT ON COLUMN "LIFE".t_store.item_publish_audit_flag IS '0--需要审核 1--免审';
COMMENT ON COLUMN "LIFE".t_store.shop_edit_audit_flag IS '0--需要审核 1--免审';
COMMENT ON COLUMN "LIFE".t_store.sync_gy_flag IS ' 同步高阳状态  0：未同步 1：已同步 2：销户 3：审核通过  4：取消/删除  5： 待一级审核  6：一级审核退回  7：待财务复核  8： 财务复核退回
';
COMMENT ON COLUMN "LIFE".t_store.area_id IS '类似 320500';
COMMENT ON COLUMN "LIFE".t_store.merchid IS '高阳结算ID';
COMMENT ON COLUMN "LIFE".t_store.stop_time IS '代理结束时间';
COMMENT ON COLUMN "LIFE".t_store.start_time IS '代理开始时间';
COMMENT ON COLUMN "LIFE".t_store.account_type IS '帐号来源：0：移动账户，1：宽连账户';