CREATE TABLE "LIFE".t_monitor_invalid_item (
  "ID" NUMBER(9) NOT NULL,
  store_id NUMBER(9) NOT NULL,
  store_name VARCHAR2(50 BYTE) NOT NULL,
  item_id NUMBER(9) NOT NULL,
  item_name VARCHAR2(512 BYTE) NOT NULL,
  exception_type NUMBER(2) NOT NULL,
  insert_time VARCHAR2(14 BYTE) DEFAULT TO_CHAR(SYSDATE, 'yyyyMMddHHmmss') NOT NULL,
  deal_status NUMBER(2) DEFAULT 0 NOT NULL,
  deal_result VARCHAR2(200 BYTE),
  deal_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_monitor_invalid_item PRIMARY KEY ("ID")
);
COMMENT ON TABLE "LIFE".t_monitor_invalid_item IS '业务监控非法商品信息表';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item."ID" IS '主键';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item.store_id IS '商户id';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item.store_name IS '商户名称';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item.item_id IS '商品id';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item.item_name IS '商品名称';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item.exception_type IS '异常类型（11：商户基本信息非法-审核状态不为审核通过；12：商户基本信息非法-清算状态不为审核通过；13：商户基本信息非法-账号状态为不可用；21：商户结算信息非法-审核状态不为审核通过；22：商户结算信息非法-清算状态不为审核通过；23：商户结算信息非法-生效日志晚于当天；24：商户结算信息非法-失效日期早于当天；31：商户费率信息非法-审核状态不为审核通过；32：商户费率信息非法-清算状态不为审核通过；33：商户费率信息非法-生效日志晚于当天；34：商户费率信息非法-失效日期早于当天；41：商品基本信息非法-审核状态不为审核通过；42：商品基本信息非法-清算状态不为审核通过；43：商品基本信息非法-生效日志晚于当天；44：商品基本信息非法-失效日期早于当天；45：商品基本信息非法-库存量不足。）';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item.insert_time IS '插入时间';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item.deal_status IS '处理状态（0：未处理；1：处理完成。）';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item.deal_result IS '处理结果';
COMMENT ON COLUMN "LIFE".t_monitor_invalid_item.deal_time IS '处理时间';