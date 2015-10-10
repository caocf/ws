CREATE TABLE "LIFE".t_market_content (
  "ID" VARCHAR2(2 BYTE) NOT NULL,
  title VARCHAR2(20 BYTE),
  last_modify_time VARCHAR2(20 BYTE),
  "CONTENT" CLOB,
  PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_market_content ADD SUPPLEMENTAL LOG GROUP ggs_240685 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_market_content IS '竞拍文案表';
COMMENT ON COLUMN "LIFE".t_market_content."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_market_content.title IS '文案标题';
COMMENT ON COLUMN "LIFE".t_market_content.last_modify_time IS '最后更新时间';
COMMENT ON COLUMN "LIFE".t_market_content."CONTENT" IS '文案内容';