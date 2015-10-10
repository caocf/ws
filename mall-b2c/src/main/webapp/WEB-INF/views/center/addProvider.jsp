<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="../css/base.css" rel="stylesheet" type="text/css"/>
    <link href="../css/usercenter.css" rel="stylesheet" type="text/css"/>
    <link href="../css/addprovider.css" type="text/css" rel="stylesheet">
    <link href="../css/axure_rp_pages.css" type="text/css" rel="stylesheet">
    <link href="../css/jquery-ui-themes.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../js/usercenter.js"></script>
    <script type="text/javascript" src="../js/cart-common.js"></script>
    <script type="text/javascript" src="../js/addprovider.js"></script>
    <title>申请成为供应商</title>
</head>
<body id="usercenter">
<script type="text/javascript" src="../js/head.js"></script>
<br/><br/>

<div id="main_container">
    <div id="u2" class="u2_container">
        <div id="u2_img" class="u2_normal detectCanvas"></div>
        <div id="u3" class="u3" style="visibility:hidden;">
            <div id="u3_rtf"></div>
        </div>
    </div>
    <div id="u4" class="u4">
        <div id="u4_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#990D1B;">商户名称：</span>
        </p></div>
    </div>
    <div id="u5" class="u5">
        <div id="u5_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#990D1B;">经营范围：</span>
        </p></div>
    </div>
    <INPUT id="supplierName" type=text value="" class="u6">

    <INPUT id="businessScope" type=text value="" class="u7">

    <INPUT id="address" type=text value="" class="u8">

    <div id="u9" class="u9">
        <div id="u9_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#990D1B;">商户所在地：</span>
        </p></div>
    </div>
    <INPUT id="mobile" type=text value="" class="u10">

    <div id="u11" class="u11">
        <div id="u11_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#990D1B;">联系方式</span><span
                style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#990D1B;">：</span>
        </p></div>
    </div>
    <div id="u12" class="u12">
        <div id="u12_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#990D1B;">手机号和</span><span
                style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#990D1B;">座机</span><span
                style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#990D1B;">至少填一个</span>
        </p></div>
    </div>
    <div id="u13" class="u13">
        <div id="u13_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#990D1B;">手机号：</span>
        </p></div>
    </div>
    <div id="u14" class="u14">
        <div id="u14_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#990D1B;">座机：</span>
        </p></div>
    </div>
    <INPUT id="phone_pre" type=text value="" class="u15">

    <div id="u16" class="u16">
        <div id="u16_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#990D1B;">联系人：</span>
        </p></div>
    </div>
    <INPUT id="contact" type=text value="" class="u17">

    <div id="u18" class="u18">
        <div id="u18_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">-</span>
        </p></div>
    </div>
    <div id="u19" class="u19">
        <div id="u19_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:bold;font-style:normal;text-decoration:none;color:#990D1B;">验证码：</span>
        </p></div>
    </div>
    <INPUT id="verifyCode" type=text value="" class="u20">

    <div id="u21" class="u21_container">
        <div id="u21_img" class="u21_normal detectCanvas">
            <img id="verifyCodeImg" src="getVerifyCodeImg.chtml"/>
        </div>
        <div id="u22" class="u22" style="visibility:hidden;">
            <div id="u22_rtf"></div>
        </div>
    </div>
    <div id="u23" class="u23">
        <div id="u23_rtf" class="change_verify_code"><p style="text-align:left;">
            <a href="#" style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:underline;color:#0000FF;">
                看不清？换一张
            </a>
        </p></div>
    </div>
    <div id="submit_button" class="u24_container">
        <div id="u24_img" class="u24_normal detectCanvas"></div>
        <div id="u25" class="u25" style="visibility:hidden;">
            <div id="u25_rtf"></div>
        </div>
    </div>
    <INPUT id="phone_middle" type=text value="" class="u26">

    <div id="u27" class="u27">
        <div id="u27_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">-</span>
        </p></div>
    </div>
    <INPUT id="phone_ext" type=text value="" class="u28">

    <div id="u29" class="u29">
        <div id="u29_rtf"><p style="text-align:left;"><span
                style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#990D1B;">区</span><span
                style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#990D1B;">号-电话号码-分机</span>
        </p></div>
    </div>
    <div id="u30" class="u30_container">
        <div id="u30_img" class="u30_normal detectCanvas"></div>
        <div id="u31" class="u31" style="visibility:hidden;">
            <div id="u31_rtf"></div>
        </div>
    </div>
    <div id="u32" class="u32_container">
        <script type="text/javascript" src="../js/foot.js"></script>
    </div>
    <div id="u34" class="u34_container">
        <div id="u34_img" class="u34_normal detectCanvas"></div>
        <div id="u35" class="u35" style="visibility:hidden;">
            <div id="u35_rtf"></div>
        </div>
    </div>
</div>
</body>
</html>
