CREATE TABLE "LIFE".t_cont_code (
  "ID" NUMBER(9) NOT NULL,
  code VARCHAR2(20 BYTE) NOT NULL,
  "NAME" VARCHAR2(100 BYTE) NOT NULL,
  area_code VARCHAR2(8 BYTE),
  cont_type NUMBER(1) DEFAULT 1 NOT NULL,
  code_type NUMBER(2) DEFAULT 0 NOT NULL,
  CONSTRAINT pk_12580_cont_code PRIMARY KEY ("ID"),
  CONSTRAINT uk_12580_cont_code UNIQUE (code)
);
ALTER TABLE "LIFE".t_cont_code ADD SUPPLEMENTAL LOG GROUP ggs_240340 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_cont_code IS '12580信息内容源表';
COMMENT ON COLUMN "LIFE".t_cont_code."ID" IS '内容源ID，使用序列SEQ_CONT_CODE';
COMMENT ON COLUMN "LIFE".t_cont_code.code IS '内容源英文标识，唯一';
COMMENT ON COLUMN "LIFE".t_cont_code."NAME" IS '内容源名称';
COMMENT ON COLUMN "LIFE".t_cont_code.area_code IS '内容源归属地市,例如025';
COMMENT ON COLUMN "LIFE".t_cont_code.cont_type IS '内容源信息类型，0表示短信，1表示彩信';
COMMENT ON COLUMN "LIFE".t_cont_code.code_type IS '内容源使用类型，0表示点播，1表示定制,3 折扣券短信,4挂机短信,5 挂机彩信, 6,广告彩信,7 折扣券彩信,8 种子彩信,9 群发,10 添加折扣券彩信时录入短信';