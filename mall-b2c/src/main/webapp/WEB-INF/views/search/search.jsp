<%@page import="com.cplatform.b2c.util.PathUtil"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
		<link href="../css/base.css" rel="stylesheet" type="text/css" />
		<link href="../css/layout.css" rel="stylesheet" type="text/css" />
		<link href="../css/search.css" rel="stylesheet" type="text/css" />
		<link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/search.js"></script>
		<script type="text/javascript" src="../js/ad.js"></script>
		<script type="text/javascript" src="../js/jquery.cookie.js"></script>
		<title>搜索-移动商城</title> 
		<script type="text/javascript">
		<c:if test="${keyword == 'G3天地' }">
		var GLOBAL_CHANNEL_INDEX = 2;
		</c:if>
		
	 function topNagivatePage(){
		var totalPage = '${totalPage}';
		if(curpage==1){
			$("#top_pagi").append("<span class='prev-disabled'>上一页<b></b></span>");
		}else{
			$("#top_pagi").append("<a href='javascript:void(0)' onclick='doPage("+curpage+","+totalPage+",1)' class='next' >上一页<b></b></a>");
		}
		if(curpage==totalPage || totalPage==0){
			$("#top_pagi").append("<span class='prev-disabled'>下一页<b></b></span>");
		}else{
			$("#top_pagi").append("<a href='javascript:void(0)' onclick='doPage("+curpage+","+totalPage+",0)' class='next' >下一页<b></b></a>");
		}
		
	}
	
	 $(document).ready(function(){
			var str = "<a>全部结果</a>";
  			var menu = new Array();
  			var menuTab = eval(${menuMap});
  			if(menuTab.flag=="ok"){
  				for(var i=0;i<menuTab.menu.length;i++){
		  			str +=  "<span>&nbsp;&gt;&nbsp;<a href='"+menuTab.menu[i].href+"'>"+menuTab.menu[i].name+"</a></span>";
					if(i==(menuTab.menu.length-1)){
						$("#noKeyword").html(menuTab.menu[i].name);
						$("#noData").html(menuTab.menu[i].name);
						keyword = menuTab.menu[i].name;
					}
  				}
  			}else if(menuTab.flag=="no"){
  				str +=  "<span>&nbsp;&gt;&nbsp;<a href='"+makeUrl('${webroot}','${keyword}',store_id,'${region_code}',null,brand,sort,'1',myparams,hideType)+"'>"+'${keyword}'+"</a></span>";
  			}
  		
			$(".breadcrumb").append(str);
			
			$(".search_submit").click(function(){
				document.search.submit();
			});
			$("#keyword").val('${keyword}');
			if($(".tb_list_menu_bt h2").html()==""){
				$(".tb_list_menu_bt h2").html(keyword);
			}
			if('${type_id}'!=null && '${type_id}'!=""){
				jsonMenu('${webroot}search/menu.chtml', {type_id:'${type_id}',keyword:'${keyword}'});
			}else{
				jsonMenu('${webroot}search/menu.chtml', {type_id:'${json.TYPEID }',keyword:'${keyword}'});
			}
			
			 
});
	</script>
</head>

