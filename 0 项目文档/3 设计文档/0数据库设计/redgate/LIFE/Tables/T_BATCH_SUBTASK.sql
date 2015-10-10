CREATE TABLE "LIFE".t_batch_subtask (
  task_id NUMBER(9) NOT NULL,
  parent_task_id NUMBER(9) NOT NULL,
  mmsc_id VARCHAR2(50 BYTE),
  mobile_file_path VARCHAR2(250 BYTE),
  CONSTRAINT pk_t_batch_subtask PRIMARY KEY (task_id)
);
ALTER TABLE "LIFE".t_batch_subtask ADD SUPPLEMENTAL LOG GROUP ggs_240541 (task_id) ALWAYS;
COMMENT ON TABLE "LIFE".t_batch_subtask IS 'PUSH群发子任务表';
COMMENT ON COLUMN "LIFE".t_batch_subtask.task_id IS '任务ID';
COMMENT ON COLUMN "LIFE".t_batch_subtask.parent_task_id IS '主任务ID';
COMMENT ON COLUMN "LIFE".t_batch_subtask.mmsc_id IS '彩信中心编号925001
1：定制群发任务';
COMMENT ON COLUMN "LIFE".t_batch_subtask.mobile_file_path IS '号码列表文件';