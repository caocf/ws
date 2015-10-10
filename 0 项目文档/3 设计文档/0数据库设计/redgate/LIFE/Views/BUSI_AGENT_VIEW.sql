CREATE FORCE VIEW "LIFE".busi_agent_view (busi_code,busi_name,flag,merchid,create_time) AS
select agent_code busi_code,agent_name busi_name,'agent' flag,merchid,create_time from jssystem.t_meta_agent  where status='authed'
union
select busi_code,busi_name,'busi' flag,merchid,create_time from jssystem.t_meta_busiinfo  where status='authed'
order by create_time desc
;