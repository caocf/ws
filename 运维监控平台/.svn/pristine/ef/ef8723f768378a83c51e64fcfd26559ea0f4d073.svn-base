<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商户统计" />
<style type="text/css">  
a{  
    color: #FFFF99;  
    text-decoration: none;  
}a:hover{  
    color: #FFFFFF;  
    text-decoration: underline;  
}  
     
#nav{  
    padding: 10px 10px 0;  
    font-size: 12px;  
    font-weight: bold;  
    margin: 1em 0 0;  
    list-style:none;  
}.bi{  
    position: relative;  
    z-index: 0;  
}.bi:hover{  
    z-index: 99;  
}.bi:hover span{  
    visibility: visible;  
    top: 0;  
    left: 0;  
    cursor: pointer;  
}.bi span{  
    position: absolute;  
    left: -999em;  
    visibility: hidden;  
}#nav li a,.bi:hover span{  
    line-height: 20px;  
    text-decoration: none;  
    background: #DDDDDD;  
    color: #666666;  
    display: block;  
    width: 80px;  
    text-align: center;  
}#nav li a:hover,.bi:hover span{  
    color: #FFFFFF;  
    background: #DC4E1B;  
}.bi:hover span{  
    padding-top: 2px;  
}  
</style>  

<%@ include file="../includes/t.jsp"%>
 <%if(request.getAttribute("msg")!=null){
	  %>
		<script type="text/javascript">
			alert("<%=request.getAttribute("msg")%>");
		</script>  
	  <% 
   }
%>
<script type="text/javascript" src="${ctx }/static/js/datepicker/WdatePicker.js" ></script>
<link href="${ctx }/static/js/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<body>
<form id="searchForm" name="searchForm" method="post">
<table style="width:800px; text-align:left; padding:0; margin:0;">
	<tr>
		<td width="80px"><label class="control-label" for="itemNameStr" style="margin-top:18px;">开始时间：</label></td>
		<td>
			<div class="controls controls-row">
				<input type="text" id="dateStr" name="dateStr" class="date required" class="txt Wdate required" width="185px;" style="margin-top:9px;"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" readonly="readonly" value="${dateStr}"/>
				<input type="hidden" name="_dateStr" value="${dateStr}"> 
			</div>	
		</td>
		<td width="80px"><label class="control-label" for="itemNameStr" style="margin-top:18px;" >结束时间：</label></td>
		<td>
			<div class="controls controls-row">
				<input type="text" id="dateEnd" name="dateEnd" class="date required" class="txt Wdate required" width="185px;" style="margin-top:9px;"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" readonly="readonly" value="${dateEnd}"/>
				<input type="hidden" name="_dateEnd" value="${dateEnd}"> 
			</div>		
		</td>
		<td>
			<select id="searchType" name="status">
			<option value="0" <c:if test="${status!='1'&&status!='2' }" >selected="selected"</c:if>>---请选择状态---</option>
			<option value="1" <c:if test="${status=='1' }" >selected="selected"</c:if>>未处理</option>
			<option value="2" <c:if test="${status=='2' }" >selected="selected"</c:if>>已处理</option>
			</select>
		</td>
		<td>
			<button name="btnQuery" class="btn btn-primary" style="margin-top:0px;" type="button" onclick="toSearch();">
				<i class="icon16 i-search"></i> 查询
			</button>
		</td>
	</tr>
</table>
</form>
<div id="container">
<c:if test="${empty monitorPage.data}">
            <div class="alert alert-info">
                <strong>
                    <i class="icon24 i-info"></i>
                    当前没有相关记录！
                </strong>
            </div>
</c:if>
<c:if test="${!empty monitorPage}" >
<table border="1">
<tbody>
	<tr style="background-color: rgb(153, 153, 153);">
		<td width="170">告警时间</td>
		<td width="170">发启人</td>
		<td width="170">平台名称</td>
		<td width="170">工单级别</td>
		<td width="170">工单内容</td>
		<td width="170">当前状态</td>
		<td width="170">操作</td>
	</tr>
	
	<c:forEach var="monitor" items="${monitorPage.data}" varStatus="status">
		<tr <c:if test="${status.index%2==1 }" >style="background-color: rgb(217, 221, 178);"</c:if>>
			<td>${monitor.monitor_time }</td>
			<td>
				<c:if test="${monitor.plat_name==1}">方正</c:if>
				<c:if test="${monitor.plat_name==2}">高阳</c:if>
				<c:if test="${monitor.plat_name==3}">宽连</c:if>
			</td>
			<td>${monitor.temp_name }</td>
			<td>${monitor.monitor_level }</td>
			<td>
				<a href="javascript:void(0);" onmouseout="javascript:hideSpan('spanName${monitor.seq_id}');" 
				onmouseover="javascript:showSpan('spanName${monitor.seq_id}');">
				<p style="width: 170px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
				${monitor.monitor_content }</p></a>
				<span id="spanName${monitor.seq_id }" onmouseout="this.style.display='none';" 
				style="padding: 3px; text-align: left; display: none; position: absolute; word-break: break-all; width: 300px; background-color: rgb(255, 255, 225);">
				${monitor.monitor_content }</span>
			</td>
			<td>
				<c:if test="${monitor.status==1}"><span style="color:red;" >未处理</span></c:if>
				<c:if test="${monitor.status==2}"><span style="color:blue;" >已处理</span></c:if>
			</td>
			<td>${monitor.txt }</td>
		</tr>
	</c:forEach>
	
