<%@page import="com.cplatform.b2c.util.PathUtil"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://app.mapabc.com/apis?t=ajaxmap&v=2.1.2&key=f03346eb3a99be025979045e8fa1a281c5159611a2b384d2536d3087d68bc93d7779b1bf1c44bada" type="text/javascript">
</script>
<title>移动商城</title>
<style type="text/css">
.map_label_title{font:normal 14px , Arial;color:#0000ff;background:#f7f9fb;border-radius:5px;padding:0 3px;text-decoration:none;}
</style>
</head>
<body>
<div id="mapObj" class="view" style="width:1320px;height:1170px"></div> 
<SCRIPT LANGUAGE="JavaScript"><!--

       //中心点坐标
       var centerLong,centerDim;

        //设置地图长宽
        var wid = '${width}' + 'px';
        var hig = '${height}' + 'px';
        var vi = document.getElementById("mapObj");
             vi.style.width= wid;
             vi.style.height = hig;
  
       
        //地图设置项      
		var mapObj=new MMap("mapObj");
		var marker = null;
		var labelOpt =null;
		var markerOpt = null;    

			//接受需渲染店面信息
	        var shops = '${mapInfo}';
	        var str= new Array();
	        str=shops.split("|");
	      
		    
		    //地图循环渲染
		    for (var i=0;i<str.length;i++)
		    {
		        if (str[i]==null || typeof(str[i]) == 'undefined')
		        {
                    continue;		        
		        }
		        var shop = new Array();
		        shop = str[i].split(",");
		        
		        //shop对象解析
		        var map_long = shop[0];//经度
		        var map_dim = shop[1];//维度
		        var shop_name = shop[2];
		        
		        if (typeof(map_long) == 'undefined' || typeof(map_dim) == 'undefined')
		        {
                    continue;		        
		        }
		        
		        //将第一个点设置为中心点
		        if (i==0 && null != map_long && map_dim!=null)
		        {
		            centerLong = map_long;
		            centerDim = map_dim;
		            mapObj.setZoomAndCenter(13,new MLngLat(centerLong,centerDim));//同时设置地图的中心点及缩放级别 
		        }
		        
			    markerOpt=new MMarkerOptions();
				markerOpt.imageSize=new MSize(-50,30);
				markerOpt.imageUrl ="../img/map_index.jpg";//这里改成模板图片路径
			    var ll = new MLngLat(map_long,map_dim);//这里是标注点的lat,lng
			    labelOpt=new MLabelOptions();
			    labelOpt.content= "<label target='_blank' class='map_label_title'><strong>"+shop_name + "</strong></label>";//这里是标注标题
			    markerOpt.labelOption = labelOpt;
			    markerOpt.canShowTip= true;
			    marker = new MMarker(ll,markerOpt);
			    mapObj.addOverlay(marker, true);
		    }
		    mapObj.setZoomAndCenter(13,new MLngLat(centerLong,centerDim));//同时设置地图的中心点及缩放级别 
		    


	</script>
	
	
</body>
</html>
