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
                <c:if test="${not empty online.actId}">
                <div class="field">
                    <div class="label noinput">
                        <label for="actId">活动编号：</label>
                    </div>
                    <div class="input">
                        ${online.actId}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty online.actName}">
                <div class="field">
                    <div class="label noinput">
                        <label for="actName">活动名称：</label>
                    </div>
                    <div class="input">
                        ${online.actName}
                    </div>
                </div>           
                </c:if>
                <c:if test="${not empty online.actDesc}">
                <div class="field">
                    <div class="label noinput">
                        <label for="actDesc">活动描述：</label>
                    </div>
                    <div class="input">
                        ${online.actDesc}
                    </div>
                </div> 
                </c:if>
                <c:if test="${not empty online.groupId}">
                <div class="field">
                    <div class="label noinput">
                        <label for="groupId">活动分类：</label>
                    </div>
                    <div class="input">
                        ${online.groupId}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty online.startTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="startTime">活动开始时间：</label>
                    </div>
                    <div class="input">
                        <ct:time source="${online.startTime}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty online.endTime}">
                <div class="field">
                    <div class="label noinput">
                        <label for="endTime">活动结束时间：</label>
                    </div>
                    <div class="input">
                        <ct:time source="${online.endTime}"/>
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty online.status}">
                <div class="field">
                    <div class="label noinput">
                        <label for="status">状态：</label>
                    </div>
                    <div class="input">
                        ${online.statusName}
                    </div>
                </div>
                </c:if>
                <c:if test="${not empty routerList}">
                <div id="routerList">
					<div class="select">
						<table class="datalist fixwidth" id="routerListTable">
							<tr>
				                <th nowrap="nowrap" width="60">指令</th>
				                <th nowrap="nowrap" width="100">特服号</th>
				                <th nowrap="nowrap" width="50">支付方式</th>
				                <th nowrap="nowrap" width="50">购买价格</th>
				            </tr>
							<c:forEach items="${routerList}" var="item">
				            <tr>
				                <td nowrap="nowrap">${item.command}</td>
				                <td nowrap="nowrap">${item.spCode}</td>
				                <td nowrap="nowrap">${item.payTypeName}</td>
				                <td nowrap="nowrap">${item.itemPrice}</td>
				            </tr>
				            </c:forEach>
						</table>
					</div>
                </div>
                </c:if>
                <div class="buttons">
               		<c:if test="${online.status == '0'}">
	               	 	<ct:display model="sms_act_online_query" btn="audit_btn">
	                   	<button type="button" class="common_btn" onclick="dealInfo('确定要审核吗？','operate/1/${online.actId}/view');">审核</button>
	                	</ct:display>
                   	</c:if>
                   	<c:if test="${online.status == '1'}">
	                   	<ct:display model="sms_act_online_query" btn="publish_btn">
		                <button type="button" class="common_btn" onclick="dealInfo('确定要发布吗？','operate/2/${online.actId}/view');">发布</button>
		                </ct:display>
	                </c:if>
	                <c:if test="${online.status == '2'}">
		                <ct:display model="sms_act_online_query" btn="pause_btn">
		                <button type="button" class="common_btn" onclick="dealInfo('确定要暂停吗？','operate/3/${online.actId}/view');">暂停</button>&nbsp;&nbsp;&nbsp;&nbsp;
		                </ct:display>
		                <ct:display model="sms_act_online_query" btn="down_line_btn">
		                <button type="button" class="common_btn" onclick="dealInfo('确定要下线吗？','operate/4/${online.actId}/view');">下线</button>
		                </ct:display>
	                </c:if>                	
                   	<c:if test="${online.status == '3'}">
	                   	<ct:display model="sms_act_online_query" btn="start_btn">
		                <button type="button" class="common_btn" onclick="dealInfo('确定要恢复吗？','operate/0/${online.actId}/view');">恢复</button>
		                </ct:display>
                   	</c:if>  
                   	
                   		<a href="${queryBackUrl}" class="btnAnchor">返回</a>              	
                </div>
            </div>
        </div>
	</div>
</div>
</body>
</html>