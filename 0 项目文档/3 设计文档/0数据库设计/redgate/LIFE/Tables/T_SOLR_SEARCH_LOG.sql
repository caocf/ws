CREATE TABLE "LIFE".t_solr_search_log (
  "ID" NUMBER(9) NOT NULL,
  "NAME" VARCHAR2(60 BYTE) NOT NULL,
  region_code VARCHAR2(6 BYTE) NOT NULL,
  "COUNT" NUMBER(9) DEFAULT 0,
  "SOURCE" NUMBER(2) DEFAULT 1,
  marked NUMBER(1) DEFAULT 0,
  log_time VARCHAR2(14 BYTE) DEFAULT to_char(sysdate, 'yyyyMMddHH24miss'),
  CONSTRAINT pk_solr_search_log PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_solr_search_log IS '搜索历史记录表';
COMMENT ON COLUMN "LIFE".t_solr_search_log."ID" IS '主键，seq_solr_search_log';
COMMENT ON COLUMN "LIFE".t_solr_search_log."NAME" IS '用户的搜索词';
COMMENT ON COLUMN "LIFE".t_solr_search_log.region_code IS '地区码';
COMMENT ON COLUMN "LIFE".t_solr_search_log."COUNT" IS '搜索结果数量';
COMMENT ON COLUMN "LIFE".t_solr_search_log."SOURCE" IS '搜索渠道，1联合搜索 2代金券';
COMMENT ON COLUMN "LIFE".t_solr_search_log.marked IS '有效标记，用于管理后台功能';
COMMENT ON COLUMN "LIFE".t_solr_search_log.log_time IS '记录时间';