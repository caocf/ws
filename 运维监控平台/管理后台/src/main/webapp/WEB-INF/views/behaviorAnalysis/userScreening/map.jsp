<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@ include file="../../includes/if.jsp"%>--%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'map.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	
	<script type="text/javascript" src="<%=path %>/static/js/jquery-1.8.3.js"></script>
	<script src="http://webapi.amap.com/maps?v=1.2&key=36f5dc55b2d5abad0b1f920f77e884bb"></script>
		<script type="text/javascript">
		var mapObj, toolbar, overview, scale;
		var editorTool;
		function mapInit() {
			var opt = {
				level :13,//设置地图缩放级别    
				center : new AMap.LngLat(118.7674130000, 32.0415440000),//设置地图中心点     
				doubleClickZoom : true,//双击放大地图    
				scrollWheel : true
			//鼠标滚轮缩放地图    
			}
	
			mapObj = new AMap.Map("iCenter", opt);
			
			mapObj.plugin(["AMap.CircleEditor"], function() {  
		        editorTool = new AMap.CircleEditor(mapObj, circle);  
		        editorTool.open();     
		    });  
		    
			AMap.Conf.network = 1;
			mapObj.plugin( [ "AMap.ToolBar", "AMap.OverView", "AMap.Scale" ],
					function() {
						toolbar = new AMap.ToolBar();
						toolbar.autoPosition = false; //加载工具条     
					mapObj.addControl(toolbar);
					overview = new AMap.OverView(); //加载鹰眼
					   
					mapObj.addControl(overview);
					scale = new AMap.Scale(); //加载比例尺    
					mapObj.addControl(scale);
				});
	
		    //在地图上绘制圆形覆盖物  
		   circle = new AMap.Circle({   
		   center:new AMap.LngLat("118.7674130000","32.0415440000"),// 圆心位置  
		   radius:1000, //半径  
		   strokeColor: "#F33", //线颜色  
		   strokeOpacity: 1, //线透明度  
		   strokeWeight: 3, //线粗细度  
		   fillColor: "#ee2200", //填充颜色  
		   fillOpacity: 0.35//填充透明度  
		   });   
		   circle.setMap(mapObj);  
		  
		  // mapObj.setFitView();
		}
		function changeMap(city) {
			var partition = new AMap.Partition();
			partition.byCity(city, byCity_CallBack);
		}
		function byCity_CallBack(data) {
			editorTool.close();  
			circle.setMap(null);
			addMarker(data.city.x, data.city.y, data.city.name);
		}
		function addMarker(x, y, name) {
			mapObj.setZoomAndCenter(13,new AMap.LngLat(x,y));  
			
			circle = new AMap.Circle({
				map: mapObj,
				center: new AMap.LngLat(x, y),
				radius: 1000,
				strokeColor: "#F33",
				strokeOpacity: 1,
				strokeWeight: 3,
				fillColor: "#ee2200",
				fillOpacity: 0.35
			});
			
			mapObj.plugin(["AMap.CircleEditor"], function() {  
		        editorTool = new AMap.CircleEditor(mapObj, circle);  
		        editorTool.open();     
		    });  
	
			mapObj.setFitView();
		}

		function onClose() {
			var mapX = circle.getCenter().getLng();
			var mapY = circle.getCenter().getLat();
			var mapR = circle.getRadius();
			window.parent.showCircle(mapX,mapY,mapR);
			editorTool.close();
			
		}
		function onOpen() {
			editorTool.open();
		}

		$(document).ready(function(){
			var area = $("#area").val();
			if(area==""||area=='全省'){
				
				mapInit();
			}else{
					var mapX=$("#mapX").val();
					var mapY=$("#mapY").val();
					var opt = {
						level :13,//设置地图缩放级别    
						center : new AMap.LngLat(mapX, mapY),//设置地图中心点     
						doubleClickZoom : true,//双击放大地图    
						scrollWheel : true
					//鼠标滚轮缩放地图    
					}
			
					mapObj = new AMap.Map("iCenter", opt);
					
					mapObj.plugin(["AMap.CircleEditor"], function() {  
				        editorTool = new AMap.CircleEditor(mapObj, circle);  
				        editorTool.open();     
				    });  
				    
					AMap.Conf.network = 1;
					mapObj.plugin( [ "AMap.ToolBar", "AMap.OverView", "AMap.Scale" ],
							function() {
								toolbar = new AMap.ToolBar();
								toolbar.autoPosition = false; //加载工具条     
							mapObj.addControl(toolbar);
							overview = new AMap.OverView(); //加载鹰眼
							   
							mapObj.addControl(overview);
							scale = new AMap.Scale(); //加载比例尺    
							mapObj.addControl(scale);
						});
			
				    //在地图上绘制圆形覆盖物  
				   circle = new AMap.Circle({   
				   center:new AMap.LngLat(mapX,mapY),// 圆心位置  
				   radius:1000, //半径  
				   strokeColor: "#F33", //线颜色  
				   strokeOpacity: 1, //线透明度  
				   strokeWeight: 3, //线粗细度  
				   fillColor: "#ee2200", //填充颜色  
				   fillOpacity: 0.35//填充透明度  
				   });   
				   circle.setMap(mapObj);  

				   changeMap(area);
				  
			}	
		});
	</script>
	
  </head>
  
  <body>
  
    <div>
						 
		<div id="iCenter" style="width:1000px;height:300px;border:#F6F6F6 solid 1px;"></div>
		<input type="hidden" id="area" value="${area }" name="area">
		<input type="hidden" id="mapX" value="${mapX }" name="mapX">
		<input type="hidden" id="mapY" value="${mapY }" name="mapY">
		<input type="button" id="ok" onclick="onClose()" <c:if test="${empty ismap || ismap==0}">disabled="disabled"</c:if>  value="确定">
		<input type="button" id="ok" onclick="onOpen()" <c:if test="${empty ismap || ismap==0}">disabled="disabled"</c:if>  value="编辑"><br/>
</div>
  </body>
</html>
