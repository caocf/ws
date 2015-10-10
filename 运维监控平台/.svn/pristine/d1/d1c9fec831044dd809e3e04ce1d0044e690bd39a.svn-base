<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商户统计" />
<%@ include file="../includes/t.jsp"%>
 <script type="text/javascript" src="${ctx }/static/js/datepicker/WdatePicker.js" ></script>
<link href="${ctx }/static/js/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<body>
<%if(request.getAttribute("msg")!=null){
	  %>
		<script type="text/javascript">
			alert("<%=request.getAttribute("msg")%>");
		</script>  
	  <% 
   }
%>

<form id="addForm" name="addForm" class="form-horizontal"  method="post">
<table style="width:600px; text-align:left; padding:0; margin:0;" border="1">
	<tr>
		<td width="200px"><label class="control-label" for="itemNameStr" style="margin-top:18px;">平台名称：</label></td>
		<td>
		
		<c:forEach var="dcRole" items="${dcRoles}">
			<input type="hidden" id="${dcRole['role_name'] }" value="${dcRole['id'] }"/>
			<c:if test="${dcRole['role_name']=='宽连' }" >
				<input type="hidden" id="plat" name="plat" value="${dcRole['id'] }"/>
			</c:if>
		</c:forEach>
		<c:if test="${_platName=='0'||_platName==1 }" >
			<input type="radio" name="platName" value="1" onclick="getChannel('方正');"/>方正&nbsp;&nbsp;&nbsp;
		</c:if>
		<c:if test="${_platName=='0'||_platName==2 }" >
			<input type="radio" name="platName" value="2" onclick="getChannel('高阳');"/>高阳&nbsp;&nbsp;&nbsp;
		</c:if>
		<c:if test="${_platName=='0'||_platName==3 }" >
			<input type="radio" name="platName" checked="checked" value="3" onclick="getChannel('宽连');"/>宽连
		</c:if>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警级别：</label></td>
		<td>
			<input type="radio" name="monitorLevel" checked="checked" value="1"/>1
			<input type="radio" name="monitorLevel" value="2"/>2
			<input type="radio" name="monitorLevel" value="3"/>3
			<input type="radio" name="monitorLevel" value="4"/>4
			<input type="radio" name="monitorLevel" value="5"/>5
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警类型：</label></td>
		<td>
			<input type="radio" name="monitorType" checked="checked" value="1"/>数据库&nbsp;&nbsp;&nbsp;
			<input type="radio" name="monitorType" value="2"/>系统
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警内容：</label></td>
		<td>
			<textarea rows="5" cols="400" id="monitor_content" name="monitor_content"></textarea>
		</td>
	</tr>
	<tr>	
		<td><label class="control-label" for="itemNameStr" style="margin-top:18px;" >是否展示：</label></td>
		<td>
			<input type="radio" name="show" checked="checked" value="1"/>是&nbsp;&nbsp;&nbsp;
			<input type="radio" name="show" value="2"/>否
		</td>
	</tr>
	<tr>	
		<td ><label class="control-label" for="itemNameStr" style="margin-top:18px;" >是否工单：</label></td>
		<td>
			<input type="radio" name="work_order" checked="checked" value="1"/>是&nbsp;&nbsp;&nbsp;
			<input type="radio" name="work_order" value="2"/>否
		</td>
	</tr>
	<tr>	
		<td width="80px"><label class="control-label" for="itemNameStr" style="margin-top:18px;" >是否短信：</label></td>
		<td>
			<input type="radio" name="send_sms" checked="checked" value="1"/>是&nbsp;&nbsp;&nbsp;
			<input type="radio" name="send_sms" value="2"/>否
		</td>
	</tr>
	<tr>	
		<td ><label class="control-label" for="itemNameStr" style="margin-top:18px;" >告警时间：</label></td>
		<td>
			<div class="controls-row">
				<input type="text" id="dateStr" name="monitor_time" class="date required" class="txt Wdate required" width="185px;" style="margin-top:9px;"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" readonly="readonly"/>
			</div>
		</td>
	</tr>
	<tr>	
		<td ><label class="control-label" for="itemNameStr" style="margin-top:18px;" >指定处理人：</label></td>
		<td>
			<input type="checkbox" id="checkAll" /><span style="color:blue">全选</span>
			<c:forEach var="role" items="${monitorRole }">
				<div>
				<span style="color:red;">${role }:</span>
				<c:forEach var="dcUserMap" items="${monitorUser }">
					<c:if test="${dcUserMap.key==role}" >
						<c:forEach var="dcUser" items="${dcUserMap.value }">
							<input type="checkbox" name="appointNames" value="${dcUser.id }"/>${dcUser.name }&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</c:if>
				</c:forEach>
				</div>
			</c:forEach>
		</td>
	</tr>
	<tr>	
	<td colspan="2">
			<button name="btnQuery" class="btn btn-primary" style="margin-top:0px;margin-left: 180px" type="button" onclick="addMonitor();">
				<i class="icon16 i-search"></i> 添加任务
			</button>
		</td>
	</tr>
</table>
</form>
<script>
	function getChannel(channel){
		var id = document.getElementById(channel).value;
		$("#plat").val(id);
	}
	function addMonitor(){
		var isCheck = false;
		var r=document.getElementsByName("appointNames"); 
		 for(var i=0;i<r.length;i++){
	         if(r[i].checked){
	        	 isCheck = true;
	       }
	    }     
		if(!isCheck){
			alert("请选择指定人！");
			return;
		}
		var monitor_content = document.getElementById("monitor_content");
		if(monitor_content.value==''){
			alert("请输入告警内容！");
			return;
		}
		
		var frm = document.getElementById("addForm");
		frm.action = "${ctx}/monitor/add";
		frm.submit();
	}

	$(function() {
		$("#checkAll").click(function() {
			if(document.getElementById("checkAll").checked){
		     	$('input[name="appointNames"]').parent("span").attr("class","checked");
		     	$('input[name="appointNames"]').attr("checked",this.checked);
			}else{
				$('input[name="appointNames"]').parent("span").removeAttr("class");
				$('input[name="appointNames"]').attr("checked",this.checked);
			}
		});
		var $subBox = $("input[name='appointNames']");
		$subBox.click(function(){
		     $("#checkAll").attr("checked",$subBox.length == $("input[name='appointNames']:checked").length ? true : false);
		 });
	});

</script>
</body> 