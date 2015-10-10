<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
 	<script type="text/javascript">
 	
		
</script>
    </head>
<body>

<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>添加奖品</h5>
    </div>
	
		  <form:form action="${ctx}/lottery/prize/view/{id}" method="post" id="fm" commandName="prize" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate">
        <input type="hidden" name ="groupFlag" value="1" />
          <form:hidden path="id"/>
        <div class="form">
					<div class="fields">
					  <div class="field">
                    <div class="label noinput">奖品ID：</div>
                    <div class="input">${prize.id}</div>
                </div>
	
 					<div class="field" >
							<div class="label">
								<label for="name" >奖品名称:</label>
							</div>
							<div class="input">
							${prize.name}&nbsp;
					</div>	
								
								
				<div class="field" >
							<div class="label">
								<label for="active" >归属活动:</label>
							</div>
							<div class="input">
							${activeName }&nbsp;
							</div>
					</div>	
					
						<div class="field" >
							<div class="label">
								<label for="hitLevel" >奖区编号:</label>
							</div>
							<div class="input">
								${prize.hitLevel}&nbsp;
							</div>
					</div>	
						<div class="field" >
							<div class="label">
								<label for="hitProbability">中奖几率:</label>
							</div>
							<div class="input">
								${prize.hitProbability }&nbsp;
							</div>
					</div>	
						<div class="field" >
							<div class="label">
								<label for="hitLimit" >每人中奖限制:</label>
							</div>
							<div class="input">
								${prize.hitLimit}&nbsp;
							</div>
					</div>	
						<div class="field" >
							<div class="label">
								<label for="hitMsg" >中奖提示语:</label>
							</div>
							<div class="input">
								${prize.hitMsg}&nbsp;
							</div>
					</div>	
						<div class="field" >
							<div class="label">
								<label for="numbers" >奖品数量:</label>
							</div>
							<div class="input">
								${prize.numbers}&nbsp;
							</div>
					</div>	
								<div class="field" >
							<div class="label">
								<label for="position" >奖品位置:</label>
							</div>
							<div class="input">
								${prize.position}&nbsp;
							</div>
					</div>	
					
								<div class="field">
							<div class="label label-textarea">
								<label for="descrption">备注：</label>
							</div>
							<div class="input">
								${prize.description}&nbsp;
							</div>
						</div>		
						
				
						<div class="buttons">
							
                    			<input type="button" class="common_btn" onclick="history.back();" value="返回" />
							
						</div>	
				
				
    </form:form>
</div>

</div>
</body>
</html>