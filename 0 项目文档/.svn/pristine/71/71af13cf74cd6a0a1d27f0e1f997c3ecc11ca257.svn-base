CREATE TABLE "LIFE".t_smsbuy_user_address (
  terminal_id VARCHAR2(22 BYTE) NOT NULL,
  address VARCHAR2(500 BYTE),
  update_time VARCHAR2(14 BYTE)
);
ALTER TABLE "LIFE".t_smsbuy_user_address ADD SUPPLEMENTAL LOG GROUP ggs_240413 (address, terminal_id, update_time) ALWAYS;
COMMENT ON COLUMN "LIFE".t_smsbuy_user_address.terminal_id IS '号码';
COMMENT ON COLUMN "LIFE".t_smsbuy_user_address.address IS '送货地址';
COMMENT ON COLUMN "LIFE".t_smsbuy_user_address.update_time IS '更新时间';