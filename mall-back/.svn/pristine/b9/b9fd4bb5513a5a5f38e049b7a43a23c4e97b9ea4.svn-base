<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
	<ht:head/>
	<script type="text/javascript">
	function rd(){
		window.location.href="./list";
	}
	</script>
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
            
           	 <td>卡序列号：</td>
            <td><input type="text" name="serialNo" value="${param.serialNo}" class="txt" style="width:120px"/>
            	</td>
            <td><input type = "hidden" name ="id" value="${id}"/></td>
              <td>状态：</td>
              <td>
              <select name="status">
						<option value="">未选择</option>
						<option value="0" <c:if test="${param.status == 0}">selected="selected"</c:if>>未激活</option>
						<option value="1" <c:if test="${param.status == 1}">selected="selected"</c:if>>已激活</option>
						<option value="2" <c:if test="${param.status == 2}">selected="selected"</c:if>>激活失败</option>
			  </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </td>
              <td><ct:btn type="search" /></td>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>任务详情列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
        	<colgroup>
		       	<col width="80"></col>
		       	<col width="100"></col>
		       	<col width="80"></col>
		       	<col width="100"></col>
		       	<col width="100"></col>
		       	<col width="100"></col>
	    	</colgroup>
            <tr>
                <th nowrap="nowrap" width="80">编号</th>
                <th nowrap="nowrap"  width="300">任务编号</th>
                <th nowrap="nowrap"  width="100">卡序列号</th>
                <th nowrap="nowrap"  width="180">状态</th>
                <th nowrap="nowrap"  width="180">创建时间</th>
                <th nowrap="nowrap"  width="180">激活时间</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap">${item.taskId}</td>
                <td nowrap="nowrap">${item.serialNo}</td>
                <td nowrap="nowrap">
                <c:if test="${item.status == 0 }">未激活</c:if>
                <c:if test="${item.status == 1 }">已激活</c:if>
                <c:if test="${item.status == 2 }">激活失败</c:if>
                </td>
                <td nowrap="nowrap"><ct:time source="${item.createTime}" /></td>
                <td nowrap="nowrap"><ct:time source="${item.actTime}" /></td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${pageData}" />

        </c:if>
        <c:if test="${empty pageData}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>
	 	<div class="buttons">
			<input type="button" class="common_btn" onclick="rd();" value="返回"/>
		</div>
</div>

</body>
</html>