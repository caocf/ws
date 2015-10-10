CREATE TABLE "LIFE".t_smsbuy_item_router (
  "ID" NUMBER(12) NOT NULL,
  act_id NUMBER(12) NOT NULL,
  item_id VARCHAR2(30 BYTE) NOT NULL,
  item_name VARCHAR2(200 BYTE),
  sp_code VARCHAR2(20 BYTE),
  cmd_opt_type NUMBER(2),
  command VARCHAR2(50 BYTE),
  pay_type NUMBER(1),
  item_price NUMBER(9),
  is_session NUMBER(1),
  priority NUMBER(5) DEFAULT 100 NOT NULL,
  is_recommond NUMBER(1) DEFAULT 0 NOT NULL,
  recommond_content VARCHAR2(100 BYTE),
  marketmsg VARCHAR2(300 BYTE),
  item_status VARCHAR2(15 BYTE),
  CONSTRAINT pk_t_smsbuy_item_router PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_smsbuy_item_router ADD SUPPLEMENTAL LOG GROUP ggs_240412 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_smsbuy_item_router.cmd_opt_type IS '1：入口指令
2：其它指令';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_router.command IS '正则指令内容';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_router.pay_type IS '1：积分
2：商城币';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_router.is_session IS '0：不需要
1：需要';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_router.priority IS '优先级，0最高，99999最低';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_router.is_recommond IS '是否推荐，0否1是';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_router.recommond_content IS '推荐内容，当推荐此商品时，用此字段内容替换回复语中相关通配符';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_router.marketmsg IS '商品营销短信';
COMMENT ON COLUMN "LIFE".t_smsbuy_item_router.item_status IS 'audit:待审核 online:审核通过 rebutAudit:审核驳回';