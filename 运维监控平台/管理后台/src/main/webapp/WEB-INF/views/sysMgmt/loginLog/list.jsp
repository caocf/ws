<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="登录日志" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
$(function(){
	$('#searchBtn').click(function(){
		// 判断日期
		var saleStartTime = $('#loginStartTime').val();
		var saleStopTime = $('#loginStopTime').val();
		if (saleStopTime) {
			if (saleStartTime > saleStopTime) {
				alert("开始日期大于结束日期，请重新选择！");
				return;
			}
		}
		
		document.queryForm.content.value = encodeURI($('#loginContent').val(), "");
		document.queryForm.startTime.value = encodeURI($('#loginStartTime').val(), "");
		document.queryForm.endTime.value = encodeURI($('#loginStopTime').val(), "");
		$("#queryForm").submit();
	});
});
</script>

<div class="row-fluid">
    <div class="span12">
        <ul id="myTab" class="nav nav-tabs slide-tab">
            <li class="active last-child">
                <a href="#home" data-toggle="tab">
                    <i class="icon14 i-search-3"></i>
                    登录日志查询
                    <b class="caret"></b>
                </a>
            </li>
        </ul>

        <div class="tab-content hide-tab-panel">
            <div class="tab-pane fade in active" id="home">
                    <div class="span4">
                        <div class="control-group group-search">
                            <label class="control-label" for="name" style="width: 135px;">姓名/登录账户：</label>
                            <div class="controls controls-row" >
                                <input type="text" id="loginContent" name="content" class="span10" value="${content}" style="margin-left: 0px;margin-top: -25px;"/>
                            </div>
                        </div>
                    </div>
                    <div class="control-group group-search" style="margin-left: 0px;float:left;">
                        <label class="control-label" for="queryStartTime">时间范围：</label>
                        <div class="controls controls-row" style="margin-top:-25px !important;">
                            <div id="from" class="input-append date" data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
                                <input size="16" type="text" name="startTime" id="loginStartTime" value="${startTime}" />
                                <span class="add-on">
                                    <i class="icon16 i-calendar-4"></i>
                                </span>
                            </div>
                            <span class="form-middle-word" style="margin-top:-25px !important;">至</span>
                            <div id="to" class="input-append date" data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
                                <input size="16" type="text" name="endTime" id="loginStopTime" value="${endTime}" />
                                <span class="add-on">
                                    <i class="icon16 i-calendar-4"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                <form class="form-horizontal" action="?" name="queryForm" id="queryForm" method="get">
                    <div class="control-group group-search">
                        <label class="control-label"></label>
                        <div class="controls controls-row">
                        	<input type="hidden" id="content" name="content" />
                        	<input type="hidden" id="startTime" name="startTime" />
                        	<input type="hidden" id="endTime" name="endTime" />
                            <button class="btn btn-primary" type="button" id="searchBtn">
                                <i class="icon16 i-search"></i>
                                筛选
                            </button>
                        </div>
                    </div>
                </form>

            </div>
        </div>

        <div class="page-header">
            <h4>登录日志列表</h4>
        </div>

        <c:if test="${empty loginLogPage.data}">
            <div class="alert alert-info">
                <strong>
                    <i class="icon24 i-info"></i>
                    当前没有相关记录！
                </strong>
            </div>
        </c:if>

        <c:if test="${not empty loginLogPage.data}">
            <table class="table table-bordered table-hover table-striped">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>用户ID</th>
                        <th>登录帐号</th>
                        <th>用户姓名</th>
                        <th>来源IP</th>
                        <th>登录时间</th>
                        <th>登录系统</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${loginLogPage.data}" var="item" varStatus="loginlog">
                        <tr>
                            <td class="center vcenter">${item.id}</td>
                            <td class="center vcenter">${item.userId}</td>
                            <td class="center vcenter">${item.userCode}</td>
                            <td class="center vcenter">${item.userName}</td>
                            <td class="center vcenter">${item.ip}</td>
                            <td class="center vcenter">
                                <ct:time source="${item.loginTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss" />
                            </td>
                            <td class="center vcenter">数据中心</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <ht:page pageData="${loginLogPage}" />
        </c:if>

    </div>
</div>

<%@ include file="../../includes/b.jsp"%>
