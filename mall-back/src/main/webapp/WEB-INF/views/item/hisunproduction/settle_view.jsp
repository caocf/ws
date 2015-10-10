<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
	    <ht:head/>
	</head>
	<body>
		<div id="content">
			<div class="box">
			    <div class="title">
			        <h5>商品协议资料查看</h5>
			    </div>
		        <div class="form">
		            <div class="fields">
		            	<c:if test="${not empty settle.contracteffdate}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="contracteffdate" >合同生效日期:</label>
		                    </div>
		                    <div class="input">
		                    <ct:time source="${settle.contracteffdate}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.contractexpdate}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="contractexpdate" >合同失效日期:</label>
		                    </div>
		                    <div class="input">
		                    <ct:time source="${settle.contractexpdate}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
		                    </div>
		                </div>
		                </c:if>
			            <div class="field">
		                    <div class="label noinput">
		                        <label for="productionname" >所属商户：</label>
		                    </div>
		                    <div class="input">
		                    ${storeName}
		                    </div>
		                </div>
		                <c:if test="${not empty settle.productionname}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="productionname" >商品名:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.productionname}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.productiontype}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="productiontype" >计费分类:</label>
		                    </div>
		                    <div class="input">
		                   ${settle.productiontypeName }
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.capitaltype3}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="capitaltype3" >积分:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.capitaltype3Name}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.capitaltype2}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="capitaltype2" >商城币:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.capitaltype2Name}
		                    </div>
		                </div>
		                </c:if>
						<c:if test="${not empty settle.capitaltype1}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="capitaltype1" >现金:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.capitaltype1Name}
		                    </div>
		                </div>
		                 <div class="field">
		                    <div class="label noinput">
		                        <label for="capitaltype4" >话费:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.capitaltype4Name}
		                    </div>
		                </div>
		                </c:if>
						<c:if test="${not empty settle.verifyflag}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="verifyflag" >验证标识:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.verifyflagName}
		                    </div>
		                </div>
		                </c:if>
						<c:if test="${not empty settle.verifysettleflag}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="verifysettleflag" >验证结算:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.verifysettleflagName}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.productionefftime}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="productionefftime" >产品上线时间:</label>
		                    </div>
		                    <div class="input">
		                    <ct:time source="${settle.productionefftime}" />
		                    </div>
		                </div>
		                </c:if>
						<c:if test="${not empty settle.productionexptime}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="productionexptime" >产品下线时间:</label>
		                    </div>
		                    <div class="input">
		                    <ct:time source="${settle.productionexptime}" />
		                    </div>
		                </div>
		                </c:if>
						<c:if test="${not empty settle.verifyexpdate}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="verifyexpdate" >验证截止日期:</label>
		                    </div>
		                    <div class="input">
		                    <ct:time source="${settle.verifyexpdate}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
		                    </div>
		                </div>
		                </c:if>
						<c:if test="${not empty settle.cityid}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="cityid" >地市编码:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.cityid}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.cityname}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="cityname" >地市名称:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.cityname}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.cityprofitrate}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="cityprofitrate" >地市分成比例:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.cityprofitrate}
		                    </div>
		                </div>
		                </c:if>
						<c:if test="${not empty settle.agentid}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="agentid" >代理商编码:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.agentid}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.agentname}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="agentname" >代理商名称:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.agentname}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.agentprofitrate}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="agentprofitrate" >代理商分成比例:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.agentprofitrate}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.price}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="price" >商品单价:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.price}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.settlementprice}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="settlementprice" >结算单价:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.settlementprice}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.settleperiod}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="settleperiod" >结算分期数:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.settleperiod}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.settleperiodtype}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="settleperiodtype" >结算分期类型:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.settleperiodtypeName}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.settledate1}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="settledate1" >结算时间:</label>
		                    </div>
		                    <div class="input">
		                    <ct:time source="${settle.settledate1}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.settlerate1}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="settlerate1" >结算比例:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.settlerate1}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.settledate2}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="settledate2" >结算时间2:</label>
		                    </div>
		                    <div class="input">
		                    <ct:time source="${settle.settledate2}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.settlerate2}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="settlerate2" >结算比例2:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.settlerate2}
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.settledate3}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="settledate3" >结算时间3:</label>
		                    </div>
		                    <div class="input">
		                    <ct:time source="${settle.settledate3}" sfmt="yyyyMMdd" tfmt="yyyy-MM-dd"/>
		                    </div>
		                </div>
		                </c:if>
		                <c:if test="${not empty settle.settlerate3}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="settlerate3" >结算比例3:</label>
		                    </div>
		                    <div class="input">
		                    ${settle.settlerate3}
		                    </div>
		                </div>
		                </c:if>   
		                <c:if test="${not empty settle.filePath}">
		                <div class="field">
		                    <div class="label noinput">
		                        <label for="filePath" >协议文件:</label>
		                    </div>
		                    <div class="input">
		                    	<a href="${ctx}/item/hisunproduction/downfile/${settle.id}">下载协议文件</a>
		                    </div>
		                </div>
		                </c:if>
		                <div  class="field">
		                	<div class="label noinput">
		                        <label for="filePath" >已关联的商品:</label>
		                    </div>
			                    <div class="input">
			                	<table class="datalist fixwidth">
						            <tr>
						            	<th nowrap="nowrap" width="70">清算系统商品编号</th>
						            	<th nowrap="nowrap" width="250">商品名称</th>
						            	<th nowrap="nowrap" width="70">商品编号</th>
						            	<th nowrap="nowrap" width="100">商品清算状态</th>
						            </tr>
						            <c:forEach items="${itemLinks}" var="item">
						            <tr>
						            	<!-- <td  nowrap="nowrap">${item.id}</td>  -->
						            	<td  nowrap="nowrap">${item.productionId}</td>
						            	<td class="ellipsis">${item.itemName}</td>
						            	<td class="ellipsis">${item.itemId}</td>
						                <td >${item.itemGyStatusName}</td>
						            </tr>
						            </c:forEach>
						        </table>
			                </div>
		                </div>
		                <div class="buttons">                 
			            	<input type="button" class="common_btn" onclick="${queryBackFun}" value="返回" />
			            </div>
		            </div>
		        </div>
			</div>
		</div>
	</body>
</html>