CREATE TABLE "LIFE".t_item_comment (
  "ID" NUMBER(8) NOT NULL,
  "CONTENT" VARCHAR2(500 BYTE),
  "TYPE" NUMBER(1) DEFAULT 1,
  question_type NUMBER(1),
  update_time VARCHAR2(14 BYTE),
  nickname VARCHAR2(100 BYTE),
  user_id NUMBER(19),
  status NUMBER(1),
  sale_id NUMBER(8),
  audit_user NUMBER(8),
  audit_time VARCHAR2(14 BYTE),
  "RANK" NUMBER(3),
  useful_num NUMBER(5) DEFAULT 0,
  useless_num NUMBER(5) DEFAULT 0,
  store_id NUMBER(8),
  act_order_id NUMBER(19),
  CONSTRAINT pk_t_item_comment PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_comment ADD SUPPLEMENTAL LOG GROUP ggs_240353 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_item_comment."CONTENT" IS '评论内容';
COMMENT ON COLUMN "LIFE".t_item_comment."TYPE" IS '评论类别
1-评论
2-咨询
';
COMMENT ON COLUMN "LIFE".t_item_comment.question_type IS '咨询类型
0-商品咨询
1-活动咨询';
COMMENT ON COLUMN "LIFE".t_item_comment.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_item_comment.nickname IS '评论人昵称';
COMMENT ON COLUMN "LIFE".t_item_comment.user_id IS '评论人ID';
COMMENT ON COLUMN "LIFE".t_item_comment.status IS '0-未审核
1-审核通过
2-审核驳回';
COMMENT ON COLUMN "LIFE".t_item_comment.sale_id IS '商品id ';
COMMENT ON COLUMN "LIFE".t_item_comment.audit_user IS '审核人';
COMMENT ON COLUMN "LIFE".t_item_comment.audit_time IS '审核时间';
COMMENT ON COLUMN "LIFE".t_item_comment."RANK" IS '评分';
COMMENT ON COLUMN "LIFE".t_item_comment.useful_num IS '有用
有用';
COMMENT ON COLUMN "LIFE".t_item_comment.useless_num IS '无用';
COMMENT ON COLUMN "LIFE".t_item_comment.store_id IS '商户id';
COMMENT ON COLUMN "LIFE".t_item_comment.act_order_id IS '订单id，用于评论';