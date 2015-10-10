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
    </head>
<body>
<br/>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="groupItems" method="post">
    	<input type="hidden" name="storeId" value="${param.storeId}"  />
    	<input type="hidden" name="itemMode" value="${param.itemMode}"  />
    	<input type="hidden" name="saleAreaCode" value="${param.saleAreaCode}"  />
        <table>
            <tr>
            	 <td>商品编号：</td>
					<td><input type="text" name="id" value="${param.id}" class="txt validate-number" style="width:60px" /></td>
                <td>商品名称：</td>
					<td><input type="text" name="name" id="name" value="${param.name}"
						class="txt" style="width:120px" /></td>
					<td width="70">创建时间：</td>
					<td width="300"><input type="text" id="inputStartTime"
						name="inputStartTime" value="${param.saleStartTime}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'saleStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
						<input type="hidden" name="saleStartTime" id="saleStartTime"
						value="${param.saleStartTime}" /> 至 <input type="text"
						id="inputEndTime" name="inputEndTime"
						value="${param.saleStopTime}"  class="txt Wdate"
						readOnly
						onclick="WdatePicker({vel:'saleStopTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
						<input type="hidden" name="saleStopTime" id="saleStopTime"
						value="${param.saleStopTime}" />
					</td>
                <td>
                    <a class="btn_blue" id="serche" name="serche" href="javascript:void(0);"><span>查询</span></a>
                </td>
            </tr>
        </table>
    </form>
</div>

<div style="float:left;width:100%; margin:0px 37px 20px 37px;">
      	 	<div ><em>选择商品：</em>  <span id="message_box"><c:if test="${not empty itemPage}">
      	 		共找到${itemPage.recordCount}个符合条件商品
      	 		<c:if test="${itemPage.pageCount > 1 }">，商品过多,请调整搜索条件重新搜索</c:if></span>
      	 	</c:if></div>	
      	 	<div id="left" style="float:left;">
      	 		<select id="first" name="first" size="15" multiple="multiple" class="td3" id="first">
      	 			<c:if test="${not empty itemPage}">
			 		<c:forEach items="${itemPage.data}" var="item">
			 		<option value="${item.id }" shopPrice="${item.shopPrice }" stockNum="${item.stockNum }">${item.name}</option>
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
		   			<c:forEach items="${rightItemlList}" var="item">
				  	<option value="${item.id}" shopPrice="${item.shopPrice }" stockNum="${item.stockNum }">${item.name}</option>
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
		   jQuery.getJSON( "selectItemAjaxQuery",$("#queryForm").serialize(),  function(json){
    		    if(json.data){
    		       $("#first").empty();
    		       $("#message_box").html("	共找到"+json.recordCount+"个符合条件商品");  
    		       if(json.pageCount > 1){
    		         $("#message_box").html(  $("#message_box").html() + ",商品过多,请调整搜索条件重新搜索");
    		       }
	    		    var items = json.data;
	    		    for(var i=0;i<items.length;i++){
	    		    	$("#first").append("<option value='"+items[i].id+"' shopPrice='" + items[i].shopPrice + "' stockNum='" + items[i].stockNum + "'>"+items[i].name+"</option>"); 
			    	 }
		    	 }
      		});
       });
});
  
</script>
       


</body>
</html>