<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../includes/if.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	</head>

	<body>
		<div class="row-fluid">
			<div class="span12">
				<form:form method="post" id="fm" commandName="channel"
					htmlEscape="true" acceptCharset="utf-8"
					cssClass="required-validate form-horizontal">
					<div class="row-fluid">
						<div class="control-group group-search">
							<label class="control-label req" for="channel_name">
								渠道名称：
							</label>
							<div class="controls controls-row">
								<form:input path="channel_name" maxlength="20" />
							</div>
							<label id="repeat-error" style="margin-left: 130px">
								<span id="repeat-error2" style="color: #FFFFFF;"></span>
							</label>
							<label class="control-label" for="channel_desc">
								渠道描述：
							</label>
							<div class="controls controls-row">
								<form:textarea path="channel_desc" />
							</div>
							<div>
								<label class="control-label req" for="is_flag">
									是否为空：
								</label>
								<select id="is_flag" name="is_flag"
									style="width: 208px; height: 50%;">
									<option value="0" name="is_flag"
										<c:if test="${empty is_flag || is_flag == 0}"> selected="selected"</c:if>>
										否
									</option>
									<option value="1" name="is_flag"
										<c:if test="${not empty is_flag && is_flag == 1}"> selected="selected"</c:if>>
										是
									</option>
								</select>
							</div>

						</div>

						<div class="control-group group-search">
							<div class="controls controls-row">
								<button type="button" class="btn btn-primary" id="sub"
									onclick=doSubmit();>
									<i class="icon16 i-disk"></i> 保 存
								</button>

								<button class="btn" type="button" onclick=doCancel();>
									<i class="icon16 i-exit"></i> 返 回
								</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</body>
</html>
