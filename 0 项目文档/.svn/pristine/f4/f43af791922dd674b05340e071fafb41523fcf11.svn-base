CREATE TABLE "LIFE".t_sys_test_terminal_id (
  "ID" NUMBER(9) NOT NULL,
  terminal_id VARCHAR2(21 BYTE) NOT NULL,
  owner_name VARCHAR2(40 BYTE),
  test_type NUMBER(3) NOT NULL,
  act_id NUMBER(9),
  unit_id VARCHAR2(20 BYTE),
  status NUMBER(3),
  remark VARCHAR2(200 BYTE),
  update_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_sys_test_terminal_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_test_terminal_id ADD SUPPLEMENTAL LOG GROUP ggs_240460 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_test_terminal_id IS '平台测试用户表';
COMMENT ON COLUMN "LIFE".t_sys_test_terminal_id.terminal_id IS '测试手机号码';
COMMENT ON COLUMN "LIFE".t_sys_test_terminal_id.owner_name IS '机主姓名';
COMMENT ON COLUMN "LIFE".t_sys_test_terminal_id.test_type IS '测试类型：0、全局测试号码；1、业务测试号码；2、彩信发送测试号码';
COMMENT ON COLUMN "LIFE".t_sys_test_terminal_id.act_id IS '活动ID';
COMMENT ON COLUMN "LIFE".t_sys_test_terminal_id.unit_id IS '所属单位';
COMMENT ON COLUMN "LIFE".t_sys_test_terminal_id.status IS '状态：0：无效测试用户；1：测试用户';
COMMENT ON COLUMN "LIFE".t_sys_test_terminal_id.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_sys_test_terminal_id.update_user_id IS '创建者ID';
COMMENT ON COLUMN "LIFE".t_sys_test_terminal_id.update_time IS '创建时间';