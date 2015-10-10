CREATE TABLE "LIFE".t_batch_task_schedule (
  "ID" NUMBER(9) NOT NULL,
  user_code VARCHAR2(100 BYTE) NOT NULL,
  status NUMBER(9) NOT NULL,
  upload_terminal_count NUMBER(8),
  result_terminal_count NUMBER(8),
  create_time VARCHAR2(14 BYTE) NOT NULL,
  end_time VARCHAR2(14 BYTE),
  upload_file_path VARCHAR2(200 BYTE),
  result_file_path VARCHAR2(200 BYTE),
  date_time VARCHAR2(8 BYTE) NOT NULL,
  filter_type NUMBER(1) NOT NULL,
  "LIMIT" NUMBER(5) DEFAULT 5,
  failure_reason VARCHAR2(200 BYTE),
  remark VARCHAR2(100 BYTE),
  task_type NUMBER(1),
  "CONTENT" VARCHAR2(1000 BYTE),
  CONSTRAINT pk_t_batch_task_schedule PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_batch_task_schedule ADD SUPPLEMENTAL LOG GROUP ggs_240623 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_batch_task_schedule IS '群发排期表';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule."ID" IS '编号';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.user_code IS '创建帐号';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.status IS '状态 0-删除;1-有效(未处理);2-无效(不处理);3-处理中;4-处理完成;5-处理失败';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.upload_terminal_count IS '上传号码数(由后台进程维护)';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.result_terminal_count IS '处理结果实际过滤号码数(由后台进程维护)';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.create_time IS '开始时间(录入系统时间)';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.end_time IS '结束时间(后台进程处理完时间)';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.upload_file_path IS '上传文件路径';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.result_file_path IS '进程处理结果文件路径';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.date_time IS '处理日期';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.filter_type IS '过滤范围 1-商城（10658364） 2-商城（10658618） 3-商盟（10658585） 4-商盟（其他）';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule."LIMIT" IS '定义收到多少条短信的用户被过滤';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.failure_reason IS '进程处理失败原因';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule.remark IS '备注';
COMMENT ON COLUMN "LIFE".t_batch_task_schedule."CONTENT" IS '自定义短信内容模板，支持通配符，[param1]表示参数1，[param2]表示参数2，依此类推';