CREATE TABLE "LIFE".t_hisun_production_settle (
  "ID" NUMBER(9) NOT NULL,
  merchid VARCHAR2(32 BYTE) NOT NULL,
  store_id VARCHAR2(32 BYTE) NOT NULL,
  serviceid VARCHAR2(10 BYTE) NOT NULL,
  contractid VARCHAR2(32 BYTE),
  contracteffdate VARCHAR2(8 BYTE),
  contractexpdate VARCHAR2(8 BYTE),
  productionid VARCHAR2(32 BYTE) NOT NULL,
  productionname VARCHAR2(255 BYTE) NOT NULL,
  productiontype VARCHAR2(32 BYTE) NOT NULL,
  capitaltype3 NUMBER(1) DEFAULT 0,
  capitaltype2 NUMBER(1) DEFAULT 0,
  capitaltype1 NUMBER(1) DEFAULT 0,
  verifyflag VARCHAR2(1 BYTE) NOT NULL,
  verifysettleflag VARCHAR2(2 BYTE),
  productionefftime VARCHAR2(14 BYTE) NOT NULL,
  productionexptime VARCHAR2(14 BYTE) NOT NULL,
  verifyexpdate VARCHAR2(8 BYTE),
  cityid VARCHAR2(32 BYTE),
  cityname VARCHAR2(32 BYTE),
  cityprofitrate VARCHAR2(2 BYTE),
  agentid VARCHAR2(32 BYTE),
  agentname VARCHAR2(125 BYTE),
  agentprofitrate VARCHAR2(2 BYTE),
  price NUMBER(9,2),
  settlementprice NUMBER(9,2),
  settleperiod NUMBER(2),
  settleperiodtype VARCHAR2(4 BYTE),
  settledate1 VARCHAR2(8 BYTE),
  settlerate1 VARCHAR2(2 BYTE),
  settledate2 VARCHAR2(8 BYTE),
  settlerate2 VARCHAR2(2 BYTE),
  settledate3 VARCHAR2(8 BYTE),
  settlerate3 VARCHAR2(2 BYTE),
  status NUMBER(1) DEFAULT 0,
  sync_gy_status1 NUMBER(2) DEFAULT 0,
  create_time VARCHAR2(14 BYTE),
  create_user NUMBER(9),
  "TYPE" NUMBER(1),
  file_path VARCHAR2(200 BYTE),
  sync_gy_status2 NUMBER(2) DEFAULT 0,
  sync_gy_status3 NUMBER(2) DEFAULT 0,
  capitaltype4 NUMBER(1) DEFAULT 0,
  sync_gy_status4 NUMBER(1) DEFAULT 0,
  "NAME" VARCHAR2(200 BYTE),
  send_code_mode NUMBER(1),
  send_code_channel NUMBER(1),
  send_code_src NUMBER(8),
  verify_code_type NUMBER(1),
  verify_start_time VARCHAR2(14 BYTE),
  verify_stop_time VARCHAR2(14 BYTE),
  verify_day NUMBER(5),
  store_fee_id NUMBER(9),
  is_linked NUMBER(1) DEFAULT 0,
  capitaltype5 NUMBER(1) DEFAULT 0,
  sync_gy_status5 NUMBER(1) DEFAULT 0,
  item_data_path VARCHAR2(200 BYTE),
  item_data_status NUMBER(1) DEFAULT 0,
  CONSTRAINT pk_t_hisun_production_settle PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_hisun_production_settle ADD SUPPLEMENTAL LOG GROUP ggs_240352 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.merchid IS '结算商户编号
商户在统一清算平台中的编号';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.store_id IS '商户编号
在业务平台中的商户编号';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.serviceid IS '业务编号';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.contractid IS '合同编号
商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.contracteffdate IS '合同生效日期
YYYYMMDD，商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.contractexpdate IS '合同失效日期';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.productionid IS '商品编号';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.productionname IS '商品名称';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.productiontype IS '对应t_sys_fee的id';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.capitaltype3 IS '资金种类3-积分
1-支持 0-no';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.capitaltype2 IS '资金种类2-商城币
1-支持 0-no';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.capitaltype1 IS '资金种类-现金
1-支持 0-no';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.verifyflag IS '验证标识
Y-验证；N-不验证（默认）';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.verifysettleflag IS '验证结算
对于需要验证的商品，一种是支付验证都成功后才能做清结算，另一种是支付成功，但是没有验证的就要做清结算，通过此字段来区分，这个字段当veriflag=Y时必输。
S1-支付后结算
S2-验证后结算
';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.productionefftime IS '产品上线时间YYYYMMDDHHMISS';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.productionexptime IS '产品下线时间YYYYMMDDHHMISS';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.verifyexpdate IS '验证截止日期
YYYYMMDD，商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.cityid IS '地市编码
商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.cityname IS '地市名称
商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.cityprofitrate IS '地市分成比例
商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.agentid IS '代理商编码
商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.agentname IS '代理商名称
商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.agentprofitrate IS '代理商分成比例
商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.price IS '商品单价 以分为单位';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.settlementprice IS '结算单价 以分为单位';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.settleperiod IS '结算分期数 商盟必输';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.settleperiodtype IS '结算分期类型
1P2P：一期支付二期支付
1P2V：一期支付二期验证
1V2V：一期验证二期验证
';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.settledate1 IS '结算时间 YYYYMMDD';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.settlerate1 IS '结算比例';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.settledate2 IS '结算时间2';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.settlerate2 IS '结算比例2';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.settledate3 IS '结算时间3';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.settlerate3 IS '结算比例3';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.status IS '状态
0-未审核
1-审核通过';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.sync_gy_status1 IS '高阳状态:对应CAPITALTYPE1
0-未同步
1-已同步
2-待审核
3-同步成功
4-审核驳回5-删除';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.create_user IS '创建人';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle."TYPE" IS '协议类型 0-商品协议 1-商品资料';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.file_path IS '协议文件路径';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.sync_gy_status2 IS '高阳状态:对应CAPITALTYPE2
0-未同步
1-已同步
2-待审核
3-同步成功
4-审核驳回5-删除';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.sync_gy_status3 IS '高阳状态:对应CAPITALTYPE3
0-未同步
1-已同步
2-待审核
3-同步成功
4-审核驳回5-删除';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.capitaltype4 IS '资金种类-话费
1-支持 0-no';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.sync_gy_status4 IS '高阳状态:对应CAPITALTYPE4
0-未同步
1-已同步
2-待审核
3-同步成功
4-审核驳回5-删除';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle."NAME" IS '商品协议名称';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.send_code_mode IS '0-不发码，1按照订单发码  2按照商品个数发码';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.send_code_channel IS '0-平台自己 1-方正码平台 2-第三方应用 3-卡密 4-拉手 5-拓维电影票 6-鼎山电影票';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.send_code_src IS '如果制码方选择第三方应用，则该字段有用 10-85度C 11-鲜芋仙 ...';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.verify_code_type IS '方正码平台验证方式0短信1彩信2短信彩信。现在只能选2';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.verify_start_time IS '验证有效开始时间';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.verify_stop_time IS '验证有效结束时间';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.verify_day IS '验证天数';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.store_fee_id IS 't_store_fee的ID';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.is_linked IS '0未关联商品，1已经关联商品';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.capitaltype5 IS '资金种类-河南积分
1-支持 0-no';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.sync_gy_status5 IS '高阳状态:对应CAPITALTYPE5
0-未同步
1-已同步
2-待审核
3-同步成功
4-审核驳回5-删除';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.item_data_path IS '商品资料路径';
COMMENT ON COLUMN "LIFE".t_hisun_production_settle.item_data_status IS '商品资料审核状态';