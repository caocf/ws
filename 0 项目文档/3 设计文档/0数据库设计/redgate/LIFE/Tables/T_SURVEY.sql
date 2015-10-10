CREATE TABLE "LIFE".t_survey (
  "ID" NUMBER(9) NOT NULL,
  s_questionid NUMBER(3) NOT NULL,
  s_answer VARCHAR2(500 BYTE),
  user_id NUMBER(9),
  s_surveyid NUMBER(2),
  CONSTRAINT pk_t_survey PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_survey ADD SUPPLEMENTAL LOG GROUP ggs_240492 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_survey IS '问卷调查结果表';
COMMENT ON COLUMN "LIFE".t_survey."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_survey.s_questionid IS '调查问卷问题编号';
COMMENT ON COLUMN "LIFE".t_survey.s_answer IS '调查问卷答案';
COMMENT ON COLUMN "LIFE".t_survey.user_id IS '用户ID';
COMMENT ON COLUMN "LIFE".t_survey.s_surveyid IS '调查问卷编号';