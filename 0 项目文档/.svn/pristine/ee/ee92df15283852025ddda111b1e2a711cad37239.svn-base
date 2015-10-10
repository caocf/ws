CREATE TABLE "LIFE".t_umc_public_msg (
  "ID" NUMBER(9),
  create_time VARCHAR2(14 BYTE),
  message_content VARCHAR2(1000 BYTE),
  status NUMBER(3)
);
ALTER TABLE "LIFE".t_umc_public_msg ADD SUPPLEMENTAL LOG GROUP ggs_240467 (create_time, "ID", message_content, status) ALWAYS;
COMMENT ON TABLE "LIFE".t_umc_public_msg IS '用户消息中心-公共消息';
COMMENT ON COLUMN "LIFE".t_umc_public_msg."ID" IS '消息Id';
COMMENT ON COLUMN "LIFE".t_umc_public_msg.create_time IS '消息创建时间，14位时间';
COMMENT ON COLUMN "LIFE".t_umc_public_msg.message_content IS '消息内容';
COMMENT ON COLUMN "LIFE".t_umc_public_msg.status IS '0普通、1已读、2删除，10以上自定义';