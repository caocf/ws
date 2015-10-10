CREATE procedure "LIFE".P_STAT_ITEM_SALE_EXT AS

STARTTIME varchar2 (12);
ENDTIME varchar2 (12);
V_SQL varchar2 (1000);

BEGIN

select stime into STARTTIME from T_TIMELABLE;
ENDTIME := to_char ((to_date(STARTTIME,'yyyymmddhh24mi') +1/48),'yyyymmddhh24mi') ;

update T_TIMELABLE set stime=ENDTIME;
commit;

--1、T_ITEM_SALE_EXT.CLICK_NUM -- 人气数 人气值 = 下单量 * 10 + 收藏量 * 5
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select product_id, sum(cou) cou
  from (select aog.goods_id product_id, sum(count) * 10 cou
          from t_act_order_goods aog
          full join t_act_order ao
            on ao.id = aog.act_order_id
         where pay_time > '''||STARTTIME||'''
           and pay_time < '''||ENDTIME||'''
         group by aog.goods_id
        union all
        select fav.favorite_id product_id, count(*) * 5 cou
          from t_member_favorite fav
         group by fav.favorite_id)
 group by product_id) B
 on (A.SALE_ID=B.PRODUCT_ID)
 when matched then
update set
A.CLICK_NUM=A.CLICK_NUM+B.COU';

execute immediate V_SQL;
commit;

--2、T_ITEM_SALE_EXT.COLLECT_NUM  --收藏数量
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select fav.favorite_id product_id, count(id) cou
  from t_member_favorite fav
 where update_time > '''||STARTTIME||'''
   and update_time < '''||ENDTIME||'''
 group by fav.favorite_id) B
on (A.SALE_ID=B.PRODUCT_ID)
 when matched then
update set
A.COLLECT_NUM=A.COLLECT_NUM+B.COU';

execute immediate V_SQL;
commit;

--3、T_ITEM_SALE_EXT.RANK      --商品评分
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select sale_id, sum(rank) RAN
  from t_item_comment
 where type = 1
   and update_time > '''||STARTTIME||'''
   and update_time < '''||ENDTIME||'''
   and status = ''1'' --status 1表示审核通过
 group by sale_id) B
 on (A.SALE_ID=B.SALE_ID)
 when matched then
update set
A.RANK=A.RANK+B.RAN';

execute immediate V_SQL;
commit;


--4、T_ITEM_SALE_EXT.SALE_NUM     --销售数量
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select aog.goods_id product_id, sum(count) COU
  from t_act_order_goods aog
  full join t_act_order ao
    on ao.id = aog.act_order_id
 where ao.pay_status = 2 --status 2表示购买成功
   and pay_time > '''||STARTTIME||'''
   and pay_time < '''||ENDTIME||'''
 group by aog.goods_id) B
 on (A.SALE_ID=B.product_id)
 when matched then
update set
A.SALE_NUM=A.SALE_NUM+B.COU';

execute immediate V_SQL;
commit;

--5、T_ITEM_SALE_EXT.COMMENT_NUM   --评论量
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select sale_id, count(*) COU
  from t_item_comment
 where type = 1
   and status = ''1'' --status 1表示审核通过
   and update_time > '''||STARTTIME||'''
   and update_time < '''||ENDTIME||'''
 group by sale_id) B
 on (A.SALE_ID=B.sale_id)
 when matched then
update set
A.COMMENT_NUM=A.COMMENT_NUM+B.COU';

execute immediate V_SQL;
commit;

--6、T_ITEM_SALE_EXT.USER_NUM     --购买人数
V_SQL := 'merge into T_ITEM_SALE_EXT A
using (
select aog.goods_id product_id, count(distinct ao.user_id) COU
  from t_act_order_goods aog
  full join t_act_order ao
    on ao.id = aog.act_order_id
 where ao.pay_status = 2 --status 2表示购买成功
   and pay_time > '''||STARTTIME||'''
   and pay_time < '''||ENDTIME||'''
 group by aog.goods_id) B
 on (A.SALE_ID=B.product_id)
 when matched then
update set
A.USER_NUM=A.USER_NUM+B.COU';

execute immediate V_SQL;
commit;

    EXCEPTION
      WHEN OTHERS THEN
    null;
END;

/