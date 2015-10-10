CREATE PROCEDURE "LIFE".PRO_MONITOR_INVALID_ITEM AS
  --业务监控：非法商品下架
BEGIN

  DECLARE
    YESTERDAY VARCHAR2(8); --昨天
    TODAY     VARCHAR2(8); --今天
    TOMORROW  VARCHAR2(8); --明天
  BEGIN
    YESTERDAY := TO_CHAR(SYSDATE - 1, 'yyyyMMdd');
    TODAY     := TO_CHAR(SYSDATE, 'yyyyMMdd');
    TOMORROW  := TO_CHAR(SYSDATE + 1, 'yyyyMMdd');
  
    --商户基本信息非法-审核状态不为审核通过
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 11
        FROM T_STORE S, T_ITEM_SALE I
       WHERE I.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS <> 3;
    COMMIT;
  
    --商户基本信息非法-清算状态不为审核通过
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 12
        FROM T_STORE S, T_ITEM_SALE I
       WHERE I.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG <> 3;
    COMMIT;
  
    --商户基本信息非法-账号状态为不可用
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 13
        FROM T_STORE S, T_ITEM_SALE I
       WHERE I.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID <> 1;
    COMMIT;
  
    --商户结算信息非法-审核状态不为审核通过
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 21
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS <> 3;
    COMMIT;
  
    --商户结算信息非法-清算状态不为审核通过
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 22
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG <> 3;
    COMMIT;
  
    --商户结算信息非法-生效日志晚于当天
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 23
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE >= TOMORROW;
    COMMIT;
  
    --商户结算信息非法-失效日期早于当天
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 24
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE <= YESTERDAY;
    COMMIT;
  
    --商户费率信息非法-审核状态不为审核通过
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 31
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS, T_STORE_FEE FEE
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND FEE.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE >= TODAY
         AND FEE.STATUS <> 3;
    COMMIT;
  
    --商户费率信息非法-清算状态不为审核通过
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 32
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS, T_STORE_FEE FEE
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND FEE.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE >= TODAY
         AND FEE.STATUS = 3
         AND FEE.SYNC_GY_FLAG1 <> 3
         AND FEE.SYNC_GY_FLAG2 <> 3
         AND FEE.SYNC_GY_FLAG3 <> 3
         AND FEE.SYNC_GY_FLAG4 <> 3;
    COMMIT;
  
    --商户费率信息非法-生效日志晚于当天
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 33
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS, T_STORE_FEE FEE
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND FEE.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE >= TODAY
         AND FEE.STATUS = 3
         AND (FEE.SYNC_GY_FLAG1 = 3 OR FEE.SYNC_GY_FLAG2 = 3 OR
             FEE.SYNC_GY_FLAG3 = 3 OR FEE.SYNC_GY_FLAG4 = 3)
         AND FEE.EFFORTDATE >= TOMORROW;
    COMMIT;
  
    --商户费率信息非法-失效日期早于当天
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 34
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS, T_STORE_FEE FEE
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND FEE.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE >= TODAY
         AND FEE.STATUS = 3
         AND (FEE.SYNC_GY_FLAG1 = 3 OR FEE.SYNC_GY_FLAG2 = 3 OR
             FEE.SYNC_GY_FLAG3 = 3 OR FEE.SYNC_GY_FLAG4 = 3)
         AND FEE.EFFORTDATE <= TODAY
         AND FEE.EXPIRYDATE <= YESTERDAY;
    COMMIT;
  
    --商品基本信息非法-审核状态不为审核通过
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 41
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS, T_STORE_FEE FEE
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND FEE.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE >= TODAY
         AND FEE.STATUS = 3
         AND (FEE.SYNC_GY_FLAG1 = 3 OR FEE.SYNC_GY_FLAG2 = 3 OR
             FEE.SYNC_GY_FLAG3 = 3 OR FEE.SYNC_GY_FLAG4 = 3)
         AND FEE.EFFORTDATE <= TODAY
         AND FEE.EXPIRYDATE >= TODAY
         AND I.STATUS <> 3;
    COMMIT;
  
    --商品基本信息非法-清算状态不为审核通过
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 42
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS, T_STORE_FEE FEE
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND FEE.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE >= TODAY
         AND FEE.STATUS = 3
         AND (FEE.SYNC_GY_FLAG1 = 3 OR FEE.SYNC_GY_FLAG2 = 3 OR
             FEE.SYNC_GY_FLAG3 = 3 OR FEE.SYNC_GY_FLAG4 = 3)
         AND FEE.EFFORTDATE <= TODAY
         AND FEE.EXPIRYDATE >= TODAY
         AND I.STATUS = 3
         AND I.SYNC_GY_FLAG <> 3;
    COMMIT;
  
    --商品基本信息非法-生效日志晚于当天
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 43
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS, T_STORE_FEE FEE
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND FEE.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE >= TODAY
         AND FEE.STATUS = 3
         AND (FEE.SYNC_GY_FLAG1 = 3 OR FEE.SYNC_GY_FLAG2 = 3 OR
             FEE.SYNC_GY_FLAG3 = 3 OR FEE.SYNC_GY_FLAG4 = 3)
         AND FEE.EFFORTDATE <= TODAY
         AND FEE.EXPIRYDATE >= TODAY
         AND I.STATUS = 3
         AND I.SYNC_GY_FLAG = 3
         AND I.SALE_START_TIME >= TOMORROW;
    COMMIT;
  
    --商品基本信息非法-失效日期早于当天
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 44
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS, T_STORE_FEE FEE
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND FEE.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE >= TODAY
         AND FEE.STATUS = 3
         AND (FEE.SYNC_GY_FLAG1 = 3 OR FEE.SYNC_GY_FLAG2 = 3 OR
             FEE.SYNC_GY_FLAG3 = 3 OR FEE.SYNC_GY_FLAG4 = 3)
         AND FEE.EFFORTDATE <= TODAY
         AND FEE.EXPIRYDATE >= TODAY
         AND I.STATUS = 3
         AND I.SYNC_GY_FLAG = 3
         AND I.SALE_START_TIME <= TODAY
         AND I.SALE_STOP_TIME <= YESTERDAY;
    COMMIT;
  
    --商品基本信息非法-库存量不足
    INSERT INTO T_MONITOR_INVALID_ITEM
      (ID, STORE_ID, STORE_NAME, ITEM_ID, ITEM_NAME, EXCEPTION_TYPE)
      SELECT SEQ_MONITOR_II_ID.NEXTVAL, S.ID, S.NAME, I.ID, I.NAME, 45
        FROM T_STORE S, T_ITEM_SALE I, T_STORE_SETTLE SS, T_STORE_FEE FEE
       WHERE I.STORE_ID = S.ID
         AND SS.STORE_ID = S.ID
         AND FEE.STORE_ID = S.ID
         AND I.ID NOT IN (SELECT T.ITEM_ID
                            FROM T_MONITOR_INVALID_ITEM T
                           WHERE T.DEAL_STATUS <> 1)
         AND I.IS_VALID = 1
         AND S.STATUS = 3
         AND S.SYNC_GY_FLAG = 3
         AND S.IS_VALID = 1
         AND SS.STATUS = 3
         AND SS.SYNC_GY_FLAG = 3
         AND SS.EFFORT_DATE <= TODAY
         AND SS.EXPIRY_DATE >= TODAY
         AND FEE.STATUS = 3
         AND (FEE.SYNC_GY_FLAG1 = 3 OR FEE.SYNC_GY_FLAG2 = 3 OR
             FEE.SYNC_GY_FLAG3 = 3 OR FEE.SYNC_GY_FLAG4 = 3)
         AND FEE.EFFORTDATE <= TODAY
         AND FEE.EXPIRYDATE >= TODAY
         AND I.STATUS = 3
         AND I.SYNC_GY_FLAG = 3
         AND I.SALE_START_TIME <= TODAY
         AND I.SALE_STOP_TIME >= TODAY
         AND I.STOCK_NUM <= 0;
    COMMIT;
  
  END;
END;

/