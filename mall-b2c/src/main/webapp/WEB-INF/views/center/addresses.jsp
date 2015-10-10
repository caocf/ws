<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link href="../css/layout.css" rel="stylesheet" type="text/css" />
    <link href="../css/usercenter.css" rel="stylesheet" type="text/css" />
    <link href="../css/cart.css" rel="stylesheet" type="text/css" />
    <link href="../css/sidebar.css" rel="stylesheet" type="text/css" />
    <link href="../js/lab/default/dialog.css" rel="stylesheet" type="text/css" />
    <link href="../js/lab/default/ibutton.css" rel="stylesheet" type="text/css" />
    <link href="../css/pro.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/static.chtml"></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/lab/jquery.dialog.js"></script>
    <script type="text/javascript" src="../js/cart-common.js"></script>
    <script type="text/javascript" src="../js/usercenter.js"></script>
    <script type="text/javascript" src="../js/ad.js"></script>
    <script type="text/javascript" src="../js/area_data.chtml"></script>
    <script type="text/javascript" src="../js/area.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/ext-ajax.js"></script>
    <script>
    	var web_url = '收货地址';
    	var webRoot = '${webRoot}';
    </script>
    <title>我的收货地址</title>
</head>
<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<div class="wrapper mauto" >
    <div class="breadcrumb"> <strong><a href="../">首页</a></strong><span>&nbsp;&gt;&nbsp;<a href="./orderManager.chtml">个人中心</a></span><span>&nbsp;&gt;&nbsp;我的收货地址</span></div>
    <div id="main" class="fr  w750">
        <div class="detail">
            <div class="detail">
                <div class="tab">
                    <div  class="tab_nav">
                        <ul class="tab">
                            <li class="curr"><a href="#info1">我的收货地址</a></li>
                        </ul>
                    </div>
                </div>
                <div class="content">
                    <c:if test="${!empty list}">
                        <table id="cyAddress" class="tab-list" width="100%">
                            <tr class="t-4">
                                <td  width="10%" class="cell-align-center">姓名</td>
                                <td  width="12%">所在地区</td>
                                <td>街道地址</td>
                                <td  width="12%" class="cell-align-center">邮编</td>
                                <td  width="12%">电话/手机</td>
                                <td  width="12%" class="thead-tbl-status">默认</td>
                                <td width="10%" class="cell-align-center">操作</td>
                            </tr>
                            <c:forEach items="${list}" var="data">
                                <tr <c:if test="${data[6] eq '1'}">class="active"</c:if>>
                                    <td class="cell-align-center">${data[1] }</td>
                                    <td>${data[2] }</td>
                                    <td>${data[3] }</td>
                                    <td class="cell-align-center">${data[4] }</td>
                                    <td>${data[5] }</td>
                                    <td style="color:blue" class="thead-tbl-status"><c:if test="${data[6] == '1'}" var="f"> <a href="" id="${data[0] }" class="col_link">当前默认地址</a></c:if><c:if test="${!f}"><a href="" id="${data[0] }" class="none">设为默认地址</a></c:if></td>
                                    <td class="cell-align-center">
				                        <a href="javascript:void(0);" onclick="editAddress(${data[0] });">修改</a> |
                                        <a onclick="delAddress(${data[0] })" href="#">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <form action="saveAddress.chtml" method="post" name="form1" id="form1" class="ext-ajaxForm">
                        <div class="neworderBox">
                            <div class="user_address">
                                <div class="addaddresBox">
                                    <div class="eidtaddress">
                                        <h3 class="title">添加新的常用地址</h3><input type="hidden" name="id" id="addressId" value=""/>
                                        <dl>
                                            <dt><span class="red">*</span> 配送地址：</dt>
                                            <dd>
                                                <select id="cmbProvince" name="province"><option value=''>请选择</option></select>
                                                <select id="cmbCity" name="city"><option value=''>请选择</option></select>
                                                <select id="cmbArea" name="region"><option value=''>请选择</option></select>
                                                <script>adrsInit($('#cmbProvince'), $('#cmbCity'), $('#cmbArea'), '');</script>
                                            </dd>
                                        </dl>
                                        <dl>
                                            <dt> 邮政编码：</dt>
                                            <dd><input type="text" id="dregion" name="zipcode" class="editInput" maxlength="10"/>                                    </dd>
                                        </dl>
                                        <dl>
                                            <dt><span class="red">*</span> 街道地址：</dt>
                                            <dd><span id="cityRegionName" style="float:left;"></span>
                                                <input type="text" id="dstreet" name="address" maxlength="100" style="border:1px solid #a4c2d9; height:22px; line-height:22px; padding:0 5px;width:500px;" /></dd>
                                        </dl>
                                        <dl>
                                            <dt><span class="red">*</span> 收货人姓名：</dt>
                                            <dd><input type="text" id="dname" name="name" maxlength="25" class="editInput" />                                    </dd>
                                        </dl>
                                        <dl>
                                            <dt><span class="red">*</span> 手机：</dt>
                                            <dd><input type="text" id="dmobileno" name="mobile" maxlength="20" class="editInput" /> </dd>
                                        </dl>
                                        <dl>
                                            <dt> 电话：</dt>
                                            <dd><input type="text" id="dcallno" name="phone" maxlength="20" class="editInput" /></dd>
                                        </dl>
                                        <dl>
                                            <dt></dt>
                                            <dd>
                                                <div class="editbtn">
                                                    <input type="button" onclick="confirmAddress()" class="btn" value="保存" />
                                                </div>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@include file="left_menu.jsp"%>
    <span class="clr"></span></div>
<script type="text/javascript" src="../js/foot.js"></script>
<script>
    $(function(){
        $("#address").addClass("col_link");
    })
</script>
</body>
</html>
