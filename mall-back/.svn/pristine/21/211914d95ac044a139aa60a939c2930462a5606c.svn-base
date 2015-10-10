<%@ page language="java" pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
	<ht:head/>
</head>
<body>
<br/>
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
            	 <td width="70">会员id：</td>
                <td width="200"><input type="text" id="id" name="id" value="${param.id}" title="会员id" class="txt validate-number" style="width:206px"/>
                <span class="error" id="advice-validate-number-id" style="display:none"></span></td>
                <td width="70">邮箱：</td>
                <td width="200"><input type="text" name="email" value="${param.email}" class="txt" style="width:206px"/></td>
                <td width="70">注册时间：</td> 	
				<td width="300">
                    <input type="text" id="inputStartTimeS" name="inputStartTimeS" value="${param.inputStartTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'regTimeBegin',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTimeS\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="regTimeBegin" id="regTimeBegin" value="${param.regTimeBegin}"/>
                   	 至
                    <input type="text" id="inputEndTimeS" name="inputEndTimeS" value="${param.inputEndTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'regTimeEnd',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTimeS\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="regTimeEnd" id="regTimeEnd" value="${param.regTimeEnd}" />
                </td>
            </tr>
            <tr>
                <td>号码：</td>
                <td><input type="text" name="terminalId" value="${param.terminalId}" class="txt" style="width:206px"/></td>
                <td>状态：</td>
                <td>                
                    <select name="status">
                        <option value="">--请选择--</option>
                        <c:forEach items="${statusMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
               		 级别：                
                    <select name="userLevel">
                        <option value="">--请选择--</option>
                        <c:forEach items="${userLevelMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${param.userLevel == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>会员列表</h3>

    <div class="mainbox">
        <c:if test="${not empty memberPage}">
        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap" width="90">会员编号</th>
                <th nowrap="nowrap" width="140">邮箱</th>
                <th nowrap="nowrap" width="70">姓名</th>
                <th nowrap="nowrap" width="90">号码</th>
                <th nowrap="nowrap" width="90">注册时间</th>
                <th nowrap="nowrap" width="60">归属地</th>
                <th nowrap="nowrap" width="50">级别</th>
                <th nowrap="nowrap" width="50">状态</th>
                <th nowrap="nowrap" width="100">操作</th>
            </tr>
            <c:forEach items="${memberPage.data}" var="item">
            <tr>
           	 	<td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap">${item.email}</td>
                <td nowrap="nowrap">${item.realName}</td>
                <td nowrap="nowrap"><c:set var="str" value="${item.terminalId}"/><c:if test="${str != null}"><c:out value="${fn:substring(str,0,3)}"/>****<c:out value="${fn:substring(str,7,11)}"/></c:if></td>
                <td nowrap="nowrap"><ct:time source="${item.regTime}" tfmt="yyyy-MM-dd"/></td>
                <td nowrap="nowrap">${item.sysRegion.regionName}</td>
                <td nowrap="nowrap">${item.userLevel}</td>
                <td nowrap="nowrap">${item.statusName}</td>
                <td nowrap="nowrap">
                <c:if test="${item.status == '1'}">
                <ct:display model="member_management_list" btn="pause_btn">
                <a href="#" onclick="dealInfo('确定要暂停吗？','operate/1/${item.id}/list');">暂停</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                </c:if>
                <c:if test="${item.status == '2'}">
                <ct:display model="member_management_list" btn="start_btn">
                <a href="#" onclick="dealInfo('确定要恢复吗？','operate/2/${item.id}/list');">恢复</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                </c:if>
                <ct:display model="member_management_list" btn="view_btn">
                	<a href="#this" id="${item.id}" class="view">查看</a>
                </ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${memberPage}" />

        </c:if>
        <c:if test="${empty memberPage}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>
</div>
<script type="text/javascript">
$().ready(function() {	
	$(".view").click(function() {
			var id = $(this).attr("id");
			 location.href="${ctx}/member/management/view?id="+id;
<!--			showDialog("会员详细信息", "${ctx}/member/management/view?id="+id,function(doc){-->
<!--			},{"Width":400,"Height":600,"ShowButtonRow" : false});			-->
	});
});
</script>
</body>
</html>