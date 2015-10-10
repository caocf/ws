<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript" src="<spring:url value="${mapabcKey}"/>"></script>
    <script type="text/javascript">    
    function showDiv(){
		with (fm) {
			//是否折扣选择
			if(sort[0].checked) {
				detailDiv.style.display="block";
			} else if(sort[1].checked){
				detailDiv.style.display="none";
			} 
			//连锁则显示总店与否选择
			if(isChain[0].checked) {
				isChainTrue.style.display="block";
			} else if(isChain[1].checked){
				isChainTrue.style.display="none";
			}
		}
	}
	
	function deletePos(obj){
    	$(obj).parent().parent().remove();
    }
    
    function deletePosItem(obj,url){
    	deleteItem(url, function(){$(obj).parent().parent().remove();});    	    	
    }
    
    //*************map start*****************
    var mapObj,toolbar,overview,scale,mouseTool;
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
		mapObj.plugin(["MMap.ToolBar","MMap.OverView","MMap.Scale","MMap.MouseTool"],function(){ 
			toolbar = new MMap.ToolBar();
			toolbar.autoPosition=false; //加载工具条 
			mapObj.addControl(toolbar);  	
			overview = new MMap.OverView(); //加载鹰眼
			mapObj.addControl(overview);  	
			scale = new MMap.Scale(); //加载比例尺
			mapObj.addControl(scale);
			mouseTool = new MMap.MouseTool(mapObj);
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
				content:"门店地址(原)",   
				offset:new MMap.Pixel(-93,-78)   
			});   
			mapObj.bind(marker,"click",function(e){   
				inforWindow.open(mapObj,marker.getPosition());     
			});
		}
		mapObj.bind(mapObj,"click",function(e){
			mouseTool.close(true);
			mouseTool.marker();
			document.getElementById("mapLong").value=e.lnglat.lng;
			document.getElementById("mapDim").value=e.lnglat.lat;	
		});
		
	}
	//******************地图关键字查询
	function keywordSearch(){
	var keywords = document.getElementById("keyword").value;
	var city = document.getElementById("city").value;
	city=city=='全国'?'total':city;
	var PoiSearchOption = {
		srctype:"POI",//数据来源
		type:"",//数据类别
		number:10,//每页数量,默认10
		batch:1,//请求页数，默认1
		range:3000,	//查询范围，默认3000米
		ext:""//扩展字段
	};
	MMap.Conf._server='http://apis.mapabc.com/';
	var MSearch = new MMap.PoiSearch(PoiSearchOption);
	MSearch.byKeywords(keywords,city,searchPoilist_CallBack);
	}
	var markers=[],infos=[];
	function searchPoilist_CallBack(data){
		mapObj.clearOverlaysByType('marker');
		mapObj.clearInfoWindow();
		if(data.status=='E0'){
			if(data.bounds){
				var a=data.bounds.split(';');
				if(a.length==2){
					var b=a[0].split(','),c=a[1].split(',');
					mapObj.setBounds(new MMap.Bounds(new MMap.LngLat(b[0],b[1]),new MMap.LngLat(c[0],c[1])));
				}else{//只返回一条数据时
					var d=a[0].split(',')
					mapObj.setCenter(new MMap.LngLat(d[0],d[1]))
				}
				
			}	
			var list='<ul id="poisearchUl">';markers=[];infos=[];
			for(var i=0,l=data.list.length;i<l;i++){
				list+='<li id="li'+i+'">'+(i+1)+'、'+data.list[i].name+'</li>'
				markers.push(new MMap.Marker({
					icon:"http://code.mapabc.com/images/lan_1.png",position:new MMap.LngLat(data.list[i].x,data.list[i].y),id:'marker'+i,offset:new MMap.Pixel(-10,-34)
				}));
				mapObj.addOverlays(markers[i]);
				infos.push(new MMap.InfoWindow({
					content:'<b>'+data.list[i].name+'</b><hr/>'+TipContents(data.list[i].type,data.list[i].address,data.list[i].tel,data.list[i].x,data.list[i].y),autoMove:true
											   }));
				mapObj.bind(markers[i],'click',function(){
					var index=this.obj.id.substring(6)
					infos[index].open(mapObj,this.obj.getPosition())
														})
				}
			list+='</ul>';
		}else if(data.status =="E1"){
	         list = "未查找到任何结果!<br />建议：<br />1.请确保所有字词拼写正确。<br />2.尝试不同的关键字。<br />3.尝试更宽泛的关键字。";	
		}else{
			 list= "错误信息："+data.status+"请对照API Server v2.0.0 简明提示码对照表查找错误类型";
		}
		document.getElementById('result').innerHTML=list;
		var liObj = document.getElementsByTagName('li')
		for (i = liObj.length - 1; i >= 0; i--) {
			liObj[i].onmouseover=function(){
				var num=this.id.substring(2);
				this.style.background='#DDDDDD';
				infos[num].open(mapObj,markers[num].getPosition());
			}
			liObj[i].onmouseout=function(){this.style.background='#FFFFFF';}
		};
	}
	function TipContents(type,address,tel,mapLong,mapDim){
		if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {
			type = "暂无";
		}
		if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
			address = "暂无";
		}
		if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address == "tel") {
			tel = "暂无";
		}
		var str ="经度：" + mapLong + "<br/>纬度：" + mapDim + "<br/>地址：" + address + "<br/>电话：" + tel + " <br/>类型："+type;
		return str;
	}
	//*************map end*****************
    </script>
