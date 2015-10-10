<%@ taglib prefix="ct" uri="/cplatform-tag" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.include file="includes/importer.jsp" />
<ht:head selected="home"/>


<link rel="stylesheet" href="${res}/js/slide/agile_carousel.css" type='text/css'>


    <div class="w1000">

        <%--
        <div class="banner">
            <img src="${res}/images/banner.jpg"/>
            <div class="btn_create"><a href="#"><img src="${res}/images/btn_create.png"/></a></div>
        </div>
        --%>
        <div class="slideshow" id="flavor_1"></div>
        <script src="${res}/js/slide/agile_carousel.js"></script>
        <script>
            $.getJSON("${res}/js/slide/data.jsp", function(data) {
                $(document).ready(function(){
                    $("#flavor_1").agile_carousel({
                        carousel_data: data,
                        carousel_outer_width: 1000,
                        carousel_outer_height: 360,
                        carousel_height: 360,
                        slide_width: 1000,
                        slide_height: 360,
                        transition_time: 400,
                        continuous_scrolling: false, //true
                        //control_set_1: "numbered_buttons",
                        //no_control_set: "hover_previous_button,hover_next_button"
                        timer: 10000
                    });
                });
            });
        </script>


        <div class="feathure">
            <ul>
                <li><a href="${ctx}/doc/wiki/p_1_2"><div><img src="${res}/images/f_1.png"/></div>新手指南</a></li>
                <li><a href="${ctx}/doc/wiki/p_2_1"><div><img src="${res}/images/f_2.png"/></div>API文档</a></li>
                <li><a href="${ctx}/doc/wiki/p_3_1"><div><img src="${res}/images/f_3.png"/></div>SDK下载</a></li>
                <li><a href="${ctx}/doc/wiki/p_4_1"><div><img src="${res}/images/f_4.png"/></div>常见问题</a></li>
            </ul>
        </div>
        <div class="gbox">
            <div class="gboxl">
                <h2><span>优秀应用案例</span></h2>
                <dl class="hotapp">
                    <dt><div class="appimg"><img src="${res}/images/spec/logo1.png"/></div></dt>
                    <dd><h3>宜搜搜索</h3><p>M+应用“#宜搜”依托M+短彩应用汇平台，利用先进的无线数据应用技术和搜索技术，通过运用和整合自然语言处理、信息提取、分布式计算、全文检索、人工智能、数据挖掘、中文分词等高新技术，像用户提供高度智能化数据检索服务，用户只需上行短信搜索任意内容，即可搜索到需要的详细内容信息。为手机用户和企业提供了移动互联网的最佳体验。</p></dd>
                </dl>
                <ul class="applist">
                    <li><div><img src="${res}/images/spec/logo2.png" alt="天气"/></div>天气</li>
                    <li><div><img src="${res}/images/spec/logo3.png" alt="冲浪资讯"/></div>冲浪资讯</li>
                    <li><div><img src="${res}/images/spec/logo4.png" alt="聊天室"/></div>聊天室</li>
                    <li><div><img src="${res}/images/spec/logo5.png" alt="MM应用集市"/></div>MM应用集市</li>
                    <li><div><img src="${res}/images/spec/logo6.png" alt="人品计算器"/></div>人品计算器</li>
                    <!-- li><div><img src="${res}/images/spec/logo7.png" alt="我们爱听冷笑话"/></div>我们爱听冷笑话</li -->
                </ul>
            </div>
            <div class="gboxr">
                <h2><a href="${ctx}/doc/messages">更多</a><span>最新动态</span></h2>
                <ul class="newslist">
                    <c:forEach items="${messagelist.content}" var="developerMessage" begin="0" end ="7" varStatus="status">
                        <li ${status.count==8 ? 'style="border-bottom:none;"' : ''}><span><ct:time source="${developerMessage.updateTime}" tfmt="yyyy-MM-dd"/></span><a target="_blank" href="${developerMessage.url}" title="${developerMessage.title}">${ct:cutString(developerMessage.title,29)}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>

    </div>


<ht:foot />


