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
	</ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="?" method="get">
        <table>
            <tr>
                <td width="70">账号</td>
                <td width="300"><input type="text" name="code" value="${param.code}" class="txt" style="width:206px"/></td>
                <td width="70">手机号：</td>
                <td width="300"><input type="text" name="mobile" value="${param.mobile}" title="手机号" class="txt validate-number" style="width:206px"/></td>
            </tr>
            <tr>
             <td width="70">商户编号：</td>
                <td width="300"><input type="text" name="shopId" value="${param.shopId}" title="商户编号" class="txt validate-number" style="width:206px"/></td>
            <td width="70">商户名称：</td>
                <td width="300"><input type="text" name="name" value="${param.name}" class="txt" style="width:206px"/></td>
            </tr>
            <tr>
            	<td>状态：</td>
                <td>
                    <select name="status">
                        <option value="">--请选择--</option>
                        <c:forEach items="${statusMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${param.status == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>类别：</td>
                <td>
                    <select name="shopClass">
                        <option value="">--请选择--</option>
                        <c:forEach items="${shopClassMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${param.shopClass == item.key}">selected="selected"</c:if>>${item.value}</option>
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
    <h3>账号列表</h3>

    <div class="mainbox">
        <c:if test="${not empty userPage.data}">

        <table class="datalist fixwidth">
            <tr>
            	<th nowrap="nowrap" width="60">账户编号</th>
                <th nowrap="nowrap" width="100">账号</th>
                <th nowrap="nowrap" width="80">类型</th>
                <th nowrap="nowrap" width="100">号码</th>
                <th nowrap="nowrap" width="60">类别</th>
                <th nowrap="nowrap" width="350">归属商户</th>
                <th nowrap="nowrap" width="50">状态</th>
                <th nowrap="nowrap" >操作</th>
            </tr>

            <c:forEach items="${userPage.data}" var="item">
            <tr>
            	<td nowrap="nowrap">${item.id}</td>
                <td nowrap="nowrap">${item.code}</td>
                <td nowrap="nowrap">${item.typeName}</td>
                <td nowrap="nowrap">${item.mobile}</td>
                <td nowrap="nowrap">${item.shopClassName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.name}</td>
                <td nowrap="nowrap">${item.statusName}</td>
                <td nowrap="nowrap">
<!-- -->
               
                	
                	
                	<c:if test="${item.status==0}">
                		<ct:display model="shop_user_list" btn="active_account_btn">
                			<a href="#" onclick="dealInfo('确定要激活账号吗？','./modifyUser?id=${item.id}&status=${item.status}');">
             激活账号</a>&nbsp;&nbsp;
                   	</ct:display>
                   </c:if>
                   <c:if test="${item.status==1}">
                 	<ct:display model="shop_user_list" btn="freeze_account_btn">
                   				<a href="#" onclick="dealInfo('确定要冻结账号吗？','./modifyUser?id=${item.id}&status=${item.status}');">
                       
                冻结账号</a>&nbsp;&nbsp;
                   	</ct:display>
                   		 </c:if>
                   		 
                   		    	<ct:display model="shop_user_list" btn="reset_pwd">
                			<a href="#" onclick="dealInfo('确定重置密码？,重置后，初始密码为：abc12#，  请谨记，重置后请尽快修改密码','./modifyUserLogin?id=${item.id}');">
             重置密码</a>&nbsp;&nbsp;
                   	</ct:display>
                   	
                   	
                   	   <ct:display model="shop_user_list" btn="mod_pwd">
                   	<a href="modifyUserPwd?id=${item.id}">修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>
        <ht:page pageData="${userPage}" />
        </c:if>
        <c:if test="${empty userPage.data}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>
</div>
</body>
</html>