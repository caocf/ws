<%@page import="com.cplatform.b2c.util.PathUtil"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://app.mapabc.com/apis?t=javascriptmap&v=3.1.1&key=f03346eb3a99be025979045e8fa1a281c5159611a2b384d2536d3087d68bc93d7779b1bf1c44bada" type="text/javascript">
</script>
<title>移动商城</title>
<style type="text/css">
.map_label_title{font:normal 14px , Arial;color:#0000ff;background:#f7f9fb;border-radius:5px;padding:0 3px;text-decoration:none;}
</style>
</head>
<body>
<div id="mapObj" class="view" style="width:1320px;height:1170px"></div> 
<SCRIPT LANGUAGE="JavaScript">

       //中心点坐标
       var centerLong,centerDim;

        //设置地图长宽
        var wid = '${width}' + 'px';
        var hig = '${height}' + 'px';
        var vi = document.getElementById("mapObj");
             vi.style.width= wid;
             vi.style.height = hig;
  
       
        //地图设置项      
		var mapObj=new MMap.Map("mapObj");
		var marker = null;
		var labelOpt =null;
		var markerOpt = null;    

			//接受需渲染店面信息
	        var shops = '${mapInfo}';
	        var str= new Array();
	        str=shops.split("|");
	       // alert(str);
		    
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
				    mapObj.setZoomAndCenter(15,new MMap.LngLat(centerLong,centerDim));//同时设置地图的中心点及缩放级别 
		        }
		        
		      //构造点对象 
		        
		        var marker = new MMap.Marker({                    
		      
		          // id:"marker", //marker id                       
		      
		           position:new MMap.LngLat(map_long,map_dim), //位置  
		      
		           offset:new MMap.Pixel(-11.5,-32), //基点为图片左上角，设置相对基点的图片位置偏移量，向左向下为负 
		      
		          ///icon:"http://code.mapabc.com/images/lan_1.png",//图标，直接传递地址，还可以为MMap.Icon 对象 
		      
		           draggable:false, //不可拖动  
		      
		           cursor:"default",//鼠标悬停时显示的光标样式
		           content:"<div style='width:250px;height:20px;text-align:left;'><img src='http://code.mapabc.com/images/lan_1.png'/><label target='_blank' class='map_label_title'><strong>"+shop_name + "</strong></label></div>"
		        });  
		        
		      //  infoWindow = new MMap.InfoWindow({ 
		        	  
		        //    content:shop_name, 
		      
		          //  offset:new MMap.Pixel(-100,-96) 
		      
		       // }); 
		      
		        //信息窗口可以绑定覆盖物对象，也可在地图上的指定点打开 
		      
		        //infoWindow.open(mapObj,new MMap.LngLat(map_long,map_dim)); 
		      
			    mapObj.addOverlays(marker, true);
		    }


	</script>
	
	
</body>
</html>