<body id="search">
	${headhtml }
	<div class="wrapper mauto">
		<div class="breadcrumb"></div>
		<div id="sidebar" class="w240 fl tb_list_left">
			<div class="tb_list_menu_bt">
				<h2 style="overflow: hidden;white-space:nowrap;text-overflow:ellipsis;">${keyword }</h2>
			</div>

			<div class="tb_list_menu"></div>
			<div id="pro"></div>
		</div>
		<c:if test="${json.RET!='0000'}">
			<div id="search_container" class="w750 bt10 fr">
					<div id="select">
						<div class="mt">
							<h1 style="overflow: hidden;white-space:nowrap;text-overflow:ellipsis;width: 730px;">
								搜索关键词“<strong id="noKeyword">${keyword }</strong>”
							</h1>
						</div>
						<dl class="fore fore1" id="selected_attrs">

							<dt>已选条件：</dt>

							<dd id="select_dd">
								<a id="all-revocation" href="javascript:void(0)"
									onclick="reset_all()">全部撤消</a>
							</dd>

						</dl>
						<!--<c:if test="${json.PARAMS!=null }">
							<c:forEach items="${json.PARAMS}" var="params" varStatus="status">
								<dl id="clickName${status.index}">
									<dt>${params.key }：</dt>
									<dd>
										<c:forEach items="${params.value }" var="values">
											<div>
												<a title="${values }" href="javascript:void(0)"
													onclick="attr_click('${params.key }:${values }',${status.index})">${values
													}</a>
											</div>
										</c:forEach>
									</dd>
								</dl>
							</c:forEach>
						</c:if>-->
						<!-- 查询条件隐藏域 -->
						<input type="hidden" id="keyword" name="keyword" value="${keyword }" /> 
						<input type="hidden" id="store_id" name="store_id" value="${store_id }" /> 
						<input type="hidden" id="region_code" name="region_code" value="${region_code }" /> 
						<input type="hidden" id="type_id" name="type_id" value="${type_id }" /> 
						<input type="hidden" id="brand" name="brand" value="${brand }" /> 
						<input type="hidden" id="sort" name="sort" value="${sort }" /> 
						<input type="hidden" id="curpage" name="curpage" value="${curpage }" />
						<input type="hidden" id="searchSource" name="searchSource" value="${searchSource }" />
						<!-- 是否点击了条件选择 -->
						<input type="hidden" id="myparams" name="myparams"
							value="${myparams }" /> <input type="hidden" id="hideType"
							name="hideType" value="${hideType }" /> <input type="hidden"
							id="webroot" name="webroot" value="${webroot }" />
					</div>

					<div class="clearfix"></div>
					<div id="filter" class="mt10">
						<div class="fore1">
							<dl class="order">
								<dt>排序：</dt>
								<dd class="curr">
									<a href="#s" onclick>人气</a><b></b>
								</dd>
								<dd class>
									<a href="#s" onclick="ss_sort('2')">价格</a><b></b>
								</dd>
								<dd class>
									<a href="#s" onclick="ss_sort('4')">上架时间</a><b></b>
								</dd>
							</dl>
							<div class="pagin pagin-m" id="top_pagi">
								<span class="text">${curpage}/${totalPage }</span>
							</div>
							<div class="total">
								<span>共<strong>${json.TOTAL }</strong>个商品</span>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
					<div class="shop_detail">
						<div class="shop_tab">
							<div id="no_found">
								<h1>
									<c:if test="${json.RET!='500'}">
									抱歉，没有找到与“<strong id="noData">${keyword }</strong> ”相关的商品或搜索超时
									</c:if>
									<c:if test="${json.RET=='500'}">
										${json.MSG}
									</c:if>
								</h1>
								<dl>
									<dt>建议您：</dt>
									<dd>
										<div>1、看看输入的文字是否有误</div>
										<div>2、调整关键字，如：“nokian97”改成“nokia n97”</div>
										<div>
											3、重新搜索<input type="text" id="keyword_2" name="keyword_2"
												onmouseover="this.focus()"
												onkeydown="javascript:if(event.keyCode==13){noDataSearch();return false;}"
												class="text"><input type="button" class="btn-search"
												onclick="noDataSearch()" value="搜索">
										</div>
									</dd>
								</dl>
							</div>
						</div>
			</div>
	</div>
	</c:if>
	<c:if test="${json.RET=='0000' }">
		<div id="search_container" class="w750 bt10 fr">
			<div id="select">
				<div class="mt">
					<h1 style="overflow: hidden;white-space:nowrap;text-overflow:ellipsis;width: 730px;">
						搜索关键词“<strong id="noKeyword">${keyword }</strong>”
					</h1>
				</div>
				<dl class="fore fore1" id="selected_attrs">

					<dt>已选条件：</dt>

					<dd id="select_dd">
						<a id="all-revocation" href="javascript:void(0)"
							onclick="reset_all()">全部撤消</a>
					</dd>

				</dl>
				<!--<c:if test="${json.PARAMS!=null }">
					<c:forEach items="${json.PARAMS}" var="params" varStatus="status">
						<dl id="clickName${status.index}">
							<dt>${params.key }：</dt>
							<dd>
								<c:forEach items="${params.value }" var="values">
									<div>
										<a title="${values }" href="javascript:void(0)"
											onclick="attr_click('${params.key }:${values }',${status.index})">${values
											}</a>
									</div>
								</c:forEach>
							</dd>
						</dl>
					</c:forEach>
				</c:if>-->
				<!-- 查询条件隐藏域 -->
				<input type="hidden" id="keyword" name="keyword" value="${keyword }" />
				<input type="hidden" id="store_id" name="store_id" value="${store_id }" />
				<input type="hidden" id="type_id" name="type_id" value="${type_id }" />
				<input type="hidden" id="region_code" name="region_code" value="${region_code }"/> 
				<input type="hidden" id="brand" name="brand" value="${brand }" /> 
				<input type="hidden" id="sort" name="sort" value="${sort }" /> 
				<input type="hidden" id="curpage" name="curpage" value="${curpage }" />
				<input type="hidden" id="searchSource" name="searchSource" value="${searchSource }" />
				<!-- 是否点击了条件选择 -->
				<input type="hidden" id="myparams" name="myparams"
					value="${myparams }" /> <input type="hidden" id="hideType"
					name="hideType" value="${hideType }" /> <input type="hidden"
					id="webroot" name="webroot" value="${webroot }" />
			</div>

			<div class="clearfix"></div>
			<div id="filter" class="mt10">
				<div class="fore1">
					<dl class="order">
						<dt>排序：</dt>
						<dd class="curr">
							<a href="#s" onclick>人气</a><b></b>
						</dd>
						<dd class>
							<a href="#s" onclick="ss_sort('2')">价格</a><b></b>
						</dd>
						<dd class>
							<a href="#s" onclick="ss_sort('4')">上架时间</a><b></b>
						</dd>
					</dl>
					<div class="pagin pagin-m" id="top_pagi">
						<span class="text">${curpage}/${totalPage }</span>
					</div>
					<div class="total">
						<span>共<strong>${json.TOTAL }</strong>个商品</span>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>

			<div class="shop_detail">


				<div class="shop_tab">
					<div class="shop_tab_nav" id="all_product"></div>

					<div class="shop_tab_wrap">
						<div class="shop_content">
							<div class="item_wrap">
								<c:if test="${json.DATA!=null }">
									<c:forEach items="${json.DATA}" var="item">
										<div class="items">
											<c:if test="${item.G_ISECKILL eq 3}">
											<%--<div class="goods-discount-tag"></div>--%>
												<div class="icon_xsg"><img  src="../img/icon_xsg.png" /></div> 
											
											</c:if>
											<c:if test="${item.G_ISECKILL eq 9 }">
											    <div class="goods-discount-tag"></div>
											</c:if>
											<a class="items_img"
												href="<ct:path flag='1' id='${item.G_ID}'/>"><img
												width="160" height="160"
												src="<ct:path flag='2' id='${item.G_ID}'/>N3/${item.G_WEB_PATH }"
												alt="">
											</a>
											<div class="price">
												<span class="old_price">${item.G_MARKET_PRICE }</span> <span
													class="now_price">￥<strong>${item.G_SHOP_PRICE
														}</strong>
												</span>
											</div>
											<div class="vip_price">
											<c:if test="${!empty item.G_MIN_PRICE }" >
												<span class="vip_price_tips">会员最低价</span> <span
													class="vip_now_price">￥<strong>${item.G_MIN_PRICE
														}</strong>
												</span>
											</c:if>
											</div>
											<div class="procuts_title">
												<a href="<ct:path flag='1' id='${item.G_ID}'/>">${item.G_NAME }</a>
											</div>
											<c:if test="${fn:trim(item.G_MARKET_CONTENT)!=''&& item.G_MARKET_CONTENT!=null}">
												<div class="procuts_text">
													<span>${item.G_MARKET_CONTENT }</span>
												</div>
											</c:if>
											
										</div>
									</c:forEach>
								</c:if>
							</div>
						</div>
						${pageInfos }
					</div>

				</div>

			</div>

		</div>
	</c:if>
	</div>
	</div>
	<script type="text/javascript" src="../js/foot.js"></script>
	<script language="javascript" type="text/javascript"
		src="${webroot }ad/getAd.chtml?code=global&disfunction=displayAd240X320Pro&appendElement=pro&position=search&region_code=${region_code }"></script>
		
</body>
</html>