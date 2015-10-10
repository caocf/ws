<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="includes/top.jsp" %>
<div class="alert alert-info">
    <p>
        单击
        <a href="${fn:escapeXml(param.service)}${fn:indexOf(param.service, '?') eq -1 ? '?' : '&'}ticket=${serviceTicketId}">这里</a> ，
        以便访问到目标应用。
    </p></div>
</div>
<%@ include file="includes/bottom.jsp" %>

