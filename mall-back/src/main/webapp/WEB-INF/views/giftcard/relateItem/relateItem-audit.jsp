<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    <script type="text/javascript">
    $(function(){
		//获取来源地址
 		var url = document.referrer;
 		$("#backUrl").val(url);
	});
    </script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>审核</h5>
    </div>
    <!-- end box / title -->
    <form id="fm" class="required-validate" action="audit" method="post" accept-charset="utf-8">
        <input type="hidden" name="batchNo" value="${batchNo }">
        <input type="hidden" id="backUrl" name="backUrl" />
        <div class="form">
            <div class="fields">
          		<div class="field">
                    <div class="label">批次号：</div>
                    <div class="input">${giftRequired.batchNo}</div>
                </div>
                <div class="field">
                    <div class="label">卡型号：</div>
                    <div class="input">${giftRequired.modelNo}</div>
                </div>
                <div class="field">
                    <div class="label">客户名称：</div>
                    <div class="input">${giftRequired.requiredUser}</div>
                </div>
                <div class="field">
                    <div class="label">需要礼品卡数量：</div>
                    <div class="input">${giftRequired.cardNum}</div>
                </div>
                <div class="field">
                    <div class="label">期望发卡时间：</div>
                    <div class="input"><ct:time source="${giftRequired.issuingTime} " tfmt="yyyy-MM-dd"/></div>
                </div>
                <div class="field">
                    <div class="label">需求提交时间：</div>
                    <div class="input"><ct:time source="${giftRequired.createdTime}" tfmt="yyyy-MM-dd"/></div>
                </div>
                <div class="field">
                    <div class="label">礼品兑换方式：</div>
                    <div class="input">${giftRequired.exchangeModeName}</div>
                </div>
                		
                <div class="field">
                	<table class="datalist"  style="width:600px">
			            <tr width="600"> 
			               <th nowrap="nowrap" width="200">商品编号</th>
			               <th nowrap="nowrap" width="200">商品名称</th>
			               <th nowrap="nowrap" width="200">商户编号</th>
			               <th nowrap="nowrap" width="200">商户名称</th>
			            </tr>
			            <c:forEach items="${giftRequiredItems}" var="item">
			            <tr >
			            	<td nowrap="nowrap"  >${item.itemId}</td>
			                <td nowrap="nowrap"  class="ellipsis">${item.itemName}</td>
			                <td nowrap="nowrap"  >${item.storeId}</td>
			                <td nowrap="nowrap"  class="ellipsis">${item.storeName}</td>
			            </tr>
			            </c:forEach>
			        </table>
		        </div>
		        
                <div class="field">
                    <div class="label label-radio">
                        <label for="status">审核:</label>
                    </div>
                    <div class="radios">
                        <div class="radio">
                            <input class="validate-one-required" type="radio" id="radio-1" name="status" checked value="2"/><label for="radio-1">审核通过</label>
                            <input class="validate-one-required" type="radio" id="radio-2" name="status" value="3"/><label for="radio-2">审核失败</label>
                        </div>
                        <span class="error" id="advice-validate-one-required-status" style="display:none"></span>
                    </div>
                </div>
                <div class="field">
                    <div class="label label-textarea">
                        <label for="advice">审核意见：</label>
                    </div>
                    <div class="input">
                     <textarea id="auditMsg" name="auditMsg" class="max-length-50" rows="8" cols="50"></textarea>
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
    </form>
</div>
<!-- end forms -->
</div>
</body>
</html>