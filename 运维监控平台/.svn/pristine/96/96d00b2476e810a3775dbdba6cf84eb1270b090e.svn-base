<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="操作日志" />
<%@ include file="../../includes/t.jsp"%>

<script type="text/javascript">
	$(function() {
		// 判断日期
		$("#sub").click(function() {
			var saleStartTime = $('#saleStartTime').val();
			var saleStopTime = $('#saleStopTime').val();
			if (saleStopTime) {
				if (saleStartTime > saleStopTime) {
					alert("开始日期大于结束日期，请重新选择！");
					return;
				}
			}
		});
	});
</script>
<div class="row-fluid">
    <div class="span12">
        <ul id="myTab" class="nav nav-tabs slide-tab">
            <li class="active last-child"><a href="#home" data-toggle="tab">
                    <i class="icon14 i-search-3"></i> 操作日志查询 <b class="caret"></b>
                </a></li>
        </ul>

        <div class="tab-content hide-tab-panel">
            <div class="tab-pane fade in active" id="home">
                <form class="form-horizontal" action="?" name="queryForm" id="queryForm" method="get">
                    <div class="control-group group-search" style="margin-left: 0px;">
                        <label class="control-label" for="queryStartTime">时间范围：</label>
                        <div class="controls controls-row">
                            <div id="from" class="input-append date" data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
                                <input size="16" type="text" name="startTime" id="saleStartTime" value="${param.startTime}"> <span class="add-on"> <i class="icon16 i-calendar-4"></i>
                                </span>
                            </div>
                            <span class="form-middle-word">至</span>
                            <div id="to" class="input-append date" data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
                                <input size="16" type="text" name="endTime" id="saleStopTime" value="${param.endTime}"> <span class="add-on"> <i class="icon16 i-calendar-4"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="control-group group-search">
                        <label class="control-label"></label>
                        <div class="controls controls-row">
                            <button id="sub" class="btn btn-primary" type="submit">
                                <i class="icon16 i-search"></i> 筛选
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="page-header">
            <h4>操作日志列表</h4>
        </div>

        <c:if test="${empty datas.data}">
            <div class="alert alert-info">
                <strong> <i class="icon24 i-info"></i> 当前没有相关记录！
                </strong>
            </div>
        </c:if>

        <c:if test="${not empty datas.data}">
            <table class="table table-bordered table-hover table-striped">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>操作时间</th>
                        <th>功能模块</th>
                        <th>操作对象</th>
                        <th>操作类型</th>
                        <th>操作次数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datas.data}" var="item">
                        <tr>
                            <td class="center vcenter">${item.id}</td>
                            <td class="center vcenter"><ct:time source="${item.opTime}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss" /></td>
                            <td class="center vcenter">${item.module}</td>
                            <td class="center vcenter">${item.opObject}</td>
                            <td class="center vcenter"><c:if test="${item.opType == 1}">查看</c:if> <c:if test="${item.opType == 2}">增加</c:if> <c:if test="${item.opType == 3}">修改</c:if> <c:if
                                    test="${item.opType == 4}"
                                >删除</c:if> <c:if test="${item.opType == 5}">审核</c:if> <c:if test="${item.opType == 6}">其他</c:if></td>
                            <td class="center vcenter">1</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <ht:page pageData="${datas}" />
        </c:if>

    </div>
</div>

<%@ include file="../../includes/b.jsp"%>