<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>短信购活动信息</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="addAct" commandName="smsBuyActOnline"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
        <form:hidden path="actId"/>
            <div class="fields">
             
                <div class="field">
                    <div class="label noinput">活动编号：</div>
                    <div class="input">${smsBuyActOnline.actId}</div>
                </div>
               	
                 <div class="field">
                    <div class="label"> 活动名称：</div>
                    <div class="input"><!-- small medium large -->
                         ${smsBuyActOnline.actName}
                    </div>
                </div>
                
                <div class="field">
                    <div class="label"> 特服号：</div>
                    <div class="input">
                         ${smsBuyActOnline.spCode}
                    </div>
                </div>
                
                
                <div class="field">
                    <div class="label">活动描述：</div>
                    <div class="input">
                        ${smsBuyActOnline.actDesc}
                    </div>
                </div>
              <div class="field">
							<div class="label">
								限制地区：
							</div>
							<div class="input">
							${smsBuyActOnline.areaName}
							</div>
				</div>
				<div class="field">
							<div class="label">
								任务状态：
							</div>
							<div class="input">
							${smsBuyActOnline.statusName}
							</div>
				</div>
						
				<div class="field">
                    <div class="label">
                      	活动开始时间：
                    </div>
                    <div class="input">
                    <ct:time source="${smsBuyActOnline.startTime}"/>
                    </div>
                </div>
		         <div class="field">
                    <div class="label">
                       	 活动结束时间:
                    </div>
                    <div class="input">
                    <ct:time source="${smsBuyActOnline.endTime}"/>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>

<div class="container">
    <br/>
    <h3>商品指令列表</h3>
    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap">归属活动</th>
                <th nowrap="nowrap">商品名称</th>
                <th nowrap="nowrap">特服号</th>
                <th nowrap="nowrap">指令内容</th>
                <th nowrap="nowrap">商品支付方式</th>
                <th nowrap="nowrap">商品购买价格(元)</th>
                <th nowrap="nowrap">商品营销短信</th>
                <th nowrap="nowrap">审核状态</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.actId}</td>
                <td nowrap="nowrap">${item.itemName}</td>
                <td nowrap="nowrap">${item.spCode}</td>
                <td nowrap="nowrap">${item.command}</td>
                <td nowrap="nowrap">${item.payTypeName}</td>
                <td nowrap="nowrap"><fmt:formatNumber value="${item.itemPrice / 100}" pattern="0.00"/></td>
                <td nowrap="nowrap" class="ellipsis">${item.marketMsg }</td>
                <td nowrap="nowrap">${item.itemStatusName}</td>
            </tr>
            </c:forEach>
        </table>
        <ht:page pageData="${pageData}" />

        </c:if>
        <c:if test="${empty pageData}">
        <div class="note">
            <p class="i">目前没有商品指令相关记录!</p>
        </div>
        </c:if>
    </div>
    <div class="box">
<div class="form">
<div class="fields">
<div class="buttons">
         <a href="${queryBackUrl}" class="btnAnchor">返回</a>
</div>
</div>
</div>
</div>
</div>

</body>
</html>