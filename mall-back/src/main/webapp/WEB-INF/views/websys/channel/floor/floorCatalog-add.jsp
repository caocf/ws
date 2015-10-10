<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    	$(document).ready(function(){
    		var url = document.referrer;
    		$("#backUrl").val(url);
    		
    		
    		$("#selectItems").click(function(){
    			selectGoods($("#items").val(),function(ids,names){
    				$('#items').val(ids.join(","));
    				$('#itemName').val(names.join("\n"));
    			},{areaCode:$("#regionCode").val()});
    		});
    		
    		
    	});
    	
    	function changeFloor(value){
    		$("#selectArea").text('');
    		if(-1 == value){
    			return;
    		}
    		var channel =  document.getElementById("channelId").value;
    		$.ajax({
				url: G_CTX_ROOT + '/websys/channel/catalog/selectArea?channel='+channel+'&floor='+value,
				success: function(data) {
					var areaAry = eval(data);
					for(var i=0;i<areaAry.length;i++){
						$("#selectArea").append("<input type='checkbox' name='regionCode' checked='checked' value='"+areaAry[i].regionCode+"'/>" +"<label>"+areaAry[i].regionName+"</label>");
					}
			}
			
			});
		}
    	
    	
    	function changeChannel(value){
    		$("#selectArea").text('');
    		$("#channelId").val(value);
    		$("#groupId").val(-1);
    	}
    	
    </script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5><c:if test="${method == 'add'}">添加分类配置</c:if><c:if test="${method == 'edit'}">修改分类配置</c:if></h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="add" commandName="channelCatalog"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <div class="form">
         <input type="hidden" id="backUrl" name="backUrl" />
        <form:hidden path="id"/>
            <div class="fields">
            	<c:if test="${method == 'add'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自动生成</div>
                </div>
                </c:if>
                <c:if test="${method == 'edit'}">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${channelCatalog.id}</div>
                </div>
                </c:if>
                <div class="field">
                    <div class="label">
                        <label for="channel" class="req">频道：</label>
                    </div>
                    <div class="select">
                        <select name="channel" id ="channel" onchange="changeChannel(this.value);">
                    	<c:forEach items="${channelMap}" var="item">
                        	    <option value="${item.key}" <c:if test="${channelCatalog.channel == item.key}">selected="selected"</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>		
              <input type="hidden" id="channelId" value="1"/>
                
                <div class="field">
                    <div class="label">
                        <label for="groupId" class="req">楼层：</label>
                    </div>
                    <div class="select">
                        <select name="groupId" id ="groupId" class="small required" onchange="changeFloor(this.value)">
                        <option value="-1">--请选择--</option>
                    	<c:forEach items="${groupMap }" var="item" >
                        	    <option value="${item.key }" <c:if test="${channelCatalog.groupId == item.key}">selected="selected"</c:if>>${item.value }</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>
                
                <div class="field">
                	<div class="label label-checkbox" >
						<label class="req">区域:</label>
					</div>
					<div class="checkboxes">
                   		 <div class="checkbox" id="selectArea">
                       		${htmlStr }
                   		 </div>
                   	 </div>
                </div>
                
                <div class="field" >
                    <div class="label">
                        <label for="itemId" class="req">选择商品:</label>
                    </div>
                    <div class="input">
                     <button type="button" id = "selectItems"  class="common_btn">请选择</button><br/>
                     <input type="hidden" name="items" id="items" value="${channelCatalog.itemId }" />
					<textarea id="itemName" name="itemOrgName" readonly="readonly" rows="10" cols="30" class="small required">${channelCatalog.itemOrgName}</textarea>
                   <span class="error" id="advice-required-items" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="orderIndex">排序：</label>
                    </div>
                    <div class="input">
                        <form:input path="orderIndex" cssClass="validate-integer" maxlength="5" />
                    </div>
                </div>
                
                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/websys/channel/catalog/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>
</body>
</html>