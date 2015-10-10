CREATE TABLE "LIFE".t_shop_comment (
  "ID" NUMBER(8),
  "CONTENT" VARCHAR2(200 BYTE),
  update_time VARCHAR2(14 BYTE),
  user_id NUMBER(8),
  status NUMBER(1),
  shop_class NUMBER(1),
  shop_id NUMBER(8)
);
ALTER TABLE "LIFE".t_shop_comment ADD SUPPLEMENTAL LOG GROUP ggs_240394 ("CONTENT", "ID", shop_class, shop_id, status, update_time, user_id) ALWAYS;
COMMENT ON COLUMN "LIFE".t_shop_comment.status IS '0-未审核
1-审核通过
2-审核驳回';
COMMENT ON COLUMN "LIFE".t_shop_comment.shop_class IS '1-业务门店
2-商户
3-渠道商';