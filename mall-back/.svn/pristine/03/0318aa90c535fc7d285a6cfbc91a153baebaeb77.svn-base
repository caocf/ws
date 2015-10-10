<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
        <style type="text/css">
<!--
BODY
{
	font-family:"Courier";
	font-size: 12px;
	margin:0px auto;
	/*不显示 横纵向 滚动条*/
	overflow-x:no;
	overflow-y:no;
}

.noborder {
	border: none;
}
select {
        
        width:330px;
        color:#909993;
}
-->
</style>
  <script type="text/javascript">
  
  
  </script>

    </head>
<body>
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="channel" method="get">
        <table>
            <tr>
                <td>商户名称：</td>
                <td><input type="text" id="name" name="name" value="${param.name}" class="txt" style="width:100px"/>
                		<input type="hidden" id="shopClass" name="shopClass" value="${param.shopClass}" />
                </td>
                <td>商户编号：</td>
					<td><input id="ordernumber" type="text" name="id" value="${param.id}" title="商户编号" class="txt validate-number"  style="width:50px" />
                <td>地市：</td>
                <td><input type="text" id="areaName" name="areaName" value="${param.areaName}" class="txt" style="width:50px"/></td>
                
                <td>商户类型：</td>
                <td>
				<select id="sort" name="sort" style="width:150px" >
				<option value="0" ${param.sort == 0?"selected":"" }>非结算商户</option>
				<option value="1" ${param.sort == 1?"selected":"" }>结算商户</option>
				</select >
                </td>
               
                  <td >  <a class="btn_blue" id="serche" name="serche" href="javascript:void(0);"><span>查询</span></a>
                </td>
            </tr>
        </table>
    </form>
</div>

<div style="float:left;width:100%; margin:0px 37px 20px 37px;">
		<div >
		<em>选择商户：</em>  
		<span id="message_box"></span>
<%-- 		<c:if test="${not empty pageData}"> --%>
<%--       	 		共找到${pageData.recordCount}个符合条件商户 --%>
<%--       	 		<c:if test="${pageData.pageCount > 1 }">，商户过多,请调整搜索条件重新搜索</c:if> --%>
<!--       	 		</span> -->
<%--       	</c:if> --%>
      	 </div>	
         <div id="left" style="float:left;">
      	 		<select id="first" name="first" size="15" multiple="multiple" class="td3" id="first">
      	 			<c:if test="${not empty pageData}">
			 		<c:forEach items="${pageData.data}" var="item">
			 		<option value="${item.id }">${item.name}</option>
		  			</c:forEach>
		  			</c:if>
				</select>    
      	 	</div>
      	 		<div style="float:left;"><input class="btn" id="add" type="button" value="&gt;" alt="添加选中项" style="width:30px; margin:10px 10px;"/><br />
      	 		<input class="btn" id="add_all" type="button" value="&gt;&gt;" alt="添加全部" style="width:30px;margin:0px 10px;"/><br />
      	 		<input class="btn" id="remove" type="button" value="&lt;" alt="删除选中项" style="width:30px;margin:10px 10px;"/><br />
      	 		<input class="btn" id="remove_all" type="button" value="&lt;&lt;" alt="删除全部" style="width:30px;margin:0px 10px;"/>
      	 	</div>
      	 	
      	 	<div id="right">
      	 		<select id="second" name="second" size="15" multiple="multiple" class="td3" id="second">
		   			<c:forEach items="${rightStoreList}" var="item">
				  	<option value="${item.id}">${item.name}</option>
					</c:forEach>
      			</select>
      	 	</div>
 </div>
</body>
<script type="text/javascript">
$().ready(function() {
	$("#add").click(function(){
		$("#first option:selected").appendTo($("#second"));
	});
	$("#add_all").click(function() {
		$("#first option").appendTo($("#second"));
	});
	$("#first").dblclick(function(){
		$("#first option:selected").appendTo($("#second"));
	});
	
	$("#second").dblclick(function(){
		$("#second option:selected").appendTo($("#first"));
	});
	
	$("#remove").click(function() {
		$("#second option:selected").appendTo($("#first"));
	});
	$("#remove_all").click(function() {
		$("#second option").appendTo($("#first"));
	});
	$("#serche").click(function() {
		jQuery.getJSON( "selectAjaxStore",$("#queryForm").serialize(),  function(json){
    		    if(json.data){
    		       $("#first").empty();
    		       $("#message_box").html("	共找到"+json.recordCount+"个符合条件商户");  
    		       if(json.pageCount > 1){
    		         $("#message_box").html(  $("#message_box").html() + ",商户过多,请调整搜索条件重新搜索");
    		       }
	    		    var items = json.data;
	    		    for(var i=0;i<items.length;i++){
	    		    	$("#first").append("<option value='"+items[i].id+"'>"+items[i].name+"</option>"); 
			    	 }
		    	 }
      		});
       });
});
  
</script>

</body>
</html>