</tbody>
</table>
	<ht:page pageData="${monitorPage}" />
</c:if>
</div>
</body>
<script type="text/javascript">
	function showSpan(id){
		var span = document.getElementById(id);
		span.style.display="block";
	}
	function hideSpan(id){
		var span = document.getElementById(id);
		span.style.display="none";
	}
</script>
<script type="text/javascript">
var second=6000; //间隔时间1分钟
var Interval_control;
var value;
// ajax 提交曲线图
$(function () {
	var doPush = "<%=request.getAttribute("doPush")%>";
	if(doPush=="null"||doPush==2){
		scroll();
		Interval_control = setInterval(scroll, second);
	}
  function scroll(){
		$.ajax({
			url: "${ctx}/monitor/list",
			type: 'post',
			dataType: 'json',
			data: {},
			success:function(response){
				if(response==null){
					alert("请求成功，返回对象null！");
				}
				else{
					drawTable(response);
				}
			},
			error:function(){
				//alert("请求失败！");
			}
		});
	}
 });

	function drawTable(response){
		var div = document.getElementById("container");
		div.innerHTML="";
	    var table = document.createElement("table");
	    table.setAttribute("border","1"); 
	    var row2 = table.insertRow(-1);
		row2.style.backgroundColor = "#999999"; 
	    var cell2 = row2.insertCell(-1);
	    cell2.width = "170";
	    cell2.innerHTML="告警时间"; 
	    cell2 = row2.insertCell(-1);
	    cell2.width = "170";
	    cell2.innerHTML="发启人"; 
	    cell2 = row2.insertCell(-1);
	    cell2.width = "170";
	    cell2.innerHTML="平台名称"; 
	    cell2 = row2.insertCell(-1);
	    cell2.width = "170";
	    cell2.innerHTML="工单级别";
	    cell2 = row2.insertCell(-1);
	    cell2.width = "170";
	    cell2.innerHTML="工单内容";
	    cell2 = row2.insertCell(-1);
	    cell2.width = "170";
	    cell2.innerHTML="当前状态";
	    cell2 = row2.insertCell(-1);
	    cell2.width = "170";
	    cell2.innerHTML="操作";
	    
	    if(response.length>0)
		for(var i=0;i<response.length;i++){
			var row = table.insertRow(-1);
			if(i%2==1)
				row.style.backgroundColor="rgb(217, 221, 178)"

			cell = row.insertCell(-1);
			cell.innerHTML=response[i].monitor_time;
			cell = row.insertCell(-1);
			cell.innerHTML=response[i].temp_name;
		    cell = row.insertCell(-1);
		    var platName = response[i].platName;
		    if(platName=='1')
		    	platName = "方正";
	    	if(platName=='2')
	    		platName = '高阳';
	    	if(platName=='3')
	    		platName = '宽连';
		    cell.innerHTML=platName;
		    cell = row.insertCell(-1);
		    cell.innerHTML=response[i].monitorLevel;					
		    cell = row.insertCell(-1);
		    cell.innerHTML="<p style=\"width: 170px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;\">"+response[i].monitor_content+"</["; 
		    cell = row.insertCell(-1);
		    var status = response[i].status;
		    if(status =='1')
	    		status = "<span style=\"color:red;\" >未处理</span>";
		   	if(status =='2')
		   		status = "<span style=\"color:blue;\" >已处理</span>";
		    cell.innerHTML=status;
		    cell = row.insertCell(-1);
		    cell.innerHTML=response[i].txt; 		    
		}
			
	    div.appendChild(table); 
	}
	
	function toSearch(){
		clearInterval(Interval_control); 
		var frm = document.getElementById("searchForm");
		frm.action = "${ctx}/monitor/search";
		frm.submit();
	}
</script>
<%@ include file="../includes/chartControl.jsp"%>
 