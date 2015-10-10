CREATE TABLE "LIFE".t_umc_user_msg (
  "ID" NUMBER(9),
  user_id NUMBER(9),
  create_time VARCHAR2(14 BYTE),
  message_content VARCHAR2(1000 BYTE),
  status NUMBER(3)
);
ALTER TABLE "LIFE".t_umc_user_msg ADD SUPPLEMENTAL LOG GROUP ggs_240468 (create_time, "ID", message_content, status, user_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_umc_user_msg IS '用户消息中心-用户消息';
COMMENT ON COLUMN "LIFE".t_umc_user_msg.status IS '0普通、1已读、2删除';