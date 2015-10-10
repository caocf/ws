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
				<form:form  id="fm" method="post" commandName="channel"
					htmlEscape="true" acceptCharset="utf-8"
					cssClass="required-validate form-horizontal">
					<div class="row-fluid">
						<div class="control-group group-search">
							<label class="control-label req" for="channel_name">
								渠道名称：
							</label>
							<div class="controls controls-row">
								<form:input path="channel_name" id="channelName" maxlength="20" />
							</div>
							<label id="repeat-error" style="margin-left: 130px">
								<span id="repeat-error2" style="color: #FFFFFF;"></span>
							</label>
							<label class="control-label" for="channel_desc">
								渠道描述：
							</label>
							<div class="controls controls-row">
								<form:textarea path="channel_desc" id="channelDesc" />
							</div>
								<label class="control-label req" for="is_flag">
									是否为空：
								</label>
							<div class="controls controls-row ">
								<form:select path="is_flag" id="isFlag">
									<form:option value="0">否</form:option>
									<form:option value="1">是</form:option>
								</form:select>
                   			</div>
						</div>
						<form:hidden path="channel_id" id="channelID"/>
						<div class="control-group group-search">
							<div class="controls controls-row">
								<button type="button" class="btn btn-primary" onclick="doUpdateChannel();">
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
