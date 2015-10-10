<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="商户统计" />
<%@ include file="../includes/t.jsp"%>
 
<script type="text/javascript" src="${ctx }/static/js/datepicker/WdatePicker.js" ></script>
<link href="${ctx }/static/js/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<body>
<a href="${ctx }/inter">返回上一级</a>
<table style="width:800px; text-align:left; padding:0; margin:0;">
	<tr>
		<td width="80px"><label class="control-label" for="itemNameStr" style="margin-top:18px;">开始时间：</label></td>
		<td>
			<div class="controls controls-row">
				<input type="text" id="dateStr" name="dateStr" class="date required" class="txt Wdate required" width="185px;" style="margin-top:9px;"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" readonly="readonly"/>
			</div>	
		</td>
		<td width="80px"><label class="control-label" for="itemNameStr" style="margin-top:18px;" >结束时间：</label></td>
		<td>
			<div class="controls controls-row">
				<input type="text" id="dateEnd" name="dateEnd" class="date required" class="txt Wdate required" width="185px;" style="margin-top:9px;"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" readonly="readonly"/>
			</div>		
		</td>
		<td>
			<button name="btnQuery" class="btn btn-primary" style="margin-top:0px;" type="button" onclick="tosearch();">
				<i class="icon16 i-search"></i> 查询
			</button>
		</td>
	</tr>
</table>

<div id="container"></div>
</body>
<script type="text/javascript">
var second=60000; //间隔时间1分钟
var Interval_control;
var value;
// ajax 提交曲线图
$(function () {
	scroll();
  Interval_control = setInterval(scroll, second);
  function scroll(){
		$.ajax({
			url: "${ctx}${req_url}",
			type: 'post',
			dataType: 'json',
			data: {"type":"${type}","name":"${name}"},
			success:function(response){
				if(response==null){
					alert("请求成功，返回对象null！");
				}
				else{
					if(response.retCode=="0000"){
						if(!compareObject(value,response)){
							curveChart(response,"container","900","600");
							value = response;
							$("#dateStr").val(response.start);
							$("#dateEnd").val(response.end);
						}
					}
				}
			},
			error:function(){
				//alert("请求失败！");
			}
		});
	}
 });
  function tosearch(){
	  clearInterval(Interval_control); 
	  	var start = $("#dateStr").val();
		var end = $("#dateEnd").val();
		$.ajax({
			url: "${ctx}${req_url}",
			type: 'post',
			dataType: 'json',
			data: {"type":"${type}","name":"${name}","start":start,"end":end},
			success:function(response){
				if(response==null){
					alert("请求成功，返回对象null！");
				}
				else{
					if(response.retCode=="0000")
						curveChart(response,"container","950","600");
				}
			},
			error:function(){
				//alert("请求失败！");
			}
		});
	}

</script>
<%@ include file="../includes/chartControl.jsp"%>
 