CREATE TABLE "LIFE".t_timelable (
  stime VARCHAR2(12 BYTE)
);
ALTER TABLE "LIFE".t_timelable ADD SUPPLEMENTAL LOG GROUP ggs_240488 (stime) ALWAYS;
COMMENT ON TABLE "LIFE".t_timelable IS '记录统计时间点的表，用于P_STAT_ITEM_SALE_EXT存储过程';
COMMENT ON COLUMN "LIFE".t_timelable.stime IS '下次job执行时，汇总从该时间开始往后30分钟内的数据，每次汇总完，自动更新该时间';