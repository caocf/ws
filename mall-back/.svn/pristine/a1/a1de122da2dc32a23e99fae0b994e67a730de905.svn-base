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
        <ct:display model="customer-list" btn="add_btn">
             <ht:menu-btn text="添加第三方应用" url="/sys/open/customer-add" type="add"/>
        </ct:display>
       
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
             <tr>
                <td width="70">应用名称：</td>
                <td width="100"/>
                <input type="text" name="name" value="${param.name}" class="txt" style="width:150px"/>
                </td>
                <td width="70">应用ID：</td>
                <td width="100"/>
                <input type="text" name="appId" title="应用ID" value="${param.appId}" class="txt validate-number" style="width:150px"/>
                </td>
                <td width="70">状态：</td>
                <td width="100"/>
                 <select name="status">
                        <option value="">--请选择--</option>
                    	<c:forEach items="${statusMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
                 <td colspan="2">
                    <ct:btn type="search" />
                </td>
               
            </tr>
        </table>
    </form>
</div>

<div class="container">
    <br/>
    <h3>第三方应用列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="80">应用ID</th>
                <th nowrap="nowrap"  width="130">应用KEY</th>
                <th nowrap="nowrap" >应用名称</th>
                <th nowrap="nowrap" width="60">状态</th>
                <th nowrap="nowrap" width="60">应用类型</th>
                <th nowrap="nowrap" width="60">商户类型</th>
                <th nowrap="nowrap" >商户名称</th>
                <th nowrap="nowrap" >门店名称</th>
                <th nowrap="nowrap" width="200" >操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
           <tr>
                <td nowrap="nowrap">${item.appId}</td>
                <td nowrap="nowrap" class="ellipsis">${item.appKey}</td>
                <td nowrap="nowrap" class="ellipsis">${item.name}</td>
                <td nowrap="nowrap" >${item.statusName}</td>
                <td nowrap="nowrap" >${item.appTypeName}</td>
                <td nowrap="nowrap" >${item.shopClassName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.storeName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.shopName}</td>
                <td nowrap="nowrap">
                  <ct:display model="customer-list" btn="view_btn">
                        <a href="customer-view.do?appId=${item.appId}">查看</a>
                    </ct:display>
                     &nbsp;&nbsp;
                    <ct:display model="customer-list" btn="mod_btn">
                        <a href="customer-edit?appId=${item.appId}">修改</a>
                        <a href="javascript:void(0);" onclick='selectItems("${item.appId}")' >商品配置</a>
                    </ct:display>
                    &nbsp;&nbsp;
                    <ct:display model="customer-list" btn="del_btn">
                        <a href="#" onclick="deleteItem('customer-del?appId=${item.appId}');">删除</a>
                    </ct:display>
                    <c:if test="${item.status == '0' || item.status == '1'}">
                     &nbsp;&nbsp;
                    <ct:display model="customer-list" btn="audit_btn">
                        <a href="customer-audit.do?appId=${item.appId}" >审核</a>
                    </ct:display>
                    </c:if>
                    <c:if test="${item.status == '2' || item.status == '4'}">
                     &nbsp;&nbsp;
                    <ct:display model="customer-list" btn="up_line_btn">
                        <a href="#" onclick="dealInfo('确定要上线吗？','customer-upline?appId=${item.appId}');">上线</a>
                    </ct:display>
                    </c:if>
                    <c:if test="${item.status == '3'}">
                     &nbsp;&nbsp;
                    <ct:display model="customer-list" btn="down_line_btn">
                        <a href="#" onclick="dealInfo('确定要下线吗？','customer-downline?appId=${item.appId}');">下线</a>
                    </ct:display>
                    </c:if>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${pageData}" />

        </c:if>
        <c:if test="${empty pageData.data}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>

<script type="text/javascript">
/**
 * 选择商户 单选
 * @param returnValueId 返回的id
 * @param returnTxtId 返回的文本
 * @param opts
 */
function selectItems(appId){	
	var url = G_CTX_ROOT + '/sys/open/item-list';
	url += "?appId="+appId+"&configStatus=0&isValid=1&status=3";
	
	showDialog("选择商品",url, function(doc,win){		
	},{ShowMessageRow:false,
		Height: 500,
		Width : 900});
}
</script>
</html>