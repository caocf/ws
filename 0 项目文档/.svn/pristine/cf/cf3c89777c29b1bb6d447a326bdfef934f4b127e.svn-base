CREATE TABLE "LIFE".t_sys_filter_word (
  "ID" NUMBER(9) NOT NULL,
  word VARCHAR2(50 BYTE),
  in_tag NUMBER(3),
  unit_id VARCHAR2(20 BYTE),
  update_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_sys_filter_word PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_filter_word ADD SUPPLEMENTAL LOG GROUP ggs_240438 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_filter_word IS '序号来源于序列：SEQ_FILTER_WORD;';
COMMENT ON COLUMN "LIFE".t_sys_filter_word.in_tag IS '0：手工输入
1：批量导入
如果是批量导入，则需要填写文件路径';
COMMENT ON COLUMN "LIFE".t_sys_filter_word.unit_id IS '通过序列产生，不足6位，前面不零';