CREATE TABLE "LIFE".t_gift_card_cb_handle (
  "ID" NUMBER(9) NOT NULL,
  user_id NUMBER(9),
  serial_no VARCHAR2(20 BYTE),
  us_id NUMBER(9),
  cb_id NUMBER(9),
  status NUMBER(2),
  order_id NUMBER(19),
  order_result VARCHAR2(500 BYTE),
  create_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_gift_card_cb_handle PRIMARY KEY ("ID")
);
COMMENT ON COLUMN "LIFE".t_gift_card_cb_handle.user_id IS '用户ID';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_handle.serial_no IS '卡号';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_handle.us_id IS '礼品卡请求流水号';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_handle.cb_id IS '回调事件ID';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_handle.status IS '1：正常 2：重复的回调，不予处理，3:创建订单异常  ';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_handle.order_id IS '订单号';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_handle.order_result IS '订单操作返回报文';
COMMENT ON COLUMN "LIFE".t_gift_card_cb_handle.create_time IS '处理时间';