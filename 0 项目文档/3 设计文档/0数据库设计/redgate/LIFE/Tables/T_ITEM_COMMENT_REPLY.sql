CREATE TABLE "LIFE".t_item_comment_reply (
  "ID" NUMBER(9) NOT NULL,
  "CONTENT" VARCHAR2(500 BYTE),
  update_time VARCHAR2(14 BYTE),
  nickname VARCHAR2(100 BYTE),
  user_id NUMBER(19),
  comment_id NUMBER(9),
  CONSTRAINT pk_t_item_comment_reply PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_item_comment_reply ADD SUPPLEMENTAL LOG GROUP ggs_240354 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_item_comment_reply IS '商品回复表';
COMMENT ON COLUMN "LIFE".t_item_comment_reply."CONTENT" IS '回复内容';
COMMENT ON COLUMN "LIFE".t_item_comment_reply.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_item_comment_reply.nickname IS '评论人昵称';
COMMENT ON COLUMN "LIFE".t_item_comment_reply.user_id IS '评论人ID';
COMMENT ON COLUMN "LIFE".t_item_comment_reply.comment_id IS '评论id';