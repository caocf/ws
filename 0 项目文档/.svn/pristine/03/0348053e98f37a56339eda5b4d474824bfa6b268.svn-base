CREATE procedure "LIFE".P_CARD_GETCODE_BYTYPE(v_store_id in t_verify_card_code_common.store_id%type,
                                            v_type_id  in t_verify_card_code_common.code_name_id%type,
              v_id out t_verify_card_code_common.id%type,
                                            v_card_id out t_verify_card_code_common.card_id%type,
                                            v_card_key out t_verify_card_code_common.card_key%type) is
  v_dtime    t_verify_card_code_common.generate_date%type;
begin
  v_dtime := to_char(sysdate, 'yyyymmddhh24miss');

  select id,card_id, card_key
  into v_id,v_card_id, v_card_key from
  ( select id,card_id, card_key from t_verify_card_code_common
    where status = 100
     and store_id = v_store_id
     and code_name_id = v_type_id
     and rownum < 100
    order by dbms_random.value )
  where rownum <= 1;

  update t_verify_card_code_common
     set status = 200, generate_date = v_dtime
   where id = v_id and status= 100;
  if sql%rowcount = 0 then
    v_card_id := '0';
    v_card_key := '0';
  end if;
  commit;

exception
  when NO_DATA_FOUND then
    v_card_id := '0';
    v_card_key := '0';
    v_id := 0;
    return;
  when others then
    v_card_id := '0';
    v_card_key := '0';
    v_id := 0;
    rollback;
end;

/