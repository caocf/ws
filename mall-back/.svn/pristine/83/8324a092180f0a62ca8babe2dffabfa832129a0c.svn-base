<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
<ht:head />
</head>
<body>
	<div id="content">
		<!-- forms -->
		<div class="box">
			<!-- box / title -->
			<div  class="title">
				<h5>
				 新建抽奖活动
				</h5>
			</div>
			<!-- end box / title -->
			<form:form method="post" id="fm" commandName="active" 
				htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" enctype="multipart/form-data">
				<div class="form">
					<form:hidden path="id" />
					<form:hidden path="status" />
					<form:hidden path="createTime" />
					<div class="fields">

						<div id="verifyTime" style="display:none">
							<div class="field">
								<div class="label">
									<label for="verifyStartTime" class="req">验证开始时间:</label>
								</div>
								<div class="input">
									<input type="text" id="verifyStartTime" name="verifyStartTime"
										class="date required" readOnly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'verifyStopTime\')||\'2020-10-01\'}'})" />
								</div>
							</div>
							<div class="field">
								<div class="label">
									<label for="verifyStopTime" class="req">验证结束时间:</label>
								</div>
								<div class="input">
									<input type="text" id="verifyStopTime" name="verifyStopTime" 
										class="date required" readOnly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'verifyStartTime\')}',maxDate:'2020-10-01'})" />
								</div>
							</div>
						</div>
						
						<div class="field">
							<div class="label">
								<label for="name" class="req">活动名称：</label>
							</div>
							<div class="input">
								<form:input path="name" cssClass="small required" maxlength="50" />
								<span class="error" id="advice-required-userPwd"
									style="display:none"></span>
							</div>
						</div>
						
						
						<div class="field">
							<div class="label">
								<label for="startTime" class="req">有效开始时间:</label>
							</div>
							<div class="input">
								<input type="text" id="startTime" name="startTime"
									 readOnly  class="date required" value="<ct:time source="${active.startTime }" tfmt="yyyy-MM-dd HH:mm:ss"/>"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'verifyStopTime\')||$dp.$D(\'stopTime\')||\'2020-10-01\'}'})" />
							</div>
						</div>
						<div class="field">
							<div class="label">
								<label for="stopTime" class="req">有效结束时间:</label>
							</div>
							<div class="input">
								<input type="text" id="stopTime" name="stopTime"  readOnly  class="date required"
									value="<ct:time source="${active.stopTime }" tfmt="yyyy-MM-dd HH:mm:ss"/>"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" />
							</div>
						</div>
						
				
						<div class="field">
		                    <div class="label">
		                        <label for="uploadfile" >目标库名单:</label>
		                    </div>
		                    <div class="input input-file">
		                        <input type="file" id="uploadfile" name="uploadfile" size="40"  class="validate-file-xls-xlsx"/>
		                        <span class="tip">xls格式</span>
		                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        <a href="${ctx }/static/resources/lotteryModel.xls">下载模版</a>
		                        <span class="error" id="advice-validate-file-uploadfile" style="display:none"></span>
		                        <span class="error" id="advice-required-uploadfile" style="display:none"></span>
		                        <br>
		                        <c:if test="${not empty lotteryTargets }">
		                        <span>下载    <a href="down?activeId=${active.id }">目标库活动用户.xls</a></span>
		                        </c:if>
		                    </div>
		                </div>
						<div class="field">
							<div class="label label-textarea">
								<label for="advice">活动介绍：</label>
							</div>
							<div class="input">
								<textarea id="activeDesc" name="activeDesc" class="max-length-100" 
									rows="8" cols="10">${active.activeDesc }</textarea>
							</div>
						</div> 
                
                		<div class="field">
							<div class="label label-radio">
								<label for="sort" class="req">抽奖模板:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" id="sort1" name="activeType" value="0" checked /><label for="sort1">转盘</label>
								</div>
								<span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
							</div>
						</div>	
						<div class="field">
							<div class="label label-radio">
								<label for="sort" class="req">奖区数量:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input class="validate-one-required" type="radio" id="sort1" name="prizeNum" value="8" <c:if test="${prizeNum == 8}">checked</c:if>/><label for="sort1">8个</label>
									<input class="validate-one-required" type="radio" id="sort1" name="prizeNum" value="12" <c:if test="${prizeNum == 12}">checked</c:if>/><label for="sort1">12个</label>
								</div>
								<span class="error" id="advice-validate-one-required-prizeNum" style="display:none"></span>
							</div>
						</div>	
						
						<div class="field">
		                    <div class="label">
		                        <label for="uploadfile" >背景大图:</label>
		                    </div>
		                    <div class="input input-file">
		                        <input type="file" id="bgImgfile" name="bgImg" size="40"  class="validate-file-png"/>
		                        <span class="tip">1.图片尺寸500x500px    2.图片类型仅支持png   3.图片背景为透明</span>
		                        <span class="error" id="advice-validate-file-bgImgfile" style="display:none"></span>
		                        <span class="error" id="advice-required-bgImgfile" style="display:none"></span>
		                    </div>
		                    <c:if test="${not empty bgImgPath }">
		                    <div style="margin: 3px;">
		                    	<img width="250px" height="250px" src="${bgImgPath }">
		                    </div>
		                    </c:if>
		                </div>
						
						<div class="field">
		                    <div class="label">
		                        <label for="uploadfile">指针图:</label>
		                    </div>
		                    <div class="input input-file">
		                        <input type="file" id="pointerImgfile" name="pointerImg" size="40"  class="validate-file-png"/>
		                        <span class="tip">1.图片尺寸215x215px    2.图片类型仅支持png   3.图片背景为透明  4.默认指针朝下</span>
		                        <span class="error" id="advice-validate-file-pointerImgfile" style="display:none"></span>
		                        <span class="error" id="advice-required-pointerImgfile" style="display:none"></span>
		                    </div>
		                    <c:if test="${not empty pointerImgPath }">
		                    <div style="margin: 3px;">
		                    	<img width="108px" height="108px" src="${pointerImgPath } ">
		                    </div>
		                    </c:if>
		                </div>
		                
		                <div class="field">
		                    <div class="label">
		                        <label for="uploadfile" >中奖背景提示图:</label>
		                    </div>
		                    <div class="input input-file">
		                        <input type="file" id="hitImgfile" name="hitImg" size="40"  class="validate-file-png-jpg"/>
		                        <span class="tip">1.图片尺寸400x200px    2.图片类型支持jpg、png</span>
		                        <span class="error" id="advice-validate-file-hitImgfile" style="display:none"></span>
		                        <span class="error" id="advice-required-hitImgfile" style="display:none"></span>
		                    </div>
		                    <c:if test="${not empty hitImgPath }">
		                    <div style="margin: 3px;">
		                    	<img width="200px" height="100px" src="${hitImgPath }">
		                    </div>
		                    </c:if>
		                </div>
                
                
                
						<div class="field">
							<div class="label label-radio">
								<label for="sort" class="req">未中奖设置:</label>
							</div>
							<div class="radios">
								<div class="radio">
									<input type="radio" id="sort1" name="unhit" value="0" <c:if test="${active.unhit == 0}">checked</c:if> /><label for="sort1">未中奖无奖品</label>
									<input type="radio" id="sort0" name="unhit" value="1" <c:if test="${active.unhit == 1}">checked</c:if> /><label for="sort0">参与抽奖既赠奖品</label>
								</div>
								<span class="error" id="advice-validate-one-required-radioex1" style="display:none"></span>
							</div>
						</div>	
						
						<div class="field">
							<div class="label">
								<label for="name" class="req">未中奖提示语：</label>
							</div>
							<div class="input">
								<form:input path="unhitMsg" cssClass="small required" maxlength="25" />
								<span class="error" id="advice-required-userPwd"
									style="display:none"></span>
							</div>
						</div>
						
						<div class="field">
							<div class="label">
								<label for="name" class="req">可中奖次数：</label>
							</div>
							<div class="input">
								<form:input path="hitLimit" cssClass="small required validate-number" maxlength="2" />
								<span class="error" id="advice-required-userPwd"
									style="display:none"></span>
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

</body>
</html>