CREATE TABLE "LIFE".t_sys_file_img_thumb (
  "ID" NUMBER(8) NOT NULL,
  file_id NUMBER(8),
  img_size VARCHAR2(50 BYTE),
  img_abs_path VARCHAR2(100 BYTE),
  img_web_path VARCHAR2(100 BYTE),
  update_time VARCHAR2(14 BYTE),
  CONSTRAINT pk_t_sys_file_img_thumb PRIMARY KEY ("ID")
);
ALTER TABLE "LIFE".t_sys_file_img_thumb ADD SUPPLEMENTAL LOG GROUP ggs_240436 ("ID") ALWAYS;
COMMENT ON TABLE "LIFE".t_sys_file_img_thumb IS '缩略图存储';
COMMENT ON COLUMN "LIFE".t_sys_file_img_thumb.file_id IS '原始文件表中的文件ID';
COMMENT ON COLUMN "LIFE".t_sys_file_img_thumb.img_size IS '50*50、128*128、256*256等尺寸规格';
COMMENT ON COLUMN "LIFE".t_sys_file_img_thumb.img_abs_path IS '绝对路径';
COMMENT ON COLUMN "LIFE".t_sys_file_img_thumb.img_web_path IS '网络路径';
COMMENT ON COLUMN "LIFE".t_sys_file_img_thumb.update_time IS '更新时间';