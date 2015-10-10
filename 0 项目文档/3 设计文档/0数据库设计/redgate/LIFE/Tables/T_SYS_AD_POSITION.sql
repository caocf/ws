CREATE TABLE "LIFE".t_sys_ad_position (
  "ID" NUMBER(9) NOT NULL,
  "NAME" VARCHAR2(100 BYTE),
  width NUMBER(9),
  height NUMBER(9),
  position VARCHAR2(20 BYTE),
  code VARCHAR2(100 BYTE),
  "TYPE" NUMBER(1),
  num NUMBER(2) DEFAULT 2 NOT NULL,
  CONSTRAINT pk_t_sys_ad_position PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_ad_position ADD SUPPLEMENTAL LOG GROUP ggs_240427 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_ad_position IS '广告位置配置';
COMMENT ON COLUMN "LIFE".t_sys_ad_position."NAME" IS '名称';
COMMENT ON COLUMN "LIFE".t_sys_ad_position.width IS '宽';
COMMENT ON COLUMN "LIFE".t_sys_ad_position.height IS '高';
COMMENT ON COLUMN "LIFE".t_sys_ad_position.position IS '模版位置';
COMMENT ON COLUMN "LIFE".t_sys_ad_position.code IS '模版代码';
COMMENT ON COLUMN "LIFE".t_sys_ad_position."TYPE" IS '模版类型
1-图片
2-文字
3-flash';