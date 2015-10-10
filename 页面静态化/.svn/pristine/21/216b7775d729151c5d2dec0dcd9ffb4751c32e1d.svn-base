<%@ page language="java" pageEncoding="utf-8" isErrorPage="true"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory,java.io.*,java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%response.setStatus(200);%>
<%--
    Throwable ex = null;
    if (exception != null)
        ex = exception;
    if (request.getAttribute("javax.servlet.error.exception") != null)
        ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

    //记录日志
    Logger logger = LoggerFactory.getLogger("500.jsp");
    logger.error(ex.getMessage(), ex);
    
    
    ByteArrayOutputStream eout = new ByteArrayOutputStream();
    ex.printStackTrace(new PrintStream(eout));
    String exceptionText = new String(eout.toByteArray());
    
--%>
<jsp:directive.include file="../includes/importer.jsp" />
<ht:head title="500 - 系统内部错误" />

<div class="tips_warn" style="height:200px;">
    <p>很抱歉，系统发生内部错误。（500）</p>
    <p style="font-size:14px;">如果您当前访问的地址没有问题，请联系我们：mplus@c-platform.com</p>
</div>

<ht:foot />