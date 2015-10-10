CREATE TABLE "LIFE".t_store_settle (
  "ID" NUMBER(9) NOT NULL,
  store_id NUMBER(9) NOT NULL,
  merchid VARCHAR2(20 BYTE),
  settle_type NUMBER(1) DEFAULT 0 NOT NULL,
  fee_settle NUMBER(1) DEFAULT 1,
  settle_period NUMBER(1),
  settle_day NUMBER(3) DEFAULT 1,
  settle_daybit NUMBER(3),
  settle_trfdays NUMBER(3),
  settle_beginamt NUMBER(8) DEFAULT 0,
  min_retainedamt NUMBER(8),
  withdraw_bankid VARCHAR2(100 BYTE),
  openbank_desc VARCHAR2(200 BYTE),
  settle_ac VARCHAR2(100 BYTE),
  bank_acname VARCHAR2(100 BYTE),
  effort_date VARCHAR2(8 BYTE),
  expiry_date VARCHAR2(8 BYTE),
  sync_gy_flag NUMBER(1) DEFAULT 0,
  status NUMBER(1) DEFAULT 0,
  create_time VARCHAR2(14 BYTE),
  create_user NUMBER(9),
  sync_time VARCHAR2(14 BYTE),
  is_bs_account NUMBER(1),
  CONSTRAINT pk_t_store_settle PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_store_settle ADD SUPPLEMENTAL LOG GROUP ggs_240425 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_store_settle.store_id IS '商户id';
COMMENT ON COLUMN "LIFE".t_store_settle.merchid IS '高阳结算id';
COMMENT ON COLUMN "LIFE".t_store_settle.settle_type IS '结算类型
0：系统结算
1：手工结算';
COMMENT ON COLUMN "LIFE".t_store_settle.fee_settle IS '佣金结算
0：收支两条线
1：作扣';
COMMENT ON COLUMN "LIFE".t_store_settle.settle_period IS '结算周期
0：日
1：周
2：旬
3：月
4：季
5：半年
6：年
7：指定日
';
COMMENT ON COLUMN "LIFE".t_store_settle.settle_day IS '结算日
标识结算间隔的数量';
COMMENT ON COLUMN "LIFE".t_store_settle.settle_daybit IS '结算日标志位
指定结算日时设置一个月31天的具体日期';
COMMENT ON COLUMN "LIFE".t_store_settle.settle_trfdays IS '结算划款天数';
COMMENT ON COLUMN "LIFE".t_store_settle.settle_beginamt IS '结算起始金额';
COMMENT ON COLUMN "LIFE".t_store_settle.min_retainedamt IS '最低留存金额';
COMMENT ON COLUMN "LIFE".t_store_settle.withdraw_bankid IS '结算银行的名称';
COMMENT ON COLUMN "LIFE".t_store_settle.openbank_desc IS '开户行详细信息';
COMMENT ON COLUMN "LIFE".t_store_settle.settle_ac IS '结算账户';
COMMENT ON COLUMN "LIFE".t_store_settle.bank_acname IS '开户人';
COMMENT ON COLUMN "LIFE".t_store_settle.effort_date IS '生效日期';
COMMENT ON COLUMN "LIFE".t_store_settle.expiry_date IS '失效日期';
COMMENT ON COLUMN "LIFE".t_store_settle.sync_gy_flag IS '同步高阳状态：0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';
COMMENT ON COLUMN "LIFE".t_store_settle.status IS '状态 0未审核  3审核通过 4审核驳回';
COMMENT ON COLUMN "LIFE".t_store_settle.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_store_settle.create_user IS '创建人';
COMMENT ON COLUMN "LIFE".t_store_settle.sync_time IS '同步时间';
COMMENT ON COLUMN "LIFE".t_store_settle.is_bs_account IS '0--不是 1--是';