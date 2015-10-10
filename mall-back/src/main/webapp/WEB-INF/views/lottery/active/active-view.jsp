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
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >活动编号:</label>
                    </div>
                    <div class="input">
                    ${active.id}
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >活动名称:</label>
                    </div>
                    <div class="input">
                    ${active.name}
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >活动时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${active.startTime}" tfmt="yyyy-MM-dd HH:mm:ss"/>~~<ct:time source="${active.stopTime}" tfmt="yyyy-MM-dd HH:mm:ss"/>
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >活动模板类型:</label>
                    </div>
                    <div class="input">
                    ${active.typeName}
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >活动状态:</label>
                    </div>
                    <div class="input">
                    ${active.statusName}
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >活动介绍:</label>
                    </div>
                    <div class="input">
                    ${active.activeDesc}
                    <c:if test="${empty active.activeDesc }">暂无活动介绍</c:if>
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >未中奖设置:</label>
                    </div>
                    <div class="input">
                    <c:if test="${active.unhit eq 0}">未中奖无奖品</c:if>
                    <c:if test="${active.unhit eq 1}">参与抽奖既赠奖品</c:if>
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >未中奖提示语:</label>
                    </div>
                    <div class="input">
                    ${active.unhitMsg}
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >创建时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${active.createTime}" tfmt="yyyy-MM-dd HH:mm:ss"/>
                    </div>
                </div>
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >可中奖次数:</label>
                    </div>
                    <div class="input">
                    <c:if test="${active.hitLimit <0}">无限制</c:if>
					<c:if test="${active.hitLimit >0}">${active.hitLimit }</c:if>
                    </div>
                </div>
                <c:if test="${not empty lotteryTargets }">
                <div class="field">
                    <div class="label noinput">
                        <label for="storeName" >活动用户下载:</label>
                    </div>
                    <div class="input">
                    	<a href="${ctx }/lottery/active/down?activeId=${active.id}">目标库活动用户.xls</a>
                    </div>
                </div>
                </c:if>
                <div class="buttons">
						<input type="button" class="common_btn" onclick="history.back();" value="返回" />
				</div>
            </div>
        </div>
	</div>
</div>
</body>
</html>