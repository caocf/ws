CREATE TABLE "LIFE".t_member (
  "ID" NUMBER(9) NOT NULL,
  user_name VARCHAR2(30 BYTE),
  user_pass VARCHAR2(100 BYTE),
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
  CONSTRAINT pk_t_member_0905 PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_member ADD SUPPLEMENTAL LOG GROUP ggs_241082 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_member IS '商城用户帐号表';
COMMENT ON COLUMN "LIFE".t_member.user_name IS '用户登录名';
COMMENT ON COLUMN "LIFE".t_member.user_pass IS '用户密码';
COMMENT ON COLUMN "LIFE".t_member.email IS '用户邮箱';
COMMENT ON COLUMN "LIFE".t_member.real_name IS '真实姓名';
COMMENT ON COLUMN "LIFE".t_member.terminal_id IS '手机号码';
COMMENT ON COLUMN "LIFE".t_member.reg_time IS '注册时间';
COMMENT ON COLUMN "LIFE".t_member.status IS '状态 1正常 2暂停';
COMMENT ON COLUMN "LIFE".t_member.update_time IS '更新时间';
COMMENT ON COLUMN "LIFE".t_member.vid IS 'JSSSO VID';
COMMENT ON COLUMN "LIFE".t_member.nick_name IS '昵称';
COMMENT ON COLUMN "LIFE".t_member.area_code IS '归属地区代码';
COMMENT ON COLUMN "LIFE".t_member.sex IS '性别1女2男';
COMMENT ON COLUMN "LIFE".t_member.birthday IS '生日';
COMMENT ON COLUMN "LIFE".t_member.opened IS '信息是否公开 0不公开 1公开';
COMMENT ON COLUMN "LIFE".t_member.qq IS 'QQ';
COMMENT ON COLUMN "LIFE".t_member.remark IS '个人信息描述';
COMMENT ON COLUMN "LIFE".t_member.signature IS '个性签名';
COMMENT ON COLUMN "LIFE".t_member.reg_source IS '注册来源 1WEBSSO 3-BOSS(刷新会员等级时会员不存在时候生成) 2 短信购';
COMMENT ON COLUMN "LIFE".t_member.user_level IS '会员级别
0 普通会员
10 体验会员 长期免费。
11 体验会员 定期免费。
20 普通会员 1元/月。
21 普通会员 3元/月。
22 普通会员 5元/月。
30 中级会员 30元/年。
40 高级会员 30元/月。
';
COMMENT ON COLUMN "LIFE".t_member.cart_uuid IS '购物车标识';
COMMENT ON COLUMN "LIFE".t_member.red_member IS '是否红钻 0否 1是';