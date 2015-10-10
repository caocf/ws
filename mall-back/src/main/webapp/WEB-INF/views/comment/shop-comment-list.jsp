<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
        
        <script type="text/javascript">
	$().ready(function() {

		$(".item_audit").click(function() {
			var id = $(this).attr("id");
			showDialog("审核", "auditPage?id="+id,function(doc){
				doc.getElementById('fm').submit();
				doc.submited=true;
				simpleAlert('操作成功',function() {
					window.location.reload();
				});
			},{"Width":600,"Height":270,"ShowMessageRow":false});
			

		});
		
	});
</script>
    </head>
<body>
<br/>
<div id="search-menu">
    <ul>
     <ht:menu-btn type="search"/>
        <ht:menu-btn text="门店评论" url="/comment/shop/list"/>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="list" method="get">
        <table>
            <tr>
                <td>评论门店：</td>
                <td><input type="text" name="name" value="${param.name}" class="txt" style="width:206px"/></td>
                <td>审核类型：</td>
                <td width="200">
                    <select name="status">
                        <option value="">--请选择--</option>
                        <option ${param.status == 0 ?"selected":""} value="0">未审核</option>
                        <option ${param.status == 1 ?"selected":""} value="1">审核通过</option>
                        <option ${param.status == 2 ?"selected":""} value="2">审核驳回</option>
                    </select>
                </td>
                <td colspan="2">&nbsp;
                    <ct:btn type="search" />
                </td>
             </tr>  
             
        </table>
    </form>
</div>
<div class="container">
    <br/>
    <h3>评论列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">
        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">评论门店</th>
                <th nowrap="nowrap">评论时间</th>
                <th nowrap="nowrap">评论内容</th>
                <th nowrap="nowrap">评论状态</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td >	${item.name } </td>
                <td  ><ct:time source="${item.updateTime}"/></td>
                <td >${item.content}</td>
                <td > ${item.statusName} </td>
                <td  > 
               
								<c:if test="${item.status==0}">
									<ct:display model="shop_comment_list" btn="audit_btn">
									<a href="#" onclick="dealInfo('确认审核？','audit?id=${item.id}&status=1');">审核通过</a>
										&nbsp;&nbsp;
									</ct:display>
									<ct:display model="shop_comment_list" btn="audit_btn">
									<a href="#" onclick="dealInfo('确认审核？','audit?id=${item.id}&status=2');">审核驳回</a>
										
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
</html>