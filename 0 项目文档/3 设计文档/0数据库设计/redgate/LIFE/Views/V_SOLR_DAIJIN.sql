CREATE FORCE VIEW "LIFE".v_solr_daijin ("ID","NAME",img_path,shop_price,market_price,stock_num,cash_idgoods,coin_idgoods,score_idgoods,grounding_time,create_time,update_time,channel_sort,red_price,item_sorts,shop_regions,sale_areas,sale_num,bill_pay) AS
select tis.id, tis.name, tis.img_path, tis.shop_price, tis.market_price, tis.stock_num, tis.cash_idgoods,
       tis.coin_idgoods, tis.score_idgoods, tis.grounding_time,
       tis.create_time, tis.update_time, tis.channel_sort,
       (select min(price) from t_item_price item_price where tis.id = item_price.sale_id and item_price.price_type_code='L1') red_price,
       (select max(substr(sys_connect_by_path(id, ','), 2)) type_path
          from t_sys_type
         where is_valid = 1
         start with id = tis.type_id
        connect by prior p_id = id) item_sorts,
        (select to_char(wm_concat(distinct b.area_code))
           from t_item_verify_shop_link a, t_shop b
           where tis.id = a.sale_id and a.shop_id = b.id) shop_regions, -- 门店对应的所有行政区域
       (select to_char(wm_concat(distinct region_code)) region_code from t_item_sale_area_link area_link where tis.id = area_link.sale_id) sale_areas, -- 商品指定的销售区域
       tise.sale_num,
       pay.bill_pay
  from t_item_sale tis
  left join t_item_sale_ext tise
    on tis.id = tise.sale_id
  left join t_item_sale_payment pay
       on tis.id = pay.item_id
where tis.status = 3
   and tis.is_valid = 1
   and tis.source = '0'
   and tis.is_view = 1
   and tis.iseckill = 5
   and nvl(tis.sale_start_time,'20100101000000') < to_char(sysdate, 'yyyymmddhh24miss')
   and nvl(tis.sale_stop_time,'30100101000000') > to_char(sysdate, 'yyyymmddhh24miss')
;