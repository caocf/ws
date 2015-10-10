CREATE TABLE "LIFE".t_sys_black_user (
  "ID" NUMBER(9) NOT NULL,
  terminal_id VARCHAR2(21 BYTE) NOT NULL,
  lev_tag NUMBER(3),
  in_tag NUMBER(3),
  remark VARCHAR2(50 BYTE),
  update_user_id NUMBER(9),
  update_time VARCHAR2(14 BYTE),
  unit_id VARCHAR2(20 CHAR),
  CONSTRAINT pk_t_sys_black_user PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_black_user ADD SUPPLEMENTAL LOG GROUP ggs_240430 ("ID") ALWAYS;
COMMENT ON COLUMN "LIFE".t_sys_black_user.lev_tag IS '不同级别的黑名单用户可以参与不同的业务。
0：平台级（任何业务都不能参加）
1：不能接收群发信息
2：广告黑名单
3：本单位所有业务都不能参加';
COMMENT ON COLUMN "LIFE".t_sys_black_user.in_tag IS '0：手工输入
1：批量导入';