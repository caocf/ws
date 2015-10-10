<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ct" uri="/cplatform-tag"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>验证码状态详情</title>
<style type="text/css">
table.form{
	width:100%;
	border-collapse:separate;
	border-spacing:1px;
	background:#ddd;
	margin:10px auto;
	font-size:14px
}
table.form td{
	padding:8px 5px;
	background:#fff
}
table.form td.label{
	padding:5px 5px;
	background:#eee;
	color:#000;
	font-weight:bold
}
table.form td.labelh1{
	padding:5px 5px;
	background:#ddd url(../img/thbg.png);
	color:#f60;
	font-weight:bold;
	text-shadow:1px 1px 1px #fff
}
table.form td p{
	line-height:22px
}
</style>
</head>
<body>
  <div class="wrapper">
      <div>
      <c:if test ="${!empty arrList}">
      <table class="form">
        <tr>
          <td class="labelh1" colspan="5">${goodName }验证码状态详情</td>
        </tr>
        <tr>
          <td class="label" width="20%">验证码</td>
          <td class="label" width="20%">购买时间</td>
          <td class="label" width="20%">截止时间</td>
          <td class="label" width="20%">验证时间</td>
          <td class="label" width="20%">验证状态</td>
        </tr>
       <c:forEach items="${arrList}" var="arr">
        <tr>
          <td><c:out value="${arr[1]}"></c:out></td>
          <td><ct:time source="${arr[2]}"  sfmt="yyyyMMddhhmmss" tfmt="yyyy-MM-dd HH:mm:ss"/></td>
          <td><ct:time source="${arr[3]}"  sfmt="yyyyMMddhhmmss" tfmt="yyyy-MM-dd HH:mm:ss"/></td>
          <td><ct:time source="${arr[3]}"  sfmt="yyyyMMddhhmmss" tfmt="yyyy-MM-dd HH:mm:ss"/></td>
          <td>
          <c:if test="${0 eq arr[5]}">未验证</c:if>
          <c:if test="${1 eq arr[5]}">已验证</c:if>
          <c:if test="${2 eq arr[5]}">已作废</c:if>
          <c:if test="${3 eq arr[5]}">已过期</c:if>
          </td>
        </tr>
        </c:forEach>

          <c:if test="${ flag}">
        <tr><td colspan="5"><font color="red">该商品验证码由第三方平台提供，状态无法实时更新，请及时关注，谢谢！</font></td></tr>
       </c:if>
      </table>
      </c:if>
      <c:if test="${empty arrList}">
      	<c:out value="暂无该订单验证码信息！"></c:out>
    </c:if>
      <div><a href="#" onclick="javascript:top.close();">返回</a></div>
    </div>
  </div>
</body>
</html>