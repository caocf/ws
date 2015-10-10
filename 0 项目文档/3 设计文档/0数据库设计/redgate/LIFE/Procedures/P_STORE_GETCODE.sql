CREATE procedure "LIFE".P_STORE_GETCODE(v_store_id in t_verify_store_code_common.store_id%type,
                                            v_item_id  in t_verify_store_code_common.item_id%type,
                                            v_code_rsa out t_verify_store_code_common.code_rsa%type) is
  v_dtime    t_verify_store_code_common.generate_date%type;
  v_code_md5 t_verify_store_code_common.code_md5%type;
begin
  v_dtime := to_char(sysdate, 'yyyymmddhh24miss');

  select code_md5, code_rsa
  into v_code_md5, v_code_rsa from
  ( select code_md5, code_rsa from t_verify_store_code_common
    where status = 100
     and store_id = v_store_id
     and item_id = v_item_id
     and rownum < 100
    order by dbms_random.value )
  where rownum <= 1;

  update t_verify_store_code_common
     set status = 200, generate_date = v_dtime
   where code_md5 = v_code_md5 and status= 100;
  if sql%rowcount = 0 then
    v_code_rsa := '0';
  end if;
  commit;

exception
  when NO_DATA_FOUND then
    v_code_rsa := '0';
    return;
  when others then
    v_code_rsa := '0';
    rollback;
end P_STORE_GETCODE;

/