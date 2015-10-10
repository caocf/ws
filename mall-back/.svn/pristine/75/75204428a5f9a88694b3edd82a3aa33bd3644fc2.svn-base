<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
	<head>
		<ht:head />
		<script type="text/javascript">
	$().ready(function() {
		$("#all").click(function() {
			if ($("#all").attr("checked")) {
				$("input[type=checkbox]").attr("checked", "checked");
			} else {
				$("input[type=checkbox]").removeAttr("checked");
			}

		});
	});

	function auditItemRouter(el) {
		if ($("input[name='id']:checked").length == 0) {
			alert("请选择需要审核的商品");
			return false;
		}
		var str = "";
		$("input[name='id']:checked").each(function() {
			str += $(this).val() + ",";
		})
		dealInfo("确认审核通过！", "${ctx}/smsbuy/item/auditItemRouter?id=" + str);
	}

	function rebutItemRouter(el) {
		if ($("input[name='id']:checked").length == 0) {
			alert("请选择需要驳回的商品");
			return false;
		}
		var str = "";
		$("input[name='id']:checked").each(function() {
			str += $(this).val() + ",";
		});
		dealInfo("确认驳回！", "${ctx}/smsbuy/item/rebutItemRouter?id=" + str);
	}
	
	function downItemRouter(el) {
		if ($("input[name='id']:checked").length == 0) {
			alert("请选择需要下线的商品");
			return false;
		}
		var str = "";
		$("input[name='id']:checked").each(function() {
			str += $(this).val() + ",";
		});
		dealInfo("确认下线！", "${ctx}/smsbuy/item/downItemRouter?id=" + str);
	}
</script>
	</head>
	<body>
		<br />
		<div id="search-menu">
			<ul>
				<ht:menu-btn type="search" />
			</ul>
			<br style="clear: left" />
		</div>
		<div class="queryContainer">
			<form name="queryForm" id="queryForm" action="?" method="get">
				<table>
					<tr>
					
					
						<td align="right">
							活动编号：
						</td>
						<td>
							<input type="text" name="actId" value="${param.actId}"
								class="txt validate-number" style="width: 120px" />
						</td>
					
						<td align="right">
							活动名称：
						</td>
						<td>
							<input type="text" name="actName" value="${param.actName}"
								class="txt" style="width: 120px" />
						</td>
					
						<td align="right">
							商品状态：
						</td>
						<td width="200">
							<select name="itemStatus">
								<option value="">
									--请选择--
								</option>
								<c:forEach items="${itemStatus}" var="itemsta">
									<option value="${itemsta.key }"
										<c:if test="${param.itemStatus == itemsta.key}">selected="selected"</c:if>>
										${itemsta.value }
									</option>
								</c:forEach>
							</select>
						</td>
					
					
					
					</tr>
					<tr>
					
						
						
						
						<td align="right">
							特服号：
						</td>
						<td>
							<input type="text" name="spCode" value="${param.spCode}"
								class="txt" style="width: 120px" />
						</td>
					
					<td align="right">
							商品名称：
						</td>
						<td>
							<input type="text" name="itemName" value="${param.itemName}"
								class="txt" style="width: 120px" />
						</td>
					
						<td>
							<ct:btn type="search" />
						</td>
						<td>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div class="container">
			<br />
			<h3>
				商品指令列表
			</h3>
			<div class="mainbox">
				<c:if test="${not empty pageData}">
					<table class="datalist fixwidth">
						<colgroup>
							<col width="80"></col>
							<col width="150"></col>
							<col width="100"></col>
							<col width="100"></col>
							<col width="80"></col>
							<col width="100"></col>
							<col width="100"></col>
							<col width="100"></col>
						</colgroup>
						<tr>
							<td colspan="8" align="left">
							<ct:display model="smsbuy_item_audit_list" btn="audit_btn">
								<ht:table-action-btn text="审核通过"
									url="/smsbuy/item/auditItemRouter" onAction="auditItemRouter" />
								<ht:table-action-btn text="驳回"
									url="/smsbuy/item/rebutItemRouter" onAction="rebutItemRouter" />
							</ct:display>
							<ct:display model="smsbuy_item_audit_list" btn="down_line_btn">
									<ht:table-action-btn text="下线"
									url="/smsbuy/item/downItemRouter" onAction="downItemRouter" />
							</ct:display>
							</td>
						</tr>
						<tr>
							<th nowrap="nowrap">
								<input type="checkbox" class="checkall" />
								活动编号
							</th>
							<th nowrap="nowrap">
								活动名称
							</th>
							<th nowrap="nowrap">
								商品名称
							</th>
							<th nowrap="nowrap">
								特服号
							</th>
							<th nowrap="nowrap">
								指令内容
							</th>
							<th nowrap="nowrap">
								商品支付方式
							</th>
							<th nowrap="nowrap">
								商品购买价格(元)
							</th>
							<th nowrap="nowrap">
								商品状态
							</th>
						</tr>
						<c:forEach items="${pageData.data}" var="item">
							<tr>
								<td nowrap="nowrap">
									<input type="checkbox" name="id" value="${item.id }" />
									${item.actId}
								</td>
								<td class="ellipsis">
									${item.actName}
								</td>
								<td class="ellipsis">
									${item.itemName}
								</td>
								<td nowrap="nowrap">
									${item.spCode}
								</td>
								<td nowrap="nowrap">
									${item.command}
								</td>
								<td nowrap="nowrap">
									${item.payTypeName}
								</td>
								<td nowrap="nowrap">
									<fmt:formatNumber value="${item.itemPrice/100}" pattern="0.00" />
								</td>
								<td nowrap="nowrap">${item.itemStatusName}</td>
							</tr>
						</c:forEach>
					</table>
					<ht:page pageData="${pageData}" />
				</c:if>
				<c:if test="${empty pageData}">
					<div class="note">
						<p class="i">
							目前没有相关记录!
						</p>
					</div>
				</c:if>
			</div>

		</div>

	</body>
</html>