-- Create table
create table T_FLOW_PACKET_RECHARGE
(
  ID               NUMBER(9) not null,
  USER_ID          NUMBER(12),
  ACT_ORDER_ID     NUMBER(19),
  TERMINAL_ID      NVARCHAR2(20),
  INURE_MODE       NUMBER(2),
  PAY_TYPE         VARCHAR2(20),
  PAYMENT_AMOUNT   NUMBER(9),
  CREATE_TIME      NVARCHAR2(14),
  RECHARGE_TIME    NVARCHAR2(14),
  RECHARGE_STATUS  NUMBER(2),
  RECHARGE_COMMENT NVARCHAR2(200),
  RECHARGE_ID      NUMBER(19),
  PACKAGE_TYPE     NVARCHAR2(200),
  USER_TERMINAL_ID NVARCHAR2(20),
  PACKAGE_ID       NUMBER(10)
);
-- Add comments to the columns 
comment on column T_FLOW_PACKET_RECHARGE.ID
  is '主键流水号';
comment on column T_FLOW_PACKET_RECHARGE.USER_ID
  is '用户id';
comment on column T_FLOW_PACKET_RECHARGE.ACT_ORDER_ID
  is '业务订单号';
comment on column T_FLOW_PACKET_RECHARGE.TERMINAL_ID
  is '被充值的手机号';
comment on column T_FLOW_PACKET_RECHARGE.INURE_MODE
  is '生效方式';
comment on column T_FLOW_PACKET_RECHARGE.PAY_TYPE
  is '支付方式';
comment on column T_FLOW_PACKET_RECHARGE.PAYMENT_AMOUNT
  is '支付金额';
comment on column T_FLOW_PACKET_RECHARGE.CREATE_TIME
  is '创建订单时间';
comment on column T_FLOW_PACKET_RECHARGE.RECHARGE_TIME
  is '充值时间';
comment on column T_FLOW_PACKET_RECHARGE.RECHARGE_STATUS
  is '充值状态';
comment on column T_FLOW_PACKET_RECHARGE.RECHARGE_COMMENT
  is '充值结果';
comment on column T_FLOW_PACKET_RECHARGE.RECHARGE_ID
  is '兑换流水号';
comment on column T_FLOW_PACKET_RECHARGE.PACKAGE_TYPE
  is '流量包类型';
comment on column T_FLOW_PACKET_RECHARGE.USER_TERMINAL_ID
  is 'user_id的充值手机号';
comment on column T_FLOW_PACKET_RECHARGE.PACKAGE_ID
  is '产品ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_FLOW_PACKET_RECHARGE
  add constraint T_FLOW_PACKET_RECHARGE_PK primary key (ID);
