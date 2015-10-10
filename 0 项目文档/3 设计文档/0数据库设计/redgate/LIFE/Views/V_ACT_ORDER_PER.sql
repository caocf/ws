CREATE FORCE VIEW "LIFE".v_act_order_per (act_order_id,act_begin_time,pay_begin_time,pay_finish_time,act_finish_time,cost_act_to_pay,cost_pay,cost_pay_to_act) AS
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
         order by ao.id desc)
;