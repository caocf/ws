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
		$(".item_reply").click(function() {
			var id = $(this).attr("id");
			showDialog("回复", 
			"replyPage?id="+id,function(doc){
				doc.getElementById('fm').submit();
				doc.submited=true;
				simpleAlert('操作成功',function() {
					window.location.reload();
				});
			},
			{"Width":600,"Height":270,"ShowMessageRow":false});
			
	});
			
			
			

		
	});
</script>
    </head>
<body>
<br/>
<div id="search-menu">
    <ul>
     <ht:menu-btn type="search"/>
        <ht:menu-btn text="商品评论" url="/comment/item/list"/>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form:form name="queryForm" id="queryForm"  action="list" method="get" commandName="itemComment">
        <table >
            <tr>
                <td width="70">评论用户:</td>
                <td width="130"><input type="text" name="nickname" value="${itemComment.nickname}" class="txt" style="width:120px"/></td>
                <td width="70">内容类型：</td>
                <td width="80">
                    <select name="type">
                        <option value="">--请选择--</option>
                        <c:forEach items="${typeMap }" var="item">
                        	    <option value="${item.key }" <c:if test="${itemComment.type == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                </td>
            
              <td width="70"> 评论时间：</td>
                <td width="300" colspan="3">
                    <input type="text" id="inputStartTime" name="inputStartTime" value="${param.inputStartTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'startTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
                    <input type="hidden" name="startTime" id="startTime" value="${param.startTime}"/>
                    至
                    <input type="text" id="inputEndTime" name="inputEndTime" value="${param.inputEndTime}" class="txt Wdate"
                           readOnly onfocus="WdatePicker({vel:'endTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
                    <input type="hidden" name="endTime" id="endTime" value="${param.endTime}" />
                </td>
             </tr>
             <tr>   
               <td width="70">评论商品：</td>
                <td><input type="text" name="name" value="${itemComment.name}" class="txt" style="width:120px"/></td>
                <td colspan="2">&nbsp;
                    <ct:btn type="search" />
                </td>
            </tr>
        </table>
    </form:form>
</div>
<div class="container">
    <br/>
    <h3>评论列表</h3>
    <div class="mainbox">
        <c:if test="${not empty commentPage}">
        <table class="datalist fixwidth" >
       		<tr>
        		<td colspan="9" align="left">
        			<ct:display model="item_comment_list" btn="audit_btn">
        				<ht:table-action-btn  text="审核通过" url="/comment/item/auditAllPage?status=1"/>
        			</ct:display>
        			<ct:display model="item_comment_list" btn="audit_btn">
        				<ht:table-action-btn  text="审核驳回" url="/comment/item/auditAllPage?status=2"/>
        			</ct:display>
        		</td>
        	</tr>
            <tr>
            	<th nowrap="nowrap" width="80"><input type="checkbox" class="checkall"/>商品评论编号</th>
                <th nowrap="nowrap" width="80">评论商品</th>
                <th nowrap="nowrap" width="200">内容</th>
                <th nowrap="nowrap" width="150">评论时间</th>
                <th nowrap="nowrap" width="50">类型</th>
                <th nowrap="nowrap" width="100">咨询类型</th>
                <th nowrap="nowrap" width="100">评论状态</th>
                <th nowrap="nowrap" width="150">回复时间</th>
                <th nowrap="nowrap">操作</th>
            </tr>

            <c:forEach items="${commentPage.data}" var="item">
            <tr>
            	<td  nowrap="nowrap"><input type="checkbox"  name="id" value="${item.id }"/>${item.id }</td>
                <td  nowrap="nowrap" class="ellipsis">	${item.name } </td>
                <td  nowrap="nowrap" class="ellipsis">	${item.content } </td>
                <td  nowrap="nowrap" ><ct:time source="${item.updateTime}"/></td>
                <td  nowrap="nowrap">${item.typeName}</td>
                <td  nowrap="nowrap">${item.questionTypeName}</td>
                <td  nowrap="nowrap"> ${item.statusName} </td>
                <td  nowrap="nowrap" title="${item.replyContent}"> <ct:time source="${item.replyUpdateTime}" /> </td>
                <td  nowrap="nowrap"> 
                <ct:display model="item_comment_list" btn="reply_btn">
                 <a href="replyPage?id=${item.id}">回复</a>&nbsp;&nbsp;
				</ct:display> 
				<c:if test="${item.status eq 0}">		
				<ct:display model="item_comment_list" btn="audit_btn">
					<a href="auditPage?id=${item.id}">审核</a>&nbsp;&nbsp;
				</ct:display>
						</c:if>			
                 </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${commentPage}" />

        </c:if>
        <c:if test="${empty commentPage}">
        <div class="note">
            <p class="i">目前没有相关记录!</p>
        </div>
        </c:if>
    </div>

</div>

</body>
</html>