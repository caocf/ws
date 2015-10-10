CREATE TABLE "LIFE".t_item_comment_temp (
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
  reply_comment VARCHAR2(500 BYTE),
  reply_time VARCHAR2(14 BYTE)
);
ALTER TABLE "LIFE".t_item_comment_temp ADD SUPPLEMENTAL LOG GROUP ggs_240499 (audit_time, audit_user, "CONTENT", "ID", nickname, question_type, "RANK", reply_comment, reply_time, sale_id, status, store_id, "TYPE", update_time, useful_num, useless_num, user_id) ALWAYS;