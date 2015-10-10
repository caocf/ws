CREATE TABLE "LIFE".t_bsct_store_ext (
  store_id VARCHAR2(20 BYTE) NOT NULL,
  prov_code VARCHAR2(8 BYTE),
  area_code VARCHAR2(8 BYTE),
  percrp_flag VARCHAR2(1 BYTE),
  trade_class VARCHAR2(4 BYTE),
  trade_desc VARCHAR2(60 BYTE),
  merch_attr VARCHAR2(1 BYTE),
  corporation_name VARCHAR2(60 BYTE),
  corporation_idtype VARCHAR2(10 BYTE),
  corporation_no VARCHAR2(30 BYTE),
  margina_mount NUMBER(13),
  reg_capital NUMBER(13),
  staff_num NUMBER(6),
  opening_date VARCHAR2(8 BYTE),
  busland_attr NUMBER(1),
  bus_section NUMBER(1),
  bus_region NUMBER(1),
  org_region_scope VARCHAR2(30 BYTE),
  org_num NUMBER(6),
  hot_line VARCHAR2(18 BYTE),
  reg_ration_id VARCHAR2(60 BYTE),
  web_name VARCHAR2(60 BYTE),
  web_url VARCHAR2(256 BYTE),
  tax_certid VARCHAR2(60 BYTE),
  icp_id VARCHAR2(32 BYTE),
  red315_flag VARCHAR2(1 BYTE),
  reg_addr VARCHAR2(120 BYTE),
  bus_addr VARCHAR2(120 BYTE),
  up_store_id NUMBER(9),
  chain_relations NUMBER(1),
  merch_src NUMBER(2),
  merch_type NUMBER(2),
  merch_class NUMBER(2),
  merch_flag VARCHAR2(2 BYTE),
  clear_flag NUMBER(2),
  CONSTRAINT pk_t_bsct_store_ext PRIMARY KEY (store_id)
);
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.percrp_flag IS '0-个人；1-企业';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.merch_attr IS '1 国营
2 股份制
3 集体
4 中外合资、合作
5 外商独资
6 私营合伙
7 私营独资
8 个体
9 其他
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.corporation_idtype IS '00 身份证
01 军人证
02 警察证
03 港澳通行证
04 护照
99 其他
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.margina_mount IS '以分为单位';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.reg_capital IS '以分为单位';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.staff_num IS '员工人数';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.opening_date IS 'yyyymmdd';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.busland_attr IS '1：自有 2：租用
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.bus_section IS '1 商业区 2 工业区 3 住宅区
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.bus_region IS '1 城区 2 郊区 3 边远地区
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.red315_flag IS 'Y：是 N：不是';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.chain_relations IS '0: 分店   1：加盟店
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.merch_src IS '1 直接拓展  2 省公司推荐  3 普通代理
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.merch_type IS '3 本地远程  4 本地现场 0 结算商户
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.merch_class IS '0：商户  1：代理充值点  2：业务受理点  3：营业厅
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.merch_flag IS 'A：移动和商户签订类型   B：业务平台和商户签订类型  C：其他
';
COMMENT ON COLUMN "LIFE".t_bsct_store_ext.clear_flag IS '2-日终清分';