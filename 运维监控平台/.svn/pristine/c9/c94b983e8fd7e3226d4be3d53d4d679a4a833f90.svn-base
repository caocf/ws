<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cplatform.mall.dc.utils.AreaSelectTag"%>
<%@ include file="../../includes/if.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-store, must-revalidate"/>
		<script src="http://webapi.amap.com/maps?v=1.2&key=36f5dc55b2d5abad0b1f920f77e884bb"></script>
		<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/artDialog.js?skin=blue"></script>
		<script type="text/javascript">

			//去掉字符串头尾空格   
			function trim(str) {   
			    return str.replace(/(^\s*)|(\s*$)/g, "");   
			}
			var flag = true;
			function showCircle(X,Y,R){
				var circle = Math.floor(R);
				if(circle>3000){
					art.dialog({
					    time: 1,
					    width:'120px',
					    height:120,
					    icon:'warning',
					    content: '所选半径不能超过3km,最大默认为3km'
					});
					document.getElementById("mapX").value=X;
					document.getElementById("mapY").value=Y;
					document.getElementById("mapR").value=3000;
				}else{
					document.getElementById("mapX").value=X;
					document.getElementById("mapY").value=Y;
					document.getElementById("mapR").value=circle;
				}
				
			}

		

			function validateJs(obj,id){
				var inputVal = obj.value;
				var coin = document.getElementById('coin_id').value;
				var score = document.getElementById("score_id").value;
				if(id=='name'){
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
				}else if($("#fee_arpu_mon_l_min").val()!=null&&trim($("#fee_arpu_mon_l_min").val())!=""&&$("#fee_arpu_mon_l_max").val()!=null&&trim($("#fee_arpu_mon_l_max").val())!=""&&(trim($("#fee_arpu_mon_l_min").val())-0 > trim($("#fee_arpu_mon_l_max").val())-0)){
							
							art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: 'ARRPU的最大值和最小值输入有错误'
						});		
					flag=false;
					
				}else if($("#birthday_min").val()!=null&&trim($("#birthday_min").val())!=""&&$("#birthday_max").val()!=null&&trim($("#birthday_max").val())!=""&&(trim($("#birthday_min").val())-0 > trim($("#birthday_max").val())-0)){
					
					art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: '生日的最大值和最小值输入有错误'
						});		
					flag=false;
					
				}else if(id=='age_max'){ 
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
				}else if($("#innet_times_min").val()!=null&&trim($("#innet_times_min").val())!=""&&$("#innet_times_max").val()!=null&&trim($("#innet_times_max").val())!=""&&(trim($("#innet_times_min").val())-0 > trim($("#innet_times_max").val())-0)){
						art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: '入网时长的最大值和最小值输入有错误'
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
					
				}else if($("#fee_call_min").val()!=null&&trim($("#fee_call_min").val())!=""&&$("#fee_call_max").val()!=null&&trim($("#fee_call_max").val())!=""&&(trim($("#fee_call_min").val())-0 > trim($("#fee_call_max").val())-0)){
						art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: '语音通信费的最大值和最小值输入有错误'
						});		
					flag=false;
					
				}else if($("#fee_incr_min").val()!=null&&trim($("#fee_incr_min").val())!=""&&$("#fee_incr_max").val()!=null&&trim($("#fee_incr_max").val())!=""&&(trim($("#fee_incr_min").val())-0 > trim($("#fee_incr_max").val())-0)){
						art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: '增值业务费的最大值和最小值输入有错误'
						});		
					flag=false;
					
				}else if($("#fee_gprs_min").val()!=null&&trim($("#fee_gprs_min").val())!=""&&$("#fee_gprs_max").val()!=null&&trim($("#fee_gprs_max").val())!=""&&(trim($("#fee_gprs_min").val())-0 > trim($("#fee_gprs_max").val())-0)){
						art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: 'GPRS通信费的最大值和最小值输入有错误'
						});		
					flag=false;
					
				}else if($("#fee_roam_min").val()!=null&&trim($("#fee_roam_min").val())!=""&&$("#fee_roam_max").val()!=null&&trim($("#fee_roam_max").val())!=""&&(trim($("#fee_roam_min").val())-0 > trim($("#fee_roam_max").val())-0)){
						art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: '漫游费的最大值和最小值输入有错误'
						});		
					flag=false;
					
				}else if($("#fee_ldc_min").val()!=null&&trim($("#fee_ldc_min").val())!=""&&$("#fee_ldc_max").val()!=null&&trim($("#fee_ldc_max").val())!=""&&(trim($("#fee_ldc_min").val())-0 > trim($("#fee_ldc_max").val())-0)){
						art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: '长途费的最大值和最小值输入有错误'
						});		
					flag=false;
				
				}else if($("#fee_wap_min").val()!=null&&trim($("#fee_wap_min").val())!=""&&$("#fee_wap_max").val()!=null&&trim($("#fee_wap_max").val())!=""&&(trim($("#fee_wap_min").val())-0 > trim($("#fee_wap_max").val())-0)){
						art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: 'WAP通信费的最大值和最小值输入有错误'
						});		
					
					flag=false;
				}else{
					
					flag = true;
					return true;
					
				}
			}
		}


			
			function CheckForm(){
				
				var name = $("#name").val();
				var birthMin=$("#birthday_min").val();
				var birthMax=$("#birthday_max").val();

				if(birthMin!=null&&trim(birthMin)!=""&&birthMax!=null&&trim(birthMax)!=""){
					
					var str1= new Array();  
					var str2= new Array();
					
					str1 = trim(birthMin).split("-");
					str2 = trim(birthMax).split("-")
					var minB = str1[0]+str1[1]+str1[2];
					var maxB = str2[0]+str2[1]+str2[2];
					if((minB-0)>(maxB-0)){
						art.dialog({
						    time: 1,
						    width:'120px',
						    height:120,
						    icon:'warning',
						    content: '生日的最大值和最小值输入有错误'
						});	
						return false;
					}
				}
				
				if(name==null||trim(name)==""){
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
					window.parent.loadPage();
					return true;
					//window.location.href="${ctx}/analysis/screening/getNonTaskList";
				}	
			}	

			function operator(id,type){
			  	if(type==1){
					art.dialog({
					    content: '确定要执行取消操作？',
					    ok: function () {
							$.ajax({ type:"POST",
								 url : '${ctx}/analysis/screening/nonOperator',
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
								 url : '${ctx}/analysis/screening/nonOperator',
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
					$.ajax({ type:"POST",
						 url : '${ctx}/analysis/screening/nonOperator',
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
				var pageNO = $("#pageNO").val();
				window.location.href = "${ctx}/analysis/screening/getNonTaskList?id="+id+"&type="+type+"&page="+pageNO;
					
			}

			function doBlur(obj,id){
				var itemName = obj.value;
				$("#t_"+id).text("");
				$("#t_"+id).text(obj.value);
				$("#td_"+id).hide();
				$("#t_"+id).show();
				$.ajax({ type:"POST",
				    url : '${ctx}/analysis/screening/nonOperator',
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

	 $(document).ready(function(){
		var ismap = $("input[name='ismap']:checked").val();
		var city = $("#area_id").find("option:selected").val();
	  	if(ismap==0||ismap==null){
	  		$("#busiFrame1").attr("height","0");
				$(window.frames["busiFrame1"].document).find("input[type='button']").attr("disabled",true);
		  		$("#mapX").val("");
				$("#mapY").val("");
				$("#mapR").val("");
	  	}else{
	  		$(window.frames["busiFrame1"].document).find("input[type='button']").removeAttr("disabled");
		 }

		if(city==99){
			$("#busiFrame1").attr("height","0");
		  	var input = $("#mapId").find("input[type='radio']");
		    input.attr("disabled","disabled");
		}
      });
			
		function hideInput(){
			$(window.frames["busiFrame1"].document).find("input[type='button']").attr("disabled",true);
				$("#mapX").val("");
				$("#mapY").val("");
				$("#mapR").val("");
				$("#busiFrame1").attr("height","0");
		}
			
		function showInput(){
			$("#busiFrame1").attr("height","350px");
			$(window.frames["busiFrame1"].document).find("input[type='button']").attr("disabled",false);
		}

		function doReset(){
			window.location.href = "${ctx}/analysis/screening/getNonTaskList";
		}

		function loadArea(){
			if($("#area_id").find("option:selected").val()!='99'){
				var input = $("#mapId").find("input[type='radio']");
			    input.attr("disabled",false);
			    var city = $("#area_id").find("option:selected").text();
				window.frames['busiFrame1'].changeMap(city);
			}else if($("#area_id").find("option:selected").val()=='99'){
				$("#locationDiv span").removeClass("checked");
				$("#locationDiv span").eq(0).addClass("checked");
			    $("#mapX").val("");
				$("#mapY").val("");
				$("#mapR").val("");
				var input = $("#mapId").find("input[type='radio']");
				$(window.frames["busiFrame1"].document).find("input[type='button']").attr("disabled",true);
				var input = $("#mapId").find("input[type='radio']");
			    input.attr("disabled","disabled");
			    $("#busiFrame1").attr("src","${ctx}/analysis/screening/loadMap");
			    $("#busiFrame1").attr("height","0");
			}
			
		}
			
</script>
	
	<body>
		<div class="daily" id="daily_dxqf">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<div class="tab-pane fade in active" id="home">
					<form name="queryForm" id="queryForm" action="${ctx}/analysis/screening/getNonTaskList" method="post"  onsubmit=" return CheckForm();">
						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									地区:
								</th>
								<select id="area_id"  name="area_id" onchange="loadArea();" 
									style="width: 100px; height: 50%;">
									<option <c:if test="${empty area_id||area_id==99}"> value="99" selected="selected"</c:if> >全省</option>
									<c:forEach items="${map}" var="area">
										<option value="${area.key}" <c:if test="${not empty area_id && area_id==area.key }">selected="selected"</c:if> >${area.value}</option>
									</c:forEach>
								</select>
								<th>
									用户性别:
								</th>

								<select id="sex_id" name="sex"
									style="width: 100px; height: 50%;">
									<option value="2" name="sex"
										<c:if test="${empty sex || sex == 2}"> selected="selected"</c:if>>
										全部
									</option>
									<option value="0" name="sex"
										<c:if test="${not empty sex && sex == 0}"> selected="selected"</c:if>>
										男
									</option>
									<option value="1" name="sex"
										<c:if test="${not empty sex && sex == 1}"> selected="selected"</c:if>>
										女
									</option>
									<option value="-1" name="sex"
										<c:if test="${not empty sex && sex == -1}"> selected="selected"</c:if>>
										未知
									</option>
								</select>

								<th>
									用户品牌:
								</th>
								<select id="user_brand" name="user_brand"
									style="width: 100px; height: 50%;">

									<option value="0" name="user_brand"
										<c:if test="${empty user_brand || user_brand == 0}"> selected="selected"</c:if>>
										全部
									</option>
									<option value="10001" name="user_brand"
										<c:if test="${not empty user_brand && user_brand == 10001}"> selected="selected"</c:if>>
										全球通
									</option>
									<option value="10002" name="user_brand"
										<c:if test="${not empty user_brand && user_brand == 10002}"> selected="selected"</c:if>>
										神州行
									</option>
									<option value="10003" name="user_brand"
										<c:if test="${not empty user_brand && user_brand == 10003}"> selected="selected"</c:if>>
										动感地带
									</option>
									<option value="10004" name="user_brand"
										<c:if test="${not empty user_brand && user_brand == 10004}"> selected="selected"</c:if>>
										行业卡
									</option>
								</select>

								<th>
									客户类型:
								</th>
								<select id="user_type" name="user_type"
									style="width: 100px; height: 50%;">
									<option value="0" name="user_type"
										<c:if test="${empty user_type || user_type == 0}"> selected="selected"</c:if>>
										全部
									</option>
									<option value="1" name="user_type"
										<c:if test="${not empty user_type && user_type == 1}"> selected="selected"</c:if>>
										普通GSM用户
									</option>
									<option value="2" name="user_type"
										<c:if test="${not empty user_type && user_type == 2}"> selected="selected"</c:if>>
										公免用户
									</option>
									<option value="3" name="user_type"
										<c:if test="${not empty user_type && user_type == 3}"> selected="selected"</c:if>>
										新春卡用户
									</option>
									<option value="4" name="user_type"
										<c:if test="${not empty user_type && user_type == 4}"> selected="selected"</c:if>>
										不停机用户
									</option>
									<option value="5" name="user_type"
										<c:if test="${not empty user_type && user_type == 5}"> selected="selected"</c:if>>
										员工手机
									</option>
									<option value="7" name="user_type"
										<c:if test="${not empty user_type && user_type == 7}"> selected="selected"</c:if>>
										测试手机
									</option>
									<option value="8" name="user_type"
										<c:if test="${not empty user_type && user_type == 8}"> selected="selected"</c:if>>
										无线PBX
									</option>
									<option value="-1" name="user_type"
										<c:if test="${not empty user_type && user_type == -1}"> selected="selected"</c:if>>
										其他
									</option>
								</select>

								<th>
									资费类型:
								</th>
								<select id="prepaytype" name="prepaytype"style="width: 100px; height: 50%;">
									<option value="0" name="prepaytype"
										<c:if test="${empty prepaytype || prepaytype == 0}"> selected="selected"</c:if>>
										全部
									</option>
									<option value="1" name="prepaytype"
										<c:if test="${not empty prepaytype && prepaytype == 1}"> selected="selected"</c:if>>
										后付费
									</option>
									<option value="2" name="prepaytype"
										<c:if test="${not empty prepaytype && prepaytype == 2}"> selected="selected"</c:if>>
										预付费
									</option>
								</select>
							</div>
						</div>

						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									ARPU值:
								</th>
								<th>
									<input type="text"  name="fee_arpu_mon_l_min" id="fee_arpu_mon_l_min" maxlength="10"  onblur="validateJs(this,'fee_arpu_mon_l_min');"
										value="${fee_arpu_mon_l_min }" style="width: 60px; height: 40%;" />
									
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text"  name="fee_arpu_mon_l_max" maxlength="10"  onblur="validateJs(this,'fee_arpu_mon_l_max');"
											id="fee_arpu_mon_l_max" value="${fee_arpu_mon_l_max}"
											style="width: 60px; height: 40%;"/>
								</th>
								<th>
									生日:
								</th>
								<th>
									<div id="from1" class="input-append date" data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
										<input id="birthday_min" name="birthday_min" type="text" readonly="readonly"  value="${birthday_min}" style="width: 100px; height: 40%;">
										<span class="add-on"> <i class="icon16 i-calendar-4"></i></span>
									</div>
									-
									<div id="from2" class="input-append date" data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
										<input id="birthday_max" name="birthday_max" type="text" readonly="readonly"  value="${birthday_max}" style="width: 100px; height: 40%;">
										<span class="add-on"> <i class="icon16 i-calendar-4"></i></span>
									</div>
									
								</th>
								<th>
									年龄:
								</th>
								<th>
									<input type="text" name="age_min" onblur="validateJs(this,'age_min');" id="age_min"
										value="${age_min }" style="width: 60px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="age_max" onblur="validateJs(this,'age_max');"
											id="age_max" value="${age_max}"
											style="width: 60px; height: 40%;">
								</th>
								<th>
									入网时长:
								</th>
								<th>
									<input type="text" name="innet_times_min" id="innet_times_min" maxlength="50" onblur="validateJs(this,'innet_times_min');"
										value="${innet_times_min }" style="width: 70px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="innet_times_max" maxlength="50"  onblur="validateJs(this,'innet_times_max');"
											id="innet_times_max" value="${innet_times_max }"
											style="width: 70px; height: 40%;">
								</th>
							</div>
						</div>

						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									积分：
								</th>
								<select id="score_id" name="scoreType" style="width: 100px; height: 50%;">
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
									<th>
										商城币：
									</th>
									<select id="coin_id" name="coinType"
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
<%--										<th>--%>
<%--											操作系统：--%>
<%--										</th>--%>
<%--										<select id="coin_id" name="coinType" style="width: 100px; height: 40%;">--%>
<%--											<option value="0" name="brand"--%>
<%--											<c:if test="${empty brand || brand == 0}"> selected="selected"</c:if>>--%>
<%--											全部--%>
<%--											</option>--%>
<%--											<option value="1" name="brand"--%>
<%--												<c:if test="${not empty brand && brand == 1}"> selected="selected"</c:if>>--%>
<%--												Android									--%>
<%--											</option>--%>
<%--											<option value="2" name="brand"--%>
<%--												<c:if test="${not empty brand && brand == 2}"> selected="selected"</c:if>>--%>
<%--												IOS--%>
<%--											</option>--%>
<%--											<option value="3" name="brand"--%>
<%--												<c:if test="${not empty brand && brand == 3}"> selected="selected"</c:if>>--%>
<%--												塞班--%>
<%--											</option>--%>
<%--											<option value="4" name="brand"--%>
<%--												<c:if test="${not empty brand && brand == 4}"> selected="selected"</c:if>>--%>
<%--												Windows--%>
<%--											</option>--%>
<%--											<option value="5" name="brand"--%>
<%--												<c:if test="${not empty brand && brand ==5}"> selected="selected"</c:if>>--%>
<%--												Windows--%>
<%--											</option>--%>
<%--										</select>--%>
										<th>
											终端品牌：
										</th>
										<select id="term_pp" name="term_pp"
											style="width: 100px; height: 50%;">
		
											<option value="0" name="term_pp"
												<c:if test="${empty term_pp || term_pp == 0}"> selected="selected"</c:if>>
												全部
											</option>
											<option value="10001" name="term_pp"
												<c:if test="${not empty term_pp && term_pp == 10001}"> selected="selected"</c:if>>
												全球通
											</option>
											<option value="10002" name="term_pp"
												<c:if test="${not empty term_pp && term_pp == 10002}"> selected="selected"</c:if>>
												神州行
											</option>
											<option value="10003" name="term_pp"
												<c:if test="${not empty term_pp && term_pp == 10003}"> selected="selected"</c:if>>
												动感地带
											</option>
											<option value="10004" name="term_pp"
												<c:if test="${not empty term_pp && term_pp == 10004}"> selected="selected"</c:if>>
												行业卡
											</option>
										</select>
									
							</div>
						</div>

						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									语音通信费：
								</th>
								<th>
									<input type="text" name="fee_call_min" id="fee_call_min" onblur="validateJs(this,'fee_call_min');" maxlength="10"
										value="${fee_call_min }" style="width: 77px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="fee_call_max" onblur="validateJs(this,'fee_call_max');" maxlength="10"
											id="fee_call_max" value="${fee_call_max }"
											style="width: 77px; height: 40%;">
								</th>
								<th>
									增值业务费：
								</th>
								<th>
									<input type="text" name="fee_incr_min" id="fee_incr_min" onblur="validateJs(this,'fee_incr_min');" maxlength="10"
										value="${fee_incr_min }" style="width: 75px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="fee_incr_max" onblur="validateJs(this,'fee_incr_max');" maxlength="10"
											id="fee_incr_max" value="${fee_incr_max }"
											style="width: 75px; height: 40%;">
								</th>
								<th>
									GPRS通信费：
								</th>
								<th>
									<input type="text" name="fee_gprs_min" id="fee_gprs_min" onblur="validateJs(this,'fee_gprs_min');" maxlength="10"
										value="${fee_gprs_min }" style="width: 77px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="fee_gprs_max" onblur="validateJs(this,'fee_gprs_max');" maxlength="10"
											id="fee_gprs_max" value="${fee_gprs_max }"
											style="width: 77px; height: 40%;">
								</th>
							</div>
						</div>

						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									漫游费：
								</th>
								<th>
									<input type="text" name="fee_roam_min" id="fee_roam_min" onblur="validateJs(this,'fee_roam_min');" maxlength="10"
										value="${fee_roam_min }" style="width: 77px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="fee_roam_max"
											id="fee_roam_max" value="${fee_roam_max }" onblur="validateJs(this,'fee_roam_max');" maxlength="10"
											style="width: 77px; height: 40%;">
								</th>
								<th>
									长途费：
								</th>
								<th>
									<input type="text" name="fee_ldc_min" id="fee_ldc_min" onblur="validateJs(this,'fee_ldc_min');" maxlength="10"
										value="${fee_ldc_min }" style="width: 75px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="fee_ldc_max" onblur="validateJs(this,'fee_ldc_max');" maxlength="10"
											id="fee_ldc_max" value="${fee_ldc_max }"
											style="width: 75px; height: 40%;">
								</th>
								<th>
									WAP信息费：
								</th>
								<th>
									<input type="text" name="fee_wap_min" id="fee_wap_min" onblur="validateJs(this,'fee_wap_min');" maxlength="10"
										value="${fee_wap_min }" style="width: 77px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="fee_wap_max" onblur="validateJs(this,'fee_wap_max');" maxlength="10"
											id="fee_wap_max" value="${fee_wap_max }"
											style="width: 77px; height: 40%;">
								</th>
							</div>
						</div>

						<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									WLAN通信费：
								</th>
								<th>
									<input type="text" name="fee_wlan_min" id="fee_wlan_min" onblur="validateJs(this,'fee_wlan_min');" maxlength="10"
										value="${fee_wlan_min }" style="width: 77px; height: 40%;">
										&nbsp;&nbsp;－&nbsp;&nbsp; <input type="text" name="fee_wlan_max"
											id="fee_wlan_max" value="${fee_wlan_max }" onblur="validateJs(this,'fee_wlan_max');" maxlength="10"
											style="width: 77px; height: 40%;">
								</th>
								<th>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
								<input type="checkbox" id="is_sjzq" value="1" name="is_sjzq"  <c:if test="${not empty is_sjzq && is_sjzq==1}">checked=checked</c:if> />手机证券用户 
								<input type="checkbox" id="is_sjyd" value="1" name="is_sjyd"  <c:if test="${not empty is_sjyd && is_sjyd==1}">checked=checked</c:if> />手机阅读用户
							    <input type="checkbox" id="is_sjqb" value="1" name="is_sjqb"  <c:if test="${not empty is_sjqb && is_sjqb==1}">checked=checked</c:if> />手机钱包用户
								<input type="checkbox" id="is_yelyw" value="1" name="is_yelyw" <c:if test="${not empty is_yelyw && is_yelyw==1}"> checked=checked </c:if> />育儿类业务用户
							</div>
						</div>
						<div id="locationDiv" class="control-group group-search" style="margin-left: 0px;">
								<div id="mapId" class="controls controls-row" >
									位置筛选：
									<c:if var= "flag" test="${empty ismap || ismap==0}">
										<input type="radio" name="ismap" value="0" id="radionId"  onchange="hideInput();" checked=checked />任意位置  <input type="radio" name="ismap" value="1"  onchange="showInput();" />选取位置
									</c:if>
									<c:if test="${!flag}">
										<input type="radio" name="ismap" value="0" id="radionId"  onchange="hideInput();"  />任意位置  <input type="radio" name="ismap" value="1"  onchange="showInput();" checked=checked />选取位置
									</c:if>
								</div>
						</div>
						<div>
							<iframe
								src="${ctx}/analysis/screening/loadMap?ismap=${ismap}&areaId=${area_id}&mapX=${mapX}&mapY=${mapY}"
								id="busiFrame1" name="busiFrame1" marginwidth="0" marginheight="0"
								frameborder="0" width="100%" height="350px" scrolling="no" >
							</iframe>
						</div>
						
						<div id="map" class="control-group group-search" style="margin-left: 0px;" >
							<div  class="controls controls-row">
								时间段筛选：<input type="radio" name="timetype" value="0" <c:if test="${empty timetype || timetype==0}">checked=checked</c:if> />全部（0-24）<input type="radio" name="timetype" value="2" <c:if test="${timetype==2}">checked=checked</c:if> />休闲（12-14&&18-23）  <input type="radio" name="timetype" value="3" <c:if test="${not empty timetype && timetype==3}">checked=checked</c:if>  />休息（23-8）
								<input type="radio" name="timetype" value="1" <c:if test="${not empty timetype && timetype==1}">checked=checked</c:if>  />工作（8-12&&14-18）
							</div>
							<div class="controls controls-row">
								所选点经度：<input type="text" name="mapX" id="mapX" maxlength="10" readonly="readonly" value="${mapX}"> 
								所选点维度：<input type="text" name="mapY" id="mapY" maxlength="10"  readonly="readonly" value="${mapY}">  
								半径：<input type="text" name="mapR" id="mapR"  maxlength="10" readonly="readonly" value="${mapR}">米
							</div>
						</div>

						<input type="hidden" id="pageNO" name="page" value="${page}">
							<div class="control-group group-search" style="margin-left: 0px;">
								<div class="controls controls-row">
									<th>
										方案名称:
									</th>
									<input type="text" name="symbol" value="1"   style="display: none;">
									<input type="text" id="name" name="name" maxlength="10"
										value="${name}">
										</th>
										<th>
											<input type="submit" class="btn btn-primary" value="确认查询并存储方案"
												style="float: inherit;"  />
											<button class="btn btn-primary" onclick="doReset();" type="button">
												<i class="icon16 i-search"></i>重置条件
											</button>
										</th>
								</div>
							</div>
					</form>
				</div>
			</div>
		</div>
		
		<c:if test="${empty noTaskList.data}">
			<div class="alert alert-info">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
			</div>
		</c:if>
		<c:if test="${not empty noTaskList.data}">
				<div class="tab-pane fade in active" id="home">
			<form action="" name="itemForm" id="itemForm"  method="get">
			 <table class="table table-bordered table-hover table-striped">
				<thead>
				<tr>
					<th>
						ID
					</th>
					<th>
						方案名称
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
				<c:forEach items="${noTaskList.data}" var="item">
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
							${item[40]}
						</td>
						<td class="center vcenter" id="status_${item[0] }">
							<c:if test="${item[39]=='0'||item[39]=='1'}">提取中</c:if>
							<c:if test="${item[39]=='4'}">用户已取消</c:if>
							<c:if test="${item[39]=='2'}">完成</c:if>
						</td>
						<td class="center vcenter" id="show_${item[0] }">
							<c:if test="${item[39]=='0'||item[39]=='1'}">
								<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
								|
								<a href="javascript:operator('${item[0]}',1)">取消</a>
								|
								<a href="javascript:operator('${item[0]}',2)">删除</a>
							</c:if>
							<c:if test="${item[39]=='4'}">
								<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
								|
								<a href="javascript:operator('${item[0]}',3)">再次查询</a>
								|
								<a href="javascript:operator('${item[0]}',4)",>修改名称</a>
								|
								<a href="javascript:operator('${item[0]}',2)">删除</a>
							</c:if>
							<c:if test="${item[39]=='2'}">
								<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
								|
								<a href="javascript:operator('${item[0]}',3)">再次查询</a>
								|
								<a href="javascript:operator('${item[0]}',4)",>修改名称</a>
								|
								<a target="_blank" href="<%=request.getContextPath() %>/downhdfs.zip?filePath=/user/hive/warehouse/union_download_user/tid=${item[0]}&symbol=4"> 导出</a>
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
		</form>
		</div>
			<ht:page pageData="${noTaskList}" />
	</c:if>
	 
		
	</body>
</html>