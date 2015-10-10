CREATE package body "LIFE".xw_data_sync is

  -- Private type declarations
  --type < TypeName > is < Datatype >;

  -- Private constant declarations
  --< ConstantName > constant < Datatype > := < Value >;

  -- Private variable declarations
  --< VariableName > < Datatype >;

  -- Function and procedure implementations
  procedure cleardata is
  begin
    for c1 in (select 'truncate table ' || table_name as sqltext
                 from user_tables
                where table_name not in('T_DEBUG_LOG','SELL_FEE_TMP')) loop
      execute immediate c1.sqltext;
    end loop;
  end cleardata;

  function xwcatory_id2klid(xwid in varchar2) return number is
    Result number;
  begin
    select kl_category_id
      into Result
      from xw_category_id2kl_category_id t
     where t.xw_category_id = xwid;
    return(Result);
  END xwcatory_id2klid;

  function xwproduct_id2klid(xwid in varchar2) return number is
    Result number;
  begin
    select kl_product_id
      into Result
      from xw_product_id2kl_product_id t
     where t.xw_product_id = xwid;
    return(Result);
  END xwproduct_id2klid;

  function xwstore_id2klid(xwid in varchar2) return number is
    Result number;
  begin
    select kl_store_id
      into Result
      from xw_store_id2kl_store_id t
     where t.xw_store_id = xwid;
    return(Result);
  END xwstore_id2klid;

  function xwstore_catory_id2klid(xwid in varchar2) return number is
    Result number;
  begin
    select kl_store_category_id
      into Result
      from xw_st_cat_id2kl_st_cat_id t
     where t.xw_store_category_id = xwid;
    return(Result);
  END xwstore_catory_id2klid;

  function xwmember_id2klid(xwid in varchar2) return number is
    Result number;
  begin
    select KL_STORE_MEMBER_ID
      into Result
      from XWMEMBER_ID2KL_MEMBER_ID t
     where t.XW_STORE_MEMBER_ID = xwid;
    return(Result);
  END xwmember_id2klid;

  function xwstore_temp_id2klid(xwtempid in varchar2, xwposition in number)
    return number is
    Result number;
  begin
    select kl_store_temp_id
      into Result
      from XW_ST_TEMP_ID2KL_ST_TEMP_ID t
     where t.xw_store_temp_id = xwtempid || xwposition;
    return(Result);
  END xwstore_temp_id2klid;

  function xwfee_id2klid(xwid in varchar2) return number is
    Result number;
  begin
    select KL_FEE_ID
      into Result
      from XWFEE_ID2KL_FEE_ID t
     where t.XW_FEE_ID = xwid;
    return(Result);
  END xwfee_id2klid;
  
FUNCTION MD5(passwd IN VARCHAR2) RETURN VARCHAR2
is
retval varchar2(32);
BEGIN
retval := utl_raw.cast_to_raw(DBMS_OBFUSCATION_TOOLKIT.MD5(INPUT_STRING => passwd));
RETURN retval;
END;

--t_sys_fee对应关系函数
FUNCTION xw_fee2kl_fee(m_id in varchar2,p_typ in varchar2,f_rate1 in varchar2) return number is
    Result number;
  begin
    select t_sys_fee_id
      into Result
      from xw_fee_id2kl_fee_id t
     where t.merc_id = m_id and t.prod_typ=p_typ and t.fee_rate1=f_rate1;
    return(Result);
  END xw_fee2kl_fee;  
  
