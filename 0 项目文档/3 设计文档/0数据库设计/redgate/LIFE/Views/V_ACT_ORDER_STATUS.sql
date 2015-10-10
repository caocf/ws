CREATE FORCE VIEW "LIFE".v_act_order_status (act_order_id,order_status,has_refund,refund_all) AS
select ao.id act_order_id,
       case
         when ao.pay_on_delivery = 1 then
          case
            when ao.close_status = 1 then
             5
            when aoe.status = 0 then
             2
            when aoe.status = 1 then
             3
            when aoe.status = 2 then
             4
            else
             0
          end
         else
          case
            when ao.close_status = 1 then
             5
            when pay_status = 0 then
             1
            when pay_status = 1 then
             6
            when pay_status = 2 and aoe.status = 0 then
             2
            when pay_status = 2 and aoe.status = 1 then
             3
            when pay_status = 2 and aoe.status = 2 then
             4
            when pay_status = 2 and aoe.status is null then
             4
            else
             0
          end
       end order_status,
       decode(tor.act_order_id, null, 0, 1) has_refund,
       decode(tor.act_order_id, null, 0, decode(po.pay_sum, 0, 1, 0)) refund_all
  from t_Act_order ao
  left join t_act_order_express aoe
    on ao.id = aoe.act_order_id
  left join (select order_id act_order_id from t_order_refund) tor
    on ao.id = tor.act_order_id
  left join (select act_order_id, sum(payment_amount) pay_sum
               from t_pay_order
              where status = 2
              group by act_order_id) po
    on ao.id = po.act_order_id
with read only
;
COMMENT ON TABLE "LIFE".v_act_order_status IS '业务订单状态';
COMMENT ON COLUMN "LIFE".v_act_order_status.act_order_id IS '业务订单ID';
COMMENT ON COLUMN "LIFE".v_act_order_status.order_status IS '待付款1, 支付中6, 已付款（待发货) 2, 待收货3 , 已完成4, 已取消5 , 其他0';
COMMENT ON COLUMN "LIFE".v_act_order_status.has_refund IS '0有退款 1无退款';
COMMENT ON COLUMN "LIFE".v_act_order_status.refund_all IS '0部分退 1全退款';