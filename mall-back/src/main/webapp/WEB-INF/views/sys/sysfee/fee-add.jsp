<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
		<ht:head />
		<script type="text/javascript">
		$().ready(function() {
			//获取来源地址
	 		var url = document.referrer;
	 		$("#backUrl").val(url);
		  //选择商户
		   	$("#storeName").click(function(){
				selectStore("storeId","storeName",'',{status:'3'});
			});
		
		});
		</script>

	</head>
	<body>
		

<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
       <div class="title">
					<h5>
						<c:if test="${method == 'add'}">商户费率分类录入</c:if>
						<c:if test="${method == 'edit'}">商户费率分类编辑</c:if>
					</h5>
				</div>
    </div>
    <!-- end box / title -->
    <form:form method="post" id="fm" commandName="fee" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
     
        <div class="form">
            
					<c:if test="${not empty sysFee.id}">
						<form:hidden path="id" />
					</c:if>
					<input type="hidden" id="backUrl" name="backUrl" />
			<div class="fields">
					<div class="field">
							<div class="label">
								<label for="name" class="req">
									所属商户：
								</label>
							</div>
							
							<div class="input">
								<!-- small medium large -->
								 <form:hidden path="storeId" />
								 <c:if test="${not empty fee.storeName  }">
								 ${fee.storeName }
								 </c:if>
								 <c:if test="${ empty fee.storeName  }">
								<form:input path="storeName"  maxlength="100" readonly="true" cssClass="small required"  />
								 </c:if>
								<span class="error" id="advice-required-storeName" style="display:none"></span>
							</div>
						</div>

					
						<div class="field">
						
							<div class="label">
								<label for="name" class="req">
									费率分类名称：
								</label>
							</div>
							
							<div class="input">
								<!-- small medium large -->
								<form:input path="name"  cssClass="small required min-length-0 max-length-25" maxlength="25"  />
								<span class="error" id="advice-required-name" style="display:none"></span>
                        <span class="error" id="advice-min-length-name" style="display:none"></span>
                        <span class="error" id="advice-max-length-name" style="display:none"></span>
                        <span class="error" id="advice-server-name" style="display:none"></span>
							</div>
						</div>
						
<!-- 
						<div class="field">
							<div class="label">
								<label for="fee" class="req">
									费率:
								</label>
							</div>
							<div class="input">
								<form:input path="fee" cssClass="small required validate-number" maxlength="20" 
									 />元
							<span class="error" id="advice-required-fee" style="display:none"></span>
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="valid">
									是否有效:
								</label>
							</div>
							
						<div class="select">
                        <select id="type" name="valid" class="required">
                        	<option value="1" <c:if test="${fee.valid == '1'}">selected="selected"</c:if>>是</option>
                        	<option value="0" <c:if test="${fee.valid == '0'}">selected="selected"</c:if>>否</option>
                        </select>
                        <span class="error" id="advice-required-type" style="display:none"></span>
                        <span class="error" id="advice-server-type" style="display:none"></span>
                    </div>
							
						</div>
 -->
 						<input type="hidden" name="valid" value="1">
						<div class="field">
							<div class="label">
								<label for="decrfee" class="req">
									描述:
								</label>
							</div>
							<div class="input">
								<form:input path="decrfee" cssClass="small required min-length-0 max-length-25" maxlength="50"  />
								<span class="error" id="advice-required-decrfee" style="display:none"></span>
                        <span class="error" id="advice-min-length-decrfee" style="display:none"></span>
                        <span class="error" id="advice-max-length-decrfee" style="display:none"></span>
                        <span class="error" id="advice-server-decrfee" style="display:none"></span>
							</div>
						</div>

				

					
              
               	<div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />                    
                    <input type="button" class="common_btn" onclick="history.back();" value="返回" />
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- end forms -->
</div>


		<script type="text/javascript">
	ajaxFormSubmit('#fm');
	//-->
</script>



	</body>
</html>