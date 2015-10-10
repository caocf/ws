<%@ page language="java" pageEncoding="utf-8" isErrorPage="true"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory,java.io.*" %>
<%@ include file="../includes/importer.jsp"%>
<%response.setStatus(200);%>
<!doctype html>
<html>
<head>
<head>
    <ht:head title="500 - 系统内部错误"/>
</head>
<body>
<%
    Throwable ex = null;
    if (exception != null)
        ex = exception;
    if (request.getAttribute("javax.servlet.error.exception") != null)
        ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

    //记录日志
    Logger logger = LoggerFactory.getLogger("com.cplatform.uncatchedException");
    logger.error(ex.getMessage(), ex);

    // ByteArrayOutputStream eout = new ByteArrayOutputStream();
    // ex.printStackTrace(new PrintStream(eout));
    // String exceptionText = new String(eout.toByteArray());
%>
<div style="padding-left:120px; height:200px; background:url(${res}/images/warning.jpg) left center no-repeat;">
    <p style="font-size:14px; line-height:30px; padding:30px; color:#000000;">
    很抱歉，系统发生内部错误。（500）<br />
     <a href="javascript:history.go(-1)">返回</a>
     </p>
</div>
</body>
</html>