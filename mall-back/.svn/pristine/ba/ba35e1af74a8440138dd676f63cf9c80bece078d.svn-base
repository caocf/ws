<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!doctype html>
<html>
<head>
    <ht:head/>
    
     <script type="text/javascript">
    		$(document).ready(function(){
    		   $("#itemName").click(function(){
		    		   selectSinggleGood($("#itemId").val(),function(itemId,itemName){
		    					$id('itemId').value =itemId;
		    					$id('itemName').value =itemName;
		    					
		    					$id('itemName').focus();//?
    							$id('itemName').blur();//?
		    			});
	    		});
    			
    			$("#addItemRouter").click(function(){
    				$("#fm").submit();
    			});
    			//获取支付类型
    			var paytype = "${router.payType}";
    			//默认选中
    			if(paytype == null || paytype == "") {
    				$("input[name='payType']").get(0).checked = true;
    			}
    			
    			$("#radio-1").click(function(){
    				 $("#single").show();
    				 $("#bacth").hide();
    			});
    		    $("#radio-2").click(function(){
    				$("#single").hide();
    				$("#bacth").show();
    			});
    			$("#marketMsg").keyup(function(){
    			  if ($("#marketMsg").val().length > 130) {
    			    alert("商品营销短信，不得超过130字！");
    			  	$("#marketMsg").val($("#marketMsg").val().substring(0,130))
    			  }
				});
    			
    		});	
    		 function sub() {
		    	$("#filepath").val($("#uploaditemfile").val());
		    }
    </script>
    
</head>
<body>
<br />
<div id="search-menu">
      <ul>
         <ct:display model="store_list" btn="add_btn">
         <ht:menu-btn text="活动配置信息" url="/smsbuy/act/preEditAct?actId=${actId}" type="add"/>
         <ht:menu-btn text="商品指令配置" type="add"/>
        </ct:display>
    </ul>
    <br style="clear: left" />
</div>

<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>短信购商品指令配置</h5>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" action="addItemRouter" commandName="smsbuyItemRouter"  htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();" enctype="multipart/form-data">
        <div class="form">
        <form:hidden path="actId"/>
        <input type="hidden" name="id" value="${router.id }">
            <div class="fields">
	             <div class="field">
	                    <div class="label label-radio">
	                        <label>商品上传方式:</label>
	                    </div>
	                    <div class="radios">
	                        <div class="radio">
	                            <input type="radio" id="radio-1" name="uploadtype" checked="checked"  value="1"/><label for="radio-1">单个商品</label>
	                            <input type="radio" id="radio-2" name="uploadtype" value="2"/><label for="radio-2">批量商品</label>
	                        </div>
	                    </div>
	               </div>
            	   <div class="field" id="single">
           	   			<div class="field">
							<div class="label">
								<label for="itemName"  class="req">选择商品：</label>
							</div>
							<div class="input">
								<form:input path="itemName" cssClass="small required"
									maxlength="100" readonly="true" value="${router.itemName }" />
								<form:hidden path="itemId" value="${router.itemId }" />
								<span class="error" id="advice-required-itemName"
									style="display:none"></span>
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="rootSpcode"  class="req">特服号：</label>
							</div>
							<div style="margin-left: 120px">
								<select name="rootSpcode">
	                    			<c:forEach items="${sysSpcodeList }" var="item">
	                    			    <c:if test="${fn:contains(router.spCode,item.spcode)}">
	                    				<c:set var="tfh" value="${fn:length(item.spcode)}"></c:set>
	                    				</c:if>
	                        			<option value="${item.spcode }" <c:if test="${fn:contains(router.spCode,item.spcode)}">selected="selected"</c:if>>${item.spcode }</option>
	                        		</c:forEach>
	                    		</select>
	                    		<form:input path="spCode" cssClass="small required" maxlength="9"  style="width:55px" value="${fn:substring(router.spCode, tfh,-1)}" onkeyup="value=value.replace(/[^\d]/g,'')"  />
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="command"  class="req">指令内容：</label>
							</div>
							<div class="input">
								<form:input path="command" cssClass="small required" maxlength="50"   style="width:80px" value="${router.command }"/>
							</div>
						</div>
						<div class="field">
							<div class="label label-radio">
								<label>商品支付方式：</label>
							</div>
							<div class="radios">
								<div class="radio">
								<c:forEach items="${payTypeMap}" var="item" varStatus="index">
	                      	 			<input type="radio" id="radio1-${index.count }" name="payType" <c:if test="${item.key == router.payType }">checked="checked"</c:if> value="${item.key}"/>
	                      	 			<label for="radio1-${index.count }">${item.value }</label>
                       			</c:forEach>
                       			</div>
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="itemPrice">商品购买价格(元)：</label>
							</div>
							<div class="input">
							<form:input path="itemPrice" cssClass="validate-number" maxlength="9" style="width:55px" value="${router.itemPrice/100 }" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,4})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;" />
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="marketMsg" >商品营销短信：</label>
							</div>
							<div class="input">
							<textarea id="marketMsg" name="marketMsg" rows="8" cols="50">${router.marketMsg }</textarea>
	                    		<font color="red">商品营销短信，不得超过130字</font>
					            <span class="error" id="advice-validate-one-required-marketMsg" style="display:none"></span>
							</div>
						</div>
						<div class="buttons">
							<button type="button" class="common_btn" id="addItemRouter">增加指令</button>
	              		</div>
					</div>
					 <div class="field" id="bacth" style="display: none">
	                    <div class="label">
	                        <label for="uploaditemfile">批量上传商品:</label>
	                    </div>
	                    <div class="input input-file">
	                        <input type="file" id="uploaditemfile" name="uploaditemfile" size="40" class="required validate-file-xls-xlsx" />
	                        <span class="tip">excel格式</span>
	                        <span class="error" id="advice-validate-file-uploaditemfile" style="display:none"></span>
	                        <span class="error" id="advice-required-uploaditemfile" style="display:none"></span>
	                        <input type="hidden" id="filepath" name="filepath">
	                    </div>
	                    <div class="buttons">
	                    	<button type="submit" class="common_btn">提交</button>
	              			</div>
	                </div>
   			</div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>