--欣网mapping_id到merchant_id转换
function xwmapid2xwmerid(mapp_id in varchar2) return varchar2 is
    Result varchar2(40);
  begin
    select merchant_id
      into Result
      from xwmall.hisun_merchant_mapping t
     where t.mapping_id=mapp_id;
    return(Result);
  END xwmapid2xwmerid;

  procedure logdata(log_step  in number,
                    vtable    in varchar2,
                    op_type   in varchar2,
                    op_result in varchar2) is
  begin
    --记录操作日志
    insert into t_pkg_debug_log
    values
      (sysdate, log_step, vtable, op_type, op_result);
    commit;

  end logdata;

  procedure covertdata is
  begin
    begin
      --插入商品类别映射关系数据
      insert into xw_category_id2kl_category_id
        select distinct category_id xw_category_id, --欣网商品类别
                        100000 + rownum kl_category_id --宽连商品类别
          from xwmall.productcategory;
      logdata(1,
              'xw_category_id2kl_category_id',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;
    begin
      --插入商品编号映射关系数据
      insert into xw_product_id2kl_product_id
        select distinct product_id xw_product_id, --欣网商品编号
                        200000 + rownum kl_product_id --宽连商品编号
          from xwmall.product;
      logdata(2,
              'xw_product_id2kl_product_id',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;
    begin
      --插入商家编号映射关系数据
      insert into xw_store_id2kl_store_id
        select distinct id xw_store_id, --欣网商家编号
                        300000 + rownum kl_store_id --宽连商家编号
          from xwmall.store;
      logdata(3,
              'xw_store_id2kl_store_id',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;
    begin
      --插入商家类别映射关系数据
      insert into xw_st_cat_id2kl_st_cat_id
        select distinct category_id xw_store_category_id, --欣网商家类别
                        400000 + rownum kl_store_category_id --宽连商家类别
          from xwmall.store_category;
      logdata(4,
              'xw_st_cat_id2kl_st_cat_id',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;
    begin
      --插入商品属性映射关系数据
      insert into XW_ST_TEMP_ID2KL_ST_TEMP_ID
        select distinct templateid || position xw_store_temp_id, --欣网商品属性模板id
                        10000 + rownum kl_store_temp_id --宽连商品属性模板id
          from xwmall.PMTEMPLATEFIELDS;
      logdata(5,
              'XW_ST_TEMP_ID2KL_ST_TEMP_ID',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入商品属性映射关系数据
      insert into XWMEMBER_ID2KL_MEMBER_ID
        select a.Customerid as XW_STORE_MEMBER_ID,
               rownum + 100000 as KL_STORE_MEMBER_ID
          from xwmall.customer a;
      logdata(6,
              'XWMEMBER_ID2KL_MEMBER_ID',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
 --T_SYS_FEE插临时表store_fee_temp1，存放固定百分比，存在“商城币、现金两种支付方式，不同结算比例的”商户及类型
insert into store_fee_temp1
select merc_id, prod_typ,count(*)
  from (select merc_id,
               prod_typ,
               fee_rate1,
               dense_rank() over(order by fee_rate1 asc) t_sys_fee_id
          from (select merc_id, prod_typ, fee_rate1
                  from (select merc_id,
                               prod_typ,
                               nvl(fee_rate1, 0) fee_rate1,
                               row_number() over(partition by merc_id, prod_typ, fee_rate1 order by merc_id, prod_typ asc) rc
                          from sell_fee_tmp
                         where up_ref2 is null
                           and exp_dt > to_char(sysdate, 'yyyyMMdd'))
                 where rc = 1))
 group by merc_id, prod_typ
having count(*) > 1;

--固定：商城币、现金两种支付方式，相同结算比例，id从1开始
insert into xw_fee_id2kl_fee_id 
select merc_id,
                prod_typ,
                fee_rate1,
                dense_rank() over(order by fee_rate1 asc) t_sys_fee_id
           from (select merc_id, prod_typ, fee_rate1
                   from (
                   select a.merc_id,
                                a.prod_typ,
                                nvl(a.fee_rate1, 0) fee_rate1,
                                row_number() over(partition by a.merc_id, a.prod_typ, a.fee_rate1 order by a.merc_id, a.prod_typ asc) rc
                           from sell_fee_tmp a
                          where a.up_ref2 is null
                            and a.exp_dt > to_char(sysdate, 'yyyyMMdd')
                            and not EXISTS (SELECT 'X' FROM store_fee_temp1 b WHERE a.merc_id = b.merc_id AND a.prod_typ = b.prod_typ)
                            )
                  where rc = 1
                  );

--固定：商城币、现金两种支付方式，不同结算比例，id从100开始
insert into xw_fee_id2kl_fee_id 
select merc_id,
                prod_typ,
                fee_rate1,
                (dense_rank() over( order by merc_id,prod_typ asc))+100 t_sys_fee_id
           from (select merc_id, prod_typ, fee_rate1
                   from (
                   select a.merc_id,
                                a.prod_typ,
                                nvl(a.fee_rate1, 0) fee_rate1,
                                row_number() over(partition by a.merc_id, a.prod_typ, a.fee_rate1 order by a.merc_id, a.prod_typ asc) rc
                           from sell_fee_tmp a
                          where a.up_ref2 is null
                            and a.exp_dt > to_char(sysdate, 'yyyyMMdd')
                            and EXISTS (SELECT 'X' FROM store_fee_temp1 b WHERE a.merc_id = b.merc_id AND a.prod_typ = b.prod_typ)
                            )
                  where rc = 1
                  ) ;


----创建临时表store_fee_temp2，存放梯度固定百分比，存在“商城币、现金两种支付方式，不同结算比例的”商户及类型
insert into store_fee_temp2
select merc_id, prod_typ
  from (
select merc_id,
                prod_typ,
                c fee_rate1,
                (dense_rank() over(order by c asc)) t_sys_fee_id from (
                select * from (
select merc_id,
       prod_typ,
       c,
       row_number() over(partition by merc_id, prod_typ, c order by merc_id, prod_typ asc) rc
  from (select merc_id,
               prod_typ,
               up_ref1 || '_' || fee_rate1 || '_' || up_ref2 || '_' ||
               fee_rate2 || '_' || nvl(up_ref3, 0) || '_' ||
               nvl(fee_rate3, 0) || '_' || nvl(up_ref4, 0) || '_' ||
               nvl(fee_rate4, 0) || '_' || nvl(up_ref5, 0) || '_' ||
               nvl(fee_rate5, 0) c
          from sell_fee_tmp t
         where exp_dt > to_char(sysdate, 'yyyyMMdd')
           and up_ref2 is not null)) where rc=1))
 group by merc_id, prod_typ
having count(*) > 1;


--梯度：商城币、现金两种支付方式，相同结算比例，id从3000开始
insert into xw_fee_id2kl_fee_id 
select merc_id,
                prod_typ,
                c fee_rate1,
                (dense_rank() over(order by c asc))+3000 t_sys_fee_id from (
                select * from (
select merc_id,
       prod_typ,
       c,
       row_number() over(partition by merc_id, prod_typ, c order by merc_id, prod_typ asc) rc
                  from (select merc_id,
                               prod_typ,
                               up_ref1 || '_' || fee_rate1 || '_' || up_ref2 || '_' ||
                               fee_rate2 || '_' || nvl(up_ref3, 0) || '_' ||
                               nvl(fee_rate3, 0) || '_' || nvl(up_ref4, 0) || '_' ||
                               nvl(fee_rate4, 0) || '_' || nvl(up_ref5, 0) || '_' ||
                               nvl(fee_rate5, 0) c
                          from sell_fee_tmp a
                         where a.exp_dt > to_char(sysdate, 'yyyyMMdd')
                           and a.up_ref2 is not null
                           and not EXISTS
                         (SELECT 'X'
                                  FROM store_fee_temp2 b
                                 WHERE a.merc_id = b.merc_id
                                   AND a.prod_typ = b.prod_typ)) a)
         where rc = 1);


--梯度：商城币、现金两种支付方式，不同结算比例，id从4000开始
insert into xw_fee_id2kl_fee_id 
select merc_id,
                prod_typ,
                c fee_rate1,
                (dense_rank() over(order by merc_id,prod_typ asc))+4000 t_sys_fee_id from (
                select * from (
select merc_id,
       prod_typ,
       c,
       row_number() over(partition by merc_id, prod_typ, c order by merc_id, prod_typ asc) rc
                  from (select merc_id,
                               prod_typ,
                               up_ref1 || '_' || fee_rate1 || '_' || up_ref2 || '_' ||
                               fee_rate2 || '_' || nvl(up_ref3, 0) || '_' ||
                               nvl(fee_rate3, 0) || '_' || nvl(up_ref4, 0) || '_' ||
                               nvl(fee_rate4, 0) || '_' || nvl(up_ref5, 0) || '_' ||
                               nvl(fee_rate5, 0) c
                          from sell_fee_tmp a
                         where a.exp_dt > to_char(sysdate, 'yyyyMMdd')
                           and a.up_ref2 is not null
                           and EXISTS
                         (SELECT 'X'
                                  FROM store_fee_temp2 b
                                 WHERE a.merc_id = b.merc_id
                                   AND a.prod_typ = b.prod_typ)) a)
         where rc = 1);

--插入t_sys_fee表
insert into t_sys_fee
  (id, name, fee, valid, decr_fee, sync_gy_flag, SYNC_GY_TIME)
  select id,
         name,
         fee,
         valid,
         desc_fee,
         0        as sync_gy_flag,
         null     as SYNC_GY_TIME
    from (select distinct t_sys_fee_id id,
                          '欣网固定费率' || fee_rate1 name,
                          fee_rate1 fee,
                          0 valid,
                          '欣网固定费率' || fee_rate1 desc_fee
            from xw_fee_id2kl_fee_id
           where t_sys_fee_id < 100
          union all
          select t_sys_fee_id id,
                 '欣网固定费率' || t_sys_fee_id name,
                 null as fee,
                 0 valid,
                 '欣网固定费率' desc_fee
            from (select t_sys_fee_id
                    from xw_fee_id2kl_fee_id
                   where t_sys_fee_id > 100
                     and t_sys_fee_id < 3000
                   group by t_sys_fee_id)
          union all
          select distinct t_sys_fee_id id,
                          '欣网阶梯费率' || fee_rate1 name,
                          null fee,
                          0 valid,
                          '欣网阶梯费率' || fee_rate1 desc_fee
            from xw_fee_id2kl_fee_id
           where t_sys_fee_id > 3000
             and t_sys_fee_id < 4000
          union all
          select t_sys_fee_id id,
                 '欣网阶梯费率' || t_sys_fee_id name,
                 null as fee,
                 0 valid,
                 '欣网阶梯费率' desc_fee
            from (select t_sys_fee_id
                    from xw_fee_id2kl_fee_id
                   where t_sys_fee_id > 4000
                   group by t_sys_fee_id));
      logdata(0,
              't_sys_fee',
              'insert',
              'sucess insert fix' || sql%rowcount || ' rows');
    end;
  
  

    begin
      --导入商品分类
      insert into t_sys_type
        (id, p_id, name, type, is_valid)
        select xwcatory_id2klid(t.category_id) id, --新商城id
               decode(parent_id, '1', 0, xwcatory_id2klid(parent_id)) p_id, --新商城pid,欣网商城最高一级别为1
               t.category_name name,
               '2' type, --商品分类
               1 as sync_gy_flag
          from xwmall.productcategory t
         where t.store_id = ' '; -- store_id=' '表示是商城商品分类，store_id不为空是自定义分类
      logdata(7,
              't_sys_type',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
      delete from t_sys_type
       where p_id is null
         and id <> 0;
      logdata(7,
              't_sys_type',
              'delete',
              'sucess insert ' || sql%rowcount || ' rows');
      --导入商户分类
      insert into t_sys_type
        (id, p_id, name, type, is_valid)
        select xwstore_catory_id2klid(t.category_id) id,
               xwstore_catory_id2klid(parent_id) p_id,
               t.category_name name,
               '1' type, --商户分类
               0 as sync_gy_flag
          from xwmall.store_category t;
      logdata(8,
              't_sys_type',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入商品分类按参数模板表t_sys_type_item_param
insert into T_SYS_TYPE_ITEM_PARAM
  (id,
   type_id,
   param_key,
   PARAM_value,
   rank,
   param_type,
   required_flag,
   search_flag,
   shop_id,
   shop_class)
  select id,
         xw_data_sync.xwcatory_id2klid(type_id) type_id,
         param_key,
         PARAM_value,
         rank,
         param_type,
         required_flag,
         search_flag,
         shop_id,
         shop_class
    from (select dense_rank() over(order by b.main_category, a.displayname, a.selvalues) id,
                 b.main_category type_id,
                 a.displayname PARAM_KEY,
                 a.selvalues PARAM_value,
                 a.position rank,
                 decode(a.type, 'select', 1, 'check', 2, 3) param_type,
                 0 as required_flag, --是否必填 0-no 1-必填
                 a.ISCANSEARCH search_flag, --是否参与检索 0-否 1-是
                 null as shop_id, --商家id
                 2 as shop_class --商家类型 1-业务门店2-商户3-渠道商       
            from xwmall.PMTEMPLATEFIELDS a, xwmall.product b
           where a.templateid = b.template_id
             and b.status = '1'
             and b.checkstatus = '1'
             and b.type = '0'
             and b.del_flag = '0')
   group by id,
            type_id,
            param_key,
            PARAM_value,
            rank,
            param_type,
            required_flag,
            search_flag,
            shop_id,
            shop_class;
      logdata(9,
              't_sys_type_item_param',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入行政区域表t_sys_region
      insert into t_sys_region
        (id,
         region_code,
         region_name,
         region_level,
         parent_region,
         short_name,
         region_spell,
         is_show,
         sort_num,
         area_code)
        select 10000 + rownum as id, --id
               REGION_CODE, --区域代码
               REGION_NAME, --区域名称
               2 as region_level, --区域等级，省、地市、区/县
               parent_code, --父区域 0-一级区域
               null, --简称
               null as region_spell, --区域拼音
               1, --是否显示 0-不显示 1-显示
               9999, --排序
               '0'|| AREA_CODE --地市码
          from xwmall.SYSTEM_AREA;
      logdata(11,
              't_sys_region',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
      delete from t_sys_region where region_code='000000';
      --update t_sys_region set region_level=2;
      logdata(11,
              't_sys_region',
              'update',
              'sucess update L2' || sql%rowcount || ' rows');
      update t_sys_region set region_level=1 where region_code like '%00';
      logdata(11,
              't_sys_region',
              'update',
              'sucess update L1' || sql%rowcount || ' rows');
      update t_sys_region set region_level=0 where region_code like '%0000';
      logdata(11,
              't_sys_region',
              'update',
              'sucess update L0' || sql%rowcount || ' rows'); 
      update t_sys_region set  PARENT_REGION='0' where PARENT_REGION='000000';
      logdata(11,
              't_sys_region',
              'update',
              'sucess update p0' || sql%rowcount || ' rows');       
    end;

    begin
      --插入属性配置相关T_SYS_PROPERTY
      null;
    end;

    begin
      --插入结算商户表t_store
      insert into t_store
        (id,
         name,
         short_name,
         area_code,
         sort,
         shop_agent_flag,
         goods_agent_flag,
         base_shop_name,
         create_time,
         update_time,
         address,
         sys_user_id,
         update_user_id,
         is_bs_account,
         bs_manager_name,
         bs_manager_phone,
         fc_manager_name,
         fc_manager_phone,
         link_name,
         link_phone,
         link_fax,
         shop_class,
         status,
         is_valid,
         item_edit_audit_flag,
         item_publish_audit_flag,
         shop_edit_audit_flag,
         sync_gy_flag,
         bs_scope,
         service_phone,
         area_id,
         merchid)
        select xwstore_id2klid(id) id, --生成新商城商户ID
               --        a.pa_store_id xw_parent_storeid,  --pa_store_id,该字段都为空，无法据此确定store表中门店之间的代理关系,
               a.store_name name, --商户名称
               substr(a.nickname, 0, 10) short_name, --商户简称
               a.area_id area_code, --归属地市
               --       a.store_ad,                          --商品介绍、广告信息
               --       a.logo,                              --商户图片
               '1' as sort, --是否移动签约 0--非签约 1--签约
               '0' shop_agent_flag, --是否折扣商户代理 0-否 1-是
               '0' goods_agent_flag, --是否商品销售代理 0-否 1-是
               null as base_shop_name, --总店名称
               to_char(to_date(nvl(trim(a.create_time),
                                   '2000-01-01 00:00:00'),
                               'yyyy-mm-dd hh24:mi:ss'),
                       'yyyymmddhh24miss') create_time, --创建时间
               null as update_time, --更新时间
               b.address address, --商户地址
               null as sys_user_id, --创建后台用户
               null as update_user_id, --更新用户
               '1' is_bs_account, --是否企业帐户 0--不是 1--是
               a.business_name bs_manager_name, --业务主管
               a.business_phone bs_manager_phone, --业务主管电话
               a.finance_name fc_manager_name, --财务主管
               a.finance_phone fc_manager_phone, --财务主管电话
               b.corporate link_name, --联系人
               a.service_phone link_phone, --联系电话
               b.business_fax link_fax, --联系传真
               '2' shop_class, --商户分类 1--业务门店 2--结算商户 3--渠道商
               '3' status, --商户状态 0--草稿 1--待审核 2--审核中 3--审核通过 4--审核驳回
               '1' is_valid, --商户是否有效 0--下架 1--上架
               '0' item_edit_audit_flag, --商品编辑免审 0--需要审核 1--免审
               '0' item_publish_audit_flag, --商品发布免审 0--需要审核 1--免审
               '0' shop_edit_audit_flag, --门店编辑免审 0--需要审核 1--免审
               '0' sync_gy_flag, --是否成功同步高阳 0--未同步 1--成功同步 2-同步失败 3-审核通过 4-审核驳回
               a.business_scope bs_scope, --营业范围
               a.service_phone service_phone, --客服电话
               a.area_id area_id, --行政编码
               null as merchid --高阳结算id
        --a.id xw_store_id                     --欣网store_id
          from xwmall.store                  a,
               xwmall.store_info             b,
               xwmall.hisun_merchant_mapping c
         where a.check_status='1' and a.status=1 and a.del_flag=0 and a.id = b.uuid
           and a.id = c.merchant_id(+);
      logdata(12,
              't_store',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;


    begin
      --商户开户与结算信息T_STORE_SETTLE

      insert into t_store_settle
        (id,
         store_id,
         merchid,
         settle_type,
         fee_settle,
         settle_period,
         settle_day,
         settle_daybit,
         settle_trfdays,
         settle_beginamt,
         min_retainedamt,
         withdraw_bankid,
         openbank_desc,
         settle_ac,
         bank_acname,
         effort_date,
         expiry_date,
         sync_gy_flag,
         status,
         create_time,
         create_user,
         sync_time)
        select rownum id,
               xwstore_id2klid(b.merchant_id) STORE_ID, --商户id
               b.mapping_id MERCHID, --高阳结算id
               0 SETTLE_TYPE, --结算类型 0：系统结算 1：手工结算
               a.settle_type FEE_SETTLE, --佣金结算 0：收支两条线 1：作扣
               a.settle_period SETTLE_PERIOD, --结算周期 0：日 1：周 2：旬 3：月 4：季 5：半年 6：年 7：指定日 这句注释看不出有什么问题，加上sql就执行报错，去掉就能执行
               a.settle_day SETTLE_DAY, --结算日  标识结算间隔的数量
               instr(a.settle_day_bit, '1', 1, 1) SETTLE_DAYBIT, --结算日标志位 指定结算日时设置一个月31天的具体日期
               a.settle_trf_days SETTLE_TRFDAYS, --结算划款天数
               a.incept_amount SETTLE_BEGINAMT, --结算起始金额
               a.preserve_amount MIN_RETAINEDAMT, --最低留存金额
               a.settle_bank WITHDRAW_BANKID, --结算银行的名称
               a.establish_bank_desc OPENBANK_DESC, --开户行详细信息
               a.settle_account_no SETTLE_AC, --结算账户
               a.settle_account_name BANK_ACNAME, --开户人
               substr(a.effect_start_time, 1, 8) EFFORT_DATE, --生效日期
               substr(a.effect_end_time, 1, 8) EXPIRY_DATE, --失效日期
               0 SYNC_GY_FLAG, --否成功同步高阳  0：未同步  1：成功同步
               decode(a.stat, 'W', 0, 'A', 1, 'U', 2) STATUS, --状态 欣网状态值 A 有效 U 无效 W 待审核，
               substr(a.create_time, 0, 14) create_time, --创建时间
               --a.creator CREATE_USER, --创建人
               null    as CREATE_USER,
               null as SYNC_TIME --同步时间
        --a.merchant_id xw_store_id            --欣网store_id
          from xwmall.finance_settle_info    a,
               xwmall.hisun_merchant_mapping b
         where a.merchant_id = b.merchant_id;
      logdata(13,
              'T_STORE_SETTLE',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入商户开票信息T_SHOP_INVOICE
      null;
    end;


    begin
      --插入商户设置信息T_SHOP_SETTINGS
      insert into t_shop_settings
        (id, shop_pic_url, homepage_word, shop_class, shop_id, pic_name,pub_tag)
        select 10000 + rownum ID,
               ltrim(t.logo,'userfiles/product/img/')  SHOP_PIC_URL, --商户招牌图片路径
               t.STORE_AD HOMEPAGE_WORD, --首页文字介绍
               2 SHOP_CLASS, --1-业务门店2-商户3-渠道商
               xwstore_id2klid(t.id) SHOP_ID,
               null as PIC_NAME, --图片名称
         1 as PUB_TAG
          from xwmall.store t;
      logdata(16,
              'T_SHOP_SETTINGS',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入货架分类表T_GOOD_SHELF
      insert into t_good_shelf
        (id,
         title,
         pid,
         level_flag,
         order_index,
         shop_class,
         shop_id,
         shop_user_id,
         update_time,
         img_url)
        select id,
               title, --货架分类名称
               decode(pid, '', 0, pid) pid, --父货架ID
               decode(PID, '', 1, 2) LEVEL_FLAG, --货架层级（暂只支持三级:1,2,3）
               ORDER_INDEX, --排序
               SHOP_CLASS, --商户分类：1--业务门店2--商户3--渠道商
               SHOP_ID, --商户编号
               SHOP_USER_ID, --创建商户账号
               UPDATE_TIME, --更新时间
               IMG_URL --图片相对地址
          from (select xw_data_sync.xwcatory_id2klid(t.category_id) id,
                       t.category_name TITLE, --货架分类名称
                       xw_data_sync.xwcatory_id2klid(t.parent_id) PID, --父货架ID
                       -- row_number() over(partition by t.category_id, t.parent_id order by t.category_id asc) LEVEL_FLAG, --货架层级（暂只支持三级:1,2,3）
                       t.POSITION ORDER_INDEX,
                       2 SHOP_CLASS,
                       xw_data_sync.xwstore_id2klid(store_id) SHOP_ID,
                       null as SHOP_USER_ID,
                       null as UPDATE_TIME,
                       ltrim(t.NAV_IMAGE,'userfiles/product/img/') as IMG_URL
                  from xwmall.productcategory t
                 where t.store_id <> ' ');
      logdata(17,
              'T_GOOD_SHELF',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入货架关联商品关系表T_GOODSHELF_GOODS_REL
      insert into t_goodshelf_goods_rel
        (id, shelf_id, good_id)
        select 10000 + rownum ID,
               xwcatory_id2klid(t.category_id) SHELF_ID,
               xwproduct_id2klid(t.product_id) GOOD_ID
          from xwmall.product_cate_rel t
         where xwproduct_id2klid(t.product_id) is not null;
      logdata(18,
              't_goodshelf_goods_rel',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入商户用户表T_SHOP_USER
      insert into t_shop_user
        (id,
         code,
         pwd,
         status,
         update_time,
         type,
         email,
         mobile,
         nick_name,
         shop_class,
         shop_id)
        select 10000 + rownum ID,
               a.e_mail CODE,
               lower(xw_data_sync.md5(a.password)),
               1 STATUS, --用户状态 0-失效 1-有效 2-暂停
               null as UPDATE_TIME, --
               1 TYPE, --用户类型 1-管理员 2-普通用户 3-操作员
               a.e_mail EMAIL,
               a.mobile MOBILE,
               null as NICK_NAME,
               2 SHOP_CLASS, --1--业务门店 2--商户 3--渠道商
               xwstore_id2klid(a.id) SHOP_ID
        --a.id xw_store_id               --欣网store_id
          from xwmall.store a;
      logdata(19,
              'T_SHOP_USER',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;
  
  
   begin
      --插入商户用户表T_STORE_FEE
 -----------------------插入阶梯结算方式数据
insert into t_store_fee_tmp
select xw_data_sync.xwstore_id2klid(xwmapid2xwmerid(merc_id)) store_id,
       merc_id merchid,
       sum(cap_typ1) as CAPITAL_TYPE1,
       sum(cap_typ2) as CAPITAL_TYPE2,
       sum(cap_typ3) as CAPITAL_TYPE3,
       TRD_MOD TRADE_MODE,
       FEE_TYP FEE_TYPE,
       xw_fee2kl_fee(merc_id,
                     prod_typ,
                     up_ref1 || '_' || fee_rate1 || '_' || up_ref2 || '_' ||
                     fee_rate2 || '_' || nvl(up_ref3, 0) || '_' ||
                     nvl(fee_rate3, 0) || '_' || nvl(up_ref4, 0) || '_' ||
                     nvl(fee_rate4, 0) || '_' || nvl(up_ref5, 0) || '_' ||
                     nvl(fee_rate5, 0)) PRODUCTION_TYPE,
       PROD_TYP XW_PRODUCTION_TYPE,
       CLR_TYP CLEAR_TYPE,
       EFF_DT EFFORTDATE,
       EXP_DT EXPIRYDATE,
       FEE_DRT_FLG FEEDRTFLAG,
       FEE_PRD_UNIT FEEPERIODUNIT,
       null as FC_NAME,       
       FEE_MOD FEEMODE,
       FEE_MTD FEEMOTHOD,
       BEG_AMT BEGANAMOUNT,
       MIN_FEE_AMT MINFEEAMOUNT,
       MAX_FEE_AMT MAXFEEAMOUNT,
       FEE_BAS_FLG FEEBASICFLAG,
       FEE_LVL_FLG FEELEVELFLAG,
       FEE_LVL_BAS_FLG FEELVLBASICFLAG,
       UP_REF1 UPREFERENCE1,
       FIX_FEE_AMT1 FIXFEEAMOUNT1,
       FEE_RATE1 FEERATE1,
       UP_REF2 UPREFERENCE2,
       FIX_FEE_AMT2 FIXFEEAMOUNT2,
       FEE_RATE2 FEERATE2,
       UP_REF3 UPREFERENCE3,
       FIX_FEE_AMT3 FIXFEEAMOUNT3,
       FEE_RATE3 FEERATE3,
       UP_REF4 UPREFERENCE4,
       FIX_FEE_AMT4 FIXFEEAMOUNT4,
       FEE_RATE4 FEERATE4,
       UP_REF5 UPREFERENCE5,
       FIX_FEE_AMT5 FIXFEEAMOUNT5,
       FEE_RATE5 FEERATE5,
       null as CREATE_TIME,
       null as SYNC_TIME,
       null as CREATE_USER,
       0 STATUS,
       0 SYNC_GY_FLAG1,
       0 SYNC_GY_FLAG2,
       0 SYNC_GY_FLAG3
  from (select merc_id,
               decode(t.cap_typ, 1, 1, 0) cap_typ1,
               decode(t.cap_typ, 2, 1, 0) cap_typ2,
               decode(t.cap_typ, 3, 1, 0) cap_typ3,
               TRD_MOD,
               FEE_TYP,
               PROD_TYP,
               CLR_TYP,
               EFF_DT,
               EXP_DT,
               FEE_DRT_FLG,
               FEE_PRD_UNIT,
               FEE_MOD,
               FEE_MTD,
               BEG_AMT,
               MIN_FEE_AMT,
               MAX_FEE_AMT,
               FEE_BAS_FLG,
               FEE_LVL_FLG,
               FEE_LVL_BAS_FLG,
               UP_REF1,
               FIX_FEE_AMT1,
               FEE_RATE1,
               UP_REF2,
               FIX_FEE_AMT2,
               FEE_RATE2,
               UP_REF3,
               FIX_FEE_AMT3,
               FEE_RATE3,
               UP_REF4,
               FIX_FEE_AMT4,
               FEE_RATE4,
               UP_REF5,
               FIX_FEE_AMT5,
               FEE_RATE5
          from sell_fee_tmp t
         where exp_dt > to_char(sysdate, 'yyyyMMdd')
           and up_ref2 is not null)
 group by merc_id,
          TRD_MOD,
          FEE_TYP,
          PROD_TYP,
          CLR_TYP,
          EFF_DT,
          EXP_DT,
          FEE_DRT_FLG,
          FEE_PRD_UNIT,
          FEE_MOD,
          FEE_MTD,
          BEG_AMT,
          MIN_FEE_AMT,
          MAX_FEE_AMT,
          FEE_BAS_FLG,
          FEE_LVL_FLG,
          FEE_LVL_BAS_FLG,
          UP_REF1,
          FIX_FEE_AMT1,
          FEE_RATE1,
          UP_REF2,
          FIX_FEE_AMT2,
          FEE_RATE2,
          UP_REF3,
          FIX_FEE_AMT3,
          FEE_RATE3,
          UP_REF4,
          FIX_FEE_AMT4,
          FEE_RATE4,
          UP_REF5,
          FIX_FEE_AMT5,
          FEE_RATE5;
      
------------------插入固定结算方式
insert into t_store_fee_tmp
select xw_data_sync.xwstore_id2klid(xwmapid2xwmerid(merc_id)) store_id,
       merc_id merchid,
       sum(cap_typ1) as CAPITAL_TYPE1,
       sum(cap_typ2) as CAPITAL_TYPE2,
       sum(cap_typ3) as CAPITAL_TYPE3,
       TRD_MOD TRADE_MODE,
       FEE_TYP FEE_TYPE,
       xw_fee2kl_fee(merc_id, PROD_TYP, fee_rate1) PRODUCTION_TYPE,
       PROD_TYP XW_PRODUCTION_TYPE,
       CLR_TYP CLEAR_TYPE,
       EFF_DT EFFORTDATE,
       EXP_DT EXPIRYDATE,
       FEE_DRT_FLG FEEDRTFLAG,
       FEE_PRD_UNIT FEEPERIODUNIT,
       null as FC_NAME,
       FEE_MOD FEEMODE,
       FEE_MTD FEEMOTHOD,
       BEG_AMT BEGANAMOUNT,
       MIN_FEE_AMT MINFEEAMOUNT,
       MAX_FEE_AMT MAXFEEAMOUNT,
       FEE_BAS_FLG FEEBASICFLAG,
       FEE_LVL_FLG FEELEVELFLAG,
       FEE_LVL_BAS_FLG FEELVLBASICFLAG,
       UP_REF1 UPREFERENCE1,
       FIX_FEE_AMT1 FIXFEEAMOUNT1,
       FEE_RATE1 FEERATE1,
       UP_REF2 UPREFERENCE2,
       FIX_FEE_AMT2 FIXFEEAMOUNT2,
       FEE_RATE2 FEERATE2,
       UP_REF3 UPREFERENCE3,
       FIX_FEE_AMT3 FIXFEEAMOUNT3,
       FEE_RATE3 FEERATE3,
       UP_REF4 UPREFERENCE4,
       FIX_FEE_AMT4 FIXFEEAMOUNT4,
       FEE_RATE4 FEERATE4,
       UP_REF5 UPREFERENCE5,
       FIX_FEE_AMT5 FIXFEEAMOUNT5,
       FEE_RATE5 FEERATE5,
       null as CREATE_TIME,
       null as SYNC_TIME,
       null as CREATE_USER,
       0 STATUS,
       0 SYNC_GY_FLAG1,
       0 SYNC_GY_FLAG2,
       0 SYNC_GY_FLAG3
  from (select merc_id,
               decode(t.cap_typ, 1, 1, 0) cap_typ1,
               decode(t.cap_typ, 2, 1, 0) cap_typ2,
               decode(t.cap_typ, 3, 1, 0) cap_typ3,
               TRD_MOD,
               FEE_TYP,
               PROD_TYP,
               CLR_TYP,
               EFF_DT,
               EXP_DT,
               FEE_DRT_FLG,
               FEE_PRD_UNIT,
               FEE_MOD,
               FEE_MTD,
               BEG_AMT,
               MIN_FEE_AMT,
               MAX_FEE_AMT,
               FEE_BAS_FLG,
               FEE_LVL_FLG,
               FEE_LVL_BAS_FLG,
               UP_REF1,
               FIX_FEE_AMT1,
               FEE_RATE1,
               UP_REF2,
               FIX_FEE_AMT2,
               FEE_RATE2,
               UP_REF3,
               FIX_FEE_AMT3,
               FEE_RATE3,
               UP_REF4,
               FIX_FEE_AMT4,
               FEE_RATE4,
               UP_REF5,
               FIX_FEE_AMT5,
               FEE_RATE5
          from sell_fee_tmp t
         where exp_dt > to_char(sysdate, 'yyyyMMdd')
           and up_ref2 is null order by merc_id, prod_typ)
 group by merc_id,
          TRD_MOD,
          FEE_TYP,
          PROD_TYP,
          CLR_TYP,
          EFF_DT,
          EXP_DT,
          FEE_DRT_FLG,
          FEE_PRD_UNIT,
          FEE_MOD,
          FEE_MTD,
          BEG_AMT,
          MIN_FEE_AMT,
          MAX_FEE_AMT,
          FEE_BAS_FLG,
          FEE_LVL_FLG,
          FEE_LVL_BAS_FLG,
          UP_REF1,
          FIX_FEE_AMT1,
          FEE_RATE1,
          UP_REF2,
          FIX_FEE_AMT2,
          FEE_RATE2,
          UP_REF3,
          FIX_FEE_AMT3,
          FEE_RATE3,
          UP_REF4,
          FIX_FEE_AMT4,
          FEE_RATE4,
          UP_REF5,
          FIX_FEE_AMT5,
          FEE_RATE5;

  
      --更新fee_type字段
      --update T_STORE_FEE_TMP set fee_type=substr(xw_production_type,1,2);
    
--插入正式表
insert into T_STORE_FEE
  (id,
   store_id,
   merchid,
   capital_type1,
   capital_type2,
   capital_type3,
   trade_mode,
   fee_type,
   production_type,
   clear_type,
   effortdate,
   expirydate,
   feedrtflag,
   feeperiodunit,
   fc_name,
   feemode,
   feemothod,
   beganamount,
   minfeeamount,
   maxfeeamount,
   feebasicflag,
   feelevelflag,
   feelvlbasicflag,
   upreference1,
   fixfeeamount1,
   feerate1,
   upreference2,
   fixfeeamount2,
   feerate2,
   upreference3,
   fixfeeamount3,
   feerate3,
   upreference4,
   fixfeeamount4,
   feerate4,
   upreference5,
   fixfeeamount5,
   feerate5,
   create_time,
   sync_time,
   create_user,
   status,
   sync_gy_flag1,
   sync_gy_flag2,
   sync_gy_flag3)
  select rownum,
         store_id,
         null as merchid,
         capital_type1,
         capital_type2,
         capital_type3,
         trade_mode,
         fee_type,
         production_type,
         clear_type,
         effortdate,
         expirydate,
         feedrtflag,
         feeperiodunit,
         fc_name,
         feemode,
         feemothod,
         beganamount,
         minfeeamount,
         maxfeeamount,
         feebasicflag,
         feelevelflag,
         feelvlbasicflag,
         upreference1,
         fixfeeamount1,
         feerate1,
         upreference2,
         fixfeeamount2,
         feerate2,
         upreference3,
         fixfeeamount3,
         feerate3,
         upreference4,
         fixfeeamount4,
         feerate4,
         upreference5,
         fixfeeamount5,
         feerate5,
         create_time,
         sync_time,
         create_user,
         status,
         sync_gy_flag1,
         sync_gy_flag2,
         sync_gy_flag3
    from T_STORE_FEE_TMP;
        
      logdata(50,
              'T_STORE_FEE',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;
  

    begin
      --插入商品发布临时表t_item_sale
     insert into T_ITEM_SALE_TMP (
 select xw_data_sync.xwproduct_id2klid(a.product_id) id, --生成新商城商品ID
               0 ORG_ID,
               0 SALE_PRICE_ID, --新商城价格体系表，t_item_price.id
               null as VERIFY_CODE_TYPE, --码类型 1--维码 2--二维码
               0 SEND_CODE_MODE, --发码方式 0--不发码 1--按照订单发码 2--按照商品个数发码
               null as SEND_CODE_CHANNEL, --制码方 0--平台自己 1--方正码平台 2--第三方应用
               null as SEND_CODE_SRC, --制码渠道 如果制码方选择第三方应用，则该字段有用 10--85度C 11--鲜芋仙 ...
               decode(a.TYPE, '0', 1, '1', 0, 0) POST_FLAG, --根据欣网商品是否虚拟商品确定，不一定准确？ 是否需要物流配送 0--不需要物流配送1--需要物流配送
               a.starttime SALE_START_TIME, --销售有效开始时间
               a.endtime SALE_STOP_TIME, --销售有效结束时间
               null as VERIFY_START_TIME, --验证有效开始时间
               null as VERIFY_STOP_TIME, --验证有效结束时间
               a.quantity STOCK_NUM, --商品库存数量
               0 USER_PER_BUY_NUM, --单个用户购买数量
               a.checkstatus STATUS, --商品状态 0-草稿 1-待审核 2-审核中 3-审核通过 4-审核驳回  (发布以后状态 5-草稿 6-待审核 7-审核中 8-审核通过 9-审核驳回 )
               a.status IS_VALID, --商品是否有效 0--下架 1--上架
               0 SYNC_GY_FLAG, --是否同步高阳 0--未同步 1--成功同步 2-同步失败 3-审核通过 4-审核驳回
               2 SHOP_CLASS, --结算商户类型 2--商户 3--渠道商
               xw_data_sync.xwstore_id2klid(a.store_id) store_id, --匹配表t_store
               a.list_price MARKET_PRICE, --市场价
               a.type ITEM_MODE, --商品类型 0--实物 1--虚拟物
               xw_data_sync.xwcatory_id2klid(a.main_category) TYPE_ID, --商品分类
               0 GROUP_FLAG, --是否是套餐 是否是优惠套餐，如果是则是N多商品的组合 0--普通商品 1--优惠套餐
               decode(a.type, 1, 1, 0) VIRTUAL_FLAG, --是否虚拟商品 是否虚拟商品 0-否 1-是
               null as VIRTUAL_TYPE, --虚拟商品类型 虚拟商品类型 1-卡密 2-直充
               a.product_name NAME, --商品名称
               null as SHORT_NAME, --商品简称
               null as WARM_PROMPT, --温馨提示
               a.description REMARK, --商品介绍
               null as SHOP_USER_ID, --创建商户账号
               to_char(to_date(nvl(trim(a.update_time),
                                   '2013-06-16 18:51:00'),
                               'yyyy-mm-dd hh24:mi:ss'),
                       'yyyymmddhh24miss') UPDATE_TIME, --更新时间
               null as BRAND, --品牌
               a.weight WEIGHT, --重量
               to_char(to_date(nvl(trim(a.create_time),
                                   '2013-06-16 18:51:00'),
                               'yyyy-mm-dd hh24:mi:ss'),
                       'yyyymmddhh24miss') CREATE_TIME, --创建时间
               null as CREATE_USER_ID, --创建人
               null as UPDATE_USER_ID, --更新人
               null as MARKET_CONTENT, --营销语
               a.unit_price SHOP_PRICE, --商城价
               a.BIG_PICTURE as IMG_PATH, --封面图路径
               null as FEE_TYPE, --对应t_sys_fee表数据
               null as settle_price, --结算价
               null as verify_day, --验证天数
               a.main_category as  xw_main_category,
               a.settlement_type xw_settlement_type  --0 普通(01) 1 促销(02) 2，3废弃

        /*
                                                                                                                       a.cash_flag xw_cash_flag,                  --欣网商城支付方式 0 仅支持商城币支付 1 现金或商城币支付
                                                                                                                       a.del_flag xw_del_flag,                    --商品删除标记，0 表示未删除 ？  1 表示已删除？待确认
                                                                                                                       a.ESALESNO xw_ESALESNO,                    --dzq 电子券 --oneCode 一维码 --cateCode 方正二维码 --caipiaoJh 彩票，已不用 --givemeet 捐款，已不用 --ofcard 点卡，已不用
                                                                                                                       a.BIG_PICTURE  xw_BIG_PICTURE,
                                                                                                                       a.SMALL_PICTURE xw_SMALL_PICTURE,
                                                                                                                       a.CENTER_PICTURE xw_CENTER_PICTURE,
                                                                                                                       a.POP_PICTURE xw_POP_PICTURE,
                                                                                                                       a.POP_PICTURE1 xw_POP_PICTURE1,
                                                                                                                       a.POP_PICTURE2 xw_POP_PICTURE2,
                                                                                                                       a.POP_PICTURE3 xw_POP_PICTURE3,
                                                                                                                       a.POP_PICTURE4 xw_POP_PICTURE4,
                                                                                                                       a.POP_PICTURE5 xw_POP_PICTURE5,
                                                                                                                       b.field_value xw_field_value,
                                                                                                                       b.field_smallvalue xw_field_smallvalue,
                                                                                                                       b.field_centervalue xw_field_centervalue
                                                                                                                    */
          from xwmall.product a,xwmall.hisun_merchant_mapping b
        --, xwmall.TEMPLATEBLOBFIELD b
         where
         a.store_id=b.merchant_id and
        --a.product_id = b.product_id(+) and
         a.checkstatus = '1'
         and
         a.type='0'
         and
         a.status='1'
         and a.del_flag = '0');
          
       
--更新t_item_sale_tmp feetype
--01状态       
merge into (select * from t_item_sale_tmp where xw_settlement_type='0') a
using (select  store_id,xw_production_type,production_type from (
select store_id,xw_production_type,production_type,substr(xw_production_type,1,2) FTYPE from t_store_fee_tmp) where ftype='01'
group by store_id,xw_production_type,production_type)  b
on (a.store_id=b.store_id and '01'||a.xw_main_category=b.xw_production_type)
when matched then
  update set a.fee_type = b.production_type; 
  
--02状态  
merge into (select * from t_item_sale_tmp where xw_settlement_type='1') a
using (select  store_id,xw_production_type,production_type from (
select store_id,xw_production_type,production_type,substr(xw_production_type,1,2) FTYPE from t_store_fee_tmp) where ftype='02'
group by store_id,xw_production_type,production_type)  b
on (a.store_id=b.store_id and '02'||a.xw_main_category=b.xw_production_type)
when matched then
  update set a.fee_type = b.production_type; 
       
       
insert into t_item_sale
  (ID,
   ORG_ID,
   SALE_PRICE_ID,
   VERIFY_CODE_TYPE,
   SEND_CODE_MODE,
   SEND_CODE_CHANNEL,
   SEND_CODE_SRC,
   POST_FLAG,
   SALE_START_TIME,
   SALE_STOP_TIME,
   VERIFY_START_TIME,
   VERIFY_STOP_TIME,
   STOCK_NUM,
   USER_PER_BUY_NUM,
   STATUS,
   IS_VALID,
   SYNC_GY_FLAG,
   SHOP_CLASS,
   STORE_ID,
   MARKET_PRICE,
   ITEM_MODE,
   TYPE_ID,
   GROUP_FLAG,
   VIRTUAL_FLAG,
   VIRTUAL_TYPE,
   NAME,
   SHORT_NAME,
   WARM_PROMPT,
   REMARK,
   SHOP_USER_ID,
   UPDATE_TIME,
   BRAND,
   WEIGHT,
   CREATE_TIME,
   CREATE_USER_ID,
   UPDATE_USER_ID,
   MARKET_CONTENT,
   SHOP_PRICE,
   IMG_PATH,
   FEE_TYPE,
   SETTLE_PRICE,
   VERIFY_DAY)
  select id,
         ORG_ID,
         SALE_PRICE_ID,
         VERIFY_CODE_TYPE,
         SEND_CODE_MODE,
         SEND_CODE_CHANNEL,
         SEND_CODE_SRC,
         POST_FLAG,
         SALE_START_TIME,
         SALE_STOP_TIME,
         VERIFY_START_TIME,
         VERIFY_STOP_TIME,
         STOCK_NUM,
         USER_PER_BUY_NUM,
         STATUS,
         IS_VALID,
         SYNC_GY_FLAG,
         SHOP_CLASS,
         STORE_ID,
         MARKET_PRICE,
         ITEM_MODE,
         TYPE_ID,
         GROUP_FLAG,
         VIRTUAL_FLAG,
         VIRTUAL_TYPE,
         NAME,
         SHORT_NAME,
         WARM_PROMPT,
         REMARK,
         SHOP_USER_ID,
         UPDATE_TIME,
         BRAND,
         WEIGHT,
         CREATE_TIME,
         CREATE_USER_ID,
         UPDATE_USER_ID,
         MARKET_CONTENT,
         SHOP_PRICE,
         IMG_PATH,
         FEE_TYPE,
         SETTLE_PRICE,
         VERIFY_DAY
    from t_item_sale_tmp;
  
      logdata(20,
              't_item_sale',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;
  
begin
  --插入T_PAGE_STATIC_INFO表
  insert into T_PAGE_STATIC_INFO
    (id, type, resource_id, status, create_time)
    select 10000 + rownum as id,
           0 as type,
           id as resource_id,
           0 as status,
           to_char(sysdate,'yyyymmddhh24miss') as create_time
      from t_item_sale;

  logdata(28,
          't_page_static_info',
          'insert',
          'sucess insert ' || sql%rowcount || ' rows');
end;

begin
      --插入商品参数表t_item_param
insert into T_ITEM_PARAM 
select rownum id,
       xw_data_sync.xwcatory_id2klid(b.main_category) type_id,
       xw_data_sync.xwproduct_id2klid(b.product_id) product_id,       
       dense_rank() over(order by b.main_category,a.displayname) param_id,       
       a.displayname PARAM_KEY,
       decode(a.name,
              'extField1',
              b.ext_field1,
              'extField2',
              b.ext_field2,
              'extFileld3',
              b.extfileld3,
              'extFileld4',
              b.EXTFILELD4,
              'extFileld5',
              b.EXT_FILELD5,
              'extFileld6',
              b.EXT_FILELD6,
              'extFileld7',
              b.EXT_FILELD7,
              'extFileld8',
              b.EXT_FILELD8,
              'extFileld9',
              b.EXT_FILELD9,
              'extFileld10',
              b.EXT_FILELD10,
              'extFileld11',
              b.EXT_FILELD11,
              'extFileld12',
              b.EXT_FILELD12,
              'extFileld13',
              b.EXT_FILELD13,
              'extFileld14',
              b.EXT_FILELD14,
              'extFileld15',
              b.EXT_FILELD15,
              'extFileld16',
              b.EXT_FILELD16,
              'extFileld17',
              b.EXT_FILELD17,
              'extFileld18',
              b.EXT_FILELD18,
              'extFileld19',
              b.EXT_FILELD19,
              'extFileld20',
              b.EXT_FILELD20) PARAM_VALUE,
       a.position rank
  from xwmall.PMTEMPLATEFIELDS a, xwmall.product b
 where a.templateid = b.template_id
   and b.status = '1'
   and b.checkstatus = '1'
   and b.type = '0'
   and b.del_flag = '0';
      logdata(21,
              't_item_param',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入商品评论、咨询表数据T_ITEM_COMMENT
      insert into T_ITEM_COMMENT_TEMP
        select 10000 + rownum as id,
               CONTENT,
               type,
               QUESTION_TYPE,
               UPDATE_TIME,
               NICKNAME,
               USER_ID,
               STATUS,
               xwproduct_id2klid(SALE_ID),
               AUDIT_USER,
               AUDIT_TIME,
               RANK,
               USEFUL_NUM,
               USELESS_NUM,
               xwstore_id2klid(STORE_ID),
               REPLY_COMMENT,
               REPLY_TIME
          from (select a.description as CONTENT,
                       1 as TYPE,
                       0 as QUESTION_TYPE,
                       translate(a.create_time, '/- :', '/') as UPDATE_TIME,
                       b.nickname as NICKNAME,
                       --a.app_user as USER_ID,
                       null        as user_id,
                       a.status    as STATUS,
                       a.productid as SALE_ID, --
                       null        as AUDIT_USER,
                       null        as AUDIT_TIME,
                       a.count     as RANK,
                       0           as USEFUL_NUM,
                       0           as USELESS_NUM,
                       a.store_id  as STORE_ID,
                       null        as REPLY_COMMENT,
                       null        as REPLY_TIME
                  from xwmall.PRODUCT_APPRAISAL a, xwmall.customer b
                 where a.app_user = b.customerid(+)
                union all
                select a.description as CONTENT,
                       2 as TYPE,
                       0 as QUESTION_TYPE,
                       translate(a.create_time, '/- :', '/') as UPDATE_TIME,
                       b.nickname as NICKNAME,
                       --a.user_id as USER_ID,
                       null as user_id,
                       a.status as STATUS,
                       a.product_id as SALE_ID, --
                       null as AUDIT_USER,
                       translate(a.check_time, '/- :', '/') as AUDIT_TIME,
                       null as RANK,
                       0 as USEFUL_NUM,
                       0 as USELESS_NUM,
                       a.store_id as STORE_ID,
                       a.reply as REPLY_COMMENT,
                       translate(a.reply_time, '/- :', '/') as REPLY_TIME
                  from xwmall.USER_QUESTION a, xwmall.customer b
                 where a.user_id = b.customerid(+));
      logdata(22,
              'T_ITEM_COMMENT_TEMP',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
      insert into t_item_comment
        (id,
         content,
         type,
         question_type,
         update_time,
         nickname,
         user_id,
         status,
         sale_id,
         audit_user,
         audit_time,
         rank,
         useful_num,
         useless_num,
         store_id)
        select id,
               CONTENT, --评论内容
               type, --评论类别 1-评论2-咨询
               QUESTION_TYPE, --咨询类型0-商品咨询1-活动咨询
               UPDATE_TIME, --更新时间
               NICKNAME, --评论人昵称
               USER_ID, --评论人ID
               STATUS, --0-未审核1-审核通过2-审核驳回
               SALE_ID, --商品id
               AUDIT_USER, --审核人
               AUDIT_TIME, --审核时间
               RANK, --评分
               USEFUL_NUM,
               USELESS_NUM,
               STORE_ID --商户id
          from T_ITEM_COMMENT_TEMP;
      logdata(22,
              't_item_comment',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
      --商品咨询回复表t_item_comment_reply
      insert into t_item_comment_reply
        (id, content, update_time, nickname, user_id, comment_id)
        select rownum + 10000 as iid,
               REPLY_COMMENT, --回复内容
               REPLY_TIME, --回复时间
               NICKNAME, --评论人昵称
               USER_ID, --评论人id
               id as comment_id --对应的t_item_comment评论id
          from T_ITEM_COMMENT_TEMP
         where type = 2;
      logdata(23,
              't_item_comment_reply',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入已发布商品其它属性表t_item_sale_ext
      insert into t_item_sale_ext
        (id,
         sale_id,
         sale_num,
         click_num,
         comment_num,
         user_num,
         collect_num,
         rank,
         logistics_fee,
         logistics_fee_type)
        select rownum + 10000 as id,
               xw_data_sync.xwproduct_id2klid(a.product_id),
               0 as SALE_NUM, --销售数量
               a.APP_USERCOUNT, --人气数
               0 as COMMENT_NUM, --评论数量
               decode(BUY_QUANTITY, ' ', 0), --购买人数
               a.FAV_COUNT, --收藏数量
               null, --商品评分
               b.amount, --物流运费
               0 as LOGISTICS_FEE_TYPE --物流计算方式
          from xwmall.product a, xwmall.shiptype b
         where a.ship_type = b.shiptypeid;
      logdata(24,
              't_item_sale_ext',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
      update t_item_sale_ext a
         set a.COMMENT_NUM = (select count(*)
                                from T_ITEM_COMMENT b
                               where b.sale_id = a.SALE_ID);
      logdata(25,
              't_item_sale_ext',
              'update',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入商品销售地区t_item_sale_area_link
      insert into t_item_sale_area_link
        (id, sale_id, province_code, city_code, region_code)
        select rownum + 10000 as id,
               xwproduct_id2klid(a.product_id) as sale_id, --商品id
               null as PROVINCE_CODE, --省
               b.PARENT_CODE, --市
               b.region_code --地区
          from xwmall.PRODUCT_AREA_RELATION a, xwmall.CHINA_AREA b
         where a.region_code = b.region_code;

      logdata(26,
              't_item_sale_area_link',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --已发布商品会员购买限制表t_item_sale_user_level_link
      insert into t_item_sale_user_level_link
        (id, sale_id, user_level)
        select rownum + 10000 as id,
               xwproduct_id2klid(a.product_id) as SALE_ID, --已发布商品ID
               a.VIP_STATUS --允许购买会员级别
          from xwmall.product a
         where a.checkstatus = '1'
           and a.del_flag = '0';
      logdata(27,
              't_item_sale_user_level_link',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;


    begin
      --插入用户表t_member
       insert into t_member
        (id,
         user_name,
         user_pass,
         email,
         real_name,
         terminal_id,
         reg_time,
         status,
         update_time,
         vid,
         nick_name,
         area_code,
         sex,
         birthday,
         opened,
         qq,
         remark,
         signature,
         reg_source,
         user_level,
         cart_uuid,
         red_member)
                        select * from (
        select xwmember_id2klid(Customerid) as id,
               user_name,
               user_pass,
               email,
               real_name,
               terminal_id,
               reg_time,
               status,
               update_time,
               vid,
               nick_name,
               null as area_code,
               sex,
               birthday,
               opened,
               qq,
               remark,
               signature,
               reg_source,
               user_level,
               cart_uuid,
               red_member
          from (select a.Customerid,
                       null as USER_NAME,
                       a.PASSWORD as USER_PASS,
                       a.EMAIL,
                       trim(a.CUSTOMERNAME) as REAL_NAME,
                       a.MOBILE as TERMINAL_ID,
                       translate(a.CREATE_TIME, '/- :', '/') as REG_TIME,
                       a.status,
                       translate(a.UPDATE_TIME, '/- :', '/') as update_time,
                       null as VID,
                       a.NICKNAME as nick_name,
                       a.REGION as area_code,
                       a.sex,
                       a.BIRTHDAY,
                       0 as OPENED,
                       null as QQ,
                       null as REMARK,
                       null as SIGNATURE,
                       0 as REG_SOURCE,
                       0 as user_level,
                       null as CART_UUID,
                       decode(b.c_class,'STANDARD',0,'VIP',1,0) as red_member
                  from xwmall.customer a,trxsys.card_master b where a.customerid=b.c_id(+))) where id is not null;
      logdata(30,
              't_member',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入用户地址表t_member_address,欣网的数据仅有家庭地址信息，公司地址信息没有
      insert into t_member_address
        select rownum + 10000 as id,
               mid,
               remark,
               region,
               address,
               zipcode,
               name,
               tmobile,
               phone,
               update_time,
               create_time,
               last_use_time,
               default_shipping,
               DEFAULT_PAY_TYPE
          from (select xw_data_sync.xwmember_id2klid(a.customerid) as mid, --用户编号
                       null as REMARK, --地址标注
                       a.REGION, --地区编码
                       a.HOME_ADDRESS as ADDRESS, --详细地址
                       translate(a.ZIPCODE, '0123456789 v', '0123456789') as ZIPCODE, --邮政编码
                       a.name as NAME, --收货人
                       substr(translate(a.MOBILE,
                                        '0123456789０１２３９５８９６是 -',
                                        '0123456789'),
                              1,
                              14) as tmobile, --联系电话：手机
                       substr(translate(a.HOME_PHONE,
                                        '0123456789０１２３９５８９６是 -',
                                        '0123456789'),
                              1,
                              14) as PHONE, --联系电话：固话
                       null as UPDATE_TIME, --更新时间
                       translate(a.CREATE_TIME, '/- :', '/') as CREATE_TIME, --创建时间
                       null as LAST_USE_TIME, --最后使用时间
                       a.ISDEFAULT as DEFAULT_SHIPPING, --默认地址标志 1是 0否
                       null as DEFAULT_PAY_TYPE --默认支付方式（保留）
                  from xwmall.customer_info a
                 where xw_data_sync.xwmember_id2klid(a.customerid) is not null);
      logdata(31,
              't_member_address',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入短信购活动配置表t_smsbuy_act_online
      insert into t_smsbuy_act_online
        (act_id,
         act_name,
         act_desc,
         act_area,
         start_time,
         end_time,
         status,
         store_id)
        select id,
               name, --活动名称
               act_desc, --活动描述
               act_area, --0表示全省 地市用区号表示，多个地市用逗号分隔
               start_time, --开始时间
               end_time, --结束时间
               status, --audit：待审核 release：待发布 online：商用pause：暂停 offline：下线
               store_id --商户id
          from (select a.id,
                   a.name,
                       null as ACT_DESC,
                       0 as ACT_AREA,
                       a.ONTIME as START_TIME,
                       a.OFFTIME as END_TIME,
                       decode(a.STATUS, 'A', 'online', 'W', 'offline') as STATUS,
                       null as STORE_ID
                  from MARKETING.ONEKEY_BUY_ACTIVITY a);
      logdata(33,
              't_smsbuy_act_online',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
    end;

    begin
      --插入短信购商品指令表T_SMSBUY_ITEM_ROUTER
insert into t_smsbuy_item_router
  (id,
   act_id,
   item_id,
   item_name,
   sp_code,
   cmd_opt_type,
   command,
   pay_type,
   item_price,
   is_session)
  select rownum + 20000 as id,
         ACT_ID,
         ITEM_ID,
         ITEM_NAME,
         sp_code,
         CMD_OPT_TYPE,
         COMMAND,
         pay_type,
         ITEM_PRICE,
         IS_SESSION
    from (select a.ACTIVITYID as ACT_ID,
                 xw_data_sync.xwproduct_id2klid(a.PRODUCT_ID) as ITEM_ID,
                 a.PRODUCT_NAME as ITEM_NAME,
                 '106583641' as sp_code,
                 1 as CMD_OPT_TYPE,
                 a.COMMAND as COMMAND,
                 decode(a.PAYTYPE, '0', 1, '1', 2) as pay_type,
                 a.QUANTITY as ITEM_PRICE,
                 0 as IS_SESSION
            from MARKETING.ONEKEY_BUY_PRODUCTS a
          union all
          select a.ACTIVITYID as ACT_ID,
                 xw_data_sync.xwproduct_id2klid(a.PRODUCT_ID) as ITEM_ID,
                 a.PRODUCT_NAME as ITEM_NAME,
                 '106586181' as sp_code,
                 1 as CMD_OPT_TYPE,
                 a.COMMAND as COMMAND,
                 decode(a.PAYTYPE, '0', 1, '1', 2) as pay_type,
                 a.QUANTITY as ITEM_PRICE,
                 0 as IS_SESSION
            from MARKETING.ONEKEY_BUY_PRODUCTS a)
   where ITEM_ID is not null;
      logdata(34,
              't_smsbuy_item_router',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');
        
   --原单位是元修改为分      
     update t_smsbuy_item_router set ITEM_PRICE=ITEM_PRICE*100 where pay_type=2;
   --原单位是积分修改为分
     update t_smsbuy_item_router set ITEM_PRICE=ceil(ITEM_PRICE/67*100) where pay_type=1;
    end;

  begin
  --插入业务路由表
   insert into T_ROUTER_RULE (SP_CODE,TARGET) values ('106583641','q_sms_mo_sms_buy');
   insert into T_ROUTER_RULE (SP_CODE,TARGET) values ('106586181','q_sms_mo_sms_buy');
      logdata(35,
              't_router_rule',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');   
  end;
  
  begin
  --插入T_SHOP_HOMEPAGE_SHOW表数据
insert into T_SHOP_HOMEPAGE_SHOW
  (id,
   title,
   shelf_id,
   good_num,
   status,
   order_index,
   shop_class,
   shop_id,
   shop_user_id,
   update_time)
  select rownum,
         a.title,
         xw_data_sync.xwcatory_id2klid(a.category_id) as shelf_id,
         a.view_count as good_num,
         1 as status,
         substr(a.UUID, (instr(a.UUID, '-') + 1)) as order_index,
         2 as shop_class,
         xw_data_sync.xwstore_id2klid(a.store_id) as shop_id,
         null as shop_user_id,
         null as update_time
    from xwmall.STORE_P_VIEW a, xwmall.hisun_merchant_mapping b
   where a.store_id = b.merchant_id and a.category_id <>' ';
         logdata(36,
              't_shop_homepage_show',
              'insert',
              'sucess insert ' || sql%rowcount || ' rows');   
  end;
  
  commit;
  
  end covertdata;
  --sss
end xw_data_sync;

/