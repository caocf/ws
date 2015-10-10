CREATE TABLE "LIFE".t_gift_required_item (
  "ID" NUMBER(8) NOT NULL,
  batch_no NUMBER(8) NOT NULL,
  item_id NUMBER(8) NOT NULL,
  status NUMBER(1) DEFAULT 0 NOT NULL,
  audit_time VARCHAR2(14 BYTE),
  audit_msg VARCHAR2(100 BYTE),
  audit_user_id NUMBER(8),
  store_id NUMBER(8),
  created_time VARCHAR2(14 BYTE),
  created_user_id NUMBER(8),
  remark VARCHAR2(200 BYTE),
  dimcode_img VARCHAR2(200 BYTE),
  dimcode_wap_url VARCHAR2(250 BYTE),
  dimcode_status NUMBER(1) DEFAULT 0,
  dimcode_time VARCHAR2(14 BYTE),
  item_level NUMBER(4,2),
  CONSTRAINT pk_t_gift_required_item PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_gift_required_item IS '礼品卡需求和商品关系表';
COMMENT ON COLUMN "LIFE".t_gift_required_item.batch_no IS '卡批次号';
COMMENT ON COLUMN "LIFE".t_gift_required_item.item_id IS '商品ID';
COMMENT ON COLUMN "LIFE".t_gift_required_item.status IS '状态 -1：删除，0：草稿，1：待审核，2：审核通过';
COMMENT ON COLUMN "LIFE".t_gift_required_item.audit_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_gift_required_item.audit_msg IS '审核意见';
COMMENT ON COLUMN "LIFE".t_gift_required_item.audit_user_id IS '审核人用户ID';
COMMENT ON COLUMN "LIFE".t_gift_required_item.store_id IS '商户编号';
COMMENT ON COLUMN "LIFE".t_gift_required_item.created_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_gift_required_item.created_user_id IS '绑定者用户ID';
COMMENT ON COLUMN "LIFE".t_gift_required_item.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_gift_required_item.dimcode_img IS '二维码图片路径';
COMMENT ON COLUMN "LIFE".t_gift_required_item.dimcode_wap_url IS '二维码wap访问地址';
COMMENT ON COLUMN "LIFE".t_gift_required_item.dimcode_status IS '生成二维码状态 0：未生成，1：已生成';
COMMENT ON COLUMN "LIFE".t_gift_required_item.dimcode_time IS '生成二维码时间';
COMMENT ON COLUMN "LIFE".t_gift_required_item.item_level IS '星级: 0.5半颗星， 1：一颗星，1.5：一颗半星，2：两颗星...5:五颗星';