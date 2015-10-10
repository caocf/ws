<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="loginuser" value="${sessionScope.session_dc_user_key__}" />
<header id="header" class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="<c:url value="/home"/>"><img src="<c:url value="/static/img/logo.png"/>" alt="运维监控系统"></a>

            <div class="nav-no-collapse">
				<ul class="nav pull-right">
                    <li class="dropdown user">
                       <a data-toggle="dropdown" class="dropdown-toggle avatar" href="#">
                            <img alt="" src="<c:url value="/static/ui/images/avatars/default_portrait.jpg"/>">
                            <span class="more"><i class="icon16 i-arrow-down-2"></i></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/base/editpwd"/>"><i class="icon16 i-user"></i> 修改密码</a></li>
                            <li><a href="<c:url value="/logout"/>"><i class="icon16 i-exit"></i> 退出登录</a></li>
                        </ul>
                    </li>
                </ul>


            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</header>
<!-- End #header -->
