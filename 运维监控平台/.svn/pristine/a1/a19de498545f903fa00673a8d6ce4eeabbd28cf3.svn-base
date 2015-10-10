<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="inner_title" value="首页"/>
<%@ include file="./includes/t.jsp" %>
 
<script type="text/javascript" src="${ctx }/static/js/datepicker/WdatePicker.js" ></script>
<link href="${ctx }/static/js/datepicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<style type="text/css">  
.left {  
    float:left;  
    display:inline;  
}  
.right{  
    float:right;  
    display:inline;  
}  
</style>
<body>
<table border="1">
<tr>
<td><h2>宽连-重点指标</h2></td>
</tr>
<tr>
<td>
<div id="container1" class="left"></div>
<div id="container2" class="right"></div>
<div id="container3" class="left" style="padding-top:0.2cm;"></div>
<div id="container4" class="right" style="padding-top:0.2cm;"></div>
</td>
</tr>
<tr>
<td><h2>高阳-重点指标</h2></td>
</tr>
<tr><td></td></tr>
<tr>
<td><h2>方正-重点指标</h2></td>
</tr>
<tr><td></td></tr>
</table>
</body>
<script type="text/javascript">
var second=60000; //间隔时间1分钟
var width=490;
var heigth=350;
var Interval_control1;
var Interval_control2;
var Interval_control3;
var Interval_control4;
$(function () {
	getUrl();
  function getUrl(){
		$.ajax({
			url: "${ctx}/main/getUrl",
			type: 'post',
			dataType: 'json',
			data: {},
			success:function(response){
				if(response==null){
					alert("请求成功，返回对象null！");
				}
				else{
					for(var i=1;i<=response.length;i++){
						drawpic(response[i-1],"Interval_control"+i,"container"+i);
					}
				}
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}
 });
// ajax 提交曲线图
function drawpic(url,interval,div) {
	scroll();
	interval = setInterval(scroll, second);
  function scroll(){
		$.ajax({
			url: "${ctx}"+url,
			type: 'post',
			dataType: 'json',
			data: {},
			success:function(response){
				if(response==null){
					alert("请求成功，返回对象null！");
				}
				else{
					if(response.retCode=="0000"){
						curveChart(response,div,width,heigth);
					}
				}
			},
			error:function(){
				//alert("请求失败！");
			}
		});
	}
 }
</script>
<%@ include file="includes/chartControl.jsp"%>
 