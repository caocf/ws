<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/t.jsp"%>
<div class="row-fluid">
	<div class="span12">
		<form:form method="post" id="fm" commandName="dcUser"
			htmlEscape="true" acceptCharset="utf-8"
			cssClass="required-validate form-horizontal">
			<div class="row-fluid">
				<div class="control-group group-search">
					<label class="control-label req" for="code">用户名：</label>
					<div class="controls controls-row">
						<form:input path="code" cssClass="span6 required max-length-10"
							disabled="true" />
					</div>

					<label class="control-label req" for="name">姓名：</label>
					<div class="controls controls-row">
						<form:input path="name" cssClass="span6 required max-length-10" />
					</div>

					<label class="control-label req" for="terminalId">电话：</label>
					<div class="controls controls-row">
						<form:input path="terminalId"
							cssClass="span6 required validate-mobile-phone" maxlength="20" />
					</div>

					<label class="control-label req" for=email>邮箱：</label>
					<div class="controls controls-row">
						<form:input path="email" cssClass="span6 required validate-email"
							maxlength="50" />
					</div>

					<div class="control-group group-search">
						<label class="control-label" for="roleId">角色：</label>
						<div class="controls controls-row" style="width: 44.2%">	
							<form:select path="roleId" id="roleId" cssClass="multiselect"
								multiple="multiple" style="height:200px;width:300px;">
								<c:forEach items="${roles}" var="role">
                                    <c:if test="${role.id != 0}">
    									<c:choose>
    										<c:when test="${role.flag}">
    											<form:option value="${role.id}" selected="selected">${role.roleName}</form:option>
    										</c:when>
    										<c:otherwise>
    											<form:option value="${role.id}">${role.roleName}</form:option>
    										</c:otherwise>
    									</c:choose>
                                    </c:if>
								</c:forEach>
							</form:select>
							<label style="display: none;" id="advice-required-roleId"
								class="error"> 请选择.</label>
						</div>
					</div>
				</div>
			</div>

			<div class="control-group group-search">
				<label class="control-label"></label>
				<div class="controls controls-row">
					<button type="submit" class="btn btn-primary" id="sub">
						<i class="icon16 i-disk"></i> 保 存
					</button>
<!-- 					<button type="reset" class="btn"> -->
<!-- 						<i class="icon16 i-loop"></i> 重 置 -->
<!-- 					</button> -->
					<button class="btn" type="button" onclick='history.back();'>
						<i class="icon16 i-exit"></i> 返 回
					</button>
				</div>
			</div>
		</form:form>
	</div>
</div>

<%@ include file="../../includes/b.jsp"%>
