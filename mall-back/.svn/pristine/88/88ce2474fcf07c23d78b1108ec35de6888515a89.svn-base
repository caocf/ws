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
    <form name="queryForm" id="queryForm" action="channel" method="get">
        <table>
            <tr>
                <td>门店名称：</td>
                <td><input type="text" id="name" name="name" value="${param.name}" class="txt" style="width:206px"/></td>
                <td colspan="4">&nbsp;
                    <a class="btn_blue" id="serche" name="serche" href="javascript:void(0);"><span>查询</span></a>
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
			 <c:forEach items="${leftShopList}" var="item">
			  <option value="${item.id }">${item.name }</option>
		  </c:forEach>
		</select>    
	</td>
    <td width="50" valign="center">
        <a class="btn_blue" id="add" name="add" href="javascript:void(0);"><span>---></span></a>
        <a class="btn_blue" id="add_all" name="add_all" href="javascript:void(0);"><span>==></span></a>
        <a class="btn_blue" id="remove" name="remove" href="javascript:void(0);"><span><---</span></a>
        <a class="btn_blue" id="remove_all" name="remove_all" href="javascript:void(0);"><span><==</span></a>
        </td>
    <td width="350" align="center">
	  <select id="second" name="second" size="10" multiple="multiple" class="td3" id="second">
	   <c:forEach items="${rightShopList}" var="item">
			  <option value="${item.id }">${item.name }</option>
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
        		 $.ajax({  
        		        type: "POST",  
		    		    url : "ajaxshop",   
		    		    cache : false, 
		    		    dataType : "json",  
		    		    data: {name:name,ids:ids},
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