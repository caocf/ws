prompt PL/SQL Developer import file
prompt Created on 2012年12月28日 by B6M0AB243123
set feedback off
set define off
prompt Disabling triggers for T_APP_INTERFACE_INFO...
alter table T_APP_INTERFACE_INFO disable all triggers;
prompt Deleting T_APP_INTERFACE_INFO...
delete from T_APP_INTERFACE_INFO;
commit;
prompt Loading T_APP_INTERFACE_INFO...
insert into T_APP_INTERFACE_INFO (ID, INTERFACE_NAME, INTERFACE_TYPE, INTERFACE_URL, METHOD)
values (1, '查询用户信息接口', 1, 'people', 'GET');
insert into T_APP_INTERFACE_INFO (ID, INTERFACE_NAME, INTERFACE_TYPE, INTERFACE_URL, METHOD)
values (2, '创建用户新鲜事接口', 0, 'activitystreams', 'POST');
insert into T_APP_INTERFACE_INFO (ID, INTERFACE_NAME, INTERFACE_TYPE, INTERFACE_URL, METHOD)
values (3, '用户上行消息接口', 0, 'interaction', 'GET');
insert into T_APP_INTERFACE_INFO (ID, INTERFACE_NAME, INTERFACE_TYPE, INTERFACE_URL, METHOD)
values (4, '回复下发消息接口', 0, 'replyMsg', 'POST');
insert into T_APP_INTERFACE_INFO (ID, INTERFACE_NAME, INTERFACE_TYPE, INTERFACE_URL, METHOD)
values (5, '用户同步接口', 0, 'followapp', 'GET');
insert into T_APP_INTERFACE_INFO (ID, INTERFACE_NAME, INTERFACE_TYPE, INTERFACE_URL, METHOD)
values (6, '发送邀请接口', 0, 'invite', 'POST');
insert into T_APP_INTERFACE_INFO (ID, INTERFACE_NAME, INTERFACE_TYPE, INTERFACE_URL, METHOD)
values (7, '内容群发接口', 0, 'pushcontent', 'POST');
insert into T_APP_INTERFACE_INFO (ID, INTERFACE_NAME, INTERFACE_TYPE, INTERFACE_URL, METHOD)
values (8, '个性化群发接口', 1, 'pushmsg', 'POST');
insert into T_APP_INTERFACE_INFO (ID, INTERFACE_NAME, INTERFACE_TYPE, INTERFACE_URL, METHOD)
values (9, '主动下发消息接口', 1, 'pushsingle', 'POST');
commit;
prompt 9 records loaded
prompt Enabling triggers for T_APP_INTERFACE_INFO...
alter table T_APP_INTERFACE_INFO enable all triggers;
set feedback on
set define on
prompt Done.
