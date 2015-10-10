CREATE FORCE VIEW "LIFE".v_search_good (g_id,g_payment_cash,g_payment_coin,g_payment_score,g_is_valid,g_iseckill,g_iseckill_price,g_cash_idgoods,g_coin_idgoods,g_score_idgoods,g_org_id,g_name,g_short_name,g_type_id,g_brand,g_create_time,g_update_time,g_market_content,g_web_path,g_market_price,g_shop_price,g_remark,g_post_flag,g_item_mode,g_start_time,g_stop_time,g_is_view,g_warm_prompt,g_group_flag,g_store_id,g_logistics_fee,g_logistics_fee_type,g_stock_num,g_region_code,g_region_codes,g_user_levels,g_property,g_tags,g_pay_type,g_cash_pay_ratio,g_other_pay_ratio,g_bill_pay,g_store_name,g_store_short_name,g_type_name,g_click_num,g_sale_num,g_comment_num,g_user_num,g_collect_num,g_rank,g_source,g_min_price,g_type_path,solr_update_time) AS
select tis."G_ID",tis."G_PAYMENT_CASH",tis."G_PAYMENT_COIN",tis."G_PAYMENT_SCORE",tis."G_IS_VALID",tis."G_ISECKILL",tis."G_ISECKILL_PRICE",tis."G_CASH_IDGOODS",tis."G_COIN_IDGOODS",tis."G_SCORE_IDGOODS",tis."G_ORG_ID",tis."G_NAME",tis."G_SHORT_NAME",tis."G_TYPE_ID",tis."G_BRAND",tis."G_CREATE_TIME",tis."G_UPDATE_TIME",tis."G_MARKET_CONTENT",tis."G_WEB_PATH",tis."G_MARKET_PRICE",tis."G_SHOP_PRICE",tis."G_REMARK",tis."G_POST_FLAG",tis."G_ITEM_MODE",tis."G_START_TIME",tis."G_STOP_TIME",tis."G_IS_VIEW",tis."G_WARM_PROMPT",tis."G_GROUP_FLAG",tis."G_STORE_ID",tis."G_LOGISTICS_FEE",tis."G_LOGISTICS_FEE_TYPE",tis."G_STOCK_NUM",tis."G_REGION_CODE",tis."G_REGION_CODES",tis."G_USER_LEVELS",tis."G_PROPERTY",tis."G_TAGS",
       -- 商品支付方式扩展表 modify_by macl , start>>>>
       pay.pay_type g_pay_type,
       pay.cash_pay_ratio g_cash_pay_ratio,
       pay.other_pay_ratio g_other_pay_ratio,
       pay.bill_pay g_bill_pay,
        -- 商品支付方式扩展表 modify_by macl , end<<<
       ts.name g_store_name,
       ts.short_name g_store_short_name,
       tst.name g_type_name,
       nvl(tise.click_num, 0) g_click_num,
       nvl(tise.sale_num, 0) g_sale_num,
       nvl(tise.comment_num, 0) g_comment_num,
       nvl(tise.user_num, 0) g_user_num,
       nvl(tise.collect_num, 0) g_collect_num,
       nvl(tise.rank, 0) g_rank,
       tis."G_SOURCE",
       (select to_char(min(price), 'fm999999990.00') from t_item_price item_price where tis.g_id = item_price.sale_id) g_min_price,
       (select max(sys_connect_by_path(id, ',')) || ',' type_path
          from t_sys_type
         where is_valid = 1
         start with id = tis.g_type_id
        connect by prior p_id = id) g_type_path,
        to_date(tis.g_update_time,'yyyy-mm-dd?hh24:mi:ss') solr_update_time
  from v_goods_info tis
  left join t_store ts
    on tis.g_store_id = ts.id
  left join t_sys_type tst
    on tis.g_type_id = tst.id
  left join t_item_sale_ext tise
    on tis.g_id = tise.sale_id
  left join t_item_sale_payment pay
       on tis.g_id = pay.item_id
;