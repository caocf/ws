<%@ page language="java" pageEncoding="utf-8"%>
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
        <ct:display model="shop_list" btn="add_btn">
        <ht:menu-btn text="添加门店" url="/store/shop/add?acShopId=" type="add"/>
	</ct:display>
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                <td width="70">商户名称</td>
                <td width="300"><input type="text" name="storeName" value="${param.storeName}" class="txt" style="width:206px"/></td>
                 <td width="70">商户编号</td>
                <td width="300"><input type="text" name="acShopId" value="${param.acShopId}" class="txt validate-number" style="width:206px"/></td>
            </tr>
            <tr>
            <td width="70">门店名称：</td>
                <td width="300"><input type="text" name="name" value="${param.name}" class="txt" style="width:206px"/></td>
                <td width="70">有效时间：</td> 	
				<td width="" colspan="3">
                    <input type="text" id="inputStartTimeS" name="inputStartTimeS" value="${param.inputStartTimeS}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTimeBegin',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'stopTimeEnd\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTimeBegin" id="startTimeBegin" value="${param.startTimeBegin}"/>
                   	 至
                    <input type="text" id="inputEndTimeE" name="inputEndTimeE" value="${param.inputEndTimeE}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'stopTimeEnd',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startTimeBegin\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="stopTimeEnd" id="stopTimeEnd" value="${param.stopTimeEnd}" />
                </td>
            </tr>
            <tr>
                <td>是否连锁：</td>
                <td>
                    <select name="isChain">
                        <option value="">--请选择--</option>
                        <c:forEach items="${isChainMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.isChain == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>是否总店：</td>
                <td>
                    <select name="isBaseShop">
                        <option value="">--请选择--</option>
                        <c:forEach items="${isBaseShopMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.isBaseShop == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>门店状态：</td>
                <td>
                    <select name="status">
                        <option value="">--请选择--</option>
                        <c:forEach items="${statusMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>签约状态：</td>
                <td>
                    <select name="sort">
                        <option value="">--请选择--</option>
                        <c:forEach items="${sortMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.sort == item.key}">selected="selected"</c:if>>${item.value }</option>
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
    <h3>门店列表</h3>

    <div class="mainbox">
        <c:if test="${not empty shopPage}">

        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap" width="70">门店编号</th>
                <th nowrap="nowrap" width="180">结算商户</th>
                <th nowrap="nowrap" width="180">门店名称</th>
                <th nowrap="nowrap" width="60">地市</th>
                <th nowrap="nowrap" width="60">签约状态</th>
                <th nowrap="nowrap" width="50">连锁</th>
                <th nowrap="nowrap" width="50">总店</th>
                <th nowrap="nowrap" width="160">有效时间</th>
                <th nowrap="nowrap" width="60">门店状态</th>
                <th nowrap="nowrap" width="130">操作</th>
            </tr>

            <c:forEach items="${shopPage.data}" var="item">
            <tr>
            	<td >${item.id}</td>
                <td >${item.storeName}</td>
                <td>${item.name}</td>
                <td >${item.regionName}</td>
                <td >${item.sortName}</td>
                <td >${item.isChainName}</td>
                <td >${item.isBaseShopName}</td>
                <td><ct:time source="${item.startTime}" tfmt="yyyy-MM-dd"/>~<ct:time source="${item.stopTime}" tfmt="yyyy-MM-dd"/></td>
                <td>${item.statusName}</td>
                <td nowrap="nowrap">
                	<ct:display model="shop_list" btn="view_btn">
                	<a href="view?id=${item.id}">查看</a>&nbsp;&nbsp;
                	</ct:display>
                		<ct:display model="shop_list" btn="mod_btn">
                   	<a href="./edit?id=${item.id}">修改</a>&nbsp;&nbsp;
                   	</ct:display>
                	<!-- <a href="#this" id="${item.id}" class="img">相册</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
                	<c:if test="${item.status == '0'}">
<!--                	<ct:display model="shop_list" btn="mod_btn">-->
<!--                   	<a href="./edit?id=${item.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--                   	</ct:display>-->
                   	<ct:display model="shop_list" btn="audit_btn">
                		<a href="#" onclick="dealInfo('确定要送审吗？如果免审，将直接审核通过','process/${item.status}/${item.id}/audit');">送审</a>&nbsp;&nbsp;
                	</ct:display>
                   	<ct:display model="shop_list" btn="del_btn">
                   	<a href="#" onclick="deleteItem('./delete/${item.id}');">删除</a>
                   	</ct:display>
                   	</c:if>
                   	<c:if test="${item.status == '4'}">
<!--                   	<ct:display model="shop_list" btn="mod_btn">-->
<!--                   	<a href="./edit?id=${item.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--                   	</ct:display>-->
                   	<ct:display model="shop_list" btn="del_btn">
                   	<a href="#" onclick="deleteItem('./delete/${item.id}');">删除</a>
                   	</ct:display>
                   	</c:if>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${shopPage}" />

        </c:if>
        <c:if test="${empty shopPage}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>
</div>
<script type="text/javascript">
$().ready(function() {		
	$(".img").click(function() {
			var id = $(this).attr("id");
			showDialog("业务门店相册管理", "${ctx}/store/shop/img_list?shopId="+id,function(doc){
			},{"Width":700,"Height":700,"ShowButtonRow" : false});			
	});
});
</script>
</body>
</html>