<div class="container">
    <br/>
    <h3>商品指令列表</h3>

    <div class="mainbox">
        <c:if test="${not empty pageData}">

        <table class="datalist fixwidth">
            <tr>
                <th nowrap="nowrap" width="150">归属活动</th>
                <th nowrap="nowrap" width="100">商品名称</th>
                <th nowrap="nowrap" width="100">特服号</th>
                <th nowrap="nowrap" width="100">指令内容</th>
                <th nowrap="nowrap" width="100">商品支付方式</th>
                <th nowrap="nowrap" width="100">商品购买价格(元)</th>
                <th nowrap="nowrap" width="100">商品营销短信</th>
                <th nowrap="nowrap"width="100">操作</th>
            </tr>

            <c:forEach items="${pageData.data}" var="item">
            <tr>
                <td nowrap="nowrap">${item.actName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.itemName}</td>
                <td nowrap="nowrap" class="ellipsis">${item.spCode}</td>
                <td nowrap="nowrap" class="ellipsis">${item.command}</td>
                <td nowrap="nowrap">${item.payTypeName}</td>
                <td nowrap="nowrap">
                <fmt:formatNumber value="${item.itemPrice/100}" pattern="0.00"/>
                </td>
                <td nowrap="nowrap" class="ellipsis">${item.marketMsg}</td>
                <td nowrap="nowrap">
                <!--  
                <ct:display model="smsbuy_act_router_list" btn="view_btn">
                <a href="./viewRouter?id=${item.id}">查看</a>
                </ct:display>
                -->
                <ct:display model="smsbuy_act_router_list" btn="view_btn">
                <a href="./preAddItemRouter?actId=${actId }&id=${item.id}">修改</a>
                </ct:display>
                 <ct:display model="smsbuy_act_router_list" btn="del_btn">
                <a href="#" onclick="deleteItem('./deleteRouter.do?id=${item.id}');">删除</a>
                </ct:display>
                </td>
            </tr>
            </c:forEach>
        </table>

        <ht:page pageData="${pageData}" />

        </c:if>
        <c:if test="${empty pageData}">
        <div class="note">
            <p class="i">目前没有商品指令相关记录!</p>
        </div>
        </c:if>
    </div>
</div>
</body>
</html>