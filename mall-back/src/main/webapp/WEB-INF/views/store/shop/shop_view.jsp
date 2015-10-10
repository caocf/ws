<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>   
    <script type="text/javascript" src="<spring:url value="${mapabcKey}"/>"></script>
    <script type="text/javascript">  
    function clickA(){
    	var a = document.getElementById("auditingClick");
    	a.click();
    }  
	//*************map start*****************
    var mapObj,toolbar,overview,scale;
	function mapInit(){
		var mapLong = document.getElementById("mapLong").value;
		var mapDim = document.getElementById("mapDim").value;
		if(mapLong == "" && mapDim == ""){//设置地图中心点(苏州)
			mapLong = 120.58531600000003;
			mapDim = 31.298886;
		}else{//设置地图中心点(门店位置)
		}
		var opt = {
			level:13,//初始地图视野级别
			center:new MMap.LngLat(mapLong,mapDim),	
			doubleClickZoom:true,//双击放大地图
			scrollWheel:true//鼠标滚轮缩放地图
		}
		mapObj = new MMap.Map("map",opt);
		mapObj.plugin(["MMap.ToolBar","MMap.OverView","MMap.Scale"],function(){ 
			toolbar = new MMap.ToolBar();
			toolbar.autoPosition=false; //加载工具条 
			mapObj.addControl(toolbar);  	
			overview = new MMap.OverView(); //加载鹰眼
			mapObj.addControl(overview);  	
			scale = new MMap.Scale(); //加载比例尺
			mapObj.addControl(scale);
		});
		var mapLongOld = document.getElementById("mapLong").value;
		var mapDimOld = document.getElementById("mapDim").value;
		if(mapLongOld != "" && mapDimOld != ""){
			marker = new MMap.Marker({ 
				id:"oldMapInfo",
				position:new MMap.LngLat(mapLongOld,mapDimOld),
				icon:"http://code.mapabc.com/images/lan_1.png",
				offset:new MMap.Pixel(0,-36),
				draggable:false,
				cursor:"default",
				visible:true
			});
			mapObj.addOverlays(marker);   
			mapObj.setCenter(marker.position);
			inforWindow = new MMap.InfoWindow({   
				content:"门店地址",   
				offset:new MMap.Pixel(-93,-78)   
			});   
			mapObj.bind(marker,"click",function(e){   
				inforWindow.open(mapObj,marker.getPosition());     
			});
		}
		
	}
	//*************map end*****************
    </script>
