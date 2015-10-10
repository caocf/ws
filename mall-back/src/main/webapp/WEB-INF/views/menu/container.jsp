<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>移动商城管理后台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<spring:url value="/static/js/ext/css/ext-all.css"/>"/>
    <link rel="stylesheet" href="<spring:url value="/static/js/ext/menu/menu.css"/>"/>
</head>
<body scroll="no">

<script type="text/javascript" src="<spring:url value="/static/js/ext/ext-base.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/static/js/ext/ext-all.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/static/js/ext/menu/treeutil.js"/>"></script>
<script>
    var tu;
    Ext.onReady(function(){
        tu = new XTreeUtil('移动商城管理后台',
                '<spring:url value="/menu/tree"/>',
                'content-iframe',
                '<spring:url value="/"/>',
                '<spring:url value="/menu/welcome"/>',
                '<spring:url value="/menu/fav?p=1"/>'
        );
        tu.init();
        tu.show();
    });
</script>

</body>
</html>