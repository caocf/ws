CREATE TABLE "LIFE".t_member_0910 (
  "ID" NUMBER(9) NOT NULL,
  user_name VARCHAR2(30 BYTE),
  user_pass VARCHAR2(50 BYTE),
  email VARCHAR2(50 BYTE),
  real_name VARCHAR2(50 BYTE),
  terminal_id VARCHAR2(20 BYTE),
  reg_time VARCHAR2(14 BYTE),
  status CHAR,
  update_time VARCHAR2(14 BYTE),
  vid VARCHAR2(50 BYTE),
  nick_name VARCHAR2(50 BYTE),
  area_code VARCHAR2(6 BYTE),
  sex NUMBER(1) DEFAULT 1,
  birthday VARCHAR2(14 BYTE),
  opened NUMBER(1) DEFAULT 0,
  qq VARCHAR2(20 BYTE),
  remark VARCHAR2(1000 BYTE),
  signature VARCHAR2(1000 BYTE),
  reg_source NUMBER(2) DEFAULT 1,
  user_level VARCHAR2(50 BYTE) DEFAULT 0,
  cart_uuid VARCHAR2(50 BYTE),
  red_member NUMBER(1) DEFAULT 0,
  CONSTRAINT pk_member_id PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_member_0910 ADD SUPPLEMENTAL LOG GROUP ggs_240370 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_member_0910 IS '商城用户帐号表';
COMMENT ON COLUMN "LIFE".t_member_0910.user_name IS '用户登录名';
COMMENT ON COLUMN "LIFE".t_member_0910.user_pass IS '用户密码';
COMMENT ON COLUMN "LIFE".t_member_0910.email IS '用户邮箱';
COMMENT ON COLUMN "LIFE".t_member_0910.real_name IS '真实姓名';
COMMENT ON COLUMN "LIFE".t_member_0910.terminal_id IS '手机号码';
COMMENT ON COLUMN "LIFE".t_member_0910.reg_time IS '注册时间';
COMMENT ON COLUMN "LIFE".t_member_0910.status IS '状态 1正常 2暂停';
COMMENT ON COLUMN "LIFE".t_member_0910.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_member_0910.vid IS 'JSSSO VID';
COMMENT ON COLUMN "LIFE".t_member_0910.nick_name IS '昵称';
COMMENT ON COLUMN "LIFE".t_member_0910.area_code IS '归属地区代码';
COMMENT ON COLUMN "LIFE".t_member_0910.sex IS '性别1女2男';
COMMENT ON COLUMN "LIFE".t_member_0910.birthday IS '生日';
COMMENT ON COLUMN "LIFE".t_member_0910.opened IS '信息是否公开 0不公开 1公开';
COMMENT ON COLUMN "LIFE".t_member_0910.qq IS 'QQ';
COMMENT ON COLUMN "LIFE".t_member_0910.remark IS '个人信息描述';
COMMENT ON COLUMN "LIFE".t_member_0910.signature IS '个性签名';
COMMENT ON COLUMN "LIFE".t_member_0910.reg_source IS '注册来源 1WEBSSO 3-BOSS(刷新会员等级时会员不存在时候生成) 2 短信购';
COMMENT ON COLUMN "LIFE".t_member_0910.user_level IS '会员级别
0 普通会员
10 体验会员 长期免费。
11 体验会员 定期免费。
20 普通会员 1元/月。
21 普通会员 3元/月。
22 普通会员 5元/月。
30 中级会员 30元/年。
40 高级会员 30元/月。
';
COMMENT ON COLUMN "LIFE".t_member_0910.cart_uuid IS '购物车标识';
COMMENT ON COLUMN "LIFE".t_member_0910.red_member IS '是否红钻 0否 1是';