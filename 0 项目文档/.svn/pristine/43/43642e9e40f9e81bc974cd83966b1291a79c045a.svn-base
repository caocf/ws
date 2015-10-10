CREATE TABLE "LIFE".t_member_third (
  "ID" NUMBER(9),
  member_id NUMBER(9),
  third_id NVARCHAR2(50),
  third_type NVARCHAR2(10),
  access_token NVARCHAR2(50),
  expires_in NUMBER(9),
  refresh_token NVARCHAR2(50)
);
COMMENT ON TABLE "LIFE".t_member_third IS '第三方绑定帐号';
COMMENT ON COLUMN "LIFE".t_member_third.member_id IS '会员ID';
COMMENT ON COLUMN "LIFE".t_member_third.third_id IS '第三方登录帐号唯一ID';
COMMENT ON COLUMN "LIFE".t_member_third.third_type IS '1新浪微博 2QQ互联 3腾讯微博 4微信';
COMMENT ON COLUMN "LIFE".t_member_third.access_token IS '授权令牌';
COMMENT ON COLUMN "LIFE".t_member_third.expires_in IS '授权令牌有效期，单位秒';
COMMENT ON COLUMN "LIFE".t_member_third.refresh_token IS '自动续期时需提供的令牌';