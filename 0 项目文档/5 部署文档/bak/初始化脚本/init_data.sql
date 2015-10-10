prompt PL/SQL Developer import file
prompt Created on 2012年12月28日 by jisn
set feedback off
set define off
prompt Disabling triggers for T_APP_CLASSIFICATION...
alter table T_APP_CLASSIFICATION disable all triggers;
prompt Disabling triggers for T_BASE_APP_CONF...
alter table T_BASE_APP_CONF disable all triggers;
prompt Disabling triggers for T_BASE_APP_CONF_TYPE...
alter table T_BASE_APP_CONF_TYPE disable all triggers;
prompt Disabling triggers for T_BASE_APP_INFO...
alter table T_BASE_APP_INFO disable all triggers;
prompt Disabling triggers for T_BASE_APP_REPLY...
alter table T_BASE_APP_REPLY disable all triggers;
prompt Disabling triggers for T_BASE_APP_REPLY_VERSION...
alter table T_BASE_APP_REPLY_VERSION disable all triggers;
prompt Disabling triggers for T_BASE_APP_ROUTER...
alter table T_BASE_APP_ROUTER disable all triggers;
prompt Disabling triggers for T_SYS_MENU...
alter table T_SYS_MENU disable all triggers;
prompt Disabling triggers for T_SYS_SERVICE_INFO...
alter table T_SYS_SERVICE_INFO disable all triggers;
prompt Disabling triggers for T_SYS_USER...
alter table T_SYS_USER disable all triggers;
prompt Deleting T_SYS_USER...
delete from T_SYS_USER;
commit;
prompt Deleting T_SYS_SERVICE_INFO...
delete from T_SYS_SERVICE_INFO;
commit;
prompt Deleting T_SYS_MENU...
delete from T_SYS_MENU;
commit;
prompt Deleting T_BASE_APP_ROUTER...
delete from T_BASE_APP_ROUTER;
commit;
prompt Deleting T_BASE_APP_REPLY_VERSION...
delete from T_BASE_APP_REPLY_VERSION;
commit;
prompt Deleting T_BASE_APP_REPLY...
delete from T_BASE_APP_REPLY;
commit;
prompt Deleting T_BASE_APP_INFO...
delete from T_BASE_APP_INFO;
commit;
prompt Deleting T_BASE_APP_CONF_TYPE...
delete from T_BASE_APP_CONF_TYPE;
commit;
prompt Deleting T_BASE_APP_CONF...
delete from T_BASE_APP_CONF;
commit;
prompt Deleting T_APP_CLASSIFICATION...
delete from T_APP_CLASSIFICATION;
commit;
prompt Loading T_APP_CLASSIFICATION...
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (188893, 0, '音乐', 0, null, 1, '20121207150514');
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (188896, 0, '查询', 0, null, 1, '20121207150544');
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (205455, 0, '资讯', 0, null, 1, '20121224141841');
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (205457, 0, '生活', 0, null, 1, '20121224141909');
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (205459, 0, '图像', 0, null, 1, '20121224141935');
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (205461, 0, '工具', 0, null, 1, '20121224141954');
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (205463, 0, '社交', 0, null, 1, '20121224142004');
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (205456, 0, '阅读', 0, null, 1, '20121224141856');
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (205458, 0, '游戏', 0, null, 1, '20121224141924');
insert into T_APP_CLASSIFICATION (id, pid, class_name, class_status, remark, update_user_id, update_time)
values (205460, 0, '教育', 0, null, 1, '20121224141946');
commit;
prompt 10 records loaded
prompt Loading T_BASE_APP_CONF...
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10096, 151, null, '1001', '对不起，没有搜索到您想要的应用信息，消息客应用热门推荐：[recommend]。请回复“#应用名称”使用对应应用，或回复“TJ”获取热门应用推荐。', '用户应用的WAP网址', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10097, 151, null, '1003', '#${APP.NAME}由【消息客】提供，请先回复短信“D”免费订制消息客服务。', '平台定制确认提短信内容', '${APP.NAME}是应用服务名；');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10098, 151, null, '1005', 'http://112.4.128.220:18009/wap/app/view_all_app.html?type=myapp', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10099, 100, null, '1002', '非常抱歉，您还没有订制消息客，请先回复短信“D”免费订制消息客。这里有海量的应用供您使用。', '未定制用户回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10100, 100, null, '1003', '抱歉，没有搜索到您上传的内容，以下应用您可能会感兴趣#[app_name]，[app_desc]，回复K开通此应用。', '应用推荐回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10101, 100, null, '1004', '#[app_name][app_desc]，当前开通用户[app_user]，不过您还未开通此应用，无法继续使用。请回复短信“K”开通应用。', '未开通应用回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10102, 100, null, '1005', '抱歉，您发送的指令我们暂时无法处理，如需帮助信息，请回复短信“？”获取消息客的帮助彩信。', '通用错误回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10036, 163, null, '1002', '206534', '自己空间提示语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10037, 163, null, '1003', '【消息客】指南：' || chr(13) || '' || chr(10) || '1.消息客是集社交、应用、服务、游戏等全方位的互动沟通短彩平台。' || chr(13) || '' || chr(10) || '2.向您提供提供信息查询、服务搜索、互动社交、应用下载等一站式服务体验。' || chr(13) || '' || chr(10) || '3.您可以直接回复@+好友昵称访问您的好友空间，随时与您的好友保持联络。' || chr(13) || '' || chr(10) || '4.你也可以直接回复#+应用名称直接搜索并访问最新最酷的应用。' || chr(13) || '' || chr(10) || '5.回复？+关键字可进行搜索查询。', '好友提示语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10038, 163, null, '1004', '【消息客】指南：' || chr(13) || '' || chr(10) || '1.消息客是集社交、应用、服务、游戏等全方位的互动沟通短彩平台。' || chr(13) || '' || chr(10) || '2.向您提供提供信息查询、服务搜索、互动社交、应用下载等一站式服务体验。' || chr(13) || '' || chr(10) || '3.您可以直接回复@+好友昵称访问您的好友空间，随时与您的好友保持联络。' || chr(13) || '' || chr(10) || '4.你也可以直接回复#+应用名称直接搜索并访问最新最酷的应用。' || chr(13) || '' || chr(10) || '5.回复？+关键字可进行搜索查询。', '陌生人提示语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10039, 163, null, '1005', '>>想查看更多的信息请点击http://112.4.128.220:18009/wap', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10046, 162, null, '1001', '请您将随后收到的邀请信通过短信直接发给您想邀请的好友。好友按照邀请信操作即可完成。', '地区限制回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10047, 162, null, '1002', '邀请信-我正在玩消息客，邀请你一起来玩。请将此短信转发至10658777804，就可以免费开通【邀请码${INVITE_CODE}】', '线下邀请短信模板', '${INVITE_CODE}是邀请码');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10048, 162, null, '1003', '用户@${USER.NICKNAME}想邀请您成为消息客好友，回复短信''J''同意。', '线上邀请短信模板', '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10049, 162, null, '1005', '好友邀请已发出，对方同意后，Ta将成为您的好友。', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10050, 162, null, '1006', '抱歉,您邀请的好友不在消息客的服务地区，无法添加。您可以回复@好友名去查找好友,或通过以下两种方式邀请好友加入消息客: ' || chr(13) || '' || chr(10) || '1.直接回复好友手机号码； ' || chr(13) || '' || chr(10) || '2.回复"YQ"获得邀请短信,转发到好友手机。', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10063, 108, null, '1001', '新鲜事频率接收成功，您已关闭接收。', '关闭新鲜事回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10064, 108, null, '1002', '新鲜事频率接收成功，您将每天接收。', '每天接收回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10065, 108, null, '1003', '新鲜事频率接收成功，您将每周一接收。', '每周接收回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10066, 108, null, '1004', '新鲜事频率接收成功，您将每月一号接收。', '每月接收回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10067, 108, null, '1005', '您尚未订制消息客，暂时无法设置。回复短信“D”免费订制消息客服务。', '未定制用户回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10090, 102, null, '1001', '感谢你的支持，目前你在消息客中还有[friend_count]名好友，正在使用[app_count]款应用，你真的要弃TA们而去吗？退订后，系统将清除你所有的信息记录并且无法恢复！如果您确认退订，请回复数字序号告知退订原因并同时退订消息客：1、操作复杂；2、对内容不感兴趣；3、其他原因。', '提示确认退订回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10091, 102, null, '1002', '抱歉，您还没有订制消息客，请回复短信“D”免费订制消息客。', '未定制用户回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10092, 102, null, '1003', '您已成功退订消息客，感谢您一路以来的支持。', '退订成功回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10093, 102, null, '1004', '操作超时，请重新回复序号确认退订：' || chr(13) || '' || chr(10) || '1、操作复杂；' || chr(13) || '' || chr(10) || '2、对内容不感兴趣；' || chr(13) || '' || chr(10) || '3、其他原因。', '超时回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10094, 102, null, '1005', '回复的选项不正确，请重新回复序号确认退订：' || chr(13) || '' || chr(10) || '1、操作复杂；' || chr(13) || '' || chr(10) || '2、对内容不感兴趣；' || chr(13) || '' || chr(10) || '3、其他原因。', '错误选项回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10103, 100, null, '1001', '1065888820', '应用特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10149, 101, null, '1002', '10658888', '欢迎短信特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10147, 101, null, '1004', '10658888', '欢迎彩信特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10148, 101, null, '1005', '5', '欢迎彩信下发延迟(秒)', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10190, 104, null, '1008', ',.;''<>!@#$%^&*()_+~`?/\，。《》？—『』[]{}:：、|～！￥%…（）-=', '限制的字符', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10040, 101, null, '1001', '欢迎您成为消息客${USER.ID}，我们为您准备了上千种不同类型的新鲜消息和好玩应用，您可以根据您的爱好自行挑选，免受打扰之苦。稍后我们会将《使用指南》通过彩信发给您。回复@+姓名搜索好友，#+应用搜索应用，或点击http://112.4.128.220:18009/wap', '加自己为好友时的提示语', '${USER.ID}是用户ID');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10041, 101, null, '1003', '205598', '欢迎彩信ID', '179933');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10042, 101, null, '1007', '用户@${USER.NICKNAME}同意加您为好友。直接回复内容即可开始与Ta聊天。温馨提示：您可将发信人号码保存至您的通讯簿，方便您以后直接与Ta聊天。', '给邀请发送方的短信回复语内容', '${USER.NICKNAME}是被邀请好友昵称');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10043, 101, null, '1008', '您和@${USER.NICKNAME}已成为消息客好友。直接回复内容即可开始与Ta聊天。温馨提示：您可将发信人号码保存至您的通讯簿，方便您以后直接与Ta聊天。', '给邀请接收方的短信回复语内容', '${USER.NICKNAME}是邀请好友者昵称');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10044, 101, null, '1010', '[消息客应用推荐]：消息客上有很多应用哦，这就推荐你#${APP.NAME}，${APP.DESC}。回复短信"K"立即开通#${APP.NAME}。', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10045, 101, null, '1011', '抱歉，您无法添加自己为好友，请将邀请信直接通过短信发送给您的好友。您的好友按照邀请信操作即可成为您的好友。', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10076, 105, null, '1003', '您的好友@[name]([phone])邀请您加入消息客-#[app]，回复K免费开通消息客-#[app]。', '您已接受@[name]([phone])的邀请,开通了[app],更多应用请点击http://112.4.128.220:18009/wap', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10077, 105, null, '1004', '请将随后的收到短信转发给好友。', '线下邀请应用提示语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10078, 105, null, '1005', '我正在玩消息客-#[app],将此短信转发至[spcode]即可开通，邀请码:[code]', '线下邀请贴', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10079, 105, null, '1006', '您的好友@[name]已接受邀请，#[app]', '邀请已被接受回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10080, 105, null, '1007', '您已接受@[name]([phone])的邀请,开通了消息客-#[app]。', '接受邀请回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10081, 105, null, '1008', '您的好友@[name]([phone])已开通了#[app],请尝试邀请其他好友。', '已经开通应用时的回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10082, 105, null, '1009', '抱歉,您邀请的好友不在消息客的服务地区，无法开通此应用。', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10083, 105, null, '1010', '您已接受@[name]([phone])的邀请,开通了消息客-#[app],更多应用请点击http://112.4.128.220:18009/wap', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10001, 151, null, '1007', '很抱歉，没有找到消息客应用#${QUERY}，您要找的是否是：' || chr(10) || '${APPS}' || chr(10) || '>>点击http://192.168.5.96:8009/wap 查看你开通的所有应用' || chr(10) || '>>回复数字序号使用对应应用。' || chr(10) || '回复“#应用名称”继续查找应用。', '搜索应用清单提示语', '${APPS}是推荐内容');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10002, 151, null, '1008', '您已经开通的应用有：' || chr(10) || '${APPS}' || chr(10) || '>>点击http://112.4.128.220:18009/wap，查看所有开通的应用' || chr(10) || '>>回复数字序号使用对应应用；' || chr(10) || '回复“TJ”获取热门应用推荐。', '已开通应用清单提示语', '${APPS}是推荐内容');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10068, 107, null, '1001', '您尚未订制消息客，回复短信“D”免费订制消息客。', '非定制用户回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10069, 107, null, '1003', '205628', '关键字搜索回复语的彩信模版编号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10070, 107, null, '1004', '※温馨提示※ 如您对搜索结果不满意，可以登陆http://112.4.128.220:18009/wap 进行搜索。', '关键字搜索回复语后缀', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10071, 107, null, '1005', '未找到关于“[keyword]”的相关信息。寻找好友发送@+好友姓名；搜索应用发送#+应用名称。', '关键字搜索无结果时的回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10072, 107, null, '1007', '205628', '用户搜索回复语的彩信模版编号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10073, 107, null, '1008', '※温馨提示※ 更多搜索结果请点击http://112.4.128.220:18009/wap', '用户搜索回复语后缀', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10074, 107, null, '1009', '未找到关键字“[keyword]”的搜索结果', '用户搜索无结果时的回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10075, 107, null, '1010', '【消息客】帮助：' || chr(13) || '' || chr(10) || '1.消息客是集社交、应用、服务、游戏等全方位的互动沟通短彩平台。' || chr(13) || '' || chr(10) || '2.向您提供提供信息查询、服务搜索、互动社交、应用下载等一站式服务体验。' || chr(13) || '' || chr(10) || '3.您可以直接回复@+好友昵称访问您的好友空间，随时与您的好友保持联络。' || chr(13) || '' || chr(10) || '4.你也可以直接回复#+应用名称直接搜索并访问最新最酷的应用。' || chr(13) || '' || chr(10) || '5.回复？+关键字可进行搜索查询。' || chr(13) || '' || chr(10) || '6.更多帮助信息请访问：http://112.4.128.220:18009/wap', '第二帧的内容', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10084, 104, null, '1002', '您尚未订制消息客，回复短信“D”免费订制消息客服务。', '推荐好友回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10085, 104, null, '1003', '@[name]([spcode])还不是您的好友，回复J加为好友。', '邀请回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10086, 104, null, '1004', '已恢复接收来自@[name]([spcode])的短信，回复P继续屏蔽。', '允许接收回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10087, 104, null, '1005', '已屏蔽来自@[name]([spcode])的短信，回复S恢复接收。', '拒绝接收回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10088, 104, null, '1006', '【回复P屏蔽该好友私信】', '屏蔽指令提示', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10089, 104, null, '1007', '您发送私信的好友不存在，Ta们都是非常活跃的消息客哦，回复序号，可添加Ta们为好友一起私聊。[recommend]', '推荐好友回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10005, 151, null, '1002', '5', '返回查询结果数量最大数量', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10006, 151, null, '1004', '10658888', '平台定制确认提醒-特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10023, 101, null, '1009', '1065888899', '聊天特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10007, 104, null, '1001', '1065888899', '特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10008, 105, null, '1001', '1065888805', '线上邀请特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10009, 105, null, '1002', '1065888806', '线下邀请特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10010, 106, null, '1001', '1065888802', '特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10003, 107, null, '1002', 'http://192.168.5.96:9001/search/keyword?userId=[user_id]&keyword=[keyword]&count=5', '关键字搜索服务地址', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10004, 107, null, '1006', 'http://192.168.5.96:9001/search/user?userId=[user_id]&keyword=[keyword]&searchUserId=[search_id]', '用户搜索服务地址', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10011, 163, null, '1001', '1065888899', '用户特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10012, 102, null, '1000', '1065888810', '提示确认退订的特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10013, 102, null, '1006', 'http://192.168.5.95:8184/api/open/msg/app/@userChange', '开放平台URl', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10014, 102, null, '1007', '180000', '超时时间', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10015, 103, null, '1001', '10658888', '特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10016, 103, null, '1008', ',.;''<>!@#$%^&*()_+~`?/\，。《》？—『』[]{}:：、|～！￥%…（）-=', '限制的字符', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10030, 103, null, '1002', '您尚未订制消息客，无法设置姓名，请回复短信“D”免费订制消息客服务。', '未定制用户回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10031, 103, null, '1003', '您设置的姓名为空，设置未成功，请回复短信*nc+姓名（如*nc消息客）设置您的姓名。 ', '空内容回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10032, 103, null, '1004', '您发送的内容包含敏感字符，请重新回复短信*nc+姓名（如*nc消息客）设置您的姓名。', '敏感词回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10033, 103, null, '1005', '5', '超时回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10034, 103, null, '1006', '您的昵称过长，回复短信*nc+姓名（如*nc消息客）设置您的姓名。', '昵称过长回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10035, 103, null, '1007', '姓名“@[nickname]”设置成功。', '设置成功回复语', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10017, 101, null, '1006', '你已经接收了@${INVITE.NAME}的邀请，开通了消息客。欢迎您成为快乐的消息客。我们为您准备了上千种不同类型的新鲜消息和好玩应用，您可以根据您的爱好自行挑选。稍后将《使用指南》通过彩信发给您。更多应用请点击http://112.4.128.220:18009/wap', '欢迎短信-好友推荐提示', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10018, 152, null, '1003', 'http://192.168.5.96:19081/app', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10019, 162, null, '1004', '1065888899', '线上邀请短信特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10020, 161, null, '1002', '10658888', '平台定制确认提醒-特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10021, 161, null, '1003', '6', '返回查询结果数量最大数量', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10022, 161, null, '1009', '1065888899', '聊天特服号', null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10025, 152, null, '1001', '【#${APP.NAME}】${APP.DESC}；目前用户数：${APP.USER_COUNT}。回复短信“K”开通。', '应用定制成功提示语', '${APP.NAME}是应用服务名；${APP.DESC}是应用服务描述；${APP.USER_COUNT}是目前用户数');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10026, 152, null, '1002', '【#${APP.NAME}】${APP.HELP}回复短信“G”将关闭。', '应用退订确认短信内容', '${APP.NAME}是应用服务名；${APP.ORDER_DATE}应用开通时间');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10027, 152, null, '1004', '#${APP.NAME}关闭成功。', '应用退订后提示语', '${APP.NAME}是应用服务名；');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10028, 152, null, '1005', '非常抱歉，#${APP.NAME}暂时处于非商用状态，无法开通。您可以尝试搜索其他应用。回复短信#+应用名称。', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10029, 152, null, '1006', '【#${APP.NAME}】开通成功 ${APP.HELP} 回复G关闭#${APP.NAME} 。', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10024, 106, null, '1002', '206775', '彩信模版', '应用彩信模板ID');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (10095, 151, null, '1006', '【消息客】热门应用推荐：' || chr(10) || '${APPS}' || chr(10) || '>>回复序号直接访问应用；' || chr(10) || '>>或回复短信#+应用名称搜索应用；' || chr(10) || '更多应用请点击http://112.4.128.220:18009/wap', '推荐应用提示语', '${APPS}是推荐内容');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209107, 161, null, '1004', '抱歉,没有【@${QUERY}】这个用户,您可以回复@好友名去查找好友,或通过以下两种方式邀请好友加入消息客: 1.直接回复好友手机号码； 2.回复''YQ''获得邀请短信,转发到好友手机；3.登录：http://112.4.128.220:18009/wap 搜索好友。', '无查询结果提示语', '${QUERY}是用户名称');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209108, 161, null, '1005', '您可以对好友@${USER.NICKNAME}进行如下操作：回复数字1直接与Ta聊天，回复C删除Ta，回复KJ查看Ta的空间。回复M继续搜索其他同名用户。', '好友操作菜单', '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209109, 161, null, '1006', '@${USER.NICKNAME}还不是您的好友。请回复短息“J”邀请@${USER.NICKNAME}成为您的好友。回复“KJ”查看Ta的空间。', '陌生人操作菜单', '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209110, 161, null, '1001', '抱歉，你尚未订制消息客，无法寻找您的好友，请回复“D”免费订制消息客吧', '陌生人选择菜单(有结果）', '1');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209111, 161, null, '1007', '您已成功删除好友@${USER.NICKNAME}，如需重新添加，请重新回复短信@${USER.NICKNAME}邀请Ta成为您的好友。', '删除好友回复语', '${USER.NICKNAME}是好友昵称');
commit;
prompt 100 records committed...
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209112, 161, null, '1010', '您输入的为本机号码或姓名，回复''KJ''查看自己的个人空间，或点击http://192.168.5.96:8009/wap 进行个人设置。', '自生操作菜单', '1');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209113, 161, null, '1011', '你现在可以与@${USER.NICKNAME}聊天啦，直接回复内容与Ta聊天。', '聊天提示短信', '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209114, 161, null, '1012', 'http://112.4.128.220:18009/wap', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209115, 161, null, '1013', '请回复数字序号选择您已添加的好友：${LIST}更多好友请登录http://112.4.128.220:18009/wap。 回复M继续搜索其他用户。', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209116, 161, null, '1014', '您目前没有添加任何好友，回复数字序号可添加Ta们为好友：${LIST}发现更多好友请登录http://112.4.128.220:18009/wap', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209117, 161, null, '1015', '请回复数字序号选择您已添加的好友：${LIST}更多好友请登录http://112.4.128.220:18009/wap 回复M搜索陌生人用户。', null, null);
insert into T_BASE_APP_CONF (id, base_app_id, version_id, param_code, param_value, remark, param_value_remark)
values (209118, 161, null, '1016', '您可以回复数字序号添加Ta们成为好友：${LIST}更多请登录http://112.4.128.220:18009/wap', null, null);
commit;
prompt 107 records loaded
prompt Loading T_BASE_APP_CONF_TYPE...
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (122, 100, '1001', '应用特服号', 0, 1, '应用特服号');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (13, 100, '1002', '未定制用户回复语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (14, 100, '1003', '应用推荐回复语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (15, 100, '1004', '未开通应用回复语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (16, 100, '1005', '通用错误回复语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (1, 101, '1001', '欢迎短信内容', 1, 0, '${USER.ID}是用户ID');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (2, 101, '1002', '欢迎短信特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (3, 101, '1003', '欢迎彩信ID', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (4, 101, '1004', '欢迎彩信特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (5, 101, '1005', '欢迎彩信下发延迟(秒)', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (6, 101, '1006', '欢迎短信-好友推荐提示', 1, 1, '${INVITE.NAME}是邀请者名字');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (7, 101, '1007', '给邀请发送方的短信回复语内容', 1, 0, '${USER.NICKNAME}是被邀请好友昵称');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (8, 101, '1008', '给邀请接收方的短信回复语内容', 1, 0, '${USER.NICKNAME}是邀请好友者昵称');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (9, 101, '1009', '聊天特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (10, 102, '1000', '提示确认退订的特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (11, 102, '1001', '提示确认退订回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (12, 102, '1002', '未定制用户回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (13, 102, '1003', '退订成功回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (14, 102, '1004', '超时回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (15, 102, '1005', '错误选项回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (16, 102, '1006', '开放平台URl', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (17, 102, '1007', '超时时间', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (18, 103, '1001', '特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (19, 103, '1002', '未定制用户回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (20, 103, '1003', '空内容回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (21, 103, '1004', '敏感词回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (22, 103, '1005', '超时回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (23, 103, '1006', '昵称过长回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (24, 103, '1007', '设置成功回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (25, 103, '1008', '限制的字符', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (26, 104, '1001', '特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (27, 104, '1002', '未定制用户回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (28, 104, '1003', '邀请回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (29, 104, '1004', '允许接收回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (30, 104, '1005', '拒绝接收回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (31, 104, '1006', '屏蔽指令提示', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (32, 105, '1001', '线上邀请特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (33, 105, '1002', '线下邀请特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (34, 105, '1003', '在线邀请应用提示语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (35, 105, '1004', '线下邀请应用提示语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (36, 105, '1005', '线下邀请贴', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (37, 105, '1006', '邀请已被接受回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (38, 105, '1007', '接受邀请回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (39, 105, '1008', '已经开通应用时的回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (40, 106, '1001', '特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (41, 106, '1002', '彩信模版', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (42, 108, '1001', '关闭新鲜事回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (43, 108, '1002', '每天接收回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (44, 108, '1003', '每周接收回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (45, 108, '1004', '每月接收回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (46, 108, '1005', '未定制用户回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (47, 151, '1001', '查询结果数量=0的提示语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (48, 151, '1002', '返回查询结果数量最大数量', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (49, 151, '1003', '平台定制确认提短信内容', 1, 0, '${APP.NAME}是应用服务名；');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (50, 151, '1004', '平台定制确认提醒-特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (51, 152, '1001', '应用定制确认短信内容', 1, 0, '${APP.NAME}是应用服务名；${APP.DESC}是应用服务描述；${APP.USER_COUNT}是目前用户数');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (52, 152, '1002', '应用退订确认短信内容', 1, 0, '${APP.NAME}是应用服务名；${APP.ORDER_DATE}应用开通时间');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (53, 152, '1003', '开放平台定制关系同步接口地址', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (54, 152, '1004', '应用退订后提示语', 1, 0, '${APP.NAME}是应用服务名；');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (55, 161, '1001', '平台定制确认提短信内容', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (56, 161, '1002', '平台定制确认提醒-特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (57, 161, '1003', '返回查询结果数量最大数量', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (58, 161, '1004', '无查询结果提示语', 1, 0, '${QUERY}是用户名称');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (59, 161, '1005', '好友操作菜单', 1, 0, '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (60, 161, '1006', '陌生人操作菜单', 1, 0, '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (61, 161, '1007', '删除好友回复语', 1, 0, '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (62, 161, '1009', '聊天特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (63, 161, '1010', '自生操作菜单', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (64, 161, '1011', '聊天提示短信', 1, 0, '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (65, 162, '1001', '线下邀请短信提示', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (66, 162, '1002', '线下邀请短信模板', 1, 0, '${INVITE_CODE}是邀请码');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (67, 162, '1003', '线上邀请短信模板', 1, 0, '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (68, 162, '1004', '线上邀请短信特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (69, 163, '1001', '用户特服号', 0, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (70, 163, '1002', '用户空间彩信模板ID', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (71, 163, '1003', '好友提示语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (72, 163, '1004', '陌生人提示语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (73, 107, '1001', '非定制用户回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (74, 107, '1002', '关键字搜索服务地址', 1, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (75, 107, '1003', '关键字搜索回复语的彩信模版编号', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (76, 107, '1004', '关键字搜索回复语后缀', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (77, 107, '1005', '关键字搜索无结果时的回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (78, 107, '1006', '用户搜索服务地址', 1, 1, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (79, 107, '1007', '用户搜索回复语的彩信模版编号', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (80, 107, '1008', '用户搜索回复语后缀', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (81, 107, '1009', '用户搜索无结果时的回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (82, 107, '1010', '第二帧的内容', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (73, 163, '1005', '自己空间提示语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (10, 101, '1010', '消息客应用推荐提示', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (11, 101, '1011', '加自己为好友时的提示语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (32, 104, '1007', '推荐好友回复语', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (40, 105, '1009', '抱歉,您邀请的好友不在消息客的服务地区，无法开通此应用。', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (41, 105, '1010', '您已接受@[name]([phone])的邀请,开通了[app],更多应用请点击http://112.4.128.220:18009/wap', 0, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (51, 151, '1005', '用户应用的WAP网址', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (55, 152, '1005', '应用定制失败提示语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (56, 152, '1006', '应用定制成功提示语', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (65, 161, '1012', 'WAP网站地址', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (66, 161, '1013', '好友选择菜单(有好友）', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (67, 161, '1014', '好友选择菜单(无好友）', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (68, 161, '1015', '好友选择菜单(查询时）', 1, 0, null);
commit;
prompt 100 records committed...
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (69, 161, '1016', '陌生人选择菜单(有结果）', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (69, 162, '1005', '线上邀请短信提示', 1, 0, null);
insert into T_BASE_APP_CONF_TYPE (id, base_app_id, param_code, param_desc, param_format, param_type, remark)
values (70, 162, '1006', '地区限制回复语', 1, 0, null);
commit;
prompt 103 records loaded
prompt Loading T_BASE_APP_INFO...
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (107, '?搜索', 1, '20121016121509');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (101, '用户注册', 1, '20121016121509');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (104, '用户聊天', 1, '20121016132510');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (105, '邀请好友加应用', 1, '20121016132714');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (151, '#应用搜索', 1, '20121016133838');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (152, '应用定制/退订', 1, '20121016134424');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (103, '修改昵称', 1, '20121016132148');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (106, '应用帮助', 1, '20121016133028');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (108, '新鲜事设置', 1, '20121016133235');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (161, '@好友搜索', 1, '20121016134752');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (162, '好友添加/删除', 1, '20121016134913');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (163, '好友空间KJ', 1, '20121016135029');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (100, '通用错误处理', 1, '20121016121509');
insert into T_BASE_APP_INFO (id, base_app_name, update_user_id, update_time)
values (102, '用户退订', 1, '20121016131845');
commit;
prompt 14 records loaded
prompt Loading T_BASE_APP_REPLY...
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208276, 151, 178993, '1001', '对不起，没有搜索到您想要的应用信息，消息客应用热门推荐：[recommend]。请回复“#应用名称”使用对应应用，或回复“TJ”获取热门应用推荐。', '用户应用的WAP网址', 1, '20121227085558', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208277, 151, 178993, '1003', '#${APP.NAME}由【消息客】提供，请先回复短信“D”免费订制消息客服务。', '平台定制确认提短信内容', 1, '20121227085558', '${APP.NAME}是应用服务名；');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208278, 151, 178993, '1005', 'http://112.4.128.220:18009/wap/app/view_all_app.html?type=myapp', null, 1, '20121227085558', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207530, 163, 179052, '1002', '206534', '自己空间提示语', 1, '20121226141611', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207531, 163, 179052, '1003', '【消息客】指南：' || chr(13) || '' || chr(10) || '1.消息客是集社交、应用、服务、游戏等全方位的互动沟通短彩平台。' || chr(13) || '' || chr(10) || '2.向您提供提供信息查询、服务搜索、互动社交、应用下载等一站式服务体验。' || chr(13) || '' || chr(10) || '3.您可以直接回复@+好友昵称访问您的好友空间，随时与您的好友保持联络。' || chr(13) || '' || chr(10) || '4.你也可以直接回复#+应用名称直接搜索并访问最新最酷的应用。' || chr(13) || '' || chr(10) || '5.回复？+关键字可进行搜索查询。', '好友提示语', 1, '20121226141611', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207532, 163, 179052, '1004', '【消息客】指南：' || chr(13) || '' || chr(10) || '1.消息客是集社交、应用、服务、游戏等全方位的互动沟通短彩平台。' || chr(13) || '' || chr(10) || '2.向您提供提供信息查询、服务搜索、互动社交、应用下载等一站式服务体验。' || chr(13) || '' || chr(10) || '3.您可以直接回复@+好友昵称访问您的好友空间，随时与您的好友保持联络。' || chr(13) || '' || chr(10) || '4.你也可以直接回复#+应用名称直接搜索并访问最新最酷的应用。' || chr(13) || '' || chr(10) || '5.回复？+关键字可进行搜索查询。', '陌生人提示语', 1, '20121226141611', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207533, 163, 179052, '1005', '>>想查看更多的信息请点击http://112.4.128.220:18009/wap', null, 1, '20121226141611', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208301, 100, 208300, '1002', '非常抱歉，您还没有订制消息客，请先回复短信“D”免费订制消息客。这里有海量的应用供您使用。', '未定制用户回复语', 1, '20121227093951', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208302, 100, 208300, '1003', '抱歉，没有搜索到您上传的内容，以下应用您可能会感兴趣#[app_name]，[app_desc]，回复K开通此应用。', '应用推荐回复语', 1, '20121227093951', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208303, 100, 208300, '1004', '#[app_name][app_desc]，当前开通用户[app_user]，不过您还未开通此应用，无法继续使用。请回复短信“K”开通应用。', '未开通应用回复语', 1, '20121227093951', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208304, 100, 208300, '1005', '抱歉，您发送的指令我们暂时无法处理，如需帮助信息，请回复短信“？”获取消息客的帮助彩信。', '通用错误回复语', 1, '20121227093951', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207939, 162, 179041, '1001', '请您将随后收到的邀请信通过短信直接发给您想邀请的好友。好友按照邀请信操作即可完成。', '地区限制回复语', 1, '20121226174225', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207940, 162, 179041, '1002', '邀请信-我正在玩消息客，邀请你一起来玩。请将此短信转发至10658777804，就可以免费开通【邀请码${INVITE_CODE}】', '线下邀请短信模板', 1, '20121226174225', '${INVITE_CODE}是邀请码');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207941, 162, 179041, '1003', '用户@${USER.NICKNAME}想邀请您成为消息客好友，回复短信''J''同意。', '线上邀请短信模板', 1, '20121226174225', '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207942, 162, 179041, '1005', '好友邀请已发出，对方同意后，Ta将成为您的好友。', null, 1, '20121226174225', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207943, 162, 179041, '1006', '抱歉,您邀请的好友不在消息客的服务地区，无法添加。您可以回复@好友名去查找好友,或通过以下两种方式邀请好友加入消息客: ' || chr(13) || '' || chr(10) || '1.直接回复好友手机号码； ' || chr(13) || '' || chr(10) || '2.回复"YQ"获得邀请短信,转发到好友手机。', null, 1, '20121226174225', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207985, 108, 178980, '1001', '新鲜事频率接收成功，您已关闭接收。', '关闭新鲜事回复语', 1, '20121226174553', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207986, 108, 178980, '1002', '新鲜事频率接收成功，您将每天接收。', '每天接收回复语', 1, '20121226174553', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207987, 108, 178980, '1003', '新鲜事频率接收成功，您将每周一接收。', '每周接收回复语', 1, '20121226174553', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207988, 108, 178980, '1004', '新鲜事频率接收成功，您将每月一号接收。', '每月接收回复语', 1, '20121226174553', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207989, 108, 178980, '1005', '您尚未订制消息客，暂时无法设置。回复短信“D”免费订制消息客服务。', '未定制用户回复语', 1, '20121226174553', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208069, 102, 178900, '1001', '感谢你的支持，目前你在消息客中还有[friend_count]名好友，正在使用[app_count]款应用，你真的要弃TA们而去吗？退订后，系统将清除你所有的信息记录并且无法恢复！如果您确认退订，请回复数字序号告知退订原因并同时退订消息客：1、操作复杂；2、对内容不感兴趣；3、其他原因。', '提示确认退订回复语', 1, '20121226175127', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208070, 102, 178900, '1002', '抱歉，您还没有订制消息客，请回复短信“D”免费订制消息客。', '未定制用户回复语', 1, '20121226175127', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208071, 102, 178900, '1003', '您已成功退订消息客，感谢您一路以来的支持。', '退订成功回复语', 1, '20121226175127', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208072, 102, 178900, '1004', '操作超时，请重新回复序号确认退订：' || chr(13) || '' || chr(10) || '1、操作复杂；' || chr(13) || '' || chr(10) || '2、对内容不感兴趣；' || chr(13) || '' || chr(10) || '3、其他原因。', '超时回复语', 1, '20121226175127', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208073, 102, 178900, '1005', '回复的选项不正确，请重新回复序号确认退订：' || chr(13) || '' || chr(10) || '1、操作复杂；' || chr(13) || '' || chr(10) || '2、对内容不感兴趣；' || chr(13) || '' || chr(10) || '3、其他原因。', '错误选项回复语', 1, '20121226175127', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (1, 151, 178993, '1006', '【消息客】热门应用推荐： ${APPS} >>回复序号直接访问应用； >>或回复短信#+应用名称搜索应用；更多应用请点击http://112.4.128.220:18009/wap', '推荐应用提示语', 1, '20121227085558', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (2, 151, 178993, '1007', '很抱歉，没有找到消息客应用#${QUERY}，您要找的是否是： ${APPS} >>点击http://192.168.5.96:8009/wap 查看你开通的所有应用 >>回复数字序号使用对应应用。回复“#应用名称”继续查找应用。', '搜索应用清单提示语', 1, '20121227085558', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (3, 151, 178993, '1008', '您已经开通的应用有： ${APPS} >>点击http://112.4.128.220:18009/wap，查看所有开通的应用 >>回复数字序号使用对应应用；回复“TJ”获取热门应用推荐。', '已开通应用清单提示语', 1, '20121227085558', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207914, 101, 189616, '1006', '你已经接收了@${INVITE.NAME}的邀请，开通了消息客。欢迎您成为快乐的消息客。我们为您准备了上千种不同类型的新鲜消息和好玩应用，您可以根据您的爱好自行挑选。稍后将《使用指南》通过彩信发给您。更多应用请点击http://112.4.128.220:18009/wap', '欢迎短信-好友推荐提示', null, null, null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207908, 101, 189616, '1001', '欢迎您成为消息客${USER.ID}，我们为您准备了上千种不同类型的新鲜消息和好玩应用，您可以根据您的爱好自行挑选，免受打扰之苦。稍后我们会将《使用指南》通过彩信发给您。回复@+姓名搜索好友，#+应用搜索应用，或点击http://112.4.128.220:18009/wap', '加自己为好友时的提示语', 1, '20121226173859', '${USER.ID}是用户ID');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207909, 101, 189616, '1003', '205598', '欢迎彩信ID', 1, '20121226173859', '179933');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207910, 101, 189616, '1007', '用户@${USER.NICKNAME}同意加您为好友。直接回复内容即可开始与Ta聊天。温馨提示：您可将发信人号码保存至您的通讯簿，方便您以后直接与Ta聊天。', '给邀请发送方的短信回复语内容', 1, '20121226173859', '${USER.NICKNAME}是被邀请好友昵称');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207911, 101, 189616, '1008', '您和@${USER.NICKNAME}已成为消息客好友。直接回复内容即可开始与Ta聊天。温馨提示：您可将发信人号码保存至您的通讯簿，方便您以后直接与Ta聊天。', '给邀请接收方的短信回复语内容', 1, '20121226173859', '${USER.NICKNAME}是邀请好友者昵称');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207912, 101, 189616, '1010', '[消息客应用推荐]：消息客上有很多应用哦，这就推荐你#${APP.NAME}，${APP.DESC}。回复短信"K"立即开通#${APP.NAME}。', '消息客应用推荐提示', 1, '20121226173859', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207913, 101, 189616, '1011', '抱歉，您无法添加自己为好友，请将邀请信直接通过短信发送给您的好友。您的好友按照邀请信操作即可成为您的好友。', '加自己为好友时的提示语', 1, '20121226173859', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208024, 105, 178955, '1003', '您的好友@[name]([phone])邀请您加入消息客-#[app]，回复K免费开通消息客-#[app]。', '您已接受@[name]([phone])的邀请,开通了[app],更多应用请点击http://112.4.128.220:18009/wap', 1, '20121226174840', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208025, 105, 178955, '1004', '请将随后的收到短信转发给好友。', '线下邀请应用提示语', 1, '20121226174840', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208026, 105, 178955, '1005', '我正在玩消息客-#[app],将此短信转发至[spcode]即可开通，邀请码:[code]', '线下邀请贴', 1, '20121226174840', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208027, 105, 178955, '1006', '您的好友@[name]已接受邀请，#[app]', '邀请已被接受回复语', 1, '20121226174840', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208028, 105, 178955, '1007', '您已接受@[name]([phone])的邀请,开通了消息客-#[app]。', '接受邀请回复语', 1, '20121226174840', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208029, 105, 178955, '1008', '您的好友@[name]([phone])已开通了#[app],请尝试邀请其他好友。', '已经开通应用时的回复语', 1, '20121226174840', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208030, 105, 178955, '1009', '抱歉,您邀请的好友不在消息客的服务地区，无法开通此应用。', '被邀请用户不在服务区的提示语', 1, '20121226174840', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208031, 105, 178955, '1010', '您已接受@[name]([phone])的邀请,开通了消息客-#[app],更多应用请点击http://112.4.128.220:18009/wap', '已定制用户接受邀请回复语', 1, '20121226174840', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208003, 107, 204567, '1001', '您尚未订制消息客，回复短信“D”免费订制消息客。', '非定制用户回复语', 1, '20121226174650', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208004, 107, 204567, '1003', '205628', '关键字搜索回复语的彩信模版编号', 1, '20121226174650', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208005, 107, 204567, '1004', '※温馨提示※ 如您对搜索结果不满意，可以登陆http://112.4.128.220:18009/wap 进行搜索。', '关键字搜索回复语后缀', 1, '20121226174650', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208006, 107, 204567, '1005', '未找到关于“[keyword]”的相关信息。寻找好友发送@+好友姓名；搜索应用发送#+应用名称。', '关键字搜索无结果时的回复语', 1, '20121226174650', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208007, 107, 204567, '1007', '205628', '用户搜索回复语的彩信模版编号', 1, '20121226174650', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208008, 107, 204567, '1008', '※温馨提示※ 更多搜索结果请点击http://112.4.128.220:18009/wap', '用户搜索回复语后缀', 1, '20121226174650', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208009, 107, 204567, '1009', '未找到关键字“[keyword]”的搜索结果', '用户搜索无结果时的回复语', 1, '20121226174650', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208010, 107, 204567, '1010', '【消息客】帮助：' || chr(13) || '' || chr(10) || '1.消息客是集社交、应用、服务、游戏等全方位的互动沟通短彩平台。' || chr(13) || '' || chr(10) || '2.向您提供提供信息查询、服务搜索、互动社交、应用下载等一站式服务体验。' || chr(13) || '' || chr(10) || '3.您可以直接回复@+好友昵称访问您的好友空间，随时与您的好友保持联络。' || chr(13) || '' || chr(10) || '4.你也可以直接回复#+应用名称直接搜索并访问最新最酷的应用。' || chr(13) || '' || chr(10) || '5.回复？+关键字可进行搜索查询。' || chr(13) || '' || chr(10) || '6.更多帮助信息请访问：http://112.4.128.220:18009/wap', '第二帧的内容', 1, '20121226174650', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208047, 104, 178936, '1002', '您尚未订制消息客，回复短信“D”免费订制消息客服务。', '推荐好友回复语', 1, '20121226174958', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208048, 104, 178936, '1003', '@[name]([spcode])还不是您的好友，回复J加为好友。', '邀请回复语', 1, '20121226174958', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208049, 104, 178936, '1004', '已恢复接收来自@[name]([spcode])的短信，回复P继续屏蔽。', '允许接收回复语', 1, '20121226174958', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208050, 104, 178936, '1005', '已屏蔽来自@[name]([spcode])的短信，回复S恢复接收。', '拒绝接收回复语', 1, '20121226174958', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208051, 104, 178936, '1006', '【回复P屏蔽该好友私信】', '屏蔽指令提示', 1, '20121226174958', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (208052, 104, 178936, '1007', '您发送私信的好友不存在，Ta们都是非常活跃的消息客哦，回复序号，可添加Ta们为好友一起私聊。[recommend]', '设置成功回复语', 1, '20121226174958', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207142, 103, 178917, '1002', '您尚未订制消息客，无法设置姓名，请回复短信“D”免费订制消息客服务。', '未定制用户回复语', 1, '20121226105736', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207143, 103, 178917, '1003', '您设置的姓名为空，设置未成功，请回复短信*nc+姓名（如*nc消息客）设置您的姓名。 ', '空内容回复语', 1, '20121226105736', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207144, 103, 178917, '1004', '您发送的内容包含敏感字符，请重新回复短信*nc+姓名（如*nc消息客）设置您的姓名。', '敏感词回复语', 1, '20121226105736', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207145, 103, 178917, '1005', '5', '超时回复语', 1, '20121226105736', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207146, 103, 178917, '1006', '您的昵称过长，回复短信*nc+姓名（如*nc消息客）设置您的姓名。', '昵称过长回复语', 1, '20121226105736', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (207147, 103, 178917, '1007', '姓名“@[nickname]”设置成功。', '设置成功回复语', 1, '20121226105736', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (206902, 152, 179004, '1001', '【#${APP.NAME}】${APP.DESC}；目前用户数：${APP.USER_COUNT}。回复短信“K”开通。', '应用定制成功提示语', 1, '20121225202526', '${APP.NAME}是应用服务名；${APP.DESC}是应用服务描述；${APP.USER_COUNT}是目前用户数');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (206903, 152, 179004, '1002', '【#${APP.NAME}】${APP.HELP}回复短信“G”将关闭。', '应用退订确认短信内容', 1, '20121225202526', '${APP.NAME}是应用服务名；${APP.ORDER_DATE}应用开通时间');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (206904, 152, 179004, '1004', '#${APP.NAME}关闭成功。', '应用退订后提示语', 1, '20121225202526', '${APP.NAME}是应用服务名；');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (206905, 152, 179004, '1005', '非常抱歉，#${APP.NAME}暂时处于非商用状态，无法开通。您可以尝试搜索其他应用。回复短信#+应用名称。', null, 1, '20121225202526', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (206906, 152, 179004, '1006', '【#${APP.NAME}】开通成功 ${APP.HELP} 回复G关闭#${APP.NAME} 。', null, 1, '20121225202526', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (206779, 106, 178967, '1002', '206775', '彩信模版', 1, '20121225181954', '应用彩信模板ID');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209119, 161, 179024, '1004', '抱歉,没有【@${QUERY}】这个用户,您可以回复@好友名去查找好友,或通过以下两种方式邀请好友加入消息客: 1.直接回复好友手机号码； 2.回复''YQ''获得邀请短信,转发到好友手机；3.登录：http://112.4.128.220:18009/wap 搜索好友。', '无查询结果提示语', 1, '20121228103029', '${QUERY}是用户名称');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209120, 161, 179024, '1005', '您可以对好友@${USER.NICKNAME}进行如下操作：回复数字1直接与Ta聊天，回复C删除Ta，回复KJ查看Ta的空间。回复M继续搜索其他同名用户。', '好友操作菜单', 1, '20121228103029', '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209121, 161, 179024, '1006', '@${USER.NICKNAME}还不是您的好友。请回复短息“J”邀请@${USER.NICKNAME}成为您的好友。回复“KJ”查看Ta的空间。', '陌生人操作菜单', 1, '20121228103029', '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209122, 161, 179024, '1001', '抱歉，你尚未订制消息客，无法寻找您的好友，请回复“D”免费订制消息客吧', '陌生人选择菜单(有结果）', 1, '20121228103029', '1');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209123, 161, 179024, '1007', '您已成功删除好友@${USER.NICKNAME}，如需重新添加，请重新回复短信@${USER.NICKNAME}邀请Ta成为您的好友。', '删除好友回复语', 1, '20121228103029', '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209124, 161, 179024, '1010', '您输入的为本机号码或姓名，回复''KJ''查看自己的个人空间，或点击http://192.168.5.96:8009/wap 进行个人设置。', '自生操作菜单', 1, '20121228103029', '1');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209125, 161, 179024, '1011', '你现在可以与@${USER.NICKNAME}聊天啦，直接回复内容与Ta聊天。', '聊天提示短信', 1, '20121228103029', '${USER.NICKNAME}是好友昵称');
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209126, 161, 179024, '1012', 'http://112.4.128.220:18009/wap', null, 1, '20121228103029', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209127, 161, 179024, '1013', '请回复数字序号选择您已添加的好友：${LIST}更多好友请登录http://112.4.128.220:18009/wap。 回复M继续搜索其他用户。', null, 1, '20121228103029', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209128, 161, 179024, '1014', '您目前没有添加任何好友，回复数字序号可添加Ta们为好友：${LIST}发现更多好友请登录http://112.4.128.220:18009/wap', null, 1, '20121228103029', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209129, 161, 179024, '1015', '请回复数字序号选择您已添加的好友：${LIST}更多好友请登录http://112.4.128.220:18009/wap 回复M搜索陌生人用户。', null, 1, '20121228103029', null);
insert into T_BASE_APP_REPLY (id, base_app_id, version_id, param_code, param_value, remark, update_user_id, update_time, param_value_remark)
values (209130, 161, 179024, '1016', '您可以回复数字序号添加Ta们成为好友：${LIST}更多请登录http://112.4.128.220:18009/wap', null, 1, '20121228103029', null);
commit;
prompt 82 records loaded
prompt Loading T_BASE_APP_REPLY_VERSION...
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (208300, 100, '默认版本', '通用错误回复语', 0, 1, '20121227093951');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (204567, 107, '默认版本', '回复语', 0, 1, '20121226174650');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (189616, 101, '默认版本', '回复语', 0, 1, '20121226173859');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (178936, 104, '默认版本', '回复语', 0, 1, '20121226174958');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (178955, 105, '默认版本', '回复语', 0, 1, '20121226174840');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (178993, 151, '默认版本', '回复语', 0, 1, '20121227085558');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (179004, 152, '默认版本', '回复语', 0, 1, '20121225202526');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (178967, 106, '默认版本', '回复语', 0, 1, '20121225181954');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (178900, 102, '默认版本', '回复语', 0, 1, '20121226175127');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (178917, 103, '默认版本', '回复语', 0, 1, '20121226105736');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (178980, 108, '默认版本', '回复语', 0, 1, '20121226174553');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (179024, 161, '默认版本', '回复语', 0, 1, '20121228103029');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (179041, 162, '默认版本', '回复语', 0, 1, '20121226174225');
insert into T_BASE_APP_REPLY_VERSION (id, base_app_id, version_name, remark, status, update_user_id, update_time)
values (179052, 163, '默认版本', '回复语', 0, 1, '20121226141611');
commit;
prompt 14 records loaded
prompt Loading T_BASE_APP_ROUTER...
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10023, 107, '用户搜索', '2', '?*', '1065888899*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10021, 107, '关键字搜索（应用特服号）', '1', '?*', '1065888820*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10020, 162, '邀请好友（手机号码）', 'INVITE_ONLINE', 'J*', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10022, 107, '关键字搜索', '1', '?*', '10658888*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10044, 151, '应用搜索入口', null, '#*', '*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10045, 152, '应用定制确认', 'OC', 'K', '10658888*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10046, 152, '应用退订确认', 'QC', 'G', '10658888*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10024, 101, '平台定制', ' ', 'D', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10025, 101, '同意成为好友', 'ACCEPT_ONLINE_INVITE', 'J', '1065888803*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10026, 101, '同意成为好友', 'ACCEPT_OFFLINE_INVITE', '*', '1065888804*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10033, 104, '密聊', '1', '*', '1065888899*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10034, 104, '确认邀请好友', '2', 'J', '1065888899*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10035, 104, '允许发送私信', '4', 'S', '1065888899*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10036, 104, '屏蔽私信', '3', 'P', '1065888899*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10037, 105, '接受线上邀请', 'CONFIRM', 'K', '1065888805*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10038, 105, '接受线下邀请', null, '*', '1065888805*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10019, 101, '平台定制', null, 'D', '1065888810', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10027, 102, '用户退订', '1', 'T', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10032, 103, '设置昵称', null, '*nc*', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10039, 106, '应用帮助', null, '?', '1065888820*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10040, 108, '关闭新鲜事', '1', '*GB', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10028, 102, '确认退订', '2', '1', '1065888810', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10029, 102, '确认退订', '2', '2', '1065888810', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10041, 108, '每天接收', '2', '*GBD', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10042, 108, '每周接收', '3', '*GBW', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10043, 108, '每月接收', '4', '*GBM', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10047, 161, '邀请用户成为好友', 'INVITE', 'J', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10048, 161, '删除好友', 'DELETE', 'C', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10049, 161, '在陌生人中搜索', 'SEARCH_STRANGERS', 'M', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10050, 161, '用户搜索入口', null, '@*', '*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10051, 162, '用户线下邀请模板', 'INVITE_OFFLINE', 'YQ', '10658888*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10052, 163, '用户空间', null, 'KJ', '10658888', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10030, 102, '确认退订', '2', '3', '1065888810', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10010, 106, '应用帮助', null, '?', '1065888821*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10031, 102, '无效的选项', '3', '*', '1065888810', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10011, 106, '应用帮助', null, '?', '1065888822*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10012, 106, '应用帮助', null, '?', '1065888823*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10013, 106, '应用帮助', null, '?', '1065888824*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10014, 106, '应用帮助', null, '?', '1065888825*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10015, 106, '应用帮助', null, '?', '1065888826*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10016, 106, '应用帮助', null, '?', '1065888827*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10017, 106, '应用帮助', null, '?', '1065888828*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10018, 106, '应用帮助', null, '?', '1065888829*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10009, 107, '关键字搜索（应用特服号）', '1', '?*', '1065888827*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10008, 107, '关键字搜索（应用特服号）', '1', '?*', '1065888826*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10007, 107, '关键字搜索（应用特服号）', '1', '?*', '1065888825*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10006, 107, '关键字搜索（应用特服号）', '1', '?*', '1065888824*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10005, 107, '关键字搜索（应用特服号）', '1', '?*', '1065888823*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10004, 107, '关键字搜索（应用特服号）', '1', '?*', '1065888822*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10003, 107, '关键字搜索（应用特服号）', '1', '?*', '1065888821*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10002, 107, '关键字搜索（应用特服号）', '1', '?*', '1065888820*', 1, 'http://192.168.5.96:19081/app');
insert into T_BASE_APP_ROUTER (id, base_app_id, sub_opt_name, sub_code, command, sp_code, mo_type, url)
values (10001, 151, '应用搜索入口(推荐）', 'RECOMMEND', 'tj', '10658888', 1, 'http://192.168.5.96:19081/app');
commit;
prompt 52 records loaded
prompt Loading T_SYS_MENU...
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (1, '001', '合作管理', '0', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (45, '001005003', '新闻维护', '001005', 'developer/developerMessageQryAction.do?method=qryList', 1, 'developer_message_query', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (8, '002', '应用内容管理', '0', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (9, '002001', '短信内容', '002', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (10, '002001001', '内容录入', '002001', 'info/sms/preAdd.do', 1, 'sms_content', null, 0, 'info/sms/3_1.html', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (11, '002001002', '内容审核', '002001', 'info/sms/auditList.do', 1, 'sms_audit', 'audit_btn', 0, 'info/sms/3_2.html', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (12, '002001003', '内容列表', '002001', 'info/sms/list.do', 1, 'sms_list', 'mod_btn,del_btn', 0, 'info/sms/3_3.html', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (13, '002002', '彩信内容', '002', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (14, '002002001', '内容录入', '002002', 'info/mmsInfoAction.do?method=openInput&contentSrc=0', 1, 'mms_content', null, 0, 'info/mms/add.jsp', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (15, '002002002', '内容审核', '002002', 'info/mmsShenHeAction.do?method=open&contentSrc=0', 1, 'mms_audit', 'audit_btn', 0, 'info/mms/auditList.jsp', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (16, '002002003', '内容列表', '002002', 'info/mmsInfoAction.do?method=list&contentSrc=0', 1, 'mms_list', 'view_btn,mod_btn,del_btn,send_btn', 0, 'info/mms/list.jsp', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (33, '002003', '应用新鲜事', '002', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (34, '002003001', '应用新鲜事审核', '002003', 'newsmanage/newsAppLogList.do?method=auditingList', 1, 'app_news_audit', 'audit_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (35, '002003002', '应用新鲜事列表', '002003', 'newsmanage/newsAppLogList.do', 1, 'news_app_log', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (30, '003', '平台新鲜事', '0', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (31, '003001', '平台新鲜事录入', '003', 'info/mmsInfoAction.do?method=openInput&contentSrc=3', 1, 'fresh_mms_add', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (36, '003002', '平台新鲜事审核', '003', 'info/mmsShenHeAction.do?method=open&contentSrc=3', 1, 'fresh_mms_audit', 'audit_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (32, '003003', '平台新鲜事列表', '003', 'info/mmsInfoAction.do?method=list&contentSrc=3', 1, 'fresh_mms_list', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (38, '004', '群发管理', '0', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (97, '004001', '群发任务管理', '004', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (39, '004001001', '群发任务申请', '004001', 'send/smsBatchTaskAction.do?method=preAddTask', 1, 'batch_task_add', null, 0, 'helpdoc/sale/sale_batch.htm', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (40, '004001002', '群发任务审核', '004001', 'send/batchTaskListAction.do?method=smsAuditList', 1, 'batch_task_audit', 'audit_btn', 0, 'helpdoc/sale/sale_audit.htm', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (41, '004001003', '群发任务监控', '004001', 'send/batchTask.do?method=smsList', 1, 'sms_batch_task_monitor', 'start_btn,pause_btn,cancel_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (21, '004002', '用户分群', '004', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (86, '004002001', '新建分群', '004002', 'usermanage/userGroupAction.do?method=showAddPage', 1, 'user_group_add', 'mod_btn,del_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (87, '004002002', '分群列表', '004002', 'usermanage/userGroupList.do', 1, 'user_group', 'mod_btn,del_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (98, '004003', '群发彩信管理', '004', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (89, '004003001', '群发彩信录入', '004003', 'info/mmsInfoAction.do?method=openInput&contentSrc=1', 1, 'batch_mms_add', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (90, '004003002', '群发彩信审核', '004003', 'info/mmsShenHeAction.do?method=open&contentSrc=1', 1, 'batch_mms_audit', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (91, '004003003', '群发彩信列表', '004003', 'info/mmsInfoAction.do?method=list&contentSrc=1', 1, 'batch_mms_list', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (99, '005', '基础应用配置', '0', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (55, '005001', '回复语管理', '005', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (57, '005001001', '回复语配置', '005001', 'baseappmanage/baseAppList.do', 1, 'app_conf_cfg', 'mod_btn,del_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (27, '005002', '欢迎彩信模板', '005', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (28, '005002001', '模板配置', '005002', 'mmsmanager/mmsInfoAction.do?method=openInput', 1, 'welcome_mms_input', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (29, '005002002', '模板列表', '005002', 'mmsmanager/mmsInfoAction.do?method=qryContMms', 1, 'welcome_mms_query', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (82, '005003', '应用帮助彩信模板', '005', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (83, '005003001', '模板配置', '005003', 'mmsmanager/mmsInfoAction.do?method=openAppHelpInput', 1, 'app_help_mms_input', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (84, '005003002', '模板列表', '005003', 'mmsmanager/mmsInfoAction.do?method=qryAppHelpContMms', 1, 'app_help_mms_query', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (100, '005004', '平台新鲜事模板', '005', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (88, '005004001', '模板配置', '005004', 'mmsmanager/kindly_remind_edit.jsp', 1, 'kindly_remind', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (51, '006', '系统管理', '0', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (52, '006001', '系统账号', '006', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (53, '006001001', '角色管理', '006001', 'unitmanage/roleList.do', 1, 'role_manage', 'add_btn,mod_btn,del_btn', 0, 'unitmanage/2_2.jsp', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (54, '006001002', '账号管理', '006001', 'partnermanage/partnerManagerList.do', 1, 'partner_manager', 'mod_btn,view_btn,del_btn', 0, 'partnermanage/3_2.jsp', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (58, '006003', '参数配置', '006', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (59, '006003001', '号段管理', '006003', 'sysmgr/segment.do', 1, 'segmentcfg', 'add_btn,mod_btn,del_btn,in_btn', 0, 'systemmanage/segment_manage.jsp', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (60, '006003002', '黑名单管理', '006003', 'sysmgr/blackUser.do', 1, 'black_cfg', 'add_btn,mod_btn,del_btn,in_btn', 0, 'systemmanage/black_user_manage.jsp', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (61, '006003003', '敏感字管理', '006003', 'sysmgr/sensitivePhrase.do', 1, 'filter_cfg', 'add_btn,mod_btn,del_btn,in_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (62, '006003004', '测试号码管理', '006003', 'config/testAccount.do', 1, 'test_account', null, 0, 'systemmanage/test_account_manage.jsp', null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (63, '006003005', '数据字典', '006003', 'sysmgr/dicType.do', 1, 'sysdata', 'add_btn,mod_btn,del_btn,in_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (92, '007', '查询统计', '0', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (22, '007001', '订购关系', '007', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (23, '007001001', '平台订购查询', '007001', 'usermanage/platUserList.do', 1, 'plat_user', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (24, '007001002', '应用开通查询', '007001', 'usermanage/userAppList.do', 1, 'user_app', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (101, '007002', '运营数据', '007', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (93, '007002001', '平台基本数据', '007002', 'platbaseinfo/platBaseInfoList.do', 1, 'platbaseinfo', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (94, '007002002', '各类排行榜', '007002', 'platinforank/platInfoRankList.do', 1, 'platinforank', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (95, '007002003', 'WAP网站数据', '007002', 'wapstatistics/wapStatisticsList.do', 1, 'wapstatistics', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (2, '001001', '开发者管理', '001', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (3, '001001001', '开发者列表', '001001', 'developer/developerInfoAction.do?method=qryList', 1, 'developer_info_query', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (4, '001002', '应用管理', '001', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (5, '001002001', '应用审核', '001002', 'appmanager/appInfoAction.do?method=qryList&flag=1', 1, 'app_info_audit', 'audit_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (7, '001002002', '应用维护', '001002', 'appmanager/appInfoAction.do?method=qryEditList', 1, 'app_info_edit', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (79, '001002003', '应用分类', '001002', 'appmanager/appClassAction.do?method=framemain', 1, 'app_type', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (96, '001003', '接口授权管理', '001', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (6, '001003001', '接口授权', '001003', 'appmanager/appInfoAction.do?method=qryInterfaceGrantList', 1, 'app_interface_grant', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (17, '001004', '工单管理', '001', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (18, '001004001', '工单录入', '001004', 'syssupport/sysSupportAction.do?method=showAddPage', 1, 'sys_support_input', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (19, '001004002', '工单列表', '001004', 'syssupport/sysSupportList.do', 1, 'sys_support_cfg', 'mod_btn,del_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (43, '001005', '动态新闻管理', '001', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (44, '001005001', '新闻录入', '001005', 'developer/developerMessageAction.do?method=preAddMessage', 1, 'developer_message_input', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (81, '001005002', '新闻审核', '001005', 'developer/developerMessageQryAction.do?method=qryAuditList', 1, 'platform_news_audit', 'audit_btn', 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (102, '005005', '关键字搜索彩信模板', '005', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (103, '005006', '用户搜索彩信模板', '005', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (104, '005007', '好友空间彩信模板', '005', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (105, '005008', '个人空间彩信模板', '005', null, 0, null, null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (106, '005005001', '模板配置', '005005', 'mmsmanager/mmsInfoAction.do?method=openKeywordSearchInput', 1, 'keyword_search_input', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (107, '005005002', '模板列表', '005005', 'mmsmanager/mmsInfoAction.do?method=qryKeywordSearchMms', 1, 'keyword_search_list', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (108, '005006001', '模板配置', '005006', 'mmsmanager/mmsInfoAction.do?method=openUserSearchInput', 1, 'user_search_input', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (109, '005006002', '模板列表', '005006', 'mmsmanager/mmsInfoAction.do?method=qryUserSearchMms', 1, 'user_search_list', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (110, '005007001', '模板配置', '005007', 'mmsmanager/mmsInfoAction.do?method=openFriendZoneInput', 1, 'friend_zone_input', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (111, '005007002', '模板列表', '005007', 'mmsmanager/mmsInfoAction.do?method=qryFriendZoneMms', 1, 'friend_zone_list', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (112, '005008001', '模板配置', '005008', 'mmsmanager/mmsInfoAction.do?method=openUserZoneInput', 1, 'user_zone_input', null, 0, null, null);
insert into T_SYS_MENU (id, menu_code, menu_name, menu_pcode, menu_url, leaf_yn, model_code, url_btns, sys_type, help_url, unit_id)
values (113, '005008002', '模板列表', '005008', 'mmsmanager/mmsInfoAction.do?method=qryUserZoneMms', 1, 'user_zone_list', null, 0, null, null);
commit;
prompt 85 records loaded
prompt Loading T_SYS_SERVICE_INFO...
insert into T_SYS_SERVICE_INFO (id, service_name, service_id, fee_type, fee, service_type, remark, batch_type)
values (1, '短信免费', 'FREE', 1, 0, 1, null, 1);
insert into T_SYS_SERVICE_INFO (id, service_name, service_id, fee_type, fee, service_type, remark, batch_type)
values (2, '彩信免费', '101101', 1, 0, 2, null, 1);
insert into T_SYS_SERVICE_INFO (id, service_name, service_id, fee_type, fee, service_type, remark, batch_type)
values (3, 'WAP免费', 'FREE', 1, 0, 3, null, 1);
commit;
prompt 3 records loaded
prompt Loading T_SYS_USER...
insert into T_SYS_USER (id, user_id, user_pwd, user_name, unit_id, tel, status, attr_flag, remark, email, teminal_id, forever, valid_time, create_time, change_pwd_time, lock_status, update_time)
values (1, 'system', 'E10ADC3949BA59ABBE56E057F20F883E', '超级管理员', '01', null, 1, 0, null, 'jisn@c-platform.com', '13851628076', 1, '20201122165749', '20091117000000', '20121221102355', '0', '20121221102355');
insert into T_SYS_USER (id, user_id, user_pwd, user_name, unit_id, tel, status, attr_flag, remark, email, teminal_id, forever, valid_time, create_time, change_pwd_time, lock_status, update_time)
values (207837, 'limin', 'E10ADC3949BA59ABBE56E057F20F883E', '李敏', null, null, 1, 1, '超级管理员，耶~', 'liminb@c-platform.com', '15951921547', 1, '20201122165749', '20121226163157', '20121226163157', '0', null);
commit;
prompt 2 records loaded
prompt Enabling triggers for T_APP_CLASSIFICATION...
alter table T_APP_CLASSIFICATION enable all triggers;
prompt Enabling triggers for T_BASE_APP_CONF...
alter table T_BASE_APP_CONF enable all triggers;
prompt Enabling triggers for T_BASE_APP_CONF_TYPE...
alter table T_BASE_APP_CONF_TYPE enable all triggers;
prompt Enabling triggers for T_BASE_APP_INFO...
alter table T_BASE_APP_INFO enable all triggers;
prompt Enabling triggers for T_BASE_APP_REPLY...
alter table T_BASE_APP_REPLY enable all triggers;
prompt Enabling triggers for T_BASE_APP_REPLY_VERSION...
alter table T_BASE_APP_REPLY_VERSION enable all triggers;
prompt Enabling triggers for T_BASE_APP_ROUTER...
alter table T_BASE_APP_ROUTER enable all triggers;
prompt Enabling triggers for T_SYS_MENU...
alter table T_SYS_MENU enable all triggers;
prompt Enabling triggers for T_SYS_SERVICE_INFO...
alter table T_SYS_SERVICE_INFO enable all triggers;
prompt Enabling triggers for T_SYS_USER...
alter table T_SYS_USER enable all triggers;
set feedback on
set define on
prompt Done.
