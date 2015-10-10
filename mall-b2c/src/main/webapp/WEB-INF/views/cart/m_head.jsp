<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>  
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.cplatform.b2c.util.AppConfig" %>
<%
ServletContext context = request.getSession().getServletContext();  
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
AppConfig appConfig = (AppConfig) ctx.getBean("appConfig");
pageContext.setAttribute("webRoot",appConfig.getWebRoot());

%>
<style>
body#movie #mosh_12580_header {
    z-index: 1000;
}
#movie_footer {
    background: none repeat scroll 0 0 #F7F7F7;
    border-top: 4px solid #E0E0E0;
    clear: both;
    height: auto;
    margin-top: 10px;
    padding: 15px 0 25px;
}
#movie_footer dl{ float:left; width:170px;}
#movie_footer dl.f_logo{ width:320px;}
#movie_footer dl.footer_phone{ width:150px;}
#movie_footer dl dt {
    color: #616161;
    font-family: "Microsoft Yahei";
    font-size: 16px;
    height: 30px;
    line-height: 30px;
    padding-bottom: 12px;
}

#movie_footer dl dd{ color:#616161;}

#movie_footer .footer_box dl dd li a {
    color: #666666;
    font-size: 12px;
    line-height: 20px;
    text-decoration: none;
}
#movie_footer .footer_box dl dd li a:hover {
    text-decoration: underline;
}
#movie_footer .footer_lists {
    overflow: hidden;
}
#movie_footer .footer_cotyright {
    clear: both;
    color: #666666;
    font-size: 12px;
    margin: 20px auto 0;
    overflow: hidden;
    text-align: center;
    width: 280px;
}
#movie_footer .certification {
    float: left;
}
#movie_footer .certification a img {
    border: 0 none;
}
#movie_footer .beian_copyright {
    float: right;
}
#movie_footer .beian_copyright .beian {
    overflow: hidden;
}
#movie_footer .beian_copyright .beian img {
    float: left;
}
#movie_footer .beian_copyright .beian span {
    float: left;
    line-height: 24px;
    padding-left: 5px;
}
#movie_footer .beian_copyright .copyright {
    clear: both;
    margin-top: 5px;
}
</style>
<div id="mosh_12580_header">
<script type="text/javascript" src="http://www.12580life.com/common/head.chtml?type=mini"></script>

    <div id="header_nav">
        <div id="header">
            <div class="wrapper mauto"><a id="logo" href="http://www.12580life.com" title="mo生活" target="_top"><img src="http://www.12580life.com/tmovie/img/logo.jpg" style="width:200px;height:60px"></a>
                <div id="city">
                	<a class="active" href="javascript:void(null);" id="showCity"></a>
                    <div id="citylist" style="display: none;">
                    <div>
                    	<dl id="showCityList">
                    	<dt>江苏：</dt>
                        <dd><a target="_top" href="javascript:void(null);" acode="0512" rcode="320500" spell="suzhou">苏州</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0519" rcode="320400" spell="changzhou">常州</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0513" rcode="320600" spell="nantong">南通</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0518" rcode="320700" spell="lianyungang">连云港</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0517" rcode="320800" spell="huaian">淮安</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0515" rcode="320900" spell="yancheng">盐城</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0514" rcode="321000" spell="yangzhou">扬州</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0511" rcode="321100" spell="zhenjiang">镇江</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0523" rcode="321200" spell="taizhou">泰州</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0527" rcode="321300" spell="suqian">宿迁</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="025" rcode="320100" spell="nanjing">南京</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0510" rcode="320200" spell="wuxi">无锡</a></dd>
                    	<dd><a target="_top" href="javascript:void(null);" acode="0516" rcode="320300" spell="xuzhou">徐州</a></dd>
                    	</dl>
                    </div>
                    </div>
                </div>
                <div id="search_head">
                    <form name="searchForm" id="searchForm" action="${webRoot}search/searchList/all.chtml" method="get">
						<div class="search_wrapper">
							<span class="icon_search"></span> <input type="text" class="mod_search_txt no_cur" autocomplete="off" name="keyWords" value="请输入你想搜索的 “商品”" onfocus="this.value = ''" />
							<button class="head_search_submit">
								<span class="hide_clip">搜索</span>
							</button>
						</div>
						<div class="search_keyword"></div>
					</form>
                </div>
            </div>
        </div>
        <div id="nav">
            <div class="wrapper mauto">
                <ul class="nav_list">
                    <li class="<c:if test="${param['naviCode'] == null || param['naviCode'] == ''}">on</c:if>"><a href="http://www.12580life.com/tmovie/" target="_top">首页</a></li>
                    <li class="<c:if test="${param['naviCode'] == 'hot'}">on</c:if>"><a href="http://www.12580life.com/tmovie/film/filmList/hot.chtml" target="_top">影片</a></li>
                    <li class="<c:if test="${param['naviCode'] == 'cinema'}">on</c:if>"><a href="http://www.12580life.com/tmovie/cinema/cinemaList.chtml" target="_top">影院</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script>
function getCodeInfo(regionCode){
	var codeList = [
	'320100_025_nanjing_南京',
	'320500_0512_suzhou_苏州',
	'320200_0510_wuxi_无锡',
	'320400_0519_changzhou_常州',
	'320600_0513_nantong_南通',
	'321000_0514_yangzhou_扬州',
	'321100_0511_zhenjiang_镇江',
	'321200_0523_taizhou_泰州',
	'320300_0516_xuzhou_徐州',
	'320900_0515_yancheng_盐城',
	'320800_0517_huaian_淮安',
	'320700_0518_lianyungang_连云港',
	'321300_0527_suqian_宿迁'
	];
	var tmpInfo;
	var tmpArr;
	for(var i=0; i<codeList.length; i++){
		tmpInfo = codeList[i];
		tmpArr = tmpInfo.split("_");
		if(tmpArr[0] == regionCode){
			return tmpInfo;
		}
	}
	return tmpInfo;
}

jQuery(function(){
	var head_region_code = parseInt('${param.regionCode}') && ('${param.regionCode}'.lastIndexOf('00') == 4) ? parseInt('${param.regionCode}'):jQuery.cookie('regionCode');
	if(!head_region_code){
		head_region_code = '320500';//默认苏州
	}
	if(head_region_code){
		var head_region_info = getCodeInfo(head_region_code);
		var head_region_arr = head_region_info.split("_");
		jQuery("#showCity").html(head_region_arr[3]+"市");
		jQuery("#showCityList a").show();
		jQuery("#showCityList a[rcode="+head_region_code+"]").hide();
	}
	
	jQuery("#mosh_12580_header #header #city").hover(function(){
		jQuery("#mosh_12580_header #header #city .active").addClass("hover");
		jQuery("#mosh_12580_header #header #citylist").show();
		},function(){
		jQuery("#mosh_12580_header #header #city .active").removeClass("hover");	
		jQuery("#mosh_12580_header #header #citylist").hide();
		}
	);
	
	jQuery("#mosh_12580_header #city #citylist a").click(function(){
		jQuery.cookie('areaCode',jQuery(this).attr("acode"), {
			expires : 90,
			domain: '12580life.com',
			path:'/'
		});
		jQuery.cookie('regionCode',jQuery(this).attr("rcode"), {
			expires : 90,
			domain: '12580life.com',
			path:'/'
		});
		top.location.href='${webRoot}channel/'+jQuery(this).attr("spell")+'.htm';
	});
	
});
</script>