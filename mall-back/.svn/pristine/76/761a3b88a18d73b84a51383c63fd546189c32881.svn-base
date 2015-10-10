<%@ page language="java" pageEncoding="utf-8"%>

<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>

    
</head>
<body>
<div id="content">
.<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5> 查看码信息<h5>
    </div>
    <!-- end box / title -->
     <input type="hidden" name="storeId" value="${storeId }" />
     <input type="hidden" name="itemId" value="${itemId }" />
        <div class="form">
              <div class="fields">
                <div class="field">
                    <div class="label">
                        <label for="uploadfile" >验证码:</label>
                    </div>
                    <div class="input ">
                        ${code.code }
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="areaId" >状态:</label>
                    </div>
                    <div class="input">
                    ${code.statusName }
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="startTime" >创建日期:</label>
                    </div>
                    <div class="input">
                   <ct:time source="${code.createDate}" />
                    </div>
                </div>
                 <div class="field">
                    <div class="label">
                        <label for="startTime" >有效日期:</label>
                    </div>
                    <div class="input">
                   <ct:time source="${code.validDate}" />
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="stopTime" >失效日期:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${code.expireDate}" />
                    </div>
                </div>
               
               
                <div class="field">
                    <div class="label">
                        <label for="areaId" >可验次数:</label>
                    </div>
                    <div class="input">
                    ${code.validTimes }
                    </div>
                </div>
                
                <div class="buttons">
                  
                    <a href="javascript:history.back();" class="btnAnchor">返回</a>
                </div>
            </div>
      </div>
      </div>
</div>
<!-- end forms -->







</body>
</html>