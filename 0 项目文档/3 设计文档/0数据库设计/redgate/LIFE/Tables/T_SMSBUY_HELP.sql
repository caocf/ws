CREATE TABLE "LIFE".t_smsbuy_help (
  help_spcode VARCHAR2(20 BYTE) NOT NULL,
  help_area VARCHAR2(100 BYTE) NOT NULL,
  recommend_type VARCHAR2(10 BYTE),
  recommend_spcode VARCHAR2(20 BYTE),
  recommend_sms VARCHAR2(300 BYTE),
  CONSTRAINT pk_t_smsbuy_help PRIMARY KEY (help_spcode,help_area)
);
ALTER TABLE "LIFE".t_smsbuy_help ADD SUPPLEMENTAL LOG GROUP ggs_240410 (help_area, help_spcode) ALWAYS;
COMMENT ON COLUMN "LIFE".t_smsbuy_help.help_spcode IS '可模糊匹配，精确匹配优先';
COMMENT ON COLUMN "LIFE".t_smsbuy_help.recommend_type IS 'priority：按优先级
hot：按最新商品
manual：按人工推荐';