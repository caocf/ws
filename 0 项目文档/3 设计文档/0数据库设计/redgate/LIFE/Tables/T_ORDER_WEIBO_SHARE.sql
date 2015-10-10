CREATE TABLE "LIFE".t_order_weibo_share (
  "ID" NUMBER(9) NOT NULL,
  order_id NUMBER(19),
  goods_name VARCHAR2(512 BYTE),
  goods_type VARCHAR2(20 BYTE),
  pay_time VARCHAR2(19 BYTE),
  share_time VARCHAR2(19 BYTE),
  status NUMBER(1),
  discribe VARCHAR2(512 BYTE),
  operate_id NUMBER(9),
  last_modify VARCHAR2(19 BYTE),
  user_id NUMBER(9),
  city VARCHAR2(20 BYTE),
  is_leaguer VARCHAR2(20 BYTE),
  is_del NUMBER(2),
  mobile VARCHAR2(14 BYTE),
  CONSTRAINT t_order_weibo_share_pk PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_order_weibo_share.order_id IS '订单号T_ACT_ORDER的id';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.goods_name IS '商品名称';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.goods_type IS '分享类型:竞拍，秒杀，我购买的商品，彩票';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.pay_time IS '支付时间';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.share_time IS '分享时间';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.status IS '状态';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.discribe IS '描述';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.operate_id IS '后台管理员id';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.last_modify IS '最后修改时间';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.user_id IS '分享用户id';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.city IS '城市';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.is_leaguer IS '是否是会员';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.is_del IS '是否删除';
COMMENT ON COLUMN "LIFE".t_order_weibo_share.mobile IS '手机号';