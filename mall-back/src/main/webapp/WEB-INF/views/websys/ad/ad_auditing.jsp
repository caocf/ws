<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    </head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>广告审核<h5>
    </div>
    <!-- end box / title -->
    <form id="fm" class="required-validate" action="${ctx}/websys/ad/ad_auditing" method="post" accept-charset="utf-8">
        <input type="hidden" name="id" value="${sysAd.id}">
        <div class="form">
            <div class="fields">                
                <div class="field">
                  <div class="label">
                        <label for="taskName">审核广告：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                       ${sysAd.adName}
                    </div>
              </div>
              
                <div class="field">
                    <div class="label label-radio">
                        <label>审核:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input type="radio" id="radio-1" name="status" checked="checked"  value="1"/><label for="radio-1">通过</label>
                            <input type="radio" id="radio-2" name="status" value="2"/><label for="radio-2">拒绝</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
                    </div>
                </div>               
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/ad/ad_list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- end forms -->
</div>
</body>
</html>