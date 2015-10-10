CREATE TABLE "LIFE".t_hisun_production_link (
  "ID" NUMBER(9) NOT NULL,
  settle_id NUMBER(9),
  production_id VARCHAR2(32 BYTE),
  item_id NUMBER(9),
  production_type VARCHAR2(32 BYTE),
  settle_price NUMBER(9,2),
  create_time VARCHAR2(14 BYTE),
  production_id_cash VARCHAR2(32 BYTE),
  production_id_coin VARCHAR2(32 BYTE),
  production_id_score VARCHAR2(32 BYTE),
  CONSTRAINT pk_t_hisun_production_link PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_hisun_production_link ADD SUPPLEMENTAL LOG GROUP ggs_240351 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_hisun_production_link.settle_id IS '商品结算资料表id';
COMMENT ON COLUMN "LIFE".t_hisun_production_link.production_id IS '高阳商品id';
COMMENT ON COLUMN "LIFE".t_hisun_production_link.item_id IS '销售商品id';
COMMENT ON COLUMN "LIFE".t_hisun_production_link.production_type IS '对应t_sys_fee的id';
COMMENT ON COLUMN "LIFE".t_hisun_production_link.settle_price IS '结算价 单位分';
COMMENT ON COLUMN "LIFE".t_hisun_production_link.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_hisun_production_link.production_id_cash IS '高阳商品id(现金)';
COMMENT ON COLUMN "LIFE".t_hisun_production_link.production_id_coin IS '高阳商品id(商城币)';
COMMENT ON COLUMN "LIFE".t_hisun_production_link.production_id_score IS '高阳商品id(积分)';