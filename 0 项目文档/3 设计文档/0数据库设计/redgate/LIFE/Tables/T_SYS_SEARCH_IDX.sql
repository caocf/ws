CREATE TABLE "LIFE".t_sys_search_idx (
  "ID" NUMBER(8) NOT NULL,
  "TYPE" VARCHAR2(10 BYTE),
  type_name VARCHAR2(20 BYTE),
  update_time VARCHAR2(14 BYTE),
  actor NUMBER(8),
  CONSTRAINT sys_search_idx PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_search_idx ADD SUPPLEMENTAL LOG GROUP ggs_240536 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sys_search_idx."TYPE" IS '类型';
COMMENT ON COLUMN "LIFE".t_sys_search_idx.type_name IS '类型名称';
COMMENT ON COLUMN "LIFE".t_sys_search_idx.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_sys_search_idx.actor IS '创建者ID';