CREATE TABLE "LIFE".t_sc_sys_user (
  "ID" NUMBER(9) NOT NULL,
  user_code VARCHAR2(20 BYTE) NOT NULL,
  user_pwd VARCHAR2(60 BYTE) NOT NULL,
  user_name VARCHAR2(100 BYTE) NOT NULL,
  terminal_id VARCHAR2(30 BYTE),
  email VARCHAR2(100 BYTE),
  remark VARCHAR2(200 BYTE),
  valid_time VARCHAR2(8 BYTE),
  create_time VARCHAR2(14 BYTE) NOT NULL,
  change_pwd_time VARCHAR2(14 BYTE),
  update_time VARCHAR2(14 BYTE) NOT NULL,
  update_user_id NUMBER(9) NOT NULL,
  lock_status NUMBER(3) DEFAULT 0 NOT NULL,
  status NUMBER(3) DEFAULT 1 NOT NULL,
  flag NUMBER(3) NOT NULL,
  unit_id NUMBER(9) DEFAULT 0 NOT NULL,
  ap_id NUMBER(9),
  platform_id VARCHAR2(4 BYTE)
);
ALTER TABLE "LIFE".t_sc_sys_user ADD SUPPLEMENTAL LOG GROUP ggs_240652 (change_pwd_time, create_time, email, flag, "ID", lock_status, remark, status, terminal_id, unit_id, update_time, update_user_id, user_code, user_name, user_pwd, valid_time) ALWAYS;
COMMENT ON TABLE "LIFE".t_sc_sys_user IS '1.系统所有用户在一张表内维护

2.用户类别的说明:运营商增加本单位用户时,需选择[2]-普通用户,[3]-客服用户
  客户系统人员有固化的权限.';
COMMENT ON COLUMN "LIFE".t_sc_sys_user."ID" IS 'ID';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.user_code IS '全平台唯一，用户帐号，用于登录';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.user_pwd IS '密码';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.user_name IS '用户真实姓名';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.terminal_id IS '终端号码';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.email IS 'EMAIL地址';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.remark IS '描述';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.valid_time IS '帐户有效期，8位日期，如果为空则表示永久有效';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.create_time IS '用户创建时间';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.change_pwd_time IS '用户修改密码时间';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.update_time IS '账号更新时间';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.update_user_id IS '更新用户ID，匹配T_SYS_USER_V2表中的ID字段';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.lock_status IS '帐号锁定状态：0,正常;1,锁定';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.status IS '[1]:正常,[2]:暂停,[3]:删除';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.flag IS '[0]:超级管理员,[1]:单位管理员 [2]单位普通人员 [3]';
COMMENT ON COLUMN "LIFE".t_sc_sys_user.unit_id IS '该用户归属单位，匹配表T_UNIT中ID';