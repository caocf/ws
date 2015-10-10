<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="title" required="false" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${empty title ? '数据中心管理系统！' : title}</title>
    <link rel="stylesheet" href="<spring:url value="/static/css/reset.css"/>" />
    <link rel="stylesheet" href="<spring:url value="/static/css/style.css"/>" />

    <script src="<spring:url value="/static/global.js"/>" type="text/javascript" charset="utf-8"></script>


    <script src="<spring:url value="/static/js/jquery-1.8.3.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<spring:url value="/static/js/jquery.blockUI.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<spring:url value="/static/js/jquery.form.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<spring:url value="/static/js/lab/jquery.dialog.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<spring:url value="/static/js/date-picker/WdatePicker.js"/>" type="text/javascript" charset="utf-8"></script>

    <link href="<spring:url value="/static/js/lab/default/dialog.css"/>" rel="stylesheet" type="text/css" />
    <link href="<spring:url value="/static/js/lab/default/ibutton.css"/>" rel="stylesheet" type="text/css" />

    <!-- 必须在main.js前，绑定验证早于ajax form -->
    <script src="<spring:url value="/static/js/validation/validation_cn.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<spring:url value="/static/js/main.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<spring:url value="/static/js/wp-scroll.js"/>" type="text/javascript" charset="utf-8"></script>

    <script src="<spring:url value="/static/js/forhome.js"/>" type="text/javascript" charset="utf-8"></script>


    <link href="<spring:url value="/static/js/xheditor/googlemap/map.css"/>" rel="stylesheet" type="text/css" />
    <script src="<spring:url value="/static/js/xheditor/xheditor-1.2.1.min.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<spring:url value="/static/js/xheditor/xheditor_lang/zh-cn.js"/>" type="text/javascript" charset="utf-8"></script>
    <script src="<spring:url value="/static/js/xheditor/xheditor-setting.js"/>" type="text/javascript" charset="utf-8"></script>
    
    <!-- dialog -->
	<script type="text/javascript" src="<spring:url value="/static/js/zDrag.js"/>"></script>
    <script type="text/javascript" src="<spring:url value="/static/js/zDialog.js"/>"></script>
    
    <script src="<spring:url value="/static/js/jquery-levelSelect-ajax.js"/>" type="text/javascript" charset="utf-8"></script>
    
        <script src="<spring:url value="/static/js/jquery.ztree.all-3.5.js"/>" type="text/javascript" charset="utf-8"></script>
         <link href="<spring:url value="/static/js/zTreeStyle/zTreeStyle.css"/>" rel="stylesheet" type="text/css" />
    
    <script src="<spring:url value="/static/js/sidebar-follow-jquery.js"/>" type="text/javascript" charset="utf-8"></script>
    
</head>