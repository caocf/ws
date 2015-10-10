CREATE TABLE "LIFE".t_gift_required (
  batch_no NUMBER(8) NOT NULL,
  model_no VARCHAR2(200 BYTE) NOT NULL,
  card_num NUMBER(7) DEFAULT 0 NOT NULL,
  issuing_time VARCHAR2(14 BYTE) NOT NULL,
  effort_date VARCHAR2(14 BYTE) NOT NULL,
  expiry_date VARCHAR2(14 BYTE) NOT NULL,
  exchange_mode NUMBER(2) DEFAULT 1 NOT NULL,
  status NUMBER(1) DEFAULT 0 NOT NULL,
  card_face_msg VARCHAR2(250 BYTE),
  print_face_status NUMBER(1) DEFAULT 0 NOT NULL,
  bind_item_status NUMBER(1) DEFAULT 0 NOT NULL,
  make_card_status NUMBER(1),
  par_value NUMBER(9),
  remark VARCHAR2(200 BYTE),
  audit_time VARCHAR2(14 BYTE),
  audit_msg VARCHAR2(100 BYTE),
  audit_user_id NUMBER(8),
  created_time VARCHAR2(14 BYTE),
  create_user_id NUMBER(8),
  required_user VARCHAR2(20 BYTE),
  face_img VARCHAR2(200 BYTE),
  region_code VARCHAR2(100 BYTE),
  store_id NUMBER(8),
  sale_id NUMBER(8),
  cutoff_day VARCHAR2(14 BYTE),
  exchange_type NUMBER(1) DEFAULT 0 NOT NULL,
  req_level NUMBER(4,2),
  CONSTRAINT pk_t_gift_required PRIMARY KEY (batch_no)
);
COMMENT ON TABLE "LIFE".t_gift_required IS '礼品卡需求表';
COMMENT ON COLUMN "LIFE".t_gift_required.batch_no IS '批次号';
COMMENT ON COLUMN "LIFE".t_gift_required.model_no IS '卡型号';
COMMENT ON COLUMN "LIFE".t_gift_required.card_num IS '礼品卡需求数量';
COMMENT ON COLUMN "LIFE".t_gift_required.issuing_time IS '期望发卡时间';
COMMENT ON COLUMN "LIFE".t_gift_required.effort_date IS '礼品卡生效日期';
COMMENT ON COLUMN "LIFE".t_gift_required.expiry_date IS '礼品卡失效日期';
COMMENT ON COLUMN "LIFE".t_gift_required.exchange_mode IS '礼品兑换方式 1：一兑一，2：二兑一，3：三兑一，4：四兑一，类似这样';
COMMENT ON COLUMN "LIFE".t_gift_required.status IS '状态  -1：已删除，0：草稿，1：待审核，2：审核通过';
COMMENT ON COLUMN "LIFE".t_gift_required.card_face_msg IS '卡面封套要求';
COMMENT ON COLUMN "LIFE".t_gift_required.print_face_status IS '封套印刷状态  0：未印刷，1：印刷中，2：印刷完成';
COMMENT ON COLUMN "LIFE".t_gift_required.bind_item_status IS '是否已绑定商品 0：未绑定，1：已绑定';
COMMENT ON COLUMN "LIFE".t_gift_required.make_card_status IS '制卡状态 0：未制卡，1：已制卡';
COMMENT ON COLUMN "LIFE".t_gift_required.par_value IS '面额';
COMMENT ON COLUMN "LIFE".t_gift_required.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_gift_required.audit_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_gift_required.audit_msg IS '审核意见';
COMMENT ON COLUMN "LIFE".t_gift_required.audit_user_id IS '审核人用户ID';
COMMENT ON COLUMN "LIFE".t_gift_required.created_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_gift_required.create_user_id IS '创建人用户ID';
COMMENT ON COLUMN "LIFE".t_gift_required.required_user IS '需求提出客户名称';
COMMENT ON COLUMN "LIFE".t_gift_required.face_img IS '卡封面图';
COMMENT ON COLUMN "LIFE".t_gift_required.region_code IS '所属区域';
COMMENT ON COLUMN "LIFE".t_gift_required.cutoff_day IS '回传与下载时用的时间戳';
COMMENT ON COLUMN "LIFE".t_gift_required.exchange_type IS '0:单一兑换，1：星级兑换';
COMMENT ON COLUMN "LIFE".t_gift_required.req_level IS '星级: 0.5半颗星， 1：一颗星，1.5：一颗半星，2：两颗星...5:五颗星';