</head>
<body onload="mapInit();">
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">门店录入</c:if><c:if test="${method == 'edit'}">门店编辑</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="shop" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
        <c:if test="${not empty shop.acShopId}">
        <form:hidden path="acShopId" />
        </c:if>
        <input type="hidden" id="backUrl" name="backUrl" />
        <c:if test="${method == 'edit'}">
        	<form:hidden path="createTime" />
        	<form:hidden path="shopUserId" />
        	<form:hidden path="isValid" />
        	<form:hidden path="shopClass" />
        	<form:hidden path="areaCode" />
        	<input type="hidden" id="oldName" name="oldName" value="${shop.name}"/>
        	<input type="hidden" id="oldShortName" name="oldShortName" value="${shop.shortName}"/>
        </c:if>
        <div class="form">
            <div class="fields">
            	<c:choose>
            	<c:when test="${empty shop.acShopId}">
            	<div class="field">
					<div class="label">
						<label for="shopId" class="req">所属商户：</label>
					</div>
					<div class="input">
					    <form:hidden path="acShopId" />
											<form:input path="storeName" maxlength="100" readonly="true"
												cssClass="small required" />
											<span class="error" id="advice-required-storeName" style="display:none"></span>
					</div>
				</div>
				</c:when>
				<c:otherwise>
				<div class="field">
                    <div class="label noinput">所属商户：</div>
                    <div class="input">${shop.storeName}</div>
                </div>
                </c:otherwise>
                </c:choose>
                <div class="field">
                    <div class="label">
                        <label for="type" class="req">门店分类：</label>
                    </div>
                    <div class="input">
                        <div>
                         	<input type="hidden" id="typeId" name="typeId" 
                         	<c:if test="${not empty shopTypeList}">value="<c:forEach items="${shopTypeList}" var="item"  varStatus="i">${item.typeId}<c:choose><c:when test="${i.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forEach>"</c:if> />
							<input type="text" id="typeName" name="typeName" readonly class="small required min-length-0 max-length-50" maxlength="50"
							<c:if test="${not empty shopTypeList}">value="<c:forEach items="${shopTypeList}" var="item"  varStatus="i">${item.sysType.name}<c:choose><c:when test="${i.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forEach>"</c:if> />
                        	<span class="error" id="advice-required-typeName" style="display:none"></span>
	                        <span class="error" id="advice-min-length-typeName" style="display:none"></span>
	                        <span class="error" id="advice-max-length-typeName" style="display:none"></span>
	                        <span class="error" id="advice-server-typeName" style="display:none"></span>
                        </div>                         
                    </div>                    
                </div>
                <div class="field">
                    <div class="label">
                        <label for="name" class="req">门店名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="name" cssClass="small required min-length-0 max-length-25" maxlength="25" />
                    	<span class="error" id="advice-required-name" style="display:none"></span>
                        <span class="error" id="advice-min-length-name" style="display:none"></span>
                        <span class="error" id="advice-max-length-name" style="display:none"></span>
                        <span class="error" id="advice-server-name" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="shortName" class="req">门店简称：</label>
                    </div>
                    <div class="input">
                        <form:input path="shortName" cssClass="small required min-length-0 max-length-10" maxlength="10" />
                    	<span class="error" id="advice-required-shortName" style="display:none"></span>
                        <span class="error" id="advice-min-length-shortName" style="display:none"></span>
                        <span class="error" id="advice-max-length-shortName" style="display:none"></span>
                        <span class="error" id="advice-server-shortName" style="display:none"></span>
                    </div>
                </div>
                <div  class="field" >
                    <div class="label noinput"><label for="areaCode" class="req">归属地市：</label></div>
                    <div class="input">
                    	<input type="text" id="areaName" name="areaName"  value="${regionName}" readonly="readonly" class="small required min-length-0 max-length-50" maxlength="50" />
                    	<span class="error" id="advice-required-areaName" style="display:none"></span>
                        <span class="error" id="advice-min-length-areaName" style="display:none"></span>
                        <span class="error" id="advice-max-length-areaName" style="display:none"></span>
                        <span class="error" id="advice-server-areaName" style="display:none"></span>
					</div>
                    <input type="hidden" id="areaCode" name="areaCode" />
                </div>
                <div class="field">
					<div class="label label-radio">
						<label for="sort" class="req">签约折扣门店:</label>
					</div>
					<div class="radios">
						<div class="radio">
							<input type="radio" id="sort1" name="sort" value="1" <c:if test="${shop.sort == '1'}">checked="checked"</c:if> <c:if test="${method == 'add'}">checked="checked"</c:if> onclick="javascript:showDiv();" /><label for="sort1">是</label>
							<input type="radio" id="sort0" name="sort" value="0" <c:if test="${shop.sort == '0'}">checked="checked"</c:if> onclick="javascript:showDiv();" /><label for="sort0">否</label>
						</div>
						<span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
					</div>
				</div>	
				<div class="field" id="detailDiv" <c:if test="${shop.sort == '0'}">style="display:none"</c:if>>
                    <div class="label label-textarea">
                        <label for="discountDetail" class="req">折扣内容：</label>
                    </div>
                    <div class="input">
                        <form:input path="discountDetail" cssClass="small required max-length-50" />
                    	<span class="error" id="advice-required-discountDetail" style="display:none"></span>
                        <span class="error" id="advice-max-length-discountDetail" style="display:none"></span>
                        <span class="error" id="advice-server-discountDetail" style="display:none"></span>
                    </div>
                </div>       
                <div class="field">
					<div class="label label-radio">
						<label for="isChain" class="req">是否连锁:</label>
					</div>
					<div class="radios">
						<div class="radio">
							<input type="radio" id="isChain1" name="isChain" value="1" <c:if test="${shop.isChain == '1'}">checked="checked"</c:if> <c:if test="${method == 'add'}">checked="checked"</c:if> onclick="javascript:showDiv();" /><label for="isChain1">是</label>
							<input type="radio" id="isChain0" name="isChain" value="0" <c:if test="${shop.isChain == '0'}">checked="checked"</c:if> onclick="javascript:showDiv();" /><label for="isChain0">否</label>
						</div>
						<span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
					</div>
				</div>
				<div id="isChainTrue">
				<div class="field">
					<div class="label label-radio">
						<label for="isBaseShop" class="req">是否总店:</label>
					</div>
					<div class="radios">
						<div class="radio">
							<input type="radio" id="isBaseShop1" name="isBaseShop" value="1" <c:if test="${shop.isBaseShop == '1'}">checked="checked"</c:if> <c:if test="${method == 'add'}">checked="checked"</c:if> /><label for="isBaseShop1">是</label>
							<input type="radio" id="isBaseShop0" name="isBaseShop" value="0" <c:if test="${shop.isBaseShop == '0'}">checked="checked"</c:if> /><label for="isBaseShop0">否</label>
						</div>
						<span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
					</div>
				</div>
				<div class="field">
                    <div class="label">
                        <label for="baseShopName" class="req">总店名称：</label>
                    </div>
                    <div class="input">
                        <form:input path="baseShopName" cssClass="small required min-length-0 max-length-25" maxlength="25" />
                    	<span class="error" id="advice-required-baseShopName" style="display:none"></span>
                    	<span class="error" id="advice-min-length-baseShopName" style="display:none"></span>
                        <span class="error" id="advice-max-length-baseShopName" style="display:none"></span>
                        <span class="error" id="advice-server-baseShopName" style="display:none"></span>
                    </div>
                </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="adType" class="req">门店终端:</label>
                    </div>
                    <div class="select">                    	
                        <select id="posSelect" name="posSelect" class="validate-selection">
                            <option value="">--请选择--</option>
                            <c:forEach items="${sysPosList }" var="item">
                        	    <option value="${item.id }">${item.name }</option>
                        	</c:forEach>
                        </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="error" id="advice-required-posSelect" style="display:none"></span>
	                    <span class="error" id="advice-server-posSelect" style="display:none"></span>
                        <button type="button" id="addShopPosButton" class="common_btn" style="display: none">添加选中</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
                <div class="field" id="shopPosList" <c:if test="${empty shopPosLinkList}">style="display: none"</c:if>>
                    <div class="label">
                        <label for="pos">已配置终端：</label>
                    </div>
					<div class="select">
						<table class="datalist fixwidth" id="posListTable">
							<tr>
				                <th nowrap="nowrap" width="100">终端</th>
				                <th nowrap="nowrap" width="100">设备号</th>
				                <th nowrap="nowrap" width="100">标识</th>
				                <th nowrap="nowrap" width="80">操作</th>
				            </tr>
							<c:forEach items="${shopPosLinkList}" var="item">
				            <tr>
				                <td nowrap="nowrap" width="100">${item.sysPos.name}</td>
				                <td nowrap="nowrap" width="100">${item.posNo}</td>
				                <td nowrap="nowrap" width="100">${item.terminalId}</td>
				                <td nowrap="nowrap" width="80">
					                <ct:display model="shop_list" btn="del_btn">    	
					                <input type="button" onclick="deletePosItem(this, 'pos_delete/${item.id}');" class="common_btn" value="删除">
					                </ct:display>
				                </td>
				            </tr>
				            </c:forEach>
						</table>
					</div>
                </div>
               	<div class="field">
                    <div class="label">
                        <label for="tag" class="req">门店标签：</label>
                    </div>                    
                    <div class="input">
                        <input type="text" id="tag" name="tag" placeholder="各个标签以分号;隔开" title="各个标签以分号;隔开" class="small required max-length-5000" 
                        <c:if test="${not empty shopTagList}">value="<c:forEach items="${shopTagList}" var="item"  varStatus="i">${item.tag}<c:choose><c:when test="${i.last}"></c:when><c:otherwise>;</c:otherwise></c:choose></c:forEach>"</c:if> />
                        <span class="error" id="advice-required-tag" style="display:none"></span>
                        <span class="error" id="advice-max-length-tag" style="display:none"></span>
                        <span class="error" id="advice-server-tag" style="display:none"></span>
                    </div>
                </div>
            
              <div class="field">
					<div class="label">
						<label for="startTime" class="req">有效开始时间:</label>
					</div>
					<div class="input">
	 
                        <input type="text"  id="startTime" name="startTime"
                        
                         value="<ct:time source="${shop.startTime}"   sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss"/>" readOnly  class="date required" 
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'stopTime\')||\'2015-10-01\'}'})"/>
						<span class="error" id="advice-required-startTime" style="display:none"></span>             
                        <span class="error" id="advice-server-startTime" style="display:none"></span>
                        
                        
                        
                       
					</div>
				</div>
				<div class="field">
					<div class="label">
						<label for="stopTime" class="req">有效结束时间:</label>
					</div>
					<div class="input">
					
                        <input type="text" id="stopTime" name="stopTime"
								 value="<ct:time source="${shop.stopTime }" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss"/>"	 readOnly  class="date required"
									    onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,minDate:'#F{$dp.$D(\'startTime\')||\'2020-10-01\'}'})" />
						<span class="error" id="advice-required-stopTime" style="display:none"></span>
                        <span class="error" id="advice-server-stopTime" style="display:none"></span>
					</div>
				</div>
            
            
            
				<div class="field">
                    <div class="label">
                        <label for="address" class="req">门店地址：</label>
                    </div>
                    <div class="input">
                        <form:input path="address" cssClass="small required min-length-0 max-length-50" maxlength="50" />
                    	<span class="error" id="advice-required-address" style="display:none"></span>
                    	<span class="error" id="advice-min-length-address" style="display:none"></span>
                        <span class="error" id="advice-max-length-address" style="display:none"></span>
                        <span class="error" id="advice-server-address" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="phone" class="req">联系电话：</label>
                    </div>
                    <div class="input">
                        <form:input path="phone" cssClass="small required validate-digits min-length-0 max-length-25" maxlength="25" />
                    	<span class="error" id="advice-required-phone" style="display:none"></span>
                    	<span class="error" id="advice-validate-digits-phone" style="display:none"></span>
                    	<span class="error" id="advice-min-length-phone" style="display:none"></span>
                        <span class="error" id="advice-max-length-phone" style="display:none"></span>
                        <span class="error" id="advice-server-phone" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="openTime" class="req">营业时间：</label>
                    </div>
                    <div class="input">
                        <form:input path="openTime" cssClass="small required min-length-0 max-length-25" maxlength="25" />
                    	<span class="error" id="advice-required-openTime" style="display:none"></span>
                    	<span class="error" id="advice-min-length-openTime" style="display:none"></span>
                        <span class="error" id="advice-max-length-openTime" style="display:none"></span>
                        <span class="error" id="advice-server-openTime" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="remark" class="req">详细介绍：</label>
                    </div>
                    <div class="input">
                    	<ht:xheditor/>
                        <form:textarea path="remark" cols="50" rows="8" cssClass="cxheditor required max-length-100" />
                    	<span class="error" id="advice-required-remark" style="display:none"></span>
                        <span class="error" id="advice-max-length-remark" style="display:none"></span>
                        <span class="error" id="advice-server-remark" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="map">地图：</label>
                    </div>                    
                    <div class="select">
                    	<p class="showOrHideMap" style="font-weight: bold;cursor: pointer">显示/隐藏地图<c:if test="${not empty shop.mapLong}">(点开地图，查看门店所处地理位置)</c:if></p>
                    	<div class="mapShowOrHide" style="display: none;">
                    	<table width="747px"  border="0" cellpadding="0" cellspacing="2" bgcolor="#FFFFFF" height='100%'>
							<tr height="100%">
								<td rowspan="2" style="width:500px;height:100%;"><div id="map" style="width:500px;height:400px;"></div></td>
								<td style="width:247px;border-left:1px solid #666;" valign="top" >
									<div style="padding:5px 0px 0px 5px;font-size:12px;height:290px">
										<div><font style="font-size: medium;font-weight: bold">关键字搜索：</font><br /><input type="text" id="city" name="city" value="<c:if test="${not empty regionName}">${regionName}</c:if>" style="width:150px;height:20px;margin-top:3px;" placeholder="地市" title="地市" /><br /><input type="text" id="keyword" name="keyword" style="width:150px;height:20px;margin-top:3px;" placeholder="输入关键字搜索" title="输入关键字搜索" /><br /><input type="button" value="查询" onclick="keywordSearch()" class="common_btn" style="margin-left:102px;margin-top:3px;" /></div>
										<div id="result" name="result" style="height:507px;overflow:auto;width:247px;margin-top:5px"></div>
								    </div>
							    </td>
						   </tr>
						</table>
						注：在地图上单击及可获取坐标
                    	</div>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="mapLong" class="req">门店经度：</label>
                    </div>
                    <div class="input">
                        <input id="mapLong" name="mapLong" placeholder="可单击地图或手工配置门店经度" title="可单击地图或手工配置门店经度" value="${shop.mapLong}" class="small required validate-number min-length-0 max-length-20" maxlength="20" />
                    	<span class="error" id="advice-required-mapLong" style="display:none"></span>
                    	<span class="error" id="advice-validate-number-mapLong" style="display:none"></span>
                        <span class="error" id="advice-min-length-mapLong" style="display:none"></span>
                        <span class="error" id="advice-max-length-mapLong" style="display:none"></span>
                        <span class="error" id="advice-server-mapLong" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="mapDim" class="req">门店纬度：</label>
                    </div>
                    <div class="input">
                        <input id="mapDim" name="mapDim" placeholder="可单击地图或手工配置门店纬度" title="可单击地图或手工配置门店纬度" value="${shop.mapDim}" class="small required validate-number min-length-0 max-length-20" maxlength="20" />
                    	<span class="error" id="advice-required-mapDim" style="display:none"></span>
                    	<span class="error" id="advice-validate-number-mapDim" style="display:none"></span>
                        <span class="error" id="advice-min-length-mapDim" style="display:none"></span>
                        <span class="error" id="advice-max-length-mapDim" style="display:none"></span>
                        <span class="error" id="advice-server-mapDim" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="busLine">公交线路：</label>
                    </div>
                    <div class="input">
                        <form:input path="busLine" cssClass="small max-length-25" maxlength="25" />
                        <span class="error" id="advice-max-length-busLine" style="display:none"></span>
                        <span class="error" id="advice-server-busLine" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="area">面积：</label>
                    </div>
                    <div class="input">
                        <form:input path="area" cssClass="small max-length-10" maxlength="10" />
                        <span class="error" id="advice-max-length-area" style="display:none"></span>
                        <span class="error" id="advice-server-area" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="parkPlace">停车位：</label>
                    </div>
                    <div class="input">
                        <form:input path="parkPlace" cssClass="small max-length-10" maxlength="10" />
                        <span class="error" id="advice-max-length-parkPlace" style="display:none"></span>
                        <span class="error" id="advice-server-parkPlace" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="avgSpend">人均消费：</label>
                    </div>
                    <div class="input">
                        <form:input path="avgSpend" cssClass="small validate-number max-length-8" maxlength="8" />元
                    	<span class="error" id="advice-validate-number-avgSpend" style="display:none"></span>
                        <span class="error" id="advice-max-length-avgSpend" style="display:none"></span>
                        <span class="error" id="advice-server-avgSpend" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="roomNum">包间数：</label>
                    </div>
                    <div class="input">
                        <form:input path="roomNum" cssClass="small max-length-25" maxlength="25" />
                        <span class="error" id="advice-max-length-roomNum" style="display:none"></span>
                        <span class="error" id="advice-server-roomNum" style="display:none"></span>
                    </div>
                </div>
               	<div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />                    
                    <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
