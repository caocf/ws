<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.include file="includes/importer.jsp" />
<ht:head selected="home"/>

<div class="w1000 mblock">
    <c:if test="${status eq 'already-validate' || status eq 'validate-fail'
     || status eq 'wrong-email' || status eq 'need-validate'
     || status eq 'validate-success' || status eq 'validate-send'}">
    <div class="shadow">
        <ul class="step step2">
            <li class="first"><span><em class="l"></em>1.填写开发者资料<em class="r"></em></span></li>
            <li class="curr"><span><em class="l"></em>2.验证邮箱<em class="r"></em></span></li>
            <li><span><em class="l"></em>3.创建应用<em class="r"></em></span></li>
            <li class="end"><span><em class="l"></em>4.应用上线<em class="r"></em></span></li>
        </ul>
    </div>
    </c:if>

    <c:choose>
        <c:when test="${status eq 'already-validate'}">
            <div class="tips_warn" style="height:200px;">
                <p>您的邮箱不需要进行验证<c:if test="${empty loginuser}">，<a href="${ctx}/login">请点击登录</a></c:if></p>
            </div>
        </c:when>
        <c:when test="${status eq 'locked'}">
            <div class="tips_warn" style="height:200px;">
                <p>对不起，该账号已锁定。</p>
            </div>
        </c:when>
        <c:when test="${status eq 'validate-fail'}">
            <div class="tips_warn" style="height:200px;">
                <p>邮箱验证失败，<a href="javascript:void(0);" onclick="resend('${param.email}');">请点击重新发送验证</a></p>
            </div>
        </c:when>

        <c:when test="${status eq 'wrong-email'}">
            <div class="tips_warn" style="height:200px;">
                <p>邮箱地址错误，请确认！</p>
            </div>
        </c:when>

        <c:when test="${status eq 'need-validate'}">
            <div class="tips_warn" style="height:200px;">
                <p>您的电子邮箱尚未通过验证，还无法创建应用</p>
                <div><small>当前邮箱：${loginuser.email} </small><br/>
                    <small>如果您未收到验证邮件，请尝试通过以下方法解决：<br/>
                        • <a href="javascript:void(0);" onclick="resend('${loginuser.email}');">点击此处，再次发送验证邮件</a><br/>
                        • <a href="${ctx}/profile/edit">修改开发者资料</a>中的邮箱地址，重新获取验证邮件<br/>
                        • 检查电子邮件信箱中的垃圾箱或广告箱，邮件有可能被误认为垃圾邮件或广告邮件。<br/>
                        • 直接发送邮件至mplus@c-platform.com或拨打电话13811502824至平台客服人员进行解决。</small>
                </div>
            </div>
        </c:when>

        <c:when test="${status eq 'validate-send'}">
            <div class="tips_ok" style="height:200px;">
                <p>验证邮件已经发至：${loginuser.email}<br/>
                    5秒后页面跳至 <a href="${ctx}/myapp">我的应用</a></p>
            </div>
            <script type="text/javascript">
                <!--
                setTimeout(function(){
                    location.href="${ctx}/myapp";
                },5000);
                //-->
            </script>
        </c:when>

        <c:when test="${status eq 'audit-success'}">
            <div class="tips_ok" style="height:200px;">
                <p>应用已提交审核，请等待审核通过，<a href="${ctx}/myapp/">点击返回</a></p>
            </div>
        </c:when>

        <c:when test="${status eq 'validate-success'}">
            <div class="tips_ok" style="height:200px;">
                <p>恭喜！您的开发者身份已激活，点击 <a href="${ctx}/myapp/create">创建应用</a></p>
            </div>
        </c:when>

        <c:when test="${status eq 'app-create-success'}">
            <div class="shadow">
                <h1 class="tl">应用创建成功</h1>
            </div>
            <div class="tips_ok">
                <p>已经成功获取App Key 和 App Sceret</p>
                <p class="appkey">
                    <em>App Key:</em><br/>  ${param.key} <br/>
                    <em style="width:100px;">App Secret:</em><br/> ${param.secret}</p>
                <p class="small">你可以使用App Key和App Secret这两个key值请求接口进行技术开发，<a href="${ctx}/myapp/${param.key}">详情请点击</a></p>
                <p class="appbtn"><a href="${ctx}/myapp" class="btn_add" id="submit"><em>完成创建</em></a></p>
            </div>
        </c:when>

        <c:when test="${status eq 'no-permission'}">
            <div class="tips_warn" style="height:200px;">
                <p>很抱歉，您的权限不足，不能访问此页面。（403）</p>
                <p style="font-size:14px;">如果您当前访问的地址没有问题，请联系我们：mplus@c-platform.com</p>
            </div>
        </c:when>

        <c:otherwise>
            <div class="tips_warn" style="height:200px;">
                <p>很抱歉，系统发生内部错误。（500）</p>
                <p style="font-size:14px;">如果您当前访问的地址没有问题，请联系我们：mplus@c-platform.com</p>
            </div>
        </c:otherwise>

    </c:choose>

</div>



<ht:foot />