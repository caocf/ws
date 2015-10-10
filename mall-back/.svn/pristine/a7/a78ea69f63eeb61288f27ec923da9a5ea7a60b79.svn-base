<%@page import="org.springframework.web.multipart.MaxUploadSizeExceededException"%><%@ page contentType="text/html;charset=UTF-8" %><%response.setStatus(200);%><%
    Throwable ex = null;
    if (request.getAttribute("javax.servlet.error.exception") != null)
        ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
    
    
    if (ex instanceof MaxUploadSizeExceededException) {
        //( (MaxUploadSizeExceededException) ex );
        out.println("{error:'超过上传文件大小限制'}");
    }
%>