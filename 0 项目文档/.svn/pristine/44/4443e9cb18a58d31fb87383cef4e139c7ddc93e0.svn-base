CREATE TABLE "LIFE".t_shop (
  "ID" NUMBER(8) NOT NULL,
  ac_shop_id NUMBER(8),
  "NAME" VARCHAR2(200 BYTE),
  short_name VARCHAR2(200 BYTE),
  area_code VARCHAR2(10 BYTE),
  "SORT" NUMBER(1),
  discount_detail VARCHAR2(100 BYTE),
  is_chain NUMBER(1),
  is_base_shop NUMBER(1),
  base_shop_name VARCHAR2(50 BYTE),
  create_time VARCHAR2(14 BYTE),
  update_time VARCHAR2(14 BYTE),
  start_time VARCHAR2(14 BYTE),
  stop_time VARCHAR2(14 BYTE),
  address VARCHAR2(500 BYTE),
  phone VARCHAR2(100 BYTE),
  open_time VARCHAR2(100 BYTE),
  bus_line VARCHAR2(400 BYTE),
  area VARCHAR2(20 BYTE),
  park_place VARCHAR2(20 BYTE),
  avg_spend NUMBER(8),
  room_num VARCHAR2(50 BYTE),
  map_long VARCHAR2(20 BYTE),
  map_dim VARCHAR2(20 BYTE),
  shop_user_id NUMBER(9),
  status NUMBER(9),
  is_valid NUMBER(1),
  shop_class NUMBER(1),
  logo VARCHAR2(100 BYTE),
  remark CLOB,
  map_type VARCHAR2(3 BYTE) DEFAULT '3',
  mark_name VARCHAR2(100 BYTE),
  address_id VARCHAR2(50 BYTE),
  sp_id VARCHAR2(50 BYTE),
  CONSTRAINT pk_t_shop PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_shop ADD SUPPLEMENTAL LOG GROUP ggs_240667 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_shop IS '存储业务门店相关信息';
COMMENT ON COLUMN "LIFE".t_shop.ac_shop_id IS '结算商户编号，可以为空';
COMMENT ON COLUMN "LIFE".t_shop."NAME" IS '商户名称';
COMMENT ON COLUMN "LIFE".t_shop.short_name IS '商户简称';
COMMENT ON COLUMN "LIFE".t_shop.area_code IS '归属地市';
COMMENT ON COLUMN "LIFE".t_shop."SORT" IS '0--非签约
1--签约';
COMMENT ON COLUMN "LIFE".t_shop.discount_detail IS '折扣内容';
COMMENT ON COLUMN "LIFE".t_shop.is_chain IS '是否连锁
0-否
1-是';
COMMENT ON COLUMN "LIFE".t_shop.is_base_shop IS '是否总店
0-否
1-是';
COMMENT ON COLUMN "LIFE".t_shop.base_shop_name IS '总店名称';
COMMENT ON COLUMN "LIFE".t_shop.create_time IS '创建时间';
COMMENT ON COLUMN "LIFE".t_shop.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_shop.start_time IS '有效开始时间';
COMMENT ON COLUMN "LIFE".t_shop.stop_time IS '有效结束时间';
COMMENT ON COLUMN "LIFE".t_shop.address IS '商户地址';
COMMENT ON COLUMN "LIFE".t_shop.phone IS '联系电话';
COMMENT ON COLUMN "LIFE".t_shop.open_time IS '营业时间';
COMMENT ON COLUMN "LIFE".t_shop.bus_line IS '公交线路';
COMMENT ON COLUMN "LIFE".t_shop.area IS '面积';
COMMENT ON COLUMN "LIFE".t_shop.park_place IS '停车位';
COMMENT ON COLUMN "LIFE".t_shop.avg_spend IS '人均消费';
COMMENT ON COLUMN "LIFE".t_shop.room_num IS '包间数';
COMMENT ON COLUMN "LIFE".t_shop.map_long IS '地图坐标经度';
COMMENT ON COLUMN "LIFE".t_shop.map_dim IS '地图坐标维度';
COMMENT ON COLUMN "LIFE".t_shop.shop_user_id IS '创建商户账号编号';
COMMENT ON COLUMN "LIFE".t_shop.status IS '0--草稿
1--待审核
2--审核中
3--审核通过
4--审核驳回';
COMMENT ON COLUMN "LIFE".t_shop.is_valid IS ' 商户是否有效 0--下架
1--上架';
COMMENT ON COLUMN "LIFE".t_shop.shop_class IS ' 1--业务门店
2--结算商户
3--渠道商
此处默认1';
COMMENT ON COLUMN "LIFE".t_shop.logo IS '门店logo图路径';
COMMENT ON COLUMN "LIFE".t_shop.remark IS '详细介绍';
COMMENT ON COLUMN "LIFE".t_shop.map_type IS '提供经纬度的地图服务商，0百度 1谷歌 2soso 3mapabc 4高德 5(51地图) 6mapbar';
COMMENT ON COLUMN "LIFE".t_shop.mark_name IS '地标、区县名称（从拉手过来的数据）';
COMMENT ON COLUMN "LIFE".t_shop.address_id IS '拉手门店标识';
COMMENT ON COLUMN "LIFE".t_shop.sp_id IS '拉手门店归属商户标识';