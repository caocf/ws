<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
</head>
<body>
<div id="content">
	<div class="box">
        <div class="form">
            <div class="fields">
                <c:if test="${not empty member.userName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="userName">登录名：</label>
                    </div>
                    <div class="input">
                        ${member.userName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.email}">
                <div class="field">
                    <div class="label noinput">
                        <label for="email">注册邮箱：</label>
                    </div>
                    <div class="input">
                        ${member.email}
                    </div>
                </div>           
                </c:if>
                <c:if test="${not empty member.realName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="realName">真实姓名：</label>
                    </div>
                    <div class="input">
                        ${member.realName}
                    </div>
                </div> 
                </c:if>
                <c:if test="${not empty member.terminalId}">
                <div class="field">
                    <div class="label noinput">
                        <label for="terminalId">手机号码：</label>
                    </div>
                    <div class="input">
                        ${member.terminalId}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.regTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="regTime">注册时间：</label>
                    </div>
                    <div class="input">
                        <ct:time source="${member.regTime}" tfmt="yyyy-MM-dd"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.status}">
                <div class="field">
                    <div class="label noinput">
                        <label for="status">状态：</label>
                    </div>
                    <div class="input">
                        ${member.statusName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.vid}">
                <div class="field">
                    <div class="label noinput">
                        <label for="vid">VID：</label>
                    </div>
                    <div class="input">
                        ${member.vid}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.nickName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="nickName">昵称：</label>
                    </div>
                    <div class="input">
                        ${member.nickName}
                    </div>
                </div> 
                </c:if>
                <c:if test="${not empty member.areaCode}">
                <div class="field">
                    <div class="label noinput">
                        <label for="areaCode">归属地：</label>
                    </div>
                    <div class="input">
                        ${member.areaCode}
                    </div>
                </div> 
                </c:if>
                <c:if test="${not empty member.sexName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="sex">性别：</label>
                    </div>
                    <div class="input">
                        ${member.sexName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.birthday}">
				<div class="field">
                    <div class="label noinput">
                        <label for="birthday">生日：</label>
                    </div>
                    <div class="input">
                        <ct:time source="${member.birthday}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.openedName}">
				<div class="field">
                    <div class="label noinput">
                        <label for="opened">信息是否公开：</label>
                    </div>
                    <div class="input">
                        ${member.openedName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.qq}">
				<div class="field">
                    <div class="label noinput">
                        <label for="qq">QQ：</label>
                    </div>
                    <div class="input">
                        ${member.qq}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.remark}">
                <div class="field">
                    <div class="label label-textarea">
                        <label for="remark">个人信息描述：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="remark" readonly="true" cols="50" rows="8" cssClass="required max-length-500" />
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.signature}">
                <div class="field">
                    <div class="label label-textarea">
                        <label for="signature" class="req">个性签名：</label>
                    </div>
                    <div class="input">
                        <form:textarea path="signature" readonly="true" cols="50" rows="8" cssClass="required max-length-500" />
                    </div>
                </div>
                </c:if>
				<div class="field">
                    <div class="label noinput">
                        <label for="regSource">注册来源：</label>
                    </div>
                    <div class="input">
                        SSO
                    </div>
                </div>
                <c:if test="${not empty member.userLevelName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="userLevel">级别：</label>
                    </div>
                    <div class="input">
                        ${member.userLevelName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty member.cartUuid}">
                <div class="field">
                    <div class="label noinput">
                        <label for="cartUuid">购物车标识：</label>
                    </div>
                    <div class="input">
                        ${member.cartUuid}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty resultMap}">
                <div class="field">
                    <div class="label noinput">
                        <label for="cartUuid">品牌：</label>
                    </div>
                    <div class="input">
                    <c:choose>
        			<c:when test="${resultMap.scoreStatusCode == '0'}">
                    <c:choose>
        			<c:when test="${not empty resultMap.brand}">
                        ${resultMap.brand}
                    </c:when>
                    <c:otherwise>
                    <a href="#this" title="状态：${resultMap.scoreStatusCode}，描述：${resultMap.scoreStatusText}">暂无数据</a>
                    </c:otherwise>
                    </c:choose>
                    </c:when>
                	<c:otherwise>
                		<a href="#this" title="状态：${resultMap.scoreStatusCode}，描述：${resultMap.scoreStatusText}">无</a>
                    </c:otherwise>
                	</c:choose>
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="cartUuid">积分余额：</label>
                    </div>
                    <div class="input">
                    <c:choose>
        			<c:when test="${resultMap.scoreStatusCode == '0'}">
                    <c:choose>
        			<c:when test="${not empty resultMap.score}">
                        ${resultMap.score}
                    </c:when>
                    <c:otherwise>
                    <a href="#this" title="状态：${resultMap.scoreStatusCode}，描述：${resultMap.scoreStatusText}">暂无数据</a>
                    </c:otherwise>
                    </c:choose>
                    </c:when>
                	<c:otherwise>
                		<a href="#this" title="状态：${resultMap.scoreStatusCode}，描述：${resultMap.scoreStatusText}">无</a>
                    </c:otherwise>
                	</c:choose>
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="cartUuid">商城币余额：</label>
                    </div>
                    <div class="input">
                    <c:choose>
        			<c:when test="${resultMap.coinStatusCode == '0'}">
        			<c:choose>
        			<c:when test="${not empty resultMap.coin}">
                        ${resultMap.coin}
                    </c:when>
                    <c:otherwise>
                    <a href="#this" title="状态：${resultMap.coinStatusCode}，描述：${resultMap.coinStatusText}">暂无数据</a>
                    </c:otherwise>
                    </c:choose>
                    </c:when>
                	<c:otherwise>
                		<a href="#this" title="状态：${resultMap.coinStatusCode}，描述：${resultMap.coinStatusText}">无</a>
                    </c:otherwise>
                	</c:choose>
                    </div>
                </div>
                </c:if>
                <div class="buttons">
                	<c:if test="${member.status == '1'}">
                		<button type="button" class="common_btn" onclick="dealInfo('确定要暂停吗？','operate/1/${member.id}/view');">暂停</button>
                	</c:if>
                	<c:if test="${member.status == '2'}">
                		<button type="button" class="common_btn" onclick="dealInfo('确定要恢复吗？','operate/2/${member.id}/view');">恢复</button>
                	</c:if>
                	<input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
                	
            </div>
        </div>
	</div>
</div>
</body>
</html>