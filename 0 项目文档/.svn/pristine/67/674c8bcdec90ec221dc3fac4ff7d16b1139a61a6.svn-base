CREATE TABLE "LIFE".t_batch_task (
  "ID" NUMBER(9) NOT NULL,
  custom_tag NUMBER(3),
  task_type NUMBER(3) NOT NULL,
  unit_id VARCHAR2(20 BYTE) NOT NULL,
  priority NUMBER(3) NOT NULL,
  speed NUMBER(3) NOT NULL,
  is_report NUMBER(3) NOT NULL,
  start_time VARCHAR2(14 BYTE),
  stop_time VARCHAR2(14 BYTE),
  sleep_time VARCHAR2(250 BYTE),
  status NUMBER(3) NOT NULL,
  sp_code VARCHAR2(20 BYTE),
  service_id VARCHAR2(20 BYTE),
  fee_type NUMBER(3),
  fee NUMBER(5),
  trace_respond NUMBER(3) NOT NULL,
  respond_timeout VARCHAR2(14 BYTE),
  info_id NUMBER(9),
  title VARCHAR2(200 BYTE),
  "CONTENT" VARCHAR2(200 BYTE),
  smil VARCHAR2(200 BYTE),
  exec_start_time VARCHAR2(14 BYTE),
  exec_stop_time VARCHAR2(14 BYTE),
  mobile_list_file VARCHAR2(200 BYTE),
  white_list_file VARCHAR2(200 BYTE),
  black_list_file VARCHAR2(200 BYTE),
  notice_terminal_id VARCHAR2(200 BYTE),
  task_name VARCHAR2(200 BYTE),
  submit_cnt NUMBER(9),
  success_cnt NUMBER(9),
  black_cnt NUMBER(9),
  fail_cnt NUMBER(9),
  creator_id NUMBER(9) NOT NULL,
  create_time VARCHAR2(14 BYTE) NOT NULL,
  auditor_id NUMBER(9),
  audit_time VARCHAR2(14 BYTE),
  advice VARCHAR2(200 BYTE),
  submit_localflag NUMBER(3),
  act_id NUMBER(19),
  area_code VARCHAR2(255 CHAR),
  service_doc VARCHAR2(255 CHAR),
  item_id VARCHAR2(50 BYTE),
  batch_type NUMBER(3) DEFAULT 1,
  router_id NUMBER(12),
  CONSTRAINT pk_t_batch_task PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_batch_task ADD SUPPLEMENTAL LOG GROUP ggs_240334 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_batch_task IS '保存群发任务，以及任务的执行状态。';
COMMENT ON COLUMN "LIFE".t_batch_task."ID" IS '任务ID';
COMMENT ON COLUMN "LIFE".t_batch_task.custom_tag IS '0：手工群发任务
1：定制群发任务';
COMMENT ON COLUMN "LIFE".t_batch_task.task_type IS '1：短信群发
2：彩信群发
3：WAP Push';
COMMENT ON COLUMN "LIFE".t_batch_task.unit_id IS '任务归属的部门的ID';
COMMENT ON COLUMN "LIFE".t_batch_task.priority IS '发送优先级0-999:高到低';
COMMENT ON COLUMN "LIFE".t_batch_task.speed IS '每秒发送的条数';
COMMENT ON COLUMN "LIFE".t_batch_task.is_report IS '是否收取状态报告:
 0:否
 1:是';
COMMENT ON COLUMN "LIFE".t_batch_task.start_time IS '任务的发送时间';
COMMENT ON COLUMN "LIFE".t_batch_task.stop_time IS '任务的终止时间';
COMMENT ON COLUMN "LIFE".t_batch_task.sleep_time IS '自动暂停发送时间: HHNN-HHNN,HHNN-HHNN';
COMMENT ON COLUMN "LIFE".t_batch_task.status IS '任务状态：
0：未审核
1：已审核
2：审核不通过
3：暂停
4：发送中
5：发送完毕
6：发送失败
7：任务预处理
8：预处理完成，等待发送';
COMMENT ON COLUMN "LIFE".t_batch_task.service_id IS '计费代码';
COMMENT ON COLUMN "LIFE".t_batch_task.fee_type IS '计费类型 1免费,2安条收费,3包月收费';
COMMENT ON COLUMN "LIFE".t_batch_task.fee IS '计费金额 单位分';
COMMENT ON COLUMN "LIFE".t_batch_task.trace_respond IS '是否跟踪用户回应
0：否
1：是';
COMMENT ON COLUMN "LIFE".t_batch_task.respond_timeout IS '更总用户回应的最长时间 单位分钟';
COMMENT ON COLUMN "LIFE".t_batch_task.info_id IS '用户选择的发送信息的ID';
COMMENT ON COLUMN "LIFE".t_batch_task.title IS '短信的内容，彩信的标题，WAP的标题';
COMMENT ON COLUMN "LIFE".t_batch_task."CONTENT" IS '彩信的内容（存放路径），WAP的URL';
COMMENT ON COLUMN "LIFE".t_batch_task.smil IS '彩信的SMIL';
COMMENT ON COLUMN "LIFE".t_batch_task.exec_start_time IS '执行开始时间';
COMMENT ON COLUMN "LIFE".t_batch_task.exec_stop_time IS '执行结束时间';
COMMENT ON COLUMN "LIFE".t_batch_task.mobile_list_file IS '号码总数';
COMMENT ON COLUMN "LIFE".t_batch_task.white_list_file IS '白名单总数';
COMMENT ON COLUMN "LIFE".t_batch_task.black_list_file IS '黑名单总数';
COMMENT ON COLUMN "LIFE".t_batch_task.submit_cnt IS '任务总数量';
COMMENT ON COLUMN "LIFE".t_batch_task.success_cnt IS '成功下发量';
COMMENT ON COLUMN "LIFE".t_batch_task.black_cnt IS '黑名单数量';
COMMENT ON COLUMN "LIFE".t_batch_task.fail_cnt IS '失败数量';
COMMENT ON COLUMN "LIFE".t_batch_task.creator_id IS '创建人ID';
COMMENT ON COLUMN "LIFE".t_batch_task.create_time IS '创建日期YYYYMMDDHHNNSS';
COMMENT ON COLUMN "LIFE".t_batch_task.auditor_id IS '审核人ID';
COMMENT ON COLUMN "LIFE".t_batch_task.audit_time IS '审核日期YYYYMMDDHHNNSS';
COMMENT ON COLUMN "LIFE".t_batch_task.advice IS '审核说明';
COMMENT ON COLUMN "LIFE".t_batch_task.submit_localflag IS '审核操作类型：1:本地审核,2:上级单位审核';
COMMENT ON COLUMN "LIFE".t_batch_task.act_id IS '短信购活动ID
';
COMMENT ON COLUMN "LIFE".t_batch_task.item_id IS '新增商品ID';
COMMENT ON COLUMN "LIFE".t_batch_task.batch_type IS '1 普通群发，2 短信购';
COMMENT ON COLUMN "LIFE".t_batch_task.router_id IS '商品指令';