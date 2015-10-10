CREATE TABLE "LIFE".t_sys_logistics (
  "ID" NUMBER(9) NOT NULL,
  "NAME" VARCHAR2(50 BYTE),
  is_valid NUMBER(1),
  interface VARCHAR2(100 BYTE),
  ename VARCHAR2(30 BYTE),
  CONSTRAINT pk_t_sys_logistics PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_logistics ADD SUPPLEMENTAL LOG GROUP ggs_240443 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_logistics IS '物流信息表';
COMMENT ON COLUMN "LIFE".t_sys_logistics."NAME" IS '品牌名称';
COMMENT ON COLUMN "LIFE".t_sys_logistics.is_valid IS '是否有效
0-无效
1-有效';
COMMENT ON COLUMN "LIFE".t_sys_logistics.interface IS '物流接口';