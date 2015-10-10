CREATE TABLE "LIFE".t_store_fee (
  "ID" NUMBER(9) NOT NULL,
  store_id NUMBER(9),
  merchid VARCHAR2(20 BYTE),
  capital_type1 NUMBER(1) DEFAULT 0,
  capital_type2 NUMBER(1) DEFAULT 0,
  capital_type3 NUMBER(1) DEFAULT 0,
  trade_mode NUMBER(1) DEFAULT 0,
  fee_type VARCHAR2(2 BYTE) DEFAULT '01',
  production_type VARCHAR2(10 BYTE) DEFAULT '01',
  clear_type VARCHAR2(2 BYTE),
  effortdate VARCHAR2(8 BYTE),
  expirydate VARCHAR2(8 BYTE),
  feedrtflag VARCHAR2(1 BYTE),
  feeperiodunit VARCHAR2(50 BYTE),
  fc_name VARCHAR2(50 BYTE),
  feemode NUMBER(1),
  feemothod NUMBER(1),
  beganamount VARCHAR2(13 BYTE),
  minfeeamount VARCHAR2(11 BYTE),
  maxfeeamount VARCHAR2(11 BYTE),
  feebasicflag NUMBER(1),
  feelevelflag VARCHAR2(1 BYTE),
  feelvlbasicflag VARCHAR2(1 BYTE),
  upreference1 VARCHAR2(11 BYTE),
  fixfeeamount1 VARCHAR2(9 BYTE),
  feerate1 VARCHAR2(5 BYTE),
  upreference2 VARCHAR2(11 BYTE),
  fixfeeamount2 VARCHAR2(9 BYTE),
  feerate2 VARCHAR2(5 BYTE),
  upreference3 VARCHAR2(11 BYTE),
  fixfeeamount3 VARCHAR2(9 BYTE),
  feerate3 VARCHAR2(5 BYTE),
  upreference4 VARCHAR2(11 BYTE),
  fixfeeamount4 VARCHAR2(9 BYTE),
  feerate4 VARCHAR2(5 BYTE),
  upreference5 VARCHAR2(11 BYTE),
  fixfeeamount5 VARCHAR2(9 BYTE),
  feerate5 VARCHAR2(5 BYTE),
  create_time VARCHAR2(14 BYTE),
  sync_time VARCHAR2(14 BYTE),
  create_user NUMBER(9),
  status NUMBER(1) DEFAULT 0,
  sync_gy_flag1 NUMBER(1) DEFAULT 0,
  sync_gy_flag2 NUMBER(1) DEFAULT 0,
  sync_gy_flag3 NUMBER(1) DEFAULT 0,
  capital_type4 NUMBER(1) DEFAULT 0,
  sync_gy_flag4 NUMBER(1) DEFAULT 0,
  phone_effortdate VARCHAR2(8 BYTE),
  capital_type5 NUMBER(1) DEFAULT 0,
  sync_gy_flag5 NUMBER(1) DEFAULT 0,
  CONSTRAINT pk_t_store_fee PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_store_fee ADD SUPPLEMENTAL LOG GROUP ggs_240423 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_store_fee IS '商户费率信息表';
COMMENT ON COLUMN "LIFE".t_store_fee.store_id IS '商户id';
COMMENT ON COLUMN "LIFE".t_store_fee.merchid IS '高阳结算id';
COMMENT ON COLUMN "LIFE".t_store_fee.capital_type1 IS '是否现金 1：是  0：否';
COMMENT ON COLUMN "LIFE".t_store_fee.capital_type2 IS '是否商城币1:是  0：否';
COMMENT ON COLUMN "LIFE".t_store_fee.capital_type3 IS '是否积分  1：是  0：否';
COMMENT ON COLUMN "LIFE".t_store_fee.trade_mode IS '交易方式
0-远程普通
1：现场脱机；
3：远程大额；
4：现场联机；（备用）';
COMMENT ON COLUMN "LIFE".t_store_fee.fee_type IS '费用类型
01：消费佣金
02：渠道佣金
 (没，用的下面的production——type)';
COMMENT ON COLUMN "LIFE".t_store_fee.production_type IS '对应t_sys_fee表的id';
COMMENT ON COLUMN "LIFE".t_store_fee.clear_type IS '清算类别
C：商品类别 (同步商品类别，按照类别结算费率)
P：商品
';
COMMENT ON COLUMN "LIFE".t_store_fee.effortdate IS '生效日期';
COMMENT ON COLUMN "LIFE".t_store_fee.expirydate IS '失效日期';
COMMENT ON COLUMN "LIFE".t_store_fee.feedrtflag IS '费用方向
D：商户/代理点支付费用
C：商户/代理点收入费用
';
COMMENT ON COLUMN "LIFE".t_store_fee.feeperiodunit IS '收费周期单位
0: 年
1: 月
2: 日';
COMMENT ON COLUMN "LIFE".t_store_fee.fc_name IS '收费周期数量';
COMMENT ON COLUMN "LIFE".t_store_fee.feemode IS '收费方式
0：不收取
1：实时单笔收取
2：单笔计算、按周期收取
3：按周期汇总计算并收取
4：按周期汇总轧差并收取';
COMMENT ON COLUMN "LIFE".t_store_fee.feemothod IS '计费方法
:1:按固定金额收取
2:按金额固定百分比收取 （需要确认）
3:按照商品差价计算 （团购）';
COMMENT ON COLUMN "LIFE".t_store_fee.beganamount IS '计费起始金额(笔数)
如果费用计算依据为笔数，不允许输入，如不输入则系统设置为0没填';
COMMENT ON COLUMN "LIFE".t_store_fee.minfeeamount IS '最低收费金额
收费方式=1时不允许输入；计费方法=2/3时可以输入，如不输入则系统设置为0没填';
COMMENT ON COLUMN "LIFE".t_store_fee.maxfeeamount IS '最高收费金额
收费方式=1时不允许输入；如不输入则系统设置为全9最大数没填';
COMMENT ON COLUMN "LIFE".t_store_fee.feebasicflag IS '费用计算依据
0：金额  1：笔数跟计费方法1对应）';
COMMENT ON COLUMN "LIFE".t_store_fee.feelevelflag IS '分层/套档标志
空格：表示只有一档；0：套档，1：分层；最多可分5层/档。
本字段没有输入的情况下，只有一档费用计算参数';
COMMENT ON COLUMN "LIFE".t_store_fee.feelvlbasicflag IS '分层/套档依据
0：金额  1：笔数';
COMMENT ON COLUMN "LIFE".t_store_fee.upreference1 IS '计费参考1
当分层/套档标志为空时，本字段缺省为全9.没填
如果2-5计费参考有输入，则必须依次递增，且必须连续输入，但需要注意不一定输满5组。
';
COMMENT ON COLUMN "LIFE".t_store_fee.fixfeeamount1 IS '固定费用金额1
如果本组的计费参考没有输入，则本组的固定费用金额和费率都不允许输入
如果本组的计费参考有输入，则本组的固定费用金额和费率必须输入其一，且只允许输入其一，当计费方法=1时，必须输入固定费用金额，当计费方法=2时，必须输入费率；
另外，需要注意汇总收费的模式下：当用户交易笔数在**笔到**笔之间，按每笔收取**金额的方式；以及用户交易笔数在**笔到*8笔之间，总共收取**金额的差别。
';
COMMENT ON COLUMN "LIFE".t_store_fee.feerate1 IS '费率1
参考固定费用金额的描述';
COMMENT ON COLUMN "LIFE".t_store_fee.upreference2 IS '计费参考2';
COMMENT ON COLUMN "LIFE".t_store_fee.fixfeeamount2 IS '固定费用金额2';
COMMENT ON COLUMN "LIFE".t_store_fee.feerate2 IS '费率2';
COMMENT ON COLUMN "LIFE".t_store_fee.upreference3 IS '计费参考3';
COMMENT ON COLUMN "LIFE".t_store_fee.fixfeeamount3 IS '固定费用金额3';
COMMENT ON COLUMN "LIFE".t_store_fee.feerate3 IS '费率3';
COMMENT ON COLUMN "LIFE".t_store_fee.upreference4 IS '计费参考4';
COMMENT ON COLUMN "LIFE".t_store_fee.fixfeeamount4 IS '固定费用金额4';
COMMENT ON COLUMN "LIFE".t_store_fee.feerate4 IS '费率4';
COMMENT ON COLUMN "LIFE".t_store_fee.upreference5 IS '计费参考5';
COMMENT ON COLUMN "LIFE".t_store_fee.fixfeeamount5 IS '固定费用金额5';
COMMENT ON COLUMN "LIFE".t_store_fee.feerate5 IS '费率5';
COMMENT ON COLUMN "LIFE".t_store_fee.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_store_fee.sync_time IS '同步时间';
COMMENT ON COLUMN "LIFE".t_store_fee.create_user IS '创建人';
COMMENT ON COLUMN "LIFE".t_store_fee.status IS '状态 0未审核  3审核通过 4审核驳回';
COMMENT ON COLUMN "LIFE".t_store_fee.sync_gy_flag1 IS '同步高阳状态：对应CAPITAL_TYPE1   0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';
COMMENT ON COLUMN "LIFE".t_store_fee.sync_gy_flag2 IS '同步高阳状态：对应CAPITAL_TYPE2     0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';
COMMENT ON COLUMN "LIFE".t_store_fee.sync_gy_flag3 IS '同步高阳状态：对应CAPITAL_TYPE3    0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';
COMMENT ON COLUMN "LIFE".t_store_fee.capital_type4 IS '是否话费  1：是  0：否';
COMMENT ON COLUMN "LIFE".t_store_fee.sync_gy_flag4 IS '同步高阳状态：对应CAPITAL_TYPE4   0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';
COMMENT ON COLUMN "LIFE".t_store_fee.phone_effortdate IS '话费生效日期';
COMMENT ON COLUMN "LIFE".t_store_fee.capital_type5 IS '是否河南积分  1：是  0：否';
COMMENT ON COLUMN "LIFE".t_store_fee.sync_gy_flag5 IS '同步高阳状态：对应CAPITAL_TYPE5   0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';