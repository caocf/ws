CREATE TABLE "LIFE".t_bsct_back_log (
  "ID" NUMBER(12) NOT NULL,
  s_type VARCHAR2(20 BYTE),
  platform_id VARCHAR2(4 BYTE) NOT NULL,
  store_id VARCHAR2(20 BYTE),
  shop_id VARCHAR2(20 BYTE),
  status NUMBER(2),
  "CONTENT" VARCHAR2(2000 BYTE),
  receive_time VARCHAR2(14 BYTE)
);
COMMENT ON COLUMN "LIFE".t_bsct_back_log.s_type IS 'STORE_OPEN：商户开通  STORE_OPEN _BACK：商户开通回执

SHOP_SYNC_ADD：门店增加  SHOP_SYNC_DEL：门店删除  SHOP_SYNC_EDIT：门店修改  SHOP_SYNC_BACK：门店同步回执

SERVICE_OPEN：服务开通  SERVICE_CLOSE：服务关闭  SERVICE_PAUSE：服务暂停  SERVICE_RESUME：服务恢复  SERVICE_ALERT ：服务变更';
COMMENT ON COLUMN "LIFE".t_bsct_back_log.status IS '0：待同步  1：已同步，未回应  2：已回应';
COMMENT ON COLUMN "LIFE".t_bsct_back_log.receive_time IS '接收回传时间';