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
        <h5>查看物流信息</h5>
    </div>
    <!-- end box / title -->
   
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${sysLogistics.id }</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="name" >物流名称：</label>
                    </div>
                    <div class="input"><!-- small medium large -->
                    ${sysLogistics.name }
                    </div>
                </div>

               
                <div class="field">
                    <div class="label">
                        <label for="interFace" >接口地址：</label>
                    </div>
                    <div class="input">
                    ${sysLogistics.interFace}
                    </div>
                </div>

                 <div class="field">
                    <div class="label label-radio">
                        <label for="unitType" >是否有效:</label>
                    </div>
                    <div class="radios">
                        ${sysLogistics.isValidName}
                </div>

             
                
            </div>
        </div>
</div>
<!-- end forms -->
</div>


</body>
</html>