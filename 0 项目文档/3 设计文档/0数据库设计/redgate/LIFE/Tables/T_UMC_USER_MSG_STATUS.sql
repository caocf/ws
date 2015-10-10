CREATE TABLE "LIFE".t_umc_user_msg_status (
  user_id NUMBER(9),
  msg_id NUMBER(9),
  status NUMBER(3)
);
ALTER TABLE "LIFE".t_umc_user_msg_status ADD SUPPLEMENTAL LOG GROUP ggs_240469 (msg_id, status, user_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_umc_user_msg_status IS '用户消息中心-用户消息状态';
COMMENT ON COLUMN "LIFE".t_umc_user_msg_status.user_id IS '用户Id';
COMMENT ON COLUMN "LIFE".t_umc_user_msg_status.msg_id IS '消息Id';
COMMENT ON COLUMN "LIFE".t_umc_user_msg_status.status IS '0普通、1已读、2删除';