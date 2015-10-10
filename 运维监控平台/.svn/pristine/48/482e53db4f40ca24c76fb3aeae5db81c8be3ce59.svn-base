<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="inner_title" value="验证记录查询"/>
<%@ include file="../includes/t.jsp" %>

<div class="row-fluid"><div class="span12">

	<ul id="myTab" class="nav nav-tabs slide-tab">
        <li class="active last-child"><a href="#home" data-toggle="tab"><i class="icon14 i-search-3"></i>验证记录查询<b class="caret"></b></a></li>
        <li class="extend-btn"><a href="javascript:void(0);" class="btn btn-primary" onclick="exportReport();"><i class="icon14 i-file-excel"></i>导出excel</a></li>
    </ul>

    <div class="tab-content hide-tab-panel">
        <div class="tab-pane fade in active" id="home">
            <form class="form-horizontal" action="?" name="queryForm" id="queryForm" method="get">
                <div class="span6">
                    <div class="control-group group-search">
                        <label class="control-label" for="itemName">商品：</label>
                        <div class="controls controls-row">
                            <input type="text" id="itemId" name="itemId" class="span4" placeholder="商品编号" value="${param.itemId}"/>
                            <input type="text" id="itemName" name="itemName" class="span8" placeholder="商品名" value="${param.itemName}" />
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="control-group group-search">
                        <label class="control-label" for="op">操作员：</label>
                        <div class="controls controls-row">
                            <input type="text" id="op" name="op" class="span6" value="${param.op}" />
                        </div>
                    </div>
                </div>
                <div class="control-group group-search">
                    <label class="control-label">验证时间：</label>
                    <div class="controls controls-row">
                        <div id="timeRangeGroup" class="btn-group" data-toggle="buttons-radio">
                            <button type="button" class="btn" data-value="all">全部</button>
                            <button type="button" class="btn" data-value="today">今天</button>
                            <button type="button" class="btn" data-value="seven">最近7天</button>
                            <button type="button" class="btn" data-value="custom">自定义</button>
                        </div>
                        <span>&nbsp;</span>
                        <input type="hidden" id="timeRange" name="timeRange" value="${param.timeRange}" />
                        <div id="from" class="input-append input-daterange date c-date"  data-provide="datepicker-inline">
                            <span class="add-on"><i class="icon16 i-calendar"></i></span>
                            <input type="text" name="queryStartTime" id="queryStartTime" data-date-format="yyyy-mm-dd" value="${param.queryStartTime}">
                            <span class="add-on">至</span>
                            <input type="text" name="queryEndTime" id="queryEndTime" data-date-format="yyyy-mm-dd" value="${param.queryEndTime}">
                        </div>
                    </div>
                </div>
                <div class="control-group group-search">
                    <label class="control-label"></label>
                    <div class="controls controls-row">
                        <button class="btn btn-primary" type="submit"><i class="icon16 i-search"></i>查询</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    
    <div class="page-header">
        <h4><i class="icon16 i-list"></i>历史记录</h4>
    </div>

    <c:if test="${empty datas.list}">
        <div class="alert alert-info">
            <strong><i class="icon24 i-info"></i> 当前没有相关记录！</strong>
        </div>
    </c:if>
    <c:if test="${not empty datas.list}">
        <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th>商品名称</th>
            <th>订单号</th>
            <th>数量</th>
            <th>验证渠道</th>
            <th>验证时间</th>
            <th>验证状态</th>
            <th>操作员</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${datas.list}" var="item">
                <tr>
                    <td class="vcenter">
                        <a target="_blank" href="<ct:path flag="1" id="${item.item_id}"/>">${item.item_name}</a>
                    </td>
                    <td class="vcenter">${item.act_order_id}</td>
                    <td class="vcenter">${item.use_times}</td>
                    <td class="vcenter">
                        <c:if test="${item.verify_channel == 'WEB'}">网站</c:if>
                        <c:if test="${item.verify_channel == 'WAP'}">WAP</c:if>
                        <c:if test="${item.verify_channel == 'SMS'}">短信</c:if>
                        <c:if test="${item.verify_channel == 'CLIENT'}">客户端</c:if>
                        <c:if test="${item.verify_channel == 'POS'}">POS机</c:if>
                    </td>
                    <td class="vcenter"><ct:time source="${item.verify_date}"/></td>
                    <td class="vcenter">
                        <c:choose>
                            <c:when test="${item.verify_status eq '0000' || item.verify_status eq '1000'}">成功</c:when>
                            <c:otherwise>失败(CODE:${item.verify_status})</c:otherwise>
                        </c:choose>
                    </td>
                    <td class="vcenter">
                        <c:if test="${item.verify_channel eq 'POS'}">${item.pos_id}<%-- - ${item.pos_seq}--%></c:if>
                        <c:if test="${item.verify_channel ne 'POS'}">${item.verify_user}</c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        </table>
        <ht:page pageData="${datas}"/>
    </c:if>

</div></div>

<script>
    $(function() {
        $("#timeRangeGroup button").click(function() {
            timeRangeStatus(this);
        });

        function timeRangeStatus(elm) {
            if (!elm || elm.length == 0) {
                $('.input-daterange').hide(); return;
            }
            var v = $(elm).data("value");
            if (v == 'custom') {
                $('.input-daterange').show();
            } else {
                $('.input-daterange input').val('');
                $('.input-daterange').hide();
            }
            $('#timeRange').val(v);
        }

        var checked = $('#timeRangeGroup button').filter("[data-value='" + $('#timeRange').val() + "']");
        timeRangeStatus(checked);
        checked.addClass('active');
    });


    function exportReport() {
        var exportUrl = '<c:url value="/cert/history/export"/>';
        var queryUrl = '<c:url value="/cert/history"/>';
        $("#queryForm").attr('action', exportUrl);
        $("#queryForm").submit();
        $("#queryForm").attr('action', queryUrl);
    }
</script>

<%@ include file="../includes/b.jsp" %>