<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cplatform.mall.dc.utils.DateTag"%>
<%@ page import="com.cplatform.mall.dc.utils.AreaSelectTag"%>
<%@ include file="../../includes/if.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>My JSP 'taskList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		
	<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/artDialog.js?skin=blue"></script>	
	<script type="text/javascript">
		//去掉字符串头尾空格   
		function trim(str) {   
		    return str.replace(/(^\s*)|(\s*$)/g, "");   
		}
		var flag = true;
		
		function getQuery(){
			jQuery("#ol_in").text("");
			var html = [];
			var shop_name = jQuery("#shop_name").val();
			var area_code = $('#area').find('option:selected').val();
			$.ajax({
				type:"POST",
				url:"${ctx}/analysis/position/queryShops",
				dataType : "json",
				data:{
					shop_name:shop_name,
					area_code:area_code
				},
				success:function(data){
					for(var i=0;i<data.length;i++){
						
						html.push("<li id="+data[i][0]+"_in onclick='javascript:selectOptions(this)' >"+data[i][1]+"</li>");
					}
					jQuery("#ol_in").append(html.join(''));
				}
			})
		}

		function selectOptions(shopVal){
			
			var id = shopVal.id;
		
			var idList = jQuery("#idList").val();
			if(idList==null||idList==""){
					jQuery("#"+id).css("background","#4595FF");
					jQuery("#idList").val(id);
			}else{
				if(idList==id){
					jQuery("#"+idList).css("background","white");
					jQuery("#idList").val("");
				}else{
					jQuery("#"+idList).css("background","white");
					jQuery("#"+id).css("background","#4595FF");
					jQuery("#idList").val(id);
				}
			}
		}
			

		function getIn(){
			var idList = jQuery("#idList").val();
			if(idList==null||idList==""){
				art.dialog({
				    time: 1.5,
				    width:'180px',
				    height:80,
				    icon:'warning',
				    content: '请选择要导入的商户'
				});
				return false;
			}else{
				
				var shopName = jQuery("#"+idList).text();
				var html = [];
				var idVal = idList.split("_")[0];
				var symbol = true;
				var liList = document.getElementById("ol_out").getElementsByTagName("li");
				for(var i = 0; i < liList.length; i ++){
					    var varStr=liList[i].id;
					    var shopId = varStr.split("_")[0]
						if(idVal==shopId){
							art.dialog({
							    time: 1.5,
							    width:'180px',
							    height:80,
							    icon:'warning',
							    content: '该商户已被选中，请选择其他商户！'
							});
							symbol = false;
						}
				}
				if(symbol){
					html.push("<li id="+idList.split("_")[0]+"_out onclick='javascript:selectOptions(this)' >"+shopName+"</li>");
					jQuery("#"+idList).remove();
					jQuery("#idList").val("");
					jQuery("#ol_out").append(html.join(''));
				}
			}
		}

		function getOut(){
			var idList = jQuery("#idList").val();
			if(idList==null||idList==""){
				art.dialog({
				    time: 1.5,
				    width:'180px',
				    height:80,
				    icon:'warning',
				    content: '请选择要移除的商户'
				});
				return false;
			}else{

				
				var shopName = jQuery("#"+idList).text();
				var html = [];
				var idVal = idList.split("_")[0];
				var symbol = true;
				var liList = document.getElementById("ol_in").getElementsByTagName("li");
				for(var i = 0; i < liList.length; i ++){
					    var varStr=liList[i].id;
					    var shopId = varStr.split("_")[0]
						if(idVal==shopId){
							art.dialog({
							    time: 1.5,
							    width:'180px',
							    height:80,
							    icon:'warning',
							    content: '该商户已被选中，请选择其他商户！'
							});
							symbol = false;
						}
				}
				if(symbol){
					html.push("<li id="+idList.split("_")[0]+"_in onclick='javascript:selectOptions(this)' >"+shopName+"</li>");
					jQuery("#"+idList).remove();
					jQuery("#idList").val("");
					jQuery("#ol_in").append(html.join(''));
				}
			}
		}

		function validateJs(obj,id){
			var inputVal = obj.value;
			var arpu = document.getElementById('arpu_type').value;
			var coin = document.getElementById('coin_id').value;
			var score = document.getElementById("score_id").value;
			if(id=='task_name'){
				if(inputVal==null||trim(inputVal)==""){
					art.dialog({
					    time: 1,
					    width:'120px',
					    height:120,
					    icon:'warning',
					    content: '方案名称不能为空'
					});
				}
				flag=false;
			}else{
				if(isNaN(trim(inputVal))||(trim(inputVal)-0)<0){
					art.dialog({
							    time: 1,
							    width:'120px',
							    height:120,
							    icon:'warning',
							    content: '请输入大于0的数字'
							});
					flag=false;
				}else if(id=='age_min'||id=='age_max'){
					var ageVal = $("#"+id).val();
					
					if(ageVal!=null&&(trim(ageVal)-0)>100){
						art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: '年龄不能超过100岁'
						});
						flag=false;
					}

					if(id=='age_max'){
						if(trim($("#age_max").val())-0 < (trim($("#age_min").val())-0)){
							art.dialog({
							    time: 1,
							    width:'120px',
							    height:120,
							    icon:'warning',
							    content: '年龄的最大值和最小值输入有错误'
							});			
						flag=false;
						}
					}
			}else if($("#rate_min").val()!=null&&trim($("#rate_min").val())!=""&&$("#rate_max").val()!=null&&trim($("#rate_max").val())!=""&&(trim($("#rate_min").val())-0 > trim($("#rate_max").val())-0)){
					art.dialog({
					    time: 1,
					    width:'120px',
					    height:120,
					    icon:'warning',
					    content: '出现频次的最大值和最小值输入有错误'
					});		
				flag=false;
				
			}else if(obj.value!=null&&trim(obj.value)!=""&&(trim(obj.value)-0)==0&&arpu=='<'){
					art.dialog({
					    time: 1,
					    width:'120px',
					    height:120,
					    icon:'warning',
					    content: 'ARPU的值不能小于0'
					});		
				flag=false;
				
			}else if(obj.value!=null&&trim(obj.value)!=""&&(trim(obj.value)-0)==0&&score=='<'){
				art.dialog({
				    time: 1,
				    width:'120px',
				    height:120,
				    icon:'warning',
				    content: '积分的值不能小于0'
				});		
			flag=false;
			
			}else if(obj.value!=null&&trim(obj.value)!=""&&(trim(obj.value)-0)==0&&coin=='<'){
				art.dialog({
				    time: 1,
				    width:'120px',
				    height:120,
				    icon:'warning',
				    content: '商城币的值不能小于0'
				});		
			flag=false;
			
			}else{
				flag = true;
				return true;
				
			}
		}
	}

		function changeSelect(type,id){
			var symbolType = $("#type").find("option:selected").val();
			var inVal = $("#id").val()-0;
			if(symbolType=="<"&&inVal=="0"){
				flag = false;
				return false;
			}else{
				flag = true;
				return true;
			}
		}

		function CheckForm(){
			var task_name = $("#task_name").val();
			var area = $("#area").find('option:selected').val();
			var year = $("#year").find('option:selected').val();
			var month = $("#month").find('option:selected').val();
			var dateType = $('input[name="dateType"]:checked').val();
			var selectedDate = $("#date").val();
			$("#area_id").val(area);
			$("#year_id").val(year);
			$("#month_id").val(month);
			$("#selectedDate").val(selectedDate);
			
			var shopList="";
			var liList = document.getElementById("ol_out").getElementsByTagName("li");
			for(var i = 0; i < liList.length; i ++){
				    var varStr=liList[i].id;
				    var shopId = varStr.split("_")[0]
					shopList+=shopId;
				   if(i<liList.length-1){
						shopList+="_";
				   }
			}
			
			$("#shopList").val(shopList);

			
			if(task_name==null||trim(task_name)==""){
				art.dialog({
				    time: 1,
				    width:'120px',
				    height:120,
				    icon:'warning',
				    content: '方案名称不能为空'
				});	
				
				return false;	
			}else if(flag==false){
				art.dialog({
				    time: 1,
				    width:'120px',
				    height:120,
				    icon:'warning',
				    content: '输入数据有误，请重新输入正确数据'
				});		
				return false;
			}else if(flag==true){
				$("#flag").val("1");
				window.parent.loadPage();
				return true;
				//window.location.href="${ctx}/analysis/position/";
			}	
		}	

		function operator(id,type){
		  	if(type==1){
				art.dialog({
				    content: '确定要执行取消操作？',
				    ok: function () {
						$.ajax({ type:"POST",
							 url : '${ctx}/analysis/position/doOperator',
						    dataType : "json",
						    data:{
						    	id:id,
						    	type:1
							},
						    success:function(msg) {
						        if (msg=='1') {
						        	 $("#status_"+id).text("");
								     $("#status_"+id).text("用户已取消");
								     $("#show_"+id).hide();
								     $("#curr_"+id).hide();
								     $("#hidden_"+id).show();
						        	var dialog = art.dialog({
						        	    content: '操作成功',
						        	    icon: 'succeed',
						        	    ok: function(){
						        	        return true;
						        	    }
						        	});
						        }else {
						        	var dialog = art.dialog({
						        	    content: '操作失败',
						        	    icon: 'error',
						        	    ok: function(){
						        	        return true;
						        	    }
						        	});
						        }
						    }
						});
				    	return true;
				    },
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
			}else if(type==2){
				art.dialog({
				    content: '确定要删除该条记录？',
				    ok: function () {
						$.ajax({ type:"POST",
							 url : '${ctx}/analysis/position/doOperator',
							 dataType : "json",
							 data:{
							    id:id,
							    type:2
							 },
						    success:function(msg) {
						        if (msg=='1') {
						        	$("tr[id="+id+"]").remove();
						        	var dialog = art.dialog({
						        	    content: '删除成功',
						        	    icon: 'succeed',
						        	    ok: function(){
						        			window.parent.delTask(id);
						        	        return true;
						        	    }
						        	});
						        }else {
						        	var dialog = art.dialog({
						        	    content: '删除失败',
						        	    icon: 'error',
						        	    ok: function(){
						        	        return true;
						        	    }
						        	});
						        }
						    }
						});
				    	return true;
				    },
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
			}else if(type==3){
				var myDate = new Date();
				var year = myDate.getFullYear();    
				var mon = myDate.getMonth();   
				if(mon+1<10){
					mon='0'+(mon+1);
				}else{
					mon=mon+1;
				}
				day = myDate.getDate();
				var mytime=myDate.toLocaleTimeString(); 
				var modTime = year+"-"+mon+"-"+day+" "+mytime;
				jQuery.ajax({ 
					 type : "POST",
					 url : '${ctx}/analysis/position/doOperator',
				    dataType : "json",
				    data:{
				    	id:id,
				    	type:3,
				    	modTime:modTime
					},
				    success:function(msg) {
				        if (msg=='1') {
				        	$("#status_"+id).text("");
						     $("#status_"+id).text("提取中");
						     $("#show_"+id).hide();
						     $("#hidden_"+id).hide();
						     $("#curr_"+id).show();
						     $("#time_"+id).text("");
						     $("#time_"+id).text(modTime);
				        	var dialog = art.dialog({
				        	    content: '操作成功',
				        	    icon: 'succeed',
				        	    ok: function(){
				        	        return true;
				        	    }
				        	});
				        }else {
				        	var dialog = art.dialog({
				        	    content: '操作失败',
				        	    icon: 'error',
				        	    ok: function(){
				        	        return true;
				        	    }
				        	});
				        }
				    }
				});
			}else if(type==4){
				$("#td_"+id).show();
				$("#t_"+id).hide();
			}
		}


		function doOperator(id,name){
			var type = 0;
			
			window.parent.document.getElementById('busiFrame').src = "${ctx}/analysis/position/getTaskList?taskId="+id+"&type="+type;
				
		}

		function doBlur(obj,id){
			var itemName = obj.value;
			if(itemName==null||trim(itemName)==""){
				art.dialog({
				    time: 1,
				    width:'120px',
				    height:120,
				    icon:'warning',
				    content: '方案名称不能为空或者是空格'
				});	
				return false;
			}else{
				$("#t_"+id).text("");
				$("#t_"+id).text(obj.value);
				$("#td_"+id).hide();
				$("#t_"+id).show();
				$.ajax({ type:"POST",
				    url : '${ctx}/analysis/position/doOperator',
				    dataType : "json",
				    data:{
				    	id:id,
				    	type:4,
				    	itemName:itemName
					},
				    success:function(msg) {
				        if (msg=='1') {
					        
				        	var dialog = art.dialog({
				        	    content: '操作成功',
				        	    icon: 'succeed',
				        	    ok: function(){
				        			window.parent.modifyTask(id,itemName);
				        	        return true;
				        	    }
				        	});
				        }else {
				        	var dialog = art.dialog({
				        	    content: '操作失败',
				        	    icon: 'error',
				        	    ok: function(){
				        	        return true;
				        	    }
				        	});
				        }
				    }
				});
			}
			
		}


		function doReset(){
			window.parent.loadPage();
		}
	</script>
	
	
  </head>
  
  <body>
  		<div class="daily" id="daily_dxqf">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<div class="tab-pane fade in active" id="home">
					<form name="queryForm" id="queryForm" action="${ctx }/analysis/position/getTaskList" method="post" onsubmit=" return CheckForm();">
							<div class="control-group group-search" style="margin-left: 0px;">
								<div class="controls controls-row">
									<th>
										商户查询:
										<%=AreaSelectTag.getShopTag(request.getAttribute("area_id"))%>
									</th>
									<th>
										&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="shop_name" value="${shop_name }" id="shop_name" />
 									</th> 
 									<th>
										<button class="btn btn-primary" type="button" style="float: inherit;"onclick=getQuery();>
											<i class="icon16 i-search"></i>查询
										</button>
									</th>
								</div>
							</div>
						<div class="control-group group-search" >
							<div class="controls controls-row">
									
											
								<table >
									<tr>
										<td>
											<input type="hidden" id="idList" name="idList"/>
											<input type="hidden" id="shopList" name="shopList"/>
											<input type="hidden" id="area_id" name="area_id"/>
											<input type="hidden" id="year_id" name="year_id"/>
											<input type="hidden" id="month_id" name="month_id"/>
											<input type="hidden" id="selectedDate" name="selectedDate"/>
											<input type="hidden" id="flag" name="flag" value="${flag}"/>
											<input type="hidden" id="page" name="page" value="${page}"/>
										</td>
									</tr>
									<tr>
										<td rowspan="6" colspan="12">
											<div id="shop_list_in" style="width: 280px;height: 100px;overflow: auto;border:1px solid #C9C9C9;">
												<ol id="ol_in">
				  								</ol>
											</div>
										</td>
										<td>
											<button class="btn btn-primary" type="button" onclick=getIn();>
												<i class="icon16 "></i>>>
											</button>
										</td>
										
										<td rowspan="6" colspan="12">
											<div id="shop_list_out" style="width: 280px;height: 100px;overflow: auto;border:1px solid #C9C9C9;">
												<ol id="ol_out">
				  						
				  								</ol>
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<button class="btn btn-primary" type="button" style="float: inherit;"onclick=getOut();>
												<i class="icon16 "></i><<
											</button>
										</td>
									</tr>
								</table>									
							</div>
						</div>
						
						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								
								
								<th>
									套餐品牌:
								</th>
								<select id="user_brand" name="user_brand"
									style="width: 100px; height: 50%;">

									<option value="0" name="user_brand"
										<c:if test="${empty user_brand || user_brand == 0}"> selected="selected"</c:if>>
										不限
									</option>
									<option value="10002" name="user_brand"
										<c:if test="${not empty user_brand && user_brand == 10002}"> selected="selected"</c:if>>
										神州行
									</option>
									<option value="10003" name="brand"
										<c:if test="${not empty user_brand && user_brand == 10003}"> selected="selected"</c:if>>
										动感地带
									</option>
									<option value="10001" name="brand"
										<c:if test="${not empty user_brand && user_brand == 10001}"> selected="selected"</c:if>>
										全球通
									</option>
								</select>
								
								
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别:
								</th>
									<input type="radio" name="sex" value="9"
										<c:if test="${ empty sex || sex == '9'}"> checked="checked"</c:if> />
									不限
								
									<input type="radio" name="sex" value="0"
										<c:if test="${not empty sex && sex == '0'}"> checked="checked"</c:if> />
									男
									
									<input type="radio" name="sex" value="1"
										<c:if test="${not empty sex && sex == '1'}"> checked="checked"</c:if> />
									女
								
									<th>
									　　　　积分：
								</th>
								<select id="score_id" name="scoreType" style="width: 100px; height: 50%;" onchange="changeSelect('score_id','score')">
									<option value="<" name="scoreType" 
									<c:if test="${empty scoreType || scoreType == '<'}"> selected="selected" </c:if>>
										小于
									</option>
									<option value="<=" name="scoreType"
										<c:if test="${not empty scoreType && scoreType == '<='}"> selected="selected"</c:if>>
										小于等于
									</option>
									<option value=">" name="scoreType"
										<c:if test="${not empty scoreType && scoreType == '>'}"> selected="selected"</c:if>>
										大于
									</option>
									<option value=">=" name="scoreType"
										<c:if test="${not empty scoreType && scoreType == '>='}"> selected="selected"</c:if>>
										大于等于
									</option>
								</select>
								<input type="text" id="score" name="score" value="${score}" maxlength="10" onblur="validateJs(this,'score_id');"
									style="width: 70px; height: 40%;">
							</div>
						</div>
						
						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									出现频次:
								</th>
								<th>
									<input type="text" name="rate_min" maxlength="10" onblur="validateJs(this,'rate_min');" id="rate_min"
										value="${rate_min }" style="width: 60px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="rate_max" maxlength="10" onblur="validateJs(this,'rate_max');"
											id="rate_max" value="${rate_max}"
											style="width: 60px; height: 40%;">
								</th>
								<th>
									　　　　商城币：
								</th>
									<select id="coin_id" name="coinType" onchange="changeSelect('coin_id','coin')"
										style="width: 100px; height: 40%;">
										<option value="<" name="
											coinType"
									<c:if test="${empty coinType || coinType == '<'}"> selected="selected"</c:if>>
											小于
										</option>
										<option value="<=" name="coinType"
											<c:if test="${not empty coinType && coinType == '<='}"> selected="selected"</c:if>>
											小于等于
										</option>
										<option value=">" name="coinType"
											<c:if test="${not empty coinType && coinType == '>'}"> selected="selected"</c:if>>
											大于
										</option>
										<option value=">=" name="coinType"
											<c:if test="${not empty coinType && coinType == '>='}"> selected="selected"</c:if>>
											大于等于
										</option>
									</select>
									<input type="text" id="coin" name="coin" value="${coin}" maxlength="10" onblur="validateJs(this,'coin_id');"
										style="width: 70px; height: 40%;">
								<th>
									　　　年龄:
								</th>
								<th>
									<input type="text" name="age_min" maxlength="3" onblur="validateJs(this,'age_min');" id="age_min"
										value="${age_min }" style="width: 60px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="age_max" maxlength="3"  onblur="validateJs(this,'age_max');"
											id="age_max" value="${age_max}"
											style="width: 60px; height: 40%;">
								</th>
							</div>
						</div>
						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<input type="radio" name="dateType" value="0"
									<c:if test="${empty dateType || dateType == '0'}"> checked="checked"</c:if> />
								按日：
								<div id="from" class="input-append date"
									data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
									<input id="date" name="date" type="text" style="width: 100px;" value="${selectedDate}">
									<span class="add-on"> <i class="icon16 i-calendar-4"></i>
									</span>
								</div>
								　　
								 <input type="radio" name="dateType" value="1"
									<c:if test="${not empty dateType && dateType == '1'}"> checked="checked"</c:if> />按月：
								<%=DateTag.getYearTag(request.getAttribute("selectedYear"))%>
								<%=DateTag.getMonthTag(request.getAttribute("selectedMonth"))%>
								
								<th>
									　　　&nbsp;时间段：
								</th>
								<select id="time_type" name="time_type"
									style="width: 100px; height: 50%;">

									<option value="0" name="time_type"
										<c:if test="${empty time_type || time_type == 0}"> selected="selected"</c:if>>
										不限
									</option>
									<option value="10002" name="time_type"
										<c:if test="${not empty time_type && time_type == 10002}"> selected="selected"</c:if>>
										早(8:00-11:00)
									</option>
									<option value="10003" name="time_type"
										<c:if test="${not empty time_type && time_type == 10003}"> selected="selected"</c:if>>
										中(11:00-13:00)
									</option>
									<option value="10001" name="time_type"
										<c:if test="${not empty time_type && time_type == 10001}"> selected="selected"</c:if>>
										晚(18:00-20:00)
									</option>
									<option value="10000" name="time_type"
										<c:if test="${not empty time_type && time_type == 10000}"> selected="selected"</c:if>>
										夜间(20:00-24:00)
									</option>
								</select>
							</div>
						</div>
						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									ARPU值：
								</th>
								<select id="arpu_time" name="arpu_time"
									style="width: 100px; height: 50%;">

									<option value="0" name="arpu_time"
										<c:if test="${empty arpu_time || arpu_time == 0}"> selected="selected"</c:if>>
										上个月
									</option>
									<option value="1" name="arpu_time"
										<c:if test="${not empty arpu_time && arpu_time == 1}"> selected="selected"</c:if>>
										上三个月
									</option>
									
								</select>
								
								<select id="arpu_type" name="arpu_type" style="width: 100px; height: 50%;" onchange="changeSelect('arup_type','arpu')">
									<option value="<" name="arpu_type" 
									<c:if test="${empty arpu_type || arpu_type == '<'}"> selected="selected" </c:if>>
										小于
									</option>
									<option value="<=" name="arpu_type"
										<c:if test="${not empty arpu_type && arpu_type == '<='}"> selected="selected"</c:if>>
										小于等于
									</option>
									<option value=">" name="arpu_type"
										<c:if test="${not empty arpu_type && arpu_type == '>'}"> selected="selected"</c:if>>
										大于
									</option>
									<option value=">=" name="arpu_type"
										<c:if test="${not empty arpu_type && arpu_type == '>='}"> selected="selected"</c:if>>
										大于等于
									</option>
								</select>
								
								<input type="text" id="arpu"  value="${arpu }" style="width: 70px;" maxlength="10" onblur="validateJs(this,'arpu_type');" name="arpu"/> 
							    
							 
							</div>
						</div>
					
						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									方案名称<font style="line-height: 15px;color: red">*</font>：
								</th>
								<th>
									<input type="text" name="task_name" id="task_name" value="${task_name }" maxlength="20">
								</th>
								<th>
									<button class="btn btn-primary"  style="float: inherit;" >
												<i class="icon16 "></i>确定查询并存储方案
							    	</button>
								</th>
								<th>
									<button class="btn btn-primary" type="button" onclick="doReset();" style="float: inherit;" >
												<i class="icon16 "></i>重置条件
							    	</button>
								</th>
							</div>
						</div>
				   </form>
			   </div>
			</div>
		</div>
    	<c:if test="${empty taskList.data}">
			<div class="alert alert-info">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
			</div>
		</c:if>
		<c:if test="${not empty taskList.data}">
		<div class="tab-pane fade in active" id="home">
			<form action="" name="itemForm" id="itemForm"  method="get">
			 <table class="table table-bordered table-hover table-striped">
				<thead>
				<tr>
					<th>
						ID
					</th>
					<th>
						名称
					</th>
					<th>
						更新日期
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
			 </thead>
			
				<tbody>
					<c:forEach items="${taskList.data}" var="item">
						<tr  id="${item[0] }">
							<td class="center vcenter" >
								${item[0]}
							</td>
							<td class="center vcenter" id="td_${item[0] }" style="display: none;">
								<input type="text"  id="itemName" value="${item[1]}" maxlength="10" name= "itemName" onblur="doBlur(this,'${item[0]}');" style="color: red"/>
							</td>
							<td class="center vcenter" id="t_${item[0] }" >
								${item[1]}
							</td>
							<td class="center vcenter" id="time_${item[0] }">
							<ct:time source="${item[2]}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td class="center vcenter" id="status_${item[0] }">
								<c:if test="${item[3]=='0'||item[3]=='1'}">提取中</c:if>
								<c:if test="${item[3]=='4'}">用户已取消</c:if>
								<c:if test="${item[3]=='3'}">任务失败</c:if>
								<c:if test="${item[3]=='2'}">完成</c:if>
							</td>
							<td class="center vcenter" id="show_${item[0] }">
								<c:if test="${item[3]=='0'||item[3]=='1'}">
									<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
									|
									<a href="javascript:operator('${item[0]}',1)">取消</a>
									|
									<a href="javascript:operator('${item[0]}',2)">删除</a>
								</c:if>
								<c:if test="${item[3]=='4'}">
									<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
									|
									<a href="javascript:operator('${item[0]}',3)">再次查询</a>
									|
									<a href="javascript:operator('${item[0]}',4)",>修改名称</a>
									|
									<a href="javascript:operator('${item[0]}',2)">删除</a>
								</c:if>
								<c:if test="${item[3]=='3'}">
									<a href="javascript:operator('${item[0]}',2)">删除</a>
								</c:if>
								<c:if test="${item[3]=='2'}">
									<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
									|
									<a href="javascript:operator('${item[0]}',3)">再次查询</a>
									|
									<a href="javascript:operator('${item[0]}',4)",>修改名称</a>
									|
									<a target="_blank" href="<%=request.getContextPath() %>/downhdfs.zip?filePath=/user/hive/warehouse/t_position_info/tid=${item[0]}&symbol=9"> 导出</a>
									|
									<a href="javascript:operator('${item[0]}',2)">删除</a>
								</c:if>
							</td>
							<td class="center vcenter" id="hidden_${item[0] }" style="display: none;">
								<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
								|
								<a href="javascript:operator('${item[0]}',3)">再次查询</a>
								|
								<a href="javascript:operator('${item[0]}',4)",>修改名称</a>
								|
								<a href="javascript:operator('${item[0]}',2)">删除</a>
							</td>
							<td class="center vcenter" id="curr_${item[0] }" style="display: none;">
								<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
								|
								<a href="javascript:operator('${item[0]}',1)">取消</a>
								|
								<a href="javascript:operator('${item[0]}',2)">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ht:page pageData="${taskList}" />
			</form>
		</div>
	</c:if>
  </body>
</html>
