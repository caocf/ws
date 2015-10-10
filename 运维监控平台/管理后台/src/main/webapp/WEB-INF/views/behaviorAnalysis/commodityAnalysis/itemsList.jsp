<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cplatform.mall.dc.utils.AreaSelectTag"%>
<%@ include file="../../includes/if.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/plugins/iframeTools.js"></script>
		<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/artDialog.js?skin=blue"></script>
		<style type="text/css">
			#area{
				width: 100px;
			}
		
		</style>
		<script type="text/javascript">

		//查询操作
		function getQuery() {
			var goodsName = parent.document.getElementById('good_title').value;
			var good_title = encodeURI(encodeURI(parent.document.getElementById('good_title').value));
			var coin = $("#coin").val();
			var area_id = $('#area').find('option:selected').val();
			var brand = $('#brand_id').find('option:selected').val() - 0;
			var sex = $('#sex_id').find('option:selected').val() - 0;
			var scoreType = $('#score_id').find('option:selected').val();
			var coinType = $('#coin_id').find('option:selected').val();
			var score = $("#score").val();
			var min_age = $("#min_age").val();
			var max_age = $("#max_age").val();
			if(isNaN(max_age)||isNaN(min_age)||isNaN(coin)||isNaN(score)){
				art.dialog({
					title:'',
					width:'150',
					height:100,
					time:1,
					icon: 'warning',
					content:'输入字符必须为数字'
				});
				return false;
			}else{
				if(trim(goodsName)==""){
					art.dialog({
							title:'',
							width:'150',
							height:100,
							time:1,
							icon: 'warning',
							content:'商品名称不能为空'
						});
					return false;
				}else if (coin == "" && score == "" && max_age == "" && min_age == "") {
						if(goodsName.length>40){
							art.dialog({
								title:'',
								width:'150',
								height:100,
								time:1,
								icon: 'warning',
								content:'商品名称不能超过40个字符'
							});
							return false;
						}else{
							window.location.href = "${ctx}/analysis/commodity/getItemList?flag=1&good_title="+ good_title+"&brand=" + brand + "&sex=" + 
							sex +"&area_id="+area_id+"&scoreType="+scoreType+"&coinType="+coinType+"&coin="+coin+"&score="+score+"&min_age="+min_age+"&max_age="+max_age+"&itemId=${item}";
						}
				
				}else if ((min_age-0)<0|| (max_age-0)>100||(min_age-0)>100||(max_age-0)<0) {
					art.dialog({
						title:'',
						width:'150',
						height:100,
						time:1,
						icon: 'warning',
						content:'年龄不能小于0'+'</br>'+'或者大于100'
					});
					return false;
				} else if (min_age!=null&&min_age.replace(/(^\s*)|(\s*$)/g, "")!=""&&max_age!=null&&max_age.replace(/(^\s*)|(\s*$)/g, "")!=""&&(min_age-0)>(max_age-0)) {
					art.dialog({
						title:'',
						width:'150',
						height:100,
						time:1,
						icon: 'warning',
						content:'年龄范围输入有误'
					});
					return false;
				}else if ((coin == "0" && coinType == '<')
						|| (score == "0" && scoreType == '<')) {
					art.dialog({
						title:'',
						width:'150',
						height:100,
						time:1,
						icon: 'warning',
						content:'积分或者商城币不能小于0'
					});
					return false;
				}else if(coin<0||score<0){
					art.dialog({
						title:'',
						width:'150',
						height:100,
						time:1,
						icon: 'warning',
						content:'积分或者商城币不能小于0'
					});
					return false;
				}else {
					if(goodsName.length>40){
						art.dialog({
							title:'',
							width:'150',
							height:100,
							time:1,
							icon: 'warning',
							content:'商品名称不能超过40个字符'
						});
						return false;
					}else{
						window.location.href = "${ctx}/analysis/commodity/getItemList?flag=1&good_title="+ good_title+"&brand=" + brand + "&sex=" + 
						sex +"&area_id="+area_id+"&scoreType="+scoreType+"&coinType="+coinType+"&coin="+coin+"&score="+score+"&min_age="+min_age+"&max_age="+max_age+"&itemId=${item}";
					}
				}
			}
		}
			
		function operator(id,type){
		  	if(type==1){
				art.dialog({
				    content: '确定要执行取消操作？',
				    ok: function () {
						$.ajax({ type:"POST",
						    url : '${ctx}/analysis/commodity/doOperator',
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
								     $("#hidden_"+id).show();
								     $("#curr_"+id).hide();
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
							 url : '${ctx}/analysis/commodity/doOperator',
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
				    url : '${ctx}/analysis/commodity/doOperator',
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

<%--			else if(type==0){--%>
<%--				var good_title = $(".class_"+id).val();--%>
<%--				window.location.href = "${ctx}/analysis/commodity/getItemList?id="+id+"&type="+type+"&good_title="+good_title+"&page=${page}";--%>
<%--			}--%>
		}

		function doOperator(id,name){
			var type = 0;
			window.parent.document.getElementById("good_title").value = name;
			window.parent.document.getElementById('busiFrame').src = "${ctx}/analysis/commodity/getItemList?id="+id+"&type="+type+"&good_title="+name+"&page=${page}&itemId=${item}";
		}

		
		function doBlur(obj,id){
			var itemName = obj.value;
			//判断商品名称不能为空
			if(trim(itemName)==""){
					art.dialog({
							title:'',
							width:'150',
							height:100,
							time:1,
							icon: 'warning',
							content:'商品名称不能为空'
						});					
					return false;
			}
			$("#t_"+id).text("");
			$("#t_"+id).text(obj.value);
			$("#td_"+id).hide();
			$("#t_"+id).show();			
			$.ajax({
				type:"POST",
			    url : '${ctx}/analysis/commodity/doOperator',
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

		function onReset(){
			parent.document.getElementById('good_title').value="";
			window.location.href = "${ctx}/analysis/commodity/getItemList";
		}
		
		//去掉字符串头尾空格   
		function trim(str) {   
		    return str.replace(/(^\s*)|(\s*$)/g, "");   
		}
		
		</script>
	</head>

	<body>
		
		  <div class="daily" id="daily_dxqf">
			<div class="well"
				style="padding: 0px !important; padding-top: 19px !important;">
				<div class="tab-pane fade in active" id="home">
					<form name="queryForm" id="queryForm" action="" method="get">
							<div class="control-group group-search" style="margin-left: 0px;">
							<div class="controls controls-row">
								<th>
									条件一：
								</th>
								<th>
									地区:
								<%=AreaSelectTag.getAreaTag(request.getAttribute("area_id"))%>
								</th>
								
								
								<th>
									用户品牌:
								</th>
								<select id="brand_id" name="brand"
									style="width: 100px; height: 50%;">
									<option value="0" name="brand"
										<c:if test="${empty brand || brand == 0}"> selected="selected"</c:if>>
										全部
									</option>
									<option value="10002" name="brand"
										<c:if test="${not empty brand && brand == 10002}"> selected="selected"</c:if>>
										神州行
									</option>
									<option value="10003" name="brand"
										<c:if test="${not empty brand && brand == 10003}"> selected="selected"</c:if>>
										动感地带
									</option>
									<option value="10001" name="brand"
										<c:if test="${not empty brand && brand == 10001}"> selected="selected"</c:if>>
										全球通
									</option>
									<option value="10004" name="brand"
										<c:if test="${not empty brand && brand == 10004}"> selected="selected"</c:if>>
										行业卡
									</option>
								</select>
								<th>
									用户性别:
								</th>

								<input type="hidden" id="good_title" value="${good_title}"/>
								
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
									年龄：
								</th>
								<th>
									<input type="text" name="min_age" id="min_age" maxlength="10" value="${min_age }"
										style="width: 100px; height: 40%;"> 
									&nbsp;&nbsp;－&nbsp;&nbsp;
									<input type="text" name="max_age" id="max_age" maxlength="10" value="${max_age }"
										style="width: 100px; height: 40%;">
								</th>
							</div>
					</div>
					<div class="control-group group-search" style="margin-left: 0px;">
						<div class="controls controls-row">
							<th>
								条件二：
							</th>
							<th>
								积分:
							</th>
							<select id="score_id" name="scoreType"
								style="width: 100px; height: 50%;">
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
							<input type="text" id="score" name="score" value="${score}" maxlength="10"
								style="width: 100px; height: 40%;">
							</th>
							<th>
								商城币:
							</th>
							<select id="coin_id" name="coinType"
								style="width: 100px; height: 40%;">
								<option value="<" name="coinType"
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
							<input type="text" id="coin" name="coin" value="${coin}" maxlength="10"
								style="width: 100px; height: 40%;">
						</div>
							
						<input type="reset" style="display: none;" />
						<input type="hidden" name = "page" value="${page}">
						<input type="hidden" name = "item" value="${item}">
						<div class="controls controls-row">
							<button class="btn btn-primary" onclick="getQuery();"type="button">
								<i class="icon16 i-search"></i> 查询并存储
							</button>

							<button class="btn btn-primary" type="button" onclick="onReset();">
								<i class="icon16 i-search"></i>重置条件
							</button>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
		
		<c:if test="${empty itemList.data}">
			<div class="alert alert-info">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
			</div>
		</c:if>
		<c:if test="${not empty itemList.data}">
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
				<c:forEach items="${itemList.data}" var="item">
					<tr  id="${item[0] }">
						<td class="center vcenter" >
							${item[0]}
						</td>
						<td class="center vcenter" id="td_${item[0] }" style="display: none;">
							<input type="text"  id="itemName" value="${item[1]}"  name= "itemName" onblur="doBlur(this,'${item[0]}');" style="color: red"/>
						</td>
						<td class="center vcenter" id="t_${item[0] }" >
							${item[1]}
						</td>
						<td class="center vcenter" id="time_${item[0]} }">
							${item[2]}
						</td>
						<td class="center vcenter" id="status_${item[0] }">
							<c:if test="${item[3]=='0'||item[3]=='1'}">提取中</c:if>
							<c:if test="${item[3]=='4'}">用户已取消</c:if>
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
							<c:if test="${item[3]=='2'}">
								<a href="javascript:doOperator('${item[0]}','${item[1] }')">提取条件</a>
								|
								<a href="javascript:operator('${item[0]}',3)">再次查询</a>
								|
								<a href="javascript:operator('${item[0]}',4)",>修改名称</a>
								|
								<a target="_blank" href="<%=request.getContextPath() %>/downhdfs.zip?filePath=/user/hive/ExportUserTask/${item[0]}&symbol=2"> 导出</a>
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
			<ht:page pageData="${itemList}" />
	</c:if>
	 

	</body>
</html>