<script type="text/javascript">
$().ready(function() {
	
	with (fm) {
		//是否折扣选择
		if(sort[0].checked) {
			detailDiv.style.display="block";
		} else if(sort[1].checked){
			detailDiv.style.display="none";
		} 
		//连锁则显示总店与否选择
		if(isChain[0].checked) {
			isChainTrue.style.display="block";
		} else if(isChain[1].checked){
			isChainTrue.style.display="none";
		}
	}
	
	//选择商户
   	$("#storeName").click(function(){
		selectStore("acShopId","storeName",'',{shopClass:'2',status:'3'});
	});

	selectRegionCallBack("#areaName",
						 function(id,txt){
							$("#areaCode").val(id);
							$("#areaName").val(txt);
							//自动填充地图地市
						 	$("#city").val(txt);
						 },
						 '${regionCode}',
						 {maxItems:1, level:2});	
	
	$("#prov").change(function(){
		var prov = $(this).val();
		$.ajax({
			   type: "POST",
			   url: "getCityList.do",
			   cache : false, 
			   dataType : "json",
			   data: {parentRegionId:prov},
			   success: function(msg){
				   $("#areaCode").empty();   
				    for(var i=0;i<msg.length;i++){
				    $("#areaCode").append("<option value='"+msg[i].id+"'>"+msg[i].regionName+"</option>"); 
				    }
			   }
		});
	});
	
	$("#posSelect").change(function(){
		var posSelect = $(this).val();	
		if(posSelect != ""){
			$("#addShopPosButton").show();
		}else{
			$("#addShopPosButton").hide();	
		}
	});
	
	$("#addShopPosButton").click(function(){
		var pos = document.getElementById("posSelect");
		var posSelect = pos.value;
		var posOption;
		for(var i = 0;i < pos.length;i++){
		    if(pos[i].selected == true){	
		    	if(window.navigator.userAgent.toLowerCase().indexOf("firefox")>0){ //firefox innerText define
				  HTMLElement.prototype.__defineGetter__( "innerText",
				  function(){
				  var anyString = "";
				  var childS = this.childNodes;
				  for(var i=0; i <childS.length; i++) {
				  if(childS[i].nodeType==1)
				  anyString += childS[i].tagName=="BR" ? '\n' : childS[i].innerText;
				  else if(childS[i].nodeType==3)
				  anyString += childS[i].nodeValue;
				  }
				  return anyString;
				  }
				  );
				  HTMLElement.prototype.__defineSetter__( "innerText",
				  function(sText){
				  this.textContent=sText;
				  }
				  );
				}	
		    	posOption = pos[i].innerText;
		    } 
		}
		if(posSelect != ""){
			var posContent = "<div class='field'><div class='label label-textarea'><label for='pos' class='req'>选中终端：</label></div><div class='checkboxes'><input type='hidden' name='posId' value='" + posSelect + "'/><label >" + posOption + "</label>&nbsp;&nbsp;&nbsp;&nbsp;设备号：<input type='text' name='posNo' class='small required min-length-0 max-length-25' maxlength='25'/>&nbsp;&nbsp;&nbsp;&nbsp;终端标识：<input type='text' name='terminalId' class='small max-length-25' maxlength='25'/>&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' onclick='deletePos(this);' class='common_btn'>删除</button></div></div>";
	    	$("#addShopPosButton").parent().parent().after(posContent);
    	}
  	});
	
	//selectItemType("#typeName",
	//			   function(id,txt){$("#shopTypeId").val(id);$("#typeName").val(txt);},
	//			   {type:1});
				   
	$("#typeName").click(function() {
		selectType(1,'typeId','typeName')
	});
	
	$(".showOrHideMap").click(function(){
    	$(".mapShowOrHide").slideToggle("fast");
  	});
  	
	 //获取来源地址
	 var url = document.referrer;
	 $("#backUrl").val(url);
});
</script>
</body>
</html>