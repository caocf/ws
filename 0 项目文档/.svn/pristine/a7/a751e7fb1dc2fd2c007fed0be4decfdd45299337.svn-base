CREATE procedure "LIFE".pro_delease_stock
--红包释放库存
 is
  cursor cur_info is
   --查询关闭的红包订单对应的库存记录表记录
    select  t1.id, t1.stock_id, t1.quantity
      from t_act_order t,t_item_param_stock_record t1
     where t.order_type = 10
       and t.close_status = 1
       and t.remark= to_char(t1.id)
       and t1.type=0;
begin
  for info in cur_info loop

      --更新库存记录类型
        update t_item_param_stock_record
           set type = 1
         where id = info.id;
         --释放库存
        update t_item_param_stock t
           set t.stocknum = t.stocknum + info.quantity
         where id = info.stock_id;
           
        commit;

  end loop;
 
  exception
  WHEN NO_DATA_FOUND THEN
 NULL;
end;

/