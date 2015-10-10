-----------------------------------------------
-- Export file for user LIFE                 --
-- Created by Scorpio on 2013/9/16, 16:25:40 --
-----------------------------------------------

set define off
spool life.log

prompt
prompt Creating table COM_AUDIT_TRAIL
prompt ==============================
prompt
create table COM_AUDIT_TRAIL
(
  aud_user      VARCHAR2(100) not null,
  aud_client_ip VARCHAR2(15) not null,
  aud_server_ip VARCHAR2(15) not null,
  aud_resource  VARCHAR2(100) not null,
  aud_action    VARCHAR2(100) not null,
  applic_cd     VARCHAR2(5) not null,
  aud_date      TIMESTAMP(6) not null
)
;
comment on table COM_AUDIT_TRAIL
  is '登录审计表';
comment on column COM_AUDIT_TRAIL.aud_user
  is '用户';
comment on column COM_AUDIT_TRAIL.aud_client_ip
  is '客户端IP';
comment on column COM_AUDIT_TRAIL.aud_server_ip
  is '服务端IP';
comment on column COM_AUDIT_TRAIL.aud_resource
  is '结果';
comment on column COM_AUDIT_TRAIL.aud_action
  is '操作';
comment on column COM_AUDIT_TRAIL.applic_cd
  is '应用标识';
comment on column COM_AUDIT_TRAIL.aud_date
  is '时间';
create index COM_AUDIT_TRAIL_ACTION_DATE_I on COM_AUDIT_TRAIL (AUD_ACTION, AUD_DATE);
create index COM_AUDIT_TRAIL_CLIENT_DATE_I on COM_AUDIT_TRAIL (AUD_CLIENT_IP, AUD_DATE);
create index COM_AUDIT_TRAIL_DATE_I on COM_AUDIT_TRAIL (AUD_DATE);
create index COM_AUDIT_TRAIL_USER_DATE_I on COM_AUDIT_TRAIL (AUD_USER, AUD_DATE);
alter table COM_AUDIT_TRAIL
  add constraint COM_AUDIT_TRAIL_PK primary key (AUD_USER, AUD_CLIENT_IP, AUD_SERVER_IP, AUD_RESOURCE, AUD_ACTION, APPLIC_CD, AUD_DATE);

prompt
prompt Creating table JINWEIDU
prompt =======================
prompt
create table JINWEIDU
(
  city  VARCHAR2(50),
  jindu NUMBER(11,8),
  weidu NUMBER(11,8)
)
;

prompt
prompt Creating table MULTI_FEE_TMP
prompt ============================
prompt
create table MULTI_FEE_TMP
(
  store_id NUMBER,
  item_id  NUMBER,
  fee      NUMBER(9,2),
  fee_id   NUMBER
)
;

prompt
prompt Creating table NEW_FEE_RATE2
prompt ============================
prompt
create table NEW_FEE_RATE2
(
  id         VARCHAR2(50),
  store_name VARCHAR2(200),
  category   VARCHAR2(200),
  fee        VARCHAR2(10),
  describe   VARCHAR2(80),
  type       NUMBER,
  lad        NUMBER,
  starttime  VARCHAR2(8),
  endtime    VARCHAR2(8),
  fee_id     NUMBER
)
;

prompt
prompt Creating table SELL_FEE_TMP
prompt ===========================
prompt
create table SELL_FEE_TMP
(
  merc_id         NUMBER,
  cap_typ         NUMBER,
  trd_mod         NUMBER,
  fee_typ         VARCHAR2(5),
  prod_typ        VARCHAR2(20),
  clr_typ         VARCHAR2(5),
  sts             NUMBER,
  eff_dt          VARCHAR2(10),
  exp_dt          VARCHAR2(10),
  fee_drt_flg     VARCHAR2(5),
  fee_prd_unit    VARCHAR2(5),
  fee_prd_num     VARCHAR2(5),
  next_fee_dt     VARCHAR2(10),
  fee_mod         VARCHAR2(5),
  fee_mtd         VARCHAR2(5),
  beg_amt         VARCHAR2(5),
  min_fee_amt     VARCHAR2(5),
  max_fee_amt     VARCHAR2(20),
  fee_bas_flg     VARCHAR2(5),
  fee_lvl_flg     VARCHAR2(5),
  fee_lvl_bas_flg VARCHAR2(5),
  up_ref1         NUMBER,
  fix_fee_amt1    VARCHAR2(20),
  fee_rate1       NUMBER,
  up_ref2         NUMBER,
  fix_fee_amt2    VARCHAR2(20),
  fee_rate2       NUMBER,
  up_ref3         NUMBER,
  fix_fee_amt3    VARCHAR2(20),
  fee_rate3       NUMBER,
  up_ref4         NUMBER,
  fix_fee_amt4    VARCHAR2(20),
  fee_rate4       NUMBER,
  up_ref5         NUMBER,
  fix_fee_amt5    VARCHAR2(20),
  fee_rate5       NUMBER,
  bus_cre_opr     VARCHAR2(20),
  ui_cre_opr      VARCHAR2(10),
  cre_dt          VARCHAR2(10),
  cre_tm          VARCHAR2(10),
  bus_upd_opr     VARCHAR2(20),
  ui_upd_opr      VARCHAR2(20),
  upd_dt          VARCHAR2(10),
  upd_tm          VARCHAR2(10),
  tm_smp          VARCHAR2(20),
  fee_chk_oppr    VARCHAR2(15),
  return_desc     VARCHAR2(20)
)
;

prompt
prompt Creating table SMS_MT_WAIT
prompt ==========================
prompt
create table SMS_MT_WAIT
(
  sequence_id       NUMBER(9) not null,
  act_code          VARCHAR2(20) not null,
  sp_code           VARCHAR2(60) not null,
  fee_terminal_id   VARCHAR2(64) not null,
  dest_terminal_id  VARCHAR2(64),
  register_delivery VARCHAR2(100),
  msg_content       VARCHAR2(2000),
  request_time      VARCHAR2(14),
  service_id        VARCHAR2(14) not null,
  fee_type          NUMBER(3),
  fee_code          NUMBER(3),
  msg_level         NUMBER(9),
  valid_time        NUMBER(9) default 0 not null,
  operator_code     VARCHAR2(20),
  area_code         VARCHAR2(20),
  pid               VARCHAR2(20),
  userid            VARCHAR2(20),
  linkid            VARCHAR2(20),
  msg_format        VARCHAR2(2)
)
;
alter table SMS_MT_WAIT
  add primary key (SEQUENCE_ID);

prompt
prompt Creating table STORE_FEE_TEMP1
prompt ==============================
prompt
create table STORE_FEE_TEMP1
(
  merc_id  VARCHAR2(40),
  prod_typ VARCHAR2(20),
  cn       NUMBER
)
;

prompt
prompt Creating table STORE_FEE_TEMP2
prompt ==============================
prompt
create table STORE_FEE_TEMP2
(
  merc_id  VARCHAR2(40),
  prod_typ VARCHAR2(20)
)
;

prompt
prompt Creating table SYS_EXPORT_SCHEMA_01
prompt ===================================
prompt
create table SYS_EXPORT_SCHEMA_01
(
  process_order          NUMBER,
  duplicate              NUMBER,
  dump_fileid            NUMBER,
  dump_position          NUMBER,
  dump_length            NUMBER,
  dump_orig_length       NUMBER,
  dump_allocation        NUMBER,
  completed_rows         NUMBER,
  error_count            NUMBER,
  elapsed_time           NUMBER,
  object_type_path       VARCHAR2(200),
  object_path_seqno      NUMBER,
  object_type            VARCHAR2(30),
  in_progress            CHAR(1),
  object_name            VARCHAR2(500),
  object_long_name       VARCHAR2(4000),
  object_schema          VARCHAR2(30),
  original_object_schema VARCHAR2(30),
  original_object_name   VARCHAR2(4000),
  partition_name         VARCHAR2(30),
  subpartition_name      VARCHAR2(30),
  dataobj_num            NUMBER,
  flags                  NUMBER,
  property               NUMBER,
  trigflag               NUMBER,
  creation_level         NUMBER,
  completion_time        DATE,
  object_tablespace      VARCHAR2(30),
  size_estimate          NUMBER,
  object_row             NUMBER,
  processing_state       CHAR(1),
  processing_status      CHAR(1),
  base_process_order     NUMBER,
  base_object_type       VARCHAR2(30),
  base_object_name       VARCHAR2(30),
  base_object_schema     VARCHAR2(30),
  ancestor_process_order NUMBER,
  domain_process_order   NUMBER,
  parallelization        NUMBER,
  unload_method          NUMBER,
  load_method            NUMBER,
  granules               NUMBER,
  scn                    NUMBER,
  grantor                VARCHAR2(30),
  xml_clob               CLOB,
  parent_process_order   NUMBER,
  name                   VARCHAR2(30),
  value_t                VARCHAR2(4000),
  value_n                NUMBER,
  is_default             NUMBER,
  file_type              NUMBER,
  user_directory         VARCHAR2(4000),
  user_file_name         VARCHAR2(4000),
  file_name              VARCHAR2(4000),
  extend_size            NUMBER,
  file_max_size          NUMBER,
  process_name           VARCHAR2(30),
  last_update            DATE,
  work_item              VARCHAR2(30),
  object_number          NUMBER,
  completed_bytes        NUMBER,
  total_bytes            NUMBER,
  metadata_io            NUMBER,
  data_io                NUMBER,
  cumulative_time        NUMBER,
  packet_number          NUMBER,
  instance_id            NUMBER,
  old_value              VARCHAR2(4000),
  seed                   NUMBER,
  last_file              NUMBER,
  user_name              VARCHAR2(30),
  operation              VARCHAR2(30),
  job_mode               VARCHAR2(30),
  queue_tabnum           NUMBER,
  control_queue          VARCHAR2(30),
  status_queue           VARCHAR2(30),
  remote_link            VARCHAR2(4000),
  version                NUMBER,
  job_version            VARCHAR2(30),
  db_version             VARCHAR2(30),
  timezone               VARCHAR2(64),
  state                  VARCHAR2(30),
  phase                  NUMBER,
  guid                   RAW(16),
  start_time             DATE,
  block_size             NUMBER,
  metadata_buffer_size   NUMBER,
  data_buffer_size       NUMBER,
  degree                 NUMBER,
  platform               VARCHAR2(101),
  abort_step             NUMBER,
  instance               VARCHAR2(60),
  cluster_ok             NUMBER,
  service_name           VARCHAR2(100),
  object_int_oid         VARCHAR2(32)
)
;
comment on table SYS_EXPORT_SCHEMA_01
  is 'Data Pump Master Table EXPORT                         SCHEMA                        ';
create index SYS_MTABLE_00003D7DE_IND_1 on SYS_EXPORT_SCHEMA_01 (OBJECT_SCHEMA, OBJECT_NAME, OBJECT_TYPE);
create index SYS_MTABLE_00003D7DE_IND_2 on SYS_EXPORT_SCHEMA_01 (BASE_PROCESS_ORDER);
alter table SYS_EXPORT_SCHEMA_01
  add unique (PROCESS_ORDER, DUPLICATE);

prompt
prompt Creating table SZMALL_ORDER_HISTORY
prompt ===================================
prompt
create table SZMALL_ORDER_HISTORY
(
  order_id    VARCHAR2(64),
  user_id     NUMBER(8),
  order_time  VARCHAR2(14),
  store_name  VARCHAR2(254),
  goods_name  VARCHAR2(254),
  totalcost   NUMBER(13,2),
  productcost NUMBER(13,2),
  status      VARCHAR2(2)
);
-- Add comments to the columns 
comment on column SZMALL_ORDER_HISTORY.order_id
  is '订单ID';
comment on column SZMALL_ORDER_HISTORY.user_id
  is '用户ID';
comment on column SZMALL_ORDER_HISTORY.order_time
  is '下单时间';
comment on column SZMALL_ORDER_HISTORY.store_name
  is '商户名';
comment on column SZMALL_ORDER_HISTORY.goods_name
  is '商品名';
comment on column SZMALL_ORDER_HISTORY.totalcost
  is '订单总金额';
comment on column SZMALL_ORDER_HISTORY.productcost
  is '商品金额';
comment on column SZMALL_ORDER_HISTORY.status
  is '订单状态 01待处理订单，02已收款未发货，03缺货订单，04无效订单，05应退款订单，06已发货，07已到货订单，08已退款订单';
-- Create/Recreate indexes 
create index IDX_SZMALL_ORDER_HISTORY on SZMALL_ORDER_HISTORY (ORDER_TIME)

prompt
prompt Creating table TEST01
prompt =====================
prompt
create table TEST01
(
  a01 VARCHAR2(100),
  a02 VARCHAR2(100),
  a03 VARCHAR2(100),
  a04 VARCHAR2(100),
  a05 VARCHAR2(100),
  a06 VARCHAR2(100),
  a07 VARCHAR2(100),
  a08 VARCHAR2(100),
  a09 VARCHAR2(100),
  a10 VARCHAR2(100),
  a11 VARCHAR2(100)
)
;

prompt
prompt Creating table TEST02
prompt =====================
prompt
create table TEST02
(
  a02 VARCHAR2(100),
  a05 VARCHAR2(100)
)
;

prompt
prompt Creating table TEST111
prompt ======================
prompt
create table TEST111
(
  sp_code        VARCHAR2(21) not null,
  command        VARCHAR2(20),
  target_sp_code VARCHAR2(21),
  target_command VARCHAR2(20)
)
;

prompt
prompt Creating table TMP_ITEM_PRICE_VIP
prompt =================================
prompt
create table TMP_ITEM_PRICE_VIP
(
  sale_id  NUMBER,
  vippirce NUMBER
)
;

prompt
prompt Creating table TMP_PRODUCT_CHECK
prompt ================================
prompt
create table TMP_PRODUCT_CHECK
(
  id NUMBER
)
;

prompt
prompt Creating table TMP_SYS_FEE
prompt ==========================
prompt
create table TMP_SYS_FEE
(
  id       NUMBER not null,
  store_id NUMBER
)
;
alter table TMP_SYS_FEE
  add constraint PK_TMP_FEE primary key (ID);

prompt
prompt Creating table T_ACT_ORDER
prompt ==========================
prompt
create table T_ACT_ORDER
(
  id                 NUMBER(19) not null,
  act_type           NUMBER(10),
  close_description  VARCHAR2(255 CHAR),
  close_status       NUMBER(10),
  close_time         VARCHAR2(255 CHAR),
  create_time        VARCHAR2(255 CHAR),
  expire_time        NUMBER(10),
  pay_description    VARCHAR2(255 CHAR),
  pay_status         NUMBER(10),
  pay_time           VARCHAR2(255 CHAR),
  refund_description VARCHAR2(255 CHAR),
  shop_id            NUMBER(19),
  shop_subject       VARCHAR2(255 CHAR),
  subject            VARCHAR2(255 CHAR),
  user_id            NUMBER(19),
  delete_description VARCHAR2(255 CHAR),
  delete_status      NUMBER(10),
  delete_time        VARCHAR2(14 CHAR),
  invoice_content    VARCHAR2(255 CHAR),
  invoice_subject    VARCHAR2(255 CHAR),
  invoice_type       NUMBER(10),
  create_source      NUMBER(10),
  remark             VARCHAR2(255 CHAR),
  uuid               VARCHAR2(32 CHAR),
  order_type         NUMBER(10),
  ext_info           VARCHAR2(500)
)
;
comment on column T_ACT_ORDER.id
  is 'ID ';
comment on column T_ACT_ORDER.act_type
  is '业务类型，20短信购，40网站商品购买，其他值内部测试 ';
comment on column T_ACT_ORDER.close_description
  is '订单关闭 说明';
comment on column T_ACT_ORDER.close_status
  is '订单关闭 状态, 0未关闭，1已关闭';
comment on column T_ACT_ORDER.close_time
  is '订单关闭 时间';
comment on column T_ACT_ORDER.create_time
  is '创建来源，用于标记网页创建的、短信创建的、wap创建的、手机客户端创建的……………… 1网站，2WAP，3客户端，4短信，0其他';
comment on column T_ACT_ORDER.expire_time
  is '订单超时时间（秒） ';
comment on column T_ACT_ORDER.pay_description
  is '订单支付 说明';
comment on column T_ACT_ORDER.pay_status
  is '订单支付 状态，0未支付，1支付中，2已支付';
comment on column T_ACT_ORDER.pay_time
  is '订单支付 时间';
comment on column T_ACT_ORDER.shop_id
  is '商户ID ';
comment on column T_ACT_ORDER.shop_subject
  is '商户标题 ';
comment on column T_ACT_ORDER.subject
  is '订单标题 ';
comment on column T_ACT_ORDER.user_id
  is '用户ID ';
comment on column T_ACT_ORDER.delete_description
  is '订单删除 说明';
comment on column T_ACT_ORDER.delete_status
  is '订单删除 状态，0未删除，1已删除';
comment on column T_ACT_ORDER.delete_time
  is '订单删除 时间';
comment on column T_ACT_ORDER.invoice_content
  is '发票内容 ';
comment on column T_ACT_ORDER.invoice_subject
  is '发票标题 ';
comment on column T_ACT_ORDER.invoice_type
  is '发票类型 0不要发票，1普通发票，2增值税发票';
comment on column T_ACT_ORDER.create_source
  is '创建来源 ';
comment on column T_ACT_ORDER.remark
  is '订单备注 ';
comment on column T_ACT_ORDER.uuid
  is '唯一号 ';
comment on column T_ACT_ORDER.ext_info
  is '订单扩展信息。用于下单时填写订单特有信息， 例如短信购的活动ID，营销活动的活动ID';
alter table T_ACT_ORDER
  add primary key (ID);

prompt
prompt Creating table T_ACT_ORDER_EXPRESS
prompt ==================================
prompt
create table T_ACT_ORDER_EXPRESS
(
  id                   NUMBER(19) not null,
  act_order_id         NUMBER(19),
  address              VARCHAR2(255 CHAR),
  cellphone_number     VARCHAR2(255 CHAR),
  express_company_id   NUMBER(19),
  express_company_name VARCHAR2(255 CHAR),
  express_cost         NUMBER(10),
  express_no           VARCHAR2(255 CHAR),
  receive_time         VARCHAR2(14 CHAR),
  receiver_name        VARCHAR2(255 CHAR),
  send_time            VARCHAR2(14 CHAR),
  status               NUMBER(10),
  status_description   VARCHAR2(255 CHAR),
  status_time          VARCHAR2(255 CHAR),
  telephone_number     VARCHAR2(255 CHAR),
  zip_code             VARCHAR2(255 CHAR),
  remark               VARCHAR2(255 CHAR),
  auto_receive_time    VARCHAR2(255 CHAR)
)
;
comment on column T_ACT_ORDER_EXPRESS.id
  is ' ';
comment on column T_ACT_ORDER_EXPRESS.act_order_id
  is '订单号 ';
comment on column T_ACT_ORDER_EXPRESS.address
  is '收货地址 ';
comment on column T_ACT_ORDER_EXPRESS.cellphone_number
  is '手机号码 ';
comment on column T_ACT_ORDER_EXPRESS.express_company_id
  is '物流公司 ';
comment on column T_ACT_ORDER_EXPRESS.express_company_name
  is '物流公司名称 ';
comment on column T_ACT_ORDER_EXPRESS.express_cost
  is '物流费（分） ';
comment on column T_ACT_ORDER_EXPRESS.express_no
  is '物流单号 ';
comment on column T_ACT_ORDER_EXPRESS.receive_time
  is '收货时间 ';
comment on column T_ACT_ORDER_EXPRESS.receiver_name
  is '收货人名称 ';
comment on column T_ACT_ORDER_EXPRESS.send_time
  is '发货时间 ';
comment on column T_ACT_ORDER_EXPRESS.status
  is '状态 0未发货，1已发货，2已收货';
comment on column T_ACT_ORDER_EXPRESS.status_description
  is '状态说明 ';
comment on column T_ACT_ORDER_EXPRESS.status_time
  is '状态时间 ';
comment on column T_ACT_ORDER_EXPRESS.telephone_number
  is '固话号码 ';
comment on column T_ACT_ORDER_EXPRESS.zip_code
  is '邮政编码 ';
comment on column T_ACT_ORDER_EXPRESS.remark
  is '物流备注 ';
create index IDX__ACT_ORDER_EXPRESS_01 on T_ACT_ORDER_EXPRESS (ACT_ORDER_ID);
alter table T_ACT_ORDER_EXPRESS
  add primary key (ID);

prompt
prompt Creating table T_ACT_ORDER_GOODS
prompt ================================
prompt
create table T_ACT_ORDER_GOODS
(
  id                 NUMBER(19) not null,
  act_order_id       NUMBER(19),
  count              NUMBER(10),
  discount           NUMBER(10),
  fee_price          NUMBER(10),
  goods_description  VARCHAR2(255 CHAR),
  goods_id           NUMBER(19),
  goods_subject      VARCHAR2(255 CHAR),
  pay_price          NUMBER(10),
  refund_amount      NUMBER(10),
  refund_count       NUMBER(10),
  refund_status      NUMBER(10),
  refund_time        VARCHAR2(255 CHAR),
  verify_code_id     VARCHAR2(30 CHAR),
  verify_description VARCHAR2(255 CHAR),
  verify_status      NUMBER(10),
  verify_time        VARCHAR2(255 CHAR),
  refund_description VARCHAR2(255 CHAR),
  remark             VARCHAR2(255 CHAR)
)
;
comment on column T_ACT_ORDER_GOODS.id
  is ' ';
comment on column T_ACT_ORDER_GOODS.act_order_id
  is '业务订单Id ';
comment on column T_ACT_ORDER_GOODS.count
  is '商品数量 ';
comment on column T_ACT_ORDER_GOODS.discount
  is '商品折扣（分） ';
comment on column T_ACT_ORDER_GOODS.fee_price
  is '结算金额（分） ';
comment on column T_ACT_ORDER_GOODS.goods_description
  is '商品描述 ';
comment on column T_ACT_ORDER_GOODS.goods_id
  is '商品ID ';
comment on column T_ACT_ORDER_GOODS.goods_subject
  is '商品标题 ';
comment on column T_ACT_ORDER_GOODS.pay_price
  is '支付单价 ';
comment on column T_ACT_ORDER_GOODS.refund_amount
  is '（暂不使用） ';
comment on column T_ACT_ORDER_GOODS.refund_count
  is '（暂不使用） ';
comment on column T_ACT_ORDER_GOODS.refund_status
  is '退款状态 0未退款，1已退款';
comment on column T_ACT_ORDER_GOODS.refund_time
  is '退款时间 ';
comment on column T_ACT_ORDER_GOODS.verify_code_id
  is '（暂不使用） ';
comment on column T_ACT_ORDER_GOODS.verify_description
  is '（暂不使用） ';
comment on column T_ACT_ORDER_GOODS.verify_status
  is '（暂不使用） ';
comment on column T_ACT_ORDER_GOODS.verify_time
  is '（暂不使用） ';
comment on column T_ACT_ORDER_GOODS.refund_description
  is '退款说明 ';
comment on column T_ACT_ORDER_GOODS.remark
  is '商品备注 ';
alter table T_ACT_ORDER_GOODS
  add primary key (ID);

prompt
prompt Creating table T_ACT_ORDER_HISTORY
prompt ==================================
prompt
create table T_ACT_ORDER_HISTORY
(
  id                  NUMBER(19) not null,
  act_order_id        NUMBER(19),
  status              NUMBER(10),
  status_type         NUMBER(10),
  update_data         VARCHAR2(255 CHAR),
  update_descriptions VARCHAR2(255 CHAR),
  update_time         VARCHAR2(255 CHAR)
)
;
comment on column T_ACT_ORDER_HISTORY.id
  is ' ';
comment on column T_ACT_ORDER_HISTORY.act_order_id
  is '订单ID ';
comment on column T_ACT_ORDER_HISTORY.status
  is '状态 ';
comment on column T_ACT_ORDER_HISTORY.status_type
  is '状态类型 1支付、2退款、3验证、4关闭、5物流';
comment on column T_ACT_ORDER_HISTORY.update_data
  is '更新数据 ';
comment on column T_ACT_ORDER_HISTORY.update_descriptions
  is '更新说明 ';
comment on column T_ACT_ORDER_HISTORY.update_time
  is '更新时间 ';
alter table T_ACT_ORDER_HISTORY
  add primary key (ID);

prompt
prompt Creating table T_ACT_ORDER_PAYMENT
prompt ==================================
prompt
create table T_ACT_ORDER_PAYMENT
(
  id           NUMBER(19) not null,
  act_order_id NUMBER(19),
  amount       NUMBER(10),
  channal      VARCHAR2(255 CHAR),
  currency     VARCHAR2(255 CHAR),
  splite_info  VARCHAR2(1000 CHAR)
)
;
comment on column T_ACT_ORDER_PAYMENT.id
  is ' ';
comment on column T_ACT_ORDER_PAYMENT.act_order_id
  is '订单ID ';
comment on column T_ACT_ORDER_PAYMENT.amount
  is '支付数量（分） ';
comment on column T_ACT_ORDER_PAYMENT.channal
  is '支付渠道 ';
comment on column T_ACT_ORDER_PAYMENT.currency
  is '支付币种（现金cash、商城币coin、积分score） ';
create index IDX__ACT_ORDER_PAYMENT_01 on T_ACT_ORDER_PAYMENT (ACT_ORDER_ID);
alter table T_ACT_ORDER_PAYMENT
  add primary key (ID);

prompt
prompt Creating table T_ACT_ORDER_SELF
prompt ===============================
prompt
create table T_ACT_ORDER_SELF
(
  id           NUMBER(19) not null,
  act_order_id NUMBER(19),
  self_get     NUMBER(1) default 0
)
;
comment on table T_ACT_ORDER_SELF
  is '订单自取记录表';
comment on column T_ACT_ORDER_SELF.act_order_id
  is '订单ID';
comment on column T_ACT_ORDER_SELF.self_get
  is '自取标示，0：未取，1：已取';
alter table T_ACT_ORDER_SELF
  add primary key (ID);

prompt
prompt Creating table T_APP_INFO
prompt =========================
prompt
create table T_APP_INFO
(
  id              NUMBER(9) not null,
  app_code        VARCHAR2(50),
  app_name        VARCHAR2(200),
  app_remark      CLOB,
  app_open_url    VARCHAR2(500),
  app_close_url   VARCHAR2(500),
  app_url         VARCHAR2(500),
  status          NUMBER(2) default 0,
  app_contacts    VARCHAR2(200),
  app_tel         VARCHAR2(200),
  app_mail        VARCHAR2(200),
  app_notice_url1 VARCHAR2(200)
)
;
comment on column T_APP_INFO.status
  is '1：在用
0：停用';
alter table T_APP_INFO
  add constraint PK_T_APP_INFO primary key (ID);

prompt
prompt Creating table T_AUDIT_STEP
prompt ===========================
prompt
create table T_AUDIT_STEP
(
  id            NUMBER(8) not null,
  bs_id         NUMBER(8),
  bs_type       NUMBER(1),
  status        NUMBER(1),
  remark        VARCHAR2(100),
  audit_user_id NUMBER(8),
  update_time   VARCHAR2(14),
  bs_tabel      VARCHAR2(100)
)
;
comment on table T_AUDIT_STEP
  is '审核步骤记录表，涉及多次审核';
comment on column T_AUDIT_STEP.bs_id
  is '根据不同业务类型，所对应的商户id、商品id等';
comment on column T_AUDIT_STEP.bs_type
  is '1--业务门店
2--商户
3--渠道商
4--商品
5--已发布商品';
comment on column T_AUDIT_STEP.status
  is '1--审核通过
2--审核驳回';
comment on column T_AUDIT_STEP.bs_tabel
  is '业务表名';
alter table T_AUDIT_STEP
  add constraint PK_T_AUDIT_STEP primary key (ID);

prompt
prompt Creating table T_BATCH_SUBTASK
prompt ==============================
prompt
create table T_BATCH_SUBTASK
(
  task_id          NUMBER(9) not null,
  parent_task_id   NUMBER(9) not null,
  mmsc_id          VARCHAR2(50),
  mobile_file_path VARCHAR2(250)
)
;
comment on table T_BATCH_SUBTASK
  is 'PUSH群发子任务表';
comment on column T_BATCH_SUBTASK.task_id
  is '任务ID';
comment on column T_BATCH_SUBTASK.parent_task_id
  is '主任务ID';
comment on column T_BATCH_SUBTASK.mmsc_id
  is '彩信中心编号925001
1：定制群发任务';
comment on column T_BATCH_SUBTASK.mobile_file_path
  is '号码列表文件';
alter table T_BATCH_SUBTASK
  add constraint PK_T_BATCH_SUBTASK primary key (TASK_ID);

prompt
prompt Creating table T_BATCH_TASK
prompt ===========================
prompt
create table T_BATCH_TASK
(
  id                 NUMBER(9) not null,
  custom_tag         NUMBER(3),
  task_type          NUMBER(3) not null,
  unit_id            VARCHAR2(20) not null,
  priority           NUMBER(3) not null,
  speed              NUMBER(3) not null,
  is_report          NUMBER(3) not null,
  start_time         VARCHAR2(14),
  stop_time          VARCHAR2(14),
  sleep_time         VARCHAR2(250),
  status             NUMBER(3) not null,
  sp_code            VARCHAR2(20),
  service_id         VARCHAR2(20),
  fee_type           NUMBER(3),
  fee                NUMBER(5),
  trace_respond      NUMBER(3) not null,
  respond_timeout    VARCHAR2(14),
  info_id            NUMBER(9),
  title              VARCHAR2(200),
  content            VARCHAR2(200),
  smil               VARCHAR2(200),
  exec_start_time    VARCHAR2(14),
  exec_stop_time     VARCHAR2(14),
  mobile_list_file   VARCHAR2(200),
  white_list_file    VARCHAR2(200),
  black_list_file    VARCHAR2(200),
  notice_terminal_id VARCHAR2(200),
  task_name          VARCHAR2(200),
  submit_cnt         NUMBER(9),
  success_cnt        NUMBER(9),
  black_cnt          NUMBER(9),
  fail_cnt           NUMBER(9),
  creator_id         NUMBER(9) not null,
  create_time        VARCHAR2(14) not null,
  auditor_id         NUMBER(9),
  audit_time         VARCHAR2(14),
  advice             VARCHAR2(200),
  submit_localflag   NUMBER(3),
  act_id             NUMBER(19),
  area_code          VARCHAR2(255 CHAR),
  service_doc        VARCHAR2(255 CHAR),
  item_id            VARCHAR2(50),
  batch_type         NUMBER(3),
  router_id          NUMBER(12)
)
;
comment on table T_BATCH_TASK
  is '保存群发任务，以及任务的执行状态。';
comment on column T_BATCH_TASK.id
  is '任务ID';
comment on column T_BATCH_TASK.custom_tag
  is '0：手工群发任务
1：定制群发任务';
comment on column T_BATCH_TASK.task_type
  is '1：短信群发
2：彩信群发
3：WAP Push';
comment on column T_BATCH_TASK.unit_id
  is '任务归属的部门的ID';
comment on column T_BATCH_TASK.priority
  is '发送优先级0-999:高到低';
comment on column T_BATCH_TASK.speed
  is '每秒发送的条数';
comment on column T_BATCH_TASK.is_report
  is '是否收取状态报告:
 0:否
 1:是';
comment on column T_BATCH_TASK.start_time
  is '任务的发送时间';
comment on column T_BATCH_TASK.stop_time
  is '任务的终止时间';
comment on column T_BATCH_TASK.sleep_time
  is '自动暂停发送时间: HHNN-HHNN,HHNN-HHNN';
comment on column T_BATCH_TASK.status
  is '任务状态：
0：未审核
1：已审核
2：审核不通过
3：暂停
4：发送中
5：发送完毕
6：发送失败
7：任务预处理
8：预处理完成，等待发送';
comment on column T_BATCH_TASK.service_id
  is '计费代码';
comment on column T_BATCH_TASK.fee_type
  is '计费类型 1免费,2安条收费,3包月收费';
comment on column T_BATCH_TASK.fee
  is '计费金额 单位分';
comment on column T_BATCH_TASK.trace_respond
  is '是否跟踪用户回应
0：否
1：是';
comment on column T_BATCH_TASK.respond_timeout
  is '更总用户回应的最长时间 单位分钟';
comment on column T_BATCH_TASK.info_id
  is '用户选择的发送信息的ID';
comment on column T_BATCH_TASK.title
  is '短信的内容，彩信的标题，WAP的标题';
comment on column T_BATCH_TASK.content
  is '彩信的内容（存放路径），WAP的URL';
comment on column T_BATCH_TASK.smil
  is '彩信的SMIL';
comment on column T_BATCH_TASK.exec_start_time
  is '执行开始时间';
comment on column T_BATCH_TASK.exec_stop_time
  is '执行结束时间';
comment on column T_BATCH_TASK.mobile_list_file
  is '号码总数';
comment on column T_BATCH_TASK.white_list_file
  is '白名单总数';
comment on column T_BATCH_TASK.black_list_file
  is '黑名单总数';
comment on column T_BATCH_TASK.submit_cnt
  is '任务总数量';
comment on column T_BATCH_TASK.success_cnt
  is '成功下发量';
comment on column T_BATCH_TASK.black_cnt
  is '黑名单数量';
comment on column T_BATCH_TASK.fail_cnt
  is '失败数量';
comment on column T_BATCH_TASK.creator_id
  is '创建人ID';
comment on column T_BATCH_TASK.create_time
  is '创建日期YYYYMMDDHHNNSS';
comment on column T_BATCH_TASK.auditor_id
  is '审核人ID';
comment on column T_BATCH_TASK.audit_time
  is '审核日期YYYYMMDDHHNNSS';
comment on column T_BATCH_TASK.advice
  is '审核说明';
comment on column T_BATCH_TASK.submit_localflag
  is '审核操作类型：1:本地审核,2:上级单位审核';
comment on column T_BATCH_TASK.act_id
  is '短信购活动ID
';
comment on column T_BATCH_TASK.item_id
  is '新增商品ID';
comment on column T_BATCH_TASK.batch_type
  is '1 普通群发，2 短信购';
comment on column T_BATCH_TASK.router_id
  is '商品指令';
alter table T_BATCH_TASK
  add constraint PK_T_BATCH_TASK primary key (ID);

prompt
prompt Creating table T_BATCH_TASK_SCHEDULE
prompt ====================================
prompt
create table T_BATCH_TASK_SCHEDULE
(
  id                    NUMBER(9) not null,
  user_code             VARCHAR2(100) not null,
  status                NUMBER(9) not null,
  upload_terminal_count NUMBER(8),
  result_terminal_count NUMBER(8),
  create_time           VARCHAR2(14) not null,
  end_time              VARCHAR2(14),
  upload_file_path      VARCHAR2(200),
  result_file_path      VARCHAR2(200),
  date_time             VARCHAR2(8) not null,
  filter_type           NUMBER(1) not null,
  limit                 NUMBER(5) default 5,
  failure_reason        VARCHAR2(200),
  remark                VARCHAR2(100),
  task_type             NUMBER(1)
)
;
comment on table T_BATCH_TASK_SCHEDULE
  is '群发排期表';
comment on column T_BATCH_TASK_SCHEDULE.id
  is '编号';
comment on column T_BATCH_TASK_SCHEDULE.user_code
  is '创建帐号';
comment on column T_BATCH_TASK_SCHEDULE.status
  is '状态 0-删除;1-有效(未处理);2-无效(不处理);3-处理中;4-处理完成;5-处理失败';
comment on column T_BATCH_TASK_SCHEDULE.upload_terminal_count
  is '上传号码数(由后台进程维护)';
comment on column T_BATCH_TASK_SCHEDULE.result_terminal_count
  is '处理结果实际过滤号码数(由后台进程维护)';
comment on column T_BATCH_TASK_SCHEDULE.create_time
  is '开始时间(录入系统时间)';
comment on column T_BATCH_TASK_SCHEDULE.end_time
  is '结束时间(后台进程处理完时间)';
comment on column T_BATCH_TASK_SCHEDULE.upload_file_path
  is '上传文件路径';
comment on column T_BATCH_TASK_SCHEDULE.result_file_path
  is '进程处理结果文件路径';
comment on column T_BATCH_TASK_SCHEDULE.date_time
  is '处理日期';
comment on column T_BATCH_TASK_SCHEDULE.filter_type
  is '过滤范围 0-全过滤(不限类型);1-普通短信;2;短信购';
comment on column T_BATCH_TASK_SCHEDULE.limit
  is '定义收到多少条短信的用户被过滤';
comment on column T_BATCH_TASK_SCHEDULE.failure_reason
  is '进程处理失败原因';
comment on column T_BATCH_TASK_SCHEDULE.remark
  is '备注';
comment on column T_BATCH_TASK_SCHEDULE.task_type
  is '1-普通群发；2-短信购';
alter table T_BATCH_TASK_SCHEDULE
  add constraint PK_T_BATCH_TASK_SCHEDULE primary key (ID);

prompt
prompt Creating table T_BOSS_PRECONTRACT
prompt =================================
prompt
create table T_BOSS_PRECONTRACT
(
  id           NUMBER(9) not null,
  mobile       VARCHAR2(21),
  prd_code     VARCHAR2(50),
  opr_code     VARCHAR2(4),
  area_code    VARCHAR2(20),
  opr_time     VARCHAR2(20),
  service_id   VARCHAR2(20),
  receive_time VARCHAR2(20),
  insert_time  VARCHAR2(20),
  status       NUMBER(3),
  trans_id     VARCHAR2(40),
  error        VARCHAR2(1000)
)
;
comment on column T_BOSS_PRECONTRACT.id
  is '主键';
comment on column T_BOSS_PRECONTRACT.mobile
  is '号码';
comment on column T_BOSS_PRECONTRACT.prd_code
  is '需要开通的应用，多个用半角逗号分隔';
comment on column T_BOSS_PRECONTRACT.opr_code
  is '操作类型，01开通，02关闭，03暂停，04恢复，10变更';
comment on column T_BOSS_PRECONTRACT.area_code
  is '地区';
comment on column T_BOSS_PRECONTRACT.opr_time
  is '操作时间（预约操作时间），14位时间';
comment on column T_BOSS_PRECONTRACT.service_id
  is '业务代码';
comment on column T_BOSS_PRECONTRACT.receive_time
  is '接收时间，14位时间';
comment on column T_BOSS_PRECONTRACT.insert_time
  is '插表时间，14位时间';
comment on column T_BOSS_PRECONTRACT.status
  is '状态，0未处理，1已处理，2处理成功，3处理失败，4忽略';
comment on column T_BOSS_PRECONTRACT.trans_id
  is 'Boss请求的编号';
comment on column T_BOSS_PRECONTRACT.error
  is '错误详情';
alter table T_BOSS_PRECONTRACT
  add constraint PK_T_BOSS_PRECONTRACT primary key (ID);

prompt
prompt Creating table T_BOSS_REQUEST
prompt =============================
prompt
create table T_BOSS_REQUEST
(
  id          NUMBER(9) not null,
  terminal_id VARCHAR2(21) not null,
  area_code   VARCHAR2(10) not null,
  type        VARCHAR2(2) not null,
  send_date   VARCHAR2(14),
  status      NUMBER(1) default 0 not null,
  error       VARCHAR2(500),
  product_id  VARCHAR2(10) not null,
  req_src     VARCHAR2(50) not null,
  insert_time VARCHAR2(14) not null
)
;
comment on column T_BOSS_REQUEST.id
  is '主键';
comment on column T_BOSS_REQUEST.terminal_id
  is '号码';
comment on column T_BOSS_REQUEST.area_code
  is '地区（NJ、SZ等）';
comment on column T_BOSS_REQUEST.type
  is '处理类型，01开通，02关闭';
comment on column T_BOSS_REQUEST.send_date
  is '发送时间';
comment on column T_BOSS_REQUEST.status
  is '状态，0未处理，1处理成功，2处理失败，3已处理待发送';
comment on column T_BOSS_REQUEST.error
  is '处理失败的原因';
comment on column T_BOSS_REQUEST.product_id
  is '产品编号';
comment on column T_BOSS_REQUEST.req_src
  is '插入记录的来源';
comment on column T_BOSS_REQUEST.insert_time
  is '插入记录的时间';
alter table T_BOSS_REQUEST
  add constraint PK_T_BOSS_REQUEST primary key (ID);

prompt
prompt Creating table T_BRAND
prompt ======================
prompt
create table T_BRAND
(
  id          NUMBER(9) not null,
  name        VARCHAR2(50),
  remark      VARCHAR2(200),
  website     VARCHAR2(100),
  brand_img   VARCHAR2(100),
  create_time VARCHAR2(14),
  is_valid    NUMBER(1)
)
;
comment on table T_BRAND
  is '商品品牌表';
comment on column T_BRAND.name
  is '品牌名称';
comment on column T_BRAND.remark
  is '品牌描述';
comment on column T_BRAND.website
  is '品牌网站';
comment on column T_BRAND.brand_img
  is '品牌图片';
comment on column T_BRAND.create_time
  is '创建时间';
comment on column T_BRAND.is_valid
  is '是否有效
0-无效
1-有效';
alter table T_BRAND
  add constraint PK_T_BRAND primary key (ID);

prompt
prompt Creating table T_CART
prompt =====================
prompt
create table T_CART
(
  uuid        VARCHAR2(64),
  access_time NUMBER,
  expire_time NUMBER,
  content     CLOB
)
;

prompt
prompt Creating table T_CHANNEL_CATALOG_CONF
prompt =====================================
prompt
create table T_CHANNEL_CATALOG_CONF
(
  id          NUMBER(8) not null,
  item_id     NUMBER(8),
  group_id    NUMBER(4),
  update_time VARCHAR2(14),
  order_index NUMBER(4),
  status      NUMBER(1) default 1,
  channel     NUMBER(4) default 1,
  region_code VARCHAR2(100)
)
;
comment on table T_CHANNEL_CATALOG_CONF
  is '产品推送分组配置表';
comment on column T_CHANNEL_CATALOG_CONF.id
  is '主键';
comment on column T_CHANNEL_CATALOG_CONF.item_id
  is '商品ID';
comment on column T_CHANNEL_CATALOG_CONF.group_id
  is '分组类别id 1-1楼, 2-2楼推送, 3-3楼推送, 4-4楼推送,5-新品首发, 11-合约机, 12-城市特色, 13-新品推荐, 14-热卖商品';
comment on column T_CHANNEL_CATALOG_CONF.update_time
  is '更新时间';
comment on column T_CHANNEL_CATALOG_CONF.order_index
  is '排序';
comment on column T_CHANNEL_CATALOG_CONF.status
  is '状态 1-启用, 0禁用';
comment on column T_CHANNEL_CATALOG_CONF.channel
  is '频道 1-商城首页';
comment on column T_CHANNEL_CATALOG_CONF.region_code
  is '省市地区编码';
alter table T_CHANNEL_CATALOG_CONF
  add constraint T_CHANNEL_CATALOG_ID primary key (ID);

prompt
prompt Creating table T_CHANNEL_CATALOG_RCMD_CONF
prompt ==========================================
prompt
create table T_CHANNEL_CATALOG_RCMD_CONF
(
  id           NUMBER(8) not null,
  group_id     NUMBER(4),
  channel      NUMBER(4),
  display_name VARCHAR2(20),
  link_url     VARCHAR2(500),
  order_index  NUMBER(4),
  type         NUMBER(2) default 1,
  image_path   VARCHAR2(150),
  region_code  VARCHAR2(100)
)
;
comment on column T_CHANNEL_CATALOG_RCMD_CONF.group_id
  is '分组类别id
1-1楼,
2-2楼推送,
3-3楼推送,
4-4楼推送,
';
comment on column T_CHANNEL_CATALOG_RCMD_CONF.channel
  is '频道 1-商城首页';
comment on column T_CHANNEL_CATALOG_RCMD_CONF.type
  is '1-文字推送类型, 2-图片推送类型';
comment on column T_CHANNEL_CATALOG_RCMD_CONF.image_path
  is '图片推送类型情况的图片路径';
comment on column T_CHANNEL_CATALOG_RCMD_CONF.region_code
  is '省市地区编码';
alter table T_CHANNEL_CATALOG_RCMD_CONF
  add constraint PK_T_CHANNEL_CATALOG_RCMD_CONF primary key (ID);

prompt
prompt Creating table T_CHANNEL_FLOOR_CONF
prompt ===================================
prompt
create table T_CHANNEL_FLOOR_CONF
(
  id          NUMBER(8) not null,
  title       VARCHAR2(100),
  floor_id    NUMBER(4),
  type_id     NUMBER(8),
  order_index NUMBER(4),
  channel     NUMBER(4) default 1,
  region_code VARCHAR2(100),
  update_time VARCHAR2(14)
)
;
comment on column T_CHANNEL_FLOOR_CONF.id
  is '主键ID';
comment on column T_CHANNEL_FLOOR_CONF.title
  is '楼层标题 可以为空';
comment on column T_CHANNEL_FLOOR_CONF.floor_id
  is '楼层ID,  0-顶层, 1-一层(以此类推)';
comment on column T_CHANNEL_FLOOR_CONF.type_id
  is '类别ID, 对应商品的分类ID';
comment on column T_CHANNEL_FLOOR_CONF.order_index
  is '排序';
comment on column T_CHANNEL_FLOOR_CONF.channel
  is '频道 1-商城';
comment on column T_CHANNEL_FLOOR_CONF.region_code
  is '区域编号';
comment on column T_CHANNEL_FLOOR_CONF.update_time
  is '更新时间';
alter table T_CHANNEL_FLOOR_CONF
  add constraint PK_T_CHANNEL_FLOOR_CONF_ID primary key (ID);

prompt
prompt Creating table T_CHANNEL_PIC_CONF
prompt =================================
prompt
create table T_CHANNEL_PIC_CONF
(
  id          NUMBER(8) not null,
  pic_path    VARCHAR2(150),
  pic_alt     VARCHAR2(150),
  pic_index   NUMBER(4),
  link_url    VARCHAR2(150),
  postion     NUMBER(2),
  channel     NUMBER(1) default 1,
  update_time VARCHAR2(14),
  region_code VARCHAR2(100)
)
;
comment on table T_CHANNEL_PIC_CONF
  is '首页图片轮换表';
comment on column T_CHANNEL_PIC_CONF.id
  is '主键';
comment on column T_CHANNEL_PIC_CONF.pic_path
  is '图片路径';
comment on column T_CHANNEL_PIC_CONF.pic_alt
  is '图片名称';
comment on column T_CHANNEL_PIC_CONF.pic_index
  is '图片排序';
comment on column T_CHANNEL_PIC_CONF.link_url
  is '链接';
comment on column T_CHANNEL_PIC_CONF.postion
  is '位置 0-标识最顶部, 往下依次为1楼,2,3,4';
comment on column T_CHANNEL_PIC_CONF.channel
  is '频道1-商城';
comment on column T_CHANNEL_PIC_CONF.update_time
  is '更新时间';
comment on column T_CHANNEL_PIC_CONF.region_code
  is '省市地区编码';
alter table T_CHANNEL_PIC_CONF
  add constraint T_CHANNEL_PIC_ID primary key (ID);

prompt
prompt Creating table T_CHANNEL_TYPE
prompt =============================
prompt
create table T_CHANNEL_TYPE
(
  id           NUMBER(20) not null,
  type_id      NUMBER(20) not null,
  display_name VARCHAR2(100),
  region_code  VARCHAR2(100),
  channel      NUMBER(4) default 1
)
;
comment on column T_CHANNEL_TYPE.id
  is '主键';
comment on column T_CHANNEL_TYPE.type_id
  is '商品分类ID';
comment on column T_CHANNEL_TYPE.display_name
  is '显示名称';
comment on column T_CHANNEL_TYPE.region_code
  is '省市地区编码';
comment on column T_CHANNEL_TYPE.channel
  is '频道 1-商城首页';
alter table T_CHANNEL_TYPE
  add constraint PK_T_CHANNEL_TYPE primary key (ID);

prompt
prompt Creating table T_CONT_CODE
prompt ==========================
prompt
create table T_CONT_CODE
(
  id        NUMBER(9) not null,
  code      VARCHAR2(20) not null,
  name      VARCHAR2(100) not null,
  area_code VARCHAR2(8),
  cont_type NUMBER(1) default 1 not null,
  code_type NUMBER(2) default 0 not null
)
;
comment on table T_CONT_CODE
  is '12580信息内容源表';
comment on column T_CONT_CODE.id
  is '内容源ID，使用序列SEQ_CONT_CODE';
comment on column T_CONT_CODE.code
  is '内容源英文标识，唯一';
comment on column T_CONT_CODE.name
  is '内容源名称';
comment on column T_CONT_CODE.area_code
  is '内容源归属地市,例如025';
comment on column T_CONT_CODE.cont_type
  is '内容源信息类型，0表示短信，1表示彩信';
comment on column T_CONT_CODE.code_type
  is '内容源使用类型，0表示点播，1表示定制,3 折扣券短信,4挂机短信,5 挂机彩信, 6,广告彩信,7 折扣券彩信,8 种子彩信,9 群发,10 添加折扣券彩信时录入短信';
create index INDX_12580_CONT_CODE on T_CONT_CODE (CODE_TYPE);
alter table T_CONT_CODE
  add constraint PK_12580_CONT_CODE primary key (ID);
alter table T_CONT_CODE
  add constraint UK_12580_CONT_CODE unique (CODE);

prompt
prompt Creating table T_CONT_MMS
prompt =========================
prompt
create table T_CONT_MMS
(
  id             NUMBER(9) not null,
  content_src    VARCHAR2(20) not null,
  title          VARCHAR2(200) not null,
  content_path   VARCHAR2(100),
  content        VARCHAR2(2000) not null,
  pic_size       VARCHAR2(200),
  smil_name      VARCHAR2(200),
  start_time     VARCHAR2(14) not null,
  end_time       VARCHAR2(14),
  keyword        VARCHAR2(200),
  area_code      VARCHAR2(100),
  remark         VARCHAR2(200),
  param1         VARCHAR2(60),
  param2         VARCHAR2(60),
  unit_id        VARCHAR2(20) not null,
  content_size   NUMBER(9),
  status         NUMBER(3) not null,
  auditor_id     NUMBER(9),
  advice         VARCHAR2(200),
  update_user_id NUMBER(9),
  update_time    VARCHAR2(14)
)
;
comment on table T_CONT_MMS
  is '彩信信息表';
comment on column T_CONT_MMS.content_src
  is '如果是cs产品，该字段填写cs产品的产品id';
comment on column T_CONT_MMS.status
  is '0:未审核   1:审核通过   2:审核驳回    3:内部审核通过   4:内部审核驳回';
comment on column T_CONT_MMS.advice
  is '审核驳回时存放原因';
create index IDX_T_CONT_MMS_01 on T_CONT_MMS (CONTENT_SRC, STATUS, START_TIME, END_TIME);
alter table T_CONT_MMS
  add constraint PK_T_CONT_MMS primary key (ID);

prompt
prompt Creating table T_CONT_SMS
prompt =========================
prompt
create table T_CONT_SMS
(
  id             NUMBER(9) not null,
  content_src    VARCHAR2(20) not null,
  content        VARCHAR2(1000) not null,
  start_time     VARCHAR2(14),
  end_time       VARCHAR2(14),
  keyword        VARCHAR2(200),
  area_code      VARCHAR2(100),
  unit_id        VARCHAR2(20) not null,
  remark         VARCHAR2(200),
  status         NUMBER(3) not null,
  auditor_id     NUMBER(9),
  advice         VARCHAR2(200),
  update_user_id NUMBER(9),
  update_time    VARCHAR2(14)
)
;
comment on table T_CONT_SMS
  is '短信信息表';
comment on column T_CONT_SMS.status
  is '0:未审核   1:审核通过   2:审核驳回    3:内部审核通过   4:内部审核驳回';
comment on column T_CONT_SMS.advice
  is '审核驳回时存放原因';

prompt
prompt Creating table T_ES_EVENT_TYPES
prompt ===============================
prompt
create table T_ES_EVENT_TYPES
(
  event_type_id          NUMBER(9),
  event_type_name        VARCHAR2(200),
  event_type_description VARCHAR2(500),
  id                     NUMBER(9)
)
;
comment on table T_ES_EVENT_TYPES
  is '事件类型表，用于事件中心的UI界面展示';
comment on column T_ES_EVENT_TYPES.event_type_id
  is '事件类型ID';
comment on column T_ES_EVENT_TYPES.event_type_name
  is '事件类型名称，例如“用户身份验证”';
comment on column T_ES_EVENT_TYPES.event_type_description
  is '事件类型说明，';

prompt
prompt Creating table T_ES_LISTENERS
prompt =============================
prompt
create table T_ES_LISTENERS
(
  listener_id            NUMBER(9) not null,
  listener_name          VARCHAR2(200),
  listener_description   VARCHAR2(500),
  event_send_type        NUMBER(3) default 0,
  event_send_address     VARCHAR2(200),
  event_send_concurrency NUMBER(3) default 1
)
;
comment on table T_ES_LISTENERS
  is '事件监听者';
comment on column T_ES_LISTENERS.listener_id
  is '事件监听者ID';
comment on column T_ES_LISTENERS.listener_name
  is '事件监听者名称';
comment on column T_ES_LISTENERS.listener_description
  is '事件监听者说明';
comment on column T_ES_LISTENERS.event_send_type
  is '事件发送方式，0：Servlet';
comment on column T_ES_LISTENERS.event_send_address
  is '事件发送地址，type=0:URL地址';
comment on column T_ES_LISTENERS.event_send_concurrency
  is '发送线程数量';
alter table T_ES_LISTENERS
  add constraint PK_ES_LISTENERS primary key (LISTENER_ID);

prompt
prompt Creating table T_ES_LISTENER_EVENTS
prompt ===================================
prompt
create table T_ES_LISTENER_EVENTS
(
  listener_id   NUMBER(9),
  event_type_id NUMBER(9),
  match_text    VARCHAR2(255),
  id            NUMBER(9)
)
;
comment on column T_ES_LISTENER_EVENTS.listener_id
  is '监听者ID';
comment on column T_ES_LISTENER_EVENTS.event_type_id
  is '消息事务ID';

prompt
prompt Creating table T_FAVORITES
prompt ==========================
prompt
create table T_FAVORITES
(
  id          NUMBER(9) not null,
  name        VARCHAR2(200),
  type        NUMBER(1),
  url         VARCHAR2(200),
  create_time VARCHAR2(14),
  obj_id      NUMBER(9),
  user_id     NUMBER(9)
)
;
comment on column T_FAVORITES.name
  is '名称';
comment on column T_FAVORITES.type
  is '类型
1-商品
2-店铺';
comment on column T_FAVORITES.url
  is '地址';
comment on column T_FAVORITES.create_time
  is '收藏时间';
comment on column T_FAVORITES.obj_id
  is '对象id';
comment on column T_FAVORITES.user_id
  is '收藏人';
alter table T_FAVORITES
  add constraint PK_T_FAVORITES primary key (ID);

prompt
prompt Creating table T_GOODSHELF_GOODS_REL
prompt ====================================
prompt
create table T_GOODSHELF_GOODS_REL
(
  id       NUMBER(9) not null,
  shelf_id NUMBER(9),
  good_id  NUMBER(9)
)
;
comment on table T_GOODSHELF_GOODS_REL
  is '货架关联商品关系表';
comment on column T_GOODSHELF_GOODS_REL.shelf_id
  is '货架ID';
comment on column T_GOODSHELF_GOODS_REL.good_id
  is '商品ID（对应t_item_org的id）';
alter table T_GOODSHELF_GOODS_REL
  add constraint T_GOODSHELF_REL_ID primary key (ID);

prompt
prompt Creating table T_GOODSHELF_GOODS_REL_1
prompt ======================================
prompt
create table T_GOODSHELF_GOODS_REL_1
(
  id            NUMBER(9) not null,
  shelf_id      NUMBER(9),
  good_id       NUMBER(9),
  xw_product_id VARCHAR2(60)
)
;

prompt
prompt Creating table T_GOOD_SHELF
prompt ===========================
prompt
create table T_GOOD_SHELF
(
  id           NUMBER(9) not null,
  title        VARCHAR2(50),
  pid          NUMBER(9),
  level_flag   NUMBER(1),
  order_index  NUMBER(3),
  shop_class   NUMBER(1),
  shop_id      NUMBER(9),
  shop_user_id NUMBER(9),
  update_time  VARCHAR2(14),
  img_url      VARCHAR2(100)
)
;
comment on table T_GOOD_SHELF
  is '货架分类表';
comment on column T_GOOD_SHELF.title
  is '货架分类名称';
comment on column T_GOOD_SHELF.pid
  is '父货架ID';
comment on column T_GOOD_SHELF.level_flag
  is '货架层级（暂只支持三级:1,2,3）';
comment on column T_GOOD_SHELF.order_index
  is '排序';
comment on column T_GOOD_SHELF.shop_class
  is '商户分类：1--业务门店
2--商户
3--渠道商';
comment on column T_GOOD_SHELF.shop_id
  is '商户编号';
comment on column T_GOOD_SHELF.shop_user_id
  is '创建商户账号';
comment on column T_GOOD_SHELF.update_time
  is '更新时间';
comment on column T_GOOD_SHELF.img_url
  is '图片地址(相对地址) ';
alter table T_GOOD_SHELF
  add constraint T_GOOD_SHELF_ID primary key (ID);

prompt
prompt Creating table T_GOOD_SHELF_1
prompt =============================
prompt
create table T_GOOD_SHELF_1
(
  id           NUMBER(9),
  title        VARCHAR2(50),
  pid          NUMBER(9),
  level_flag   NUMBER(1),
  order_index  NUMBER(2),
  shop_class   NUMBER(1),
  shop_id      NUMBER(9),
  shop_user_id NUMBER(9),
  update_time  VARCHAR2(14),
  img_url      VARCHAR2(100),
  xw_store_id  VARCHAR2(32)
)
;

prompt
prompt Creating table T_HISUN_PRODUCTION_LINK
prompt ======================================
prompt
create table T_HISUN_PRODUCTION_LINK
(
  id                  NUMBER(9) not null,
  settle_id           NUMBER(9),
  production_id       VARCHAR2(32),
  item_id             NUMBER(9),
  production_type     VARCHAR2(32),
  settle_price        NUMBER(9,2),
  create_time         VARCHAR2(14),
  production_id_cash  VARCHAR2(32),
  production_id_coin  VARCHAR2(32),
  production_id_score VARCHAR2(32)
)
;
comment on column T_HISUN_PRODUCTION_LINK.settle_id
  is '商品结算资料表id';
comment on column T_HISUN_PRODUCTION_LINK.production_id
  is '高阳商品id';
comment on column T_HISUN_PRODUCTION_LINK.item_id
  is '销售商品id';
comment on column T_HISUN_PRODUCTION_LINK.production_type
  is '对应t_sys_fee的id';
comment on column T_HISUN_PRODUCTION_LINK.settle_price
  is '结算价 单位分';
comment on column T_HISUN_PRODUCTION_LINK.create_time
  is '创建时间';
comment on column T_HISUN_PRODUCTION_LINK.production_id_cash
  is '高阳商品id(现金)';
comment on column T_HISUN_PRODUCTION_LINK.production_id_coin
  is '高阳商品id(商城币)';
comment on column T_HISUN_PRODUCTION_LINK.production_id_score
  is '高阳商品id(积分)';
alter table T_HISUN_PRODUCTION_LINK
  add constraint PK_T_HISUN_PRODUCTION_LINK primary key (ID);

prompt
prompt Creating table T_HISUN_PRODUCTION_SETTLE
prompt ========================================
prompt
create table T_HISUN_PRODUCTION_SETTLE
(
  id                NUMBER(9) not null,
  merchid           VARCHAR2(32) not null,
  store_id          VARCHAR2(32) not null,
  serviceid         VARCHAR2(10) not null,
  contractid        VARCHAR2(32),
  contracteffdate   VARCHAR2(8),
  contractexpdate   VARCHAR2(8),
  productionid      VARCHAR2(32) not null,
  productionname    VARCHAR2(255) not null,
  productiontype    VARCHAR2(32) not null,
  capitaltype3      NUMBER(1) default 0,
  capitaltype2      NUMBER(1) default 0,
  capitaltype1      NUMBER(1) default 0,
  verifyflag        VARCHAR2(1) not null,
  verifysettleflag  VARCHAR2(2),
  productionefftime VARCHAR2(14) not null,
  productionexptime VARCHAR2(14) not null,
  verifyexpdate     VARCHAR2(8),
  cityid            VARCHAR2(32),
  cityname          VARCHAR2(32),
  cityprofitrate    VARCHAR2(2),
  agentid           VARCHAR2(32),
  agentname         VARCHAR2(125),
  agentprofitrate   VARCHAR2(2),
  price             NUMBER(9,2),
  settlementprice   NUMBER(9,2),
  settleperiod      NUMBER(2),
  settleperiodtype  VARCHAR2(4),
  settledate1       VARCHAR2(8),
  settlerate1       VARCHAR2(2),
  settledate2       VARCHAR2(8),
  settlerate2       VARCHAR2(2),
  settledate3       VARCHAR2(8),
  settlerate3       VARCHAR2(2),
  status            NUMBER(1) default 0,
  sync_gy_status1   NUMBER(2) default 0,
  create_time       VARCHAR2(14),
  create_user       NUMBER(9),
  type              NUMBER(1),
  file_path         VARCHAR2(200),
  sync_gy_status2   NUMBER(2) default 0,
  sync_gy_status3   NUMBER(2) default 0
)
;
comment on column T_HISUN_PRODUCTION_SETTLE.merchid
  is '结算商户编号
商户在统一清算平台中的编号';
comment on column T_HISUN_PRODUCTION_SETTLE.store_id
  is '商户编号
在业务平台中的商户编号';
comment on column T_HISUN_PRODUCTION_SETTLE.serviceid
  is '业务编号';
comment on column T_HISUN_PRODUCTION_SETTLE.contractid
  is '合同编号
商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.contracteffdate
  is '合同生效日期
YYYYMMDD，商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.contractexpdate
  is '合同失效日期';
comment on column T_HISUN_PRODUCTION_SETTLE.productionid
  is '商品编号';
comment on column T_HISUN_PRODUCTION_SETTLE.productionname
  is '商品名称';
comment on column T_HISUN_PRODUCTION_SETTLE.productiontype
  is '对应t_sys_fee的id';
comment on column T_HISUN_PRODUCTION_SETTLE.capitaltype3
  is '资金种类3-积分
1-支持 0-no';
comment on column T_HISUN_PRODUCTION_SETTLE.capitaltype2
  is '资金种类2-商城币
1-支持 0-no';
comment on column T_HISUN_PRODUCTION_SETTLE.capitaltype1
  is '资金种类-现金
1-支持 0-no';
comment on column T_HISUN_PRODUCTION_SETTLE.verifyflag
  is '验证标识
Y-验证；N-不验证（默认）';
comment on column T_HISUN_PRODUCTION_SETTLE.verifysettleflag
  is '验证结算
对于需要验证的商品，一种是支付验证都成功后才能做清结算，另一种是支付成功，但是没有验证的就要做清结算，通过此字段来区分，这个字段当veriflag=Y时必输。
S1-支付后结算
S2-验证后结算
';
comment on column T_HISUN_PRODUCTION_SETTLE.productionefftime
  is '产品上线时间YYYYMMDDHHMISS';
comment on column T_HISUN_PRODUCTION_SETTLE.productionexptime
  is '产品下线时间YYYYMMDDHHMISS';
comment on column T_HISUN_PRODUCTION_SETTLE.verifyexpdate
  is '验证截止日期
YYYYMMDD，商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.cityid
  is '地市编码
商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.cityname
  is '地市名称
商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.cityprofitrate
  is '地市分成比例
商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.agentid
  is '代理商编码
商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.agentname
  is '代理商名称
商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.agentprofitrate
  is '代理商分成比例
商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.price
  is '商品单价 以分为单位';
comment on column T_HISUN_PRODUCTION_SETTLE.settlementprice
  is '结算单价 以分为单位';
comment on column T_HISUN_PRODUCTION_SETTLE.settleperiod
  is '结算分期数 商盟必输';
comment on column T_HISUN_PRODUCTION_SETTLE.settleperiodtype
  is '结算分期类型
1P2P：一期支付二期支付
1P2V：一期支付二期验证
1V2V：一期验证二期验证
';
comment on column T_HISUN_PRODUCTION_SETTLE.settledate1
  is '结算时间 YYYYMMDD';
comment on column T_HISUN_PRODUCTION_SETTLE.settlerate1
  is '结算比例';
comment on column T_HISUN_PRODUCTION_SETTLE.settledate2
  is '结算时间2';
comment on column T_HISUN_PRODUCTION_SETTLE.settlerate2
  is '结算比例2';
comment on column T_HISUN_PRODUCTION_SETTLE.settledate3
  is '结算时间3';
comment on column T_HISUN_PRODUCTION_SETTLE.settlerate3
  is '结算比例3';
comment on column T_HISUN_PRODUCTION_SETTLE.status
  is '状态
0-未审核
1-审核通过';
comment on column T_HISUN_PRODUCTION_SETTLE.sync_gy_status1
  is '高阳状态:对应CAPITALTYPE1
0-未同步
1-已同步
2-待审核
3-同步成功
4-审核驳回5-删除';
comment on column T_HISUN_PRODUCTION_SETTLE.create_time
  is '创建时间';
comment on column T_HISUN_PRODUCTION_SETTLE.create_user
  is '创建人';
comment on column T_HISUN_PRODUCTION_SETTLE.type
  is '协议类型 0-商品协议 1-商品资料';
comment on column T_HISUN_PRODUCTION_SETTLE.file_path
  is '协议文件路径';
comment on column T_HISUN_PRODUCTION_SETTLE.sync_gy_status2
  is '高阳状态:对应CAPITALTYPE2
0-未同步
1-已同步
2-待审核
3-同步成功
4-审核驳回5-删除';
comment on column T_HISUN_PRODUCTION_SETTLE.sync_gy_status3
  is '高阳状态:对应CAPITALTYPE3
0-未同步
1-已同步
2-待审核
3-同步成功
4-审核驳回5-删除';
alter table T_HISUN_PRODUCTION_SETTLE
  add constraint PK_T_HISUN_PRODUCTION_SETTLE primary key (ID);

prompt
prompt Creating table T_ITEM_COMMENT
prompt =============================
prompt
create table T_ITEM_COMMENT
(
  id            NUMBER(8) not null,
  content       VARCHAR2(500),
  type          NUMBER(1) default 1,
  question_type NUMBER(1),
  update_time   VARCHAR2(14),
  nickname      VARCHAR2(100),
  user_id       NUMBER(19),
  status        NUMBER(1),
  sale_id       NUMBER(8),
  audit_user    NUMBER(8),
  audit_time    VARCHAR2(14),
  rank          NUMBER(3),
  useful_num    NUMBER(5) default 0,
  useless_num   NUMBER(5) default 0,
  store_id      NUMBER(8),
  act_order_id  NUMBER(19)
)
;
comment on column T_ITEM_COMMENT.content
  is '评论内容';
comment on column T_ITEM_COMMENT.type
  is '评论类别
1-评论
2-咨询
';
comment on column T_ITEM_COMMENT.question_type
  is '咨询类型
0-商品咨询
1-活动咨询';
comment on column T_ITEM_COMMENT.update_time
  is '更新时间';
comment on column T_ITEM_COMMENT.nickname
  is '评论人昵称';
comment on column T_ITEM_COMMENT.user_id
  is '评论人ID';
comment on column T_ITEM_COMMENT.status
  is '0-未审核
1-审核通过
2-审核驳回';
comment on column T_ITEM_COMMENT.sale_id
  is '商品id ';
comment on column T_ITEM_COMMENT.audit_user
  is '审核人';
comment on column T_ITEM_COMMENT.audit_time
  is '审核时间';
comment on column T_ITEM_COMMENT.rank
  is '评分';
comment on column T_ITEM_COMMENT.useful_num
  is '有用
有用';
comment on column T_ITEM_COMMENT.useless_num
  is '无用';
comment on column T_ITEM_COMMENT.store_id
  is '商户id';
comment on column T_ITEM_COMMENT.act_order_id
  is '订单id，用于评论';
alter table T_ITEM_COMMENT
  add constraint PK_T_ITEM_COMMENT primary key (ID);

prompt
prompt Creating table T_ITEM_COMMENT_REPLY
prompt ===================================
prompt
create table T_ITEM_COMMENT_REPLY
(
  id          NUMBER(9) not null,
  content     VARCHAR2(500),
  update_time VARCHAR2(14),
  nickname    VARCHAR2(100),
  user_id     NUMBER(19),
  comment_id  NUMBER(9)
)
;
comment on table T_ITEM_COMMENT_REPLY
  is '商品回复表';
comment on column T_ITEM_COMMENT_REPLY.content
  is '回复内容';
comment on column T_ITEM_COMMENT_REPLY.update_time
  is '更新时间';
comment on column T_ITEM_COMMENT_REPLY.nickname
  is '评论人昵称';
comment on column T_ITEM_COMMENT_REPLY.user_id
  is '评论人ID';
comment on column T_ITEM_COMMENT_REPLY.comment_id
  is '评论id';
alter table T_ITEM_COMMENT_REPLY
  add constraint PK_T_ITEM_COMMENT_REPLY primary key (ID);

prompt
prompt Creating table T_ITEM_COMMENT_TEMP
prompt ==================================
prompt
create table T_ITEM_COMMENT_TEMP
(
  id            NUMBER(8) not null,
  content       VARCHAR2(500),
  type          NUMBER(1) default 1,
  question_type NUMBER(1),
  update_time   VARCHAR2(14),
  nickname      VARCHAR2(100),
  user_id       NUMBER(19),
  status        NUMBER(1),
  sale_id       NUMBER(8),
  audit_user    NUMBER(8),
  audit_time    VARCHAR2(14),
  rank          NUMBER(3),
  useful_num    NUMBER(5) default 0,
  useless_num   NUMBER(5) default 0,
  store_id      NUMBER(8),
  reply_comment VARCHAR2(500),
  reply_time    VARCHAR2(14)
)
;

prompt
prompt Creating table T_ITEM_EXT_PIC_TMP
prompt =================================
prompt
create table T_ITEM_EXT_PIC_TMP
(
  product_id    NUMBER,
  pic_path      VARCHAR2(150),
  switch_status NUMBER
)
;

prompt
prompt Creating table T_ITEM_EXT_PIC_TMP_0909
prompt ======================================
prompt
create table T_ITEM_EXT_PIC_TMP_0909
(
  product_id    NUMBER,
  pic_path      VARCHAR2(150),
  switch_status NUMBER
)
;

prompt
prompt Creating table T_ITEM_GROUP_LINK
prompt ================================
prompt
create table T_ITEM_GROUP_LINK
(
  id           NUMBER(8),
  item_org_id  NUMBER(8),
  item_sale_id NUMBER(8)
)
;

prompt
prompt Creating table T_ITEM_LOGISTICS_FEE
prompt ===================================
prompt
create table T_ITEM_LOGISTICS_FEE
(
  id      NUMBER(8) not null,
  sale_id NUMBER(8),
  qd_id   NUMBER(8),
  fee_num NUMBER(8,2)
)
;
comment on table T_ITEM_LOGISTICS_FEE
  is '物流运费';
alter table T_ITEM_LOGISTICS_FEE
  add constraint PK_T_ITEM_LOGISTICS_FEE primary key (ID);

prompt
prompt Creating table T_ITEM_ORG
prompt =========================
prompt
create table T_ITEM_ORG
(
  id             NUMBER(8) not null,
  item_mode      NUMBER(1),
  type_id        NUMBER(8),
  group_flag     NUMBER(1),
  virtual_flag   NUMBER(1),
  virtual_type   NUMBER(1),
  name           VARCHAR2(100),
  short_name     VARCHAR2(100),
  warm_prompt    VARCHAR2(200),
  remark         CLOB,
  status         NUMBER(1),
  shop_user_id   NUMBER(8),
  shop_class     NUMBER(1),
  shop_id        NUMBER(8),
  update_time    VARCHAR2(14),
  brand          VARCHAR2(20),
  weight         NUMBER(8,2),
  create_time    VARCHAR2(14),
  create_user_id NUMBER(9),
  update_user_id NUMBER(9),
  market_content VARCHAR2(200)
)
;
comment on table T_ITEM_ORG
  is '商品基本属性存储表';
comment on column T_ITEM_ORG.type_id
  is '商品分类';
comment on column T_ITEM_ORG.group_flag
  is '是否是优惠套餐，如果是则是N多商品的组合
0-普通商品
1-优惠套餐';
comment on column T_ITEM_ORG.virtual_flag
  is '是否虚拟商品
0-否
1-是';
comment on column T_ITEM_ORG.virtual_type
  is '虚拟商品类型
1-卡密
2-直充';
comment on column T_ITEM_ORG.name
  is '商品名称';
comment on column T_ITEM_ORG.short_name
  is '商品简称';
comment on column T_ITEM_ORG.warm_prompt
  is '温馨提示';
comment on column T_ITEM_ORG.remark
  is '商品介绍';
comment on column T_ITEM_ORG.status
  is '0--草稿
1--待审核
2--审核中
3--审核通过
4--审核驳回';
comment on column T_ITEM_ORG.shop_class
  is '1--业务门店
2--商户
3--渠道商';
comment on column T_ITEM_ORG.shop_id
  is '商户编号';
comment on column T_ITEM_ORG.update_time
  is '更新时间';
comment on column T_ITEM_ORG.brand
  is '品牌';
comment on column T_ITEM_ORG.weight
  is '重量';
comment on column T_ITEM_ORG.create_time
  is '创建时间';
comment on column T_ITEM_ORG.create_user_id
  is '创建人';
comment on column T_ITEM_ORG.update_user_id
  is '更新人';
comment on column T_ITEM_ORG.market_content
  is '营销语';
alter table T_ITEM_ORG
  add constraint PK_T_ITEM_ORG primary key (ID);

prompt
prompt Creating table T_ITEM_PARAM
prompt ===========================
prompt
create table T_ITEM_PARAM
(
  id          NUMBER(9) not null,
  type_id     NUMBER(9),
  item_id     NUMBER(9),
  param_id    NUMBER(9),
  param_key   VARCHAR2(20),
  param_value VARCHAR2(200),
  rank        NUMBER(8)
)
;
comment on column T_ITEM_PARAM.type_id
  is '分类编号';
comment on column T_ITEM_PARAM.item_id
  is '商品编号';
comment on column T_ITEM_PARAM.param_id
  is '规格参数id (t_sys_type_item_param)';
comment on column T_ITEM_PARAM.param_key
  is '规格参数名';
comment on column T_ITEM_PARAM.param_value
  is '规格参数值';
comment on column T_ITEM_PARAM.rank
  is '排序参数';
create index IDX_ITEM_PARAM_1 on T_ITEM_PARAM (ITEM_ID);
alter table T_ITEM_PARAM
  add constraint PK_ITEM_PARAM primary key (ID);

prompt
prompt Creating table T_ITEM_POST_AREA_LINK
prompt ====================================
prompt
create table T_ITEM_POST_AREA_LINK
(
  id            NUMBER(8) not null,
  sale_id       NUMBER(8),
  province_code VARCHAR2(10),
  city_code     VARCHAR2(10),
  region_code   VARCHAR2(10)
)
;
alter table T_ITEM_POST_AREA_LINK
  add constraint PK_T_ITEM_POST_AREA_LINK primary key (ID);

prompt
prompt Creating table T_ITEM_PRICE
prompt ===========================
prompt
create table T_ITEM_PRICE
(
  id              NUMBER(9) not null,
  province        VARCHAR2(20),
  area_code       VARCHAR2(20),
  store_id        NUMBER(9) not null,
  item_id         NUMBER(9),
  price_type_code VARCHAR2(20) not null,
  price           NUMBER(11,2) not null,
  remark          VARCHAR2(500),
  sale_id         NUMBER(9) not null
)
;
comment on table T_ITEM_PRICE
  is '存放商品的各类价格信息，以纵表形式展示。';
comment on column T_ITEM_PRICE.id
  is '唯一ID';
comment on column T_ITEM_PRICE.province
  is '省份';
comment on column T_ITEM_PRICE.area_code
  is '地市';
comment on column T_ITEM_PRICE.store_id
  is '商户ID';
comment on column T_ITEM_PRICE.item_id
  is '商品ID';
comment on column T_ITEM_PRICE.price_type_code
  is '来源:T_ITEM_PRICE_TYPE';
comment on column T_ITEM_PRICE.price
  is '商品价格';
comment on column T_ITEM_PRICE.remark
  is '备注';
comment on column T_ITEM_PRICE.sale_id
  is '商品id';
create index IDX_SALE_ID on T_ITEM_PRICE (SALE_ID);
alter table T_ITEM_PRICE
  add constraint PK_T_ITEM_PRICE primary key (ID);

prompt
prompt Creating table T_ITEM_PRICE_COIN_PAY_PROPORTI
prompt =============================================
prompt
create table T_ITEM_PRICE_COIN_PAY_PROPORTI
(
  id              NUMBER(9) not null,
  range           VARCHAR2(20) not null,
  item_type_id    NUMBER(9),
  cash_proportion NUMBER(11,2) not null,
  coin_proportion NUMBER(11,2) not null,
  remark          VARCHAR2(500)
)
;
comment on table T_ITEM_PRICE_COIN_PAY_PROPORTI
  is '商城币+现金=100%';
comment on column T_ITEM_PRICE_COIN_PAY_PROPORTI.id
  is '唯一ID';
comment on column T_ITEM_PRICE_COIN_PAY_PROPORTI.range
  is '使用范围（全局/局部）';
comment on column T_ITEM_PRICE_COIN_PAY_PROPORTI.item_type_id
  is '商品分类ID';
comment on column T_ITEM_PRICE_COIN_PAY_PROPORTI.cash_proportion
  is '现金支付比例';
comment on column T_ITEM_PRICE_COIN_PAY_PROPORTI.coin_proportion
  is '商城币支付比例';
comment on column T_ITEM_PRICE_COIN_PAY_PROPORTI.remark
  is '备注';
alter table T_ITEM_PRICE_COIN_PAY_PROPORTI
  add constraint PK_T_ITEM_PRICE_COIN_PAY_PROPO primary key (ID);

prompt
prompt Creating table T_ITEM_PRICE_TYPE
prompt ================================
prompt
create table T_ITEM_PRICE_TYPE
(
  id              NUMBER(9) not null,
  province        VARCHAR2(20) not null,
  area_code       VARCHAR2(20) not null,
  price_type      VARCHAR2(20),
  price_type_code VARCHAR2(20) not null,
  remark          VARCHAR2(500)
)
;
comment on table T_ITEM_PRICE_TYPE
  is '存放商品各类类型编码及名称，发布商品时可以动态的生成页面，录入不同类型的价格。';
comment on column T_ITEM_PRICE_TYPE.id
  is '唯一ID';
comment on column T_ITEM_PRICE_TYPE.province
  is '商品发布销售省份';
comment on column T_ITEM_PRICE_TYPE.area_code
  is '地市';
comment on column T_ITEM_PRICE_TYPE.price_type
  is '价格类型名称,市场价,商城价,红钻价';
comment on column T_ITEM_PRICE_TYPE.price_type_code
  is '价格类型编码,根据订购关系表中的会员类型确定';
comment on column T_ITEM_PRICE_TYPE.remark
  is '备注';
create index IDX_ITEM_PRICE_TYPE_1 on T_ITEM_PRICE_TYPE (PRICE_TYPE_CODE);
alter table T_ITEM_PRICE_TYPE
  add constraint PK_T_ITEM_PRICE_TYPE primary key (ID);

prompt
prompt Creating table T_ITEM_PROPERTY
prompt ==============================
prompt
create table T_ITEM_PROPERTY
(
  id          NUMBER(8),
  item_id     NUMBER(8),
  property_id NUMBER(8),
  content     VARCHAR2(50),
  file_id     NUMBER(8),
  img_path    VARCHAR2(100)
)
;
comment on table T_ITEM_PROPERTY
  is '商品属性，例如颜色、尺寸等，涉及到组合和价格相关';
comment on column T_ITEM_PROPERTY.item_id
  is '对应t_item_org表中ID字段';
comment on column T_ITEM_PROPERTY.property_id
  is '关联t_sys_property表id';
comment on column T_ITEM_PROPERTY.file_id
  is '对应t_sys_file表中ID';
comment on column T_ITEM_PROPERTY.img_path
  is '图片路径';

prompt
prompt Creating table T_ITEM_SALE
prompt ==========================
prompt
create table T_ITEM_SALE
(
  id                NUMBER(8) not null,
  org_id            NUMBER(8),
  sale_price_id     NUMBER(8),
  verify_code_type  NUMBER(1),
  send_code_mode    NUMBER(1),
  send_code_channel NUMBER(1),
  send_code_src     NUMBER(2),
  post_flag         NUMBER(1),
  sale_start_time   VARCHAR2(14),
  sale_stop_time    VARCHAR2(14),
  verify_start_time VARCHAR2(14),
  verify_stop_time  VARCHAR2(14),
  stock_num         NUMBER(19),
  user_per_buy_num  NUMBER(8),
  status            NUMBER(1),
  is_valid          NUMBER(1),
  sync_gy_flag      NUMBER(1),
  shop_class        NUMBER(1),
  store_id          NUMBER(8),
  market_price      NUMBER(9,2),
  item_mode         NUMBER(1),
  type_id           NUMBER(8),
  group_flag        NUMBER(1),
  virtual_flag      NUMBER(1),
  virtual_type      NUMBER(1),
  name              VARCHAR2(200),
  short_name        VARCHAR2(200),
  warm_prompt       VARCHAR2(200),
  remark            CLOB,
  shop_user_id      NUMBER(8),
  update_time       VARCHAR2(14),
  brand             VARCHAR2(20),
  weight            NUMBER(8,2),
  create_time       VARCHAR2(14),
  create_user_id    NUMBER(9),
  update_user_id    NUMBER(9),
  market_content    VARCHAR2(200),
  shop_price        NUMBER(9,2),
  img_path          VARCHAR2(200),
  fee_type          NUMBER(9),
  settle_price      NUMBER(9,2),
  verify_day        NUMBER(5),
  is_view           NUMBER(1) default 1,
  iseckill          NUMBER(2) default 0,
  cash_idgoods      NUMBER(1),
  coin_idgoods      NUMBER(1),
  score_idgoods     NUMBER(1),
  grounding_time    VARCHAR2(14),
  audit_time        VARCHAR2(14),
  iseckill_price    NUMBER(9,2)
)
;
comment on table T_ITEM_SALE
  is '基于商品基本属性所发布销售的商品表';
comment on column T_ITEM_SALE.org_id
  is '和t_item_org匹配';
comment on column T_ITEM_SALE.verify_code_type
  is '1-维码 2-二维码';
comment on column T_ITEM_SALE.send_code_mode
  is '0-不发码 1-按照订单发码 2-按照商品个数发码';
comment on column T_ITEM_SALE.send_code_channel
  is '0-平台自己 1-方正码平台 2-第三方应用';
comment on column T_ITEM_SALE.send_code_src
  is '如果制码方选择第三方应用，则该字段有用 10-85度C 11-鲜芋仙 ...';
comment on column T_ITEM_SALE.post_flag
  is '0-不需要物流配送 1-需要物流配送';
comment on column T_ITEM_SALE.sale_start_time
  is '销售有效开始时间';
comment on column T_ITEM_SALE.sale_stop_time
  is '销售有效结束时间';
comment on column T_ITEM_SALE.verify_start_time
  is '验证有效开始时间';
comment on column T_ITEM_SALE.verify_stop_time
  is '验证有效结束时间';
comment on column T_ITEM_SALE.stock_num
  is '商品库存数量';
comment on column T_ITEM_SALE.user_per_buy_num
  is '0不限制  单个用户购买数量';
comment on column T_ITEM_SALE.status
  is '-1已删除0-草稿 1-待审核 2-审核中 3-审核通过 4-审核驳回';
comment on column T_ITEM_SALE.is_valid
  is '0-下架 1-上架';
comment on column T_ITEM_SALE.sync_gy_flag
  is '0-未同步 1-已同步 2-待审核 3-审核通过4-审核驳回5-删除';
comment on column T_ITEM_SALE.shop_class
  is '"1--业务门店2--商户3--渠道商"';
comment on column T_ITEM_SALE.store_id
  is '匹配表t_store 商户id';
comment on column T_ITEM_SALE.market_price
  is '市场价';
comment on column T_ITEM_SALE.item_mode
  is '商品类型0--实物 1--虚拟物';
comment on column T_ITEM_SALE.type_id
  is '商品分类';
comment on column T_ITEM_SALE.group_flag
  is '"是否是优惠套餐，如果是则是N多商品的组合 0-普通商品 1-优惠套餐"';
comment on column T_ITEM_SALE.virtual_flag
  is '"是否虚拟商品 0-否 1-是"    (暂无用处)';
comment on column T_ITEM_SALE.virtual_type
  is '"虚拟商品类型 1-卡密 2-直充"';
comment on column T_ITEM_SALE.name
  is '商品名称';
comment on column T_ITEM_SALE.short_name
  is '商品简称';
comment on column T_ITEM_SALE.warm_prompt
  is '温馨提示';
comment on column T_ITEM_SALE.remark
  is '商品介绍';
comment on column T_ITEM_SALE.shop_user_id
  is '商户用户id';
comment on column T_ITEM_SALE.update_time
  is '更新时间';
comment on column T_ITEM_SALE.brand
  is '品牌';
comment on column T_ITEM_SALE.weight
  is '重量';
comment on column T_ITEM_SALE.create_time
  is '创建时间';
comment on column T_ITEM_SALE.create_user_id
  is '创建人';
comment on column T_ITEM_SALE.update_user_id
  is '更新人';
comment on column T_ITEM_SALE.market_content
  is '营销语';
comment on column T_ITEM_SALE.shop_price
  is '商城价';
comment on column T_ITEM_SALE.img_path
  is '封面图路径';
comment on column T_ITEM_SALE.fee_type
  is '对应t_sys_fee表id';
comment on column T_ITEM_SALE.settle_price
  is '结算价';
comment on column T_ITEM_SALE.verify_day
  is '验证天数';
comment on column T_ITEM_SALE.is_view
  is '商品是否显示 0，不显示；1显示';
comment on column T_ITEM_SALE.iseckill
  is '是否秒杀： 0不秒杀 ，1秒杀。';
comment on column T_ITEM_SALE.cash_idgoods
  is '商品支付方式为现金 ，0不支持， 1支持。';
comment on column T_ITEM_SALE.coin_idgoods
  is '商品支付方式为商城币 ，0不支持， 1支持。';
comment on column T_ITEM_SALE.score_idgoods
  is '商品支付方式为积分，0不支持， 1支持。';
comment on column T_ITEM_SALE.grounding_time
  is '上架时间';
comment on column T_ITEM_SALE.audit_time
  is '审核时间';
comment on column T_ITEM_SALE.iseckill_price
  is '秒杀价格';
create index IDX_ITEM_SALE_TYPE on T_ITEM_SALE (TYPE_ID);
create index IDX_STORE_ID on T_ITEM_SALE (STORE_ID);
alter table T_ITEM_SALE
  add constraint PK_T_ITEM_SALE primary key (ID);

prompt
prompt Creating table T_ITEM_SALE_0909
prompt ===============================
prompt
create table T_ITEM_SALE_0909
(
  id                NUMBER(8) not null,
  org_id            NUMBER(8),
  sale_price_id     NUMBER(8),
  verify_code_type  NUMBER(1),
  send_code_mode    NUMBER(1),
  send_code_channel NUMBER(1),
  send_code_src     NUMBER(2),
  post_flag         NUMBER(1),
  sale_start_time   VARCHAR2(14),
  sale_stop_time    VARCHAR2(14),
  verify_start_time VARCHAR2(14),
  verify_stop_time  VARCHAR2(14),
  stock_num         NUMBER(19),
  user_per_buy_num  NUMBER(8),
  status            NUMBER(1),
  is_valid          NUMBER(1),
  sync_gy_flag      NUMBER(1),
  shop_class        NUMBER(1),
  store_id          NUMBER(8),
  market_price      NUMBER(9,2),
  item_mode         NUMBER(1),
  type_id           NUMBER(8),
  group_flag        NUMBER(1),
  virtual_flag      NUMBER(1),
  virtual_type      NUMBER(1),
  name              VARCHAR2(200),
  short_name        VARCHAR2(200),
  warm_prompt       VARCHAR2(200),
  remark            CLOB,
  shop_user_id      NUMBER(8),
  update_time       VARCHAR2(14),
  brand             VARCHAR2(20),
  weight            NUMBER(8,2),
  create_time       VARCHAR2(14),
  create_user_id    NUMBER(9),
  update_user_id    NUMBER(9),
  market_content    VARCHAR2(200),
  shop_price        NUMBER(9,2),
  img_path          VARCHAR2(200),
  fee_type          NUMBER(9),
  settle_price      NUMBER(9,2),
  verify_day        NUMBER(5),
  is_view           NUMBER(1) default 1,
  iseckill          NUMBER(2) default 0,
  cash_idgoods      NUMBER(1),
  coin_idgoods      NUMBER(1),
  score_idgoods     NUMBER(1),
  grounding_time    VARCHAR2(14),
  audit_time        VARCHAR2(14),
  iseckill_price    NUMBER(9,2)
)
;

prompt
prompt Creating table T_ITEM_SALE_0909_FULL
prompt ====================================
prompt
create table T_ITEM_SALE_0909_FULL
(
  id                NUMBER(8) not null,
  org_id            NUMBER(8),
  sale_price_id     NUMBER(8),
  verify_code_type  NUMBER(1),
  send_code_mode    NUMBER(1),
  send_code_channel NUMBER(1),
  send_code_src     NUMBER(2),
  post_flag         NUMBER(1),
  sale_start_time   VARCHAR2(14),
  sale_stop_time    VARCHAR2(14),
  verify_start_time VARCHAR2(14),
  verify_stop_time  VARCHAR2(14),
  stock_num         NUMBER(19),
  user_per_buy_num  NUMBER(8),
  status            NUMBER(1),
  is_valid          NUMBER(1),
  sync_gy_flag      NUMBER(1),
  shop_class        NUMBER(1),
  store_id          NUMBER(8),
  market_price      NUMBER(9,2),
  item_mode         NUMBER(1),
  type_id           NUMBER(8),
  group_flag        NUMBER(1),
  virtual_flag      NUMBER(1),
  virtual_type      NUMBER(1),
  name              VARCHAR2(200),
  short_name        VARCHAR2(200),
  warm_prompt       VARCHAR2(200),
  remark            CLOB,
  shop_user_id      NUMBER(8),
  update_time       VARCHAR2(14),
  brand             VARCHAR2(20),
  weight            NUMBER(8,2),
  create_time       VARCHAR2(14),
  create_user_id    NUMBER(9),
  update_user_id    NUMBER(9),
  market_content    VARCHAR2(200),
  shop_price        NUMBER(9,2),
  img_path          VARCHAR2(200),
  fee_type          NUMBER(9),
  settle_price      NUMBER(9,2),
  verify_day        NUMBER(5),
  is_view           NUMBER(1),
  iseckill          NUMBER(2),
  cash_idgoods      NUMBER(1),
  coin_idgoods      NUMBER(1),
  score_idgoods     NUMBER(1),
  grounding_time    VARCHAR2(14),
  audit_time        VARCHAR2(14),
  iseckill_price    NUMBER(9,2)
)
;

prompt
prompt Creating table T_ITEM_SALE_1
prompt ============================
prompt
create table T_ITEM_SALE_1
(
  id                NUMBER(8) not null,
  org_id            NUMBER(8),
  sale_price_id     NUMBER(8),
  verify_code_type  NUMBER(1),
  send_code_mode    NUMBER(1),
  send_code_channel NUMBER(1),
  send_code_src     NUMBER(2),
  post_flag         NUMBER(1),
  sale_start_time   VARCHAR2(14),
  sale_stop_time    VARCHAR2(14),
  verify_start_time VARCHAR2(14),
  verify_stop_time  VARCHAR2(14),
  stock_num         NUMBER(8),
  user_per_buy_num  NUMBER(8),
  status            NUMBER(1),
  is_valid          NUMBER(1),
  sync_gy_flag      NUMBER(1),
  shop_class        NUMBER(1),
  store_id          NUMBER(8),
  market_price      NUMBER(9,2),
  item_mode         NUMBER(1),
  type_id           NUMBER(8),
  group_flag        NUMBER(1),
  virtual_flag      NUMBER(1),
  virtual_type      NUMBER(1),
  name              VARCHAR2(200),
  short_name        VARCHAR2(100),
  warm_prompt       VARCHAR2(200),
  remark            CLOB,
  shop_user_id      NUMBER(8),
  update_time       VARCHAR2(14),
  brand             VARCHAR2(20),
  weight            NUMBER(8,2),
  create_time       VARCHAR2(14),
  create_user_id    NUMBER(9),
  update_user_id    NUMBER(9),
  market_content    VARCHAR2(200),
  shop_price        NUMBER(9,2),
  img_path          VARCHAR2(200)
)
;

prompt
prompt Creating table T_ITEM_SALE_ADD_0910
prompt ===================================
prompt
create table T_ITEM_SALE_ADD_0910
(
  id                NUMBER(8) not null,
  org_id            NUMBER(8),
  sale_price_id     NUMBER(8),
  verify_code_type  NUMBER(1),
  send_code_mode    NUMBER(1),
  send_code_channel NUMBER(1),
  send_code_src     NUMBER(2),
  post_flag         NUMBER(1),
  sale_start_time   VARCHAR2(14),
  sale_stop_time    VARCHAR2(14),
  verify_start_time VARCHAR2(14),
  verify_stop_time  VARCHAR2(14),
  stock_num         NUMBER(19),
  user_per_buy_num  NUMBER(8),
  status            NUMBER(1),
  is_valid          NUMBER(1),
  sync_gy_flag      NUMBER(1),
  shop_class        NUMBER(1),
  store_id          NUMBER(8),
  market_price      NUMBER(9,2),
  item_mode         NUMBER(1),
  type_id           NUMBER(8),
  group_flag        NUMBER(1),
  virtual_flag      NUMBER(1),
  virtual_type      NUMBER(1),
  name              VARCHAR2(200),
  short_name        VARCHAR2(200),
  warm_prompt       VARCHAR2(200),
  remark            CLOB,
  shop_user_id      NUMBER(8),
  update_time       VARCHAR2(14),
  brand             VARCHAR2(20),
  weight            NUMBER(8,2),
  create_time       VARCHAR2(14),
  create_user_id    NUMBER(9),
  update_user_id    NUMBER(9),
  market_content    VARCHAR2(200),
  shop_price        NUMBER(9,2),
  img_path          VARCHAR2(200),
  fee_type          NUMBER(9),
  settle_price      NUMBER(9,2),
  verify_day        NUMBER(5),
  is_view           NUMBER(1),
  iseckill          NUMBER(2),
  cash_idgoods      NUMBER(1),
  coin_idgoods      NUMBER(1),
  score_idgoods     NUMBER(1),
  grounding_time    VARCHAR2(14),
  audit_time        VARCHAR2(14),
  iseckill_price    NUMBER(9,2)
)
;

prompt
prompt Creating table T_ITEM_SALE_AREA_LINK
prompt ====================================
prompt
create table T_ITEM_SALE_AREA_LINK
(
  id            NUMBER(8) not null,
  sale_id       NUMBER(8),
  province_code VARCHAR2(10),
  city_code     VARCHAR2(10),
  region_code   VARCHAR2(10)
)
;
create index IDX_ITEM_SALE_AREA_LINK_1 on T_ITEM_SALE_AREA_LINK (SALE_ID);
alter table T_ITEM_SALE_AREA_LINK
  add constraint PK_T_ITEM_SALE_AREA_LINK primary key (ID);

prompt
prompt Creating table T_ITEM_SALE_EXT
prompt ==============================
prompt
create table T_ITEM_SALE_EXT
(
  id                 NUMBER(9) not null,
  sale_id            NUMBER(8),
  sale_num           NUMBER(8) default 0,
  click_num          NUMBER(8) default 0,
  comment_num        NUMBER(8) default 0,
  user_num           NUMBER(8) default 0,
  collect_num        NUMBER(8) default 0,
  rank               NUMBER(9,2) default 0,
  logistics_fee      NUMBER(9,2) default 0,
  logistics_fee_type NUMBER(1) default 0
)
;
comment on table T_ITEM_SALE_EXT
  is '基于发布商品表的附加其它属性表';
comment on column T_ITEM_SALE_EXT.sale_id
  is '和t_item_sale匹配';
comment on column T_ITEM_SALE_EXT.sale_num
  is '销售数量';
comment on column T_ITEM_SALE_EXT.click_num
  is '人气数';
comment on column T_ITEM_SALE_EXT.comment_num
  is '评论量';
comment on column T_ITEM_SALE_EXT.user_num
  is '购买人数';
comment on column T_ITEM_SALE_EXT.collect_num
  is '收藏数量';
comment on column T_ITEM_SALE_EXT.rank
  is '商品评分';
comment on column T_ITEM_SALE_EXT.logistics_fee
  is '物流运费';
comment on column T_ITEM_SALE_EXT.logistics_fee_type
  is '物流计算方式 0-不累计
1-按数量';
create index IDX_ITEM_SALE_EXT_1 on T_ITEM_SALE_EXT (SALE_ID);
alter table T_ITEM_SALE_EXT
  add constraint PK_T_ITEM_SALE_EXT primary key (ID);

prompt
prompt Creating table T_ITEM_SALE_EXT_0910
prompt ===================================
prompt
create table T_ITEM_SALE_EXT_0910
(
  id                 NUMBER(9) not null,
  sale_id            NUMBER(8),
  sale_num           NUMBER(8),
  click_num          NUMBER(8),
  comment_num        NUMBER(8),
  user_num           NUMBER(8),
  collect_num        NUMBER(8),
  rank               NUMBER(9,2),
  logistics_fee      NUMBER(9,2),
  logistics_fee_type NUMBER(1)
)
;

prompt
prompt Creating table T_ITEM_SALE_SHOP_LINK
prompt ====================================
prompt
create table T_ITEM_SALE_SHOP_LINK
(
  id       NUMBER(8) not null,
  sale_id  NUMBER(8),
  store_id NUMBER(8),
  shop_id  NUMBER(8)
)
;
comment on column T_ITEM_SALE_SHOP_LINK.shop_id
  is '如果是全商户门店都可以使用，则只存商户ID，该业务门店ID可以允许为空';
alter table T_ITEM_SALE_SHOP_LINK
  add constraint PK_T_ITEM_SALE_SHOP_LINK primary key (ID);

prompt
prompt Creating table T_ITEM_SALE_TMP
prompt ==============================
prompt
create table T_ITEM_SALE_TMP
(
  id                 NUMBER(8) not null,
  org_id             NUMBER(8),
  sale_price_id      NUMBER(8),
  verify_code_type   NUMBER(1),
  send_code_mode     NUMBER(1),
  send_code_channel  NUMBER(1),
  send_code_src      NUMBER(2),
  post_flag          NUMBER(1),
  sale_start_time    VARCHAR2(14),
  sale_stop_time     VARCHAR2(14),
  verify_start_time  VARCHAR2(14),
  verify_stop_time   VARCHAR2(14),
  stock_num          NUMBER(19),
  user_per_buy_num   NUMBER(8),
  status             NUMBER(1),
  is_valid           NUMBER(1),
  sync_gy_flag       NUMBER(1),
  shop_class         NUMBER(1),
  store_id           NUMBER(8),
  market_price       NUMBER(9,2),
  item_mode          NUMBER(1),
  type_id            NUMBER(8),
  group_flag         NUMBER(1),
  virtual_flag       NUMBER(1),
  virtual_type       NUMBER(1),
  name               VARCHAR2(200),
  short_name         VARCHAR2(100),
  warm_prompt        VARCHAR2(200),
  remark             CLOB,
  shop_user_id       NUMBER(8),
  update_time        VARCHAR2(14),
  brand              VARCHAR2(20),
  weight             NUMBER(8,2),
  create_time        VARCHAR2(14),
  create_user_id     NUMBER(9),
  update_user_id     NUMBER(9),
  market_content     VARCHAR2(200),
  shop_price         NUMBER(9,2),
  img_path           VARCHAR2(200),
  fee_type           NUMBER(9),
  settle_price       NUMBER(9,2),
  verify_day         NUMBER(5),
  xw_main_category   VARCHAR2(40),
  xw_settlement_type VARCHAR2(10)
)
;

prompt
prompt Creating table T_ITEM_SALE_USER_AREA_LINK
prompt =========================================
prompt
create table T_ITEM_SALE_USER_AREA_LINK
(
  id            NUMBER(8) not null,
  sale_id       NUMBER(8),
  province_code VARCHAR2(10),
  city_code     VARCHAR2(10),
  region_code   VARCHAR2(10)
)
;
create index IDX_SALE_USER_AREA_LINK_1 on T_ITEM_SALE_USER_AREA_LINK (SALE_ID);
create index IDX_SALE_USER_AREA_LINK_2 on T_ITEM_SALE_USER_AREA_LINK (SALE_ID, REGION_CODE);
alter table T_ITEM_SALE_USER_AREA_LINK
  add constraint PK_T_ITEM_SALE_USER_AREA_LINK primary key (ID);

prompt
prompt Creating table T_ITEM_SALE_USER_LEVEL_LINK
prompt ==========================================
prompt
create table T_ITEM_SALE_USER_LEVEL_LINK
(
  id         NUMBER(8) not null,
  sale_id    NUMBER(8),
  user_level VARCHAR2(4)
)
;
create index IDX_SALE_USER_LEVEL_LINK_1 on T_ITEM_SALE_USER_LEVEL_LINK (SALE_ID);
alter table T_ITEM_SALE_USER_LEVEL_LINK
  add constraint PK_T_ITEM_SALE_USER_LEVEL_LINK primary key (ID);

prompt
prompt Creating table T_ITEM_TAG
prompt =========================
prompt
create table T_ITEM_TAG
(
  id      NUMBER(8) not null,
  item_id NUMBER(8),
  tag     VARCHAR2(20)
)
;
comment on table T_ITEM_TAG
  is '存放商品打签标识，纯文字';
create index IDX_ITEM_TAG_1 on T_ITEM_TAG (ITEM_ID);
alter table T_ITEM_TAG
  add constraint PK_T_ITEM_TAG primary key (ID);

prompt
prompt Creating table T_ITEM_VERIFY_SHOP_LINK
prompt ======================================
prompt
create table T_ITEM_VERIFY_SHOP_LINK
(
  id       NUMBER(8) not null,
  sale_id  NUMBER(8),
  store_id NUMBER(8),
  shop_id  NUMBER(8)
)
;
comment on column T_ITEM_VERIFY_SHOP_LINK.shop_id
  is '如果是全商户门店都可以验证，则只存商户ID，该业务门店ID可以允许为空';
alter table T_ITEM_VERIFY_SHOP_LINK
  add constraint PK_T_ITEM_VERIFY_SHOP_LINK primary key (ID);

prompt
prompt Creating table T_LIMIT_PURCH_TMP
prompt ================================
prompt
create table T_LIMIT_PURCH_TMP
(
  product_id   NUMBER,
  limite_count NUMBER
)
;

prompt
prompt Creating table T_LOTTERY_ACTIVE
prompt ===============================
prompt
create table T_LOTTERY_ACTIVE
(
  id               NUMBER(8) not null,
  name             VARCHAR2(100),
  start_time       VARCHAR2(14),
  stop_time        VARCHAR2(14),
  active_type      NUMBER(1),
  active_desc      VARCHAR2(200),
  unhit            NUMBER(1),
  unhit_msg        VARCHAR2(50),
  create_time      VARCHAR2(14),
  create_member_id NUMBER(8),
  status           NUMBER(1),
  hit_limit        NUMBER(2) default -1
)
;
comment on table T_LOTTERY_ACTIVE
  is '抽奖活动表';
comment on column T_LOTTERY_ACTIVE.name
  is '活动名称';
comment on column T_LOTTERY_ACTIVE.start_time
  is '活动开始时间';
comment on column T_LOTTERY_ACTIVE.stop_time
  is '活动结束时间';
comment on column T_LOTTERY_ACTIVE.active_type
  is '活动类型';
comment on column T_LOTTERY_ACTIVE.active_desc
  is '活动介绍';
comment on column T_LOTTERY_ACTIVE.unhit
  is '未中奖设置(预留)';
comment on column T_LOTTERY_ACTIVE.unhit_msg
  is '未中奖提示语';
comment on column T_LOTTERY_ACTIVE.create_time
  is '创建时间';
comment on column T_LOTTERY_ACTIVE.create_member_id
  is '创建人ID';
comment on column T_LOTTERY_ACTIVE.status
  is '活动状态:0草稿，1审核中，2审核通过，3驳回';
comment on column T_LOTTERY_ACTIVE.hit_limit
  is '中奖次数限制，-1表示不限制次数';
alter table T_LOTTERY_ACTIVE
  add constraint PK_T_LOTTERY_ACTIVE primary key (ID);

prompt
prompt Creating table T_LOTTERY_ACTIVE_CONF
prompt ====================================
prompt
create table T_LOTTERY_ACTIVE_CONF
(
  id        NUMBER(8) not null,
  active_id NUMBER(8),
  key       VARCHAR2(400),
  value     VARCHAR2(400)
)
;
comment on table T_LOTTERY_ACTIVE_CONF
  is '活动配置表';
comment on column T_LOTTERY_ACTIVE_CONF.active_id
  is '所属活动ID';
comment on column T_LOTTERY_ACTIVE_CONF.key
  is '配置项：key-value方式存放。';
comment on column T_LOTTERY_ACTIVE_CONF.value
  is '配置项：key-value方式存放。';
alter table T_LOTTERY_ACTIVE_CONF
  add constraint PK_T_LOTTERY_ACTIVE_CONF primary key (ID);

prompt
prompt Creating table T_LOTTERY_LOG
prompt ============================
prompt
create table T_LOTTERY_LOG
(
  id        NUMBER(8) not null,
  active_id NUMBER(8),
  target_id VARCHAR2(21),
  content   VARCHAR2(200),
  hit_time  VARCHAR2(14),
  prize_id  NUMBER(8)
)
;
comment on table T_LOTTERY_LOG
  is '抽奖日志表';
comment on column T_LOTTERY_LOG.active_id
  is '活动ID';
comment on column T_LOTTERY_LOG.target_id
  is '中奖手机号';
comment on column T_LOTTERY_LOG.content
  is '中奖内容';
comment on column T_LOTTERY_LOG.hit_time
  is '中奖时间';
comment on column T_LOTTERY_LOG.prize_id
  is '奖品ID';
alter table T_LOTTERY_LOG
  add constraint PK_T_LOTTERY_LOG primary key (ID);

prompt
prompt Creating table T_LOTTERY_PRIZE
prompt ==============================
prompt
create table T_LOTTERY_PRIZE
(
  id              NUMBER(8) not null,
  active_id       NUMBER(8),
  hit_level       NUMBER(2),
  hit_probability VARCHAR2(5),
  hit_limit       NUMBER(2),
  hit_msg         VARCHAR2(100),
  numbers         NUMBER(6),
  name            VARCHAR2(200),
  description     VARCHAR2(200),
  position        VARCHAR2(20),
  used            NUMBER(8)
)
;
comment on table T_LOTTERY_PRIZE
  is '奖品表';
comment on column T_LOTTERY_PRIZE.active_id
  is '所属活动ID';
comment on column T_LOTTERY_PRIZE.hit_level
  is '奖区编号（奖品等级）';
comment on column T_LOTTERY_PRIZE.hit_probability
  is '中奖概率15,35,55)';
comment on column T_LOTTERY_PRIZE.hit_limit
  is '每人中奖限制次数';
comment on column T_LOTTERY_PRIZE.hit_msg
  is '中奖提示语';
comment on column T_LOTTERY_PRIZE.numbers
  is '奖品数量';
comment on column T_LOTTERY_PRIZE.name
  is '奖品名称';
comment on column T_LOTTERY_PRIZE.description
  is '备注';
comment on column T_LOTTERY_PRIZE.position
  is '中奖位置';
comment on column T_LOTTERY_PRIZE.used
  is '使用次数';
alter table T_LOTTERY_PRIZE
  add constraint PK_T_LOTTERY_PRIZE primary key (ID);

prompt
prompt Creating table T_LOTTERY_TARGET
prompt ===============================
prompt
create table T_LOTTERY_TARGET
(
  id        NUMBER(8) not null,
  active_id NUMBER(8),
  phone     VARCHAR2(13),
  num_had   NUMBER(2),
  num_could NUMBER(2)
)
;
comment on table T_LOTTERY_TARGET
  is '抽奖目标库';
comment on column T_LOTTERY_TARGET.active_id
  is '所属活动ID';
comment on column T_LOTTERY_TARGET.phone
  is '手机号码';
comment on column T_LOTTERY_TARGET.num_had
  is '本活动中已中奖次数';
comment on column T_LOTTERY_TARGET.num_could
  is '本活动可中奖次数';
alter table T_LOTTERY_TARGET
  add constraint PK_T_LOTTERY_TARGET primary key (ID);

prompt
prompt Creating table T_MARKET_AUCTION
prompt ===============================
prompt
create table T_MARKET_AUCTION
(
  goods_no      NUMBER(20) not null,
  floor_no      VARCHAR2(5) not null,
  operator_id   NUMBER(12) not null,
  auction_price NUMBER(12,2) not null,
  auction_time  TIMESTAMP(6) not null,
  goods_name    VARCHAR2(50),
  id            NUMBER(9) default 0 not null
)
;
comment on table T_MARKET_AUCTION
  is '竞拍出价记录';
comment on column T_MARKET_AUCTION.goods_no
  is '竞拍商品编号';
comment on column T_MARKET_AUCTION.floor_no
  is '加价楼层';
comment on column T_MARKET_AUCTION.operator_id
  is '出价人编号';
comment on column T_MARKET_AUCTION.auction_price
  is '出价金额';
comment on column T_MARKET_AUCTION.auction_time
  is '出价时间';
comment on column T_MARKET_AUCTION.goods_name
  is '商品名';
comment on column T_MARKET_AUCTION.id
  is '编号';
alter table T_MARKET_AUCTION
  add constraint PK_ID primary key (ID);

prompt
prompt Creating table T_MARKET_BLACKLIST
prompt =================================
prompt
create table T_MARKET_BLACKLIST
(
  id          NUMBER(8) not null,
  user_id     VARCHAR2(8),
  mobile      VARCHAR2(13),
  mistaketime DATE not null,
  reason      VARCHAR2(100),
  status      VARCHAR2(1),
  type        VARCHAR2(10) not null
)
;
comment on table T_MARKET_BLACKLIST
  is '黑名单';
comment on column T_MARKET_BLACKLIST.id
  is '编号';
comment on column T_MARKET_BLACKLIST.user_id
  is '用户ID';
comment on column T_MARKET_BLACKLIST.mobile
  is '手机';
comment on column T_MARKET_BLACKLIST.mistaketime
  is '违规时间';
comment on column T_MARKET_BLACKLIST.reason
  is '违规原因';
comment on column T_MARKET_BLACKLIST.status
  is '状态';
comment on column T_MARKET_BLACKLIST.type
  is '违规的类型 1为竞拍，2为秒杀';
alter table T_MARKET_BLACKLIST
  add primary key (ID);

prompt
prompt Creating table T_MARKET_CHANNEL
prompt ===============================
prompt
create table T_MARKET_CHANNEL
(
  id         NUMBER(20) not null,
  goods_no   NUMBER(20) not null,
  goods_name NVARCHAR2(50) not null,
  position   NVARCHAR2(2) not null
)
;
comment on table T_MARKET_CHANNEL
  is '秒杀频道表';
comment on column T_MARKET_CHANNEL.id
  is '编号';
comment on column T_MARKET_CHANNEL.goods_no
  is '秒杀商品编号';
comment on column T_MARKET_CHANNEL.goods_name
  is '秒杀商品名称';
comment on column T_MARKET_CHANNEL.position
  is '秒杀频道位置';
alter table T_MARKET_CHANNEL
  add constraint PK_T_MARKET_CHANNEL primary key (ID);

prompt
prompt Creating table T_MARKET_CONTENT
prompt ===============================
prompt
create table T_MARKET_CONTENT
(
  id               VARCHAR2(2) not null,
  title            VARCHAR2(20),
  last_modify_time VARCHAR2(20),
  content          CLOB
)
;
comment on table T_MARKET_CONTENT
  is '竞拍文案表';
comment on column T_MARKET_CONTENT.id
  is '编号';
comment on column T_MARKET_CONTENT.title
  is '文案标题';
comment on column T_MARKET_CONTENT.last_modify_time
  is '最后更新时间';
comment on column T_MARKET_CONTENT.content
  is '文案内容';
alter table T_MARKET_CONTENT
  add primary key (ID);

prompt
prompt Creating table T_MARKET_GOODS
prompt =============================
prompt
create table T_MARKET_GOODS
(
  goods_no            NUMBER(20) not null,
  goods_name          VARCHAR2(50) not null,
  goods_photo_path    VARCHAR2(250) not null,
  marche_price        NUMBER(12,2) not null,
  time_range          VARCHAR2(15) not null,
  product_id          VARCHAR2(20),
  date_range          DATE,
  price_range         NUMBER(12,2),
  margin_flag         VARCHAR2(2) default '1',
  vip_margin_flag     VARCHAR2(2) default '1',
  status              VARCHAR2(2) default 1,
  auction_type        VARCHAR2(2) default 1,
  min_auction_time    VARCHAR2(3) default 0,
  delay_time          VARCHAR2(3) default 0,
  end_time            VARCHAR2(8) default 0,
  account_for_auction CLOB,
  goods_description   CLOB,
  shop_goods_id       NUMBER(8),
  goods_small_pic_a   VARCHAR2(250),
  goods_small_pic_b   VARCHAR2(250),
  goods_small_pic_c   VARCHAR2(250),
  start_price         NUMBER(12,2) default 0,
  check_status        VARCHAR2(2) default 0,
  check_content       VARCHAR2(600),
  check_man           VARCHAR2(20),
  check_time          VARCHAR2(20)
)
;
comment on table T_MARKET_GOODS
  is '竞拍商品表';
comment on column T_MARKET_GOODS.goods_no
  is '商品编号';
comment on column T_MARKET_GOODS.goods_name
  is '商品名';
comment on column T_MARKET_GOODS.goods_photo_path
  is '主图片路径';
comment on column T_MARKET_GOODS.marche_price
  is '市场价';
comment on column T_MARKET_GOODS.time_range
  is '竞拍时间';
comment on column T_MARKET_GOODS.product_id
  is '保证金编号';
comment on column T_MARKET_GOODS.date_range
  is '竞拍日期';
comment on column T_MARKET_GOODS.price_range
  is '加价幅度';
comment on column T_MARKET_GOODS.margin_flag
  is '普通会员是否需要购买保证金，0：需要，1：不需要';
comment on column T_MARKET_GOODS.vip_margin_flag
  is 'VIP会员是否需要购买保证金，0：需要，1：不需要';
comment on column T_MARKET_GOODS.status
  is '有效状态，0是上架，1是下架';
comment on column T_MARKET_GOODS.auction_type
  is '1：限时抢拍，2：延时竞拍';
comment on column T_MARKET_GOODS.min_auction_time
  is '延时竞拍活动最短时长（分钟）';
comment on column T_MARKET_GOODS.delay_time
  is '延时时长（分钟）';
comment on column T_MARKET_GOODS.end_time
  is '活动结束时间';
comment on column T_MARKET_GOODS.account_for_auction
  is '描述';
comment on column T_MARKET_GOODS.goods_description
  is '商品描述';
comment on column T_MARKET_GOODS.shop_goods_id
  is '商品链接';
comment on column T_MARKET_GOODS.goods_small_pic_a
  is '轮换图片路径';
comment on column T_MARKET_GOODS.goods_small_pic_b
  is '轮换图片路径';
comment on column T_MARKET_GOODS.goods_small_pic_c
  is '轮换图片路径';
comment on column T_MARKET_GOODS.start_price
  is '起拍价';
comment on column T_MARKET_GOODS.check_status
  is '0-未审核，1-待审核，2-审核通过， 3-审核不通过';
comment on column T_MARKET_GOODS.check_content
  is '审核意见';
comment on column T_MARKET_GOODS.check_man
  is '审核人';
comment on column T_MARKET_GOODS.check_time
  is '审核时间';
alter table T_MARKET_GOODS
  add constraint PK_GOODS primary key (GOODS_NO);

prompt
prompt Creating table T_MARKET_ORDER
prompt =============================
prompt
create table T_MARKET_ORDER
(
  id               NUMBER(9) not null,
  product_id       VARCHAR2(32) not null,
  auction_quantity INTEGER not null,
  auction_price    NUMBER(13,2) not null,
  auction_time     TIMESTAMP(6),
  status           INTEGER,
  operator_id      VARCHAR2(32),
  product_name     VARCHAR2(254),
  product_price    NUMBER(13,2),
  goods_no         VARCHAR2(20),
  orderid          VARCHAR2(64),
  create_time      TIMESTAMP(6),
  update_time      TIMESTAMP(6),
  del_flag         INTEGER default 0 not null
)
;
comment on table T_MARKET_ORDER
  is '竞拍订单表';
comment on column T_MARKET_ORDER.id
  is '编号';
comment on column T_MARKET_ORDER.product_id
  is '商品ID';
comment on column T_MARKET_ORDER.auction_quantity
  is '竞拍商品数量';
comment on column T_MARKET_ORDER.auction_price
  is '竞拍价';
comment on column T_MARKET_ORDER.auction_time
  is '竞拍时间';
comment on column T_MARKET_ORDER.status
  is '0-未支付，未生成订单  1-支付成功   2-已过期  3-已生成订单';
comment on column T_MARKET_ORDER.operator_id
  is '拍得者卡号';
comment on column T_MARKET_ORDER.product_name
  is '商品名称';
comment on column T_MARKET_ORDER.product_price
  is '商品单价';
comment on column T_MARKET_ORDER.goods_no
  is '竞拍商品号';
comment on column T_MARKET_ORDER.orderid
  is '订单编号，用于根据订单的状态更新竞拍商品的状态';
comment on column T_MARKET_ORDER.create_time
  is '记录创建时间';
comment on column T_MARKET_ORDER.update_time
  is '记录更新时间';
comment on column T_MARKET_ORDER.del_flag
  is '删除标志：0-未删除 1-已删除';
alter table T_MARKET_ORDER
  add constraint PK_ORDER_ID primary key (ID);

prompt
prompt Creating table T_MARKET_PRODUCT
prompt ===============================
prompt
create table T_MARKET_PRODUCT
(
  id           VARCHAR2(4) not null,
  product_name VARCHAR2(200),
  list_price   NUMBER(12,2),
  sc_price     NUMBER(12,2),
  product_pic  VARCHAR2(50),
  product_link VARCHAR2(150)
)
;
comment on table T_MARKET_PRODUCT
  is '竞拍商品广告';
comment on column T_MARKET_PRODUCT.id
  is '编号';
comment on column T_MARKET_PRODUCT.product_name
  is '商品名';
comment on column T_MARKET_PRODUCT.list_price
  is '市场价';
comment on column T_MARKET_PRODUCT.sc_price
  is '商城价';
comment on column T_MARKET_PRODUCT.product_pic
  is '商品图片路径';
comment on column T_MARKET_PRODUCT.product_link
  is '商品页面链接';
alter table T_MARKET_PRODUCT
  add primary key (ID);

prompt
prompt Creating table T_MARKET_REMIND
prompt ==============================
prompt
create table T_MARKET_REMIND
(
  id         NUMBER(8) not null,
  mobile     VARCHAR2(11),
  goods_no   NUMBER(8),
  status     VARCHAR2(2) default 0,
  begin_time TIMESTAMP(6),
  type       VARCHAR2(2) default 1,
  goods_name VARCHAR2(70)
)
;
comment on table T_MARKET_REMIND
  is '定时提醒';
comment on column T_MARKET_REMIND.id
  is '编号';
comment on column T_MARKET_REMIND.mobile
  is '手机号';
comment on column T_MARKET_REMIND.goods_no
  is '提醒商品编号';
comment on column T_MARKET_REMIND.status
  is '状态 0： 未发送， 1：已发送';
comment on column T_MARKET_REMIND.begin_time
  is '商品活动开始时间';
comment on column T_MARKET_REMIND.type
  is '1:竞拍提醒,2:秒杀提醒 ';
comment on column T_MARKET_REMIND.goods_name
  is '商品名称';
alter table T_MARKET_REMIND
  add primary key (ID);

prompt
prompt Creating table T_MARKET_SECKILL
prompt ===============================
prompt
create table T_MARKET_SECKILL
(
  goods_no      NUMBER(9) not null,
  goods_name    VARCHAR2(250) not null,
  goods_price   NUMBER(13,2) not null,
  seckill_price NUMBER(13,2) not null,
  goods_pic1    VARCHAR2(250),
  goods_pic2    VARCHAR2(250),
  goods_pic3    VARCHAR2(250),
  goods_pic4    VARCHAR2(250),
  start_time    TIMESTAMP(6) not null,
  end_time      TIMESTAMP(6) not null,
  update_time   TIMESTAMP(6),
  status        VARCHAR2(1),
  imagepath     VARCHAR2(100),
  user_id       NUMBER(8) not null,
  order_id      VARCHAR2(13),
  create_time   TIMESTAMP(6),
  id            NUMBER(9) not null
)
;
comment on table T_MARKET_SECKILL
  is '秒杀订单表';
comment on column T_MARKET_SECKILL.goods_no
  is '商品编号';
comment on column T_MARKET_SECKILL.goods_name
  is '商品名称';
comment on column T_MARKET_SECKILL.goods_price
  is '市场价';
comment on column T_MARKET_SECKILL.seckill_price
  is '秒杀价';
comment on column T_MARKET_SECKILL.goods_pic1
  is '图片1';
comment on column T_MARKET_SECKILL.goods_pic2
  is '图片2';
comment on column T_MARKET_SECKILL.goods_pic3
  is '图片3';
comment on column T_MARKET_SECKILL.goods_pic4
  is '图片4';
comment on column T_MARKET_SECKILL.start_time
  is '秒杀开始时间';
comment on column T_MARKET_SECKILL.end_time
  is '秒杀结束时间';
comment on column T_MARKET_SECKILL.update_time
  is '更新时间';
comment on column T_MARKET_SECKILL.status
  is '0-未支付，未生成订单  1-支付成功   2-已过期  3-已生成订单';
comment on column T_MARKET_SECKILL.imagepath
  is '封面图片';
comment on column T_MARKET_SECKILL.user_id
  is '支付者id';
comment on column T_MARKET_SECKILL.order_id
  is '支付者手机';
comment on column T_MARKET_SECKILL.create_time
  is '订单号';
comment on column T_MARKET_SECKILL.id
  is '编号';
alter table T_MARKET_SECKILL
  add constraint SECKILL_ID primary key (ID);

prompt
prompt Creating table T_MARKET_SECKILL_CONTENT
prompt =======================================
prompt
create table T_MARKET_SECKILL_CONTENT
(
  id               VARCHAR2(2) not null,
  title            VARCHAR2(20),
  last_modify_time VARCHAR2(20),
  content          CLOB
)
;
comment on table T_MARKET_SECKILL_CONTENT
  is '秒杀文案配置表';
comment on column T_MARKET_SECKILL_CONTENT.id
  is '编号';
comment on column T_MARKET_SECKILL_CONTENT.title
  is '秒杀文案名称';
comment on column T_MARKET_SECKILL_CONTENT.last_modify_time
  is '最后修改时间';
comment on column T_MARKET_SECKILL_CONTENT.content
  is '秒杀文案内容';
alter table T_MARKET_SECKILL_CONTENT
  add primary key (ID);

prompt
prompt Creating table T_MARKET_SECKILL_LOG
prompt ===================================
prompt
create table T_MARKET_SECKILL_LOG
(
  id          NUMBER(8) not null,
  goods_no    NUMBER(8) not null,
  user_id     NUMBER(8) not null,
  create_time TIMESTAMP(6) not null,
  status      VARCHAR2(1) default 0 not null
)
;
comment on table T_MARKET_SECKILL_LOG
  is '秒杀记录表';
comment on column T_MARKET_SECKILL_LOG.id
  is '编号';
comment on column T_MARKET_SECKILL_LOG.goods_no
  is '商品编号';
comment on column T_MARKET_SECKILL_LOG.user_id
  is '用户编号';
comment on column T_MARKET_SECKILL_LOG.create_time
  is '秒杀时间';
comment on column T_MARKET_SECKILL_LOG.status
  is '0：秒杀失败；1：秒杀成功';
alter table T_MARKET_SECKILL_LOG
  add primary key (ID);

prompt
prompt Creating table T_MEMBER
prompt =======================
prompt
create table T_MEMBER
(
  id          NUMBER(9) not null,
  user_name   VARCHAR2(30),
  user_pass   VARCHAR2(50),
  email       VARCHAR2(50),
  real_name   VARCHAR2(50),
  terminal_id VARCHAR2(20),
  reg_time    VARCHAR2(14),
  status      CHAR(1),
  update_time VARCHAR2(14),
  vid         VARCHAR2(50),
  nick_name   VARCHAR2(50),
  area_code   VARCHAR2(6),
  sex         NUMBER(1),
  birthday    VARCHAR2(14),
  opened      NUMBER(1),
  qq          VARCHAR2(20),
  remark      VARCHAR2(1000),
  signature   VARCHAR2(1000),
  reg_source  NUMBER(2),
  user_level  VARCHAR2(50),
  cart_uuid   VARCHAR2(50),
  red_member  NUMBER(1)
)
;
alter table T_MEMBER
  add constraint PK_T_MEMBER_0905 primary key (ID);

prompt
prompt Creating table T_MEMBER_0910
prompt ============================
prompt
create table T_MEMBER_0910
(
  id          NUMBER(9) not null,
  user_name   VARCHAR2(30),
  user_pass   VARCHAR2(50),
  email       VARCHAR2(50),
  real_name   VARCHAR2(50),
  terminal_id VARCHAR2(20),
  reg_time    VARCHAR2(14),
  status      CHAR(1),
  update_time VARCHAR2(14),
  vid         VARCHAR2(50),
  nick_name   VARCHAR2(50),
  area_code   VARCHAR2(6),
  sex         NUMBER(1) default 1,
  birthday    VARCHAR2(14),
  opened      NUMBER(1) default 0,
  qq          VARCHAR2(20),
  remark      VARCHAR2(1000),
  signature   VARCHAR2(1000),
  reg_source  NUMBER(2) default 1,
  user_level  VARCHAR2(50) default 0,
  cart_uuid   VARCHAR2(50),
  red_member  NUMBER(1) default 0
)
;
comment on table T_MEMBER_0910
  is '商城用户帐号表';
comment on column T_MEMBER_0910.user_name
  is '用户登录名';
comment on column T_MEMBER_0910.user_pass
  is '用户密码';
comment on column T_MEMBER_0910.email
  is '用户邮箱';
comment on column T_MEMBER_0910.real_name
  is '真实姓名';
comment on column T_MEMBER_0910.terminal_id
  is '手机号码';
comment on column T_MEMBER_0910.reg_time
  is '注册时间';
comment on column T_MEMBER_0910.status
  is '状态 1正常 2暂停';
comment on column T_MEMBER_0910.update_time
  is '更新时间';
comment on column T_MEMBER_0910.vid
  is 'JSSSO VID';
comment on column T_MEMBER_0910.nick_name
  is '昵称';
comment on column T_MEMBER_0910.area_code
  is '归属地区代码';
comment on column T_MEMBER_0910.sex
  is '性别1女2男';
comment on column T_MEMBER_0910.birthday
  is '生日';
comment on column T_MEMBER_0910.opened
  is '信息是否公开 0不公开 1公开';
comment on column T_MEMBER_0910.qq
  is 'QQ';
comment on column T_MEMBER_0910.remark
  is '个人信息描述';
comment on column T_MEMBER_0910.signature
  is '个性签名';
comment on column T_MEMBER_0910.reg_source
  is '注册来源 1WEBSSO 3-BOSS(刷新会员等级时会员不存在时候生成) 2 短信购';
comment on column T_MEMBER_0910.user_level
  is '会员级别
0 普通会员
10 体验会员 长期免费。
11 体验会员 定期免费。
20 普通会员 1元/月。
21 普通会员 3元/月。
22 普通会员 5元/月。
30 中级会员 30元/年。
40 高级会员 30元/月。
';
comment on column T_MEMBER_0910.cart_uuid
  is '购物车标识';
comment on column T_MEMBER_0910.red_member
  is '是否红钻 0否 1是';
create index IDX_T_MEMBER on T_MEMBER_0910 (TERMINAL_ID);
alter table T_MEMBER_0910
  add constraint PK_MEMBER_ID primary key (ID);

prompt
prompt Creating table T_MEMBER_ADDRESS
prompt ===============================
prompt
create table T_MEMBER_ADDRESS
(
  id               NUMBER(9) not null,
  mid              NUMBER(9) not null,
  remark           VARCHAR2(10),
  region           VARCHAR2(10) not null,
  address          VARCHAR2(200) not null,
  zipcode          VARCHAR2(10),
  name             VARCHAR2(50) not null,
  mobile           VARCHAR2(40),
  phone            VARCHAR2(40),
  update_time      VARCHAR2(14),
  create_time      VARCHAR2(14),
  last_use_time    VARCHAR2(14),
  default_shipping CHAR(1) default '0',
  default_pay_type VARCHAR2(2)
)
;
comment on table T_MEMBER_ADDRESS
  is '收货地址';
comment on column T_MEMBER_ADDRESS.mid
  is '用户编号';
comment on column T_MEMBER_ADDRESS.remark
  is '地址标注';
comment on column T_MEMBER_ADDRESS.region
  is '地区编码';
comment on column T_MEMBER_ADDRESS.address
  is '详细地址';
comment on column T_MEMBER_ADDRESS.zipcode
  is '邮政编码';
comment on column T_MEMBER_ADDRESS.name
  is '收货人';
comment on column T_MEMBER_ADDRESS.mobile
  is '联系电话：手机';
comment on column T_MEMBER_ADDRESS.phone
  is '联系电话：固话';
comment on column T_MEMBER_ADDRESS.update_time
  is '更新时间';
comment on column T_MEMBER_ADDRESS.create_time
  is '创建时间';
comment on column T_MEMBER_ADDRESS.last_use_time
  is '最后使用时间';
comment on column T_MEMBER_ADDRESS.default_shipping
  is '默认地址标志 1是 0否';
comment on column T_MEMBER_ADDRESS.default_pay_type
  is '默认支付方式（保留）';
alter table T_MEMBER_ADDRESS
  add constraint PK_MEMBER_ADDRESS primary key (ID);

prompt
prompt Creating table T_MEMBER_ADDRESS_0905
prompt ====================================
prompt
create table T_MEMBER_ADDRESS_0905
(
  id               NUMBER(9) not null,
  mid              NUMBER(9) not null,
  remark           VARCHAR2(10),
  region           VARCHAR2(10) not null,
  address          VARCHAR2(200) not null,
  zipcode          VARCHAR2(12),
  name             VARCHAR2(50) not null,
  mobile           VARCHAR2(20),
  phone            VARCHAR2(20),
  update_time      VARCHAR2(14),
  create_time      VARCHAR2(14),
  last_use_time    VARCHAR2(14),
  default_shipping CHAR(1),
  default_pay_type VARCHAR2(2)
)
;
alter table T_MEMBER_ADDRESS_0905
  add constraint PK_T_MEMBER_ADDRESS_0905 primary key (ID);

prompt
prompt Creating table T_MEMBER_BAK
prompt ===========================
prompt
create table T_MEMBER_BAK
(
  id          NUMBER(9) not null,
  user_name   VARCHAR2(30),
  user_pass   VARCHAR2(50),
  email       VARCHAR2(50),
  real_name   VARCHAR2(50),
  terminal_id VARCHAR2(20),
  reg_time    VARCHAR2(14),
  status      CHAR(1),
  update_time VARCHAR2(14),
  vid         VARCHAR2(50),
  nick_name   VARCHAR2(50),
  area_code   VARCHAR2(6),
  sex         NUMBER(1),
  birthday    VARCHAR2(14),
  opened      NUMBER(1),
  qq          VARCHAR2(20),
  remark      VARCHAR2(1000),
  signature   VARCHAR2(1000),
  reg_source  NUMBER(2),
  user_level  VARCHAR2(50),
  cart_uuid   VARCHAR2(50),
  red_member  NUMBER(1)
)
;

prompt
prompt Creating table T_MEMBER_FAVORITE
prompt ================================
prompt
create table T_MEMBER_FAVORITE
(
  id            NUMBER(9) not null,
  favorite_id   NUMBER(9),
  update_time   VARCHAR2(14),
  favorite_type NUMBER(1),
  user_id       NUMBER(9)
)
;
comment on table T_MEMBER_FAVORITE
  is '用户收藏表';
comment on column T_MEMBER_FAVORITE.favorite_id
  is '商品id或商户id';
comment on column T_MEMBER_FAVORITE.favorite_type
  is '收藏类型 1、商品 2、商户';
alter table T_MEMBER_FAVORITE
  add constraint PK_MEMBER_FAVORITE_ID primary key (ID);

prompt
prompt Creating table T_MEMBER_INVOICE
prompt ===============================
prompt
create table T_MEMBER_INVOICE
(
  id                 NUMBER(9) not null,
  mid                NUMBER(9),
  invoice_type       CHAR(1),
  invoice_title_type CHAR(1),
  invoice_title      VARCHAR2(40)
)
;
comment on column T_MEMBER_INVOICE.id
  is 'id';
comment on column T_MEMBER_INVOICE.mid
  is '会员编号';
comment on column T_MEMBER_INVOICE.invoice_type
  is '发票类型 1普通发票';
comment on column T_MEMBER_INVOICE.invoice_title_type
  is '发票抬头类型 1个人2单位';
comment on column T_MEMBER_INVOICE.invoice_title
  is '发票抬头内容';
alter table T_MEMBER_INVOICE
  add constraint PK_MEMBER_INVOICE primary key (ID);

prompt
prompt Creating table T_MEMBER_ORGI
prompt ============================
prompt
create table T_MEMBER_ORGI
(
  id          NUMBER(9) not null,
  user_name   VARCHAR2(30),
  user_pass   VARCHAR2(50),
  email       VARCHAR2(50),
  real_name   VARCHAR2(54),
  terminal_id VARCHAR2(20),
  reg_time    VARCHAR2(14),
  status      CHAR(1),
  update_time VARCHAR2(14),
  vid         VARCHAR2(50),
  nick_name   VARCHAR2(50),
  area_code   VARCHAR2(6),
  sex         NUMBER(1),
  birthday    VARCHAR2(14),
  opened      NUMBER(1),
  qq          VARCHAR2(20),
  remark      VARCHAR2(1000),
  signature   VARCHAR2(1000),
  reg_source  NUMBER(2),
  user_level  VARCHAR2(50),
  cart_uuid   VARCHAR2(50),
  red_member  NUMBER(1)
)
;

prompt
prompt Creating table T_MEMBER_TERMINALID
prompt ==================================
prompt
create table T_MEMBER_TERMINALID
(
  id          NUMBER(9) not null,
  user_name   VARCHAR2(30),
  user_pass   VARCHAR2(50),
  email       VARCHAR2(50),
  real_name   VARCHAR2(54),
  terminal_id VARCHAR2(20),
  reg_time    VARCHAR2(14),
  status      CHAR(1),
  update_time VARCHAR2(14),
  vid         VARCHAR2(50),
  nick_name   VARCHAR2(50),
  area_code   VARCHAR2(6),
  sex         NUMBER(1),
  birthday    VARCHAR2(14),
  opened      NUMBER(1),
  qq          VARCHAR2(20),
  remark      VARCHAR2(1000),
  signature   VARCHAR2(1000),
  reg_source  NUMBER(2),
  user_level  VARCHAR2(50),
  cart_uuid   VARCHAR2(50),
  red_member  NUMBER(1)
)
;

prompt
prompt Creating table T_MEMBER_TERMINALID2
prompt ===================================
prompt
create table T_MEMBER_TERMINALID2
(
  id          NUMBER(9) not null,
  user_name   VARCHAR2(30),
  user_pass   VARCHAR2(50),
  email       VARCHAR2(50),
  real_name   VARCHAR2(54),
  terminal_id VARCHAR2(20),
  reg_time    VARCHAR2(14),
  status      CHAR(1),
  update_time VARCHAR2(14),
  vid         VARCHAR2(50),
  nick_name   VARCHAR2(50),
  area_code   VARCHAR2(6),
  sex         NUMBER(1),
  birthday    VARCHAR2(14),
  opened      NUMBER(1),
  qq          VARCHAR2(20),
  remark      VARCHAR2(1000),
  signature   VARCHAR2(1000),
  reg_source  NUMBER(2),
  user_level  VARCHAR2(50),
  cart_uuid   VARCHAR2(50),
  red_member  NUMBER(1)
)
;

prompt
prompt Creating table T_MEMBER_VERIFY_LOG
prompt ==================================
prompt
create table T_MEMBER_VERIFY_LOG
(
  id          NUMBER(9) not null,
  member_id   NUMBER(9),
  verify_time VARCHAR2(14),
  shop_class  NUMBER(1),
  shop_id     NUMBER(9)
)
;
comment on table T_MEMBER_VERIFY_LOG
  is '会员验证记录表';
comment on column T_MEMBER_VERIFY_LOG.member_id
  is '验证成功的会员id';
comment on column T_MEMBER_VERIFY_LOG.verify_time
  is '验证时间';
comment on column T_MEMBER_VERIFY_LOG.shop_class
  is '"1--业务门店 2--商户 3--渠道商"';
alter table T_MEMBER_VERIFY_LOG
  add constraint PK_MEMBER_VERIFY_LOG primary key (ID);

prompt
prompt Creating table T_MEMBER_XW
prompt ==========================
prompt
create table T_MEMBER_XW
(
  id          NUMBER(9) not null,
  user_name   VARCHAR2(30),
  user_pass   VARCHAR2(50),
  email       VARCHAR2(50),
  real_name   VARCHAR2(50),
  terminal_id VARCHAR2(20),
  reg_time    VARCHAR2(14),
  status      CHAR(1),
  update_time VARCHAR2(14),
  vid         VARCHAR2(50),
  nick_name   VARCHAR2(50),
  area_code   VARCHAR2(6),
  sex         NUMBER(1) default 1,
  birthday    VARCHAR2(14),
  opened      NUMBER(1) default 0,
  qq          VARCHAR2(20),
  remark      VARCHAR2(1000),
  signature   VARCHAR2(1000),
  reg_source  NUMBER(2) default 1,
  user_level  VARCHAR2(50) default 0,
  cart_uuid   VARCHAR2(50),
  red_member  NUMBER(1) default 0
)
;
comment on table T_MEMBER_XW
  is '商城用户帐号表';
comment on column T_MEMBER_XW.user_name
  is '用户登录名';
comment on column T_MEMBER_XW.user_pass
  is '用户密码';
comment on column T_MEMBER_XW.email
  is '用户邮箱';
comment on column T_MEMBER_XW.real_name
  is '真实姓名';
comment on column T_MEMBER_XW.terminal_id
  is '手机号码';
comment on column T_MEMBER_XW.reg_time
  is '注册时间';
comment on column T_MEMBER_XW.status
  is '状态 1正常 2暂停';
comment on column T_MEMBER_XW.update_time
  is '更新时间';
comment on column T_MEMBER_XW.vid
  is 'JSSSO VID';
comment on column T_MEMBER_XW.nick_name
  is '昵称';
comment on column T_MEMBER_XW.area_code
  is '归属地区代码';
comment on column T_MEMBER_XW.sex
  is '性别1女2男';
comment on column T_MEMBER_XW.birthday
  is '生日';
comment on column T_MEMBER_XW.opened
  is '信息是否公开 0不公开 1公开';
comment on column T_MEMBER_XW.qq
  is 'QQ';
comment on column T_MEMBER_XW.remark
  is '个人信息描述';
comment on column T_MEMBER_XW.signature
  is '个性签名';
comment on column T_MEMBER_XW.reg_source
  is '注册来源 1WEBSSO';
comment on column T_MEMBER_XW.user_level
  is '会员级别
0 普通会员
10 体验会员 长期免费。
11 体验会员 定期免费。
20 普通会员 1元/月。
21 普通会员 3元/月。
22 普通会员 5元/月。
30 中级会员 30元/年。
40 高级会员 30元/月。
';
comment on column T_MEMBER_XW.cart_uuid
  is '购物车标识';
comment on column T_MEMBER_XW.red_member
  is '是否红钻 0否 1是';
alter table T_MEMBER_XW
  add constraint PK_MEMBER_ID_XW primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_01
prompt =================================
prompt
create table T_MMS_BATCH_LOG_01
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_01
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_01.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_01.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_01.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_01.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_01.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_01
  add constraint PK_T_MMS_BATCH_LOG_01 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_02
prompt =================================
prompt
create table T_MMS_BATCH_LOG_02
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_02
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_02.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_02.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_02.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_02.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_02.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_02
  add constraint PK_T_MMS_BATCH_LOG_02 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_03
prompt =================================
prompt
create table T_MMS_BATCH_LOG_03
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_03
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_03.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_03.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_03.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_03.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_03.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_03
  add constraint PK_T_MMS_BATCH_LOG_03 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_04
prompt =================================
prompt
create table T_MMS_BATCH_LOG_04
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_04
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_04.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_04.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_04.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_04.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_04.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_04
  add constraint PK_T_MMS_BATCH_LOG_04 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_05
prompt =================================
prompt
create table T_MMS_BATCH_LOG_05
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_05
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_05.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_05.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_05.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_05.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_05.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_05
  add constraint PK_T_MMS_BATCH_LOG_05 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_06
prompt =================================
prompt
create table T_MMS_BATCH_LOG_06
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_06
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_06.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_06.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_06.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_06.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_06.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_06
  add constraint PK_T_MMS_BATCH_LOG_06 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_07
prompt =================================
prompt
create table T_MMS_BATCH_LOG_07
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_07
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_07.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_07.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_07.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_07.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_07.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_07
  add constraint PK_T_MMS_BATCH_LOG_07 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_08
prompt =================================
prompt
create table T_MMS_BATCH_LOG_08
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_08
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_08.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_08.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_08.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_08.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_08.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_08
  add constraint PK_T_MMS_BATCH_LOG_08 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_09
prompt =================================
prompt
create table T_MMS_BATCH_LOG_09
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_09
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_09.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_09.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_09.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_09.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_09.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_09
  add constraint PK_T_MMS_BATCH_LOG_09 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_10
prompt =================================
prompt
create table T_MMS_BATCH_LOG_10
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_10
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_10.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_10.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_10.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_10.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_10.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_10
  add constraint PK_T_MMS_BATCH_LOG_10 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_11
prompt =================================
prompt
create table T_MMS_BATCH_LOG_11
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_11
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_11.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_11.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_11.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_11.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_11.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_11
  add constraint PK_T_MMS_BATCH_LOG_11 primary key (ID);

prompt
prompt Creating table T_MMS_BATCH_LOG_12
prompt =================================
prompt
create table T_MMS_BATCH_LOG_12
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  msg_id      VARCHAR2(50)
)
;
comment on table T_MMS_BATCH_LOG_12
  is '彩信群发下行日志表，按月分表存储';
comment on column T_MMS_BATCH_LOG_12.id
  is '序列，递增无实意，主键';
comment on column T_MMS_BATCH_LOG_12.task_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_12.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_BATCH_LOG_12.status
  is 'submit_resp回包中的状态。';
comment on column T_MMS_BATCH_LOG_12.msg_id
  is '通讯消息ID';
alter table T_MMS_BATCH_LOG_12
  add constraint PK_T_MMS_BATCH_LOG_12 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_01
prompt ==============================
prompt
create table T_MMS_MO_LOG_01
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_01
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_01.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_01.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_01.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_01.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_01.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_01.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_01.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_01.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_01.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_01.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_01.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_01.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_01.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_01.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_01
  add constraint PK_T_MMS_MO_LOG_01 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_02
prompt ==============================
prompt
create table T_MMS_MO_LOG_02
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_02
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_02.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_02.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_02.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_02.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_02.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_02.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_02.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_02.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_02.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_02.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_02.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_02.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_02.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_02.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_02
  add constraint PK_T_MMS_MO_LOG_02 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_03
prompt ==============================
prompt
create table T_MMS_MO_LOG_03
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_03
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_03.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_03.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_03.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_03.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_03.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_03.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_03.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_03.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_03.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_03.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_03.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_03.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_03.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_03.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_03
  add constraint PK_T_MMS_MO_LOG_03 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_04
prompt ==============================
prompt
create table T_MMS_MO_LOG_04
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_04
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_04.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_04.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_04.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_04.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_04.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_04.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_04.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_04.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_04.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_04.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_04.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_04.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_04.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_04.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_04
  add constraint PK_T_MMS_MO_LOG_04 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_05
prompt ==============================
prompt
create table T_MMS_MO_LOG_05
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_05
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_05.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_05.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_05.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_05.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_05.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_05.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_05.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_05.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_05.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_05.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_05.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_05.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_05.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_05.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_05
  add constraint PK_T_MMS_MO_LOG_05 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_06
prompt ==============================
prompt
create table T_MMS_MO_LOG_06
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_06
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_06.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_06.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_06.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_06.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_06.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_06.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_06.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_06.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_06.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_06.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_06.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_06.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_06.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_06.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_06
  add constraint PK_T_MMS_MO_LOG_06 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_07
prompt ==============================
prompt
create table T_MMS_MO_LOG_07
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_07
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_07.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_07.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_07.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_07.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_07.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_07.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_07.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_07.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_07.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_07.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_07.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_07.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_07.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_07.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_07
  add constraint PK_T_MMS_MO_LOG_07 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_08
prompt ==============================
prompt
create table T_MMS_MO_LOG_08
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_08
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_08.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_08.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_08.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_08.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_08.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_08.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_08.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_08.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_08.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_08.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_08.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_08.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_08.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_08.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_08
  add constraint PK_T_MMS_MO_LOG_08 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_09
prompt ==============================
prompt
create table T_MMS_MO_LOG_09
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_09
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_09.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_09.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_09.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_09.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_09.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_09.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_09.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_09.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_09.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_09.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_09.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_09.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_09.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_09.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_09
  add constraint PK_T_MMS_MO_LOG_09 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_10
prompt ==============================
prompt
create table T_MMS_MO_LOG_10
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_10
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_10.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_10.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_10.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_10.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_10.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_10.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_10.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_10.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_10.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_10.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_10.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_10.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_10.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_10.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_10
  add constraint PK_T_MMS_MO_LOG_10 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_11
prompt ==============================
prompt
create table T_MMS_MO_LOG_11
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_11
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_11.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_11.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_11.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_11.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_11.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_11.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_11.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_11.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_11.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_11.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_11.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_11.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_11.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_11.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_11
  add constraint PK_T_MMS_MO_LOG_11 primary key (ID);

prompt
prompt Creating table T_MMS_MO_LOG_12
prompt ==============================
prompt
create table T_MMS_MO_LOG_12
(
  id              NUMBER(9) not null,
  title           VARCHAR2(200),
  mms_dir         VARCHAR2(250),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_MMS_MO_LOG_12
  is '彩信上行日志表，由路由模块完成路由后记录。';
comment on column T_MMS_MO_LOG_12.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MO_LOG_12.title
  is '彩信标题，可为空';
comment on column T_MMS_MO_LOG_12.mms_dir
  is '彩信内容相对路径，格式为：DELIVER/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MO_LOG_12.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_MMS_MO_LOG_12.sp_code
  is '特服号，非空';
comment on column T_MMS_MO_LOG_12.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_12.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_MMS_MO_LOG_12.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_MMS_MO_LOG_12.act_id
  is '活动ID';
comment on column T_MMS_MO_LOG_12.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_MMS_MO_LOG_12.unit_id
  is '单位编码';
comment on column T_MMS_MO_LOG_12.area_code
  is '地区标识';
comment on column T_MMS_MO_LOG_12.operator_code
  is '运营商标识';
comment on column T_MMS_MO_LOG_12.linkid
  is 'LINKID，保留字段';
alter table T_MMS_MO_LOG_12
  add constraint PK_T_MMS_MO_LOG_12 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_01
prompt ==============================
prompt
create table T_MMS_MT_LOG_01
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_01
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_01.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_01.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_01.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_01.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_01.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_01.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_01.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_01.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_01.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_01.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_01.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_01.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_01.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_01.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_01.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_01.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_01.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_01.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_01.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_01.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_01.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_01.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_01.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_01
  add constraint PK_T_MMS_MT_LOG_01 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_02
prompt ==============================
prompt
create table T_MMS_MT_LOG_02
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_02
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_02.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_02.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_02.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_02.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_02.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_02.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_02.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_02.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_02.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_02.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_02.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_02.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_02.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_02.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_02.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_02.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_02.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_02.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_02.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_02.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_02.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_02.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_02.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_02
  add constraint PK_T_MMS_MT_LOG_02 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_03
prompt ==============================
prompt
create table T_MMS_MT_LOG_03
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_03
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_03.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_03.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_03.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_03.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_03.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_03.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_03.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_03.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_03.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_03.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_03.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_03.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_03.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_03.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_03.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_03.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_03.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_03.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_03.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_03.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_03.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_03.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_03.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_03
  add constraint PK_T_MMS_MT_LOG_03 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_04
prompt ==============================
prompt
create table T_MMS_MT_LOG_04
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_04
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_04.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_04.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_04.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_04.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_04.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_04.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_04.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_04.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_04.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_04.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_04.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_04.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_04.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_04.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_04.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_04.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_04.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_04.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_04.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_04.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_04.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_04.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_04.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_04
  add constraint PK_T_MMS_MT_LOG_04 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_05
prompt ==============================
prompt
create table T_MMS_MT_LOG_05
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_05
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_05.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_05.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_05.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_05.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_05.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_05.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_05.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_05.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_05.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_05.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_05.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_05.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_05.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_05.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_05.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_05.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_05.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_05.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_05.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_05.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_05.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_05.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_05.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_05
  add constraint PK_T_MMS_MT_LOG_05 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_06
prompt ==============================
prompt
create table T_MMS_MT_LOG_06
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_06
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_06.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_06.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_06.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_06.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_06.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_06.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_06.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_06.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_06.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_06.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_06.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_06.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_06.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_06.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_06.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_06.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_06.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_06.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_06.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_06.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_06.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_06.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_06.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_06
  add constraint PK_T_MMS_MT_LOG_06 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_07
prompt ==============================
prompt
create table T_MMS_MT_LOG_07
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_07
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_07.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_07.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_07.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_07.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_07.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_07.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_07.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_07.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_07.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_07.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_07.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_07.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_07.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_07.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_07.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_07.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_07.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_07.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_07.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_07.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_07.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_07.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_07.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_07
  add constraint PK_T_MMS_MT_LOG_07 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_08
prompt ==============================
prompt
create table T_MMS_MT_LOG_08
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_08
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_08.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_08.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_08.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_08.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_08.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_08.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_08.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_08.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_08.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_08.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_08.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_08.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_08.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_08.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_08.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_08.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_08.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_08.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_08.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_08.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_08.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_08.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_08.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_08
  add constraint PK_T_MMS_MT_LOG_08 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_09
prompt ==============================
prompt
create table T_MMS_MT_LOG_09
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_09
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_09.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_09.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_09.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_09.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_09.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_09.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_09.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_09.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_09.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_09.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_09.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_09.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_09.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_09.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_09.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_09.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_09.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_09.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_09.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_09.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_09.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_09.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_09.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_09
  add constraint PK_T_MMS_MT_LOG_09 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_10
prompt ==============================
prompt
create table T_MMS_MT_LOG_10
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_10
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_10.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_10.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_10.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_10.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_10.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_10.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_10.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_10.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_10.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_10.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_10.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_10.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_10.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_10.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_10.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_10.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_10.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_10.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_10.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_10.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_10.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_10.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_10.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_10
  add constraint PK_T_MMS_MT_LOG_10 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_11
prompt ==============================
prompt
create table T_MMS_MT_LOG_11
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_11
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_11.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_11.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_11.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_11.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_11.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_11.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_11.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_11.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_11.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_11.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_11.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_11.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_11.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_11.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_11.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_11.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_11.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_11.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_11.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_11.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_11.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_11.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_11.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_11
  add constraint PK_T_MMS_MT_LOG_11 primary key (ID);

prompt
prompt Creating table T_MMS_MT_LOG_12
prompt ==============================
prompt
create table T_MMS_MT_LOG_12
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  status_sign      VARCHAR2(100),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_LOG_12
  is '彩信业务下行日志表，按月分表存储';
comment on column T_MMS_MT_LOG_12.id
  is '序列，递增无实意，主键';
comment on column T_MMS_MT_LOG_12.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_LOG_12.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_LOG_12.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_LOG_12.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_LOG_12.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_LOG_12.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_LOG_12.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_LOG_12.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_LOG_12.fee
  is '费率，非空';
comment on column T_MMS_MT_LOG_12.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_LOG_12.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_12.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_LOG_12.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_LOG_12.act_id
  is '活动ID';
comment on column T_MMS_MT_LOG_12.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_LOG_12.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_LOG_12.area_code
  is '地区标识';
comment on column T_MMS_MT_LOG_12.operator_code
  is '运营商标识';
comment on column T_MMS_MT_LOG_12.msg_id
  is '下行标识，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_12.status
  is '下行状态码，彩信中心返回，非空';
comment on column T_MMS_MT_LOG_12.status_sign
  is '下行状态注解';
comment on column T_MMS_MT_LOG_12.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_LOG_12
  add constraint PK_T_MMS_MT_LOG_12 primary key (ID);

prompt
prompt Creating table T_MMS_MT_WAIT
prompt ============================
prompt
create table T_MMS_MT_WAIT
(
  id               NUMBER(9) not null,
  title            VARCHAR2(200),
  smil_name        VARCHAR2(40),
  mms_dir          VARCHAR2(250),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(120),
  cc_terminal_id   VARCHAR2(250),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(9),
  is_report        NUMBER(1),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  sale_mode        NUMBER(3),
  act_id           NUMBER(9),
  mms_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  mms_type         NUMBER(3)
)
;
comment on table T_MMS_MT_WAIT
  is '彩信业务下行等待表，存放待发送的彩信下行；
一般用于定时发送下行。';
comment on column T_MMS_MT_WAIT.id
  is '序列，递增无实意，主键，SEQ_MMS_MT_WAIT';
comment on column T_MMS_MT_WAIT.title
  is '彩信标题，可为空';
comment on column T_MMS_MT_WAIT.smil_name
  is '彩信SMIL文件，可有可无';
comment on column T_MMS_MT_WAIT.mms_dir
  is '彩信内容相对路径，格式为：信息源代码/月期（YYYYMM）/ 彩信编号';
comment on column T_MMS_MT_WAIT.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_MMS_MT_WAIT.dest_terminal_id
  is '接收方手机号码，非空';
comment on column T_MMS_MT_WAIT.cc_terminal_id
  is '抄送方手机号码，个数为0-9，以“,”间隔';
comment on column T_MMS_MT_WAIT.service_id
  is '业务代码，非空';
comment on column T_MMS_MT_WAIT.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_MMS_MT_WAIT.fee
  is '费率，非空';
comment on column T_MMS_MT_WAIT.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_MMS_MT_WAIT.request_time
  is '记录插入时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_WAIT.submit_time
  is '要求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_MMS_MT_WAIT.sale_mode
  is '本字段无效，默认填0';
comment on column T_MMS_MT_WAIT.act_id
  is '活动ID';
comment on column T_MMS_MT_WAIT.mms_id
  is '彩信编号，非空';
comment on column T_MMS_MT_WAIT.unit_id
  is '单位（第三合作方）编号';
comment on column T_MMS_MT_WAIT.area_code
  is '地区标识';
comment on column T_MMS_MT_WAIT.operator_code
  is '运营商标识';
comment on column T_MMS_MT_WAIT.mms_type
  is '1、彩信
2、图片
3、铃声';
alter table T_MMS_MT_WAIT
  add constraint PK_T_MMS_MT_WAIT primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_01
prompt ==============================
prompt
create table T_MMS_REPORT_01
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_01
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_01.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_01.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_01.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_01.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_01.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_01.sender
  is '手机号码';
alter table T_MMS_REPORT_01
  add constraint PK_T_MMS_REPORT_01 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_02
prompt ==============================
prompt
create table T_MMS_REPORT_02
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_02
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_02.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_02.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_02.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_02.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_02.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_02.sender
  is '手机号码';
alter table T_MMS_REPORT_02
  add constraint PK_T_MMS_REPORT_02 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_03
prompt ==============================
prompt
create table T_MMS_REPORT_03
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_03
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_03.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_03.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_03.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_03.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_03.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_03.sender
  is '手机号码';
alter table T_MMS_REPORT_03
  add constraint PK_T_MMS_REPORT_03 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_04
prompt ==============================
prompt
create table T_MMS_REPORT_04
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_04
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_04.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_04.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_04.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_04.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_04.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_04.sender
  is '手机号码';
alter table T_MMS_REPORT_04
  add constraint PK_T_MMS_REPORT_04 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_05
prompt ==============================
prompt
create table T_MMS_REPORT_05
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_05
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_05.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_05.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_05.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_05.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_05.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_05.sender
  is '手机号码';
alter table T_MMS_REPORT_05
  add constraint PK_T_MMS_REPORT_05 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_06
prompt ==============================
prompt
create table T_MMS_REPORT_06
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_06
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_06.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_06.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_06.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_06.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_06.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_06.sender
  is '手机号码';
alter table T_MMS_REPORT_06
  add constraint PK_T_MMS_REPORT_06 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_07
prompt ==============================
prompt
create table T_MMS_REPORT_07
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_07
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_07.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_07.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_07.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_07.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_07.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_07.sender
  is '手机号码';
alter table T_MMS_REPORT_07
  add constraint PK_T_MMS_REPORT_07 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_08
prompt ==============================
prompt
create table T_MMS_REPORT_08
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_08
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_08.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_08.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_08.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_08.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_08.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_08.sender
  is '手机号码';
alter table T_MMS_REPORT_08
  add constraint PK_T_MMS_REPORT_08 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_09
prompt ==============================
prompt
create table T_MMS_REPORT_09
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_09
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_09.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_09.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_09.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_09.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_09.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_09.sender
  is '手机号码';
alter table T_MMS_REPORT_09
  add constraint PK_T_MMS_REPORT_09 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_10
prompt ==============================
prompt
create table T_MMS_REPORT_10
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_10
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_10.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_10.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_10.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_10.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_10.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_10.sender
  is '手机号码';
alter table T_MMS_REPORT_10
  add constraint PK_T_MMS_REPORT_10 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_11
prompt ==============================
prompt
create table T_MMS_REPORT_11
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_11
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_11.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_11.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_11.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_11.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_11.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_11.sender
  is '手机号码';
alter table T_MMS_REPORT_11
  add constraint PK_T_MMS_REPORT_11 primary key (ID);

prompt
prompt Creating table T_MMS_REPORT_12
prompt ==============================
prompt
create table T_MMS_REPORT_12
(
  id            NUMBER(9) not null,
  msg_id        VARCHAR2(50),
  status        VARCHAR2(10),
  status_detail VARCHAR2(50),
  get_time      VARCHAR2(14),
  sender        VARCHAR2(21)
)
;
comment on table T_MMS_REPORT_12
  is '彩信下行状态报告表，按月分表存放';
comment on column T_MMS_REPORT_12.id
  is '序列，递增无实意，主键';
comment on column T_MMS_REPORT_12.msg_id
  is '彩信信息标识，以此关联彩信下行，非空';
comment on column T_MMS_REPORT_12.status
  is '状态报告状态码，非空';
comment on column T_MMS_REPORT_12.status_detail
  is '状态报告状态细节代码';
comment on column T_MMS_REPORT_12.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_MMS_REPORT_12.sender
  is '手机号码';
alter table T_MMS_REPORT_12
  add constraint PK_T_MMS_REPORT_12 primary key (ID);

prompt
prompt Creating table T_OPEN_CUSTOMER
prompt ==============================
prompt
create table T_OPEN_CUSTOMER
(
  app_id     VARCHAR2(32) not null,
  app_key    VARCHAR2(32),
  name       VARCHAR2(100),
  remark     VARCHAR2(1000),
  ips        VARCHAR2(300),
  status     CHAR(1) default 0,
  pay_notify VARCHAR2(200),
  app_type   CHAR(1) default 1,
  shop_class NUMBER(1),
  shop_id    NUMBER(9) default 0,
  store_id   NUMBER(9) default 0
)
;
comment on table T_OPEN_CUSTOMER
  is '开放平台第三方应用信息';
comment on column T_OPEN_CUSTOMER.app_id
  is 'app id';
comment on column T_OPEN_CUSTOMER.app_key
  is 'app key';
comment on column T_OPEN_CUSTOMER.name
  is '应用名称';
comment on column T_OPEN_CUSTOMER.remark
  is '应用详情';
comment on column T_OPEN_CUSTOMER.ips
  is '最多20个的可信任ip地址，为空表示不限制ip，逗号分割';
comment on column T_OPEN_CUSTOMER.status
  is '应用状态，0新建 1审核驳回 2测试中 3上线 4下线';
comment on column T_OPEN_CUSTOMER.pay_notify
  is '支付通知接口';
comment on column T_OPEN_CUSTOMER.app_type
  is '应用类型，1垂直频道 2商户';
comment on column T_OPEN_CUSTOMER.shop_class
  is '商户类型，1业务门店
2商户
3渠道商';
comment on column T_OPEN_CUSTOMER.shop_id
  is '门店ID，如果为商户或渠道商则填0';
comment on column T_OPEN_CUSTOMER.store_id
  is '商户、渠道商ID';
alter table T_OPEN_CUSTOMER
  add constraint PK_OPEN_CUSTOMER primary key (APP_ID);

prompt
prompt Creating table T_OPEN_CUSTOMER_ROLE
prompt ===================================
prompt
create table T_OPEN_CUSTOMER_ROLE
(
  app_id  VARCHAR2(32) not null,
  role_id NUMBER(9) not null
)
;
comment on table T_OPEN_CUSTOMER_ROLE
  is '第三方应用和权限的对应关系表';
comment on column T_OPEN_CUSTOMER_ROLE.app_id
  is 'app id';
comment on column T_OPEN_CUSTOMER_ROLE.role_id
  is 'role_id';
alter table T_OPEN_CUSTOMER_ROLE
  add constraint PK_OPEN_CUSTOMER_ROLE primary key (APP_ID, ROLE_ID);

prompt
prompt Creating table T_OPEN_ROLE_ITEM
prompt ===============================
prompt
create table T_OPEN_ROLE_ITEM
(
  id   NUMBER(9),
  name VARCHAR2(20),
  path VARCHAR2(200)
)
;
comment on table T_OPEN_ROLE_ITEM
  is '开放接口中需要权限的项目表';
comment on column T_OPEN_ROLE_ITEM.id
  is 'id';
comment on column T_OPEN_ROLE_ITEM.name
  is '名称';
comment on column T_OPEN_ROLE_ITEM.path
  is '对应允许的路径，antpath风格。多个用逗号分割';

prompt
prompt Creating table T_ORDER_EXCEPTION
prompt ================================
prompt
create table T_ORDER_EXCEPTION
(
  id           NUMBER(19) not null,
  act_order_id VARCHAR2(50 CHAR),
  create_time  VARCHAR2(14 CHAR),
  description  VARCHAR2(500 CHAR),
  source       VARCHAR2(100 CHAR),
  type         VARCHAR2(50 CHAR),
  user_id      VARCHAR2(50 CHAR)
)
;
alter table T_ORDER_EXCEPTION
  add primary key (ID);

prompt
prompt Creating table T_ORDER_REFUND
prompt =============================
prompt
create table T_ORDER_REFUND
(
  id                    NUMBER(9) not null,
  order_id              NUMBER(19),
  store_id              NUMBER(9),
  status                NUMBER(1),
  refund_fee            NUMBER(9,2),
  create_time           VARCHAR2(14),
  user_id               NUMBER(9),
  reason                VARCHAR2(300),
  shop_user_id          NUMBER(9),
  update_time           VARCHAR2(14),
  shop_remark           VARCHAR2(300),
  audit_user_id         NUMBER(9),
  audit_time            VARCHAR2(14),
  audit_remark          VARCHAR2(300),
  deal_time             VARCHAR2(14),
  cash_fee              NUMBER(9,2),
  coin_fee              NUMBER(9,2),
  total_code            NUMBER(9),
  success_code          NUMBER(9),
  order_record_cash_id  NUMBER(19),
  order_record_coin_id  NUMBER(19),
  order_record_score_id NUMBER(19)
)
;
comment on column T_ORDER_REFUND.order_id
  is '订单id';
comment on column T_ORDER_REFUND.store_id
  is '商户id';
comment on column T_ORDER_REFUND.status
  is '状态
		 1-等待商户确认
		2-等待审核
		3-商户拒绝确认
		4-等待系统退款 
		5-审核失败
		6-退款成功 7-退款失败';
comment on column T_ORDER_REFUND.refund_fee
  is '实际退款金额';
comment on column T_ORDER_REFUND.create_time
  is '申请时间';
comment on column T_ORDER_REFUND.user_id
  is '操作人';
comment on column T_ORDER_REFUND.reason
  is '申请原因';
comment on column T_ORDER_REFUND.shop_user_id
  is '商户操作人';
comment on column T_ORDER_REFUND.update_time
  is '商户操作时间';
comment on column T_ORDER_REFUND.shop_remark
  is '商户说明';
comment on column T_ORDER_REFUND.audit_user_id
  is '审核人';
comment on column T_ORDER_REFUND.audit_time
  is '审核时间';
comment on column T_ORDER_REFUND.audit_remark
  is '审核说明';
comment on column T_ORDER_REFUND.deal_time
  is '处理时间';
comment on column T_ORDER_REFUND.cash_fee
  is '实际退款现金';
comment on column T_ORDER_REFUND.coin_fee
  is '实际退款商城币';
comment on column T_ORDER_REFUND.total_code
  is '拟退码数量';
comment on column T_ORDER_REFUND.success_code
  is '退码成功数量';
comment on column T_ORDER_REFUND.order_record_cash_id
  is '成功退款的现金id，0表示调用接口异常';
comment on column T_ORDER_REFUND.order_record_coin_id
  is '成功退款的商城币id，0表示调用接口异常';
comment on column T_ORDER_REFUND.order_record_score_id
  is '成功退款的积分id，0表示调用接口异常';
alter table T_ORDER_REFUND
  add constraint PK_T_ORDER_REFUND primary key (ID);

prompt
prompt Creating table T_ORDER_REFUND_GOODS
prompt ===================================
prompt
create table T_ORDER_REFUND_GOODS
(
  id             NUMBER(9) not null,
  refund_id      NUMBER(9),
  order_goods_id NUMBER(19),
  goods_id       NUMBER(9),
  back_number    NUMBER(5),
  cash           NUMBER(9,2),
  coin           NUMBER(9,2)
)
;
comment on column T_ORDER_REFUND_GOODS.refund_id
  is '退货单编号';
comment on column T_ORDER_REFUND_GOODS.order_goods_id
  is '订单商品id';
comment on column T_ORDER_REFUND_GOODS.goods_id
  is '商品id';
comment on column T_ORDER_REFUND_GOODS.back_number
  is '退货数量';
comment on column T_ORDER_REFUND_GOODS.cash
  is '现金';
comment on column T_ORDER_REFUND_GOODS.coin
  is '商城币';
alter table T_ORDER_REFUND_GOODS
  add constraint PK_T_ORDER_REFUND_GOODS primary key (ID);

prompt
prompt Creating table T_PAGE_STATIC_INFO
prompt =================================
prompt
create table T_PAGE_STATIC_INFO
(
  id          NUMBER(8) not null,
  type        NUMBER(1) not null,
  resource_id NUMBER(8) not null,
  status      NUMBER(1) not null,
  create_time VARCHAR2(14) not null
)
;
comment on column T_PAGE_STATIC_INFO.type
  is '类型：0是商品，1是商户';
comment on column T_PAGE_STATIC_INFO.resource_id
  is '资源ID：商品ID或商户ID';
comment on column T_PAGE_STATIC_INFO.status
  is '状态：0是静态化失败，1是静态化成功';
alter table T_PAGE_STATIC_INFO
  add constraint PAGESTATICINFOID primary key (ID);

prompt
prompt Creating table T_PAY_ORDER
prompt ==========================
prompt
create table T_PAY_ORDER
(
  id             NUMBER(19) not null,
  act_order_id   NUMBER(19),
  pay_order_id   NUMBER(19),
  operate        NUMBER(3),
  pay_mode       NUMBER(3),
  pay_channel    VARCHAR2(50),
  user_id        NUMBER(19),
  shop_id        NUMBER(19),
  payment_amount NUMBER(9),
  status         NUMBER(3),
  status_text    VARCHAR2(500),
  create_time    VARCHAR2(14),
  update_time    VARCHAR2(14),
  remark         VARCHAR2(500),
  charge_time    VARCHAR2(14)
)
;
comment on table T_PAY_ORDER
  is '支付订单主表';
comment on column T_PAY_ORDER.id
  is '记录ID';
comment on column T_PAY_ORDER.act_order_id
  is '业务订单Id';
comment on column T_PAY_ORDER.pay_order_id
  is '支付订单Id';
comment on column T_PAY_ORDER.operate
  is '1-支付,2-退款';
comment on column T_PAY_ORDER.pay_mode
  is '1-web, 2-后台';
comment on column T_PAY_ORDER.pay_channel
  is '支付渠道，例如支付宝-ali, 联动umpay, 手机支付cmpay,...';
comment on column T_PAY_ORDER.user_id
  is '用户Id';
comment on column T_PAY_ORDER.shop_id
  is '商户Id，用户购买商户的';
comment on column T_PAY_ORDER.payment_amount
  is '应付款等价金额(单位:分)';
comment on column T_PAY_ORDER.status
  is '状态, -1异常、0未执行、1执行中、2成功、3失败';
comment on column T_PAY_ORDER.status_text
  is '状态文字说明';
comment on column T_PAY_ORDER.create_time
  is '创建时间';
comment on column T_PAY_ORDER.update_time
  is '状态更新时间';
comment on column T_PAY_ORDER.remark
  is '订单备注';
comment on column T_PAY_ORDER.charge_time
  is '支付平台回调中的“支付/退款”的时间';
create index IDX__PAY_ORDER_01 on T_PAY_ORDER (ACT_ORDER_ID);
create index IDX__PAY_ORDER_02 on T_PAY_ORDER (PAY_ORDER_ID);
alter table T_PAY_ORDER
  add constraint PK_PAY_ORDER primary key (ID);

prompt
prompt Creating table T_PAY_ORDER_PAYMENT
prompt ==================================
prompt
create table T_PAY_ORDER_PAYMENT
(
  id       NUMBER(19) not null,
  pid      NUMBER(19) not null,
  currency VARCHAR2(10),
  amount   NUMBER(9),
  remark   VARCHAR2(50)
)
;
comment on column T_PAY_ORDER_PAYMENT.id
  is '记录ID';
comment on column T_PAY_ORDER_PAYMENT.pid
  is '主记录ID';
comment on column T_PAY_ORDER_PAYMENT.currency
  is '支付货币，现金cache, 积分score, 商城币coin，...';
comment on column T_PAY_ORDER_PAYMENT.amount
  is '支付数量';
comment on column T_PAY_ORDER_PAYMENT.remark
  is '备注';
create index IDX_PAY_ORDER_PAYMENT on T_PAY_ORDER_PAYMENT (PID);

prompt
prompt Creating table T_PKG_DEBUG_LOG
prompt ==============================
prompt
create table T_PKG_DEBUG_LOG
(
  vtime     DATE,
  log_step  NUMBER(3),
  vtable    VARCHAR2(30),
  op_type   VARCHAR2(8),
  op_result VARCHAR2(500)
)
;

prompt
prompt Creating table T_RED_TMP
prompt ========================
prompt
create table T_RED_TMP
(
  id         NUMBER,
  red_member NUMBER
)
;

prompt
prompt Creating table T_ROUTER_RULE
prompt ============================
prompt
create table T_ROUTER_RULE
(
  sp_code VARCHAR2(22) not null,
  target  VARCHAR2(40) not null
)
;
comment on column T_ROUTER_RULE.sp_code
  is '特服号';
comment on column T_ROUTER_RULE.target
  is '目的队列';

prompt
prompt Creating table T_ROUTER_RULE_SPECIAL
prompt ====================================
prompt
create table T_ROUTER_RULE_SPECIAL
(
  sp_code        VARCHAR2(21) not null,
  command        VARCHAR2(20),
  target_sp_code VARCHAR2(21),
  target_command VARCHAR2(20)
)
;
comment on column T_ROUTER_RULE_SPECIAL.sp_code
  is '特服号';
comment on column T_ROUTER_RULE_SPECIAL.command
  is '指令，为空时匹配所有指令';
comment on column T_ROUTER_RULE_SPECIAL.target_sp_code
  is '目标特服号，为空时无变化';
comment on column T_ROUTER_RULE_SPECIAL.target_command
  is '目标指令，为空时无变化';

prompt
prompt Creating table T_SALE_PAYTYPE_TMP
prompt =================================
prompt
create table T_SALE_PAYTYPE_TMP
(
  pid           NUMBER,
  coin_idgoods  INTEGER,
  cash_idgoods  INTEGER not null,
  score_idgoods INTEGER not null
)
;

prompt
prompt Creating table T_SALE_REGION_TMP
prompt ================================
prompt
create table T_SALE_REGION_TMP
(
  pid           NUMBER,
  province_code VARCHAR2(50),
  city_code     VARCHAR2(50),
  region_code   VARCHAR2(50)
)
;

prompt
prompt Creating table T_SC_APP_INFO
prompt ============================
prompt
create table T_SC_APP_INFO
(
  id             NUMBER(9) not null,
  app_code       VARCHAR2(50),
  app_name       VARCHAR2(200),
  app_remark     CLOB,
  app_open_url   VARCHAR2(500),
  app_close_url  VARCHAR2(500),
  app_url        VARCHAR2(500),
  status         NUMBER(2) default 1 not null,
  app_contacts   VARCHAR2(200),
  app_tel        VARCHAR2(200),
  app_mail       VARCHAR2(200),
  app_notice_url VARCHAR2(200)
)
;
comment on column T_SC_APP_INFO.app_code
  is '应用识别码';
comment on column T_SC_APP_INFO.app_name
  is '应用名称';
comment on column T_SC_APP_INFO.app_remark
  is '应用说明';
comment on column T_SC_APP_INFO.app_open_url
  is '应用开通链接';
comment on column T_SC_APP_INFO.app_close_url
  is '应用关闭链接';
comment on column T_SC_APP_INFO.app_url
  is '应用访问地址';
comment on column T_SC_APP_INFO.status
  is '1：在用
0：停用';
comment on column T_SC_APP_INFO.app_contacts
  is '联系人';
comment on column T_SC_APP_INFO.app_tel
  is '联系电话';
comment on column T_SC_APP_INFO.app_mail
  is '联系人邮箱';
comment on column T_SC_APP_INFO.app_notice_url
  is '应用透传接口';

prompt
prompt Creating table T_SC_APP_PRODUCT
prompt ===============================
prompt
create table T_SC_APP_PRODUCT
(
  id             NUMBER(9) not null,
  app_id         NUMBER(9),
  product_id     VARCHAR2(50),
  product_name   VARCHAR2(200),
  product_remark VARCHAR2(2000),
  product_price  NUMBER(8,2),
  product_type   NUMBER(2)
)
;
comment on column T_SC_APP_PRODUCT.product_type
  is '1:正常套餐
2:叠加包';
alter table T_SC_APP_PRODUCT
  add constraint PK_T_APP_PRODUCT primary key (ID);

prompt
prompt Creating table T_SC_APP_USE_EVENT
prompt =================================
prompt
create table T_SC_APP_USE_EVENT
(
  id            NUMBER(9) not null,
  app_code      VARCHAR2(50),
  event_type_id VARCHAR2(50),
  event_name    VARCHAR2(50),
  event_remark  VARCHAR2(200)
)
;
comment on column T_SC_APP_USE_EVENT.app_code
  is '应用识别码';
comment on column T_SC_APP_USE_EVENT.event_type_id
  is '事件类型编号';
comment on column T_SC_APP_USE_EVENT.event_name
  is '事件名称';
comment on column T_SC_APP_USE_EVENT.event_remark
  is '描述';
alter table T_SC_APP_USE_EVENT
  add constraint PK_T_SC_APP_SHOP_EVENT primary key (ID);

prompt
prompt Creating table T_SC_APP_USE_LOG
prompt ===============================
prompt
create table T_SC_APP_USE_LOG
(
  id            NUMBER(9) not null,
  app_code      VARCHAR2(50),
  app_name      VARCHAR2(200),
  product_id    VARCHAR2(50),
  product_name  VARCHAR2(200),
  shop_id       NUMBER(9),
  event_type_id VARCHAR2(50),
  event_time    VARCHAR2(14),
  event_value   VARCHAR2(200)
)
;
comment on column T_SC_APP_USE_LOG.id
  is '主键';
comment on column T_SC_APP_USE_LOG.app_code
  is '应用识别码';
comment on column T_SC_APP_USE_LOG.app_name
  is '应用名称';
comment on column T_SC_APP_USE_LOG.product_id
  is '产品ID';
comment on column T_SC_APP_USE_LOG.product_name
  is '产品名称';
comment on column T_SC_APP_USE_LOG.shop_id
  is '商户ID';
comment on column T_SC_APP_USE_LOG.event_type_id
  is '事件类型编号';
comment on column T_SC_APP_USE_LOG.event_time
  is '事件时间';
comment on column T_SC_APP_USE_LOG.event_value
  is '事件值';
alter table T_SC_APP_USE_LOG
  add constraint PK_T_SC_APP_USE_LOG primary key (ID);

prompt
prompt Creating table T_SC_ESOP_SHOP_SYNC_LOG
prompt ======================================
prompt
create table T_SC_ESOP_SHOP_SYNC_LOG
(
  id           NUMBER(9) not null,
  update_time  VARCHAR2(14),
  content      VARCHAR2(1000),
  shop_name    VARCHAR2(200),
  shop_contact VARCHAR2(20),
  tel          VARCHAR2(50),
  email        VARCHAR2(50),
  shop_address VARCHAR2(100)
)
;
comment on column T_SC_ESOP_SHOP_SYNC_LOG.id
  is '主键';
comment on column T_SC_ESOP_SHOP_SYNC_LOG.update_time
  is '同步时间';
comment on column T_SC_ESOP_SHOP_SYNC_LOG.content
  is '原始报文';
comment on column T_SC_ESOP_SHOP_SYNC_LOG.shop_name
  is '商户名称';
comment on column T_SC_ESOP_SHOP_SYNC_LOG.shop_contact
  is '联系人';
comment on column T_SC_ESOP_SHOP_SYNC_LOG.tel
  is '电话';
comment on column T_SC_ESOP_SHOP_SYNC_LOG.email
  is '邮箱';
comment on column T_SC_ESOP_SHOP_SYNC_LOG.shop_address
  is '地址';
alter table T_SC_ESOP_SHOP_SYNC_LOG
  add constraint PK_T_SC_ESOP_SHOP_SYNC_LOG primary key (ID);

prompt
prompt Creating table T_SC_SHOP_ORDER
prompt ==============================
prompt
create table T_SC_SHOP_ORDER
(
  id             NUMBER(9) not null,
  shop_id        NUMBER(9),
  app_id         NUMBER(9),
  product_id     VARCHAR2(50),
  update_time    VARCHAR2(14),
  product_status NUMBER(2),
  end_time       VARCHAR2(14)
)
;
comment on column T_SC_SHOP_ORDER.id
  is '主键';
comment on column T_SC_SHOP_ORDER.shop_id
  is '商户ID';
comment on column T_SC_SHOP_ORDER.app_id
  is '应用编号';
comment on column T_SC_SHOP_ORDER.product_id
  is '业务编号';
comment on column T_SC_SHOP_ORDER.update_time
  is '同步时间';
comment on column T_SC_SHOP_ORDER.product_status
  is '应用状态 1:开通
2:停用
';
comment on column T_SC_SHOP_ORDER.end_time
  is '到期时间';
alter table T_SC_SHOP_ORDER
  add constraint PK_T_SC_USER_ORDER primary key (ID);

prompt
prompt Creating table T_SC_SHOP_ORDER_HISTORY
prompt ======================================
prompt
create table T_SC_SHOP_ORDER_HISTORY
(
  id             NUMBER(9) not null,
  shop_id        NUMBER(9),
  app_id         NUMBER(9),
  product_id     VARCHAR2(50),
  update_time    VARCHAR2(14),
  product_status NUMBER(2),
  end_time       VARCHAR2(14)
)
;
comment on column T_SC_SHOP_ORDER_HISTORY.id
  is '主键';
comment on column T_SC_SHOP_ORDER_HISTORY.shop_id
  is '商户ID';
comment on column T_SC_SHOP_ORDER_HISTORY.app_id
  is '应用编号';
comment on column T_SC_SHOP_ORDER_HISTORY.product_id
  is '业务编号';
comment on column T_SC_SHOP_ORDER_HISTORY.update_time
  is '同步时间';
comment on column T_SC_SHOP_ORDER_HISTORY.product_status
  is '应用状态 1:开通
2:停用
';
comment on column T_SC_SHOP_ORDER_HISTORY.end_time
  is '到期时间';
alter table T_SC_SHOP_ORDER_HISTORY
  add constraint PK_T_SC_USER_ORDER_HISTORY primary key (ID);

prompt
prompt Creating table T_SC_SHOP_ORDER_UNTREDTED
prompt ========================================
prompt
create table T_SC_SHOP_ORDER_UNTREDTED
(
  id             NUMBER(9) not null,
  shop_id        NUMBER(9),
  product_id     VARCHAR2(50),
  update_time    VARCHAR2(14),
  product_status NUMBER(2),
  deal_time      VARCHAR2(14),
  deal_status    NUMBER(2)
)
;
comment on column T_SC_SHOP_ORDER_UNTREDTED.id
  is '主键';
comment on column T_SC_SHOP_ORDER_UNTREDTED.shop_id
  is '商户ID';
comment on column T_SC_SHOP_ORDER_UNTREDTED.product_id
  is '业务编号';
comment on column T_SC_SHOP_ORDER_UNTREDTED.update_time
  is '同步时间';
comment on column T_SC_SHOP_ORDER_UNTREDTED.product_status
  is '应用状态  0:关闭
1:开通
2:暂停
3:恢复';
comment on column T_SC_SHOP_ORDER_UNTREDTED.deal_time
  is '处理时间';
comment on column T_SC_SHOP_ORDER_UNTREDTED.deal_status
  is '处理状态  0：未处理
1：已处理';
alter table T_SC_SHOP_ORDER_UNTREDTED
  add constraint PK_T_SC_USER_ORDER_UNTREDTED primary key (ID);

prompt
prompt Creating table T_SC_SYS_LOG
prompt ===========================
prompt
create table T_SC_SYS_LOG
(
  id          NUMBER(9) not null,
  user_id     VARCHAR2(20) not null,
  oper_time   VARCHAR2(14) not null,
  oper_type   NUMBER(3) not null,
  module      VARCHAR2(100) not null,
  description VARCHAR2(200),
  result_code VARCHAR2(10),
  ip          VARCHAR2(20),
  user_name   VARCHAR2(100),
  user_type   VARCHAR2(20),
  op_id       NUMBER(9)
)
;
comment on table T_SC_SYS_LOG
  is '系统日志表';
comment on column T_SC_SYS_LOG.oper_type
  is '操作类型   1-查看 2-添加 3-修改 4-删除 5-审核  6-其他';
comment on column T_SC_SYS_LOG.result_code
  is '操作是否成功 0-成功  非0 失败';
comment on column T_SC_SYS_LOG.user_name
  is '用户名称';
comment on column T_SC_SYS_LOG.user_type
  is '用户类型';
comment on column T_SC_SYS_LOG.op_id
  is '操作对象ID';

prompt
prompt Creating table T_SC_SYS_MENU
prompt ============================
prompt
create table T_SC_SYS_MENU
(
  menu_code  VARCHAR2(20) not null,
  menu_name  VARCHAR2(50) not null,
  menu_pcode VARCHAR2(20) not null,
  menu_url   VARCHAR2(100),
  leaf_yn    NUMBER(3) not null,
  model_code VARCHAR2(200),
  url_btns   VARCHAR2(100),
  sys_type   NUMBER(1) not null
)
;
comment on table T_SC_SYS_MENU
  is '菜单表';
comment on column T_SC_SYS_MENU.menu_code
  is '菜单code，唯一值';
comment on column T_SC_SYS_MENU.menu_name
  is '菜单名称';
comment on column T_SC_SYS_MENU.menu_pcode
  is '当前菜单父菜单code';
comment on column T_SC_SYS_MENU.menu_url
  is '菜单指向的URL链接，没有链接可以使用#';
comment on column T_SC_SYS_MENU.leaf_yn
  is '是否为末级标志，0-是;1否';
comment on column T_SC_SYS_MENU.model_code
  is '标识模块,最末级菜单需要指明该字段,且在本系统内具有唯一性';
comment on column T_SC_SYS_MENU.url_btns
  is '每个页面所拥有的按钮(add_btn,mod_btn,del_btn)';
comment on column T_SC_SYS_MENU.sys_type
  is '每个系统有自己的菜单：0后台管理系统 1商户自服务业务门店 2商户自服务结算商户 3商户自服务渠道商';

prompt
prompt Creating table T_SC_SYS_REGION
prompt ==============================
prompt
create table T_SC_SYS_REGION
(
  id            NUMBER(9) not null,
  region_code   VARCHAR2(100),
  region_name   VARCHAR2(200),
  region_level  NUMBER(10),
  parent_region VARCHAR2(100) not null,
  short_name    VARCHAR2(50),
  region_spell  VARCHAR2(200),
  is_show       NUMBER(1) default 1,
  sort_num      NUMBER(9) default 99999,
  area_code     VARCHAR2(20)
)
;
comment on table T_SC_SYS_REGION
  is '系统行政区域表';
comment on column T_SC_SYS_REGION.region_code
  is '区域代码';
comment on column T_SC_SYS_REGION.region_name
  is '区域名称';
comment on column T_SC_SYS_REGION.region_level
  is '区域等级';
comment on column T_SC_SYS_REGION.parent_region
  is '父区域
0-一级区域';
comment on column T_SC_SYS_REGION.short_name
  is '简称';
comment on column T_SC_SYS_REGION.region_spell
  is '区域拼音';
comment on column T_SC_SYS_REGION.is_show
  is '是否显示 0-不显示 1-显示';
comment on column T_SC_SYS_REGION.sort_num
  is '排序';
comment on column T_SC_SYS_REGION.area_code
  is '地市码';

prompt
prompt Creating table T_SC_SYS_ROLE
prompt ============================
prompt
create table T_SC_SYS_ROLE
(
  id             NUMBER(9) not null,
  remark         VARCHAR2(200),
  update_user_id NUMBER(9),
  update_time    VARCHAR2(14),
  role_name      VARCHAR2(50 CHAR) not null,
  unit_id        VARCHAR2(20 CHAR)
)
;
comment on table T_SC_SYS_ROLE
  is '角色主表';
comment on column T_SC_SYS_ROLE.id
  is 'ID，使用序列SEQ_COMM_ID';
comment on column T_SC_SYS_ROLE.remark
  is '备注';
comment on column T_SC_SYS_ROLE.update_user_id
  is '创建人，匹配T_SYS_USER表中的ID字段';
comment on column T_SC_SYS_ROLE.update_time
  is '更新时间';

prompt
prompt Creating table T_SC_SYS_ROLE_PRIVILEGE
prompt ======================================
prompt
create table T_SC_SYS_ROLE_PRIVILEGE
(
  id        NUMBER(9) not null,
  role_id   NUMBER(9),
  menu_code VARCHAR2(20),
  menu_btn  VARCHAR2(100)
)
;
comment on table T_SC_SYS_ROLE_PRIVILEGE
  is '[关于权限按钮列表]:
系统有固化的权限按钮,新增,修改,删除,查看,审核..
名称对应:ADD_BTN,MOD_BTN,DEL_BTN,VIEW_BTN,AUDIT_BTN...';
comment on column T_SC_SYS_ROLE_PRIVILEGE.id
  is 'ID';
comment on column T_SC_SYS_ROLE_PRIVILEGE.menu_btn
  is '所拥有的按钮访问列表,格式如:ADD_BTN,MOD_BTN,DEL_BTN';

prompt
prompt Creating table T_SC_SYS_UNIT
prompt ============================
prompt
create table T_SC_SYS_UNIT
(
  id             NUMBER(9) not null,
  name           VARCHAR2(100) not null,
  area_code      VARCHAR2(8),
  unit_type      NUMBER(1) not null,
  remark         VARCHAR2(200),
  flag           NUMBER(1) not null,
  update_user_id NUMBER(9) not null,
  update_time    VARCHAR2(14) not null,
  prov_id        NUMBER(9),
  parent_unit_id NUMBER(9)
)
;
comment on column T_SC_SYS_UNIT.id
  is '单位ID';
comment on column T_SC_SYS_UNIT.name
  is '单位名称';
comment on column T_SC_SYS_UNIT.area_code
  is '单位归属地市，匹配T_SYS_regon表中AREA_CODE';
comment on column T_SC_SYS_UNIT.unit_type
  is '单位分类，1-电商基地单位
2-省单位
3-地市单位';
comment on column T_SC_SYS_UNIT.remark
  is '备注';
comment on column T_SC_SYS_UNIT.flag
  is '单位标识，0表示正常，9表示注销';
comment on column T_SC_SYS_UNIT.update_user_id
  is '单位变更用户ID，匹配T_SYS_USER表中ID';
comment on column T_SC_SYS_UNIT.update_time
  is '单位更新时间';
comment on column T_SC_SYS_UNIT.prov_id
  is '单位所属省ID';

prompt
prompt Creating table T_SC_SYS_USER
prompt ============================
prompt
create table T_SC_SYS_USER
(
  id              NUMBER(9) not null,
  user_code       VARCHAR2(20) not null,
  user_pwd        VARCHAR2(60) not null,
  user_name       VARCHAR2(100) not null,
  terminal_id     VARCHAR2(30),
  email           VARCHAR2(100),
  remark          VARCHAR2(200),
  valid_time      VARCHAR2(8),
  create_time     VARCHAR2(14) not null,
  change_pwd_time VARCHAR2(14),
  update_time     VARCHAR2(14) not null,
  update_user_id  NUMBER(9) not null,
  lock_status     NUMBER(3) default 0 not null,
  status          NUMBER(3) default 1 not null,
  flag            NUMBER(3) not null,
  unit_id         NUMBER(9) default 0 not null
)
;
comment on table T_SC_SYS_USER
  is '1.系统所有用户在一张表内维护

2.用户类别的说明:运营商增加本单位用户时,需选择[2]-普通用户,[3]-客服用户
  客户系统人员有固化的权限.';
comment on column T_SC_SYS_USER.id
  is 'ID';
comment on column T_SC_SYS_USER.user_code
  is '全平台唯一，用户帐号，用于登录';
comment on column T_SC_SYS_USER.user_pwd
  is '密码';
comment on column T_SC_SYS_USER.user_name
  is '用户真实姓名';
comment on column T_SC_SYS_USER.terminal_id
  is '终端号码';
comment on column T_SC_SYS_USER.email
  is 'EMAIL地址';
comment on column T_SC_SYS_USER.remark
  is '描述';
comment on column T_SC_SYS_USER.valid_time
  is '帐户有效期，8位日期，如果为空则表示永久有效';
comment on column T_SC_SYS_USER.create_time
  is '用户创建时间';
comment on column T_SC_SYS_USER.change_pwd_time
  is '用户修改密码时间';
comment on column T_SC_SYS_USER.update_time
  is '账号更新时间';
comment on column T_SC_SYS_USER.update_user_id
  is '更新用户ID，匹配T_SYS_USER_V2表中的ID字段';
comment on column T_SC_SYS_USER.lock_status
  is '帐号锁定状态：0,正常;1,锁定';
comment on column T_SC_SYS_USER.status
  is '[1]:正常,[2]:暂停,[3]:删除';
comment on column T_SC_SYS_USER.flag
  is '[0]:超级管理员,[1]:单位管理员 [2]单位普通人员 [3]';
comment on column T_SC_SYS_USER.unit_id
  is '该用户归属单位，匹配表T_UNIT中ID';

prompt
prompt Creating table T_SC_SYS_USER_REGION
prompt ===================================
prompt
create table T_SC_SYS_USER_REGION
(
  id          NUMBER(9) not null,
  user_id     NUMBER(9),
  region_code VARCHAR2(100)
)
;
comment on table T_SC_SYS_USER_REGION
  is '系统用户区域表
该表主要是针对数据权限进行管理。
该用户可以操作的区域进行管理';

prompt
prompt Creating table T_SC_SYS_USER_ROLE
prompt =================================
prompt
create table T_SC_SYS_USER_ROLE
(
  user_id NUMBER(9) not null,
  role_id NUMBER(9) not null,
  id      NUMBER(9) not null
)
;
comment on table T_SC_SYS_USER_ROLE
  is '用户与角色多对多关系';
comment on column T_SC_SYS_USER_ROLE.user_id
  is '用户ID';
comment on column T_SC_SYS_USER_ROLE.role_id
  is '角色ID';

prompt
prompt Creating table T_SHOP
prompt =====================
prompt
create table T_SHOP
(
  id              NUMBER(8) not null,
  ac_shop_id      NUMBER(8),
  name            VARCHAR2(50),
  short_name      VARCHAR2(20),
  area_code       VARCHAR2(10),
  sort            NUMBER(1),
  discount_detail VARCHAR2(100),
  is_chain        NUMBER(1),
  is_base_shop    NUMBER(1),
  base_shop_name  VARCHAR2(50),
  create_time     VARCHAR2(14),
  update_time     VARCHAR2(14),
  start_time      VARCHAR2(14),
  stop_time       VARCHAR2(14),
  address         VARCHAR2(100),
  phone           VARCHAR2(50),
  open_time       VARCHAR2(50),
  bus_line        VARCHAR2(50),
  area            VARCHAR2(20),
  park_place      VARCHAR2(20),
  avg_spend       NUMBER(8),
  room_num        VARCHAR2(50),
  map_long        VARCHAR2(20),
  map_dim         VARCHAR2(20),
  shop_user_id    NUMBER(9),
  status          NUMBER(9),
  is_valid        NUMBER(1),
  shop_class      NUMBER(1),
  logo            VARCHAR2(100),
  remark          CLOB
)
;
comment on table T_SHOP
  is '存储业务门店相关信息';
comment on column T_SHOP.ac_shop_id
  is '结算商户编号，可以为空';
comment on column T_SHOP.name
  is '商户名称';
comment on column T_SHOP.short_name
  is '商户简称';
comment on column T_SHOP.area_code
  is '归属地市';
comment on column T_SHOP.sort
  is '0--非签约
1--签约';
comment on column T_SHOP.discount_detail
  is '折扣内容';
comment on column T_SHOP.is_chain
  is '是否连锁
0-否
1-是';
comment on column T_SHOP.is_base_shop
  is '是否总店
0-否
1-是';
comment on column T_SHOP.base_shop_name
  is '总店名称';
comment on column T_SHOP.create_time
  is '创建时间';
comment on column T_SHOP.update_time
  is '更新时间';
comment on column T_SHOP.start_time
  is '有效开始时间';
comment on column T_SHOP.stop_time
  is '有效结束时间';
comment on column T_SHOP.address
  is '商户地址';
comment on column T_SHOP.phone
  is '联系电话';
comment on column T_SHOP.open_time
  is '营业时间';
comment on column T_SHOP.bus_line
  is '公交线路';
comment on column T_SHOP.area
  is '面积';
comment on column T_SHOP.park_place
  is '停车位';
comment on column T_SHOP.avg_spend
  is '人均消费';
comment on column T_SHOP.room_num
  is '包间数';
comment on column T_SHOP.map_long
  is '地图坐标经度';
comment on column T_SHOP.map_dim
  is '地图坐标维度';
comment on column T_SHOP.shop_user_id
  is '创建商户账号编号';
comment on column T_SHOP.status
  is '0--草稿
1--待审核
2--审核中
3--审核通过
4--审核驳回';
comment on column T_SHOP.is_valid
  is ' 商户是否有效 0--下架
1--上架';
comment on column T_SHOP.shop_class
  is ' 1--业务门店
2--结算商户
3--渠道商
此处默认1';
comment on column T_SHOP.logo
  is '门店logo图路径';
comment on column T_SHOP.remark
  is '详细介绍';
alter table T_SHOP
  add constraint PK_T_SHOP primary key (ID);

prompt
prompt Creating table T_SHOP_COMMENT
prompt =============================
prompt
create table T_SHOP_COMMENT
(
  id          NUMBER(8),
  content     VARCHAR2(200),
  update_time VARCHAR2(14),
  user_id     NUMBER(8),
  status      NUMBER(1),
  shop_class  NUMBER(1),
  shop_id     NUMBER(8)
)
;
comment on column T_SHOP_COMMENT.status
  is '0-未审核
1-审核通过
2-审核驳回';
comment on column T_SHOP_COMMENT.shop_class
  is '1-业务门店
2-商户
3-渠道商';

prompt
prompt Creating table T_SHOP_CUSTOMER_SERVICE
prompt ======================================
prompt
create table T_SHOP_CUSTOMER_SERVICE
(
  id         NUMBER(9) not null,
  nick_name  VARCHAR2(50),
  code       VARCHAR2(2000),
  shop_class NUMBER(1),
  shop_id    NUMBER(9),
  status     NUMBER(1)
)
;
comment on column T_SHOP_CUSTOMER_SERVICE.nick_name
  is '客服昵称';
comment on column T_SHOP_CUSTOMER_SERVICE.code
  is '飞信代码';
comment on column T_SHOP_CUSTOMER_SERVICE.shop_class
  is '"1--业务门店
2--商户
3--渠道商"';
comment on column T_SHOP_CUSTOMER_SERVICE.status
  is '1 -有效 0-无效';
alter table T_SHOP_CUSTOMER_SERVICE
  add constraint PK_T_SHOP_CUSTOMER_SERVICE primary key (ID);

prompt
prompt Creating table T_SHOP_DELIVERY_MODE
prompt ===================================
prompt
create table T_SHOP_DELIVERY_MODE
(
  id           NUMBER(9) not null,
  logistics_id NUMBER(9),
  shop_class   NUMBER(1),
  shop_id      NUMBER(9),
  fee          NUMBER(9,2)
)
;
comment on table T_SHOP_DELIVERY_MODE
  is '物流配送方式';
comment on column T_SHOP_DELIVERY_MODE.id
  is 'ID';
comment on column T_SHOP_DELIVERY_MODE.logistics_id
  is '物流信息表ID';
comment on column T_SHOP_DELIVERY_MODE.shop_class
  is '1--业务门店
2--商户
3--渠道商';
comment on column T_SHOP_DELIVERY_MODE.fee
  is '运费';
alter table T_SHOP_DELIVERY_MODE
  add constraint PK_T_SHOP_DELIVERY_MODE primary key (ID);

prompt
prompt Creating table T_SHOP_EXT
prompt =========================
prompt
create table T_SHOP_EXT
(
  id          NUMBER(8) not null,
  shop_id     NUMBER(8),
  comment_num NUMBER(8),
  s_level     NUMBER(8),
  fz_store_id NUMBER(8),
  fz_shop_id  NUMBER(8)
)
;
comment on table T_SHOP_EXT
  is '业务门店表的扩展字段表，和t_shop_bs 关系1:1';
comment on column T_SHOP_EXT.s_level
  is '1/2/3/4/5星';
comment on column T_SHOP_EXT.fz_store_id
  is '商户方正同步ID';
comment on column T_SHOP_EXT.fz_shop_id
  is '门店方正同步ID';
alter table T_SHOP_EXT
  add constraint PK_T_SHOP_EXT primary key (ID);

prompt
prompt Creating table T_SHOP_HOMEPAGE_SHOW
prompt ===================================
prompt
create table T_SHOP_HOMEPAGE_SHOW
(
  id           NUMBER(9) not null,
  title        VARCHAR2(100),
  shelf_id     NUMBER(9),
  good_num     NUMBER(3),
  status       NUMBER(1),
  order_index  NUMBER(1),
  shop_class   NUMBER(1),
  shop_id      NUMBER(9),
  shop_user_id NUMBER(9),
  update_time  VARCHAR2(14)
)
;
comment on table T_SHOP_HOMEPAGE_SHOW
  is '首页商品展示配置表';
comment on column T_SHOP_HOMEPAGE_SHOW.title
  is '标题';
comment on column T_SHOP_HOMEPAGE_SHOW.shelf_id
  is '货架ID';
comment on column T_SHOP_HOMEPAGE_SHOW.good_num
  is '商品数量';
comment on column T_SHOP_HOMEPAGE_SHOW.status
  is '状态：0，停用；1，启用';
comment on column T_SHOP_HOMEPAGE_SHOW.order_index
  is '排序';
comment on column T_SHOP_HOMEPAGE_SHOW.shop_class
  is '商户分类：1--业务门店
2--结算商户
3--渠道商';
comment on column T_SHOP_HOMEPAGE_SHOW.shop_id
  is '商户编号';
comment on column T_SHOP_HOMEPAGE_SHOW.shop_user_id
  is '商户账号ID';
comment on column T_SHOP_HOMEPAGE_SHOW.update_time
  is '更新时间';
alter table T_SHOP_HOMEPAGE_SHOW
  add constraint T_SHOP_HOMEPAGE_SHOW_ID primary key (ID);

prompt
prompt Creating table T_SHOP_HOMEPAGE_SHOW_1
prompt =====================================
prompt
create table T_SHOP_HOMEPAGE_SHOW_1
(
  id           NUMBER(9) not null,
  title        VARCHAR2(100),
  shelf_id     NUMBER(9),
  good_num     NUMBER(3),
  status       NUMBER(1),
  order_index  NUMBER(1),
  shop_class   NUMBER(1),
  shop_id      NUMBER(9),
  shop_user_id NUMBER(9),
  update_time  VARCHAR2(14),
  store_id     VARCHAR2(30)
)
;

prompt
prompt Creating table T_SHOP_INVOICE
prompt =============================
prompt
create table T_SHOP_INVOICE
(
  id           NUMBER(9) not null,
  shop_class   NUMBER(1),
  shop_id      NUMBER(9),
  invoice_id   NUMBER(9),
  invoice_name VARCHAR2(40)
)
;
comment on table T_SHOP_INVOICE
  is '商户发票表';
comment on column T_SHOP_INVOICE.shop_class
  is '商户类型';
comment on column T_SHOP_INVOICE.shop_id
  is '商户id';
comment on column T_SHOP_INVOICE.invoice_id
  is '发票id';
comment on column T_SHOP_INVOICE.invoice_name
  is '发票名';
alter table T_SHOP_INVOICE
  add constraint PK_SHOP_INVOICE primary key (ID);

prompt
prompt Creating table T_SHOP_LOGIN_LOG
prompt ===============================
prompt
create table T_SHOP_LOGIN_LOG
(
  id           NUMBER(9) not null,
  user_name    VARCHAR2(20),
  user_id      NUMBER(9),
  ip           VARCHAR2(15),
  login_time   VARCHAR2(14),
  success_flag NUMBER(2),
  user_code    VARCHAR2(20),
  shop_id      NUMBER(9),
  shop_class   NUMBER(1),
  shop_name    VARCHAR2(100)
)
;
comment on column T_SHOP_LOGIN_LOG.success_flag
  is '1-成功 0-失败';

prompt
prompt Creating table T_SHOP_OP_LOG
prompt ============================
prompt
create table T_SHOP_OP_LOG
(
  id          NUMBER(9) not null,
  user_id     VARCHAR2(20) not null,
  oper_time   VARCHAR2(14) not null,
  oper_type   NUMBER(3) not null,
  module      VARCHAR2(100) not null,
  description VARCHAR2(1000),
  result_code VARCHAR2(10),
  ip          VARCHAR2(20),
  user_name   VARCHAR2(100),
  op_id       NUMBER(16),
  shop_id     NUMBER(9),
  shop_class  NUMBER(1),
  shop_name   VARCHAR2(100),
  user_code   VARCHAR2(20)
)
;
comment on column T_SHOP_OP_LOG.oper_type
  is '操作类型   1-查看 2-添加 3-修改 4-删除 5-审核  6-其他';
comment on column T_SHOP_OP_LOG.result_code
  is '操作是否成功 0-成功  非0 失败';
comment on column T_SHOP_OP_LOG.user_name
  is '用户名称';
comment on column T_SHOP_OP_LOG.op_id
  is '操作对象ID';
comment on column T_SHOP_OP_LOG.shop_id
  is '商户ID';
comment on column T_SHOP_OP_LOG.shop_class
  is '商户类别';
comment on column T_SHOP_OP_LOG.shop_name
  is '商户名';
comment on column T_SHOP_OP_LOG.user_code
  is '用户帐号';

prompt
prompt Creating table T_SHOP_POS_LINK
prompt ==============================
prompt
create table T_SHOP_POS_LINK
(
  id          NUMBER(8) not null,
  shop_id     NUMBER(8),
  pos_id      NUMBER(8),
  pos_no      VARCHAR2(50),
  terminal_id VARCHAR2(50)
)
;
comment on table T_SHOP_POS_LINK
  is '业务门店终端关系表，每个门店可以配置多个终端';
comment on column T_SHOP_POS_LINK.terminal_id
  is '终端标识';
alter table T_SHOP_POS_LINK
  add constraint PK_T_SHOP_POS_LINK primary key (ID);

prompt
prompt Creating table T_SHOP_REGION
prompt ============================
prompt
create table T_SHOP_REGION
(
  id          NUMBER(9) not null,
  region_code VARCHAR2(100),
  shop_class  NUMBER(1),
  shop_id     NUMBER(9)
)
;
comment on column T_SHOP_REGION.region_code
  is '区域编码';
comment on column T_SHOP_REGION.shop_class
  is '商户分类：1-业务门店；2-结算商户；3-渠道商';
comment on column T_SHOP_REGION.shop_id
  is '商户编号';

prompt
prompt Creating table T_SHOP_SETTINGS
prompt ==============================
prompt
create table T_SHOP_SETTINGS
(
  id               NUMBER(9) not null,
  shop_pic_url     VARCHAR2(100),
  homepage_word    CLOB,
  shop_class       NUMBER(1),
  shop_id          NUMBER(9),
  pic_name         VARCHAR2(100),
  pub_tag          NUMBER(1) default 0,
  operate_end_time VARCHAR2(14)
)
;
comment on table T_SHOP_SETTINGS
  is '商户设置信息';
comment on column T_SHOP_SETTINGS.shop_pic_url
  is '商户招牌图片路径';
comment on column T_SHOP_SETTINGS.homepage_word
  is '首页文字介绍';
comment on column T_SHOP_SETTINGS.shop_class
  is '1--业务门店
2--商户
3--渠道商';
comment on column T_SHOP_SETTINGS.pic_name
  is '图片名称';
comment on column T_SHOP_SETTINGS.pub_tag
  is '发布状态。 0未发布 1已发布 修改任意网店设置后此字段为 0 审核后为1';
comment on column T_SHOP_SETTINGS.operate_end_time
  is '最后操作时间';
alter table T_SHOP_SETTINGS
  add constraint T_SHOP_SETTINGS_ID primary key (ID);

prompt
prompt Creating table T_SHOP_SETTINGS_1
prompt ================================
prompt
create table T_SHOP_SETTINGS_1
(
  id            NUMBER(9) not null,
  shop_pic_url  VARCHAR2(100),
  homepage_word CLOB,
  shop_class    NUMBER(1),
  shop_id       NUMBER(9),
  pic_name      VARCHAR2(100),
  xw_store_id   VARCHAR2(32)
)
;

prompt
prompt Creating table T_SHOP_TAG
prompt =========================
prompt
create table T_SHOP_TAG
(
  id         NUMBER(8) not null,
  shop_class NUMBER(1),
  shop_id    NUMBER(8),
  tag        VARCHAR2(20)
)
;
comment on table T_SHOP_TAG
  is '存放商户打签标识，纯文字';
comment on column T_SHOP_TAG.shop_class
  is '1--业务门店
2--商户
3--渠道商';
alter table T_SHOP_TAG
  add constraint PK_T_SHOP_TAG primary key (ID);

prompt
prompt Creating table T_SHOP_TYPE_LINK
prompt ===============================
prompt
create table T_SHOP_TYPE_LINK
(
  id      NUMBER(8) not null,
  shop_id NUMBER(8),
  type_id NUMBER(8)
)
;
comment on table T_SHOP_TYPE_LINK
  is '存储业务门店分类关系表
1业务门店对应N个商户分类';
comment on column T_SHOP_TYPE_LINK.shop_id
  is '可以为空';
alter table T_SHOP_TYPE_LINK
  add constraint PK_T_SHOP_TYPE_LINK primary key (ID);

prompt
prompt Creating table T_SHOP_USER
prompt ==========================
prompt
create table T_SHOP_USER
(
  id          NUMBER(8) not null,
  code        VARCHAR2(100),
  pwd         VARCHAR2(32),
  status      NUMBER(1),
  update_time VARCHAR2(14),
  type        NUMBER(1),
  email       VARCHAR2(100),
  mobile      VARCHAR2(20),
  nick_name   VARCHAR2(20),
  shop_class  NUMBER(1),
  shop_id     NUMBER(9)
)
;
comment on table T_SHOP_USER
  is '渠道商、结算商户、业务门店账号表';
comment on column T_SHOP_USER.status
  is '0-失效
1-有效
2-暂停';
comment on column T_SHOP_USER.type
  is '1-管理员
2-普通用户
3-操作员';
comment on column T_SHOP_USER.shop_class
  is '"1--业务门店
2--商户
3--渠道商"';
alter table T_SHOP_USER
  add constraint PK_T_SHOP_USER primary key (ID);

prompt
prompt Creating table T_SHOP_USER_LINK
prompt ===============================
prompt
create table T_SHOP_USER_LINK
(
  id           NUMBER(8) not null,
  shop_id      NUMBER(8),
  shop_class   NUMBER(1),
  shop_user_id NUMBER(8)
)
;
comment on table T_SHOP_USER_LINK
  is '商户及对应账号关联表';
comment on column T_SHOP_USER_LINK.shop_class
  is '1--业务门店
2--商户
3--渠道商';
alter table T_SHOP_USER_LINK
  add constraint PK_T_SHOP_USER_LINK primary key (ID);

prompt
prompt Creating table T_SHOP_USER_ROLE
prompt ===============================
prompt
create table T_SHOP_USER_ROLE
(
  id      NUMBER(9) not null,
  user_id NUMBER(9),
  role_id NUMBER(9)
)
;
comment on column T_SHOP_USER_ROLE.user_id
  is '商户帐号';
comment on column T_SHOP_USER_ROLE.role_id
  is '角色id';

prompt
prompt Creating table T_SMSBUY_ACT_ONLINE
prompt ==================================
prompt
create table T_SMSBUY_ACT_ONLINE
(
  act_id     NUMBER(12) not null,
  act_name   VARCHAR2(50) not null,
  act_desc   VARCHAR2(500),
  act_area   VARCHAR2(100),
  start_time CHAR(14),
  end_time   CHAR(14),
  status     VARCHAR2(15),
  store_id   NUMBER(8),
  is_hot     NUMBER(1) default 0 not null,
  sp_code    VARCHAR2(20)
)
;
comment on column T_SMSBUY_ACT_ONLINE.act_area
  is '0表示全省。
地市用区号表示，多个地市用逗号分隔。
';
comment on column T_SMSBUY_ACT_ONLINE.status
  is 'audit:待审核 online:审核通过 rebutAudit:审核驳回';
comment on column T_SMSBUY_ACT_ONLINE.is_hot
  is '是否是热门活动';
comment on column T_SMSBUY_ACT_ONLINE.sp_code
  is '特服号';
create index Index_SMSBUY_ACT_ONLINE on T_SMSBUY_ACT_ONLINE (START_TIME, END_TIME);
alter table T_SMSBUY_ACT_ONLINE
  add constraint PK_T_SMSBUY_ACT_ONLINE primary key (ACT_ID);

prompt
prompt Creating table T_SMSBUY_HELP
prompt ============================
prompt
create table T_SMSBUY_HELP
(
  help_spcode      VARCHAR2(20) not null,
  help_area        VARCHAR2(100) not null,
  recommend_type   VARCHAR2(10),
  recommend_spcode VARCHAR2(20),
  recommend_sms    VARCHAR2(300)
)
;
comment on column T_SMSBUY_HELP.help_spcode
  is '可模糊匹配，精确匹配优先';
comment on column T_SMSBUY_HELP.recommend_type
  is 'priority：按优先级
hot：按最新商品
manual：按人工推荐';
alter table T_SMSBUY_HELP
  add constraint PK_T_SMSBUY_HELP primary key (HELP_SPCODE, HELP_AREA);

prompt
prompt Creating table T_SMSBUY_ITEM_ONLINE
prompt ===================================
prompt
create table T_SMSBUY_ITEM_ONLINE
(
  item_id     VARCHAR2(30) not null,
  item_name   VARCHAR2(50),
  auditor_id  VARCHAR2(20),
  advice      VARCHAR2(100),
  opt_id      VARCHAR2(20),
  opt_time    CHAR(14),
  quato_buy   NUMBER(9),
  reply_buy   VARCHAR2(300),
  quato_store NUMBER(9),
  reply_store VARCHAR2(300)
)
;
comment on column T_SMSBUY_ITEM_ONLINE.auditor_id
  is '放空';
comment on column T_SMSBUY_ITEM_ONLINE.advice
  is '放空';
comment on column T_SMSBUY_ITEM_ONLINE.quato_buy
  is '0，不限购';
comment on column T_SMSBUY_ITEM_ONLINE.quato_store
  is '0，库存不限';
alter table T_SMSBUY_ITEM_ONLINE
  add constraint PK_T_SMSBUY_ITEM_ONLINE primary key (ITEM_ID);

prompt
prompt Creating table T_SMSBUY_ITEM_ROUTER
prompt ===================================
prompt
create table T_SMSBUY_ITEM_ROUTER
(
  id                NUMBER(12) not null,
  act_id            NUMBER(12) not null,
  item_id           VARCHAR2(30) not null,
  item_name         VARCHAR2(200),
  sp_code           VARCHAR2(20),
  cmd_opt_type      NUMBER(2),
  command           VARCHAR2(50),
  pay_type          NUMBER(1),
  item_price        NUMBER(9),
  is_session        NUMBER(1),
  priority          NUMBER(5) default 100 not null,
  is_recommond      NUMBER(1) default 0 not null,
  recommond_content VARCHAR2(100),
  marketmsg         VARCHAR2(300),
  item_status       VARCHAR2(15)
)
;
comment on column T_SMSBUY_ITEM_ROUTER.cmd_opt_type
  is '1：入口指令
2：其它指令';
comment on column T_SMSBUY_ITEM_ROUTER.command
  is '正则指令内容';
comment on column T_SMSBUY_ITEM_ROUTER.pay_type
  is '1：积分
2：商城币';
comment on column T_SMSBUY_ITEM_ROUTER.is_session
  is '0：不需要
1：需要';
comment on column T_SMSBUY_ITEM_ROUTER.priority
  is '优先级，0最高，99999最低';
comment on column T_SMSBUY_ITEM_ROUTER.is_recommond
  is '是否推荐，0否1是';
comment on column T_SMSBUY_ITEM_ROUTER.recommond_content
  is '推荐内容，当推荐此商品时，用此字段内容替换回复语中相关通配符';
comment on column T_SMSBUY_ITEM_ROUTER.marketmsg
  is '商品营销短信';
comment on column T_SMSBUY_ITEM_ROUTER.item_status
  is 'audit:待审核 online:审核通过 rebutAudit:审核驳回';
create index Index_SMSBUY_ITEM_ROUTER on T_SMSBUY_ITEM_ROUTER (ACT_ID, ITEM_ID);
alter table T_SMSBUY_ITEM_ROUTER
  add constraint PK_T_SMSBUY_ITEM_ROUTER primary key (ID);

prompt
prompt Creating table T_SMSBUY_ORDER_INFO
prompt ==================================
prompt
create table T_SMSBUY_ORDER_INFO
(
  order_id    NUMBER(12) not null,
  terminal_id VARCHAR2(21) not null,
  act_id      NUMBER(12),
  sp_code     VARCHAR2(21) not null,
  update_time VARCHAR2(14) not null
)
;
comment on column T_SMSBUY_ORDER_INFO.order_id
  is '订单编号';
comment on column T_SMSBUY_ORDER_INFO.terminal_id
  is '号码';
comment on column T_SMSBUY_ORDER_INFO.act_id
  is '所属活动编号';
comment on column T_SMSBUY_ORDER_INFO.sp_code
  is '上行特服号';
comment on column T_SMSBUY_ORDER_INFO.update_time
  is '更新时间';
alter table T_SMSBUY_ORDER_INFO
  add constraint PK_SMSBUY_ORDER_INFO primary key (ORDER_ID);

prompt
prompt Creating table T_SMSBUY_USER_ADDRESS
prompt ====================================
prompt
create table T_SMSBUY_USER_ADDRESS
(
  terminal_id VARCHAR2(22) not null,
  address     VARCHAR2(500),
  update_time VARCHAR2(14)
)
;
comment on column T_SMSBUY_USER_ADDRESS.terminal_id
  is '号码';
comment on column T_SMSBUY_USER_ADDRESS.address
  is '送货地址';
comment on column T_SMSBUY_USER_ADDRESS.update_time
  is '更新时间';

prompt
prompt Creating table T_SMS_ACT_ONLINE
prompt ===============================
prompt
create table T_SMS_ACT_ONLINE
(
  act_id     NUMBER(12) not null,
  act_name   VARCHAR2(50) not null,
  act_desc   VARCHAR2(500),
  group_id   VARCHAR2(10),
  start_time CHAR(14),
  end_time   CHAR(14),
  status     VARCHAR2(10)
)
;
comment on column T_SMS_ACT_ONLINE.status
  is 'audit：待审核
release：待发布
online：商用
pause：暂停
offline：下线';
alter table T_SMS_ACT_ONLINE
  add constraint PK_T_SMS_ACT_ONLINE primary key (ACT_ID);

prompt
prompt Creating table T_SMS_ACT_ROUTER
prompt ===============================
prompt
create table T_SMS_ACT_ROUTER
(
  id           NUMBER(12) not null,
  act_id       NUMBER(12) not null,
  sp_code      VARCHAR2(20),
  cmd_opt_type NUMBER(2),
  command      VARCHAR2(50),
  pay_type     NUMBER(1),
  item_price   NUMBER(9),
  is_session   NUMBER(1)
)
;
comment on column T_SMS_ACT_ROUTER.cmd_opt_type
  is '1：入口指令
2：其它指令';
comment on column T_SMS_ACT_ROUTER.command
  is '正则指令内容';
comment on column T_SMS_ACT_ROUTER.pay_type
  is '0：不需要支付
1：积分
2：商城币
';
comment on column T_SMS_ACT_ROUTER.is_session
  is '0：不需要
1：需要';
alter table T_SMS_ACT_ROUTER
  add constraint PK_T_SMS_ACT_ROUTER primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_01
prompt =================================
prompt
create table T_SMS_BATCH_LOG_01
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_01
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_01.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_01.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_01.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_01.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_01
  add constraint PK_T_SMS_BATCH_LOG_01 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_02
prompt =================================
prompt
create table T_SMS_BATCH_LOG_02
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_02
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_02.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_02.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_02.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_02.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_02
  add constraint PK_T_SMS_BATCH_LOG_02 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_03
prompt =================================
prompt
create table T_SMS_BATCH_LOG_03
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_03
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_03.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_03.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_03.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_03.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_03
  add constraint PK_T_SMS_BATCH_LOG_03 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_04
prompt =================================
prompt
create table T_SMS_BATCH_LOG_04
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_04
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_04.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_04.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_04.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_04.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_04
  add constraint PK_T_SMS_BATCH_LOG_04 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_05
prompt =================================
prompt
create table T_SMS_BATCH_LOG_05
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_05
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_05.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_05.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_05.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_05.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_05
  add constraint PK_T_SMS_BATCH_LOG_05 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_06
prompt =================================
prompt
create table T_SMS_BATCH_LOG_06
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_06
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_06.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_06.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_06.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_06.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_06
  add constraint PK_T_SMS_BATCH_LOG_06 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_07
prompt =================================
prompt
create table T_SMS_BATCH_LOG_07
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_07
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_07.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_07.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_07.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_07.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_07
  add constraint PK_T_SMS_BATCH_LOG_07 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_08
prompt =================================
prompt
create table T_SMS_BATCH_LOG_08
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_08
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_08.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_08.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_08.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_08.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_08
  add constraint PK_T_SMS_BATCH_LOG_08 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_09
prompt =================================
prompt
create table T_SMS_BATCH_LOG_09
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_09
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_09.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_09.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_09.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_09.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_09
  add constraint PK_T_SMS_BATCH_LOG_09 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_10
prompt =================================
prompt
create table T_SMS_BATCH_LOG_10
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_10
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_10.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_10.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_10.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_10.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_10
  add constraint PK_T_SMS_BATCH_LOG_10 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_11
prompt =================================
prompt
create table T_SMS_BATCH_LOG_11
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_11
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_11.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_11.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_11.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_11.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_11
  add constraint PK_T_SMS_BATCH_LOG_11 primary key (ID);

prompt
prompt Creating table T_SMS_BATCH_LOG_12
prompt =================================
prompt
create table T_SMS_BATCH_LOG_12
(
  id          NUMBER(9) not null,
  task_id     NUMBER(9),
  terminal_id VARCHAR2(21),
  submit_time VARCHAR2(14),
  status      VARCHAR2(10),
  message_id  VARCHAR2(50)
)
;
comment on table T_SMS_BATCH_LOG_12
  is '彩信群发下行日志表，按月分表存储';
comment on column T_SMS_BATCH_LOG_12.id
  is '序列，递增无实意，主键';
comment on column T_SMS_BATCH_LOG_12.task_id
  is '群发任务ID';
comment on column T_SMS_BATCH_LOG_12.terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_BATCH_LOG_12.message_id
  is '通讯消息ID';
alter table T_SMS_BATCH_LOG_12
  add constraint PK_T_SMS_BATCH_LOG_12 primary key (ID);

prompt
prompt Creating table T_SMS_MOLONG_WAIT
prompt ================================
prompt
create table T_SMS_MOLONG_WAIT
(
  id             NUMBER(9) not null,
  sp_code        VARCHAR2(30),
  src_terminalid VARCHAR2(30),
  msg_content    VARCHAR2(200),
  key            VARCHAR2(200),
  sum_count      NUMBER(9),
  now_order      NUMBER(9),
  linkid         VARCHAR2(30),
  recive_time    VARCHAR2(30),
  area_code      VARCHAR2(30)
)
;
alter table T_SMS_MOLONG_WAIT
  add constraint PK_SMS_MOLONG_WAIT primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_01
prompt ==============================
prompt
create table T_SMS_MO_LOG_01
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_01
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_01.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_01.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_01.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_01.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_01.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_01.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_01.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_01.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_01.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_01.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_01.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_01.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_01.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_01
  add constraint PK_T_SMS_MO_LOG_01 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_02
prompt ==============================
prompt
create table T_SMS_MO_LOG_02
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_02
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_02.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_02.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_02.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_02.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_02.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_02.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_02.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_02.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_02.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_02.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_02.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_02.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_02.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_02
  add constraint PK_T_SMS_MO_LOG_02 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_03
prompt ==============================
prompt
create table T_SMS_MO_LOG_03
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_03
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_03.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_03.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_03.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_03.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_03.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_03.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_03.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_03.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_03.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_03.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_03.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_03.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_03.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_03
  add constraint PK_T_SMS_MO_LOG_03 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_04
prompt ==============================
prompt
create table T_SMS_MO_LOG_04
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_04
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_04.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_04.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_04.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_04.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_04.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_04.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_04.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_04.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_04.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_04.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_04.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_04.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_04.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_04
  add constraint PK_T_SMS_MO_LOG_04 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_05
prompt ==============================
prompt
create table T_SMS_MO_LOG_05
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_05
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_05.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_05.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_05.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_05.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_05.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_05.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_05.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_05.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_05.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_05.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_05.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_05.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_05.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_05
  add constraint PK_T_SMS_MO_LOG_05 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_06
prompt ==============================
prompt
create table T_SMS_MO_LOG_06
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_06
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_06.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_06.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_06.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_06.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_06.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_06.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_06.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_06.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_06.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_06.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_06.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_06.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_06.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_06
  add constraint PK_T_SMS_MO_LOG_06 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_07
prompt ==============================
prompt
create table T_SMS_MO_LOG_07
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_07
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_07.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_07.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_07.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_07.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_07.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_07.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_07.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_07.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_07.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_07.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_07.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_07.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_07.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_07
  add constraint PK_T_SMS_MO_LOG_07 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_08
prompt ==============================
prompt
create table T_SMS_MO_LOG_08
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_08
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_08.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_08.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_08.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_08.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_08.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_08.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_08.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_08.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_08.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_08.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_08.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_08.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_08.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_08
  add constraint PK_T_SMS_MO_LOG_08 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_09
prompt ==============================
prompt
create table T_SMS_MO_LOG_09
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_09
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_09.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_09.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_09.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_09.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_09.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_09.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_09.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_09.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_09.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_09.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_09.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_09.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_09.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_09
  add constraint PK_T_SMS_MO_LOG_09 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_10
prompt ==============================
prompt
create table T_SMS_MO_LOG_10
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_10
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_10.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_10.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_10.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_10.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_10.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_10.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_10.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_10.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_10.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_10.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_10.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_10.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_10.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_10
  add constraint PK_T_SMS_MO_LOG_10 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_11
prompt ==============================
prompt
create table T_SMS_MO_LOG_11
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_11
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_11.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_11.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_11.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_11.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_11.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_11.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_11.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_11.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_11.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_11.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_11.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_11.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_11.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_11
  add constraint PK_T_SMS_MO_LOG_11 primary key (ID);

prompt
prompt Creating table T_SMS_MO_LOG_12
prompt ==============================
prompt
create table T_SMS_MO_LOG_12
(
  id              NUMBER(9) not null,
  msg_content     VARCHAR2(1000),
  src_terminal_id VARCHAR2(21),
  sp_code         VARCHAR2(20),
  get_time        VARCHAR2(14),
  mo_from         NUMBER(3),
  mo_to           NUMBER(3),
  act_id          NUMBER(9),
  done_time       VARCHAR2(14),
  unit_id         VARCHAR2(20),
  area_code       VARCHAR2(8),
  operator_code   VARCHAR2(50),
  linkid          VARCHAR2(20)
)
;
comment on table T_SMS_MO_LOG_12
  is '短信上行日志表，数据由业务处理程序流入';
comment on column T_SMS_MO_LOG_12.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MO_LOG_12.msg_content
  is '短信内容';
comment on column T_SMS_MO_LOG_12.src_terminal_id
  is '发送源手机号码，非空';
comment on column T_SMS_MO_LOG_12.sp_code
  is '特服号，非空';
comment on column T_SMS_MO_LOG_12.get_time
  is '上行接收时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_12.mo_from
  is 'MO来源：
1:通讯
2:短信业务模拟
3:彩信业务模拟
4:WAP模拟
5:WEB模拟
6:USSD模拟
7:同步定购关系模拟
0:未知来源';
comment on column T_SMS_MO_LOG_12.mo_to
  is '1、ACT处理
2、J2EE处理
3、TP接口处理';
comment on column T_SMS_MO_LOG_12.act_id
  is '活动ID';
comment on column T_SMS_MO_LOG_12.done_time
  is '处理此上行的时间（YYYYMMDDHHMMSS）';
comment on column T_SMS_MO_LOG_12.unit_id
  is '单位（第三合作方）编码';
comment on column T_SMS_MO_LOG_12.area_code
  is '地区标识';
comment on column T_SMS_MO_LOG_12.operator_code
  is '运营商标识';
comment on column T_SMS_MO_LOG_12.linkid
  is 'LINKID，保留字段';
alter table T_SMS_MO_LOG_12
  add constraint PK_T_SMS_MO_LOG_12 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_01
prompt ==============================
prompt
create table T_SMS_MT_LOG_01
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_01
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_01.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_01.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_01.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_01.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_01.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_01.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_01.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_01.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_01.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_01.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_01.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_01.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_01.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_01.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_01.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_01.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_01.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_01.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_01.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_01
  add constraint PK_T_SMS_MT_LOG_01 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_02
prompt ==============================
prompt
create table T_SMS_MT_LOG_02
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_02
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_02.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_02.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_02.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_02.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_02.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_02.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_02.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_02.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_02.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_02.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_02.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_02.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_02.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_02.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_02.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_02.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_02.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_02.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_02.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_02
  add constraint PK_T_SMS_MT_LOG_02 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_03
prompt ==============================
prompt
create table T_SMS_MT_LOG_03
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_03
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_03.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_03.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_03.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_03.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_03.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_03.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_03.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_03.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_03.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_03.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_03.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_03.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_03.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_03.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_03.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_03.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_03.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_03.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_03.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_03
  add constraint PK_T_SMS_MT_LOG_03 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_04
prompt ==============================
prompt
create table T_SMS_MT_LOG_04
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_04
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_04.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_04.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_04.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_04.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_04.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_04.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_04.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_04.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_04.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_04.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_04.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_04.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_04.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_04.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_04.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_04.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_04.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_04.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_04.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_04
  add constraint PK_T_SMS_MT_LOG_04 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_05
prompt ==============================
prompt
create table T_SMS_MT_LOG_05
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_05
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_05.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_05.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_05.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_05.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_05.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_05.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_05.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_05.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_05.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_05.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_05.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_05.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_05.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_05.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_05.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_05.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_05.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_05.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_05.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_05
  add constraint PK_T_SMS_MT_LOG_05 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_06
prompt ==============================
prompt
create table T_SMS_MT_LOG_06
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_06
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_06.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_06.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_06.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_06.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_06.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_06.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_06.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_06.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_06.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_06.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_06.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_06.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_06.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_06.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_06.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_06.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_06.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_06.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_06.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_06
  add constraint PK_T_SMS_MT_LOG_06 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_07
prompt ==============================
prompt
create table T_SMS_MT_LOG_07
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_07
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_07.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_07.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_07.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_07.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_07.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_07.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_07.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_07.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_07.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_07.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_07.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_07.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_07.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_07.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_07.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_07.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_07.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_07.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_07.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_07
  add constraint PK_T_SMS_MT_LOG_07 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_08
prompt ==============================
prompt
create table T_SMS_MT_LOG_08
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_08
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_08.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_08.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_08.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_08.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_08.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_08.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_08.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_08.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_08.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_08.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_08.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_08.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_08.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_08.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_08.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_08.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_08.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_08.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_08.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_08
  add constraint PK_T_SMS_MT_LOG_08 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_09
prompt ==============================
prompt
create table T_SMS_MT_LOG_09
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_09
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_09.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_09.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_09.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_09.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_09.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_09.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_09.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_09.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_09.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_09.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_09.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_09.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_09.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_09.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_09.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_09.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_09.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_09.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_09.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_09
  add constraint PK_T_SMS_MT_LOG_09 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_10
prompt ==============================
prompt
create table T_SMS_MT_LOG_10
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_10
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_10.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_10.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_10.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_10.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_10.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_10.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_10.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_10.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_10.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_10.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_10.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_10.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_10.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_10.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_10.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_10.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_10.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_10.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_10.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_10
  add constraint PK_T_SMS_MT_LOG_10 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_11
prompt ==============================
prompt
create table T_SMS_MT_LOG_11
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_11
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_11.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_11.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_11.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_11.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_11.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_11.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_11.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_11.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_11.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_11.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_11.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_11.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_11.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_11.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_11.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_11.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_11.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_11.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_11.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_11
  add constraint PK_T_SMS_MT_LOG_11 primary key (ID);

prompt
prompt Creating table T_SMS_MT_LOG_12
prompt ==============================
prompt
create table T_SMS_MT_LOG_12
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50),
  msg_id           VARCHAR2(50),
  status           VARCHAR2(10),
  sms_id           NUMBER(9)
)
;
comment on table T_SMS_MT_LOG_12
  is '短信业务下行日志表';
comment on column T_SMS_MT_LOG_12.id
  is '序列，递增无实意，主键';
comment on column T_SMS_MT_LOG_12.msg_content
  is '短信内容';
comment on column T_SMS_MT_LOG_12.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_LOG_12.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_LOG_12.sp_code
  is '特服号';
comment on column T_SMS_MT_LOG_12.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_LOG_12.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_LOG_12.fee
  is '费率，非空';
comment on column T_SMS_MT_LOG_12.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_LOG_12.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_LOG_12.request_time
  is '请求下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_12.submit_time
  is '实际下行时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_LOG_12.act_id
  is '活动ID';
comment on column T_SMS_MT_LOG_12.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_LOG_12.area_code
  is '地区标识';
comment on column T_SMS_MT_LOG_12.operator_code
  is '运营商标识';
comment on column T_SMS_MT_LOG_12.msg_id
  is '下行标识';
comment on column T_SMS_MT_LOG_12.status
  is '发送状态码，非空';
comment on column T_SMS_MT_LOG_12.sms_id
  is '短信编号';
alter table T_SMS_MT_LOG_12
  add constraint PK_T_SMS_MT_LOG_12 primary key (ID);

prompt
prompt Creating table T_SMS_MT_WAIT
prompt ============================
prompt
create table T_SMS_MT_WAIT
(
  id               NUMBER(9) not null,
  msg_content      VARCHAR2(1000),
  fee_terminal_id  VARCHAR2(21),
  dest_terminal_id VARCHAR2(21),
  sp_code          VARCHAR2(20),
  service_id       VARCHAR2(20),
  fee_type         NUMBER(3),
  fee              NUMBER(5),
  is_report        NUMBER(3),
  msg_format       NUMBER(2),
  request_time     VARCHAR2(14),
  submit_time      VARCHAR2(14),
  act_id           NUMBER(9),
  unit_id          VARCHAR2(20),
  area_code        VARCHAR2(8),
  operator_code    VARCHAR2(50)
)
;
comment on table T_SMS_MT_WAIT
  is '短信业务下行日志表';
comment on column T_SMS_MT_WAIT.id
  is '序列，递增无实意，主键，SEQ_SMS_MT_WAIT';
comment on column T_SMS_MT_WAIT.msg_content
  is '短信内容';
comment on column T_SMS_MT_WAIT.fee_terminal_id
  is '计费手机号码，第三方付费必填';
comment on column T_SMS_MT_WAIT.dest_terminal_id
  is '接收方手机号码，可为多个，以“,”间隔，非空';
comment on column T_SMS_MT_WAIT.sp_code
  is '特服号';
comment on column T_SMS_MT_WAIT.service_id
  is '业务代码，非空';
comment on column T_SMS_MT_WAIT.fee_type
  is '计费类型：1－免费，2－点播，3－包月';
comment on column T_SMS_MT_WAIT.fee
  is '费率，非空';
comment on column T_SMS_MT_WAIT.is_report
  is '此条彩信下行是否收取状态报告：0－不收取，1－收取。非空';
comment on column T_SMS_MT_WAIT.msg_format
  is '短信编码格式：
0－ASCII串，
3－短信写卡操作，
4－二进制信息，
8－UCS2编码，
15－含GB汉字';
comment on column T_SMS_MT_WAIT.request_time
  is '记录插入时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_WAIT.submit_time
  is '要求下发时间：YYYYMMDDHHMMSS，非空';
comment on column T_SMS_MT_WAIT.act_id
  is '活动ID';
comment on column T_SMS_MT_WAIT.unit_id
  is '单位（第三合作方）编号';
comment on column T_SMS_MT_WAIT.area_code
  is '地区标识';
comment on column T_SMS_MT_WAIT.operator_code
  is '运营商标识';
alter table T_SMS_MT_WAIT
  add constraint PK_T_SMS_MT_WAIT primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_01
prompt ==============================
prompt
create table T_SMS_REPORT_01
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_01
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_01.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_01.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_01.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_01.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_01.sender
  is '手机号码';
alter table T_SMS_REPORT_01
  add constraint PK_T_SMS_REPORT_01 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_02
prompt ==============================
prompt
create table T_SMS_REPORT_02
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_02
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_02.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_02.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_02.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_02.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_02.sender
  is '手机号码';
alter table T_SMS_REPORT_02
  add constraint PK_T_SMS_REPORT_02 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_03
prompt ==============================
prompt
create table T_SMS_REPORT_03
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_03
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_03.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_03.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_03.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_03.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_03.sender
  is '手机号码';
alter table T_SMS_REPORT_03
  add constraint PK_T_SMS_REPORT_03 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_04
prompt ==============================
prompt
create table T_SMS_REPORT_04
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_04
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_04.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_04.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_04.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_04.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_04.sender
  is '手机号码';
alter table T_SMS_REPORT_04
  add constraint PK_T_SMS_REPORT_04 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_05
prompt ==============================
prompt
create table T_SMS_REPORT_05
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_05
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_05.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_05.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_05.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_05.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_05.sender
  is '手机号码';
alter table T_SMS_REPORT_05
  add constraint PK_T_SMS_REPORT_05 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_06
prompt ==============================
prompt
create table T_SMS_REPORT_06
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_06
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_06.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_06.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_06.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_06.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_06.sender
  is '手机号码';
alter table T_SMS_REPORT_06
  add constraint PK_T_SMS_REPORT_06 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_07
prompt ==============================
prompt
create table T_SMS_REPORT_07
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_07
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_07.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_07.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_07.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_07.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_07.sender
  is '手机号码';
alter table T_SMS_REPORT_07
  add constraint PK_T_SMS_REPORT_07 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_08
prompt ==============================
prompt
create table T_SMS_REPORT_08
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_08
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_08.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_08.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_08.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_08.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_08.sender
  is '手机号码';
alter table T_SMS_REPORT_08
  add constraint PK_T_SMS_REPORT_08 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_09
prompt ==============================
prompt
create table T_SMS_REPORT_09
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_09
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_09.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_09.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_09.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_09.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_09.sender
  is '手机号码';
alter table T_SMS_REPORT_09
  add constraint PK_T_SMS_REPORT_09 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_10
prompt ==============================
prompt
create table T_SMS_REPORT_10
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_10
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_10.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_10.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_10.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_10.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_10.sender
  is '手机号码';
alter table T_SMS_REPORT_10
  add constraint PK_T_SMS_REPORT_10 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_11
prompt ==============================
prompt
create table T_SMS_REPORT_11
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_11
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_11.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_11.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_11.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_11.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_11.sender
  is '手机号码';
alter table T_SMS_REPORT_11
  add constraint PK_T_SMS_REPORT_11 primary key (ID);

prompt
prompt Creating table T_SMS_REPORT_12
prompt ==============================
prompt
create table T_SMS_REPORT_12
(
  id       NUMBER(9) not null,
  msg_id   VARCHAR2(50),
  status   VARCHAR2(10),
  get_time VARCHAR2(14),
  sender   VARCHAR2(21)
)
;
comment on table T_SMS_REPORT_12
  is '短信下行状态报告表，按月分表存放';
comment on column T_SMS_REPORT_12.id
  is '序列，递增无实意，主键';
comment on column T_SMS_REPORT_12.msg_id
  is '短信信息标识，以此关联短信下行，非空';
comment on column T_SMS_REPORT_12.status
  is '状态报告状态，非空';
comment on column T_SMS_REPORT_12.get_time
  is '收取状态报告的时间（YYYYMMDDHHMMSS），非空';
comment on column T_SMS_REPORT_12.sender
  is '手机号码';
alter table T_SMS_REPORT_12
  add constraint PK_T_SMS_REPORT_12 primary key (ID);

prompt
prompt Creating table T_SSO_INFO
prompt =========================
prompt
create table T_SSO_INFO
(
  sid         VARCHAR2(50),
  remember_me VARCHAR2(1),
  create_time VARCHAR2(14),
  user_id     NUMBER(9) not null,
  login_type  VARCHAR2(10)
)
;
alter table T_SSO_INFO
  add constraint PK_T_SSO_INFO_TEMP primary key (USER_ID);

prompt
prompt Creating table T_STORE
prompt ======================
prompt
create table T_STORE
(
  id                      NUMBER(8) not null,
  name                    VARCHAR2(50),
  short_name              VARCHAR2(20),
  area_code               VARCHAR2(10),
  sort                    NUMBER(1),
  shop_agent_flag         NUMBER(1),
  goods_agent_flag        NUMBER(1),
  base_shop_name          VARCHAR2(50),
  create_time             VARCHAR2(14),
  update_time             VARCHAR2(14),
  address                 VARCHAR2(100),
  sys_user_id             NUMBER(8),
  update_user_id          NUMBER(9),
  is_bs_account           NUMBER(1),
  bs_manager_name         VARCHAR2(50),
  bs_manager_phone        VARCHAR2(50),
  fc_manager_name         VARCHAR2(50),
  fc_manager_phone        VARCHAR2(50),
  link_name               VARCHAR2(50),
  link_phone              VARCHAR2(50),
  link_fax                VARCHAR2(50),
  shop_class              NUMBER(1),
  status                  NUMBER(1),
  is_valid                NUMBER(1),
  item_edit_audit_flag    NUMBER(1),
  item_publish_audit_flag NUMBER(1),
  shop_edit_audit_flag    NUMBER(1),
  sync_gy_flag            NUMBER(1),
  bs_scope                VARCHAR2(200),
  service_phone           VARCHAR2(50),
  area_id                 VARCHAR2(20),
  merchid                 VARCHAR2(20),
  stop_time               VARCHAR2(14),
  start_time              VARCHAR2(14)
)
;
comment on table T_STORE
  is '包括代理商和商户在此表维护';
comment on column T_STORE.area_code
  is '0--失效 1--有效';
comment on column T_STORE.sort
  is '0--非签约 1--签约';
comment on column T_STORE.shop_agent_flag
  is '是否折扣商户代理 0-否 1-是';
comment on column T_STORE.goods_agent_flag
  is '是否商品销售代理 0-否 1-是';
comment on column T_STORE.base_shop_name
  is '（废弃）';
comment on column T_STORE.is_bs_account
  is '0--不是 1--是(废弃 ，放在t_store_settle表中)';
comment on column T_STORE.shop_class
  is '1--业务门店 2--结算商户 3--渠道商';
comment on column T_STORE.status
  is '0--草稿 1 待审核 2 审核中  3-审核通过 4-审核驳回 ';
comment on column T_STORE.is_valid
  is '0--下架 1--上架';
comment on column T_STORE.item_edit_audit_flag
  is '0--需要审核 1--免审';
comment on column T_STORE.item_publish_audit_flag
  is '0--需要审核 1--免审';
comment on column T_STORE.shop_edit_audit_flag
  is '0--需要审核 1--免审';
comment on column T_STORE.sync_gy_flag
  is ' 同步高阳状态  0：未同步 1：已同步 2：销户 3：审核通过  4：取消/删除  5： 待一级审核  6：一级审核退回  7：待财务复核  8： 财务复核退回
';
comment on column T_STORE.area_id
  is '类似 320500';
comment on column T_STORE.merchid
  is '高阳结算ID';
comment on column T_STORE.stop_time
  is '代理结束时间';
comment on column T_STORE.start_time
  is '代理开始时间';
alter table T_STORE
  add constraint PK_T_STORE primary key (ID);

prompt
prompt Creating table T_STORE_0909
prompt ===========================
prompt
create table T_STORE_0909
(
  id                      NUMBER(8) not null,
  name                    VARCHAR2(50),
  short_name              VARCHAR2(20),
  area_code               VARCHAR2(10),
  sort                    NUMBER(1),
  shop_agent_flag         NUMBER(1),
  goods_agent_flag        NUMBER(1),
  base_shop_name          VARCHAR2(50),
  create_time             VARCHAR2(14),
  update_time             VARCHAR2(14),
  address                 VARCHAR2(100),
  sys_user_id             NUMBER(8),
  update_user_id          NUMBER(9),
  is_bs_account           NUMBER(1),
  bs_manager_name         VARCHAR2(50),
  bs_manager_phone        VARCHAR2(50),
  fc_manager_name         VARCHAR2(50),
  fc_manager_phone        VARCHAR2(50),
  link_name               VARCHAR2(50),
  link_phone              VARCHAR2(50),
  link_fax                VARCHAR2(50),
  shop_class              NUMBER(1),
  status                  NUMBER(1),
  is_valid                NUMBER(1),
  item_edit_audit_flag    NUMBER(1),
  item_publish_audit_flag NUMBER(1),
  shop_edit_audit_flag    NUMBER(1),
  sync_gy_flag            NUMBER(1),
  bs_scope                VARCHAR2(200),
  service_phone           VARCHAR2(50),
  area_id                 VARCHAR2(20),
  merchid                 VARCHAR2(20),
  stop_time               VARCHAR2(14),
  start_time              VARCHAR2(14)
)
;

prompt
prompt Creating table T_STORE_AGENT
prompt ============================
prompt
create table T_STORE_AGENT
(
  id               NUMBER(8) not null,
  qd_store_id      NUMBER(8),
  js_store_id      NUMBER(8),
  start_time       VARCHAR2(14),
  stop_time        VARCHAR2(14),
  status           NUMBER(1),
  sys_user_id      NUMBER(8),
  shop_agent_flag  NUMBER(1),
  goods_agent_flag NUMBER(1)
)
;
comment on table T_STORE_AGENT
  is '渠道商代理结算商户关系表';
comment on column T_STORE_AGENT.status
  is '0--草稿
1--待审核
2--审核中
3--审核通过
4--审核驳回';
comment on column T_STORE_AGENT.shop_agent_flag
  is '是否折扣商户代理 0-否 1-是';
comment on column T_STORE_AGENT.goods_agent_flag
  is '是否商品销售代理 0-否 1-是';
alter table T_STORE_AGENT
  add constraint PK_T_STORE_AGENT primary key (ID);

prompt
prompt Creating table T_STORE_FEE
prompt ==========================
prompt
create table T_STORE_FEE
(
  id              NUMBER(9) not null,
  store_id        NUMBER(9),
  merchid         VARCHAR2(20),
  capital_type1   NUMBER(1) default 0,
  capital_type2   NUMBER(1) default 0,
  capital_type3   NUMBER(1) default 0,
  trade_mode      NUMBER(1) default 0,
  fee_type        VARCHAR2(2) default '01',
  production_type VARCHAR2(10) default '01',
  clear_type      VARCHAR2(2),
  effortdate      VARCHAR2(8),
  expirydate      VARCHAR2(8),
  feedrtflag      VARCHAR2(1),
  feeperiodunit   VARCHAR2(50),
  fc_name         VARCHAR2(50),
  feemode         NUMBER(1),
  feemothod       NUMBER(1),
  beganamount     VARCHAR2(13),
  minfeeamount    VARCHAR2(11),
  maxfeeamount    VARCHAR2(11),
  feebasicflag    NUMBER(1),
  feelevelflag    VARCHAR2(1),
  feelvlbasicflag VARCHAR2(1),
  upreference1    VARCHAR2(11),
  fixfeeamount1   VARCHAR2(9),
  feerate1        VARCHAR2(5),
  upreference2    VARCHAR2(11),
  fixfeeamount2   VARCHAR2(9),
  feerate2        VARCHAR2(5),
  upreference3    VARCHAR2(11),
  fixfeeamount3   VARCHAR2(9),
  feerate3        VARCHAR2(5),
  upreference4    VARCHAR2(11),
  fixfeeamount4   VARCHAR2(9),
  feerate4        VARCHAR2(5),
  upreference5    VARCHAR2(11),
  fixfeeamount5   VARCHAR2(9),
  feerate5        VARCHAR2(5),
  create_time     VARCHAR2(14),
  sync_time       VARCHAR2(14),
  create_user     NUMBER(9),
  status          NUMBER(1) default 0,
  sync_gy_flag1   NUMBER(1) default 0,
  sync_gy_flag2   NUMBER(1) default 0,
  sync_gy_flag3   NUMBER(1) default 0
)
;
comment on table T_STORE_FEE
  is '商户费率信息表';
comment on column T_STORE_FEE.store_id
  is '商户id';
comment on column T_STORE_FEE.merchid
  is '高阳结算id';
comment on column T_STORE_FEE.capital_type1
  is '是否现金 1：是  0：否';
comment on column T_STORE_FEE.capital_type2
  is '是否商城币1:是  0：否';
comment on column T_STORE_FEE.capital_type3
  is '是否积分  1：是  0：否';
comment on column T_STORE_FEE.trade_mode
  is '交易方式
0-远程普通
1：现场脱机；
3：远程大额；
4：现场联机；（备用）';
comment on column T_STORE_FEE.fee_type
  is '费用类型
01：消费佣金
02：渠道佣金
 (没，用的下面的production——type)';
comment on column T_STORE_FEE.production_type
  is '对应t_sys_fee表的id';
comment on column T_STORE_FEE.clear_type
  is '清算类别
C：商品类别 (同步商品类别，按照类别结算费率)
P：商品
';
comment on column T_STORE_FEE.effortdate
  is '生效日期';
comment on column T_STORE_FEE.expirydate
  is '失效日期';
comment on column T_STORE_FEE.feedrtflag
  is '费用方向
D：商户/代理点支付费用
C：商户/代理点收入费用
';
comment on column T_STORE_FEE.feeperiodunit
  is '收费周期单位
0: 年
1: 月
2: 日';
comment on column T_STORE_FEE.fc_name
  is '收费周期数量';
comment on column T_STORE_FEE.feemode
  is '收费方式
0：不收取
1：实时单笔收取
2：单笔计算、按周期收取
3：按周期汇总计算并收取
4：按周期汇总轧差并收取';
comment on column T_STORE_FEE.feemothod
  is '计费方法
:1:按固定金额收取
2:按金额固定百分比收取 （需要确认）
3:按照商品差价计算 （团购）';
comment on column T_STORE_FEE.beganamount
  is '计费起始金额(笔数)
如果费用计算依据为笔数，不允许输入，如不输入则系统设置为0没填';
comment on column T_STORE_FEE.minfeeamount
  is '最低收费金额
收费方式=1时不允许输入；计费方法=2/3时可以输入，如不输入则系统设置为0没填';
comment on column T_STORE_FEE.maxfeeamount
  is '最高收费金额
收费方式=1时不允许输入；如不输入则系统设置为全9最大数没填';
comment on column T_STORE_FEE.feebasicflag
  is '费用计算依据
0：金额  1：笔数跟计费方法1对应）';
comment on column T_STORE_FEE.feelevelflag
  is '分层/套档标志
空格：表示只有一档；0：套档，1：分层；最多可分5层/档。
本字段没有输入的情况下，只有一档费用计算参数';
comment on column T_STORE_FEE.feelvlbasicflag
  is '分层/套档依据
0：金额  1：笔数';
comment on column T_STORE_FEE.upreference1
  is '计费参考1
当分层/套档标志为空时，本字段缺省为全9.没填
如果2-5计费参考有输入，则必须依次递增，且必须连续输入，但需要注意不一定输满5组。
';
comment on column T_STORE_FEE.fixfeeamount1
  is '固定费用金额1
如果本组的计费参考没有输入，则本组的固定费用金额和费率都不允许输入
如果本组的计费参考有输入，则本组的固定费用金额和费率必须输入其一，且只允许输入其一，当计费方法=1时，必须输入固定费用金额，当计费方法=2时，必须输入费率；
另外，需要注意汇总收费的模式下：当用户交易笔数在**笔到**笔之间，按每笔收取**金额的方式；以及用户交易笔数在**笔到*8笔之间，总共收取**金额的差别。
';
comment on column T_STORE_FEE.feerate1
  is '费率1
参考固定费用金额的描述';
comment on column T_STORE_FEE.upreference2
  is '计费参考2';
comment on column T_STORE_FEE.fixfeeamount2
  is '固定费用金额2';
comment on column T_STORE_FEE.feerate2
  is '费率2';
comment on column T_STORE_FEE.upreference3
  is '计费参考3';
comment on column T_STORE_FEE.fixfeeamount3
  is '固定费用金额3';
comment on column T_STORE_FEE.feerate3
  is '费率3';
comment on column T_STORE_FEE.upreference4
  is '计费参考4';
comment on column T_STORE_FEE.fixfeeamount4
  is '固定费用金额4';
comment on column T_STORE_FEE.feerate4
  is '费率4';
comment on column T_STORE_FEE.upreference5
  is '计费参考5';
comment on column T_STORE_FEE.fixfeeamount5
  is '固定费用金额5';
comment on column T_STORE_FEE.feerate5
  is '费率5';
comment on column T_STORE_FEE.create_time
  is '创建时间';
comment on column T_STORE_FEE.sync_time
  is '同步时间';
comment on column T_STORE_FEE.create_user
  is '创建人';
comment on column T_STORE_FEE.status
  is '状态 0未审核  3审核通过 4审核驳回';
comment on column T_STORE_FEE.sync_gy_flag1
  is '同步高阳状态：对应CAPITAL_TYPE1   0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';
comment on column T_STORE_FEE.sync_gy_flag2
  is '同步高阳状态：对应CAPITAL_TYPE2     0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';
comment on column T_STORE_FEE.sync_gy_flag3
  is '同步高阳状态：对应CAPITAL_TYPE3    0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';
alter table T_STORE_FEE
  add constraint PK_T_STORE_FEE primary key (ID);

prompt
prompt Creating table T_STORE_FEE_2
prompt ============================
prompt
create table T_STORE_FEE_2
(
  id              NUMBER,
  store_id        NUMBER(9),
  merchid         VARCHAR2(20),
  capital_type1   NUMBER(1),
  capital_type2   NUMBER(1),
  capital_type3   NUMBER(1),
  trade_mode      NUMBER(1),
  fee_type        VARCHAR2(2),
  production_type VARCHAR2(10),
  clear_type      VARCHAR2(2),
  effortdate      VARCHAR2(8),
  expirydate      VARCHAR2(8),
  feedrtflag      VARCHAR2(1),
  feeperiodunit   VARCHAR2(50),
  fc_name         VARCHAR2(50),
  feemode         NUMBER(1),
  feemothod       NUMBER(1),
  beganamount     VARCHAR2(13),
  minfeeamount    VARCHAR2(11),
  maxfeeamount    VARCHAR2(11),
  feebasicflag    NUMBER(1),
  feelevelflag    VARCHAR2(1),
  feelvlbasicflag VARCHAR2(1),
  upreference1    VARCHAR2(11),
  fixfeeamount1   VARCHAR2(9),
  feerate1        VARCHAR2(5),
  upreference2    VARCHAR2(11),
  fixfeeamount2   VARCHAR2(9),
  feerate2        VARCHAR2(5),
  upreference3    VARCHAR2(11),
  fixfeeamount3   VARCHAR2(9),
  feerate3        VARCHAR2(5),
  upreference4    VARCHAR2(11),
  fixfeeamount4   VARCHAR2(9),
  feerate4        VARCHAR2(5),
  upreference5    VARCHAR2(11),
  fixfeeamount5   VARCHAR2(9),
  feerate5        VARCHAR2(5),
  create_time     VARCHAR2(14),
  sync_time       VARCHAR2(14),
  create_user     NUMBER(9),
  status          NUMBER(1),
  sync_gy_flag1   NUMBER(1),
  sync_gy_flag2   NUMBER(1),
  sync_gy_flag3   NUMBER(1)
)
;

prompt
prompt Creating table T_STORE_FEE_TMP
prompt ==============================
prompt
create table T_STORE_FEE_TMP
(
  store_id           NUMBER(9),
  merchid            VARCHAR2(20),
  capital_type1      NUMBER(1) default 0,
  capital_type2      NUMBER(1) default 0,
  capital_type3      NUMBER(1) default 0,
  trade_mode         NUMBER(1) default 0,
  fee_type           VARCHAR2(2) default '01',
  production_type    VARCHAR2(10) default '01',
  xw_production_type VARCHAR2(10),
  clear_type         VARCHAR2(2),
  effortdate         VARCHAR2(8),
  expirydate         VARCHAR2(8),
  feedrtflag         VARCHAR2(1),
  feeperiodunit      VARCHAR2(50),
  fc_name            VARCHAR2(50),
  feemode            NUMBER(1),
  feemothod          NUMBER(1),
  beganamount        VARCHAR2(13),
  minfeeamount       VARCHAR2(11),
  maxfeeamount       VARCHAR2(11),
  feebasicflag       NUMBER(1),
  feelevelflag       VARCHAR2(1),
  feelvlbasicflag    VARCHAR2(1),
  upreference1       VARCHAR2(11),
  fixfeeamount1      VARCHAR2(9),
  feerate1           VARCHAR2(5),
  upreference2       VARCHAR2(11),
  fixfeeamount2      VARCHAR2(9),
  feerate2           VARCHAR2(5),
  upreference3       VARCHAR2(11),
  fixfeeamount3      VARCHAR2(9),
  feerate3           VARCHAR2(5),
  upreference4       VARCHAR2(11),
  fixfeeamount4      VARCHAR2(9),
  feerate4           VARCHAR2(5),
  upreference5       VARCHAR2(11),
  fixfeeamount5      VARCHAR2(9),
  feerate5           VARCHAR2(5),
  create_time        VARCHAR2(14),
  sync_time          VARCHAR2(14),
  create_user        NUMBER(9),
  status             NUMBER(1) default 0,
  sync_gy_flag1      NUMBER(1) default 0,
  sync_gy_flag2      NUMBER(1) default 0,
  sync_gy_flag3      NUMBER(1) default 0
)
;

prompt
prompt Creating table T_STORE_FEE_TMP2
prompt ===============================
prompt
create table T_STORE_FEE_TMP2
(
  id              NUMBER(9) not null,
  store_id        VARCHAR2(50),
  merchid         VARCHAR2(20),
  capital_type1   NUMBER(1) default 0,
  capital_type2   NUMBER(1) default 0,
  capital_type3   NUMBER(1) default 0,
  trade_mode      NUMBER(1) default 0,
  fee_type        VARCHAR2(2) default '01',
  production_type VARCHAR2(10) default '01',
  clear_type      VARCHAR2(2),
  effortdate      VARCHAR2(8),
  expirydate      VARCHAR2(8),
  feedrtflag      VARCHAR2(1),
  feeperiodunit   VARCHAR2(50),
  fc_name         VARCHAR2(50),
  feemode         NUMBER(1),
  feemothod       NUMBER(1),
  beganamount     VARCHAR2(13),
  minfeeamount    VARCHAR2(11),
  maxfeeamount    VARCHAR2(11),
  feebasicflag    NUMBER(1),
  feelevelflag    VARCHAR2(1),
  feelvlbasicflag VARCHAR2(1),
  upreference1    VARCHAR2(11),
  fixfeeamount1   VARCHAR2(9),
  feerate1        VARCHAR2(5),
  upreference2    VARCHAR2(11),
  fixfeeamount2   VARCHAR2(9),
  feerate2        VARCHAR2(5),
  upreference3    VARCHAR2(11),
  fixfeeamount3   VARCHAR2(9),
  feerate3        VARCHAR2(5),
  upreference4    VARCHAR2(11),
  fixfeeamount4   VARCHAR2(9),
  feerate4        VARCHAR2(5),
  upreference5    VARCHAR2(11),
  fixfeeamount5   VARCHAR2(9),
  feerate5        VARCHAR2(5),
  create_time     VARCHAR2(14),
  sync_time       VARCHAR2(14),
  create_user     NUMBER(9),
  status          NUMBER(1) default 0,
  sync_gy_flag1   NUMBER(1) default 0,
  sync_gy_flag2   NUMBER(1) default 0,
  sync_gy_flag3   NUMBER(1) default 0,
  fee_t           VARCHAR2(2)
)
;

prompt
prompt Creating table T_STORE_LOGISTICS_FEE
prompt ====================================
prompt
create table T_STORE_LOGISTICS_FEE
(
  id       NUMBER(8) not null,
  store_id NUMBER(8),
  qd_id    NUMBER(8),
  fee_num  FLOAT
)
;
comment on table T_STORE_LOGISTICS_FEE
  is '物流运费';
alter table T_STORE_LOGISTICS_FEE
  add constraint PK_T_STORE_LOGISTICS_FEE primary key (ID);

prompt
prompt Creating table T_STORE_SETTLE
prompt =============================
prompt
create table T_STORE_SETTLE
(
  id              NUMBER(9) not null,
  store_id        NUMBER(9) not null,
  merchid         VARCHAR2(20),
  settle_type     NUMBER(1) default 0 not null,
  fee_settle      NUMBER(1) default 1,
  settle_period   NUMBER(1),
  settle_day      NUMBER(3) default 1,
  settle_daybit   NUMBER(3),
  settle_trfdays  NUMBER(3),
  settle_beginamt NUMBER(8) default 0,
  min_retainedamt NUMBER(8),
  withdraw_bankid VARCHAR2(100),
  openbank_desc   VARCHAR2(200),
  settle_ac       VARCHAR2(100),
  bank_acname     VARCHAR2(100),
  effort_date     VARCHAR2(8),
  expiry_date     VARCHAR2(8),
  sync_gy_flag    NUMBER(1) default 0,
  status          NUMBER(1) default 0,
  create_time     VARCHAR2(14),
  create_user     NUMBER(9),
  sync_time       VARCHAR2(14),
  is_bs_account   NUMBER(1)
)
;
comment on column T_STORE_SETTLE.store_id
  is '商户id';
comment on column T_STORE_SETTLE.merchid
  is '高阳结算id';
comment on column T_STORE_SETTLE.settle_type
  is '结算类型
0：系统结算
1：手工结算';
comment on column T_STORE_SETTLE.fee_settle
  is '佣金结算
0：收支两条线
1：作扣';
comment on column T_STORE_SETTLE.settle_period
  is '结算周期
0：日
1：周
2：旬
3：月
4：季
5：半年
6：年
7：指定日
';
comment on column T_STORE_SETTLE.settle_day
  is '结算日
标识结算间隔的数量';
comment on column T_STORE_SETTLE.settle_daybit
  is '结算日标志位
指定结算日时设置一个月31天的具体日期';
comment on column T_STORE_SETTLE.settle_trfdays
  is '结算划款天数';
comment on column T_STORE_SETTLE.settle_beginamt
  is '结算起始金额';
comment on column T_STORE_SETTLE.min_retainedamt
  is '最低留存金额';
comment on column T_STORE_SETTLE.withdraw_bankid
  is '结算银行的名称';
comment on column T_STORE_SETTLE.openbank_desc
  is '开户行详细信息';
comment on column T_STORE_SETTLE.settle_ac
  is '结算账户';
comment on column T_STORE_SETTLE.bank_acname
  is '开户人';
comment on column T_STORE_SETTLE.effort_date
  is '生效日期';
comment on column T_STORE_SETTLE.expiry_date
  is '失效日期';
comment on column T_STORE_SETTLE.sync_gy_flag
  is '同步高阳状态：0：未同步；1：已同步；2：待审核；3：审核通过；4：审核退回；5：失效/删除';
comment on column T_STORE_SETTLE.status
  is '状态 0未审核  3审核通过 4审核驳回';
comment on column T_STORE_SETTLE.create_time
  is '创建时间';
comment on column T_STORE_SETTLE.create_user
  is '创建人';
comment on column T_STORE_SETTLE.sync_time
  is '同步时间';
comment on column T_STORE_SETTLE.is_bs_account
  is '0--不是 1--是';
alter table T_STORE_SETTLE
  add constraint PK_T_STORE_SETTLE primary key (ID);

prompt
prompt Creating table T_SURVEY
prompt =======================
prompt
create table T_SURVEY
(
  id           NUMBER(9) not null,
  s_questionid NUMBER(3) not null,
  s_answer     VARCHAR2(500),
  user_id      NUMBER(9),
  s_surveyid   NUMBER(2)
)
;
comment on table T_SURVEY
  is '问卷调查结果表';
comment on column T_SURVEY.id
  is '主键';
comment on column T_SURVEY.s_questionid
  is '调查问卷问题编号';
comment on column T_SURVEY.s_answer
  is '调查问卷答案';
comment on column T_SURVEY.user_id
  is '用户ID';
comment on column T_SURVEY.s_surveyid
  is '调查问卷编号';
alter table T_SURVEY
  add constraint PK_T_SURVEY primary key (ID);

prompt
prompt Creating table T_SYS_AD
prompt =======================
prompt
create table T_SYS_AD
(
  id          NUMBER(9) not null,
  ad_name     VARCHAR2(100),
  ad_type     NUMBER(2),
  ad_flag     NUMBER(2) default 0,
  link        VARCHAR2(200),
  linkman     VARCHAR2(100),
  content     VARCHAR2(200),
  status      NUMBER(2),
  start_time  VARCHAR2(14),
  valid_time  VARCHAR2(14),
  click_cnt   NUMBER(9) default 0,
  view_cnt    NUMBER(9) default 0,
  create_time VARCHAR2(14),
  create_user NUMBER(9),
  position_id NUMBER(9)
)
;
comment on column T_SYS_AD.ad_name
  is '广告名称';
comment on column T_SYS_AD.ad_type
  is '广告类型
1-图片
2-文字
3-脚本';
comment on column T_SYS_AD.ad_flag
  is '广告类别
0-内部广告
1-外部广告';
comment on column T_SYS_AD.link
  is '广告链接';
comment on column T_SYS_AD.linkman
  is '广告负责人';
comment on column T_SYS_AD.content
  is '广告内容
如果是图片广告，则为图片存储路径';
comment on column T_SYS_AD.status
  is '广告状态
0-未审核
1-审核通过
2-审核不通过
';
comment on column T_SYS_AD.start_time
  is '开始时间';
comment on column T_SYS_AD.valid_time
  is '有效期';
comment on column T_SYS_AD.click_cnt
  is '点击次数';
comment on column T_SYS_AD.view_cnt
  is '展现次数';
comment on column T_SYS_AD.create_time
  is '创建日期';
comment on column T_SYS_AD.create_user
  is '创建人';
comment on column T_SYS_AD.position_id
  is '广告位置';
alter table T_SYS_AD
  add constraint PK_T_SYS_AD primary key (ID);

prompt
prompt Creating table T_SYS_AD_POSITION
prompt ================================
prompt
create table T_SYS_AD_POSITION
(
  id       NUMBER(9) not null,
  name     VARCHAR2(100),
  width    NUMBER(9),
  height   NUMBER(9),
  position VARCHAR2(20),
  code     VARCHAR2(100),
  type     NUMBER(1),
  num      NUMBER(2) default 2 not null
)
;
comment on table T_SYS_AD_POSITION
  is '广告位置配置';
comment on column T_SYS_AD_POSITION.name
  is '名称';
comment on column T_SYS_AD_POSITION.width
  is '宽';
comment on column T_SYS_AD_POSITION.height
  is '高';
comment on column T_SYS_AD_POSITION.position
  is '模版位置';
comment on column T_SYS_AD_POSITION.code
  is '模版代码';
comment on column T_SYS_AD_POSITION.type
  is '模版类型
1-图片
2-文字
3-flash';
alter table T_SYS_AD_POSITION
  add constraint PK_T_SYS_AD_POSITION primary key (ID);

prompt
prompt Creating table T_SYS_AD_REGION
prompt ==============================
prompt
create table T_SYS_AD_REGION
(
  id          NUMBER(9) not null,
  region_code VARCHAR2(10),
  ad_id       NUMBER(9)
)
;
comment on table T_SYS_AD_REGION
  is '广告展现区域表';
comment on column T_SYS_AD_REGION.region_code
  is '展现区域编号';
comment on column T_SYS_AD_REGION.ad_id
  is '广告id';
alter table T_SYS_AD_REGION
  add constraint PK_T_SYS_AD_REGON primary key (ID);

prompt
prompt Creating table T_SYS_ANNOUNCEMENT
prompt =================================
prompt
create table T_SYS_ANNOUNCEMENT
(
  id          NUMBER(9) not null,
  title       VARCHAR2(100),
  description CLOB,
  pub_status  NUMBER(1),
  pub_user    NUMBER(9),
  pub_time    VARCHAR2(14),
  pub_dest    NUMBER(1),
  audit_user  NUMBER(9),
  audit_time  VARCHAR2(14),
  end_time    VARCHAR2(14)
)
;
comment on table T_SYS_ANNOUNCEMENT
  is '公告表';
comment on column T_SYS_ANNOUNCEMENT.id
  is 'id';
comment on column T_SYS_ANNOUNCEMENT.title
  is '标题';
comment on column T_SYS_ANNOUNCEMENT.description
  is '内容';
comment on column T_SYS_ANNOUNCEMENT.pub_status
  is '状态 0新建 1审核驳回 2已发布 3删除';
comment on column T_SYS_ANNOUNCEMENT.pub_user
  is '发布人';
comment on column T_SYS_ANNOUNCEMENT.pub_time
  is '发布时间';
comment on column T_SYS_ANNOUNCEMENT.pub_dest
  is '发布对象 0后台 1商户自服务 2个人中心 3门户';
comment on column T_SYS_ANNOUNCEMENT.audit_user
  is '审核人';
comment on column T_SYS_ANNOUNCEMENT.audit_time
  is '审核时间';
comment on column T_SYS_ANNOUNCEMENT.end_time
  is '结束时间';
alter table T_SYS_ANNOUNCEMENT
  add constraint PK_SYS_ANNOUNCEMENT primary key (ID);

prompt
prompt Creating table T_SYS_AREA_INFO
prompt ==============================
prompt
create table T_SYS_AREA_INFO
(
  id            NUMBER(9) not null,
  area_code     VARCHAR2(8) not null,
  area_name     VARCHAR2(20) not null,
  prov_id       NUMBER(9),
  province_name VARCHAR2(20) not null,
  area_spell    VARCHAR2(20),
  day_limit     VARCHAR2(9 CHAR),
  month_limit   VARCHAR2(9 CHAR)
)
;
comment on column T_SYS_AREA_INFO.id
  is '地市ID';
comment on column T_SYS_AREA_INFO.area_code
  is '地市代码(区号)';
comment on column T_SYS_AREA_INFO.area_name
  is '地市名称';
comment on column T_SYS_AREA_INFO.prov_id
  is '所属省ID';
comment on column T_SYS_AREA_INFO.province_name
  is '所属省名称';
comment on column T_SYS_AREA_INFO.area_spell
  is '地市全拼';
alter table T_SYS_AREA_INFO
  add constraint PK_T_SYS_AREA_INFO primary key (ID);

prompt
prompt Creating table T_SYS_BLACK_USER
prompt ===============================
prompt
create table T_SYS_BLACK_USER
(
  id             NUMBER(9) not null,
  terminal_id    VARCHAR2(21) not null,
  lev_tag        NUMBER(3),
  in_tag         NUMBER(3),
  remark         VARCHAR2(50),
  update_user_id NUMBER(9),
  update_time    VARCHAR2(14),
  unit_id        VARCHAR2(20 CHAR)
)
;
comment on column T_SYS_BLACK_USER.lev_tag
  is '不同级别的黑名单用户可以参与不同的业务。
0：平台级（任何业务都不能参加）
1：不能接收群发信息
2：广告黑名单
3：本单位所有业务都不能参加';
comment on column T_SYS_BLACK_USER.in_tag
  is '0：手工输入
1：批量导入';
alter table T_SYS_BLACK_USER
  add constraint PK_T_SYS_BLACK_USER primary key (ID);

prompt
prompt Creating table T_SYS_CURRENCY_EXCHANGE
prompt ======================================
prompt
create table T_SYS_CURRENCY_EXCHANGE
(
  id                NUMBER(9) not null,
  province          VARCHAR2(20) not null,
  area_code         VARCHAR2(20),
  main_currency     VARCHAR2(20) not null,
  exchange_currency VARCHAR2(20) not null,
  exchange_value    NUMBER(11,2) not null,
  remark            VARCHAR2(500),
  operator          VARCHAR2(20),
  input_time        VARCHAR2(14)
)
;
comment on table T_SYS_CURRENCY_EXCHANGE
  is '存放商城币、积分、现金之间的兑换比例';
comment on column T_SYS_CURRENCY_EXCHANGE.id
  is '唯一ID';
comment on column T_SYS_CURRENCY_EXCHANGE.province
  is '省份';
comment on column T_SYS_CURRENCY_EXCHANGE.area_code
  is '地市';
comment on column T_SYS_CURRENCY_EXCHANGE.main_currency
  is '主货币名称,SCORE';
comment on column T_SYS_CURRENCY_EXCHANGE.exchange_currency
  is '兑换货币名称，填写商城币COIN';
comment on column T_SYS_CURRENCY_EXCHANGE.exchange_value
  is '兑换值，即一元现金可以兑换多少积分或商城币';
comment on column T_SYS_CURRENCY_EXCHANGE.remark
  is '备注';
comment on column T_SYS_CURRENCY_EXCHANGE.operator
  is '操作者';
comment on column T_SYS_CURRENCY_EXCHANGE.input_time
  is '操作时间';
alter table T_SYS_CURRENCY_EXCHANGE
  add constraint PK_T_SYS_CURRENCY_EXCHANGE primary key (ID);

prompt
prompt Creating table T_SYS_CURRENCY_EXCHANGE_LOG
prompt ==========================================
prompt
create table T_SYS_CURRENCY_EXCHANGE_LOG
(
  id            NUMBER(9) not null,
  user_id       VARCHAR2(20) not null,
  terminal_id   VARCHAR2(20) not null,
  exchange_time VARCHAR2(14) not null,
  score         NUMBER(11,2) not null,
  coin          NUMBER(11,2) not null,
  order_id      NUMBER(9),
  remark        VARCHAR2(500)
)
;
comment on table T_SYS_CURRENCY_EXCHANGE_LOG
  is '存放商城币、积分、现金之间的兑换的日志';
comment on column T_SYS_CURRENCY_EXCHANGE_LOG.id
  is '唯一ID';
comment on column T_SYS_CURRENCY_EXCHANGE_LOG.user_id
  is '用户ID';
comment on column T_SYS_CURRENCY_EXCHANGE_LOG.terminal_id
  is '手机号码';
comment on column T_SYS_CURRENCY_EXCHANGE_LOG.exchange_time
  is '兑换日期';
comment on column T_SYS_CURRENCY_EXCHANGE_LOG.score
  is '兑换积分,如输入1000';
comment on column T_SYS_CURRENCY_EXCHANGE_LOG.coin
  is '兑换商城币????';
comment on column T_SYS_CURRENCY_EXCHANGE_LOG.order_id
  is '订单号';
comment on column T_SYS_CURRENCY_EXCHANGE_LOG.remark
  is '备注';
alter table T_SYS_CURRENCY_EXCHANGE_LOG
  add constraint PK_T_SYS_CURRENCY_EXCHANGE_LOG primary key (ID);

prompt
prompt Creating table T_SYS_FEE
prompt ========================
prompt
create table T_SYS_FEE
(
  id           NUMBER(8) not null,
  name         VARCHAR2(120),
  fee          NUMBER(9,2),
  valid        NUMBER(1),
  decr_fee     VARCHAR2(100),
  sync_gy_flag NUMBER(1),
  sync_gy_time VARCHAR2(14),
  store_id     NUMBER(9)
)
;
comment on table T_SYS_FEE
  is '费率配置表';
comment on column T_SYS_FEE.name
  is '费率名称';
comment on column T_SYS_FEE.fee
  is '费率';
comment on column T_SYS_FEE.valid
  is '1-有效 0-无效';
comment on column T_SYS_FEE.decr_fee
  is '描述';
comment on column T_SYS_FEE.sync_gy_flag
  is '0-未同步 1-已同步';
alter table T_SYS_FEE
  add constraint PK_T_SYS_FEE primary key (ID);

prompt
prompt Creating table T_SYS_FEE2
prompt =========================
prompt
create table T_SYS_FEE2
(
  id         NUMBER,
  store_id   VARCHAR2(200),
  store_name VARCHAR2(150),
  category   VARCHAR2(50),
  fee_type   VARCHAR2(1),
  decr_fee   VARCHAR2(100)
)
;

prompt
prompt Creating table T_SYS_FEE_TEMP
prompt =============================
prompt
create table T_SYS_FEE_TEMP
(
  id NUMBER(8) not null,
  t1 NUMBER(7,4),
  g1 NUMBER(12,2),
  t2 NUMBER(7,4),
  g2 NUMBER(12,2),
  t3 NUMBER(7,4),
  g3 NUMBER(12,2),
  t4 NUMBER(7,4),
  g4 NUMBER(12,2),
  t5 NUMBER(7,4),
  g5 NUMBER(12,2)
)
;

prompt
prompt Creating table T_SYS_FEE_TMP2
prompt =============================
prompt
create table T_SYS_FEE_TMP2
(
  id         NUMBER,
  store_id   VARCHAR2(50),
  store_name VARCHAR2(200),
  category   VARCHAR2(150),
  fee_type   NUMBER,
  starttime  VARCHAR2(8),
  endtime    VARCHAR2(8),
  describe   VARCHAR2(4000)
)
;

prompt
prompt Creating table T_SYS_FILE
prompt =========================
prompt
create table T_SYS_FILE
(
  id            NUMBER(8) not null,
  file_type     VARCHAR2(20),
  remark        VARCHAR2(100),
  update_time   VARCHAR2(14),
  file_abs_path VARCHAR2(100),
  file_web_path VARCHAR2(100),
  create_user   NUMBER(8),
  bs_key        VARCHAR2(50),
  bs_id         NUMBER(8),
  table_name    VARCHAR2(100)
)
;
comment on table T_SYS_FILE
  is '平台所有平台通过该表存储';
comment on column T_SYS_FILE.file_type
  is '文件类型';
comment on column T_SYS_FILE.remark
  is '文件描述';
comment on column T_SYS_FILE.update_time
  is '更新时间';
comment on column T_SYS_FILE.file_abs_path
  is '存储路径';
comment on column T_SYS_FILE.file_web_path
  is '文件访问路径';
comment on column T_SYS_FILE.create_user
  is '创建人';
comment on column T_SYS_FILE.bs_key
  is '根据不同业务定义相应类型KEY，例如主图，菜肴图，附件';
comment on column T_SYS_FILE.bs_id
  is '业务唯一编号，例如商户ID、商品ID、其它';
comment on column T_SYS_FILE.table_name
  is '表名';
alter table T_SYS_FILE
  add constraint PK_T_SYS_FILE primary key (ID);

prompt
prompt Creating table T_SYS_FILE_IMG
prompt =============================
prompt
create table T_SYS_FILE_IMG
(
  id            NUMBER(8) not null,
  file_type     VARCHAR2(20),
  remark        VARCHAR2(100),
  update_time   VARCHAR2(14),
  file_abs_path VARCHAR2(100),
  file_web_path VARCHAR2(100),
  create_user   NUMBER(8),
  file_name     VARCHAR2(100),
  bs_key        VARCHAR2(50),
  bs_id         NUMBER(8),
  table_name    VARCHAR2(100),
  sort          NUMBER(3) default 0
)
;
comment on table T_SYS_FILE_IMG
  is '平台所有平台通过该表存储';
comment on column T_SYS_FILE_IMG.file_type
  is '文件类型';
comment on column T_SYS_FILE_IMG.update_time
  is '更新时间';
comment on column T_SYS_FILE_IMG.file_abs_path
  is '存储路径';
comment on column T_SYS_FILE_IMG.file_web_path
  is 'web路径';
comment on column T_SYS_FILE_IMG.create_user
  is '创建人';
comment on column T_SYS_FILE_IMG.file_name
  is '文件名';
comment on column T_SYS_FILE_IMG.bs_key
  is '根据不同业务定义相应类型KEY，例如主图，菜肴图，附件';
comment on column T_SYS_FILE_IMG.bs_id
  is '业务唯一编号，例如商户ID、商品ID、其它';
comment on column T_SYS_FILE_IMG.table_name
  is '表名';
comment on column T_SYS_FILE_IMG.sort
  is '排序';
alter table T_SYS_FILE_IMG
  add constraint PK_T_SYS_FILE_IMG primary key (ID);

prompt
prompt Creating table T_SYS_FILE_IMG_LINK
prompt ==================================
prompt
create table T_SYS_FILE_IMG_LINK
(
  id         NUMBER(8) not null,
  bs_key     VARCHAR2(50),
  bs_id      NUMBER(8),
  file_id    NUMBER(8),
  table_name VARCHAR2(100),
  sort       NUMBER(3) default 0,
  file_name  VARCHAR2(100)
)
;
comment on table T_SYS_FILE_IMG_LINK
  is '业务和文件关联表';
comment on column T_SYS_FILE_IMG_LINK.bs_key
  is '根据不同业务定义相应类型KEY，例如主图，菜肴图，附件';
comment on column T_SYS_FILE_IMG_LINK.bs_id
  is '业务唯一编号，例如商户ID、商品ID、其它';
comment on column T_SYS_FILE_IMG_LINK.file_id
  is '关联原始文件存储表t_sys_file表中ID';
comment on column T_SYS_FILE_IMG_LINK.table_name
  is '表名';
comment on column T_SYS_FILE_IMG_LINK.sort
  is '排序';
comment on column T_SYS_FILE_IMG_LINK.file_name
  is '文件名';
alter table T_SYS_FILE_IMG_LINK
  add constraint PK_T_SYS_FILE_IMG_LINK primary key (ID);

prompt
prompt Creating table T_SYS_FILE_IMG_THUMB
prompt ===================================
prompt
create table T_SYS_FILE_IMG_THUMB
(
  id           NUMBER(8) not null,
  file_id      NUMBER(8),
  img_size     VARCHAR2(50),
  img_abs_path VARCHAR2(100),
  img_web_path VARCHAR2(100),
  update_time  VARCHAR2(14)
)
;
comment on table T_SYS_FILE_IMG_THUMB
  is '缩略图存储';
comment on column T_SYS_FILE_IMG_THUMB.file_id
  is '原始文件表中的文件ID';
comment on column T_SYS_FILE_IMG_THUMB.img_size
  is '50*50、128*128、256*256等尺寸规格';
comment on column T_SYS_FILE_IMG_THUMB.img_abs_path
  is '绝对路径';
comment on column T_SYS_FILE_IMG_THUMB.img_web_path
  is '网络路径';
comment on column T_SYS_FILE_IMG_THUMB.update_time
  is '更新时间';
alter table T_SYS_FILE_IMG_THUMB
  add constraint PK_T_SYS_FILE_IMG_THUMB primary key (ID);

prompt
prompt Creating table T_SYS_FILE_LINK
prompt ==============================
prompt
create table T_SYS_FILE_LINK
(
  id         NUMBER(8) not null,
  bs_key     VARCHAR2(50),
  bs_id      NUMBER(8),
  file_id    NUMBER(8),
  table_name VARCHAR2(100)
)
;
comment on table T_SYS_FILE_LINK
  is '业务和文件关联表';
comment on column T_SYS_FILE_LINK.bs_key
  is '根据不同业务定义相应类型KEY，例如主图，菜肴图，附件';
comment on column T_SYS_FILE_LINK.bs_id
  is '业务唯一编号，例如商户ID、商品ID、其它';
comment on column T_SYS_FILE_LINK.file_id
  is '关联原始文件存储表t_sys_file表中ID';
comment on column T_SYS_FILE_LINK.table_name
  is '表名';
alter table T_SYS_FILE_LINK
  add constraint PK_T_SYS_FILE_LINK primary key (ID);

prompt
prompt Creating table T_SYS_FILTER_WORD
prompt ================================
prompt
create table T_SYS_FILTER_WORD
(
  id             NUMBER(9) not null,
  word           VARCHAR2(50),
  in_tag         NUMBER(3),
  unit_id        VARCHAR2(20),
  update_user_id NUMBER(9),
  update_time    VARCHAR2(14)
)
;
comment on table T_SYS_FILTER_WORD
  is '序号来源于序列：SEQ_FILTER_WORD;';
comment on column T_SYS_FILTER_WORD.in_tag
  is '0：手工输入
1：批量导入
如果是批量导入，则需要填写文件路径';
comment on column T_SYS_FILTER_WORD.unit_id
  is '通过序列产生，不足6位，前面不零';
alter table T_SYS_FILTER_WORD
  add constraint PK_T_SYS_FILTER_WORD primary key (ID);

prompt
prompt Creating table T_SYS_INVOICE
prompt ============================
prompt
create table T_SYS_INVOICE
(
  id   NUMBER(9) not null,
  name VARCHAR2(40)
)
;
comment on table T_SYS_INVOICE
  is '发票字典表';
comment on column T_SYS_INVOICE.name
  is '发票名';
alter table T_SYS_INVOICE
  add constraint PK_SYS_INVOICE primary key (ID);

prompt
prompt Creating table T_SYS_LANDMARK
prompt =============================
prompt
create table T_SYS_LANDMARK
(
  id            NUMBER(9) not null,
  area_code     VARCHAR2(20),
  regon_id      NUMBER(9),
  landmark_name VARCHAR2(100),
  map_long      VARCHAR2(20),
  map_dim       VARCHAR2(20),
  flag          NUMBER(1) default 1
)
;
comment on table T_SYS_LANDMARK
  is '地标管理';
comment on column T_SYS_LANDMARK.id
  is 'id';
comment on column T_SYS_LANDMARK.area_code
  is '地市编码';
comment on column T_SYS_LANDMARK.regon_id
  is '区域id';
comment on column T_SYS_LANDMARK.landmark_name
  is '地标名称';
comment on column T_SYS_LANDMARK.map_long
  is '坐标long';
comment on column T_SYS_LANDMARK.map_dim
  is '坐标dim';
comment on column T_SYS_LANDMARK.flag
  is '是否显示1-显示 0-不显示 ';
alter table T_SYS_LANDMARK
  add constraint PK_T_SYS_LANDMARK primary key (ID);

prompt
prompt Creating table T_SYS_LOG
prompt ========================
prompt
create table T_SYS_LOG
(
  id          NUMBER(9) not null,
  user_id     VARCHAR2(20) not null,
  oper_time   VARCHAR2(14) not null,
  oper_type   NUMBER(3) not null,
  module      VARCHAR2(100) not null,
  description VARCHAR2(200),
  result_code VARCHAR2(10),
  ip          VARCHAR2(20),
  user_name   VARCHAR2(100),
  user_type   VARCHAR2(20),
  op_id       NUMBER(9)
)
;
comment on table T_SYS_LOG
  is '系统日志表';
comment on column T_SYS_LOG.oper_type
  is '操作类型   1-查看 2-添加 3-修改 4-删除 5-审核  6-其他';
comment on column T_SYS_LOG.result_code
  is '操作是否成功 0-成功  非0 失败';
comment on column T_SYS_LOG.user_name
  is '用户名称';
comment on column T_SYS_LOG.user_type
  is '用户类型';
comment on column T_SYS_LOG.op_id
  is '操作对象ID';
alter table T_SYS_LOG
  add constraint PK_T_SYS_LOG primary key (ID);

prompt
prompt Creating table T_SYS_LOGIN_LOG
prompt ==============================
prompt
create table T_SYS_LOGIN_LOG
(
  id           NUMBER(9) not null,
  user_name    VARCHAR2(20),
  user_id      NUMBER(9),
  ip           VARCHAR2(15),
  login_time   VARCHAR2(14),
  success_flag NUMBER(2),
  user_type    NUMBER(2)
)
;
comment on table T_SYS_LOGIN_LOG
  is '管理后台登录日志表';
comment on column T_SYS_LOGIN_LOG.success_flag
  is '1-成功 0-失败';
alter table T_SYS_LOGIN_LOG
  add constraint PK_T_SYS_LOGIN_LOG primary key (ID);

prompt
prompt Creating table T_SYS_LOGISTICS
prompt ==============================
prompt
create table T_SYS_LOGISTICS
(
  id        NUMBER(9) not null,
  name      VARCHAR2(50),
  is_valid  NUMBER(1),
  interface VARCHAR2(100),
  ename     VARCHAR2(30)
)
;
comment on table T_SYS_LOGISTICS
  is '物流信息表';
comment on column T_SYS_LOGISTICS.name
  is '品牌名称';
comment on column T_SYS_LOGISTICS.is_valid
  is '是否有效
0-无效
1-有效';
comment on column T_SYS_LOGISTICS.interface
  is '物流接口';
alter table T_SYS_LOGISTICS
  add constraint PK_T_SYS_LOGISTICS primary key (ID);

prompt
prompt Creating table T_SYS_LOGON_LOG
prompt ==============================
prompt
create table T_SYS_LOGON_LOG
(
  id          NUMBER(9) not null,
  user_code   VARCHAR2(20),
  logon_ip    VARCHAR2(20),
  logon_time  VARCHAR2(14),
  success_tag CHAR(1)
)
;
comment on table T_SYS_LOGON_LOG
  is '管理后台登录日志表,负责限制登录错误次数';
comment on column T_SYS_LOGON_LOG.success_tag
  is '0失败
1成功';
alter table T_SYS_LOGON_LOG
  add constraint PK_T_SYS_LOGON_LOG primary key (ID);

prompt
prompt Creating table T_SYS_MENU
prompt =========================
prompt
create table T_SYS_MENU
(
  menu_code  VARCHAR2(20) not null,
  menu_name  VARCHAR2(50) not null,
  menu_pcode VARCHAR2(20) not null,
  menu_url   VARCHAR2(100),
  leaf_yn    NUMBER(3) not null,
  model_code VARCHAR2(200),
  url_btns   VARCHAR2(100),
  sys_type   NUMBER(1) not null
)
;
comment on table T_SYS_MENU
  is '菜单表';
comment on column T_SYS_MENU.menu_code
  is '菜单code，唯一值';
comment on column T_SYS_MENU.menu_name
  is '菜单名称';
comment on column T_SYS_MENU.menu_pcode
  is '当前菜单父菜单code';
comment on column T_SYS_MENU.menu_url
  is '菜单指向的URL链接，没有链接可以使用#';
comment on column T_SYS_MENU.leaf_yn
  is '是否为末级标志，0-是;1否';
comment on column T_SYS_MENU.model_code
  is '标识模块,最末级菜单需要指明该字段,且在本系统内具有唯一性';
comment on column T_SYS_MENU.url_btns
  is '每个页面所拥有的按钮(add_btn,mod_btn,del_btn)';
comment on column T_SYS_MENU.sys_type
  is '每个系统有自己的菜单：0后台管理系统 1商户自服务业务门店 2商户自服务结算商户 3商户自服务渠道商';
alter table T_SYS_MENU
  add constraint PK_SYS_MENU primary key (MENU_CODE);

prompt
prompt Creating table T_SYS_POS
prompt ========================
prompt
create table T_SYS_POS
(
  id      NUMBER(8) not null,
  name    VARCHAR2(50),
  src     VARCHAR2(50),
  type    VARCHAR2(20),
  factory VARCHAR2(50)
)
;
comment on table T_SYS_POS
  is '终端配置表，基于终端来源，类型，厂家';
comment on column T_SYS_POS.name
  is '可以为空';
comment on column T_SYS_POS.type
  is '1--商户类型
2--商品类型';
alter table T_SYS_POS
  add constraint PK_T_SYS_POS primary key (ID);

prompt
prompt Creating table T_SYS_PROPERTY
prompt =============================
prompt
create table T_SYS_PROPERTY
(
  id      NUMBER(8) not null,
  content VARCHAR2(50),
  type    NUMBER(8)
)
;
comment on table T_SYS_PROPERTY
  is '属性配置相关，例如颜色、尺寸等';
comment on column T_SYS_PROPERTY.type
  is '0--单选
1--多选 2--录入';
alter table T_SYS_PROPERTY
  add constraint PK_T_SYS_PROPERTY primary key (ID);

prompt
prompt Creating table T_SYS_PROVINCE
prompt =============================
prompt
create table T_SYS_PROVINCE
(
  id         NUMBER(9) not null,
  prov_name  VARCHAR2(30) not null,
  short_name VARCHAR2(10),
  seq_val    NUMBER(3) default 999 not null
)
;
comment on table T_SYS_PROVINCE
  is '省信息表';
comment on column T_SYS_PROVINCE.id
  is '省ID';
comment on column T_SYS_PROVINCE.prov_name
  is '省名称';
comment on column T_SYS_PROVINCE.short_name
  is '省简称';
comment on column T_SYS_PROVINCE.seq_val
  is '排序值，排序使用';

prompt
prompt Creating table T_SYS_REGION
prompt ===========================
prompt
create table T_SYS_REGION
(
  id            NUMBER(9) not null,
  region_code   VARCHAR2(100),
  region_name   VARCHAR2(200),
  region_level  NUMBER(10),
  parent_region VARCHAR2(100) not null,
  short_name    VARCHAR2(50),
  region_spell  VARCHAR2(200),
  is_show       NUMBER(1) default 1,
  sort_num      NUMBER(9) default 99999,
  area_code     VARCHAR2(200)
)
;
comment on table T_SYS_REGION
  is '系统行政区域表';
comment on column T_SYS_REGION.region_code
  is '区域代码';
comment on column T_SYS_REGION.region_name
  is '区域名称';
comment on column T_SYS_REGION.region_level
  is '区域等级';
comment on column T_SYS_REGION.parent_region
  is '父区域
0-一级区域';
comment on column T_SYS_REGION.short_name
  is '简称';
comment on column T_SYS_REGION.region_spell
  is '区域拼音';
comment on column T_SYS_REGION.is_show
  is '是否显示 0-不显示 1-显示';
comment on column T_SYS_REGION.sort_num
  is '排序';
comment on column T_SYS_REGION.area_code
  is '地市码';
alter table T_SYS_REGION
  add constraint PK_SYS_REGION primary key (ID);

prompt
prompt Creating table T_SYS_ROLE
prompt =========================
prompt
create table T_SYS_ROLE
(
  id             NUMBER(9) not null,
  remark         VARCHAR2(200),
  update_user_id NUMBER(9),
  update_time    VARCHAR2(14),
  role_name      VARCHAR2(50 CHAR) not null,
  unit_id        VARCHAR2(20 CHAR)
)
;
comment on table T_SYS_ROLE
  is '角色主表';
comment on column T_SYS_ROLE.id
  is 'ID，使用序列SEQ_COMM_ID';
comment on column T_SYS_ROLE.remark
  is '备注';
comment on column T_SYS_ROLE.update_user_id
  is '创建人，匹配T_SYS_USER表中的ID字段';
comment on column T_SYS_ROLE.update_time
  is '更新时间';

prompt
prompt Creating table T_SYS_ROLE_PRIVILEGE
prompt ===================================
prompt
create table T_SYS_ROLE_PRIVILEGE
(
  id        NUMBER(9) not null,
  role_id   NUMBER(9),
  menu_code VARCHAR2(20),
  menu_btn  VARCHAR2(100)
)
;
comment on table T_SYS_ROLE_PRIVILEGE
  is '[关于权限按钮列表]:
系统有固化的权限按钮,新增,修改,删除,查看,审核..
名称对应:ADD_BTN,MOD_BTN,DEL_BTN,VIEW_BTN,AUDIT_BTN...';
comment on column T_SYS_ROLE_PRIVILEGE.id
  is 'ID';
comment on column T_SYS_ROLE_PRIVILEGE.menu_btn
  is '所拥有的按钮访问列表,格式如:ADD_BTN,MOD_BTN,DEL_BTN';
alter table T_SYS_ROLE_PRIVILEGE
  add constraint PK_T_SYS_ROLE_PRIVILEGE primary key (ID);

prompt
prompt Creating table T_SYS_SEARCH_IDX
prompt ===============================
prompt
create table T_SYS_SEARCH_IDX
(
  id          NUMBER(8) not null,
  type        VARCHAR2(10),
  type_name   VARCHAR2(20),
  update_time VARCHAR2(14),
  actor       NUMBER(8)
)
;
comment on column T_SYS_SEARCH_IDX.type
  is '类型';
comment on column T_SYS_SEARCH_IDX.type_name
  is '类型名称';
comment on column T_SYS_SEARCH_IDX.update_time
  is '更新时间';
comment on column T_SYS_SEARCH_IDX.actor
  is '创建者ID';
alter table T_SYS_SEARCH_IDX
  add constraint SYS_SEARCH_IDX primary key (ID);

prompt
prompt Creating table T_SYS_SEGMENT
prompt ============================
prompt
create table T_SYS_SEGMENT
(
  id            NUMBER(9) not null,
  segment_code  VARCHAR2(10) not null,
  operator_code VARCHAR2(50) not null,
  area_code     VARCHAR2(8) not null,
  mmsc_id       VARCHAR2(20) not null
)
;
comment on table T_SYS_SEGMENT
  is '号段表';
comment on column T_SYS_SEGMENT.id
  is '唯一ID，使用序列SEQ_SEGMENT';
comment on column T_SYS_SEGMENT.segment_code
  is '号段';
comment on column T_SYS_SEGMENT.operator_code
  is '中国移动ZGYD';
comment on column T_SYS_SEGMENT.area_code
  is '该号段归属地市，匹配T_SYS_AREA_INFO表中area_code';
comment on column T_SYS_SEGMENT.mmsc_id
  is '彩信中心编号，此处无用，适应c-product平台';
create index IDX_SEGMENT_CODE on T_SYS_SEGMENT (SEGMENT_CODE);
alter table T_SYS_SEGMENT
  add constraint PK_T_SYS_SEGMENT primary key (ID);

prompt
prompt Creating table T_SYS_SPCODE
prompt ===========================
prompt
create table T_SYS_SPCODE
(
  id      NUMBER(8) not null,
  sp_code VARCHAR2(50),
  valid   NUMBER(1)
)
;
comment on table T_SYS_SPCODE
  is '系统特服号';
comment on column T_SYS_SPCODE.sp_code
  is 'spcode';
comment on column T_SYS_SPCODE.valid
  is '0-有效 1-无效';
alter table T_SYS_SPCODE
  add constraint PK_T_SYS_SPCODE primary key (ID);

prompt
prompt Creating table T_SYS_STORE_ROLE
prompt ===============================
prompt
create table T_SYS_STORE_ROLE
(
  id             NUMBER(9) not null,
  remark         VARCHAR2(200),
  update_user_id NUMBER(9),
  update_time    VARCHAR2(14),
  role_name      VARCHAR2(50 CHAR) not null,
  shop_class     VARCHAR2(20 CHAR),
  shop_id        NUMBER(9)
)
;
comment on table T_SYS_STORE_ROLE
  is '商户自服务平台角色表';
comment on column T_SYS_STORE_ROLE.id
  is 'ID，使用序列SEQ_COMM_ID';
comment on column T_SYS_STORE_ROLE.remark
  is '备注';
comment on column T_SYS_STORE_ROLE.update_user_id
  is '创建人，匹配T_SYS_USER表中的ID字段';
comment on column T_SYS_STORE_ROLE.update_time
  is '更新时间';
comment on column T_SYS_STORE_ROLE.role_name
  is '角色名称';
comment on column T_SYS_STORE_ROLE.shop_class
  is '角色所属商户 1门店 2商户 3渠道';
comment on column T_SYS_STORE_ROLE.shop_id
  is '角色所属商户ID';

prompt
prompt Creating table T_SYS_STORE_ROLE_PRIVILEGE
prompt =========================================
prompt
create table T_SYS_STORE_ROLE_PRIVILEGE
(
  id        NUMBER(9) not null,
  role_id   NUMBER(9),
  menu_code VARCHAR2(20),
  menu_btn  VARCHAR2(100)
)
;
comment on table T_SYS_STORE_ROLE_PRIVILEGE
  is '商户自服务平台角色权限关系表';
comment on column T_SYS_STORE_ROLE_PRIVILEGE.id
  is 'ID';
comment on column T_SYS_STORE_ROLE_PRIVILEGE.menu_btn
  is '所拥有的按钮访问列表,格式如:ADD_BTN,MOD_BTN,DEL_BTN';

prompt
prompt Creating table T_SYS_TEMPLATE_EVENT
prompt ===================================
prompt
create table T_SYS_TEMPLATE_EVENT
(
  id     NUMBER(9) not null,
  code   NUMBER(6),
  type   NUMBER(1),
  tgid   NUMBER(9),
  tgname VARCHAR2(100),
  name   VARCHAR2(50),
  memo   VARCHAR2(100)
)
;
alter table T_SYS_TEMPLATE_EVENT
  add constraint PK_T_SYS_TEMPLATE_EVENT primary key (ID);

prompt
prompt Creating table T_SYS_TEMPLATE_GROUP
prompt ===================================
prompt
create table T_SYS_TEMPLATE_GROUP
(
  id    VARCHAR2(255 CHAR) not null,
  gname VARCHAR2(255 CHAR),
  memo  VARCHAR2(255 CHAR)
)
;
alter table T_SYS_TEMPLATE_GROUP
  add primary key (ID);

prompt
prompt Creating table T_SYS_TEMPLATE_GROUP_REF
prompt =======================================
prompt
create table T_SYS_TEMPLATE_GROUP_REF
(
  id  VARCHAR2(255 CHAR) not null,
  gid VARCHAR2(255 CHAR),
  tid VARCHAR2(255 CHAR)
)
;
alter table T_SYS_TEMPLATE_GROUP_REF
  add primary key (ID);

prompt
prompt Creating table T_SYS_TEMPLATE_INFO
prompt ==================================
prompt
create table T_SYS_TEMPLATE_INFO
(
  id             NUMBER(9) not null,
  tname          VARCHAR2(100),
  type           NUMBER(2),
  dataurl        VARCHAR2(200),
  filepath       VARCHAR2(200),
  outputchartset VARCHAR2(50)
)
;
comment on column T_SYS_TEMPLATE_INFO.type
  is 'type=1，数据查询路径
type=2，jsp页面路径';
comment on column T_SYS_TEMPLATE_INFO.dataurl
  is 'type=1，此字段标识数据获取URL
type=2，此字段填入需要通过httpclient静态化的jsp页面的地址';
comment on column T_SYS_TEMPLATE_INFO.outputchartset
  is 'GBK
UTF-8
ISO-8859-1';
alter table T_SYS_TEMPLATE_INFO
  add constraint PK_T_SYS_TEMPLATE_INFO primary key (ID);

prompt
prompt Creating table T_SYS_TEST_TERMINAL_ID
prompt =====================================
prompt
create table T_SYS_TEST_TERMINAL_ID
(
  id             NUMBER(9) not null,
  terminal_id    VARCHAR2(21) not null,
  owner_name     VARCHAR2(40),
  test_type      NUMBER(3) not null,
  act_id         NUMBER(9),
  unit_id        VARCHAR2(20),
  status         NUMBER(3),
  remark         VARCHAR2(200),
  update_user_id NUMBER(9),
  update_time    VARCHAR2(14)
)
;
comment on table T_SYS_TEST_TERMINAL_ID
  is '平台测试用户表';
comment on column T_SYS_TEST_TERMINAL_ID.terminal_id
  is '测试手机号码';
comment on column T_SYS_TEST_TERMINAL_ID.owner_name
  is '机主姓名';
comment on column T_SYS_TEST_TERMINAL_ID.test_type
  is '测试类型：0、全局测试号码；1、业务测试号码；2、彩信发送测试号码';
comment on column T_SYS_TEST_TERMINAL_ID.act_id
  is '活动ID';
comment on column T_SYS_TEST_TERMINAL_ID.unit_id
  is '所属单位';
comment on column T_SYS_TEST_TERMINAL_ID.status
  is '状态：0：无效测试用户；1：测试用户';
comment on column T_SYS_TEST_TERMINAL_ID.remark
  is '备注';
comment on column T_SYS_TEST_TERMINAL_ID.update_user_id
  is '创建者ID';
comment on column T_SYS_TEST_TERMINAL_ID.update_time
  is '创建时间';
alter table T_SYS_TEST_TERMINAL_ID
  add constraint PK_T_SYS_TEST_TERMINAL_ID primary key (ID);

prompt
prompt Creating table T_SYS_TYPE
prompt =========================
prompt
create table T_SYS_TYPE
(
  id       NUMBER(20) not null,
  p_id     NUMBER(20),
  name     VARCHAR2(100),
  type     NUMBER(1),
  is_valid NUMBER(1) default 1
)
;
comment on table T_SYS_TYPE
  is '分类表，存储商户分类和商品分类，以树状结构存储';
comment on column T_SYS_TYPE.p_id
  is '根的话该字段为0';
comment on column T_SYS_TYPE.type
  is '1--商户类型
2--商品类型';
comment on column T_SYS_TYPE.is_valid
  is '0-无效 1有效';
alter table T_SYS_TYPE
  add constraint PK_T_SYS_TYPE primary key (ID);

prompt
prompt Creating table T_SYS_TYPE_ITEM_PARAM
prompt ====================================
prompt
create table T_SYS_TYPE_ITEM_PARAM
(
  id            NUMBER(8) not null,
  type_id       NUMBER(8),
  param_key     VARCHAR2(20),
  param_value   VARCHAR2(80),
  rank          NUMBER(8),
  param_type    NUMBER(1),
  required_flag NUMBER(1),
  search_flag   NUMBER(1) default 1,
  shop_id       NUMBER(9),
  shop_class    NUMBER(1)
)
;
comment on table T_SYS_TYPE_ITEM_PARAM
  is '分类对应商品规格参数模板表。
由商户自己创建或者后台定义的分类规格参数模板都在该表中定义';
comment on column T_SYS_TYPE_ITEM_PARAM.param_value
  is '规格参数值,用分号分割';
comment on column T_SYS_TYPE_ITEM_PARAM.rank
  is '可以根据该值排序所有参数值，否则默认根据录入顺序';
comment on column T_SYS_TYPE_ITEM_PARAM.param_type
  is '参数类型
1-单选
2-复选
3-自填';
comment on column T_SYS_TYPE_ITEM_PARAM.required_flag
  is '是否必填
0-no
1-必填';
comment on column T_SYS_TYPE_ITEM_PARAM.search_flag
  is '是否参与检索
0-否
1-是';
comment on column T_SYS_TYPE_ITEM_PARAM.shop_id
  is '商户ID';
comment on column T_SYS_TYPE_ITEM_PARAM.shop_class
  is '1--业务门店
2--商户
3--渠道商';
alter table T_SYS_TYPE_ITEM_PARAM
  add constraint PK_T_SYS_TYPE_ITEM_PARAM primary key (ID);

prompt
prompt Creating table T_SYS_UNIT
prompt =========================
prompt
create table T_SYS_UNIT
(
  id             NUMBER(9) not null,
  name           VARCHAR2(100) not null,
  area_code      VARCHAR2(8),
  unit_type      NUMBER(1) not null,
  remark         VARCHAR2(200),
  flag           NUMBER(1) not null,
  update_user_id NUMBER(9) not null,
  update_time    VARCHAR2(14) not null,
  prov_id        NUMBER(9),
  parent_unit_id NUMBER(9)
)
;
comment on column T_SYS_UNIT.id
  is '单位ID';
comment on column T_SYS_UNIT.name
  is '单位名称';
comment on column T_SYS_UNIT.area_code
  is '单位归属地市，匹配T_SYS_regon表中AREA_CODE';
comment on column T_SYS_UNIT.unit_type
  is '单位分类，1-电商基地单位
2-省单位
3-地市单位';
comment on column T_SYS_UNIT.remark
  is '备注';
comment on column T_SYS_UNIT.flag
  is '单位标识，0表示正常，9表示注销';
comment on column T_SYS_UNIT.update_user_id
  is '单位变更用户ID，匹配T_SYS_USER表中ID';
comment on column T_SYS_UNIT.update_time
  is '单位更新时间';
comment on column T_SYS_UNIT.prov_id
  is '单位所属省ID';

prompt
prompt Creating table T_SYS_USER
prompt =========================
prompt
create table T_SYS_USER
(
  id              NUMBER(9) not null,
  user_code       VARCHAR2(20) not null,
  user_pwd        VARCHAR2(60) not null,
  user_name       VARCHAR2(100) not null,
  terminal_id     VARCHAR2(30),
  email           VARCHAR2(100),
  remark          VARCHAR2(200),
  valid_time      VARCHAR2(8),
  create_time     VARCHAR2(14) not null,
  change_pwd_time VARCHAR2(14),
  update_time     VARCHAR2(14) not null,
  update_user_id  NUMBER(9) not null,
  lock_status     NUMBER(3) default 0 not null,
  status          NUMBER(3) default 1 not null,
  flag            NUMBER(3) not null,
  unit_id         NUMBER(9) default 0 not null
)
;
comment on table T_SYS_USER
  is '1.系统所有用户在一张表内维护

2.用户类别的说明:运营商增加本单位用户时,需选择[2]-普通用户,[3]-客服用户
  客户系统人员有固化的权限.';
comment on column T_SYS_USER.id
  is 'ID';
comment on column T_SYS_USER.user_code
  is '全平台唯一，用户帐号，用于登录';
comment on column T_SYS_USER.user_pwd
  is '密码';
comment on column T_SYS_USER.user_name
  is '用户真实姓名';
comment on column T_SYS_USER.terminal_id
  is '终端号码';
comment on column T_SYS_USER.email
  is 'EMAIL地址';
comment on column T_SYS_USER.remark
  is '描述';
comment on column T_SYS_USER.valid_time
  is '帐户有效期，8位日期，如果为空则表示永久有效';
comment on column T_SYS_USER.create_time
  is '用户创建时间';
comment on column T_SYS_USER.change_pwd_time
  is '用户修改密码时间';
comment on column T_SYS_USER.update_time
  is '账号更新时间';
comment on column T_SYS_USER.update_user_id
  is '更新用户ID，匹配T_SYS_USER_V2表中的ID字段';
comment on column T_SYS_USER.lock_status
  is '帐号锁定状态：0,正常;1,锁定';
comment on column T_SYS_USER.status
  is '[1]:正常,[2]:暂停,[3]:删除';
comment on column T_SYS_USER.flag
  is '[0]:超级管理员,[1]:单位管理员 [2]单位普通人员 [3]';
comment on column T_SYS_USER.unit_id
  is '该用户归属单位，匹配表T_UNIT中ID';

prompt
prompt Creating table T_SYS_USER_REGION
prompt ================================
prompt
create table T_SYS_USER_REGION
(
  id          NUMBER(9) not null,
  user_id     NUMBER(9),
  region_code VARCHAR2(100)
)
;
comment on table T_SYS_USER_REGION
  is '系统用户区域表
该表主要是针对数据权限进行管理。
该用户可以操作的区域进行管理';
alter table T_SYS_USER_REGION
  add constraint PK_T_SYS_USER_REGON primary key (ID);

prompt
prompt Creating table T_SYS_USER_ROLE
prompt ==============================
prompt
create table T_SYS_USER_ROLE
(
  user_id NUMBER(9) not null,
  role_id NUMBER(9) not null,
  id      NUMBER(9) not null
)
;
comment on table T_SYS_USER_ROLE
  is '用户与角色多对多关系';
comment on column T_SYS_USER_ROLE.user_id
  is '用户ID';
comment on column T_SYS_USER_ROLE.role_id
  is '角色ID';
alter table T_SYS_USER_ROLE
  add constraint PK_T_SYS_USER_ROLE primary key (ID)
  disable;

prompt
prompt Creating table T_TIMELABLE
prompt ==========================
prompt
create table T_TIMELABLE
(
  stime VARCHAR2(12)
)
;
comment on table T_TIMELABLE
  is '记录统计时间点的表，用于P_STAT_ITEM_SALE_EXT存储过程';
comment on column T_TIMELABLE.stime
  is '下次job执行时，汇总从该时间开始往后30分钟内的数据，每次汇总完，自动更新该时间';

prompt
prompt Creating table T_TMP1
prompt =====================
prompt
create table T_TMP1
(
  store_id NUMBER,
  d        VARCHAR2(8)
)
;

prompt
prompt Creating table T_TP_CONF
prompt ========================
prompt
create table T_TP_CONF
(
  unit_id          VARCHAR2(20) not null,
  passwd           VARCHAR2(20) not null,
  sms_sp_code      VARCHAR2(21),
  mms_sp_code      VARCHAR2(21),
  sms_flux_limited NUMBER(8) default 0 not null,
  mms_flux_limited NUMBER(8) default 0 not null,
  url              VARCHAR2(200),
  status           NUMBER(1) default 0 not null,
  ip               VARCHAR2(200),
  id               NUMBER(9) not null,
  name             VARCHAR2(40)
)
;
comment on column T_TP_CONF.unit_id
  is 'TP编号';
comment on column T_TP_CONF.passwd
  is 'TP密码（明文）';
comment on column T_TP_CONF.sms_sp_code
  is '短信特服号';
comment on column T_TP_CONF.mms_sp_code
  is '彩信特服号';
comment on column T_TP_CONF.sms_flux_limited
  is '每分钟短信下发限制，0为不许发送，-1为不限';
comment on column T_TP_CONF.mms_flux_limited
  is '每分钟彩信下发限制，0为不许发送，-1为不限';
comment on column T_TP_CONF.url
  is 'TP接收上行的url（目前无用）';
comment on column T_TP_CONF.status
  is '审核状态，0待审核，1审核通过';
comment on column T_TP_CONF.ip
  is '客户端IP，多个用半角逗号分隔，没有限制时填空';
comment on column T_TP_CONF.id
  is '主键';
comment on column T_TP_CONF.name
  is 'TP名称';
alter table T_TP_CONF
  add constraint PK_T_TP_CONF primary key (ID);

prompt
prompt Creating table T_TP_REPORT_LOG
prompt ==============================
prompt
create table T_TP_REPORT_LOG
(
  tp_msg_id   VARCHAR2(50) not null,
  msg_id      VARCHAR2(30),
  status      VARCHAR2(30),
  update_time VARCHAR2(14) not null,
  type        NUMBER(1) default 1 not null,
  need_report VARCHAR2(10) default 'false' not null
)
;
comment on column T_TP_REPORT_LOG.tp_msg_id
  is 'TP的MsgId，主键';
comment on column T_TP_REPORT_LOG.msg_id
  is '网关的MsgId';
comment on column T_TP_REPORT_LOG.status
  is '发送状态';
comment on column T_TP_REPORT_LOG.update_time
  is '更新时间，14位时间';
comment on column T_TP_REPORT_LOG.type
  is '类型，1为短信，2为彩信';
comment on column T_TP_REPORT_LOG.need_report
  is '是否需要状态报告，true=需要，其他不需要';
alter table T_TP_REPORT_LOG
  add constraint PK_T_TP_REPORT_LOG primary key (TP_MSG_ID);

prompt
prompt Creating table T_UMC_PUBLIC_MSG
prompt ===============================
prompt
create table T_UMC_PUBLIC_MSG
(
  id              NUMBER(9),
  create_time     VARCHAR2(14),
  message_content VARCHAR2(1000),
  status          NUMBER(3)
)
;
comment on table T_UMC_PUBLIC_MSG
  is '用户消息中心-公共消息';
comment on column T_UMC_PUBLIC_MSG.id
  is '消息Id';
comment on column T_UMC_PUBLIC_MSG.create_time
  is '消息创建时间，14位时间';
comment on column T_UMC_PUBLIC_MSG.message_content
  is '消息内容';
comment on column T_UMC_PUBLIC_MSG.status
  is '0普通、1已读、2删除，10以上自定义';
create index IDX_UMC_PUBLIC_MSG on T_UMC_PUBLIC_MSG (CREATE_TIME);

prompt
prompt Creating table T_UMC_USER_MSG
prompt =============================
prompt
create table T_UMC_USER_MSG
(
  id              NUMBER(9),
  user_id         NUMBER(9),
  create_time     VARCHAR2(14),
  message_content VARCHAR2(1000),
  status          NUMBER(3)
)
;
comment on table T_UMC_USER_MSG
  is '用户消息中心-用户消息';
comment on column T_UMC_USER_MSG.status
  is '0普通、1已读、2删除';
create index IDX_UMC_USER_MSG_01 on T_UMC_USER_MSG (CREATE_TIME, USER_ID);

prompt
prompt Creating table T_UMC_USER_MSG_STATUS
prompt ====================================
prompt
create table T_UMC_USER_MSG_STATUS
(
  user_id NUMBER(9),
  msg_id  NUMBER(9),
  status  NUMBER(3)
)
;
comment on table T_UMC_USER_MSG_STATUS
  is '用户消息中心-用户消息状态';
comment on column T_UMC_USER_MSG_STATUS.user_id
  is '用户Id';
comment on column T_UMC_USER_MSG_STATUS.msg_id
  is '消息Id';
comment on column T_UMC_USER_MSG_STATUS.status
  is '0普通、1已读、2删除';
create index IDX_UMC_USER_MSG_STATUS_01 on T_UMC_USER_MSG_STATUS (MSG_ID);

prompt
prompt Creating table T_USER_CUSTOM
prompt ============================
prompt
create table T_USER_CUSTOM
(
  id          NUMBER(12) not null,
  terminal_id VARCHAR2(21) not null,
  product_id  VARCHAR2(20) not null,
  status      NUMBER(1) default 0 not null,
  update_time VARCHAR2(14) not null,
  custom_time VARCHAR2(14) not null
)
;
comment on column T_USER_CUSTOM.id
  is '主键';
comment on column T_USER_CUSTOM.terminal_id
  is '号码';
comment on column T_USER_CUSTOM.product_id
  is '定制的业务编号，Boss的产品ID或Misc的业务ID  106003-红钻';
comment on column T_USER_CUSTOM.status
  is '0正常，1暂停';
comment on column T_USER_CUSTOM.update_time
  is '14位时间，记录更新时间';
comment on column T_USER_CUSTOM.custom_time
  is '定制时间（来自BOSS或MISC）';
alter table T_USER_CUSTOM
  add constraint PK_USER_CUSTOM primary key (ID);

prompt
prompt Creating table T_USER_CUSTOM_BAK
prompt ================================
prompt
create table T_USER_CUSTOM_BAK
(
  id          NUMBER(12) not null,
  terminal_id VARCHAR2(21) not null,
  product_id  VARCHAR2(20) not null,
  status      NUMBER(1) not null,
  update_time VARCHAR2(14) not null,
  custom_time VARCHAR2(14) not null
)
;

prompt
prompt Creating table T_USER_CUSTOM_LOG
prompt ================================
prompt
create table T_USER_CUSTOM_LOG
(
  id           NUMBER(12) not null,
  operate_type NUMBER(1) not null,
  terminal_id  VARCHAR2(21) not null,
  product_id   VARCHAR2(20) not null,
  custom_time  VARCHAR2(14),
  cancel_time  VARCHAR2(14),
  operate_time VARCHAR2(14) not null,
  reason       VARCHAR2(200)
)
;
comment on column T_USER_CUSTOM_LOG.id
  is '主键';
comment on column T_USER_CUSTOM_LOG.operate_type
  is '操作类型，1定制，2退定，3暂停，4恢复';
comment on column T_USER_CUSTOM_LOG.terminal_id
  is '号码';
comment on column T_USER_CUSTOM_LOG.product_id
  is '产品ID';
comment on column T_USER_CUSTOM_LOG.custom_time
  is '定制时间';
comment on column T_USER_CUSTOM_LOG.cancel_time
  is '退定时间';
comment on column T_USER_CUSTOM_LOG.operate_time
  is '操作时间';
comment on column T_USER_CUSTOM_LOG.reason
  is '操作原因（操作的补充说明）';
alter table T_USER_CUSTOM_LOG
  add constraint PK_USER_CUSTOM_LOG primary key (ID);

prompt
prompt Creating table T_USER_EXCHANGE_POINT_LOG
prompt ========================================
prompt
create table T_USER_EXCHANGE_POINT_LOG
(
  id          NUMBER(10) not null,
  terminal_id VARCHAR2(20),
  amount      NUMBER(5),
  user_ip     VARCHAR2(30),
  exc_date    VARCHAR2(14),
  r_code      NUMBER(2),
  r_msg       VARCHAR2(200),
  exc_percent NUMBER(2)
)
;
comment on table T_USER_EXCHANGE_POINT_LOG
  is '商城币兑换日志表';
comment on column T_USER_EXCHANGE_POINT_LOG.terminal_id
  is '手机号码';
comment on column T_USER_EXCHANGE_POINT_LOG.amount
  is '兑换商城币数';
comment on column T_USER_EXCHANGE_POINT_LOG.user_ip
  is '用户ip';
comment on column T_USER_EXCHANGE_POINT_LOG.exc_date
  is '兑换时间';
comment on column T_USER_EXCHANGE_POINT_LOG.r_code
  is '兑换结果code';
comment on column T_USER_EXCHANGE_POINT_LOG.r_msg
  is '兑换结果描述';
comment on column T_USER_EXCHANGE_POINT_LOG.exc_percent
  is '兑换比例';
alter table T_USER_EXCHANGE_POINT_LOG
  add constraint PK_T_USER_EXCHANGE_POINT_LOG primary key (ID);

prompt
prompt Creating table T_USER_FAVORITE_ITEM
prompt ===================================
prompt
create table T_USER_FAVORITE_ITEM
(
  id      NUMBER(8) not null,
  user_id NUMBER(19),
  f_type  NUMBER(1),
  f_id    NUMBER(8),
  f_time  VARCHAR2(14)
)
;
comment on column T_USER_FAVORITE_ITEM.f_type
  is '1：商品
2：商户';
alter table T_USER_FAVORITE_ITEM
  add constraint PK_T_USER_FAVORITE_ITEM primary key (ID);

prompt
prompt Creating table T_VERIFY_CARD_CODE_COMMON
prompt ========================================
prompt
create table T_VERIFY_CARD_CODE_COMMON
(
  id            NUMBER(12) not null,
  card_id       VARCHAR2(30) not null,
  card_key      VARCHAR2(256) not null,
  store_id      VARCHAR2(20) not null,
  item_id       VARCHAR2(30) not null,
  create_date   CHAR(14),
  status        NUMBER(3),
  generate_date CHAR(14),
  order_id      VARCHAR2(30),
  user_id       VARCHAR2(30)
)
;
comment on table T_VERIFY_CARD_CODE_COMMON
  is '存放商户预生成的卡密';
comment on column T_VERIFY_CARD_CODE_COMMON.item_id
  is '0：验证码可对应所有商品';
comment on column T_VERIFY_CARD_CODE_COMMON.status
  is '0：已制码，未验证
1：已撤销
2：使用中
3：已使用
4：已过期
100: 初始化，未使用';
create index INDEX2_VERIFY_CARD_CODE_COMMON on T_VERIFY_CARD_CODE_COMMON (ORDER_ID);
create index INDEX_VERIFY_CARD_CODE_COMMON on T_VERIFY_CARD_CODE_COMMON (STORE_ID, ITEM_ID, STATUS);
alter table T_VERIFY_CARD_CODE_COMMON
  add constraint PK_T_VERIFY_CARD_CODE_COMMON primary key (ID);

prompt
prompt Creating table T_VERIFY_CODE_INFO
prompt =================================
prompt
create table T_VERIFY_CODE_INFO
(
  order_id      VARCHAR2(30) not null,
  item_order_id NUMBER(19),
  act_order_id  NUMBER(19),
  code          VARCHAR2(256),
  code_2d       VARCHAR2(100),
  code_status   NUMBER(3),
  trans_date    CHAR(14),
  valid_date    CHAR(14),
  expire_date   CHAR(14),
  valid_times   NUMBER(3),
  remain_times  NUMBER(3),
  terminal_id   VARCHAR2(15),
  item_id       VARCHAR2(30),
  item_name     VARCHAR2(100),
  item_quantity NUMBER(9),
  total_price   NUMBER(10),
  platform_id   VARCHAR2(15),
  store_id      VARCHAR2(20),
  sms_content   VARCHAR2(300)
)
;
comment on column T_VERIFY_CODE_INFO.code_status
  is '0：正常
1：已撤销
2：使用中
3：已使用
4：已过期';
comment on column T_VERIFY_CODE_INFO.platform_id
  is 'FOUNDER：方正
STORE：商户
CPLATFORM：宽连';
create index INDEX_VERIFY_CODE_INFO on T_VERIFY_CODE_INFO (ACT_ORDER_ID);
alter table T_VERIFY_CODE_INFO
  add constraint PK_T_VERIFY_CODE_INFO primary key (ORDER_ID);

prompt
prompt Creating table T_VERIFY_CONSUME
prompt ===============================
prompt
create table T_VERIFY_CONSUME
(
  id              NUMBER(10) not null,
  order_id        VARCHAR2(30) not null,
  ship_id         VARCHAR2(20),
  pos_id          VARCHAR2(30),
  verify_channel  VARCHAR2(10),
  verify_date     CHAR(14),
  use_times       NUMBER(3),
  verify_trans_id VARCHAR2(30),
  is_reverse      NUMBER(1),
  verify_user     VARCHAR2(20),
  verify_status   VARCHAR2(10)
)
;
comment on column T_VERIFY_CONSUME.verify_channel
  is 'WEB，WAP，客户端，终端，短信';
comment on column T_VERIFY_CONSUME.is_reverse
  is '0：未冲正
1：已冲正';
create index INDEX_VERIFY_CONSUME on T_VERIFY_CONSUME (ORDER_ID);
alter table T_VERIFY_CONSUME
  add constraint PK_T_VERIFY_CONSUME primary key (ID);

prompt
prompt Creating table T_VERIFY_CONSUME_NOTIFY
prompt ======================================
prompt
create table T_VERIFY_CONSUME_NOTIFY
(
  id          NUMBER(10) not null,
  order_id    VARCHAR2(30) not null,
  ship_id     VARCHAR2(20),
  pos_id      VARCHAR2(30),
  pos_seq     VARCHAR2(30),
  verify_date CHAR(14),
  terminal_id VARCHAR2(15)
)
;
create index INDEX_VERIFY_CONSUME_NOTIFY on T_VERIFY_CONSUME_NOTIFY (ORDER_ID);
alter table T_VERIFY_CONSUME_NOTIFY
  add constraint PK_T_VERIFY_CONSUME_NOTIFY primary key (ID);

prompt
prompt Creating table T_VERIFY_CPLATFORM_CODE
prompt ======================================
prompt
create table T_VERIFY_CPLATFORM_CODE
(
  code_md5      VARCHAR2(32) not null,
  code_rsa      VARCHAR2(256),
  store_id      VARCHAR2(20),
  item_id       VARCHAR2(30),
  generate_date CHAR(14),
  status        NUMBER(3),
  order_id      VARCHAR2(30),
  source        NUMBER(1),
  batch_no      VARCHAR2(20),
  user_id       VARCHAR2(30),
  verify_times  NUMBER(5),
  remain_times  NUMBER(5),
  terminal_id   VARCHAR2(15),
  code_type     VARCHAR2(20)
)
;
comment on table T_VERIFY_CPLATFORM_CODE
  is '存放宽连自有码';
comment on column T_VERIFY_CPLATFORM_CODE.item_id
  is '0：验证码可对应所有商品';
comment on column T_VERIFY_CPLATFORM_CODE.status
  is '0：已制码，未验证
1：已撤销
2：使用中
3：已使用
4：已过期
100: 初始化，未使用';
comment on column T_VERIFY_CPLATFORM_CODE.order_id
  is '集采预生成订单编号，关联验证表记录（SEQ_ACT_ORDER_SUB）';
comment on column T_VERIFY_CPLATFORM_CODE.source
  is '1：集采，2：制码';
comment on column T_VERIFY_CPLATFORM_CODE.code_type
  is '团购，O2O商城， 彩信折扣券， 短信折扣券';
create index INDEX2_VERIFY_CPLATFORM_CODE on T_VERIFY_CPLATFORM_CODE (STORE_ID, ITEM_ID);
create index INDEX_VERIFY_CPLATFORM_CODE on T_VERIFY_CPLATFORM_CODE (ORDER_ID);
alter table T_VERIFY_CPLATFORM_CODE
  add constraint PK_T_VERIFY_CPLATFORM_CODE primary key (CODE_MD5);

prompt
prompt Creating table T_VERIFY_DELAY
prompt =============================
prompt
create table T_VERIFY_DELAY
(
  id           NUMBER(9) not null,
  trans_time   CHAR(14),
  code_tye     NUMBER(1),
  code_content VARCHAR2(100),
  expire_date  CHAR(14),
  platform_id  VARCHAR2(15),
  store_id     VARCHAR2(20)
)
;
comment on column T_VERIFY_DELAY.code_tye
  is '1：订单编号
2：子订单编号
3：辅助码串';
comment on column T_VERIFY_DELAY.platform_id
  is 'FOUNDER：方正
STORE：商户
CPLATFORM：宽连';
alter table T_VERIFY_DELAY
  add constraint PK_T_VERIFY_DELAY primary key (ID);

prompt
prompt Creating table T_VERIFY_FOUNDER_STORE
prompt =====================================
prompt
create table T_VERIFY_FOUNDER_STORE
(
  founder_store_id VARCHAR2(30) not null,
  founder_shop_id  VARCHAR2(300),
  local_store_id   VARCHAR2(30) not null
)
;
create index INDEX_VERIFY_FOUNDER_STORE on T_VERIFY_FOUNDER_STORE (LOCAL_STORE_ID);
alter table T_VERIFY_FOUNDER_STORE
  add constraint PK_T_VERIFY_FOUNDER_STORE primary key (FOUNDER_STORE_ID);

prompt
prompt Creating table T_VERIFY_FOUNDER_TRANS
prompt =====================================
prompt
create table T_VERIFY_FOUNDER_TRANS
(
  order_id VARCHAR2(30) not null,
  trans_id VARCHAR2(30) not null
)
;
alter table T_VERIFY_FOUNDER_TRANS
  add constraint PK_T_VERIFY_FOUNDER_TRANS primary key (TRANS_ID);

prompt
prompt Creating table T_VERIFY_QUERY
prompt =============================
prompt
create table T_VERIFY_QUERY
(
  id           NUMBER(9) not null,
  trans_time   CHAR(14),
  code_tye     NUMBER(1),
  code_content VARCHAR2(100),
  platform_id  VARCHAR2(15),
  store_id     VARCHAR2(20)
)
;
comment on column T_VERIFY_QUERY.code_tye
  is '1：订单编号
2：子订单编号
3：辅助码串';
comment on column T_VERIFY_QUERY.platform_id
  is 'FOUNDER：方正
STORE：商户
CPLATFORM：宽连';
alter table T_VERIFY_QUERY
  add constraint PK_T_VERIFY_QUERY primary key (ID);

prompt
prompt Creating table T_VERIFY_RESEND
prompt ==============================
prompt
create table T_VERIFY_RESEND
(
  id           NUMBER(9) not null,
  trans_time   CHAR(14),
  code_tye     NUMBER(1),
  code_content VARCHAR2(100),
  platform_id  VARCHAR2(15),
  store_id     VARCHAR2(20)
)
;
comment on column T_VERIFY_RESEND.code_tye
  is '1：订单编号
2：子订单编号
3：辅助码串';
comment on column T_VERIFY_RESEND.platform_id
  is 'FOUNDER：方正
STORE：商户
CPLATFORM：宽连';
alter table T_VERIFY_RESEND
  add constraint PK_T_VERIFY_RESEND primary key (ID);

prompt
prompt Creating table T_VERIFY_REVERSE
prompt ===============================
prompt
create table T_VERIFY_REVERSE
(
  id              NUMBER(10) not null,
  order_id        VARCHAR2(30) not null,
  ship_id         VARCHAR2(20),
  pos_id          VARCHAR2(30),
  reverse_channel VARCHAR2(10),
  reverse_date    CHAR(14),
  reverse_user    VARCHAR2(20),
  reverse_status  VARCHAR2(10)
)
;
comment on column T_VERIFY_REVERSE.reverse_channel
  is 'WEB，WAP，客户端，终端，短信';
create index INDEX_VERIFY_REVERSE on T_VERIFY_REVERSE (ORDER_ID);
alter table T_VERIFY_REVERSE
  add constraint PK_T_VERIFY_REVERSE primary key (ID);

prompt
prompt Creating table T_VERIFY_REVERSE_NOTIFY
prompt ======================================
prompt
create table T_VERIFY_REVERSE_NOTIFY
(
  id           NUMBER(10) not null,
  order_id     VARCHAR2(30) not null,
  suborder_id  VARCHAR2(30),
  ship_id      VARCHAR2(20),
  pos_id       VARCHAR2(30),
  pos_seq      VARCHAR2(30),
  reverse_date CHAR(14),
  terminal_id  VARCHAR2(15)
)
;
create index INDEX_VERIFY_REVERSE_NOTIFY on T_VERIFY_REVERSE_NOTIFY (ORDER_ID);
alter table T_VERIFY_REVERSE_NOTIFY
  add constraint PK_T_VERIFY_REVERSE_NOTIFY primary key (ID);

prompt
prompt Creating table T_VERIFY_REVOKE
prompt ==============================
prompt
create table T_VERIFY_REVOKE
(
  id           NUMBER(9) not null,
  trans_time   CHAR(14),
  code_tye     NUMBER(1),
  code_content VARCHAR2(100),
  platform_id  VARCHAR2(15),
  store_id     VARCHAR2(20)
)
;
comment on column T_VERIFY_REVOKE.code_tye
  is '1：订单编号
2：子订单编号
3：辅助码串';
comment on column T_VERIFY_REVOKE.platform_id
  is 'FOUNDER：方正
STORE：商户
CPLATFORM：宽连';
alter table T_VERIFY_REVOKE
  add constraint PK_T_VERIFY_REVOKE primary key (ID);

prompt
prompt Creating table T_VERIFY_STORE_CODE_COMMON
prompt =========================================
prompt
create table T_VERIFY_STORE_CODE_COMMON
(
  code_md5      VARCHAR2(32) not null,
  code_rsa      VARCHAR2(256),
  store_id      VARCHAR2(20) not null,
  item_id       VARCHAR2(30) not null,
  create_date   CHAR(14),
  status        NUMBER(3),
  generate_date CHAR(14),
  verify_date   CHAR(14),
  order_id      VARCHAR2(30),
  user_id       VARCHAR2(30)
)
;
comment on table T_VERIFY_STORE_CODE_COMMON
  is '存放商户预生成的验证码';
comment on column T_VERIFY_STORE_CODE_COMMON.item_id
  is '0：验证码可对应所有商品';
comment on column T_VERIFY_STORE_CODE_COMMON.status
  is '0：已制码，未验证
1：已撤销
2：使用中
3：已使用
4：已过期
100: 初始化，未使用';
create index INDEX2_VERIFY_STORE_CODE_COMMO on T_VERIFY_STORE_CODE_COMMON (ORDER_ID);
create index INDEX_VERIFY_STORE_CODE_COMMON on T_VERIFY_STORE_CODE_COMMON (STORE_ID, ITEM_ID, STATUS);
alter table T_VERIFY_STORE_CODE_COMMON
  add constraint PK_T_VERIFY_STORE_CODE_COMMON primary key (CODE_MD5);

prompt
prompt Creating table T_WEB_ACCESS_LOG
prompt ===============================
prompt
create table T_WEB_ACCESS_LOG
(
  id                NUMBER(9) not null,
  terminal_id       VARCHAR2(11),
  custom_id         VARCHAR2(20),
  access_ip         VARCHAR2(20),
  access_start_time VARCHAR2(20) not null,
  access_stop_time  VARCHAR2(20),
  settle_time       VARCHAR2(10),
  remark            VARCHAR2(200),
  src               VARCHAR2(10),
  sessionid         VARCHAR2(200)
)
;
comment on column T_WEB_ACCESS_LOG.terminal_id
  is '用户手机号';
comment on column T_WEB_ACCESS_LOG.custom_id
  is '用户会员编号';
comment on column T_WEB_ACCESS_LOG.access_ip
  is '访问IP';
comment on column T_WEB_ACCESS_LOG.access_start_time
  is '访问开始时间';
comment on column T_WEB_ACCESS_LOG.access_stop_time
  is '访问结束时间';
comment on column T_WEB_ACCESS_LOG.settle_time
  is '停留时间';
comment on column T_WEB_ACCESS_LOG.src
  is '来源：wap网站/web网站';
create index IDX_WEB_ACCESSS_LOG on T_WEB_ACCESS_LOG (SESSIONID);

prompt
prompt Creating table USER_CUSTOM
prompt ==========================
prompt
create table USER_CUSTOM
(
  act_code        VARCHAR2(30) not null,
  terminal_id     VARCHAR2(21) not null,
  fee_terminal_id VARCHAR2(21),
  keyword1        VARCHAR2(20),
  keyword2        VARCHAR2(20),
  order_time      VARCHAR2(14),
  area_code       VARCHAR2(4),
  operator_code   VARCHAR2(4),
  fee_month       VARCHAR2(14),
  send_flag       VARCHAR2(8),
  service_id      VARCHAR2(30),
  fee_type        VARCHAR2(2),
  fee_code        VARCHAR2(10),
  xxly            CHAR(1),
  liveflag        CHAR(1),
  mask            CHAR(1)
)
;

prompt
prompt Creating table XWFEE_ID2KL_FEE_ID
prompt =================================
prompt
create table XWFEE_ID2KL_FEE_ID
(
  xw_fee_id VARCHAR2(60) not null,
  kl_fee_id NUMBER(9)
)
;
alter table XWFEE_ID2KL_FEE_ID
  add primary key (XW_FEE_ID);

prompt
prompt Creating table XWMEMBER_ID2KL_MEMBER_ID
prompt =======================================
prompt
create table XWMEMBER_ID2KL_MEMBER_ID
(
  xw_store_member_id VARCHAR2(60) not null,
  kl_store_member_id NUMBER(9)
)
;
alter table XWMEMBER_ID2KL_MEMBER_ID
  add constraint PK_XWMEMBER_ID2KL primary key (XW_STORE_MEMBER_ID);

prompt
prompt Creating table XW_CATEGORY_ID2KL_CATEGORY_ID
prompt ============================================
prompt
create table XW_CATEGORY_ID2KL_CATEGORY_ID
(
  xw_category_id VARCHAR2(60),
  kl_category_id NUMBER(8)
)
;

prompt
prompt Creating table XW_FEE_ID2KL_FEE_ID
prompt ==================================
prompt
create table XW_FEE_ID2KL_FEE_ID
(
  merc_id      VARCHAR2(30),
  prod_typ     VARCHAR2(20),
  fee_rate1    VARCHAR2(200),
  t_sys_fee_id NUMBER
)
;

prompt
prompt Creating table XW_PRODUCT_ID2KL_PRODUCT_ID
prompt ==========================================
prompt
create table XW_PRODUCT_ID2KL_PRODUCT_ID
(
  xw_product_id VARCHAR2(60) not null,
  kl_product_id NUMBER(8)
)
;
alter table XW_PRODUCT_ID2KL_PRODUCT_ID
  add primary key (XW_PRODUCT_ID);

prompt
prompt Creating table XW_PRODUCT_ID2_PRODUCT_ID
prompt ========================================
prompt
create table XW_PRODUCT_ID2_PRODUCT_ID
(
  xw_product_id VARCHAR2(32),
  product_id    NUMBER(8)
)
;

prompt
prompt Creating table XW_STORE_ID2KL_STORE_ID
prompt ======================================
prompt
create table XW_STORE_ID2KL_STORE_ID
(
  xw_store_id VARCHAR2(60),
  kl_store_id NUMBER(8)
)
;

prompt
prompt Creating table XW_STORE_ID2_STORE_ID
prompt ====================================
prompt
create table XW_STORE_ID2_STORE_ID
(
  xw_store_id VARCHAR2(32),
  store_id    NUMBER(9)
)
;

prompt
prompt Creating table XW_ST_CAT_ID2KL_ST_CAT_ID
prompt ========================================
prompt
create table XW_ST_CAT_ID2KL_ST_CAT_ID
(
  xw_store_category_id VARCHAR2(60),
  kl_store_category_id NUMBER(8)
)
;

prompt
prompt Creating table XW_ST_TEMP_ID2KL_ST_TEMP_ID
prompt ==========================================
prompt
create table XW_ST_TEMP_ID2KL_ST_TEMP_ID
(
  xw_store_temp_id VARCHAR2(60),
  kl_store_temp_id NUMBER(8)
)
;

prompt
prompt Creating sequence HIBERNATE_SEQUENCE
prompt ====================================
prompt
create sequence HIBERNATE_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000080
increment by 1
cache 20;

prompt
prompt Creating sequence SEQUENCE_SMS_MOLONG_WAIT
prompt ==========================================
prompt
create sequence SEQUENCE_SMS_MOLONG_WAIT
minvalue 1
maxvalue 9999999999999999999999999999
start with 121
increment by 1
cache 20;

prompt
prompt Creating sequence SEQUENCE_SMS_MO_LOG
prompt =====================================
prompt
create sequence SEQUENCE_SMS_MO_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQUENCE_SMS_MT_LOG
prompt =====================================
prompt
create sequence SEQUENCE_SMS_MT_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQUENCE_SMS_MT_WAIT
prompt ======================================
prompt
create sequence SEQUENCE_SMS_MT_WAIT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ACT_ORDER
prompt ===============================
prompt
create sequence SEQ_ACT_ORDER
minvalue 1
maxvalue 9999999999999999999999999999
start with 37501480
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ACT_ORDER_HISTORY
prompt =======================================
prompt
create sequence SEQ_ACT_ORDER_HISTORY
minvalue 1
maxvalue 9999999999999999999999999999
start with 1002600
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ACT_ORDER_SUB
prompt ===================================
prompt
create sequence SEQ_ACT_ORDER_SUB
minvalue 1
maxvalue 9999999999999999999999999999
start with 47803740
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_BOSS_PRECONTRACT
prompt ======================================
prompt
create sequence SEQ_BOSS_PRECONTRACT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_BOSS_REQUEST
prompt ==================================
prompt
create sequence SEQ_BOSS_REQUEST
minvalue 1
maxvalue 9999999999999999999999999999
start with 321
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_CHANNEL_CATALOG_CONF
prompt ==========================================
prompt
create sequence SEQ_CHANNEL_CATALOG_CONF
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000120
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_CHANNEL_CATALOG_RCMD_CONF
prompt ===============================================
prompt
create sequence SEQ_CHANNEL_CATALOG_RCMD_CONF
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000140
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_COMM_ID
prompt =============================
prompt
create sequence SEQ_COMM_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1016440
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ES_EVENT_TYPES
prompt ====================================
prompt
create sequence SEQ_ES_EVENT_TYPES
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000180
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ES_LISTENERS
prompt ==================================
prompt
create sequence SEQ_ES_LISTENERS
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ES_LISTENER_EVENTS
prompt ========================================
prompt
create sequence SEQ_ES_LISTENER_EVENTS
minvalue 1
maxvalue 9999999999999999999999999999
start with 1001740
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_EXCHANGE_POINT_LOG_ID
prompt ===========================================
prompt
create sequence SEQ_EXCHANGE_POINT_LOG_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000440
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_GOODSHELF_GOOD_REL
prompt ========================================
prompt
create sequence SEQ_GOODSHELF_GOOD_REL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1001160
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_GOOD_SHELF
prompt ================================
prompt
create sequence SEQ_GOOD_SHELF
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ITEM_COMMENT
prompt ==================================
prompt
create sequence SEQ_ITEM_COMMENT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000120
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_LOTTERY_ACTIVE
prompt ====================================
prompt
create sequence SEQ_LOTTERY_ACTIVE
minvalue 1
maxvalue 9999999999999999999999999999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_LOTTERY_ACTIVE_CONF
prompt =========================================
prompt
create sequence SEQ_LOTTERY_ACTIVE_CONF
minvalue 1
maxvalue 9999999999999999999999999999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_LOTTERY_LOG
prompt =================================
prompt
create sequence SEQ_LOTTERY_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_LOTTERY_PRIZE
prompt ===================================
prompt
create sequence SEQ_LOTTERY_PRIZE
minvalue 1
maxvalue 9999999999999999999999999999
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_LOTTERY_TARGET
prompt ====================================
prompt
create sequence SEQ_LOTTERY_TARGET
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_MEMBER
prompt ============================
prompt
create sequence SEQ_MEMBER
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_MEMBER_ID
prompt ===============================
prompt
create sequence SEQ_MEMBER_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 4056364
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_MMS_BATCH_TASK
prompt ====================================
prompt
create sequence SEQ_MMS_BATCH_TASK
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_OPEN_CUSTOMER
prompt ===================================
prompt
create sequence SEQ_OPEN_CUSTOMER
minvalue 1000000000
maxvalue 9999999999
start with 1000000020
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ORDER
prompt ===========================
prompt
create sequence SEQ_ORDER
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ORDER_EXCEPTION
prompt =====================================
prompt
create sequence SEQ_ORDER_EXCEPTION
minvalue 1
maxvalue 9999999999999999999999999999
start with 2081
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_ORDER_REFUND
prompt ==================================
prompt
create sequence SEQ_ORDER_REFUND
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000280
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PAGE_STATIC_INFO
prompt ======================================
prompt
create sequence SEQ_PAGE_STATIC_INFO
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000840
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PAY_COMMON
prompt ================================
prompt
create sequence SEQ_PAY_COMMON
minvalue 1
maxvalue 9999999999999999999999999999
start with 23701340
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_PAY_ORDER
prompt ===============================
prompt
create sequence SEQ_PAY_ORDER
minvalue 1
maxvalue 9999999999999999999999999999
start with 2242620
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SHOP
prompt ==========================
prompt
create sequence SEQ_SHOP
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000060
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SHOP_INVOICE
prompt ==================================
prompt
create sequence SEQ_SHOP_INVOICE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000120
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SHOP_REGION
prompt =================================
prompt
create sequence SEQ_SHOP_REGION
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000020
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SHOP_SETTINGS
prompt ===================================
prompt
create sequence SEQ_SHOP_SETTINGS
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000040
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMSBUY_ITEM
prompt =================================
prompt
create sequence SEQ_SMSBUY_ITEM
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000140
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_ACT_ONLINE
prompt ====================================
prompt
create sequence SEQ_SMS_ACT_ONLINE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000020
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_ACT_ROUTER
prompt ====================================
prompt
create sequence SEQ_SMS_ACT_ROUTER
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_BATCH_LOG
prompt ===================================
prompt
create sequence SEQ_SMS_BATCH_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 36101
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_BATCH_TASK
prompt ====================================
prompt
create sequence SEQ_SMS_BATCH_TASK
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000200
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_MO_LOG
prompt ================================
prompt
create sequence SEQ_SMS_MO_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 10121
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_MT_LOG
prompt ================================
prompt
create sequence SEQ_SMS_MT_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 1248961
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SMS_REPORT
prompt ================================
prompt
create sequence SEQ_SMS_REPORT
minvalue 1
maxvalue 9999999999999999999999999999
start with 41241
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_STORE
prompt ===========================
prompt
create sequence SEQ_STORE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000240
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_COMM_ID
prompt =================================
prompt
create sequence SEQ_SYS_COMM_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1314760
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_ITEM_GROUP_LINK_ID
prompt ============================================
prompt
create sequence SEQ_SYS_ITEM_GROUP_LINK_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000140
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_ITEM_ID
prompt =================================
prompt
create sequence SEQ_SYS_ITEM_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 2127760
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_ITEM_PARAM_ID
prompt =======================================
prompt
create sequence SEQ_SYS_ITEM_PARAM_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1001760
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_ITEM_PROPERTY_ID
prompt ==========================================
prompt
create sequence SEQ_SYS_ITEM_PROPERTY_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_ITEM_TAG_ID
prompt =====================================
prompt
create sequence SEQ_SYS_ITEM_TAG_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000240
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_SEARCH_IDX
prompt ====================================
prompt
create sequence SEQ_SYS_SEARCH_IDX
minvalue 1
maxvalue 9999999999
start with 201
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_SEGMENT
prompt =================================
prompt
create sequence SEQ_SYS_SEGMENT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_S_SURVEY
prompt ==============================
prompt
create sequence SEQ_S_SURVEY
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000140
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_UMC_MSG
prompt =============================
prompt
create sequence SEQ_UMC_MSG
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_USER_CUSTOM
prompt =================================
prompt
create sequence SEQ_USER_CUSTOM
minvalue 1
maxvalue 9999999999999999999999999999
start with 2212836
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_USER_CUSTOM_LOG
prompt =====================================
prompt
create sequence SEQ_USER_CUSTOM_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 127421
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_VERIFYCODE_CPLATFORM
prompt ==========================================
prompt
create sequence SEQ_VERIFYCODE_CPLATFORM
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_VERIFYCODE_ORDER
prompt ======================================
prompt
create sequence SEQ_VERIFYCODE_ORDER
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000080
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_XW_FEE_TEMP
prompt =================================
prompt
create sequence SEQ_XW_FEE_TEMP
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating view V_ACT_ORDER_PER
prompt =============================
prompt
create or replace force view v_act_order_per as
select act_order_id,
       act_begin_time,
       pay_begin_time,
       pay_finish_time,
       act_finish_time,
       (pay_begin_time - act_begin_time) * (24 * 3600) cost_act_to_pay,
       (pay_finish_time - pay_begin_time) * (24 * 3600) cost_pay,
       (act_finish_time - pay_finish_time) * (24 * 3600) cost_pay_to_act
  from (select ao.id act_order_id,
               to_date(ao.create_time, 'YYYYMMDDHH24MISS') act_begin_time,
               to_date(min(po.create_time), 'YYYYMMDDHH24MISS') pay_begin_time,
               to_date(max(po.update_time), 'YYYYMMDDHH24MISS') pay_finish_time,
               to_date(ao.pay_time, 'YYYYMMDDHH24MISS') act_finish_time
          from t_act_order ao
          left join t_pay_order po
            on po.act_order_id = ao.id
         group by ao.id, ao.create_time, ao.pay_time
         order by ao.id desc);

prompt
prompt Creating view V_GOODS_INFO
prompt ==========================
prompt
create or replace force view v_goods_info as
select tis.id g_id,
       tis.cash_idgoods g_payment_cash,
       tis.coin_idgoods g_payment_coin,
       tis.score_idgoods g_payment_score,
       tis.is_valid g_is_valid,
       tis.iseckill g_iseckill,
       tis.iseckill_price g_iseckill_price,
       tis.cash_idgoods g_cash_idgoods,
       tis.coin_idgoods g_coin_idgoods,
       tis.score_idgoods g_score_idgoods,
       tis.org_id g_org_id,
       tis.name g_name,
       tis.short_name g_short_name,
       tis.type_id g_type_id,
       tis.brand g_brand,
       tis.create_time g_create_time,
       tis.update_time g_update_time,
       tis.market_content g_market_content,
       tis.img_path g_web_path,
       to_char(nvl(tis.market_price, 0), 'fm999999990.00') g_market_price,
       to_char(nvl(tis.shop_price, 0), 'fm999999990.00') g_shop_price,
       tis.remark g_remark,
       tis.post_flag g_post_flag,
       tis.item_mode g_item_mode,
       tis.sale_start_time g_start_time,
       tis.sale_stop_time g_stop_time,
       tis.is_view g_is_view,
       tis.warm_prompt g_warm_prompt,
       tis.group_flag g_group_flag,
       tis.store_id g_store_id,
       to_char(nvl(tise.logistics_fee, 0), 'fm999999990.00') g_logistics_fee,
       nvl(tise.logistics_fee_type, 0) g_logistics_fee_type,
       (select to_char(wm_concat(region_code)) region_code from t_item_sale_area_link area_link where tis.id = area_link.sale_id) g_region_code,
       (select to_char(wm_concat(region_code)) region_codes from t_item_sale_user_area_link user_area_link where tis.id = user_area_link.sale_id) g_region_codes,
       (select to_char(wm_concat(user_level)) user_levels from t_item_sale_user_level_link sale_user_level where tis.id = sale_user_level.sale_id) g_user_levels,
       (select to_char(wm_concat(param_value)) property from t_item_param item_param where tis.id = item_param.item_id) g_property,
       (select to_char(wm_concat(tag)) tags from t_item_tag item_tag where tis.id = item_tag.item_id) g_tags
  from t_item_sale tis
  left join t_item_sale_ext tise
    on tis.id = tise.sale_id
 where tis.status = 3
   and tis.is_valid = 1;

prompt
prompt Creating view V_REGION
prompt ======================
prompt
create or replace force view v_region as
select r2.region_code,r.region_name||' '||r1.region_name||' '||r2.region_name region,
 r.short_name||' '||r1.short_name||' '||r2.short_name shortname from t_sys_region r, t_sys_region r1, t_sys_region r2 where r.region_code = r1.parent_region and r1.region_code = r2.parent_region;

prompt
prompt Creating view V_SEARCH_GOOD
prompt ===========================
prompt
create or replace force view v_search_good as
select tis.id g_id,
       tis.cash_idgoods g_payment_cash,
       tis.coin_idgoods g_payment_coin,
       tis.score_idgoods g_payment_score,
       tis.is_valid g_is_valid,
       tis.iseckill g_iseckill,
       tis.iseckill_price g_iseckill_price,
       tis.cash_idgoods g_cash_idgoods,
       tis.coin_idgoods g_coin_idgoods,
       tis.score_idgoods g_score_idgoods,
       tis.org_id g_org_id,
       tis.name g_name,
       tis.short_name g_short_name,
       ts.id g_store_id,
       ts.name g_store_name,
       ts.short_name g_store_short_name,
       tis.type_id g_type_id,
       tst.name g_type_name,
       tis.brand g_brand,
       tis.create_time g_create_time,
       tis.update_time g_update_time,
       tis.market_content g_market_content,
       nvl(tise.click_num, 0) g_click_num,
       tis.img_path g_web_path,
       to_char(nvl(tis.market_price, 0), 'fm999999990.00') g_market_price,
       to_char(nvl(tis.shop_price, 0), 'fm999999990.00') g_shop_price,
       nvl(tise.sale_num, 0) g_sale_num,
       nvl(tise.comment_num, 0) g_comment_num,
       nvl(tise.user_num, 0) g_user_num,
       nvl(tise.collect_num, 0) g_collect_num,
       nvl(tis.stock_num, 0) g_stock_num,
       tis.remark g_remark,
       to_char(nvl(tise.logistics_fee, 0), 'fm999999990.00') g_logistics_fee,
       nvl(tise.logistics_fee_type, 0) g_logistics_fee_type,
       tis.post_flag g_post_flag,
       tis.item_mode g_item_mode,
       tis.sale_start_time g_start_time,
       tis.sale_stop_time g_stop_time,
       nvl(tise.rank, 0) g_rank,
       tis.is_view g_is_view,
       tis.warm_prompt g_warm_prompt,
       tis.group_flag g_group_flag,
       (select to_char(wm_concat(region_code)) region_code from t_item_sale_area_link area_link where tis.id = area_link.sale_id) g_region_code,
       (select to_char(wm_concat(region_code)) region_codes from t_item_sale_user_area_link user_area_link where tis.id = user_area_link.sale_id) g_region_codes,
       (select to_char(wm_concat(user_level)) user_levels from t_item_sale_user_level_link sale_user_level where tis.id = sale_user_level.sale_id) g_user_levels,
       (select to_char(wm_concat(param_value)) property from t_item_param item_param where tis.id = item_param.item_id) g_property,
       (select to_char(wm_concat(tag)) tags from t_item_tag item_tag where tis.id = item_tag.item_id) g_tags,
       (select to_char(min(price), 'fm999999990.00') from t_item_price item_price where tis.id = item_price.sale_id) g_min_price,
       (select max(sys_connect_by_path(id, ',')) || ',' type_path
          from t_sys_type
         where is_valid = 1
         start with id = tis.type_id
        connect by prior p_id = id) g_type_path
  from t_item_sale tis
  left join t_store ts
    on tis.store_id = ts.id
  left join t_sys_type tst
    on tis.type_id = tst.id
  left join t_item_sale_ext tise
    on tis.id = tise.sale_id
 where tis.status = 3
   and tis.is_valid = 1;

prompt
prompt Creating view V_VERIFY_CONSUME
prompt ==============================
prompt
create or replace force view v_verify_consume as
select 'C'||to_char(id) as id, order_id, ship_id, pos_id,
'' as pos_seq, verify_channel, verify_date, use_times, is_reverse, verify_user, verify_status from t_verify_consume
union all
select 'F'||to_char(id) as id, order_id, ship_id, pos_id,
pos_seq, 'POS' as verify_channel, verify_date, 1, 0, terminal_id, '0000' from t_verify_consume_notify where pos_id is not null;

prompt
prompt Creating package XW_DATA_SYNC
prompt =============================
prompt
create or replace package xw_data_sync is

  -- Author  : ZHANGJIAN
  -- Created : 2013/7/1 17:01:55
  -- Purpose :

  -- Public type declarations
  --type <TypeName> is <Datatype>;

  -- Public constant declarations
  --<ConstantName> constant <Datatype> := <Value>;

  -- Public variable declarations
  --<VariableName> <Datatype>;

  -- Public function and procedure declarations
  procedure cleardata;
  function xwcatory_id2klid(xwid in varchar2) return number;
  function xwproduct_id2klid(xwid in varchar2) return number;
  function xwstore_id2klid(xwid in varchar2) return number;
  function xwmember_id2klid(xwid in varchar2) return number;
  function xwstore_catory_id2klid(xwid in varchar2) return number;
  function xwstore_temp_id2klid(xwtempid in varchar2,xwposition in number) return number;
  function xwfee_id2klid(xwid in varchar2) return number;
  FUNCTION MD5(passwd IN VARCHAR2) RETURN VARCHAR2;
  FUNCTION xw_fee2kl_fee(m_id in varchar2,p_typ in varchar2,f_rate1 in varchar2) return number;
  function xwmapid2xwmerid(mapp_id in varchar2) return varchar2;
  procedure covertdata;
  procedure logdata(log_step in number,vtable in varchar2,op_type in varchar2,op_result in varchar2);


end xw_data_sync;
/

prompt
prompt Creating type SPELL_CODE
prompt ========================
prompt
CREATE OR REPLACE TYPE "SPELL_CODE"                                          as object(spell varchar2(10),code number)
/

prompt
prompt Creating type T_SPELLCODE
prompt =========================
prompt
CREATE OR REPLACE TYPE "T_SPELLCODE"                                          is table of spell_code
/

prompt
prompt Creating function F_GETSPELLCODE
prompt ================================
prompt
create or replace function f_getSpellcode return t_spellcode Pipelined
is
Begin
  PIPE Row(spell_code('a', -20319));
  PIPE Row(spell_code('ai', -20317));
  PIPE Row(spell_code('an', -20304));
  PIPE Row(spell_code('ang', -20295));
  PIPE Row(spell_code('ao', -20292));
  PIPE Row(spell_code('ba', -20283));
  PIPE Row(spell_code('bai', -20265));
  PIPE Row(spell_code('ban', -20257));
  PIPE Row(spell_code('bang', -20242));
  PIPE Row(spell_code('bao', -20230));
  PIPE Row(spell_code('bei', -20051));
  PIPE Row(spell_code('ben', -20036));
  PIPE Row(spell_code('beng', -20032));
  PIPE Row(spell_code('bi', -20026));
  PIPE Row(spell_code('bian', -20002));
  PIPE Row(spell_code('biao', -19990));
  PIPE Row(spell_code('bie', -19986));
  PIPE Row(spell_code('bin', -19982));
  PIPE Row(spell_code('bing', -19976));
  PIPE Row(spell_code('bo', -19805));
  PIPE Row(spell_code('bu', -19784));
  PIPE Row(spell_code('ca', -19775));
  PIPE Row(spell_code('cai', -19774));
  PIPE Row(spell_code('can', -19763));
  PIPE Row(spell_code('cang', -19756));
  PIPE Row(spell_code('cao', -19751));
  PIPE Row(spell_code('ce', -19746));
  PIPE Row(spell_code('ceng', -19741));
  PIPE Row(spell_code('cha', -19739));
  PIPE Row(spell_code('chai', -19728));
  PIPE Row(spell_code('chan', -19725));
  PIPE Row(spell_code('chang', -19715));
  PIPE Row(spell_code('chao', -19540));
  PIPE Row(spell_code('che', -19531));
  PIPE Row(spell_code('chen', -19525));
  PIPE Row(spell_code('cheng', -19515));
  PIPE Row(spell_code('chi', -19500));
  PIPE Row(spell_code('chong', -19484));
  PIPE Row(spell_code('chou', -19479));
  PIPE Row(spell_code('chu', -19467));
  PIPE Row(spell_code('chuai', -19289));
  PIPE Row(spell_code('chuan', -19288));
  PIPE Row(spell_code('chuang', -19281));
  PIPE Row(spell_code('chui', -19275));
  PIPE Row(spell_code('chun', -19270));
  PIPE Row(spell_code('chuo', -19263));
  PIPE Row(spell_code('ci', -19261));
  PIPE Row(spell_code('cong', -19249));
  PIPE Row(spell_code('cou', -19243));
  PIPE Row(spell_code('cu', -19242));
  PIPE Row(spell_code('cuan', -19238));
  PIPE Row(spell_code('cui', -19235));
  PIPE Row(spell_code('cun', -19227));
  PIPE Row(spell_code('cuo', -19224));
  PIPE Row(spell_code('da', -19218));
  PIPE Row(spell_code('dai', -19212));
  PIPE Row(spell_code('dan', -19038));
  PIPE Row(spell_code('dang', -19023));
  PIPE Row(spell_code('dao', -19018));
  PIPE Row(spell_code('de', -19006));
  PIPE Row(spell_code('deng', -19003));
  PIPE Row(spell_code('di', -18996));
  PIPE Row(spell_code('dian', -18977));
  PIPE Row(spell_code('diao', -18961));
  PIPE Row(spell_code('die', -18952));
  PIPE Row(spell_code('ding', -18783));
  PIPE Row(spell_code('diu', -18774));
  PIPE Row(spell_code('dong', -18773));
  PIPE Row(spell_code('dou', -18763));
  PIPE Row(spell_code('du', -18756));
  PIPE Row(spell_code('duan', -18741));
  PIPE Row(spell_code('dui', -18735));
  PIPE Row(spell_code('dun', -18731));
  PIPE Row(spell_code('duo', -18722));
  PIPE Row(spell_code('e', -18710));
  PIPE Row(spell_code('en', -18697));
  PIPE Row(spell_code('er', -18696));
  PIPE Row(spell_code('fa', -18526));
  PIPE Row(spell_code('fan', -18518));
  PIPE Row(spell_code('fang', -18501));
  PIPE Row(spell_code('fei', -18490));
  PIPE Row(spell_code('fen', -18478));
  PIPE Row(spell_code('feng', -18463));
  PIPE Row(spell_code('fo', -18448));
  PIPE Row(spell_code('fou', -18447));
  PIPE Row(spell_code('fu', -18446));
  PIPE Row(spell_code('ga', -18239));
  PIPE Row(spell_code('gai', -18237));
  PIPE Row(spell_code('gan', -18231));
  PIPE Row(spell_code('gang', -18220));
  PIPE Row(spell_code('gao', -18211));
  PIPE Row(spell_code('ge', -18201));
  PIPE Row(spell_code('gei', -18184));
  PIPE Row(spell_code('gen', -18183));
  PIPE Row(spell_code('geng', -18181));
  PIPE Row(spell_code('gong', -18012));
  PIPE Row(spell_code('gou', -17997));
  PIPE Row(spell_code('gu', -17988));
  PIPE Row(spell_code('gua', -17970));
  PIPE Row(spell_code('guai', -17964));
  PIPE Row(spell_code('guan', -17961));
  PIPE Row(spell_code('guang', -17950));
  PIPE Row(spell_code('gui', -17947));
  PIPE Row(spell_code('gun', -17931));
  PIPE Row(spell_code('guo', -17928));
  PIPE Row(spell_code('ha', -17922));
  PIPE Row(spell_code('hai', -17759));
  PIPE Row(spell_code('han', -17752));
  PIPE Row(spell_code('hang', -17733));
  PIPE Row(spell_code('hao', -17730));
  PIPE Row(spell_code('he', -17721));
  PIPE Row(spell_code('hei', -17703));
  PIPE Row(spell_code('hen', -17701));
  PIPE Row(spell_code('heng', -17697));
  PIPE Row(spell_code('hong', -17692));
  PIPE Row(spell_code('hou', -17683));
  PIPE Row(spell_code('hu', -17676));
  PIPE Row(spell_code('hua', -17496));
  PIPE Row(spell_code('huai', -17487));
  PIPE Row(spell_code('huan', -17482));
  PIPE Row(spell_code('huang', -17468));
  PIPE Row(spell_code('hui', -17454));
  PIPE Row(spell_code('hun', -17433));
  PIPE Row(spell_code('huo', -17427));
  PIPE Row(spell_code('ji', -17417));
  PIPE Row(spell_code('jia', -17202));
  PIPE Row(spell_code('jian', -17185));
  PIPE Row(spell_code('jiang', -16983));
  PIPE Row(spell_code('jiao', -16970));
  PIPE Row(spell_code('jie', -16942));
  PIPE Row(spell_code('jin', -16915));
  PIPE Row(spell_code('jing', -16733));
  PIPE Row(spell_code('jiong', -16708));
  PIPE Row(spell_code('jiu', -16706));
  PIPE Row(spell_code('ju', -16689));
  PIPE Row(spell_code('juan', -16664));
  PIPE Row(spell_code('jue', -16657));
  PIPE Row(spell_code('jun', -16647));
  PIPE Row(spell_code('ka', -16474));
  PIPE Row(spell_code('kai', -16470));
  PIPE Row(spell_code('kan', -16465));
  PIPE Row(spell_code('kang', -16459));
  PIPE Row(spell_code('kao', -16452));
  PIPE Row(spell_code('ke', -16448));
  PIPE Row(spell_code('ken', -16433));
  PIPE Row(spell_code('keng', -16429));
  PIPE Row(spell_code('kong', -16427));
  PIPE Row(spell_code('kou', -16423));
  PIPE Row(spell_code('ku', -16419));
  PIPE Row(spell_code('kua', -16412));
  PIPE Row(spell_code('kuai', -16407));
  PIPE Row(spell_code('kuan', -16403));
  PIPE Row(spell_code('kuang', -16401));
  PIPE Row(spell_code('kui', -16393));
  PIPE Row(spell_code('kun', -16220));
  PIPE Row(spell_code('kuo', -16216));
  PIPE Row(spell_code('la', -16212));
  PIPE Row(spell_code('lai', -16205));
  PIPE Row(spell_code('lan', -16202));
  PIPE Row(spell_code('lang', -16187));
  PIPE Row(spell_code('lao', -16180));
  PIPE Row(spell_code('le', -16171));
  PIPE Row(spell_code('lei', -16169));
  PIPE Row(spell_code('leng', -16158));
  PIPE Row(spell_code('li', -16155));
  PIPE Row(spell_code('lia', -15959));
  PIPE Row(spell_code('lian', -15958));
  PIPE Row(spell_code('liang', -15944));
  PIPE Row(spell_code('liao', -15933));
  PIPE Row(spell_code('lie', -15920));
  PIPE Row(spell_code('lin', -15915));
  PIPE Row(spell_code('ling', -15903));
  PIPE Row(spell_code('liu', -15889));
  PIPE Row(spell_code('long', -15878));
  PIPE Row(spell_code('lou', -15707));
  PIPE Row(spell_code('lu', -15701));
  PIPE Row(spell_code('lv', -15681));
  PIPE Row(spell_code('luan', -15667));
  PIPE Row(spell_code('lue', -15661));
  PIPE Row(spell_code('lun', -15659));
  PIPE Row(spell_code('luo', -15652));
  PIPE Row(spell_code('ma', -15640));
  PIPE Row(spell_code('mai', -15631));
  PIPE Row(spell_code('man', -15625));
  PIPE Row(spell_code('mang', -15454));
  PIPE Row(spell_code('mao', -15448));
  PIPE Row(spell_code('me', -15436));
  PIPE Row(spell_code('mei', -15435));
  PIPE Row(spell_code('men', -15419));
  PIPE Row(spell_code('meng', -15416));
  PIPE Row(spell_code('mi', -15408));
  PIPE Row(spell_code('mian', -15394));
  PIPE Row(spell_code('miao', -15385));
  PIPE Row(spell_code('mie', -15377));
  PIPE Row(spell_code('min', -15375));
  PIPE Row(spell_code('ming', -15369));
  PIPE Row(spell_code('miu', -15363));
  PIPE Row(spell_code('mo', -15362));
  PIPE Row(spell_code('mou', -15183));
  PIPE Row(spell_code('mu', -15180));
  PIPE Row(spell_code('na', -15165));
  PIPE Row(spell_code('nai', -15158));
  PIPE Row(spell_code('nan', -15153));
  PIPE Row(spell_code('nang', -15150));
  PIPE Row(spell_code('nao', -15149));
  PIPE Row(spell_code('ne', -15144));
  PIPE Row(spell_code('nei', -15143));
  PIPE Row(spell_code('nen', -15141));
  PIPE Row(spell_code('neng', -15140));
  PIPE Row(spell_code('ni', -15139));
  PIPE Row(spell_code('nian', -15128));
  PIPE Row(spell_code('niang', -15121));
  PIPE Row(spell_code('niao', -15119));
  PIPE Row(spell_code('nie', -15117));
  PIPE Row(spell_code('nin', -15110));
  PIPE Row(spell_code('ning', -15109));
  PIPE Row(spell_code('niu', -14941));
  PIPE Row(spell_code('nong', -14937));
  PIPE Row(spell_code('nu', -14933));
  PIPE Row(spell_code('nv', -14930));
  PIPE Row(spell_code('nuan', -14929));
  PIPE Row(spell_code('nue', -14928));
  PIPE Row(spell_code('nuo', -14926));
  PIPE Row(spell_code('o', -14922));
  PIPE Row(spell_code('ou', -14921));
  PIPE Row(spell_code('pa', -14914));
  PIPE Row(spell_code('pai', -14908));
  PIPE Row(spell_code('pan', -14902));
  PIPE Row(spell_code('pang', -14894));
  PIPE Row(spell_code('pao', -14889));
  PIPE Row(spell_code('pei', -14882));
  PIPE Row(spell_code('pen', -14873));
  PIPE Row(spell_code('peng', -14871));
  PIPE Row(spell_code('pi', -14857));
  PIPE Row(spell_code('pian', -14678));
  PIPE Row(spell_code('piao', -14674));
  PIPE Row(spell_code('pie', -14670));
  PIPE Row(spell_code('pin', -14668));
  PIPE Row(spell_code('ping', -14663));
  PIPE Row(spell_code('po', -14654));
  PIPE Row(spell_code('pu', -14645));
  PIPE Row(spell_code('qi', -14630));
  PIPE Row(spell_code('qia', -14594));
  PIPE Row(spell_code('qian', -14429));
  PIPE Row(spell_code('qiang', -14407));
  PIPE Row(spell_code('qiao', -14399));
  PIPE Row(spell_code('qie', -14384));
  PIPE Row(spell_code('qin', -14379));
  PIPE Row(spell_code('qing', -14368));
  PIPE Row(spell_code('qiong', -14355));
  PIPE Row(spell_code('qiu', -14353));
  PIPE Row(spell_code('qu', -14345));
  PIPE Row(spell_code('quan', -14170));
  PIPE Row(spell_code('que', -14159));
  PIPE Row(spell_code('qun', -14151));
  PIPE Row(spell_code('ran', -14149));
  PIPE Row(spell_code('rang', -14145));
  PIPE Row(spell_code('rao', -14140));
  PIPE Row(spell_code('re', -14137));
  PIPE Row(spell_code('ren', -14135));
  PIPE Row(spell_code('reng', -14125));
  PIPE Row(spell_code('ri', -14123));
  PIPE Row(spell_code('rong', -14122));
  PIPE Row(spell_code('rou', -14112));
  PIPE Row(spell_code('ru', -14109));
  PIPE Row(spell_code('ruan', -14099));
  PIPE Row(spell_code('rui', -14097));
  PIPE Row(spell_code('run', -14094));
  PIPE Row(spell_code('ruo', -14092));
  PIPE Row(spell_code('sa', -14090));
  PIPE Row(spell_code('sai', -14087));
  PIPE Row(spell_code('san', -14083));
  PIPE Row(spell_code('sang', -13917));
  PIPE Row(spell_code('sao', -13914));
  PIPE Row(spell_code('se', -13910));
  PIPE Row(spell_code('sen', -13907));
  PIPE Row(spell_code('seng', -13906));
  PIPE Row(spell_code('sha', -13905));
  PIPE Row(spell_code('shai', -13896));
  PIPE Row(spell_code('shan', -13894));
  PIPE Row(spell_code('shang', -13878));
  PIPE Row(spell_code('shao', -13870));
  PIPE Row(spell_code('she', -13859));
  PIPE Row(spell_code('shen', -13847));
  PIPE Row(spell_code('sheng', -13831));
  PIPE Row(spell_code('shi', -13658));
  PIPE Row(spell_code('shou', -13611));
  PIPE Row(spell_code('shu', -13601));
  PIPE Row(spell_code('shua', -13406));
  PIPE Row(spell_code('shuai', -13404));
  PIPE Row(spell_code('shuan', -13400));
  PIPE Row(spell_code('shuang', -13398));
  PIPE Row(spell_code('shui', -13395));
  PIPE Row(spell_code('shun', -13391));
  PIPE Row(spell_code('shuo', -13387));
  PIPE Row(spell_code('si', -13383));
  PIPE Row(spell_code('song', -13367));
  PIPE Row(spell_code('sou', -13359));
  PIPE Row(spell_code('su', -13356));
  PIPE Row(spell_code('suan', -13343));
  PIPE Row(spell_code('sui', -13340));
  PIPE Row(spell_code('sun', -13329));
  PIPE Row(spell_code('suo', -13326));
  PIPE Row(spell_code('ta', -13318));
  PIPE Row(spell_code('tai', -13147));
  PIPE Row(spell_code('tan', -13138));
  PIPE Row(spell_code('tang', -13120));
  PIPE Row(spell_code('tao', -13107));
  PIPE Row(spell_code('te', -13096));
  PIPE Row(spell_code('teng', -13095));
  PIPE Row(spell_code('ti', -13091));
  PIPE Row(spell_code('tian', -13076));
  PIPE Row(spell_code('tiao', -13068));
  PIPE Row(spell_code('tie', -13063));
  PIPE Row(spell_code('ting', -13060));
  PIPE Row(spell_code('tong', -12888));
  PIPE Row(spell_code('tou', -12875));
  PIPE Row(spell_code('tu', -12871));
  PIPE Row(spell_code('tuan', -12860));
  PIPE Row(spell_code('tui', -12858));
  PIPE Row(spell_code('tun', -12852));
  PIPE Row(spell_code('tuo', -12849));
  PIPE Row(spell_code('wa', -12838));
  PIPE Row(spell_code('wai', -12831));
  PIPE Row(spell_code('wan', -12829));
  PIPE Row(spell_code('wang', -12812));
  PIPE Row(spell_code('wei', -12802));
  PIPE Row(spell_code('wen', -12607));
  PIPE Row(spell_code('weng', -12597));
  PIPE Row(spell_code('wo', -12594));
  PIPE Row(spell_code('wu', -12585));
  PIPE Row(spell_code('xi', -12556));
  PIPE Row(spell_code('xia', -12359));
  PIPE Row(spell_code('xian', -12346));
  PIPE Row(spell_code('xiang', -12320));
  PIPE Row(spell_code('xiao', -12300));
  PIPE Row(spell_code('xie', -12120));
  PIPE Row(spell_code('xin', -12099));
  PIPE Row(spell_code('xing', -12089));
  PIPE Row(spell_code('xiong', -12074));
  PIPE Row(spell_code('xiu', -12067));
  PIPE Row(spell_code('xu', -12058));
  PIPE Row(spell_code('xuan', -12039));
  PIPE Row(spell_code('xue', -11867));
  PIPE Row(spell_code('xun', -11861));
  PIPE Row(spell_code('ya', -11847));
  PIPE Row(spell_code('yan', -11831));
  PIPE Row(spell_code('yang', -11798));
  PIPE Row(spell_code('yao', -11781));
  PIPE Row(spell_code('ye', -11604));
  PIPE Row(spell_code('yi', -11589));
  PIPE Row(spell_code('yin', -11536));
  PIPE Row(spell_code('ying', -11358));
  PIPE Row(spell_code('yo', -11340));
  PIPE Row(spell_code('yong', -11339));
  PIPE Row(spell_code('you', -11324));
  PIPE Row(spell_code('yu', -11303));
  PIPE Row(spell_code('yuan', -11097));
  PIPE Row(spell_code('yue', -11077));
  PIPE Row(spell_code('yun', -11067));
  PIPE Row(spell_code('za', -11055));
  PIPE Row(spell_code('zai', -11052));
  PIPE Row(spell_code('zan', -11045));
  PIPE Row(spell_code('zang', -11041));
  PIPE Row(spell_code('zao', -11038));
  PIPE Row(spell_code('ze', -11024));
  PIPE Row(spell_code('zei', -11020));
  PIPE Row(spell_code('zen', -11019));
  PIPE Row(spell_code('zeng', -11018));
  PIPE Row(spell_code('zha', -11014));
  PIPE Row(spell_code('zhai', -10838));
  PIPE Row(spell_code('zhan', -10832));
  PIPE Row(spell_code('zhang', -10815));
  PIPE Row(spell_code('zhao', -10800));
  PIPE Row(spell_code('zhe', -10790));
  PIPE Row(spell_code('zhen', -10780));
  PIPE Row(spell_code('zheng', -10764));
  PIPE Row(spell_code('zhi', -10587));
  PIPE Row(spell_code('zhong', -10544));
  PIPE Row(spell_code('zhou', -10533));
  PIPE Row(spell_code('zhu', -10519));
  PIPE Row(spell_code('zhua', -10331));
  PIPE Row(spell_code('zhuai', -10329));
  PIPE Row(spell_code('zhuan', -10328));
  PIPE Row(spell_code('zhuang', -10322));
  PIPE Row(spell_code('zhui', -10315));
  PIPE Row(spell_code('zhun', -10309));
  PIPE Row(spell_code('zhuo', -10307));
  PIPE Row(spell_code('zi', -10296));
  PIPE Row(spell_code('zong', -10281));
  PIPE Row(spell_code('zou', -10274));
  PIPE Row(spell_code('zu', -10270));
  PIPE Row(spell_code('zuan', -10262));
  PIPE Row(spell_code('zui', -10260));
  PIPE Row(spell_code('zun', -10256));
  PIPE Row(spell_code('zuo', -10254));
  --PIPE Row(spell_code('bu', -30538));
  --PIPE Row(spell_code('jiao', -25358));
  Return;
end;
/

prompt
prompt Creating function F_GETFIRSTORFULLSPELL
prompt =======================================
prompt
create or replace function f_getFirstOrFullSpell(p_cnStr In varchar2,p_sign In number default null) return varchar2

as

lv_spell varchar2(200);
lv_temp Varchar2(10);
lv_char varchar2(10);
li_bytes Integer;

begin
if p_cnStr is null then
return '';
end if;
for i In 1..length(p_cnStr) loop
lv_char:=substr(p_cnStr,i,1);
if lengthb(lv_char) = 1 then
lv_spell:=lv_spell||lv_char;
elsif lengthb(lv_char) = 2 then
Select ascii(lv_char)-256*256 Into li_bytes From dual;
select nvl(max(spell),'###') Into lv_temp from table(f_getSpellcode) where code<=li_bytes;
if p_sign is null then
lv_spell:=lv_spell||substr(lv_temp,1,1);
else
lv_spell:=lv_spell||lv_temp;
end if;
elsif lengthb(lv_char) = 3 then
Select ascii(lv_char)-256*256 Into li_bytes From dual;
select max(spell) Into lv_temp from table(f_getSpellcode) where code<=li_bytes;
if p_sign is null then
lv_spell:=lv_spell||substr(lv_char,1,1);
else
lv_spell:=lv_spell||lv_char;
end if;
end if;
end loop;
return lv_spell;
end;
/

prompt
prompt Creating function MD5
prompt =====================
prompt
CREATE OR REPLACE FUNCTION MD5(

passwd IN VARCHAR2)

RETURN VARCHAR2

IS

retval varchar2(32);

BEGIN

retval := utl_raw.cast_to_raw(DBMS_OBFUSCATION_TOOLKIT.MD5(INPUT_STRING => passwd)) ;

RETURN retval;

END;
/

prompt
prompt Creating procedure P_CARD_GETCODE
prompt =================================
prompt
create or replace procedure P_CARD_GETCODE(v_store_id in t_verify_card_code_common.store_id%type,
                                            v_item_id  in t_verify_card_code_common.item_id%type,
              v_id out t_verify_card_code_common.id%type,
                                            v_card_id out t_verify_card_code_common.card_id%type,
                                            v_card_key out t_verify_card_code_common.card_key%type) is
  v_dtime    t_verify_card_code_common.generate_date%type;
begin
  v_dtime := to_char(sysdate, 'yyyymmddhh24miss');

  select id,card_id, card_key
  into v_id,v_card_id, v_card_key from
  ( select id,card_id, card_key from t_verify_card_code_common
    where status = 100
     and store_id = v_store_id
     and item_id = v_item_id
     and rownum < 100
    order by dbms_random.value )
  where rownum <= 1;

  update t_verify_card_code_common
     set status = 200, generate_date = v_dtime
   where id = v_id and status= 100;
  if sql%rowcount = 0 then
    v_card_id := '0';
    v_card_key := '0';
  end if;
  commit;

exception
  when NO_DATA_FOUND then
    v_card_id := '0';
    v_card_key := '0';
    v_id := 0;
    return;
  when others then
    v_card_id := '0';
    v_card_key := '0';
    v_id := 0;
    rollback;
end P_CARD_GETCODE;
/

prompt
prompt Creating procedure P_STAT_ITEM_SALE_EXT
prompt =======================================
prompt
create or replace procedure P_STAT_ITEM_SALE_EXT AS

STARTTIME varchar2 (12);
ENDTIME varchar2 (12);
V_SQL varchar2 (1000);

BEGIN

select stime into STARTTIME from T_TIMELABLE;
ENDTIME := to_char ((to_date(STARTTIME,'yyyymmddhh24mi') +1/48),'yyyymmddhh24mi') ;

update T_TIMELABLE set stime=ENDTIME;
commit;

--1、T_ITEM_SALE_EXT.CLICK_NUM -- 人气数 人气值 = 下单量 * 10 + 收藏量 * 5
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select product_id, sum(cou) cou
  from (select aog.goods_id product_id, sum(count) * 10 cou
          from t_act_order_goods aog
          full join t_act_order ao
            on ao.id = aog.act_order_id
         where pay_time > '''||STARTTIME||'''
           and pay_time < '''||ENDTIME||'''
         group by aog.goods_id
        union all
        select fav.favorite_id product_id, count(*) * 5 cou
          from t_member_favorite fav
         group by fav.favorite_id)
 group by product_id) B
 on (A.SALE_ID=B.PRODUCT_ID)
 when matched then
update set
A.CLICK_NUM=A.CLICK_NUM+B.COU';

execute immediate V_SQL;
commit;

--2、T_ITEM_SALE_EXT.COLLECT_NUM  --收藏数量
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select fav.favorite_id product_id, count(id) cou
  from t_member_favorite fav
 where update_time > '''||STARTTIME||'''
   and update_time < '''||ENDTIME||'''
 group by fav.favorite_id) B
on (A.SALE_ID=B.PRODUCT_ID)
 when matched then
update set
A.COLLECT_NUM=A.COLLECT_NUM+B.COU';

execute immediate V_SQL;
commit;

--3、T_ITEM_SALE_EXT.RANK      --商品评分
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select sale_id, sum(rank) RAN
  from t_item_comment
 where type = 1
   and update_time > '''||STARTTIME||'''
   and update_time < '''||ENDTIME||'''
   and status = ''1'' --status 1表示审核通过
 group by sale_id) B
 on (A.SALE_ID=B.SALE_ID)
 when matched then
update set
A.RANK=A.RANK+B.RAN';

execute immediate V_SQL;
commit;


--4、T_ITEM_SALE_EXT.SALE_NUM     --销售数量
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select aog.goods_id product_id, sum(count) COU
  from t_act_order_goods aog
  full join t_act_order ao
    on ao.id = aog.act_order_id
 where ao.pay_status = 2 --status 2表示购买成功
   and pay_time > '''||STARTTIME||'''
   and pay_time < '''||ENDTIME||'''
 group by aog.goods_id) B
 on (A.SALE_ID=B.product_id)
 when matched then
update set
A.SALE_NUM=A.SALE_NUM+B.COU';

execute immediate V_SQL;
commit;

--5、T_ITEM_SALE_EXT.COMMENT_NUM   --评论量
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select sale_id, count(*) COU
  from t_item_comment
 where type = 1
   and status = ''1'' --status 1表示审核通过
   and update_time > '''||STARTTIME||'''
   and update_time < '''||ENDTIME||'''
 group by sale_id) B
 on (A.SALE_ID=B.sale_id)
 when matched then
update set
A.COMMENT_NUM=A.COMMENT_NUM+B.COU';

execute immediate V_SQL;
commit;

--6、T_ITEM_SALE_EXT.USER_NUM     --购买人数
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select aog.goods_id product_id, count(distinct ao.user_id) COU
  from t_act_order_goods aog
  full join t_act_order ao
    on ao.id = aog.act_order_id
 where ao.pay_status = 2 --status 2表示购买成功
   and pay_time > '''||STARTTIME||'''
   and pay_time < '''||ENDTIME||'''
 group by aog.goods_id) B
 on (A.SALE_ID=B.product_id)
 when matched then
update set
A.USER_NUM=A.USER_NUM+B.COU';

execute immediate V_SQL;
commit;

    EXCEPTION
      WHEN OTHERS THEN
    null;
END;
/

prompt
prompt Creating procedure P_STORE_GETCODE
prompt ==================================
prompt
create or replace procedure P_STORE_GETCODE(v_store_id in t_verify_store_code_common.store_id%type,
                                            v_item_id  in t_verify_store_code_common.item_id%type,
                                            v_code_rsa out t_verify_store_code_common.code_rsa%type) is
  v_dtime    t_verify_store_code_common.generate_date%type;
  v_code_md5 t_verify_store_code_common.code_md5%type;
begin
  v_dtime := to_char(sysdate, 'yyyymmddhh24miss');

  select code_md5, code_rsa
  into v_code_md5, v_code_rsa from
  ( select code_md5, code_rsa from t_verify_store_code_common
    where status = 100
     and store_id = v_store_id
     and item_id = v_item_id
     and rownum < 100
    order by dbms_random.value )
  where rownum <= 1;

  update t_verify_store_code_common
     set status = 200, generate_date = v_dtime
   where code_md5 = v_code_md5 and status= 100;
  if sql%rowcount = 0 then
    v_code_rsa := '0';
  end if;
  commit;

exception
  when NO_DATA_FOUND then
    v_code_rsa := '0';
    return;
  when others then
    v_code_rsa := '0';
    rollback;
end P_STORE_GETCODE;
/


spool off