</head>
<body onload="mapInit();">
<div id="content">
	<div class="box"> 
        <div class="form">
            <div class="fields">                
                <c:if test="${not empty shop.storeName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >结算商户:</label>
                    </div>
                    <div class="input">
                    ${shop.storeName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shopTypeList}">
                <div class="field">
                    <div class="label noinput">
                        <label for="shopType" >门店分类:</label>
                    </div>
                    <div class="input">
                    <ol>
                    <c:forEach items="${shopTypeList}" var="item">
                    	<li>${item.sysType.name}</li>
                    </c:forEach>
                    </ol>
					</div>
                </div>
                </c:if>
                <c:if test="${not empty shop.name}">
                <div class="field">
                    <div class="label noinput">
                        <label for="name" >门店名称:</label>
                    </div>
                    <div class="input">
                    ${shop.name}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.shortName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="shortName" >门店简称:</label>
                    </div>
                    <div class="input">
                    ${shop.shortName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.regionName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="regionName" >归属地市:</label>
                    </div>
                    <div class="input">
                    ${shop.regionName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.sortName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="sortName" >签约状态:</label>
                    </div>
                    <div class="input">
                    ${shop.sortName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.discountDetail}">
                <div class="field">
                    <div class="label noinput">
                        <label for="discountDetail" >折扣内容:</label>
                    </div>
                    <div class="input">
                    ${shop.discountDetail}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.isChain}">
                <div class="field">
                    <div class="label noinput">
                        <label for="isChain" >是否连锁:</label>
                    </div>
                    <div class="input">
                    ${shop.isChainName}
                    </div>
                </div>
                </c:if>
                <c:if test="${shop.isChain == '1'}">
                <c:if test="${not empty shop.isBaseShop}">
                <div class="field">
                    <div class="label noinput">
                        <label for="isBaseShop" >是否总店:</label>
                    </div>
                    <div class="input">
                    ${shop.isBaseShopName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.baseShopName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="baseShopName" >总店名称:</label>
                    </div>
                    <div class="input">
                    ${shop.baseShopName}
                    </div>
                </div>
                </c:if>
                </c:if>
                <c:if test="${not empty shopPosLinkList}">
                <div class="field">
                    <div class="label noinput">
                        <label for="shopPosLink" >门店终端:</label>
                    </div>
                    <div class="input">
                    <ol>
                    <c:forEach items="${shopPosLinkList}" var="item">
                    	<li>${item.sysPos.name}(设备号:${item.posNo}<c:if test="${not empty item.terminalId}">;标识:${item.terminalId}</c:if>)</li>
                    </c:forEach>
                    </ol>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shopTagList}">
                <div class="field">
                    <div class="label noinput">
                        <label for="shopTag" >门店标签:</label>
                    </div>
                    <div class="input">
                    <ol>
                    <c:forEach items="${shopTagList}" var="item">
                    	<li>${item.tag}</li>
                    </c:forEach>
                    </ol>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.createTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="createTime" >创建时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${shop.createTime}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.shopUserId}">
                <div class="field">
                    <div class="label noinput">
                        <label for="shopUserId" >创建商户账号ID:</label>
                    </div>
                    <div class="input">
                    ${shop.shopUserId}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.updateTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="updateTime" >更新时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${shop.updateTime}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.startTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="startTimeToStopTime" >有效期:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${shop.startTime}" tfmt="yyyy-MM-dd"/>至<ct:time source="${shop.stopTime}" tfmt="yyyy-MM-dd"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.address}">
                <div class="field">
                    <div class="label noinput">
                        <label for="address" >门店地址:</label>
                    </div>
                    <div class="input">
                    ${shop.address}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.phone}">
                <div class="field">
                    <div class="label noinput">
                        <label for="phone" >联系电话:</label>
                    </div>
                    <div class="input">
                    ${shop.phone}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.openTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="openTime" >营业时间:</label>
                    </div>
                    <div class="input">
                    ${shop.openTime}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.busLine}">
                <div class="field">
                    <div class="label noinput">
                        <label for="busLine" >公交线路:</label>
                    </div>
                    <div class="input">
                    ${shop.busLine}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.area}">
                <div class="field">
                    <div class="label noinput">
                        <label for="area" >面积:</label>
                    </div>
                    <div class="input">
                    ${shop.area}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.parkPlace}">
                <div class="field">
                    <div class="label noinput">
                        <label for="parkPlace" >停车位:</label>
                    </div>
                    <div class="input">
                    ${shop.parkPlace}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.avgSpend}">
                <div class="field">
                    <div class="label noinput">
                        <label for="avgSpend" >人均消费:</label>
                    </div>
                    <div class="input">
                    ${shop.avgSpend}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.roomNum}">
                <div class="field">
                    <div class="label noinput">
                        <label for="roomNum" >包间数:</label>
                    </div>
                    <div class="input">
                    ${shop.roomNum}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.remark}">
                <div class="field">
                    <div class="label noinput">
                        <label for="remark" >详细介绍:</label>
                    </div>
                    <div class="input">
                    ${shop.remark}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.mapLong}">
                <div class="field">
                    <div class="label label-textarea">
                        <label for="map" >门店坐标:</label>
                    </div>
                    <div class="input">
                    	<p class="showOrHideMap" style="font-weight: bold;cursor: pointer;">显示/隐藏地图<c:if test="${not empty shop.mapLong}">(点开地图，查看门店所处地理位置)</c:if></p> 
                    	<div class="mapShowOrHide" style="display: none;">
                    	<div id="map" style="width:500px;height:400px;"></div><br />	
                    	</div>
                    	<input type="hidden" id="mapLong" value="${shop.mapLong}" readonly />
                    	<input type="hidden" id="mapDim" value="${shop.mapDim}" readonly />
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty shop.statusName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="status" >门店状态:</label>
                    </div>
                    <div class="input">
                    ${shop.statusName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty  shop.isValidName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="isValid" >有效状态:</label>
                    </div>
                    <div class="input">
                    ${shop.isValidName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty  shop.shopClassName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="shopClass" >门店类型:</label>
                    </div>
                    <div class="input">
                    ${shop.shopClassName}
                    </div>
                </div>
                </c:if>
                <div class="buttons">
                <ct:display model="shop_audit_list" btn="audit_btn">
                	<c:if test="${shop.status == '0'}">
                		<button type="button" class="common_btn" onclick="dealInfo('确定要送审吗？如果免审，将直接审核通过','process/${shop.status}/${shop.id}/view');">送审</button>&nbsp;&nbsp;&nbsp;&nbsp;
                	</c:if>
                	<c:if test="${shop.status == '2'}">
                		<button type="button" class="common_btn" onclick="clickA()">审核</button>&nbsp;&nbsp;&nbsp;&nbsp;
                		<a id="auditingClick" href="shop_auditing?id=${shop.id}&whereAbout=view"></a>
                	</c:if>
                </ct:display>
                	<c:if test="${shop.status == '3'}">
                	<c:if test="${shop.isValid != '1'}">
                	<ct:display model="shop_audit_list" btn="up_line_btn">
                		<button type="button" class="common_btn" onclick="dealInfo('确定要设置成有效吗？','valid/${shop.status}/${shop.id}/view');">有效</button>&nbsp;&nbsp;&nbsp;&nbsp;
                	</ct:display>
                	</c:if>
                	<c:if test="${shop.isValid == '1'}">
                	<ct:display model="shop_audit_list" btn="down_line_btn">
                		<button type="button" class="common_btn" onclick="dealInfo('确定要设置成无效吗？','valid/31/${shop.id}/view');">无效</button>&nbsp;&nbsp;&nbsp;&nbsp;
                	</ct:display>
                	</c:if>
                	</c:if>
                	<a href="${queryBackUrl}" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
	</div>
</div>
<script type="text/javascript">
$().ready(function() {	
	$(".showOrHideMap").click(function(){
    	$(".mapShowOrHide").slideToggle("fast");
  	});
});
</script>
</body>
</html>