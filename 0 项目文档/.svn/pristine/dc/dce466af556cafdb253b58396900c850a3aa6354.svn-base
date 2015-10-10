CREATE TABLE "LIFE".t_smsbuy_act_online (
  act_id NUMBER(12) NOT NULL,
  act_name VARCHAR2(50 BYTE) NOT NULL,
  act_desc VARCHAR2(500 BYTE),
  act_area VARCHAR2(100 BYTE),
  start_time CHAR(14 BYTE),
  end_time CHAR(14 BYTE),
  status VARCHAR2(15 BYTE),
  store_id NUMBER(8),
  is_hot NUMBER(1) DEFAULT 0 NOT NULL,
  sp_code VARCHAR2(20 BYTE),
  CONSTRAINT pk_t_smsbuy_act_online PRIMARY KEY (act_id)
);
ALTER TABLE "LIFE".t_smsbuy_act_online ADD SUPPLEMENTAL LOG GROUP ggs_240409 (act_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_smsbuy_act_online.act_area IS '0表示全省。
地市用区号表示，多个地市用逗号分隔。
';
COMMENT ON COLUMN "LIFE".t_smsbuy_act_online.status IS 'audit:待审核 online:审核通过 rebutAudit:审核驳回';
COMMENT ON COLUMN "LIFE".t_smsbuy_act_online.is_hot IS '是否是热门活动';
COMMENT ON COLUMN "LIFE".t_smsbuy_act_online.sp_code IS '特服号';