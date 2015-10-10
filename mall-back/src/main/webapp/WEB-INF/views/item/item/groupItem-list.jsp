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
<div id="search-menu">
    <ul>
        <ht:menu-btn type="search"/>
    </ul>
    <br style="clear: left" />
</div>
<div class="queryContainer" >
    <form name="queryForm" id="queryForm" action="groupItems" method="post">
        <table>
            <tr>
                <td>商品名称：</td>
					<td><input type="text" name="name" id="name" value="${param.name}"
						class="txt" style="width:120px" /></td>
					<td width="70">创建时间：</td>
					<td width="300"><input type="text" id="inputStartTime"
						name="inputStartTime" value="${param.inputStartTime}"
						class="txt Wdate" readOnly
						onclick="WdatePicker({vel:'queryStartTime',realDateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'inputEndTime\')||\'2020-10-01\'}'})" />
						<input type="hidden" name="queryStartTime" id="queryStartTime"
						value="${param.queryStartTime}" /> 至 <input type="text"
						id="inputEndTime" name="inputEndTime"
						value="${param.inputEndTime}" class="Wdate" class="txt Wdate"
						readOnly
						onclick="WdatePicker({vel:'queryEndTime',realDateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'inputStartTime\')}',maxDate:'2020-10-01'})" />
						<input type="hidden" name="queryEndTime" id="queryEndTime"
						value="${param.queryEndTime}" />
					</td>
                <td colspan="4">&nbsp;
                    <a class="btn_blue" id="serche" name="serche" href="#"><span>查询</span></a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div style="border:1px dashed #E6E6E6;margin:50px 0px 0px 50px; width:750px; height:200px; background-color:#ffffff;">
<table width="750" border='0' align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="350" align="center">
    	<!--multiple="multiple" 能同时选择多个   size="10"  确定下拉选的长度-->
		<select id="first" name="first" size="10" multiple="multiple" class="td3" id="first">
			 <c:forEach items="${itemList}" var="item">
			 <option value="${item.id }">${item.name}</option>
		  </c:forEach>
		</select>    
	</td>
    <td width="50" valign="center">
        <a class="btn_blue" id="add" name="add" href="#"><span>---></span></a>
        <a class="btn_blue" id="add_all" name="add_all" href="#"><span>==></span></a>
        <a class="btn_blue" id="remove" name="remove" href="#"><span><---</span></a>
        <a class="btn_blue" id="remove_all" name="remove_all" href="#"><span><==</span></a>
        </td>
    <td width="350" align="center">
	  <select id="second" name="second" size="10" multiple="multiple" class="td3" id="second">
	   <c:forEach items="${rightItemlList}" var="item">
			  <option value="${item.id}">${item.name}</option>
		</c:forEach>
      </select>
	</td>
	</tr>
</table>
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
	        var ids="";
	        $("#second").each(function(){
		        $(this).children("option").each(function(){
		        ids=ids+"'"+$(this).val()+"',";
		        });
   			 });
   			  ids=ids.substring(0, ids.length-1);//每一个option
	       var name=$("#name").val();
	       var beginTime=$("#queryStartTime").val();
	       var endTime=$("#queryEndTime").val();
        		 $.ajax({  
        		        type: "POST",  
		    		    url : "groupItemsAjax",   
		    		    cache : false, 
		    		    dataType : "json",  
		    		    data: {name:name,beginTime:beginTime,endTime:endTime},
		    		    success : function(r){
		    		    $("#first").empty();   
		    		    for(var i=0;i<r.length;i++){
		    		    $("#first").append("<option value='"+r[i].id+"'>"+r[i].name+"</option>"); 
		    		    }
		        }});
        });
});
  
</script>
       


</body>
</html>