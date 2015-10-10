<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
    <head>
        <ht:head/>
    </head>
<frameset id="fst1" rows="100%*" cols="260px,*" border="0" framespacing="0" frameborder="NO" bordercolor="#0099CC"">
    <frame id="goodsTreeFrame" name="goodsTreeFrame" src="<spring:url value="/sys/type/type_container_goods"/>">    
    <frame id="goodsListFrame" name="goodsListFrame" src="<spring:url value="/sys/type/jumpGoods/0/0"/>">
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>