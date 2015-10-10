<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="title" required="false" %>

<meta charset="utf-8">
<title>${empty title ? '' : title}${empty title ? '' : ' - '}运维监控平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value="/static/ui/css/font.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/css/bootstrap/bootstrap.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/css/bootstrap/bootstrap-responsive.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/css/icons.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/css/genyx-theme/jquery.ui.genyx.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/js/plugins/forms/uniform/uniform.default.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/js/plugins/forms/switch/bootstrapSwitch.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/js/plugins/forms/datepicker/datepicker.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/js/plugins/forms/select2/select2.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/js/plugins/forms/multiselect/ui.multiselect.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/js/plugins/ui/jgrowl/jquery.jgrowl.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/etc/zTreeStyle/zTreeStyle.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/css/app.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/ui/css/custom.css"/>" rel="stylesheet" type="text/css" />

<!--[if IE 8]>
<link href="<c:url value="/static/ui/css/ie8.css"/>" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if lt IE 9]>
<script src="<c:url value="/static/ui/js/html5shiv.js"/>"></script>
<![endif]-->
<link rel="shortcut icon" href="<c:url value="/static/ui/images/ico/favicon.ico"/>">
<script src="<c:url value="/static/global.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/jquery.1.8.3.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/jquery-ui.1.10.2.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/conditionizr.min.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/bootstrap/bootstrap.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/core/nicescroll/jquery.nicescroll.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/core/jrespond/jRespond.min.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/forms/datepicker/bootstrap-datepicker.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/forms/datepicker/bootstrap-datepicker.zh-CN.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/forms/uniform/jquery.uniform.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/forms/autosize/jquery.autosize.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/forms/inputlimit/jquery.inputlimiter.1.3.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/forms/mask/jquery.mask.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/forms/switch/bootstrapSwitch-ext.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/forms/select2/select2.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/forms/multiselect/ui.multiselect.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/plugins/ui/jgrowl/jquery.jgrowl.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/etc/ext.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/etc/main.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/etc/jquery.form.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/etc/validation_cn.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/etc/wp-scroll.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/etc/zDrag.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/etc/zDialog.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/etc/jquery-levelSelect-ajax.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/etc/jquery.ztree.all-3.5.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/jquery.genyxAdmin.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/js/app.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/Charts/FusionCharts.js"/>" type="text/javascript" charset="utf-8"></script>

<!-- 统计图 -->
<script src="<c:url value="/static/ui/chart/excanvas.compiled.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/static/ui/chart/highstock.js"/>" type="text/javascript" charset="utf-8"></script>
 
