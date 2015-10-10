CREATE FORCE VIEW "LIFE".v_verify_consume ("ID",order_id,ship_id,pos_id,pos_seq,verify_channel,verify_date,use_times,is_reverse,verify_user,verify_status) AS
select 'C'||to_char(id) as id, order_id, ship_id, pos_id,
'' as pos_seq, verify_channel, verify_date, use_times, is_reverse, verify_user, verify_status from t_verify_consume
union all
select 'F'||to_char(id) as id, order_id, ship_id, pos_id,
pos_seq, 'POS' as verify_channel, verify_date, 1, 0, terminal_id, '0000' from t_verify_consume_notify where pos_id is not null;