CREATE TABLE "LIFE".t_goods_ext_info (
  goods_id NUMBER(12) NOT NULL,
  out_merch_id VARCHAR2(50 BYTE),
  out_goods_id VARCHAR2(50 BYTE),
  "TYPE" VARCHAR2(20 BYTE),
  update_time VARCHAR2(14 BYTE) NOT NULL,
  CONSTRAINT pk_goods_ext_info PRIMARY KEY (goods_id)
);
COMMENT ON TABLE "LIFE".t_goods_ext_info IS '商品扩展信息表';
COMMENT ON COLUMN "LIFE".t_goods_ext_info.goods_id IS '订单号';
COMMENT ON COLUMN "LIFE".t_goods_ext_info.out_merch_id IS '外部商户编号（如电影院编号）';
COMMENT ON COLUMN "LIFE".t_goods_ext_info.out_goods_id IS '外部商品编号（如电影票编号）';
COMMENT ON COLUMN "LIFE".t_goods_ext_info."TYPE" IS '商品类型，暂定hn_coupon为河南优惠券';
COMMENT ON COLUMN "LIFE".t_goods_ext_info.update_time IS '更新时间';