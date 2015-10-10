<%@page import="com.cplatform.b2c.util.PathUtil"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://api.map.baidu.com/api?v=1.5&ak=FAe6af2bb6bf884b5f853a55bcea9d76" type="text/javascript">
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
		var mapObj=new BMap.Map("mapObj");
		mapObj.enableScrollWheelZoom();      //启用滚轮放大缩小 
		var marker = null;
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
		            mapObj.centerAndZoom(new BMap.Point(map_long, map_dim),15);//同时设置地图的中心点及缩放级别 
		        }
				
			    var point = new BMap.Point(map_long,map_dim);//这里是标注点的lat,lng
			     var label = new BMap.Label("<label target='_blank' class='map_label_title'><strong>" + shop_name + "</strong></label>",{offset:new BMap.Size(-28,28)});//这里是标注标题
			    marker = new BMap.Marker(point,label);
			    marker.setLabel(label);
			    mapObj.addOverlay(marker);
		    }


	</script>
	
	
</body>
</html>
